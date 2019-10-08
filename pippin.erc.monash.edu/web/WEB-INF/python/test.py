#!/usr/bin/env python
# coding: utf-8

# In[1]:


# get_ipython().system('pip3 install numpy')
# get_ipython().system('pip3 install xgboost')
# get_ipython().system('pip3 install pandas')
# get_ipython().system('pip3 install iFeature')


# In[12]:


import iFeature as ifea
import os
import re
import xgboost as xgb
import pandas as pd
import numpy
import pickle
import sys

input_file = "E:/Java/java/pippin.erc.monash.edu/web/WEB-INF/python/user00001/input.fasta"
#input_file = sys.argv[1]
print(sys.argv[1])
indexOfLast = input_file.rfind("/")
outputFolder = input_file[0:indexOfLast]
print(outputFolder)


# # download ifeature values
def calculateIFeature(fname):
    os.system("python ./iFeature.py --file %s --type %s --order alphabetically --out %s" % (
    input_file, "APAAC", outputFolder + "/calculatedFeatures/APAAC.txt"))
    os.system("python ./iFeature.py --file %s --type %s --order alphabetically --out %s" % (
    input_file, "CKSAAGP", outputFolder + "/calculatedFeatures/CKSAAGP.txt"))
    os.system("python ./iFeature.py --file %s --type %s --order alphabetically --out %s" % (
    input_file, "CKSAAP", outputFolder + "/calculatedFeatures/CKSAAP.txt"))
    os.system("python ./iFeature.py --file %s --type %s --order alphabetically --out %s" % (
    input_file, "CTDD", outputFolder + "/calculatedFeatures/CTDD.txt"))
    os.system("python ./iFeature.py --file %s --type %s --order alphabetically --out %s" % (
    input_file, "DPC", outputFolder + "/calculatedFeatures/DPC.txt"))
    os.system("python ./iFeature.py --file %s --type %s --order alphabetically --out %s" % (
    input_file, "GTPC", outputFolder + "/calculatedFeatures/GTPC.txt"))
    os.system("python ./iFeature.py --file %s --type %s --order alphabetically --out %s" % (
    input_file, "NMBroto", outputFolder + "/calculatedFeatures/NMBroto.txt"))
    os.system("python ./iFeature.py --file %s --type %s --order alphabetically --out %s" % (
    input_file, "DDE", outputFolder + "/calculatedFeatures/DDEtest.txt"))


calculateIFeature(input_file)

# get_ipython().system('python ./iFeature.py --file input.fasta --type DDE --out calculatedFeatures/DDEtest.txt')
# !python ./iFeature.py --file input.fasta --type DDE --out output/DDEtest.txt


# read the sequences
from Bio import SeqIO

record_dict = SeqIO.to_dict(SeqIO.parse(input_file, "fasta"))

# define the motif feature patterns
motif = {
    "MKTLLLTLVVVTIVCLDLGY": "MKTLLLTLVVVTIVCLDLGY",
    "WGGQGTPKDATDRCCFVHDCCY": "WGGQGTPKDATDRCCFVHDCCY",
    "KPGVDIKCCSTDKCN": "KPGVDIKCCSTDKCN",
    "MFTVFLLVVLATTVV": "MFTVFLLVVLATTVV",
    "IVCGKNDPCLRAICECDRAAAICFRENLNTYNKNYMYYSDSRCTEESEQC": "IVCGKNDPCLRAICECDRAAAICFRENLNTYNKNYMYYSDSRCTEESEQC",
    "CPPGZNJCYKKTWCD": "CPPGZNJCYKKTWCD",
    "PS00118": "CC[A-OQ-Z][A-Z]H[A-FH-KM-XZ][A-Z]C",
    "PS00119": "[LIVMA]C[ABDEGHJKNOQRUXZ]CD[A-FH-RT-Z][A-FH-Z][A-MO-Z][A-Z][A-PRT-Z]C",
    "PS00272": "GC[A-Z]{1,3}CP[A-Z]{8,10}CC[A-Z]{2}[PDEN]",
    "PS00280": "F[A-Z]{2}[A-HJ-Z]GC[A-Z]{6}[FY][A-Z]{5}C",
    "PS60004": "C[SREYKLIMQVN][A-Z]{2}[DGWET][A-Z][FYSPKV]C[GNDSRHTP][A-Z]{1,5}[NPGSMTAHF][GWPNIYRSKLQ][A-Z]CC[STRHGD][A-Z]{0,2}[NFLWSRYIT]C[A-Z]{0,3}[VFGAITSNRKL][FLIKRNGH][VWIARKF]C",
    "PS60005": "C[A-Z]{2}[EPSAGT][A-Z]{3}C[GSNDL][A-Z]{0,3}[PILV][A-Z][FPNDSG][GQ][A-Z]CC[A-Z]{3,4}C[FLVIA][A-Z]{1,2}[FVIWA]C",
    "PS60013": "CC[TGN][PFG][PRG][A-Z]{0,2}C[KRS][DS][RK][RQW]C[KR][PD][MLQH][A-Z]?[KR]CC ",
    "PS60014": "CC[SHYN][A-Z]?[PRG][RPATV]C[ARMFTNHG][A-Z]{0,4}[QWHDGENLFYVP][RIVYLGSDW]C",
    "PS60015": "C[LAV][A-Z][DEK][A-Z]{3}C[A-Z]{6,7}CC[A-Z]{4}C[A-Z]C[A-Z]{5}C[A-Z]C",
    "PS60021": "C[KALRVG][A-Z][A-LN-Z][A-Z]{1,3}C[A-Z]{4,6}CC[A-Z]{4,6}C[A-Z]{4}[ERK]WC",
    "PS60022": "C[A-Z]{1,4}[FLIV][SEP]C[DE][EIQ][A-Z]{4,7}C[A-Z]{0,7}C[KST][A-Z]{4,18}C[YK][A-Z]{1,3}C",
    "PS60023": "CQCC[A-Z]{2}N[GA][FY]CS",
    "PS60025": "GEEE[A-Z]{2}[KE][A-DF-Z]{2}[A-Z]?E[A-Z][ILA]RE"
}
motifName = []
for key in motif:
    motifName = motifName + [key]

