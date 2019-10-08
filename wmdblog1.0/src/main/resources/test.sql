/*
Navicat MySQL Data Transfer

Source Server         : 118.24.178.170_3306
Source Server Version : 50717
Source Host           : 118.24.178.170:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-06-18 17:39:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `title` varchar(40) DEFAULT '无标题',
  `category` varchar(20) DEFAULT '无分类',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_update` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `markdown` mediumtext NOT NULL,
  `summary` varchar(100) DEFAULT NULL,
  `tags` varchar(200) DEFAULT NULL,
  `auth` tinyint(4) DEFAULT '0',
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `article_tags_idx` (`tags`)
) ENGINE=InnoDB AUTO_INCREMENT=116028 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for article_comment
-- ----------------------------
DROP TABLE IF EXISTS `article_comment`;
CREATE TABLE `article_comment` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `content` varchar(500) NOT NULL,
  `article_id` mediumint(9) DEFAULT NULL,
  `user_id` mediumint(9) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comment_reply
-- ----------------------------
DROP TABLE IF EXISTS `comment_reply`;
CREATE TABLE `comment_reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(500) NOT NULL,
  `comment_id` int(11) NOT NULL,
  `reply_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `gmt_create` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `notice_id` int(11) NOT NULL,
  `gmt_create` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `salt` varchar(4) NOT NULL DEFAULT 'salt',
  `password` char(40) NOT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `email` varchar(320) DEFAULT NULL,
  `auth` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9832 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `gender` varchar(2) NOT NULL DEFAULT '保密',
  `nickname` varchar(10) NOT NULL,
  `nickimg` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`) USING BTREE,
  CONSTRAINT `user_userinfo_Ids` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for visit_article
-- ----------------------------
DROP TABLE IF EXISTS `visit_article`;
CREATE TABLE `visit_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `visitor_id` int(11) NOT NULL,
  `article_id` int(11) NOT NULL,
  `gmt_create` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for visit_user
-- ----------------------------
DROP TABLE IF EXISTS `visit_user`;
CREATE TABLE `visit_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `visitor_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `gmt_create` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Procedure structure for splitString
-- ----------------------------
DROP PROCEDURE IF EXISTS `splitString`;
DELIMITER ;;
CREATE DEFINER=`zhaoxuyang`@`%` PROCEDURE `splitString`(IN f_string varchar(1000),IN f_delimiter varchar(5))
BEGIN 
declare cnt int default 0; 
declare i int default 0; 
set cnt = func_split_TotalLength(f_string,f_delimiter); 
DROP TABLE IF EXISTS `tmp_split`; 
create temporary table `tmp_split` (`val_` varchar(128) not null) DEFAULT CHARSET=utf8;

while i < cnt 
do 
set i = i + 1; 
insert into tmp_split(`val_`) values (func_split(f_string,f_delimiter,i)); 
end while; 
END
;;
DELIMITER ;
