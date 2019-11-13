import keras
from keras.datasets import cifar10
from keras.preprocessing.image import ImageDataGenerator
from keras.models import Sequential
from keras.layers import Dense, Dropout, Activation, Flatten
from keras.layers import Conv2D, MaxPooling2D, BatchNormalization
from keras import optimizers
import numpy as np
from keras.layers.core import Lambda
from keras.preprocessing.image import img_to_array 
from keras import backend as K
from keras.optimizers import SGD
from keras.callbacks import ModelCheckpoint  
from sklearn.preprocessing import LabelEncoder 
from keras import regularizers
from keras.models import load_model
import cv2
from keras.utils.np_utils import to_categorical  
import os
from sklearn.model_selection import train_test_split

from keras import backend as K
K.set_image_dim_ordering('th')

classes = [0. ,  1.,  2.,   3.  , 4.]  # 5类病
  
labelencoder = LabelEncoder()  # 标签编码
labelencoder.fit(classes)  

img_path = "E:/apple/images/" # 图片主目录

size_resize = 500 #在内存中将每张图片重置为500*500像素


# 加载图片（路径，灰度图默认为False）
def load_img(path, grayscale=False):
    if grayscale:
        #如果是灰度图
        img = cv2.imread(path,1) 
    else:
        #如果不是灰度图
        img = cv2.imread(path) # 通过图片路径将图片读取成无符号int型数组
        img = cv2.resize(img,(size_resize,size_resize)) # 将图片重置为500*500像素
        img = np.array(img,dtype="float") / 255.0 # 将像素值转成0到1之间的浮点数（归一化）
    return img


# 生成数据
def generateData(batch_size,x_train,y_train):  
    #print 'generateData...'
    while True:  
        train_data = []  
        train_label = []  
        batch = 0  
        for i in (range(len(x_train))): 
            url = x_train[i]
            batch += 1 
            img = load_img(url)
            img = img_to_array(img) 
            train_data.append(img)  
            
            label = y_train[i]
              
            # print label.shape  
            train_label.append(label)  
            if batch % batch_size==0: 
                #print 'get enough bacth!\n'
                train_data = np.array(train_data)  
                train_label = np.array(train_label)
                train_label = labelencoder.transform(train_label)  
                train_label = to_categorical(train_label, num_classes=5)  
                
                yield (train_data,train_label)  
                train_data = []  
                train_label = []  
                batch = 0  

# 生成验证数据
def generateValidData(batch_size,x_test,y_test):  
    #print 'generateValidData...'
    while True:  
        valid_data = []  
        valid_label = []  
        batch = 0  
        for i in (range(len(x_test))):  
            url = x_test[i]
            batch += 1  
            img = load_img(url)
            img = img_to_array(img)  
            valid_data.append(img)  
            label = y_test[i] 
            # print label.shape  
            valid_label.append(label)  
            if batch % batch_size==0:  
                valid_data = np.array(valid_data)  
                valid_label = np.array(valid_label)
                valid_label = labelencoder.transform(valid_label)  
                valid_label = to_categorical(valid_label, num_classes=5)  
                yield (valid_data,valid_label)  
                valid_data = []  
                valid_label = []  
                batch = 0  

x_all = [] # 图片数据
y_all = [] # 标签数据

# 开始遍历每个文件夹下的图片，将图片输入读入

for parent,dirs,files in os.walk(img_path):
    for dir in dirs:
        print(dir)
        path = img_path + dir
        for p,d,imgs in os.walk(path):
            for img in imgs:
                x_all.append(path+"/"+img)
                y_all.append(int(dir))
       
# 将读入的图片数据按2:8的比例划分，8份用于训练，2份用于测试，keras底层会将这8/10份数据继续划分，一部分用于训练，一部分用于测试
# 输入：
# x_all：图片数据
# y_all：标签数据
# test_size = 0.2：划分比例
# random_state = 0 ： 随机状态
# 返回：
# 要用于测试和训练的输入数据
x_train,x_test, y_train, y_test = train_test_split(x_all, y_all, test_size = 0.2, random_state = 0) #划分训练，验证数据集