# get the motif feature values of the example sequence
motifValue = {}
for proteinID in record_dict.keys():
    motifValue[proteinID] = []
    for feature in motif:
        match = re.search(r'%s' % motif[feature], str(record_dict[proteinID].seq))
        if match:
            motifValue[proteinID].append("1")
        else:
            motifValue[proteinID].append("0")

# In[8]:


# get 102 sequence feature values
path = "102iFeatureTypes.txt"
lines = [line for line in open(path)]
mylist = []

for line in lines:
    line = line.replace("\n", "")
    splited = line.split("\t")
    mylist.append(splited)

features = []
iFeatureValues = {}
for key in motifValue.keys():
    iFeatureValues[key] = []

for i in range(len(mylist)):
    path = outputFolder + "/calculatedFeatures/" + mylist[i][0] + ".txt"
    with open(path) as f:
        content = f.readlines()
    for j in range(len(mylist[i])):
        if j > 0:
            if (mylist[i][j] in content[0]):
                sss = content[0].split("\t");
                ind = 0
                for s in sss:
                    if s == mylist[i][j]:
                        break;
                    ind = ind + 1
                if "DDE" == mylist[i][0]:
                    sss[ind] = sss[ind] + "_DDE"
                if "DPC" == mylist[i][0]:
                    sss[ind] = sss[ind] + "_DPC"
                if "NMBROTO" == mylist[i][0]:
                    sss[ind] = sss[ind] + "nmbroto"
                features = features + [sss[ind]]

                for k in range(1, len(content)):
                    valuesss = []
                    ddd = content[k].split("\t")
                    valuesss.append(ddd[ind])
                    iFeatureValues[ddd[0]] = iFeatureValues[ddd[0]] + [ddd[ind]]

# In[9]:


# combine motif and ifeature together
featureName = motifName + features

for key in motifValue.keys():
    motifValue[key] = motifValue[key] + iFeatureValues[key]

# In[10]:


# training and save the model to a file

# fix the order of the features to the same order in the train
df = pd.read_csv("PrePostFinal111.csv")
X = df.iloc[:, :-1]
y = df.iloc[:, -1]

data_dmatrix = xgb.DMatrix(data=X, label=y)
featureN = data_dmatrix.feature_names

newName = []
for w in X.columns.tolist():
    if "-gp1" in w:
        w = w.replace("-gp1", "")
    newName = newName + [w]

X.columns = newName

# reorder the training data according tot he columns in test data
columnsList = X.columns.tolist();
X = X[featureName]

# # save trained model to file
# data_dmatrix = xgb.DMatrix(data=X,label=y)
# param = {'max_depth':6, 'eta':1, 'silent':1, 'objective':'binary:logistic'}
# num_round = 100
# classifier = xgb.train(param, data_dmatrix, num_round)
# pickle.dump(classifier, open("xgboostPrePost.dat", "wb"))
# print("XGBoost classifier already trained and saved to a file xgboostPrePost.dat")


# In[11]:


# load model from file
loaded_model = pickle.load(open("xgboostPrePost.dat", "rb"))

# generate test data
dataTest = pd.DataFrame(list(motifValue.values()))
dataTest.columns = X.columns.tolist()
dataTest = dataTest.convert_objects(convert_numeric=True)
testMatrix = xgb.DMatrix(dataTest)

# make predictions for test data: 0 is for post, 1 is for pre
predictProb = loaded_model.predict(testMatrix)  # the predicted probabilities for the pre

predictClass = [round(value) for value in predictProb]  # the predicted class labels

classes = []
for w in predictClass:
    if w == 0.0:
        w = "Pre"
    else:
        w = "Post"
    classes.append(w)

#  pre is 1, post is o
proteinsList = list(record_dict.keys())  # protein ID for the test

result = []
for i in range(len(proteinsList)):
    rei = [proteinsList[i]] + [1 - predictProb[i]] + [predictProb[i]] + [classes[i]]
    result = result + [rei]

# put the result into a table
resultDF = pd.DataFrame(result)
resultDF.columns = ["Protein ID", "Prob(Pre)", "Prob(Post)", "Predicted Class"]
print(resultDF)
resultDF.to_csv(outputFolder + "/predict_output.csv", sep='\t')

# In[ ]:




