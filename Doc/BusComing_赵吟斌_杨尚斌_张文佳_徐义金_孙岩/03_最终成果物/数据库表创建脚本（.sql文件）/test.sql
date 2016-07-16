/*
Navicat MySQL Data Transfer

Source Server         : Dictionary
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-06-15 19:40:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for deposit
-- ----------------------------
DROP TABLE IF EXISTS `deposit`;
CREATE TABLE `deposit` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `DateBy` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ShoesName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SpecNo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Remark` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Colour` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Inventory` int(11) DEFAULT NULL,
  `Shop` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `State` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of deposit
-- ----------------------------
INSERT INTO `deposit` VALUES ('1', '2016-10-10', '10002', '43', '154', '黑', '100', '4546', '未处理订单');
INSERT INTO `deposit` VALUES ('2', '6789', '123', '7890', '765', '3456', '432', '34567', '未处理订单');

-- ----------------------------
-- Table structure for library
-- ----------------------------
DROP TABLE IF EXISTS `library`;
CREATE TABLE `library` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ShoesName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SpecNo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Bid` int(11) DEFAULT NULL,
  `Remark` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Inventory` int(11) DEFAULT NULL,
  `DateBy` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Retailprice` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of library
-- ----------------------------

-- ----------------------------
-- Table structure for market
-- ----------------------------
DROP TABLE IF EXISTS `market`;
CREATE TABLE `market` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `DateBy` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ShoesName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Shop` int(11) DEFAULT NULL,
  `Bid` int(11) DEFAULT NULL,
  `Situation` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `RecorderName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Cma` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of market
-- ----------------------------
INSERT INTO `market` VALUES ('1', '2016-6-12', '10003', '500', '100', '红', '3245', '40');

-- ----------------------------
-- Table structure for tuiho
-- ----------------------------
DROP TABLE IF EXISTS `tuiho`;
CREATE TABLE `tuiho` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `DateBy` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ShoesName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SpecNo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Rmaerk` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `State` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TuiName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Color` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Inventory` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tuiho
-- ----------------------------

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `UserName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LoginName` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', 'snowin', '普通管理员', 'snowin');
INSERT INTO `userinfo` VALUES ('2', 'user01', '系统管理员', 'user01');
