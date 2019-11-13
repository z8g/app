#coding=utf-8

import argparse

import subprocess
import os,shutil
import sys

# def getArgs():
	
# 	__version__ = 'V 1.0'
# 	parser = argparse.ArgumentParser()
# 	parser._action_groups.pop()

# 	required = parser.add_argument_group('Required arguments')
	
# 	required.add_argument('-i', '--input', required=True, metavar='<PATH>', help='Input file (*** Must be specified ***)')
# 	required.add_argument('-o', '--output', required=True, metavar='<PATH>', help='Give the output path of Deep4mcPred (*** Must be specified ***)')
# 	required.add_argument('-s', '--speciesarg', required=True, metavar='<PATH>', help=' (*** Must be specified ***)')
# 	required.add_argument('-d', '--programdir', required=False, metavar='<PATH>', help='Directory',default=None)
# 	required.add_argument('-t',"--inputType", required=True,
# 						choices=['file', 'fixed'], help="select input file type")

# 	args = parser.parse_args()

# 	return args

def main():
	#args = getArgs()
	print("start")
	predict_py = "predict.py"

	if(sys.argv[5] is not None):
		programdir = sys.argv[5]
		predict_py = programdir + '/' + predict_py
	
	type_species = sys.argv[3]

	input_file = sys.argv[1]
	output_file = sys.argv[2]


	if(',' not in type_species):
		if(sys.argv[5] is not None):
			type_species = programdir + '/' + sys.argv[5]
		else:
			type_species = sys.argv[3]

		# Python call shell command
		# Need to be modified
		subprocess.call('python ' + predict_py + ' -s ' + type_species + ' -input ' + input_file + ' --inputType '+ sys.argv[4] +' -wm ' + type_species + '.hdf5' + ' -o ' + output_file ,shell=True)
		srcFile = output_file + "_custom2.txt"
		detFile = output_file
		shutil.move(srcFile,detFile)

	else:
		type_speciess = type_species.split(',')
		fw = open(output_file+'_custom3.txt','w+')
		for species in type_speciess:

			if(sys.argv[5] is not None):
				species = programdir + '/' + species

			# Need to be modified
			subprocess.call('python ' + predict_py + ' -s ' + species + ' -input ' + input_file + ' --inputType '+ sys.argv[4] +' -wm ' + species + '.hdf5' + ' -o ' + output_file ,shell=True)
			fp = open(output_file + "_custom2.txt",'r')
			while 1:
				lines = fp.readlines(3000000)
				if not lines:
					break
				for line in lines:
					if(line.strip() != ''):
						fw.write(line)
			fp.close()
			os.remove(output_file + "_custom2.txt")
		fw.close()
		srcFile = output_file + "_custom3.txt"
		detFile = output_file
		shutil.move(srcFile,detFile)

if __name__ == '__main__':
	main()