print(len(x_train),len(x_test),len(y_train),len(y_test))

# VGG 模型，不要乱改
#layer1 32*32*3
model = Sequential()
#第一个 卷积层 的卷积核的数目是32 ，卷积核的大小是3*3，stride没写，默认应该是1*1
#对于stride=1*1,并且padding ='same',这种情况卷积后的图像shape与卷积前相同，本层后shape还是32*32
model.add(Conv2D(64, (3, 3), padding='same',input_shape=(3,500,500)))
model.add(Activation('relu'))
#layer2 32*32*64
model.add(Conv2D(64, (3, 3), padding='same'))
model.add(Activation('relu'))
#下面两行代码是等价的，#keras Pool层有个奇怪的地方，stride,默认是(2*2),
#padding默认是valid，在写代码是这些参数还是最好都加上,这一步之后,输出的shape是16*16*64
#model.add(MaxPooling2D(pool_size=(2, 2)))
model.add(MaxPooling2D(pool_size=(2, 2),strides=(2,2),padding='same')  )
#layer3 16*16*64
model.add(Conv2D(128, (3, 3), padding='same'))
model.add(Activation('relu'))
#layer4 16*16*128
model.add(Conv2D(128, (3, 3), padding='same'))
model.add(Activation('relu'))
model.add(MaxPooling2D(pool_size=(2, 2)))
#layer5 8*8*128
model.add(Conv2D(256, (3, 3), padding='same'))
model.add(Activation('relu'))
#layer6 8*8*256
model.add(Conv2D(256, (3, 3), padding='same'))
model.add(Activation('relu'))
#layer7 8*8*256
model.add(Conv2D(256, (3, 3), padding='same'))
model.add(Activation('relu'))
model.add(MaxPooling2D(pool_size=(2, 2)))
#layer8 4*4*256
model.add(Conv2D(512, (3, 3), padding='same'))
model.add(Activation('relu'))
#layer9 4*4*512
model.add(Conv2D(512, (3, 3), padding='same'))
model.add(Activation('relu'))
#layer10 4*4*512
model.add(Conv2D(512, (3, 3), padding='same'))
model.add(Activation('relu'))

model.add(MaxPooling2D(pool_size=(2, 2)))

#layer11 2*2*512
model.add(Conv2D(512, (3, 3), padding='same'))
model.add(Activation('relu'))
#layer12 2*2*512
model.add(Conv2D(512, (3, 3), padding='same'))
model.add(Activation('relu'))
#layer13 2*2*512
model.add(Conv2D(512, (3, 3), padding='same'))
model.add(Activation('relu'))
model.add(MaxPooling2D(pool_size=(2, 2)))
#layer14 1*1*512
model.add(Flatten())
model.add(Dense(512))
model.add(Activation('relu'))
#layer15 512
model.add(Dense(512))
model.add(Activation('relu'))
#layer16 512
model.add(Dropout(0.5))
model.add(Dense(5))
model.add(Activation('softmax'))
# 5
model.summary() # 显示VGG
model.compile(loss='categorical_crossentropy',optimizer='sgd',metrics=['accuracy']) 

EPOCHS = 20 # 训练20轮
BS = 8 #每次读入8条数据，如果内存不够可以改成4、2
modelcheck = ModelCheckpoint("E:/apple/my_model_bp.h5",monitor='val_acc',save_best_only=True,mode='max')  #将模型保存，使用val_acc监视器，每次取最好的结果
callable = [modelcheck]  # 将modelCheck设置为回调函数

# //表示整除
H = model.fit_generator(generator=generateData(BS,x_train,y_train),steps_per_epoch=len(x_train)//BS,epochs=EPOCHS,verbose=1,  
                    validation_data=generateValidData(BS,x_test,y_test),validation_steps=len(x_test)//BS,callbacks=callable,max_q_size=1)  
