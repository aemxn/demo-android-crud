/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50612
Source Host           : 127.0.0.1:3306
Source Database       : test_comment

Target Server Type    : MYSQL
Target Server Version : 50612
File Encoding         : 65001

Date: 2013-11-29 12:09:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for testtable
-- ----------------------------
DROP TABLE IF EXISTS `testtable`;
CREATE TABLE `testtable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comments` varchar(300) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of testtable
-- ----------------------------
INSERT INTO `testtable` VALUES ('44', 'what', '3');
INSERT INTO `testtable` VALUES ('45', 'try', '3');
INSERT INTO `testtable` VALUES ('46', 'try  hhdh', '3');
INSERT INTO `testtable` VALUES ('47', 'hahah', '5');
INSERT INTO `testtable` VALUES ('48', 'jabsba', '2');
INSERT INTO `testtable` VALUES ('49', 'fggfh', '5');
INSERT INTO `testtable` VALUES ('50', 'tes', '1');
INSERT INTO `testtable` VALUES ('51', 'jfhsjf', '1');
INSERT INTO `testtable` VALUES ('52', 'hihi', '6');
