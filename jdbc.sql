/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : jdbc

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2019-11-25 08:46:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shopName` varchar(255) NOT NULL,
  `prvice` double NOT NULL,
  `time` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('6', '牛仔裤', '55', '2019-11-13');
INSERT INTO `book` VALUES ('13', '牛奶', '55', '2019-11-15');
INSERT INTO `book` VALUES ('15', '旺仔牛奶', '12', '2019-11-15');
INSERT INTO `book` VALUES ('16', '苹果', '11', '2019-11-15');

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for `hobby`
-- ----------------------------
DROP TABLE IF EXISTS `hobby`;
CREATE TABLE `hobby` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `hobby` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hobby
-- ----------------------------
INSERT INTO `hobby` VALUES ('1233', '4456', '篮球');
INSERT INTO `hobby` VALUES ('1233', '4456', '乒乓球');
INSERT INTO `hobby` VALUES ('1233', '4456', '跑步');
INSERT INTO `hobby` VALUES ('chj1123', '45678', '篮球');
INSERT INTO `hobby` VALUES ('chj1123', '45678', '乒乓球');
INSERT INTO `hobby` VALUES ('chj1123', '45678', '排球');
INSERT INTO `hobby` VALUES ('chj1123', '45678', '跑步');
INSERT INTO `hobby` VALUES ('45678', '12345', '乒乓球');
INSERT INTO `hobby` VALUES ('12333', '4456', '篮球');
INSERT INTO `hobby` VALUES ('12333', '4456', '乒乓球');
INSERT INTO `hobby` VALUES ('12333', '4456', '排球');
INSERT INTO `hobby` VALUES ('123', '777777', '篮球');
INSERT INTO `hobby` VALUES ('123', '777777', '排球');
INSERT INTO `hobby` VALUES ('123456', '4444', '篮球');
INSERT INTO `hobby` VALUES ('123456', '4444', '乒乓球');
INSERT INTO `hobby` VALUES ('1234567', '111111', '篮球');
INSERT INTO `hobby` VALUES ('1234567', '150660', '篮球');
INSERT INTO `hobby` VALUES ('1234567', '150660', '乒乓球');
INSERT INTO `hobby` VALUES ('1234567', '150660', '排球');
INSERT INTO `hobby` VALUES ('1234567', '150660', '跑步');
INSERT INTO `hobby` VALUES ('150660123', '150660', '篮球');
INSERT INTO `hobby` VALUES ('150660123', '150660', '乒乓球');
INSERT INTO `hobby` VALUES ('150660123', '150660', '排球');
INSERT INTO `hobby` VALUES ('12345678', '123456', '篮球');
INSERT INTO `hobby` VALUES ('12345678', '123456', '乒乓球');
INSERT INTO `hobby` VALUES ('12345678', '123456', '排球');
INSERT INTO `hobby` VALUES ('1234', '123', '篮球');
INSERT INTO `hobby` VALUES ('1234', '123', '乒乓球');
INSERT INTO `hobby` VALUES ('1234', '123', '排球');
INSERT INTO `hobby` VALUES ('12345678', '123456', '篮球');
INSERT INTO `hobby` VALUES ('12345678', '123456', '乒乓球');
INSERT INTO `hobby` VALUES ('12345678', '123456', '排球');

-- ----------------------------
-- Table structure for `shopcart`
-- ----------------------------
DROP TABLE IF EXISTS `shopcart`;
CREATE TABLE `shopcart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `shopId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shopcart
-- ----------------------------
INSERT INTO `shopcart` VALUES ('21', '8', '5');
INSERT INTO `shopcart` VALUES ('22', '8', '6');
INSERT INTO `shopcart` VALUES ('23', '8', '7');
INSERT INTO `shopcart` VALUES ('28', '7', '6');
INSERT INTO `shopcart` VALUES ('29', '7', '6');
INSERT INTO `shopcart` VALUES ('30', '7', '6');
INSERT INTO `shopcart` VALUES ('31', '7', '6');
INSERT INTO `shopcart` VALUES ('32', '15', '6');
INSERT INTO `shopcart` VALUES ('33', '15', '12');

-- ----------------------------
-- Table structure for `usermessage`
-- ----------------------------
DROP TABLE IF EXISTS `usermessage`;
CREATE TABLE `usermessage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `sex` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usermessage
-- ----------------------------
INSERT INTO `usermessage` VALUES ('7', '1233', '4456', '男');
INSERT INTO `usermessage` VALUES ('8', 'chj1123', '45678', '女');
INSERT INTO `usermessage` VALUES ('9', '45678', '12345', '男');
INSERT INTO `usermessage` VALUES ('10', '12333', '4456', '男');
INSERT INTO `usermessage` VALUES ('11', '123', '777777', '男');
INSERT INTO `usermessage` VALUES ('12', '123456', '4444', '男');
INSERT INTO `usermessage` VALUES ('13', '1234567', '111111', '男');
INSERT INTO `usermessage` VALUES ('14', '1234567', '150660', '男');
INSERT INTO `usermessage` VALUES ('15', '150660123', '150660', '男');
INSERT INTO `usermessage` VALUES ('16', '12345678', '123456', '男');
INSERT INTO `usermessage` VALUES ('17', '1234', '123', '男');
INSERT INTO `usermessage` VALUES ('18', '12345678', '123456', '男');
INSERT INTO `usermessage` VALUES ('19', '123456', '123456', '男');
