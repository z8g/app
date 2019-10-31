# -*- coding: utf-8 -*-
"""
Created on Sat Sep 28 08:00:44 2019

@author: cjg
"""

from osgeo import gdal
from osgeo import osr
import sys

def getSRSPair(dataset):
    '''
    获得给定数据的投影参考系和地理参考系
    :param dataset: GDAL地理数据
    :return: 投影参考系和地理参考系
    '''
    prosrs = osr.SpatialReference()
    prosrs.ImportFromWkt(dataset.GetProjection())
    geosrs = prosrs.CloneGeogCS()
    return prosrs, geosrs

def lonlat2geo(dataset, lon, lat):
    '''
    将经纬度坐标转为投影坐标（具体的投影坐标系由给定数据确定）
    :param dataset: GDAL地理数据
    :param lon: 经度
    :param lat: 纬度
    :return: 经纬度(lon, lat)对应的投影坐标(x, y)
    '''
    prosrs, geosrs = getSRSPair(dataset)
    ct = osr.CoordinateTransformation(geosrs,prosrs)
    coords = ct.TransformPoint(lon, lat)
    return coords[:2]

def geo2imagexy(dataset, x, y):
    '''
    根据GDAL的六参数模型将影像图上坐标（行列号）转为投影坐标或地理坐标（根据具体数据的坐标系统转换）
    :param dataset: GDAL地理数据
    :param x: 投影坐标x
    :param y: 投影坐标y
    :return: 投影坐标(x, y)对应的行列号(row, col)
    '''
    trans = dataset.GetGeoTransform()
    col =  (x - trans[0])/trans[1]
    row =  (y - trans[3])/trans[5]
    return round(row),round(col)

if __name__ == '__main__':
    gdal.AllRegister()
    
    '''获取命令行参数'''
    imagePath = ''
    inputfile = ''
    outputfile = ''
    if len(sys.argv) < 4:
        print( 'xx.py <imagePath> <inputfile> <outputfile>')
        sys.exit()
    imagePath = sys.argv[1]
    inputfile = sys.argv[2]
    outputfile = sys.argv[3]
    
    '''获取图像数据'''
    dataset = gdal.Open(imagePath)
    
    '''读取文件经纬度，并转换保存 ''' 
    f = open(inputfile,"r")
    aa = []
    line = f.readline()
    line = line[:-1]
    while line:
        line = f.readline()
        if line == '\n':
            line = line.strip("\n")
        data = line.split('\t')
        if len(data) > 2:
            lon = float(data[2]) #经度
            lat = float(data[1])  #纬度
            #经纬度转换为图像投影坐标
            coords = lonlat2geo(dataset,lon,lat)
            #print('(%s, %s)->(%s, %s)' % (lon, lat,coords[0],coords[1]))
            #图像投影坐标转换为行列号
            (row,col) = geo2imagexy(dataset,coords[0],coords[1])
            #print('(%s, %s)->(%s, %s)' % (lon, lat,row,col))
            str1 = str(row) + ' '+ str(col)
            aa.append(str1)
    '''将行列号写入文件'''
    count = 0
    with open(outputfile,"w") as f1:                                                   #设置文件对象
        for i in aa:                                                                 #对于双层列表中的数据
            f1.writelines(i)
            count = count + 1
            if count != (len(aa)):
                f1.writelines('\n')
    f.close()
    f1.close()
    print("done!")