1. python文件为 test.py
	jupyter notebook 文件为test.ipynb.

2. 有用户提交一个job后，给改用户新建一个文件夹，文件夹名字为job ID。 讲该用户提交的文件保存在该文件夹下，名字格式为XXX.fasta

3. 在该文件夹下新建一个一个文件夹，名字为calculatedFeature。下载下来的iFeature 的一些特征会保存在该文件夹里。

4. 运行test.py 的命令格式为

	python test.py jobID/XXXX.fasta

	上面命令不行的话就是 ipython test.py jobID/XXXX.fasta

5. 程序的运行结果是该用户目录下的一个叫做predict_output.csv文件，包括ProteinID， Prob(Pre),Prob(Post),Predicted Class四列,可以把这个文件中的表格内容反馈给用户。

可以看到我有一个例子文件夹叫做user00001，里面有三个文件分别是：

calculatedFeatures 文件夹
input.fasta
predict_output.csv

6. 需要下载的包是

pandas
numpy
xgboost 
...


特别注意：目前下载其中一个叫做DDE的特征还有一些问题。在calculatedFeature文件夹下有一个DDE.txt 是我用别的方式下载好的，保存在这个文件夹里。做网站测试的时候，对于新提交的蛋白质序列，这个特征计算不出来，将导致后续的结果又问题。所以可以先尝试每个用户都提交了相同的input.fasta中的两条蛋白质序列，讲DDE保存在他们对应的jobID/calculatedFeatures 文件夹里。




我改了几个部分。1. 新建了temp文件夹，保存用到的那些txt文件。无论你输入的fasta是什么，python会调用temp文件夹下的这些txt文件。2. 执行结束后，在用户文件夹下会生成一个predict_output_RF.csv 文件保存结果。3. python代码会在每个用户job的文件夹下自动生成calculatedFeatures这个文件夹。


