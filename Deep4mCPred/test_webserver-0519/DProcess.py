import string
import re
import pandas as pd
import numpy as np
import keras.utils.np_utils as kutils
from collections import Counter

  
def ENAC_Encoding(sampleSeq3DArr):

    CategoryLen = 5
    
    probMatr = np.zeros((len(sampleSeq3DArr),len(sampleSeq3DArr[0]), CategoryLen))
    
    window = 2
    AAA = 'ACGT-'

    sampleNo = 0
    for sequence in sampleSeq3DArr:
        AANo     = 0
        sequenceStr = ''
        for AA in sequence:
            sequenceStr += AA
        for j in range(len(sequenceStr)):
            thisLetter = sequenceStr[j]

            if j < len(sequenceStr) and j + window <= len(sequenceStr):
                count = Counter(sequenceStr[j:j + window])
                for key in count:
                    count[key] = count[key] / len(sequenceStr[j:j + window])
                probMatr[sampleNo][AANo][0] = count['A']
                probMatr[sampleNo][AANo][1] = count['C']
                probMatr[sampleNo][AANo][2] = count['G']
                probMatr[sampleNo][AANo][3] = count['T']
                probMatr[sampleNo][AANo][4] = count['-']

            AANo += 1
        sampleNo += 1
    
    return probMatr



def ANF_NCP_EIIP_Onehot_Encoding(sampleSeq3DArr):

    CategoryLen = 1+3+1+4
    
    probMatr = np.zeros((len(sampleSeq3DArr),len(sampleSeq3DArr[0]), CategoryLen))
    
    sampleNo = 0
    for sequence in sampleSeq3DArr:
        AANo     = 0
        sequenceStr = ''
        for AA in sequence:
            sequenceStr += AA
        for j in range(len(sequenceStr)):
            thisLetter = sequenceStr[j]
            if(thisLetter == "A"):
                probMatr[sampleNo][AANo][0] = 1
                probMatr[sampleNo][AANo][1] = 1
                probMatr[sampleNo][AANo][2] = 1
                probMatr[sampleNo][AANo][3] = 0.126
                probMatr[sampleNo][AANo][4] = 1
                probMatr[sampleNo][AANo][5] = 0
                probMatr[sampleNo][AANo][6] = 0
                probMatr[sampleNo][AANo][7] = 0
            elif(thisLetter == "C"):
                probMatr[sampleNo][AANo][0] = 0
                probMatr[sampleNo][AANo][1] = 1
                probMatr[sampleNo][AANo][2] = 0
                probMatr[sampleNo][AANo][3] = 0.134
                probMatr[sampleNo][AANo][4] = 0
                probMatr[sampleNo][AANo][5] = 1
                probMatr[sampleNo][AANo][6] = 0
                probMatr[sampleNo][AANo][7] = 0
            elif(thisLetter == "G"):
                probMatr[sampleNo][AANo][0] = 1
                probMatr[sampleNo][AANo][1] = 0
                probMatr[sampleNo][AANo][2] = 0
                probMatr[sampleNo][AANo][3] = 0.0806
                probMatr[sampleNo][AANo][4] = 0
                probMatr[sampleNo][AANo][5] = 0
                probMatr[sampleNo][AANo][6] = 1
                probMatr[sampleNo][AANo][7] = 0
            elif(thisLetter == "T"):
                probMatr[sampleNo][AANo][0] = 0
                probMatr[sampleNo][AANo][1] = 0
                probMatr[sampleNo][AANo][2] = 1
                probMatr[sampleNo][AANo][3] = 0.1335
                probMatr[sampleNo][AANo][4] = 0
                probMatr[sampleNo][AANo][5] = 0
                probMatr[sampleNo][AANo][6] = 0
                probMatr[sampleNo][AANo][7] = 1
            else:
                probMatr[sampleNo][AANo][0] = 0
                probMatr[sampleNo][AANo][1] = 0
                probMatr[sampleNo][AANo][2] = 0
                probMatr[sampleNo][AANo][3] = 0
                probMatr[sampleNo][AANo][4] = 0
                probMatr[sampleNo][AANo][5] = 0
                probMatr[sampleNo][AANo][6] = 0
                probMatr[sampleNo][AANo][7] = 0

            probMatr[sampleNo][AANo][8] = sequenceStr[0: j + 1].count(sequenceStr[j]) / (j + 1)

            AANo += 1
        sampleNo += 1
    
    return probMatr


