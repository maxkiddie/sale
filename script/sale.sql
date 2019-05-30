/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50557
 Source Host           : 127.0.0.1:3306
 Source Schema         : sale

 Target Server Type    : MySQL
 Target Server Version : 50557
 File Encoding         : 65001

 Date: 30/05/2019 17:58:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `realname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `use_status` tinyint(2) NOT NULL,
  `reg_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '9cbf8a4dcb8e30682b927f352d6559a0', 'Kiddie', '531592003@qq.com', '15088132421', 1, '2019-05-27 10:44:10');

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `use_status` tinyint(2) NOT NULL,
  `inx` tinyint(3) NULL DEFAULT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES (4, 'banner名字', 'http://baidu.com', NULL, NULL, '图片', '2016-08-01 10:20:30', '2016-08-02 10:20:30', 1, 0, '2019-05-30 11:44:35');

-- ----------------------------
-- Table structure for faq
-- ----------------------------
DROP TABLE IF EXISTS `faq`;
CREATE TABLE `faq`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ask` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `inx` tinyint(3) NOT NULL,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of faq
-- ----------------------------
INSERT INTO `faq` VALUES (6, '我的问题', '回答了', 0, NULL);

-- ----------------------------
-- Table structure for o_order
-- ----------------------------
DROP TABLE IF EXISTS `o_order`;
CREATE TABLE `o_order`  (
  `order_id` bigint(20) NOT NULL,
  `total_pay` bigint(20) NOT NULL,
  `actual_pay` bigint(20) NOT NULL,
  `payment_type` tinyint(2) NOT NULL,
  `post_fee` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `shipping_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shipping_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int(20) NOT NULL,
  `buyer_nick` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `buyer_message` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `buyer_rate` tinyint(1) NULL DEFAULT NULL,
  `receiver_country` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `receiver_state` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `receiver_city` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `receiver_district` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `receiver_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `receiver_mobile` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `receiver_zip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `receiver` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of o_order
-- ----------------------------
INSERT INTO `o_order` VALUES (201905301056433031, 800100, 600100, 1, 500, '2019-05-30 10:56:43', '顺丰快递', '123465123456789', 1, 'Kiddie', '一定要做好', 0, '中国', '广东省', '广州市', '增城区', '荔城街府佑路1号', '+8615088132421', '511300', '徐则林');

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `sku_id` bigint(20) NOT NULL,
  `num` int(11) NOT NULL,
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` bigint(20) NOT NULL,
  `total_price` bigint(20) NOT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES (7, 201905301056433031, 11, 4, '苹果手机黑色64G', 199900, 599600, '主图');

-- ----------------------------
-- Table structure for order_status
-- ----------------------------
DROP TABLE IF EXISTS `order_status`;
CREATE TABLE `order_status`  (
  `order_id` bigint(20) NOT NULL,
  `order_status` tinyint(2) NOT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `payment_time` datetime NULL DEFAULT NULL,
  `consign_time` datetime NULL DEFAULT NULL,
  `end_time` datetime NULL DEFAULT NULL,
  `close_time` datetime NULL DEFAULT NULL,
  `comment_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of order_status
-- ----------------------------
INSERT INTO `order_status` VALUES (201905301056433031, 4, '2019-05-30 10:56:43', '2019-05-30 10:59:32', '2019-05-30 10:59:50', '2019-05-30 11:00:31', NULL, NULL);

