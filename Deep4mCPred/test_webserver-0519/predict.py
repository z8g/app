#coding=utf-8
import sys
import pandas as pd
import numpy as np
import argparse
import csv
import os,shutil
import subprocess

import time

os.environ["CUDA_DEVICE_ORDER"] = "PCI_BUS_ID"  
os.environ["CUDA_VISIBLE_DEVICES"] = "-1"
import keras.layers.core as core
import keras.layers.convolutional as conv
import keras.models as models
import keras.utils.np_utils as kutils
from keras.layers.embeddings import Embedding
from keras.layers.recurrent import LSTM
from keras.layers import merge
from keras.layers import pooling
from keras.models import Model
#from keras.engine.topology import Merge
from keras.callbacks import EarlyStopping, ModelCheckpoint,Callback
from keras.layers import Dense, Dropout, Activation, Flatten, Input, MaxPooling1D, AveragePooling1D, Bidirectional
from keras.optimizers import Adam, SGD, Nadam, Adamax, Adadelta, Adagrad, RMSprop
from keras.regularizers import l2, l1

from DProcess import convertRawToXY
from attention import Attention,myFlatten
from AnalyseFASTA import analyseFASTAPredict,analyseFixedPredict

import copy

def train_cnn(learning_rate,weight_decay,dropoutMerge1,dropoutMerge2,dropoutMerge3,dropoutMerge4,
                dropout_dense1,dense1,dense3,conv1_filter,conv2_filter,conv3_filter,earlystop,batch_size,
                input_row_One_Hot,input_col_One_Hot,input_row_ANF_NCP,input_col_ANF_NCP,
                input_row_CKSNAP_NCP,input_col_CKSNAP_NCP,input_row_PSTNPss_NCP,input_col_PSTNPss_NCP,
                beta_1,beta_2,epsilon,attentionhidden_x,attentionhidden_xr,attention_reg_x,attention_reg_xr,
                best_model, weightsModel, predict=False,train=False,transfer=False):

    ############################################# Model ###################################################

    # choose optimazation
    #optimization = Adam(lr=learning_rate, beta_1=beta_1, beta_2=beta_2, epsilon=epsilon)
    optimization = Adamax(lr=learning_rate, beta_1=beta_1, beta_2=beta_2, epsilon=epsilon)

    #####################################One_Hot#####################################

    main_input = Input(shape=(input_row_One_Hot,input_col_One_Hot))

    x1 = conv.Convolution1D(conv1_filter, 1, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(main_input)
    
    x3 = conv.Convolution1D(conv1_filter, 3, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(main_input)

    x5 = conv.Convolution1D(conv1_filter, 5, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(main_input)

    mergeX = merge([x1, x3, x5], mode='concat', concat_axis=-1)
    mergeX = Dropout(dropoutMerge1)(mergeX)
    mergeX = MaxPooling1D(3,1)(mergeX)

    mergeX = conv.Convolution1D(conv2_filter, 1, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(mergeX)

    y3 = conv.Convolution1D(conv2_filter, 3, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(mergeX)

    y5 = conv.Convolution1D(conv2_filter, 5, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(mergeX)

    mergeY = merge([mergeX, y3, y5], mode='concat', concat_axis=-1)
    mergeY = Dropout(dropoutMerge2)(mergeY)
    mergeY = MaxPooling1D(5,1)(mergeY)

    mergeY = conv.Convolution1D(conv3_filter, 1, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(mergeY)

    z3 = conv.Convolution1D(conv3_filter, 3, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(mergeY)

    z5 = conv.Convolution1D(conv3_filter, 5, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(mergeY)

    mergeZ = merge([mergeY,z3,z5], mode='concat', concat_axis=-1)
    mergeZ = Dropout(dropoutMerge3)(mergeZ)
    mergeZ = MaxPooling1D(3,1)(mergeZ)

    mergeZ_reshape=core.Reshape((mergeZ._keras_shape[2],mergeZ._keras_shape[1]))(mergeZ)

    decoder_mergeZ = Attention(hidden=attentionhidden_x,activation='linear',init='he_normal',W_regularizer=l1(attention_reg_x)) # success  
    decoded_mergeZ=decoder_mergeZ(mergeZ)
    output_mergeZ = myFlatten(mergeZ._keras_shape[2])(decoded_mergeZ)

    decoder_mergeZr = Attention(hidden=attentionhidden_xr,activation='linear',init='he_normal',W_regularizer=l1(attention_reg_xr))
    decoded_mergeZr=decoder_mergeZr(mergeZ_reshape)
    output_mergeZr = myFlatten(mergeZ_reshape._keras_shape[2])(decoded_mergeZr)


    q1 = Bidirectional(LSTM(conv3_filter, consume_less='gpu',init='he_normal',W_regularizer=l2(weight_decay) ))(mergeZ)
    #q1 = Dropout(dropout4)(q1)

    q1r = Bidirectional(LSTM(conv3_filter, consume_less='gpu',init='he_normal',W_regularizer=l2(weight_decay) ))(mergeZ_reshape)
    #q1 = Dropout(dropout4)(q1)



    #####################################ANF_NCP#####################################
        
    input_A = Input(shape=(input_row_ANF_NCP,input_col_ANF_NCP))

    x1_A = conv.Convolution1D(conv1_filter, 1, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(input_A)

    x3_A = conv.Convolution1D(conv1_filter, 3, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(input_A)

    x5_A = conv.Convolution1D(conv1_filter, 5, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(input_A)

    mergeX_A = merge([x1_A, x3_A, x5_A], mode='concat', concat_axis=-1)
    mergeX_A = Dropout(dropoutMerge1)(mergeX_A)
    mergeX_A = MaxPooling1D(3,1)(mergeX_A)

    mergeX_A = conv.Convolution1D(conv2_filter, 1, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(mergeX_A)

    y3_A = conv.Convolution1D(conv2_filter, 3, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(mergeX_A)

    y5_A = conv.Convolution1D(conv2_filter, 5, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(mergeX_A)

    mergeY_A = merge([mergeX_A, y3_A, y5_A], mode='concat', concat_axis=-1)
    mergeY_A = Dropout(dropoutMerge2)(mergeY_A)
    mergeY_A = MaxPooling1D(5,1)(mergeY_A)

    mergeY_A = conv.Convolution1D(conv3_filter, 1, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(mergeY_A)

    z3_A = conv.Convolution1D(conv3_filter, 3, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(mergeY_A)

    z5_A = conv.Convolution1D(conv3_filter, 5, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(mergeY_A)

    mergeZ_A = merge([mergeY_A,z3_A,z5_A], mode='concat', concat_axis=-1)
    mergeZ_A = Dropout(dropoutMerge3)(mergeZ_A)
    mergeZ_A = MaxPooling1D(3,1)(mergeZ_A)

    mergeZ_A_reshape=core.Reshape((mergeZ_A._keras_shape[2],mergeZ_A._keras_shape[1]))(mergeZ_A)

    decoder_mergeZ_A = Attention(hidden=attentionhidden_x,activation='linear',init='he_normal',W_regularizer=l1(attention_reg_x)) # success  
    decoded_mergeZ_A=decoder_mergeZ_A(mergeZ_A)
    output_mergeZ_A = myFlatten(mergeZ_A._keras_shape[2])(decoded_mergeZ_A)

    decoder_mergeZ_Ar = Attention(hidden=attentionhidden_xr,activation='linear',init='he_normal',W_regularizer=l1(attention_reg_xr))
    decoded_mergeZ_Ar=decoder_mergeZ_Ar(mergeZ_A_reshape)
    output_mergeZ_Ar = myFlatten(mergeZ_A_reshape._keras_shape[2])(decoded_mergeZ_Ar)


    q1_A = Bidirectional(LSTM(conv3_filter, consume_less='gpu',init='he_normal',W_regularizer=l2(weight_decay) ))(mergeZ_A)
    #q1_A = Dropout(dropout4)(q1_A)

    q1r_A = Bidirectional(LSTM(conv3_filter, consume_less='gpu',init='he_normal',W_regularizer=l2(weight_decay) ))(mergeZ_A_reshape)
    #q1_A = Dropout(dropout4)(q1_A)



    #####################################CKSNAP_NCP#####################################
        
    input_C = Input(shape=(input_row_CKSNAP_NCP,input_col_CKSNAP_NCP))

    x1_C = conv.Convolution1D(conv1_filter, 1, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(input_C)

    x3_C = conv.Convolution1D(conv1_filter, 3, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(input_C)

    x5_C = conv.Convolution1D(conv1_filter, 5, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(input_C)

    mergeX_C = merge([x1_C, x3_C, x5_C], mode='concat', concat_axis=-1)
    mergeX_C = Dropout(dropoutMerge1)(mergeX_C)
    mergeX_C = MaxPooling1D(3,1)(mergeX_C)

    mergeX_C = conv.Convolution1D(conv2_filter, 1, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(mergeX_C)

    y3_C = conv.Convolution1D(conv2_filter, 3, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(mergeX_C)

    y5_C = conv.Convolution1D(conv2_filter, 5, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(mergeX_C)

    mergeY_C = merge([mergeX_C, y3_C, y5_C], mode='concat', concat_axis=-1)
    mergeY_C = Dropout(dropoutMerge2)(mergeY_C)
    mergeY_C = MaxPooling1D(5,1)(mergeY_C)

    mergeY_C = conv.Convolution1D(conv3_filter, 1, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(mergeY_C)

    z3_C = conv.Convolution1D(conv3_filter, 3, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(mergeY_C)

    z5_C = conv.Convolution1D(conv3_filter, 5, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(mergeY_C)

    mergeZ_C = merge([mergeY_C,z3_C,z5_C], mode='concat', concat_axis=-1)
    mergeZ_C = Dropout(dropoutMerge3)(mergeZ_C)
    mergeZ_C = MaxPooling1D(3,1)(mergeZ_C)

    mergeZ_C_reshape=core.Reshape((mergeZ_C._keras_shape[2],mergeZ_C._keras_shape[1]))(mergeZ_C)

    decoder_mergeZ_C = Attention(hidden=attentionhidden_x,activation='linear',init='he_normal',W_regularizer=l1(attention_reg_x)) # success  
    decoded_mergeZ_C=decoder_mergeZ_C(mergeZ_C)
    output_mergeZ_C = myFlatten(mergeZ_C._keras_shape[2])(decoded_mergeZ_C)

    decoder_mergeZ_Cr = Attention(hidden=attentionhidden_xr,activation='linear',init='he_normal',W_regularizer=l1(attention_reg_xr))
    decoded_mergeZ_Cr=decoder_mergeZ_Cr(mergeZ_C_reshape)
    output_mergeZ_Cr = myFlatten(mergeZ_C_reshape._keras_shape[2])(decoded_mergeZ_Cr)


    q1_C = Bidirectional(LSTM(conv3_filter, consume_less='gpu',init='he_normal',W_regularizer=l2(weight_decay) ))(mergeZ_C)
    #q1_C = Dropout(dropout4)(q1_C)

    q1r_C = Bidirectional(LSTM(conv3_filter, consume_less='gpu',init='he_normal',W_regularizer=l2(weight_decay) ))(mergeZ_C_reshape)
    #q1_C = Dropout(dropout4)(q1_C)


    #####################################PSTNPss_NCP#####################################
        
    input_P = Input(shape=(input_row_PSTNPss_NCP,input_col_PSTNPss_NCP))

    x1_P = conv.Convolution1D(conv1_filter, 1, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(input_P)

    x3_P = conv.Convolution1D(conv1_filter, 3, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(input_P)

    x5_P = conv.Convolution1D(conv1_filter, 5, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(input_P)

    mergeX_P = merge([x1_P, x3_P, x5_P], mode='concat', concat_axis=-1)
    mergeX_P = Dropout(dropoutMerge1)(mergeX_P)
    mergeX_P = MaxPooling1D(3,1)(mergeX_P)

    mergeX_P = conv.Convolution1D(conv2_filter, 1, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(mergeX_P)

    y3_P = conv.Convolution1D(conv2_filter, 3, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(mergeX_P)

    y5_P = conv.Convolution1D(conv2_filter, 5, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(mergeX_P)

    mergeY_P = merge([mergeX_P, y3_P, y5_P], mode='concat', concat_axis=-1)
    mergeY_P = Dropout(dropoutMerge2)(mergeY_P)
    mergeY_P = MaxPooling1D(5,1)(mergeY_P)

    mergeY_P = conv.Convolution1D(conv3_filter, 1, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(mergeY_P)

    z3_P = conv.Convolution1D(conv3_filter, 3, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(mergeY_P)

    z5_P = conv.Convolution1D(conv3_filter, 5, activation='relu',border_mode="same",W_regularizer=l2(weight_decay) ,init='he_normal')(mergeY_P)
    
    mergeZ_P = merge([mergeY_P,z3_P,z5_P], mode='concat', concat_axis=-1)
    mergeZ_P = Dropout(dropoutMerge3)(mergeZ_P)
    mergeZ_P = MaxPooling1D(3,1)(mergeZ_P)

    mergeZ_P_reshape=core.Reshape((mergeZ_P._keras_shape[2],mergeZ_P._keras_shape[1]))(mergeZ_P)

    decoder_mergeZ_P = Attention(hidden=attentionhidden_x,activation='linear',init='he_normal',W_regularizer=l1(attention_reg_x)) # success  
    decoded_mergeZ_P=decoder_mergeZ_P(mergeZ_P)
    output_mergeZ_P = myFlatten(mergeZ_P._keras_shape[2])(decoded_mergeZ_P)

    decoder_mergeZ_Pr = Attention(hidden=attentionhidden_xr,activation='linear',init='he_normal',W_regularizer=l1(attention_reg_xr))
    decoded_mergeZ_Pr=decoder_mergeZ_Pr(mergeZ_P_reshape)
    output_mergeZ_Pr = myFlatten(mergeZ_P_reshape._keras_shape[2])(decoded_mergeZ_Pr)


    q1_P = Bidirectional(LSTM(conv3_filter, consume_less='gpu',init='he_normal',W_regularizer=l2(weight_decay) ))(mergeZ_P)
    #q1_P = Dropout(dropout4)(q1_P)

    q1r_P = Bidirectional(LSTM(conv3_filter, consume_less='gpu',init='he_normal',W_regularizer=l2(weight_decay) ))(mergeZ_P_reshape)
    #q1_P = Dropout(dropout4)(q1_P)


    #####################################Merge#####################################
    mergeQ = merge([q1, q1r, output_mergeZ, output_mergeZr,q1_A, q1r_A, output_mergeZ_A, output_mergeZ_Ar,q1_C, q1r_C, output_mergeZ_C, output_mergeZ_Cr,q1_P, q1r_P, output_mergeZ_P, output_mergeZ_Pr], mode='concat', concat_axis=-1)
    mergeQ = Dropout(dropoutMerge4)(mergeQ)

    aa=Dense(dense1,init='he_normal',activation='relu')(mergeQ)
    aa=Dropout(dropout_dense1)(aa)
    
    #w = Dense(dense2,init='he_normal', activation='relu')(aa)
    #w = Dropout(dropout_dense2)(w)

    w2 = Dense(dense3,init='he_normal', activation='relu')(aa)
    #w = Dropout(dropout5)(w)

    out=Dense(2,init='he_normal',activation='softmax')(w2)


    cnn=Model([main_input,input_A,input_C,input_P],out)
    cnn.compile(loss='binary_crossentropy',optimizer=optimization,metrics=['accuracy'])

    print cnn.summary()

    return cnn



def getArgs():
    
    __version__ = 'V 1.0'
    parser = argparse.ArgumentParser(description='Deep4mcPred')
    parser._action_groups.pop()

    required = parser.add_argument_group('Required arguments')
    required.add_argument('-input', '--inputfile', required=True, type=str, help='DNA sequences to be predicted in fasta format.')
    required.add_argument('-s', '--speciesarg', required=True, type=str, metavar='<PATH>', help='Input file in FASTA format (*** Must be specified ***)')
    required.add_argument('-o', '--outresult', required=True, type=str, metavar='<PATH>', help='Give the output path of Deep4mcPred (*** Must be specified ***)')
    required.add_argument('-wm', '--weightsModelarg', required=False, type=str, metavar='<PATH>', help='The type of protease (*** Must be specified ***)',default=None)
    required.add_argument("--inputType", required=True,
                        choices=['file', 'fixed'], help="select input file type")

    args = parser.parse_args()

    return args


def main():
    args = getArgs()
    inputfile = args.inputfile
    species = args.speciesarg
    out_result = args.outresult

    earlystop = 20
    batch_size = 512

    input_row_One_Hot = 41
    input_col_One_Hot = 5

    input_row_ANF_NCP = 41
    input_col_ANF_NCP = 9

    input_row_CKSNAP_NCP = 150
    input_col_CKSNAP_NCP = 17

    input_row_PSTNPss_NCP = 39
    input_col_PSTNPss_NCP = 25

    beta_1 = 0.9
    beta_2 = 0.999
    epsilon = 1e-08

    attentionhidden_x = 10
    attentionhidden_xr = 8
    attention_reg_x = 0.151948
    attention_reg_xr = 2

    learning_rate = 0.002
    weight_decay = 0.00005

    conv1_filter = 32
    dropoutMerge1 = 0.5

    conv2_filter = 136
    dropoutMerge2 = 0.1

    conv3_filter = 48
    dropoutMerge3 = 0.1

    dropoutMerge4 = 0.5

    dense1 = 64
    dropout_dense1 = 0.5

    dense3 = 8

    weightsModel = args.weightsModelarg
    best_model = weightsModel

    mycsvTrain = species+ '_train_cnn.txt'
    train_All = pd.read_csv(mycsvTrain)

    window = 20

    if args.inputType == 'file':
        predict_data,ids,poses,focuses=analyseFASTAPredict(inputfile,window,'-',focus='C')
    if args.inputType == 'fixed':
        predict_data,ids,poses,focuses=analyseFixedPredict(inputfile)
    

    testLabel = predict_data.iloc[:,0]

    #####################################ENAC#####################################
    testX_One_Hot,testY_One_Hot = convertRawToXY(train_All.as_matrix(),predict_data.as_matrix(),codingMode='ENAC')

    #####################################ANF_NCP_EIIP_Onehot#####################################
    testX_ANF_NCP,testY_ANF_NCP = convertRawToXY(train_All.as_matrix(),predict_data.as_matrix(),codingMode='ANF_NCP_EIIP_Onehot')

    #####################################CKSNAP_NCP_EIIP_Onehot#####################################
    testX_CKSNAP_NCP,testY_CKSNAP_NCP = convertRawToXY(train_All.as_matrix(),predict_data.as_matrix(),codingMode='CKSNAP_NCP_EIIP_Onehot')
    
    #####################################PSTNPss_NCP_EIIP_Onehot#####################################
    testX_PSTNPss_NCP,testY_PSTNPss_NCP = convertRawToXY(train_All.as_matrix(),predict_data.as_matrix(),codingMode='PSTNPss_NCP_EIIP_Onehot')



    predict_model = train_cnn(learning_rate=learning_rate,weight_decay=weight_decay,dropoutMerge1=dropoutMerge1,
                            dropoutMerge2=dropoutMerge2,dropoutMerge3=dropoutMerge3,dropoutMerge4=dropoutMerge4,
                            dropout_dense1=dropout_dense1,dense1=dense1,dense3=dense3,conv1_filter=conv1_filter,
                            conv2_filter=conv2_filter,conv3_filter=conv3_filter,earlystop = earlystop,batch_size = batch_size,
                            input_row_One_Hot = input_row_One_Hot,input_col_One_Hot = input_col_One_Hot,
                            input_row_ANF_NCP = input_row_ANF_NCP,input_col_ANF_NCP = input_col_ANF_NCP,
                            input_row_CKSNAP_NCP = input_row_CKSNAP_NCP,input_col_CKSNAP_NCP = input_col_CKSNAP_NCP,
                            input_row_PSTNPss_NCP = input_row_PSTNPss_NCP,input_col_PSTNPss_NCP = input_col_PSTNPss_NCP,
                            beta_1 = beta_1,beta_2 = beta_2,epsilon = epsilon,
                            attentionhidden_x = attentionhidden_x,attentionhidden_xr = attentionhidden_xr,
                            attention_reg_x = attention_reg_x,attention_reg_xr = attention_reg_xr, 
                            best_model = best_model,weightsModel = weightsModel,predict = True, train = False,transfer=False)

    predict_model.load_weights(weightsModel)

    predictproba=predict_model.predict([testX_One_Hot,testX_ANF_NCP,testX_CKSNAP_NCP,testX_PSTNPss_NCP])
               
    poses=poses+1

    speciesArr = species.split('/')
    speciess = speciesArr[len(speciesArr)-1]

    if(speciess == 'A'):
        speciess2 = 'A.thaliana'
    elif(speciess == 'C'):
        speciess2 = 'C.elegan'
    elif(speciess == 'D'):
        speciess2 = 'D.melanogaster'
    elif(speciess == 'E'):
        speciess2 = 'E.coli'
    elif(speciess == 'Gsub'):
        speciess2 = 'G.subterraneus'
    elif(speciess == 'Gpick'):
        speciess2 = 'G.pickeringii'

    typess = np.full((testLabel.shape[0],1),speciess2)
    results=np.column_stack((ids,poses,focuses,predictproba[:,1],typess))
    result=pd.DataFrame(results)
    result.to_csv(out_result+ "_custom2.txt", index=False, header=None, sep='\t',quoting=csv.QUOTE_NONNUMERIC)


if __name__ == "__main__":
    main()         
   