import sys
import cv2
import random
import numpy as np
import os
import argparse
from keras.preprocessing.image import img_to_array
from keras.models import load_model
from sklearn.preprocessing import LabelEncoder 

# 定义了一个函数，用于Java与Python的通信时调用
def predict(img_path,model_path):
     
    os.environ["CUDA_VISIBLE_DEVICES"] = "0"

    from keras import backend as K
    K.set_image_dim_ordering('th') # 定义输入数据的一种结构：tf、th

    classes = [0. ,  1. , 2. , 3. , 4.]  # 5类
  
    labelencoder = LabelEncoder()  # 标签编码器
    labelencoder.fit(classes) 

    model = load_model(model_path) # 加载模型，这一步很耗时

    img = cv2.imread(img_path) # 读入测试图片数据
    img = cv2.resize(img,(500,500)) # 因为训练时将图片重置为500*500像素，所以这里保持一致

    img = img.astype("float")/255.0 # 与训练时保持一致
    img = img_to_array(img) 

    img = np.expand_dims(img, axis=0)
    pred_ = model.predict_classes(img,verbose=2) # 获取测试结果
    #pred_ = int(labelencoder.inverse_transform(pred_[0]))
    return pred_

###############################
# 调用函数
result = predict(sys.argv[1], "D:/2019/20190508/apple/python/my_model_bp.h5")
print(result) # 输出一个列表