-- ----------------------------
-- Table structure for person_info
-- ----------------------------
DROP TABLE IF EXISTS `person_info`;
CREATE TABLE `person_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `first_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `middle_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `birth_date` datetime NOT NULL,
  `state` tinyint(2) NOT NULL,
  `height_feet` double NOT NULL,
  `height_inches` double NOT NULL,
  `weight` double NOT NULL,
  `eyes` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `hair` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` tinyint(2) NOT NULL,
  `address1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `zip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `signature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `additional` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of person_info
-- ----------------------------
INSERT INTO `person_info` VALUES (5, 1, 'Xu', 'Zhao', 'Jie', '2019-05-28 09:39:26', 123, 20, 30, 54, 'Blue', 'Black', 1, 'Guangdong', 'ddddddd', 'guangzhou', '511300', '图片0', '签名', NULL);
INSERT INTO `person_info` VALUES (6, 1, 'Xu', 'Zhao', 'Jie', '2019-05-28 09:39:26', 123, 20, 30, 54, 'Blue', 'Black', 1, 'Guangdong', 'ddddddd', 'guangzhou', '511300', '图片0', '签名', NULL);
INSERT INTO `person_info` VALUES (7, 1, 'Xu', 'Zhao', 'Jie', '2019-05-28 09:39:26', 123, 20, 30, 54, 'Blue', 'Black', 1, 'Guangdong', 'ddddddd', 'guangzhou', '511300', '图片0', '签名', NULL);
INSERT INTO `person_info` VALUES (8, 1, 'Xu', 'Zhao', 'Jie', '2019-05-28 09:39:26', 123, 20, 30, 54, 'Blue', 'Black', 1, 'Guangdong', 'ddddddd', 'guangzhou', '511300', '图片0', '签名', NULL);
INSERT INTO `person_info` VALUES (9, 1, 'Xu', 'Zhao', 'Jie', '2019-05-28 09:39:26', 123, 20, 30, 54, 'Blue', 'Black', 1, 'Guangdong', 'ddddddd', 'guangzhou', '511300', '图片0', '签名', NULL);
INSERT INTO `person_info` VALUES (10, 1, 'Xu', 'Zhao', 'Jie', '2019-05-28 09:39:26', 123, 20, 30, 54, 'Blue', 'Black', 1, 'Guangdong', 'ddddddd', 'guangzhou', '511300', '图片0', '签名', NULL);
INSERT INTO `person_info` VALUES (11, 1, 'Xu', 'Zhao', 'Jie', '2019-05-28 09:39:26', 123, 20, 30, 54, 'Blue', 'Black', 1, 'Guangdong', 'ddddddd', 'guangzhou', '511300', '图片0', '签名', NULL);
INSERT INTO `person_info` VALUES (12, 1, 'Xu', 'Zhao', 'Jie', '2019-05-28 09:39:26', 123, 20, 30, 54, 'Blue', 'Black', 1, 'Guangdong', 'ddddddd', 'guangzhou', '511300', '图片0', '签名', NULL);
INSERT INTO `person_info` VALUES (13, 1, 'Xu', 'Zhao', 'Jie', '2019-05-28 09:39:26', 123, 20, 30, 54, 'Blue', 'Black', 1, 'Guangdong', 'ddddddd', 'guangzhou', '511300', '图片0', '签名', NULL);
INSERT INTO `person_info` VALUES (14, 1, 'Xu', 'Zhao', 'Jie', '2019-05-28 09:39:26', 123, 20, 30, 54, 'Blue', 'Black', 1, 'Guangdong', 'ddddddd', 'guangzhou', '511300', '图片0', '签名', NULL);

-- ----------------------------
-- Table structure for r_order_person
-- ----------------------------
DROP TABLE IF EXISTS `r_order_person`;
CREATE TABLE `r_order_person`  (
  `id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `person_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for reduction
-- ----------------------------
DROP TABLE IF EXISTS `reduction`;
CREATE TABLE `reduction`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `spu_id` int(11) NOT NULL,
  `sku_id` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `limit_num` int(11) NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of reduction
-- ----------------------------
INSERT INTO `reduction` VALUES (3, 13, 11, '优惠很大', '3部手机每部1499', 149900.00, 3, '2019-05-09 12:11:23', '2019-06-09 12:11:27', '2019-05-30 10:54:05');
INSERT INTO `reduction` VALUES (4, 13, 11, '优惠很大', '2部手机每部1699', 169900.00, 2, '2019-05-30 00:15:00', '2019-06-30 20:15:00', '2019-05-30 11:47:50');

-- ----------------------------
-- Table structure for sku
-- ----------------------------
DROP TABLE IF EXISTS `sku`;
CREATE TABLE `sku`  (
  `sku_id` int(11) NOT NULL AUTO_INCREMENT,
  `spu_id` int(11) NOT NULL,
  `main_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `spu_specs` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stock` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `now_price` decimal(10, 2) NULL DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`sku_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sku
-- ----------------------------
INSERT INTO `sku` VALUES (11, 13, '主图', '苹果手机黑色64G', '999', 249900.00, 199900.00, '2019-05-30 10:45:02', '2019-05-30 10:45:02');

-- ----------------------------
-- Table structure for spu
-- ----------------------------
DROP TABLE IF EXISTS `spu`;
CREATE TABLE `spu`  (
  `spu_id` int(20) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `main_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `max_price` decimal(10, 2) NULL DEFAULT NULL,
  `now_max_price` decimal(10, 2) NULL DEFAULT NULL,
  `min_price` decimal(10, 2) NULL DEFAULT NULL,
  `now_min_price` decimal(10, 0) NULL DEFAULT NULL,
  `spu_status` tinyint(2) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`spu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of spu
-- ----------------------------
INSERT INTO `spu` VALUES (13, 0, 'IPhoneX', '苹果手机便宜卖', '图片', 249900.00, 199900.00, 249900.00, 199900, 1, '2019-05-30 10:43:31', '2019-05-30 10:45:02');

-- ----------------------------
-- Table structure for spu_detail
-- ----------------------------
DROP TABLE IF EXISTS `spu_detail`;
CREATE TABLE `spu_detail`  (
  `spu_id` int(11) NOT NULL,
  `images` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `detail` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`spu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of spu_detail
-- ----------------------------
INSERT INTO `spu_detail` VALUES (13, '图片;图片;图片', '苹果手机详情富文本');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `realname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `use_status` tinyint(2) NOT NULL,
  `reg_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, 'kiddie', '9cbf8a4dcb8e30682b927f352d6559a0', '徐则林', NULL, NULL, 1, '2019-05-27 19:50:13');
INSERT INTO `user` VALUES (3, 'xuzhaojie', 'e10adc3949ba59abbe56e057f20f883e', '徐则林', NULL, NULL, 1, '2019-05-30 11:14:32');
INSERT INTO `user` VALUES (4, 'wenjiayi', 'e10adc3949ba59abbe56e057f20f883e', '徐则林', '531592003@qq.com', ' 8615088132421', 1, '2019-05-30 11:39:35');

-- ----------------------------
-- Table structure for web_info
-- ----------------------------
DROP TABLE IF EXISTS `web_info`;
CREATE TABLE `web_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `detail` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of web_info
-- ----------------------------
INSERT INTO `web_info` VALUES (2, '关于我们', 'ABOUTUS', '发发发烦烦烦付付反反复复付fffffffff', '2019-05-29 17:01:47', '2019-05-29 17:01:56');
INSERT INTO `web_info` VALUES (3, '怎么支付', 'HOWTOPAY', '发发发烦烦烦付付反反复复付fffffffff', '2019-05-29 17:07:54', '2019-05-29 17:07:54');
INSERT INTO `web_info` VALUES (5, '怎么支付', 'HOWTOPAY', '发发发烦烦烦付付反反复复付fffffffff', '2019-05-30 11:46:09', '2019-05-30 11:46:09');

SET FOREIGN_KEY_CHECKS = 1;
