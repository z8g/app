python /ipathogen-data/jinxiang/N4/test_N4_webserver/Deep4mcPred.py -i /ipathogen-data/jinxiang/N4/test_N4_webserver/test_data.txt -o /ipathogen-data/jinxiang/N4/222.txt -s A,C,D --inputType fixed -d /ipathogen-data/jinxiang/N4/test_N4_webserver
-d 指向你把我发你的文件放在哪
-d 的最后不要加"/"
其他的地方在其他目录的时候，写绝对路径

环境要求：

安装anaconda2.0，使用Python2.7，在anaconda的bin目录下使用
./pip install pandas
./pip install numpy
./pip install scipy
./pip install h5py
./pip install keras==2.1.6
./pip install tensorflow==1.12.0

mkdir -p ~/.keras
touch ~/.keras/keras.json
vim ~/.keras/keras.json

在keras.json插入以下内容:

{
    "epsilon": 1e-07,
    "floatx": "float32",
    "image_data_format": "channels_last",
    "backend": "tensorflow"
}

--------------------------------------------------------------------
DeepCleave.py 参数说明
  -i 输入文件的路径 
  -o 输出文件的路径 
  -s 物种的类型，可选项为['A', 'C', 'D','E','Gpick','Gsub'],-s可以写多个物种，用逗号分隔。(即可以对同一个输入文件，预测它在不同的物种上发生N4甲基化的可能性)   
  --inputType 可选项为['file', 'fixed'] 输入的直接是一个不定长序列FASTA文件或者输入是预处理好的固定为41长度的FASTA序列文件。

修改Deep4mcPred.py中第39行和49行的Python调用路径，改为$anaconda/bin/python下面的绝对路径

所有文件同目录

然后直接调用Deep4mcPred.py即可

Example：

python Deep4mcPred.py -i test_data.txt -o 111.txt -s A,C,D --inputType fixed

python Deep4mcPred.py -i test_data.txt -o 111.txt -s A,C,D --inputType file

python Deep4mcPred.py -i test_data.txt -o 111.txt -s C --inputType fixed

python Deep4mcPred.py -i test_data.txt -o 111.txt -s C --inputType file


输出文件格式：

">P_1404"       30      "C"     0.2708137333393097      "A.thaliana"
">P_1404"       31      "C"     0.23431061208248138     "A.thaliana"
">P_1404"       33      "C"     0.23686401546001434     "A.thaliana"
">P_1404"       37      "C"     0.3236692547798157      "A.thaliana"
">N_1105"       9       "C"     0.010368588380515575    "C.elegan"
">N_1105"       11      "C"     0.1529068499803543      "C.elegan"
">N_1105"       12      "C"     0.05389779806137085     "C.elegan"
">N_1105"       21      "C"     0.7407802939414978      "C.elegan"
">N_1105"       24      "C"     0.0651155635714531      "C.elegan"


第一列：发生N4甲基化的序列的头信息。
第二列：发生N4甲基化位点在该序列的哪个位置。
第三列：发生N4甲基化的核苷酸。
第四列：该位点发生N4甲基化的可能性大小。
第五列：进行N4甲基化预测物种模型的类型。
