/*
Navicat MySQL Data Transfer

Source Server         : 118.24.178.170_3306
Source Server Version : 50717
Source Host           : 118.24.178.170:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-02-08 17:45:28
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `email` varchar(320) DEFAULT NULL,
  `gmt_create` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('23', '资料下载', 'ziliaoxiazai', '1395359719@qq.com', '2019-01-23 20:10:55');