def CKSNAP_NCP_EIIP_Onehot_Encoding(sampleSeq3DArr):

    gap = 5
    AA = 'ACGT-'
    aaPairs = []
    for aa1 in AA:
        for aa2 in AA:
            aaPairs.append(aa1 + aa2)

    CategoryLen = 8 + 8 + 1
    fetureLen = 5*5*6
    
    probMatr = np.zeros((len(sampleSeq3DArr),fetureLen, CategoryLen))
    
    sampleNo = 0

    for sequence in sampleSeq3DArr:
        AANo     = 0
        sequenceStr = ''
        for AAA in sequence:
            sequenceStr += AAA

        for g in range(gap + 1):
            myDict = {}
            for pair in aaPairs:
                myDict[pair] = 0
            sum1 = 0
            for index1 in range(len(sequenceStr)):
                index2 = index1 + g + 1
                if index1 < len(sequenceStr) and index2 < len(sequenceStr) and sequenceStr[index1] in AA and sequenceStr[
                    index2] in AA:
                    myDict[sequenceStr[index1] + sequenceStr[index2]] = myDict[sequenceStr[index1] + sequenceStr[index2]] + 1
                    sum1 = sum1 + 1
            for pair in aaPairs:
                if(pair[0] == "A"):
                    probMatr[sampleNo][AANo][0] = 1
                    probMatr[sampleNo][AANo][1] = 1
                    probMatr[sampleNo][AANo][2] = 1
                    probMatr[sampleNo][AANo][3] = 0.126
                    probMatr[sampleNo][AANo][4] = 1
                    probMatr[sampleNo][AANo][5] = 0
                    probMatr[sampleNo][AANo][6] = 0
                    probMatr[sampleNo][AANo][7] = 0
                elif(pair[0] == "C"):
                    probMatr[sampleNo][AANo][0] = 0
                    probMatr[sampleNo][AANo][1] = 1
                    probMatr[sampleNo][AANo][2] = 0
                    probMatr[sampleNo][AANo][3] = 0.134
                    probMatr[sampleNo][AANo][4] = 0
                    probMatr[sampleNo][AANo][5] = 1
                    probMatr[sampleNo][AANo][6] = 0
                    probMatr[sampleNo][AANo][7] = 0
                elif(pair[0] == "G"):
                    probMatr[sampleNo][AANo][0] = 1
                    probMatr[sampleNo][AANo][1] = 0
                    probMatr[sampleNo][AANo][2] = 0
                    probMatr[sampleNo][AANo][3] = 0.0806
                    probMatr[sampleNo][AANo][4] = 0
                    probMatr[sampleNo][AANo][5] = 0
                    probMatr[sampleNo][AANo][6] = 1
                    probMatr[sampleNo][AANo][7] = 0
                elif(pair[0] == "T"):
                    probMatr[sampleNo][AANo][0] = 0
                    probMatr[sampleNo][AANo][1] = 0
                    probMatr[sampleNo][AANo][2] = 1
                    probMatr[sampleNo][AANo][3] = 0.1335
                    probMatr[sampleNo][AANo][4] = 0
                    probMatr[sampleNo][AANo][5] = 0
                    probMatr[sampleNo][AANo][6] = 0
                    probMatr[sampleNo][AANo][7] = 1
                else:
                    probMatr[sampleNo][AANo][0] = 0
                    probMatr[sampleNo][AANo][1] = 0
                    probMatr[sampleNo][AANo][2] = 0
                    probMatr[sampleNo][AANo][3] = 0
                    probMatr[sampleNo][AANo][4] = 0
                    probMatr[sampleNo][AANo][5] = 0
                    probMatr[sampleNo][AANo][6] = 0
                    probMatr[sampleNo][AANo][7] = 0

                if(pair[1] == "A"):
                    probMatr[sampleNo][AANo][8] = 1
                    probMatr[sampleNo][AANo][9] = 1
                    probMatr[sampleNo][AANo][10] = 1
                    probMatr[sampleNo][AANo][11] = 0.126
                    probMatr[sampleNo][AANo][12] = 1
                    probMatr[sampleNo][AANo][13] = 0
                    probMatr[sampleNo][AANo][14] = 0
                    probMatr[sampleNo][AANo][15] = 0
                elif(pair[1] == "C"):
                    probMatr[sampleNo][AANo][8] = 0
                    probMatr[sampleNo][AANo][9] = 1
                    probMatr[sampleNo][AANo][10] = 0
                    probMatr[sampleNo][AANo][11] = 0.134
                    probMatr[sampleNo][AANo][12] = 0
                    probMatr[sampleNo][AANo][13] = 1
                    probMatr[sampleNo][AANo][14] = 0
                    probMatr[sampleNo][AANo][15] = 0
                elif(pair[1] == "G"):
                    probMatr[sampleNo][AANo][8] = 1
                    probMatr[sampleNo][AANo][9] = 0
                    probMatr[sampleNo][AANo][10] = 0
                    probMatr[sampleNo][AANo][11] = 0.0806
                    probMatr[sampleNo][AANo][12] = 0
                    probMatr[sampleNo][AANo][13] = 0
                    probMatr[sampleNo][AANo][14] = 1
                    probMatr[sampleNo][AANo][15] = 0
                elif(pair[1] == "T"):
                    probMatr[sampleNo][AANo][8] = 0
                    probMatr[sampleNo][AANo][9] = 0
                    probMatr[sampleNo][AANo][10] = 1
                    probMatr[sampleNo][AANo][11] = 0.1335
                    probMatr[sampleNo][AANo][12] = 0
                    probMatr[sampleNo][AANo][13] = 0
                    probMatr[sampleNo][AANo][14] = 0
                    probMatr[sampleNo][AANo][15] = 1
                else:
                    probMatr[sampleNo][AANo][8] = 0
                    probMatr[sampleNo][AANo][9] = 0
                    probMatr[sampleNo][AANo][10] = 0
                    probMatr[sampleNo][AANo][11] = 0
                    probMatr[sampleNo][AANo][12] = 0
                    probMatr[sampleNo][AANo][13] = 0
                    probMatr[sampleNo][AANo][14] = 0
                    probMatr[sampleNo][AANo][15] = 0

                probMatr[sampleNo][AANo][16] = myDict[pair] / sum1

                AANo += 1
        sampleNo += 1
    
    return probMatr


