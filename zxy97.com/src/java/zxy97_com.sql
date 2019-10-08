/*
Navicat MySQL Data Transfer

Source Server         : root_118.24.178.170_3306
Source Server Version : 50636
Source Host           : 118.24.178.170:3306
Source Database       : zxy97_com

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2018-12-22 20:14:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_update` datetime DEFAULT NULL,
  `title` text,
  `title_link` text,
  `content` text,
  `full_link` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', '2018-12-22 17:33:00', '2018-12-22 17:33:00', '活性氮的气候效应', '#', '<h3><i class=\"fa fa-file-text-o\"></i> 项目描述</h3>\r\n                        <p>中国农业大学资源与环境学院巨晓棠教授主持的 <code>973</code> 项目我国活性氮源及其对空气质量与气候变化的影响机理研究的第三子课题活性氮的气候效应（课题编号：2014CB953803）</p>\r\n\r\n                        <h2><i class=\"fa fa-user-o\"></i> 项目职责</h2>\r\n                        <p>用 <code>VB.NET</code> 开发Windows桌面应用——《活性氮气候效应测算系统》。</p>', '#');
INSERT INTO `article` VALUES ('2', '2018-12-22 19:33:00', '2018-12-22 19:33:00', 'zxy97博客', 'http://blog.zxy97.com/', '<h2><i class=\"fa fa-file-text-o\"></i> 项目描述</h2>\r\n                        <p>基于 <code>SpringMVC</code> 和 <code>MyBatis</code> 开发的一个 <code>RESTful</code> 风格的博客系统</p>\r\n                        <p>支持多个用户同时发布支持 <code>MarkDown</code> 语法的博文：博文支持引用目录，标题，超链接，表格，代码高亮，序列图，流程图，LaTeX，图片上传等</p>\r\n                        <p>实现博文的发表、修改、回收站、恢复、彻底删除、分类、权限设置</p>\r\n                        <p>用户主页的显示，如：<a href=\"http://blog.zxy97.com/zhaoxuyang\" title=\"http://blog.zxy97.com/zhaoxuyang\"><code>http://blog.zxy97.com/zhaoxuyang</code></a> </p>\r\n                        <h2><i class=\"fa fa-user-o\"></i> 项目职责</h2>\r\n                        <ol>\r\n                            <li>控制层使用SpringMVC的注解实现</li>\r\n                            <li>DAO层使用MyBatis的注解以及ResultMap实现MySQL数据库的操作</li>\r\n                            <li>View层使用EL和JSTL显示服务端的数据</li>\r\n                            <li>使用Lambda表达式进行文章的分类汇总</li>\r\n                            <li>前端集成了Markdown，使用Jquery发送Ajax请求</li>\r\n                            <li>图片上传功能使用自定义的Servlet实现</li>\r\n                            <li>使用Ajax进行注册查重</li>\r\n                            <li>使用session进行用户会话跟踪</li>\r\n                            <li>使用Tomcat在云主机上部署应用：<a href=\"http://blog.zxy97.com\" title=\"http://blog.zxy97.com\"><code>http://blog.zxy97.com</code></a></li>\r\n                        </ol>', 'http://blog.zxy97.com/zhaoxuyang/article/details/26');
INSERT INTO `article` VALUES ('3', '2018-12-22 19:34:23', '2018-12-22 19:34:23', 'zxy97加密解密工具', 'http://jiami.zxy97.com/', '<h2><i class=\"fa fa-user-o\"></i> 项目内容</h2>\r\n                        <ol>\r\n                            <li>网站界面使用BootStrap实现</li>\r\n                            <li>页面请求通过Ajax和Servlet实现</li>\r\n                            <li>页面传参使用JSON和session实现</li>\r\n                            <li>AES桌面版使用JFrame实现</li>\r\n                            <li>AES网页版包含了文件上传下载、文件压缩解压、对称加密等技术</li>\r\n                            <li>RSA中包含了公钥加密、私钥解密，私钥签名、公钥验证等技术</li>\r\n                            <li>散列实现了MD5、SHA</li>\r\n                            <li>使用Tomcat在云主机上部署应用：<a href=\"http://jiami.zxy97.com\" title=\"http://jiami.zxy97.com\"><code>http://jiami.zxy97.com</code></a></li>\r\n                        </ol>', 'http://blog.zxy97.com/zhaoxuyang/article/details/28');
INSERT INTO `article` VALUES ('4', '2018-12-22 19:35:03', '2018-12-22 19:35:03', 'zxy97网盘', 'http://pan.zxy97.com/', '<h2><i class=\"fa fa-file-text-o\"></i> 项目描述</h2>\r\n                        <p>来源于西北农林科技大学信息工程学院2018届面向对象程序设计实践——Java方向。</p>\r\n                        <p>基于 <code>Java</code> 开发的一款网盘 ，用以实现多用户在线浏览、上传文件(夹)、下载文件，以及对文件和目录的创建、删除、重命名、隐藏等操作。</p>\r\n                        <ol>\r\n                            <li>支持上传文件夹</li>\r\n                        </ol>\r\n\r\n                        <h2><i class=\"fa fa-user-o\"></i> 项目职责</h2>\r\n                        <ol>\r\n                            <li>使用JSP + Servlet 技术，采用MVC模式进行开发</li>\r\n                            <li>使用session进行会话跟踪</li>\r\n                            <li>使用javax.mail实现邮件发送，用于用户注册激活，找回密码</li>\r\n                            <li>使用java.io.File实现文件操作</li>\r\n                            <li>使用MySQL + JDBC 存储用户信息</li>\r\n                            <li>通过自定义配置文件设置网站参数，文件上传大小，网站LOGO，邮件授权信息等</li>\r\n                            <li>使用Ajax进行注册查重</li>\r\n                            <li>使用org.apache.commons.fileupload实现上传文件和文件夹</li>\r\n                            <li>使用Tomcat在云主机上部署应用：<a href=\"http://pan.zxy97.com\" title=\"http://pan.zxy97.com\"><code>http://pan.zxy97.com</code></a></li>\r\n                        </ol>', 'http://blog.zxy97.com/zhaoxuyang/article/details/30');
INSERT INTO `article` VALUES ('5', '2018-12-22 19:36:22', '2018-12-22 19:36:22', 'CAFU', '#', '<h3><i class=\"fa fa-file-text-o\"></i> 项目描述</h3>\r\n                        <p>\"CAFU is a galaxy workflow for comprehensive as sembly and function annotation of large-scale unmapped RNA-Seq data.\"</p>\r\n\r\n                        <h2><i class=\"fa fa-user-o\"></i> 项目职责</h2>\r\n                        <p>基于Galaxy编写 <code>Shell</code> 脚本和 <code>XML</code> 文件，从而制作出系统的各个工具。</p>\r\n                        <p>将各个工具整合成 <code>workflow</code> 。</p>\r\n                        <p>将系统部署到 <code>亚马逊云</code> 上。</p>', '#');
INSERT INTO `article` VALUES ('6', '2018-12-22 19:37:41', '2018-12-22 19:37:41', 'RAP2', '#', '<h3><i class=\"fa fa-file-text-o\"></i> 项目描述</h3>\r\n                        <p>\"RAP2 Galaxy Explorer\" -- a webserver to quality control gene expression profile, gene prediction and GWAS boilogical interpretation.\"</p>\r\n\r\n                        <h2><i class=\"fa fa-user-o\"></i> 项目职责</h2>\r\n                        <p>基于Galaxy编写 <code>Shell</code> 脚本和 <code>XML</code> 文件，从而制作出系统的各个工具。</p>\r\n                        <p>将各个工具整合成 <code>workflow</code> 。</p>\r\n                        <p>将系统打包到 <code>Docker</code> 中。</p>', '#');

-- ----------------------------
-- Table structure for `link`
-- ----------------------------
DROP TABLE IF EXISTS `link`;
CREATE TABLE `link` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` text,
  `href` text,
  `content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of link
-- ----------------------------
INSERT INTO `link` VALUES ('1', 'zxy97加密 – 提供文件的在线加解密、常用加密算法', 'http://jiami.zxy97.com/', 'zxy97加密');
INSERT INTO `link` VALUES ('2', 'zxy97短网址 – 在线生成短网址', 'http://dwz.zxy97.com/', 'zxy97短网址');
INSERT INTO `link` VALUES ('3', 'zxy97在线制作 – 在线制作软件下载页面', 'http://ogdp.zxy97.com/', 'zxy97在线制作');
INSERT INTO `link` VALUES ('4', 'zxy97图像识别 – 免费提供图像识别、文字识别、人脸识别服务', 'http://img.ai.zxy97.com/', 'zxy97图像识别');
INSERT INTO `link` VALUES ('5', 'zxy97文件托管 – 免费的资源托管平台', 'http://file.zxy97.com/', 'zxy97文件托管');
INSERT INTO `link` VALUES ('6', 'zxy97网盘 – 为文件的存储、管理与分享而生！', 'http://pan.zxy97.com/', 'zxy97网盘');
INSERT INTO `link` VALUES ('7', 'zxy97博客 – 记录美好回忆', 'http://blog.zxy97.com/', 'zxy97博客');

-- ----------------------------
-- Table structure for `nav`
-- ----------------------------
DROP TABLE IF EXISTS `nav`;
CREATE TABLE `nav` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `content` text,
  `link` text,
  `title` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nav
-- ----------------------------
INSERT INTO `nav` VALUES ('1', 'CSDN博客', 'https://blog.csdn.net/zhaoxuyang1997/', 'https://blog.csdn.net/zhaoxuyang1997/');
INSERT INTO `nav` VALUES ('2', 'GitHub', 'https://github.com/1395359719/', 'https://github.com/1395359719/');
INSERT INTO `nav` VALUES ('3', 'QQ日志', 'https://user.qzone.qq.com/1395359719/', 'https://user.qzone.qq.com/1395359719/');
