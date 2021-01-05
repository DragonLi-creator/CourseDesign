/*
 Navicat MySQL Data Transfer

 Source Server         : MySQL80
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : coursedesign

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 05/01/2021 18:47:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`, `name`) USING BTREE,
  INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (3, '万柏林区');
INSERT INTO `address` VALUES (2, '小店区');
INSERT INTO `address` VALUES (1, '尖草坪区');
INSERT INTO `address` VALUES (4, '迎泽区');

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `head_icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '123456', 'D:\\\\CourseDesign\\\\picture\\\\UserPicture\\\\1.jpg');

-- ----------------------------
-- Table structure for appointment
-- ----------------------------
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `appointment_time` datetime(0) NOT NULL,
  `house_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `house_id1`(`house_id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE,
  CONSTRAINT `house_id1` FOREIGN KEY (`house_id`) REFERENCES `house` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `name` FOREIGN KEY (`name`) REFERENCES `member` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of appointment
-- ----------------------------
INSERT INTO `appointment` VALUES (1, 'm1', '123456', '2021-01-07 16:08:43', 1);

-- ----------------------------
-- Table structure for contract
-- ----------------------------
DROP TABLE IF EXISTS `contract`;
CREATE TABLE `contract`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `host_id` int NOT NULL,
  `rent_id` int NOT NULL,
  `house_id` int NOT NULL,
  `content_path` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `start_time` datetime(0) NOT NULL,
  `end_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `host_id`(`host_id`) USING BTREE,
  INDEX `rent_id`(`rent_id`) USING BTREE,
  INDEX `house_id`(`house_id`) USING BTREE,
  CONSTRAINT `host_id` FOREIGN KEY (`host_id`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `house_id` FOREIGN KEY (`house_id`) REFERENCES `house` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rent_id` FOREIGN KEY (`rent_id`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of contract
-- ----------------------------
INSERT INTO `contract` VALUES (1, 2, 1, 2, 'D:\\\\CourseDesign\\\\contract\\\\1.txt', '2021-01-10 16:13:18', '2021-02-07 16:13:24');

-- ----------------------------
-- Table structure for house
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `host_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `house_resource` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `house_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` int NOT NULL,
  `pay_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `floor` int NOT NULL,
  `area` int NOT NULL,
  `renovation` int NOT NULL,
  `image` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `release_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `address_name`(`address_name`) USING BTREE,
  INDEX `host_name`(`host_name`) USING BTREE,
  INDEX `house_resource`(`house_resource`) USING BTREE,
  INDEX `house_type`(`house_type`) USING BTREE,
  INDEX `pay_type`(`pay_type`) USING BTREE,
  CONSTRAINT `address_name` FOREIGN KEY (`address_name`) REFERENCES `address` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `host_name` FOREIGN KEY (`host_name`) REFERENCES `member` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `house_resource` FOREIGN KEY (`house_resource`) REFERENCES `house_resource_type` (`resource_type`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `house_type` FOREIGN KEY (`house_type`) REFERENCES `house_type` (`house_type`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pay_type` FOREIGN KEY (`pay_type`) REFERENCES `pay_type` (`pay_type`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of house
-- ----------------------------
INSERT INTO `house` VALUES (1, '1号', '尖草坪区', 'm2', '住居房', '三室两厅一卫', 10000, '微信', 3, 400000, 1, 'D:\\\\CourseDesign\\\\picture\\\\UserPicture\\\\1.jpg', '无', '2021-01-06 15:55:08');
INSERT INTO `house` VALUES (2, '2号', '小店区', 'm2', '住居房', '两室一厅一卫', 5000, '支付宝', 2, 200000, 0, 'D:\\\\CourseDesign\\\\picture\\\\UserPicture\\\\1.jpg', '无', '2021-01-04 16:01:35');

-- ----------------------------
-- Table structure for house_resource_type
-- ----------------------------
DROP TABLE IF EXISTS `house_resource_type`;
CREATE TABLE `house_resource_type`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `resource_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `resource_type`(`resource_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of house_resource_type
-- ----------------------------
INSERT INTO `house_resource_type` VALUES (1, '住居房');
INSERT INTO `house_resource_type` VALUES (3, '写字楼');
INSERT INTO `house_resource_type` VALUES (2, '店面房');

-- ----------------------------
-- Table structure for house_type
-- ----------------------------
DROP TABLE IF EXISTS `house_type`;
CREATE TABLE `house_type`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `house_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `house_type`(`house_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of house_type
-- ----------------------------
INSERT INTO `house_type` VALUES (1, '一室一厅一卫');
INSERT INTO `house_type` VALUES (4, '三室两厅一卫');
INSERT INTO `house_type` VALUES (2, '两室一厅一卫');
INSERT INTO `house_type` VALUES (3, '两室两厅一卫');
INSERT INTO `house_type` VALUES (5, '四室两厅两卫');
INSERT INTO `house_type` VALUES (6, '无');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` int NOT NULL,
  `head_icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `age` int NOT NULL,
  `tel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `adress` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mail` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `host` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE,
  INDEX `address`(`adress`) USING BTREE,
  CONSTRAINT `address` FOREIGN KEY (`adress`) REFERENCES `address` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES (1, 'm1', 1, 'D:\\\\CourseDesign\\\\picture\\\\UserPicture\\\\1.jpg', 'm1', '123456', 18, '123456', '尖草坪区', '@163.com', 0);
INSERT INTO `member` VALUES (2, 'm2', 0, 'D:\\\\CourseDesign\\\\picture\\\\UserPicture\\\\1.jpg', 'm2', '123456', 20, '123456', '小店区', '@qq.com', 1);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `message_time` datetime(0) NOT NULL,
  `house_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `house_id2`(`house_id`) USING BTREE,
  INDEX `name2`(`name`) USING BTREE,
  CONSTRAINT `house_id2` FOREIGN KEY (`house_id`) REFERENCES `house` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `name2` FOREIGN KEY (`name`) REFERENCES `member` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, 'm1', '无', '2021-01-09 16:25:02', 1);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` int NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `detail_id` int NOT NULL,
  `image` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `detail_id`(`detail_id`) USING BTREE,
  INDEX `name1`(`name`) USING BTREE,
  CONSTRAINT `detail_id` FOREIGN KEY (`detail_id`) REFERENCES `house` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `name1` FOREIGN KEY (`name`) REFERENCES `member` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '无', 1, 'm2', 1, 'D:\\\\CourseDesign\\\\picture\\\\UserPicture\\\\1.jpg');

-- ----------------------------
-- Table structure for pay_type
-- ----------------------------
DROP TABLE IF EXISTS `pay_type`;
CREATE TABLE `pay_type`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `pay_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pay_type`(`pay_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pay_type
-- ----------------------------
INSERT INTO `pay_type` VALUES (2, '微信');
INSERT INTO `pay_type` VALUES (1, '支付宝');
INSERT INTO `pay_type` VALUES (3, '网银');

-- ----------------------------
-- Table structure for rent
-- ----------------------------
DROP TABLE IF EXISTS `rent`;
CREATE TABLE `rent`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `address_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `house_resource` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` int NOT NULL,
  `house_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `people` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `release_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `address_name1`(`address_name`) USING BTREE,
  INDEX `house_resource1`(`house_resource`) USING BTREE,
  INDEX `house_type1`(`house_type`) USING BTREE,
  INDEX `people`(`people`) USING BTREE,
  CONSTRAINT `address_name1` FOREIGN KEY (`address_name`) REFERENCES `address` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `house_resource1` FOREIGN KEY (`house_resource`) REFERENCES `house_resource_type` (`resource_type`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `house_type1` FOREIGN KEY (`house_type`) REFERENCES `house_type` (`house_type`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `people` FOREIGN KEY (`people`) REFERENCES `member` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rent
-- ----------------------------
INSERT INTO `rent` VALUES (1, '万柏林区', '住居房', 50000, '两室一厅一卫', '无', 'm1', '2021-01-02 16:23:15');

SET FOREIGN_KEY_CHECKS = 1;
