#!/usr/bin/env python
#_*_coding:utf-8_*_

import re
import math
import argparse

def DDE(fastas, **kw):
	AA = kw['order'] if kw['order'] != None else 'ACDEFGHIKLMNPQRSTVWY'

	myCodons = {
		'A': 4,
		'C': 2,
		'D': 2,
		'E': 2,
		'F': 2,
		'G': 4,
		'H': 2,
		'I': 3,
		'K': 2,
		'L': 6,
		'M': 1,
		'N': 2,
		'P': 4,
		'Q': 2,
		'R': 6,
		'S': 6,
		'T': 4,
		'V': 4,
		'W': 1,
		'Y': 2
	}

	encodings = []
	diPeptides = [aa1 + aa2 for aa1 in AA for aa2 in AA]
	header = ['#'] + diPeptides
	encodings.append(header)

	myTM = []
	for pair in diPeptides:
		myTM.append((myCodons[pair[0]] / 61) * (myCodons[pair[1]] / 61))

	AADict = {}
	for i in range(len(AA)):
		AADict[AA[i]] = i

	for i in fastas:
		name, sequence = i[0], re.sub('-', '', i[1])
		code = [name]
		tmpCode = [0] * 400
		for j in range(len(sequence) - 2 + 1):
			tmpCode[AADict[sequence[j]] * 20 + AADict[sequence[j+1]]] = tmpCode[AADict[sequence[j]] * 20 + AADict[sequence[j+1]]] +1
		if sum(tmpCode) != 0:
			tmpCode = [i/sum(tmpCode) for i in tmpCode]

		myTV = []
		for j in range(len(myTM)):
			myTV.append(myTM[j] * (1-myTM[j]) / (len(sequence) - 1))

		for j in range(len(tmpCode)):
			tmpCode[j] = (tmpCode[j] - myTM[j]) / math.sqrt(myTV[j])

		code = code + tmpCode
		encodings.append(code)
	return encodings

if __name__ == '__main__':
    parser = argparse.ArgumentParser(usage="it's usage tip.",
                                     description="Generating various numerical representation schemes for protein sequences")
    parser.add_argument("--file", required=True, help="input fasta file")
    parser.add_argument("--type", required=True,
                                                         choices=['DDE'],
                                                         help="the encoding type")
    parser.add_argument("--path", dest='filePath',
                                                         help="data file path used for 'PSSM', 'SSEB(C)', 'Disorder(BC)', 'ASA' and 'TA' encodings")
    parser.add_argument("--order", dest='order',
                                                         choices=['alphabetically', 'polarity', 'sideChainVolume', 'userDefined'],
                                                         help="output order for of Amino Acid Composition (i.e. AAC, EAAC, CKSAAP, DPC, DDE, TPC) descriptors")
    parser.add_argument("--out", dest='outFile',
                                                         help="the generated descriptor file")
    args = parser.parse_args()
    fastas = readFasta.readFasta(args.file)
    userDefinedOrder = args.userDefinedOrder if args.userDefinedOrder != None else 'ACDEFGHIKLMNPQRSTVWY'
    userDefinedOrder = re.sub('[^ACDEFGHIKLMNPQRSTVWY]', '', userDefinedOrder)
    if len(userDefinedOrder) != 20:
        userDefinedOrder = 'ACDEFGHIKLMNPQRSTVWY'
    myAAorder = {'alphabetically': 'ACDEFGHIKLMNPQRSTVWY',
                'polarity': 'DENKRQHSGTAPYVMCWIFL',
                'sideChainVolume': 'GASDPCTNEVHQILMKRFYW',
                'userDefined': userDefinedOrder
    }
    myOrder = myAAorder[args.order] if args.order != None else 'ACDEFGHIKLMNPQRSTVWY'
    kw = {'path': args.filePath, 'train': args.trainFile, 'label': args.labelFile, 'order': myOrder}
    
    myFun = args.type + '.' + args.type + '(fastas, **kw)'
    print('Descriptor type: ' + args.type)
    encodings = eval(myFun)
    outFile = args.outFile if args.outFile != None else 'encoding.tsv'
    saveCode.savetsv(encodings, outFile)
    

