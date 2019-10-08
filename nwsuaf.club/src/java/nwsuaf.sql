/*
Navicat MySQL Data Transfer

Source Server         : 118.24.178.170_3306
Source Server Version : 50717
Source Host           : 118.24.178.170:3306
Source Database       : nwsuaf

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-03-07 16:55:26
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `apply`
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `realname` varchar(20) NOT NULL,
  `concat` varchar(50) NOT NULL,
  `content` varchar(500) NOT NULL,
  `project_id` int(10) NOT NULL,
  `username` varchar(20) NOT NULL,
  `gmt_create` varchar(19) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apply
-- ----------------------------
INSERT INTO `apply` VALUES ('4', '陈信宏', '111', '111', '4', '123456', '2019-03-07 11:37:46');
INSERT INTO `apply` VALUES ('5', 'a', 'b', 'c', '5', '123456', '2019-03-07 11:44:04');
INSERT INTO `apply` VALUES ('6', 'www', 'www', 'www', '5', '123456', '2019-03-07 16:17:06');
INSERT INTO `apply` VALUES ('7', '赵栩旸', '18821714373', '赵栩旸的简介', '2', '2015014117', '2019-03-07 16:29:37');
INSERT INTO `apply` VALUES ('8', '游客', '无', '我们啊飒飒', '0', '2015014117', '2019-03-07 16:30:37');
INSERT INTO `apply` VALUES ('9', 'wwwwww', '呜呜呜呜', '吾问无为谓', '2', '2015014117', '2019-03-07 16:33:20');
INSERT INTO `apply` VALUES ('10', 'www', '呜呜呜呜', '呜呜呜呜', '2', '2015014117', '2019-03-07 16:33:52');

-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '文章的ID，无符号自动递增',
  `title` varchar(40) NOT NULL DEFAULT '无标题' COMMENT '文章的标题',
  `type` varchar(20) NOT NULL DEFAULT '无分类' COMMENT '具体类型（比赛通知/培训报告/竞赛项目/在孵项目/学习资料/关于我们/FAQ）',
  `content` longtext NOT NULL COMMENT '文章的内容（富文本）',
  `gmt_create` varchar(19) NOT NULL COMMENT '该条记录的创建时间（yyyy-MM-dd HH:mm:ss）',
  `gmt_update` varchar(19) NOT NULL COMMENT '该条记录的修改时间（yyyy-MM-dd HH:mm:ss）',
  `username` varchar(20) NOT NULL COMMENT '作者的用户名,对应于user表中的username',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------

-- ----------------------------
-- Table structure for `project`
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目编号',
  `name` varchar(40) NOT NULL,
  `category` varchar(8) DEFAULT NULL COMMENT '项目的类别（在孵项目/竞赛项目）',
  `type` varchar(20) DEFAULT NULL COMMENT '项目的具体类型（信息技术/机械制造/环保能源/医疗健康/文化创意/公益创业/生活服务/教育/其他）',
  `content` varchar(1000) DEFAULT NULL COMMENT '项目简介（少于等于1000字）',
  `logo` varchar(200) DEFAULT NULL COMMENT '项目LOGO的图片地址',
  `state` varchar(3) DEFAULT NULL COMMENT '项目的状态（已发表/草稿/审核中）',
  `gmt_create` varchar(19) DEFAULT NULL COMMENT '项目创建时间（yyyy-MM-dd HH:mm:ss）',
  `gmt_update` varchar(19) DEFAULT NULL COMMENT '修改时间（yyyy-MM-dd HH:mm:ss）',
  `gmt_end` varchar(10) DEFAULT NULL COMMENT '申请截止日期（yyyy-MM-dd）',
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('1', '西北农林大学创新创业服务平台', '在孵项目', '信息技术', '        西北农林大学创新创业服务平台：用户可以管理自己的项目、提交项目；操作员可以审核项目(通过后成为公开信息)，发布比赛通知、培训报告、学习资料等公开信息。系统管理员可以对用户、操作员和文章进行管理。游客(即所有人)可以查阅公开信息和下载文件，访问用户主页等。', 'http://file.zxy97.com/download/20190306221739.png', '草稿', '2019-03-06 22:17:36', '2019-03-06 22:17:36', '2019-03-06', '2015014117');
INSERT INTO `project` VALUES ('2', '一介网盘', '在孵项目', '公益创业', '根据软件工程规范开发一个网盘，用户登录后可进行文件和文件夹的上传、文件管理。游客可进行目录的浏览和文件的下载。', 'http://file.zxy97.com/download/20190306222316.png', '已发布', '2019-03-06 22:23:13', '2019-03-06 22:23:13', '2019-01-02', '2015014117');
INSERT INTO `project` VALUES ('3', 'zxy97 博客  ', '在孵项目', '信息技术', ' 开发一个支持 MarkDown 语法的多用户博客系统。\r\n    作者：拥有个人主页，主页中按照文章的分类依次显示出所有文章，作者可以新建、修改、删除文章。\r\n    文章：可由作者自定义分类，权限为公开、登录可见、仅自己可见；文章的内容使用 MarkDown 编写，即支持引用目录，标题，超链接，表格，代码高亮，序列图，流程图，LaTeX ，图片上传等。\r\n', 'http://file.zxy97.com/download/20190306222729.png', '审核中', '2019-03-06 22:27:23', '2019-03-07 16:50:36', '2019-03-09', '2015014117');
INSERT INTO `project` VALUES ('4', 'A', '竞赛项目', '信息技术', '........', '', '已发布', '2019-03-07 11:36:35', '2019-03-07 11:36:35', '2019-03-14', '123456');
INSERT INTO `project` VALUES ('5', 'B', '在孵项目', '信息技术', '1', '', '已发布', '2019-03-07 11:43:13', '2019-03-07 11:43:13', '2019-03-19', '123456');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(20) NOT NULL COMMENT '用户名（字母或数字），主键',
  `password` varchar(20) NOT NULL COMMENT '登录密码',
  `realname` varchar(20) NOT NULL COMMENT '真实姓名',
  `auth` varchar(5) NOT NULL COMMENT '用户身份（用户/操作员/系统管理员）',
  `gmt_create` varchar(19) NOT NULL COMMENT '该条记录的创建时间（yyyy-MM-dd HH:mm:ss）',
  `info` varchar(1000) DEFAULT NULL COMMENT '用户信息（用户简介，少于等于1000字）',
  `logo` varchar(200) DEFAULT NULL COMMENT '头像地址',
  `birth` varchar(10) DEFAULT NULL COMMENT '出生日期（yyyy-MM-dd）',
  `tel` varchar(16) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(320) DEFAULT NULL COMMENT '邮箱地址，用于找回密码',
  `gender` varchar(2) DEFAULT NULL COMMENT '性别（男/女/保密）',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('123456', '12345678', '陈信宏', '用户', '2019-03-07 11:34:14', '', '', '', '', '936294469@qq.com', '男');
INSERT INTO `user` VALUES ('2015014117', '2015014117', '赵栩旸', '用户', '2019-02-06 11:54:38', '', '', '', '', '1395359719@qq.com', '保密');
INSERT INTO `user` VALUES ('caozuoyuan', 'caozuoyuan', '操作员1号', '操作员', '2019-02-05 22:53:34', '', '', '', '', '1395359719@qq.com', '男');
INSERT INTO `user` VALUES ('zhaoxuyang', 'zhaoxuyang', '系统管理员', '系统管理员', '2019-02-05 16:45:36', '西北农林科技大学创新创业服务平台的开发者。\r\n\r\n个人主页：<a href=\"http://zxy97.com/\" target=\"_blank\">zxy97.com</a>\r\nQQ：1395359719\r\n<script>alert(\"欢迎来到赵栩旸的主页！\")</script>', 'http://file.zxy97.com/download/20190205164418.png', '1997-10-15', '18821714373', '1395359719@qq.com', '男');
