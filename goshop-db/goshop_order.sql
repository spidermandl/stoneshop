/*
 Navicat Premium Data Transfer

 Source Server         : local_judge
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : 127.0.0.1
 Source Database       : goshop_order

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : utf-8

 Date: 01/05/2018 02:20:39 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `gs_goods_cart`
-- ----------------------------
DROP TABLE IF EXISTS `gs_goods_cart`;
CREATE TABLE `gs_goods_cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `count` int(11) NOT NULL,
  `price` decimal(12,2) DEFAULT NULL,
  `spec_info` longtext,
  `goods_id` bigint(20) DEFAULT NULL,
  `of_id` bigint(20) DEFAULT NULL,
  `cart_type` varchar(255) DEFAULT NULL,
  `sc_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6D29FF85797D9D78` (`goods_id`),
  KEY `FK6D29FF85B3E31589` (`sc_id`),
  KEY `FK6D29FF85377D3633` (`of_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `gs_store_cart`
-- ----------------------------
DROP TABLE IF EXISTS `gs_store_cart`;
CREATE TABLE `gs_store_cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `cart_session_id` varchar(255) DEFAULT NULL,
  `total_price` decimal(19,2) DEFAULT NULL,
  `store_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `sc_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK7EE3A390537B6C51` (`user_id`),
  KEY `FK7EE3A390920D7683` (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