def CalculateMatrix(data, order):
    matrix = np.zeros((len(data[0]) - 2, 64))
    for i in range(len(data[0]) - 2): # position
        for j in range(len(data)):
            if re.search('-', data[j][i:i+3]):
                pass
            else:
                matrix[i][order[data[j][i:i+3]]] += 1
    return matrix

def Order_Trinucleotides():
    nucleotides = ['A', 'C', 'G', 'T']
    trinucleotides = [n1 + n2 + n3 for n1 in nucleotides for n2 in nucleotides for n3 in nucleotides]
    order = {}
    for i in range(len(trinucleotides)):
        order[trinucleotides[i]] = i
    return order

def Matrix_Encoding(sampleSeq3DArr, order):
    positive = []
    negative = []

    for sequence in sampleSeq3DArr:
        sequenceStr = ''
        i = 0
        AAInt = -1
        for AA in sequence:
            if(i == 0):
                AAInt = int(AA)
            else:
                sequenceStr += AA
            if(i == (len(sequence) - 1 )):
                if (AAInt == 1):
                    positive.append(sequenceStr)
                else:
                    negative.append(sequenceStr)

            i += 1

    matrix_po = CalculateMatrix(positive, order)
    matrix_ne = CalculateMatrix(negative, order)

    positive_number = len(positive)
    negative_number = len(negative)

    return matrix_po, matrix_ne, positive_number, negative_number



def PSTNPss_NCP_EIIP_Onehot_Encoding(sampleSeqLable3DArr, sampleSeq3DArr):

    order = Order_Trinucleotides()

    matrix_po, matrix_ne, positive_number, negative_number = Matrix_Encoding(sampleSeqLable3DArr, order)

    CategoryLen = 8 + 8 + 8 + 1
    
    probMatr = np.zeros((len(sampleSeq3DArr),len(sampleSeq3DArr[0]) - 2, CategoryLen))
    
    sampleNo = 0
    for sequence in sampleSeq3DArr:
        AANo     = 0
        sequenceStr = ''
        for AA in sequence:
            sequenceStr += AA

        for j in range(len(sequenceStr) - 2):
            pair = sequenceStr[j: j+3]
            if re.search('-', pair):
                if(pair[0] == "A"):
                    probMatr[sampleNo][AANo][0] = 1
                    probMatr[sampleNo][AANo][1] = 1
                    probMatr[sampleNo][AANo][2] = 1
                    probMatr[sampleNo][AANo][3] = 0.126
                    probMatr[sampleNo][AANo][4] = 1
                    probMatr[sampleNo][AANo][5] = 0
                    probMatr[sampleNo][AANo][6] = 0
                    probMatr[sampleNo][AANo][7] = 0
                elif(pair[0] == "C"):
                    probMatr[sampleNo][AANo][0] = 0
                    probMatr[sampleNo][AANo][1] = 1
                    probMatr[sampleNo][AANo][2] = 0
                    probMatr[sampleNo][AANo][3] = 0.134
                    probMatr[sampleNo][AANo][4] = 0
                    probMatr[sampleNo][AANo][5] = 1
                    probMatr[sampleNo][AANo][6] = 0
                    probMatr[sampleNo][AANo][7] = 0
                elif(pair[0] == "G"):
                    probMatr[sampleNo][AANo][0] = 1
                    probMatr[sampleNo][AANo][1] = 0
                    probMatr[sampleNo][AANo][2] = 0
                    probMatr[sampleNo][AANo][3] = 0.0806
                    probMatr[sampleNo][AANo][4] = 0
                    probMatr[sampleNo][AANo][5] = 0
                    probMatr[sampleNo][AANo][6] = 1
                    probMatr[sampleNo][AANo][7] = 0
                elif(pair[0] == "T"):
                    probMatr[sampleNo][AANo][0] = 0
                    probMatr[sampleNo][AANo][1] = 0
                    probMatr[sampleNo][AANo][2] = 1
                    probMatr[sampleNo][AANo][3] = 0.1335
                    probMatr[sampleNo][AANo][4] = 0
                    probMatr[sampleNo][AANo][5] = 0
                    probMatr[sampleNo][AANo][6] = 0
                    probMatr[sampleNo][AANo][7] = 1
                else:
                    probMatr[sampleNo][AANo][0] = 0
                    probMatr[sampleNo][AANo][1] = 0
                    probMatr[sampleNo][AANo][2] = 0
                    probMatr[sampleNo][AANo][3] = 0
                    probMatr[sampleNo][AANo][4] = 0
                    probMatr[sampleNo][AANo][5] = 0
                    probMatr[sampleNo][AANo][6] = 0
                    probMatr[sampleNo][AANo][7] = 0

                if(pair[1] == "A"):
                    probMatr[sampleNo][AANo][8] = 1
                    probMatr[sampleNo][AANo][9] = 1
                    probMatr[sampleNo][AANo][10] = 1
                    probMatr[sampleNo][AANo][11] = 0.126
                    probMatr[sampleNo][AANo][12] = 1
                    probMatr[sampleNo][AANo][13] = 0
                    probMatr[sampleNo][AANo][14] = 0
                    probMatr[sampleNo][AANo][15] = 0
                elif(pair[1] == "C"):
                    probMatr[sampleNo][AANo][8] = 0
                    probMatr[sampleNo][AANo][9] = 1
                    probMatr[sampleNo][AANo][10] = 0
                    probMatr[sampleNo][AANo][11] = 0.134
                    probMatr[sampleNo][AANo][12] = 0
                    probMatr[sampleNo][AANo][13] = 1
                    probMatr[sampleNo][AANo][14] = 0
                    probMatr[sampleNo][AANo][15] = 0
                elif(pair[1] == "G"):
                    probMatr[sampleNo][AANo][8] = 1
                    probMatr[sampleNo][AANo][9] = 0
                    probMatr[sampleNo][AANo][10] = 0
                    probMatr[sampleNo][AANo][11] = 0.0806
                    probMatr[sampleNo][AANo][12] = 0
                    probMatr[sampleNo][AANo][13] = 0
                    probMatr[sampleNo][AANo][14] = 1
                    probMatr[sampleNo][AANo][15] = 0
                elif(pair[1] == "T"):
                    probMatr[sampleNo][AANo][8] = 0
                    probMatr[sampleNo][AANo][9] = 0
                    probMatr[sampleNo][AANo][10] = 1
                    probMatr[sampleNo][AANo][11] = 0.1335
                    probMatr[sampleNo][AANo][12] = 0
                    probMatr[sampleNo][AANo][13] = 0
                    probMatr[sampleNo][AANo][14] = 0
                    probMatr[sampleNo][AANo][15] = 1
                else:
                    probMatr[sampleNo][AANo][8] = 0
                    probMatr[sampleNo][AANo][9] = 0
                    probMatr[sampleNo][AANo][10] = 0
                    probMatr[sampleNo][AANo][11] = 0
                    probMatr[sampleNo][AANo][12] = 0
                    probMatr[sampleNo][AANo][13] = 0
                    probMatr[sampleNo][AANo][14] = 0
                    probMatr[sampleNo][AANo][15] = 0

                if(pair[2] == "A"):
                    probMatr[sampleNo][AANo][16] = 1
                    probMatr[sampleNo][AANo][17] = 1
                    probMatr[sampleNo][AANo][18] = 1
                    probMatr[sampleNo][AANo][19] = 0.126
                    probMatr[sampleNo][AANo][20] = 1
                    probMatr[sampleNo][AANo][21] = 0
                    probMatr[sampleNo][AANo][22] = 0
                    probMatr[sampleNo][AANo][23] = 0
                elif(pair[2] == "C"):
                    probMatr[sampleNo][AANo][16] = 0
                    probMatr[sampleNo][AANo][17] = 1
                    probMatr[sampleNo][AANo][18] = 0
                    probMatr[sampleNo][AANo][19] = 0.134
                    probMatr[sampleNo][AANo][20] = 0
                    probMatr[sampleNo][AANo][21] = 1
                    probMatr[sampleNo][AANo][22] = 0
                    probMatr[sampleNo][AANo][23] = 0
                elif(pair[2] == "G"):
                    probMatr[sampleNo][AANo][16] = 1
                    probMatr[sampleNo][AANo][17] = 0
                    probMatr[sampleNo][AANo][18] = 0
                    probMatr[sampleNo][AANo][19] = 0.0806
                    probMatr[sampleNo][AANo][20] = 0
                    probMatr[sampleNo][AANo][21] = 0
                    probMatr[sampleNo][AANo][22] = 1
                    probMatr[sampleNo][AANo][23] = 0
                elif(pair[2] == "T"):
                    probMatr[sampleNo][AANo][16] = 0
                    probMatr[sampleNo][AANo][17] = 0
                    probMatr[sampleNo][AANo][18] = 1
                    probMatr[sampleNo][AANo][19] = 0.1335
                    probMatr[sampleNo][AANo][20] = 0
                    probMatr[sampleNo][AANo][21] = 0
                    probMatr[sampleNo][AANo][22] = 0
                    probMatr[sampleNo][AANo][23] = 1
                else:
                    probMatr[sampleNo][AANo][16] = 0
                    probMatr[sampleNo][AANo][17] = 0
                    probMatr[sampleNo][AANo][18] = 0
                    probMatr[sampleNo][AANo][19] = 0
                    probMatr[sampleNo][AANo][20] = 0
                    probMatr[sampleNo][AANo][21] = 0
                    probMatr[sampleNo][AANo][22] = 0
                    probMatr[sampleNo][AANo][23] = 0
                probMatr[sampleNo][AANo][24] = 0
            else:
                p_num, n_num = positive_number, negative_number
                po_number = matrix_po[j][order[pair]]

                ne_number = matrix_ne[j][order[pair]]

                if(pair[0] == "A"):
                    probMatr[sampleNo][AANo][0] = 1
                    probMatr[sampleNo][AANo][1] = 1
                    probMatr[sampleNo][AANo][2] = 1
                    probMatr[sampleNo][AANo][3] = 0.126
                    probMatr[sampleNo][AANo][4] = 1
                    probMatr[sampleNo][AANo][5] = 0
                    probMatr[sampleNo][AANo][6] = 0
                    probMatr[sampleNo][AANo][7] = 0
                elif(pair[0] == "C"):
                    probMatr[sampleNo][AANo][0] = 0
                    probMatr[sampleNo][AANo][1] = 1
                    probMatr[sampleNo][AANo][2] = 0
                    probMatr[sampleNo][AANo][3] = 0.134
                    probMatr[sampleNo][AANo][4] = 0
                    probMatr[sampleNo][AANo][5] = 1
                    probMatr[sampleNo][AANo][6] = 0
                    probMatr[sampleNo][AANo][7] = 0
                elif(pair[0] == "G"):
                    probMatr[sampleNo][AANo][0] = 1
                    probMatr[sampleNo][AANo][1] = 0
                    probMatr[sampleNo][AANo][2] = 0
                    probMatr[sampleNo][AANo][3] = 0.0806
                    probMatr[sampleNo][AANo][4] = 0
                    probMatr[sampleNo][AANo][5] = 0
                    probMatr[sampleNo][AANo][6] = 1
                    probMatr[sampleNo][AANo][7] = 0
                elif(pair[0] == "T"):
                    probMatr[sampleNo][AANo][0] = 0
                    probMatr[sampleNo][AANo][1] = 0
                    probMatr[sampleNo][AANo][2] = 1
                    probMatr[sampleNo][AANo][3] = 0.1335
                    probMatr[sampleNo][AANo][4] = 0
                    probMatr[sampleNo][AANo][5] = 0
                    probMatr[sampleNo][AANo][6] = 0
                    probMatr[sampleNo][AANo][7] = 1
                else:
                    probMatr[sampleNo][AANo][0] = 0
                    probMatr[sampleNo][AANo][1] = 0
                    probMatr[sampleNo][AANo][2] = 0
                    probMatr[sampleNo][AANo][3] = 0
                    probMatr[sampleNo][AANo][4] = 0
                    probMatr[sampleNo][AANo][5] = 0
                    probMatr[sampleNo][AANo][6] = 0
                    probMatr[sampleNo][AANo][7] = 0

                if(pair[1] == "A"):
                    probMatr[sampleNo][AANo][8] = 1
                    probMatr[sampleNo][AANo][9] = 1
                    probMatr[sampleNo][AANo][10] = 1
                    probMatr[sampleNo][AANo][11] = 0.126
                    probMatr[sampleNo][AANo][12] = 1
                    probMatr[sampleNo][AANo][13] = 0
                    probMatr[sampleNo][AANo][14] = 0
                    probMatr[sampleNo][AANo][15] = 0
                elif(pair[1] == "C"):
                    probMatr[sampleNo][AANo][8] = 0
                    probMatr[sampleNo][AANo][9] = 1
                    probMatr[sampleNo][AANo][10] = 0
                    probMatr[sampleNo][AANo][11] = 0.134
                    probMatr[sampleNo][AANo][12] = 0
                    probMatr[sampleNo][AANo][13] = 1
                    probMatr[sampleNo][AANo][14] = 0
                    probMatr[sampleNo][AANo][15] = 0
                elif(pair[1] == "G"):
                    probMatr[sampleNo][AANo][8] = 1
                    probMatr[sampleNo][AANo][9] = 0
                    probMatr[sampleNo][AANo][10] = 0
                    probMatr[sampleNo][AANo][11] = 0.0806
                    probMatr[sampleNo][AANo][12] = 0
                    probMatr[sampleNo][AANo][13] = 0
                    probMatr[sampleNo][AANo][14] = 1
                    probMatr[sampleNo][AANo][15] = 0
                elif(pair[1] == "T"):
                    probMatr[sampleNo][AANo][8] = 0
                    probMatr[sampleNo][AANo][9] = 0
                    probMatr[sampleNo][AANo][10] = 1
                    probMatr[sampleNo][AANo][11] = 0.1335
                    probMatr[sampleNo][AANo][12] = 0
                    probMatr[sampleNo][AANo][13] = 0
                    probMatr[sampleNo][AANo][14] = 0
                    probMatr[sampleNo][AANo][15] = 1
                else:
                    probMatr[sampleNo][AANo][8] = 0
                    probMatr[sampleNo][AANo][9] = 0
                    probMatr[sampleNo][AANo][10] = 0
                    probMatr[sampleNo][AANo][11] = 0
                    probMatr[sampleNo][AANo][12] = 0
                    probMatr[sampleNo][AANo][13] = 0
                    probMatr[sampleNo][AANo][14] = 0
                    probMatr[sampleNo][AANo][15] = 0

                if(pair[2] == "A"):
                    probMatr[sampleNo][AANo][16] = 1
                    probMatr[sampleNo][AANo][17] = 1
                    probMatr[sampleNo][AANo][18] = 1
                    probMatr[sampleNo][AANo][19] = 0.126
                    probMatr[sampleNo][AANo][20] = 1
                    probMatr[sampleNo][AANo][21] = 0
                    probMatr[sampleNo][AANo][22] = 0
                    probMatr[sampleNo][AANo][23] = 0
                elif(pair[2] == "C"):
                    probMatr[sampleNo][AANo][16] = 0
                    probMatr[sampleNo][AANo][17] = 1
                    probMatr[sampleNo][AANo][18] = 0
                    probMatr[sampleNo][AANo][19] = 0.134
                    probMatr[sampleNo][AANo][20] = 0
                    probMatr[sampleNo][AANo][21] = 1
                    probMatr[sampleNo][AANo][22] = 0
                    probMatr[sampleNo][AANo][23] = 0
                elif(pair[2] == "G"):
                    probMatr[sampleNo][AANo][16] = 1
                    probMatr[sampleNo][AANo][17] = 0
                    probMatr[sampleNo][AANo][18] = 0
                    probMatr[sampleNo][AANo][19] = 0.0806
                    probMatr[sampleNo][AANo][20] = 0
                    probMatr[sampleNo][AANo][21] = 0
                    probMatr[sampleNo][AANo][22] = 1
                    probMatr[sampleNo][AANo][23] = 0
                elif(pair[2] == "T"):
                    probMatr[sampleNo][AANo][16] = 0
                    probMatr[sampleNo][AANo][17] = 0
                    probMatr[sampleNo][AANo][18] = 1
                    probMatr[sampleNo][AANo][19] = 0.1335
                    probMatr[sampleNo][AANo][20] = 0
                    probMatr[sampleNo][AANo][21] = 0
                    probMatr[sampleNo][AANo][22] = 0
                    probMatr[sampleNo][AANo][23] = 1
                else:
                    probMatr[sampleNo][AANo][16] = 0
                    probMatr[sampleNo][AANo][17] = 0
                    probMatr[sampleNo][AANo][18] = 0
                    probMatr[sampleNo][AANo][19] = 0
                    probMatr[sampleNo][AANo][20] = 0
                    probMatr[sampleNo][AANo][21] = 0
                    probMatr[sampleNo][AANo][22] = 0
                    probMatr[sampleNo][AANo][23] = 0

                probMatr[sampleNo][AANo][24] = (po_number - ne_number) / (p_num + n_num)

            AANo += 1
        sampleNo += 1

    return probMatr


def convertRawToXY(rawDataFrameTrain, rawDataFrameTest, codingMode='ENAC'):

    targetList = rawDataFrameTest[:, 0]
    targetList2 = []
    for ii in targetList:
        targetList2.append(int(ii))
    targetArr = kutils.to_categorical(targetList2,2)

    sampleSeq3DArr = rawDataFrameTest[:, 1:]

    # 4 dim
    if codingMode == 'ENAC':
        probMatr = ENAC_Encoding(sampleSeq3DArr)
    # 9 dim
    if codingMode == 'ANF_NCP_EIIP_Onehot':
        probMatr = ANF_NCP_EIIP_Onehot_Encoding(sampleSeq3DArr)
    # 12 dim
    if codingMode == 'CKSNAP_NCP_EIIP_Onehot':
        probMatr = CKSNAP_NCP_EIIP_Onehot_Encoding(sampleSeq3DArr)
    # 15 dim
    if codingMode == 'PSTNPss_NCP_EIIP_Onehot':
        probMatr = PSTNPss_NCP_EIIP_Onehot_Encoding(rawDataFrameTrain,sampleSeq3DArr)

    return probMatr, targetArr
