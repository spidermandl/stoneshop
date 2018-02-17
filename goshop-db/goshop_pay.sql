/*
 Navicat Premium Data Transfer

 Source Server         : local_judge
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : 127.0.0.1
 Source Database       : goshop_pay

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : utf-8

 Date: 02/16/2018 14:33:58 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `gs_gold_log`
-- ----------------------------
DROP TABLE IF EXISTS `gs_gold_log`;
CREATE TABLE `gs_gold_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `gl_admin_content` longtext,
  `gl_admin_time` datetime DEFAULT NULL,
  `gl_content` longtext,
  `gl_count` int(11) NOT NULL,
  `gl_money` int(11) NOT NULL,
  `gl_payment` varchar(255) DEFAULT NULL,
  `gl_type` int(11) NOT NULL,
  `gl_admin_id` bigint(20) DEFAULT NULL COMMENT 'gs_user表主键',
  `gl_user_id` bigint(20) DEFAULT NULL COMMENT 'gs_user表主键',
  `gr_id` bigint(20) DEFAULT NULL COMMENT 'gs_gold_record表主键',
  PRIMARY KEY (`id`),
  KEY `FKEDD9DCF6FDCC66F7` (`gr_id`),
  KEY `FKEDD9DCF61EE1CC67` (`gl_admin_id`),
  KEY `FKEDD9DCF6B6766AF7` (`gl_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `gs_gold_record`
-- ----------------------------
DROP TABLE IF EXISTS `gs_gold_record`;
CREATE TABLE `gs_gold_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `gold_admin_info` longtext,
  `gold_admin_time` datetime DEFAULT NULL,
  `gold_count` int(11) NOT NULL,
  `gold_exchange_info` longtext,
  `gold_money` int(11) NOT NULL,
  `gold_pay_status` int(11) NOT NULL,
  `gold_payment` varchar(255) DEFAULT NULL,
  `gold_sn` varchar(255) DEFAULT NULL,
  `gold_status` int(11) NOT NULL,
  `gold_admin_id` bigint(20) DEFAULT NULL COMMENT 'gs_user表主键',
  `gold_user_id` bigint(20) DEFAULT NULL COMMENT 'gs_user表主键',
  PRIMARY KEY (`id`),
  KEY `FK5A87D5FB1CC134C` (`gold_admin_id`),
  KEY `FK5A87D5F81653372` (`gold_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `gs_payment`
-- ----------------------------
DROP TABLE IF EXISTS `gs_payment`;
CREATE TABLE `gs_payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `chinabank_account` varchar(255) DEFAULT NULL,
  `chinabank_key` varchar(255) DEFAULT NULL,
  `content` longtext,
  `install` bit(1) NOT NULL,
  `interfaceType` int(11) NOT NULL,
  `mark` varchar(255) DEFAULT NULL,
  `merchantAcctId` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `partner` varchar(255) DEFAULT NULL,
  `pid` varchar(255) DEFAULT NULL,
  `rmbKey` varchar(255) DEFAULT NULL,
  `safeKey` varchar(255) DEFAULT NULL,
  `seller_email` varchar(255) DEFAULT NULL,
  `spname` varchar(255) DEFAULT NULL,
  `tenpay_key` varchar(255) DEFAULT NULL,
  `tenpay_partner` varchar(255) DEFAULT NULL,
  `trade_mode` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `store_id` bigint(20) DEFAULT NULL COMMENT 'gs_store表主键',
  `alipay_divide_rate` decimal(12,2) DEFAULT NULL,
  `alipay_rate` decimal(12,2) DEFAULT NULL,
  `balance_divide_rate` decimal(12,2) DEFAULT NULL,
  `currency_code` varchar(255) DEFAULT NULL,
  `paypal_userId` varchar(255) DEFAULT NULL,
  `poundage` decimal(12,2) DEFAULT NULL,
  `lzbank_key` varchar(255) DEFAULT NULL,
  `lzbank_partner` varchar(255) DEFAULT NULL,
  `lzbank_trade_mode` varchar(255) DEFAULT NULL,
  `weixin_appId` longtext,
  `weixin_appSecret` longtext,
  `weixin_partnerId` longtext,
  `weixin_partnerKey` longtext,
  `weixin_paySignKey` longtext,
  PRIMARY KEY (`id`),
  KEY `FK1F3071D5920D7683` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `gs_payment`
-- ----------------------------
BEGIN;
INSERT INTO `gs_payment` VALUES ('1', '2017-11-15 23:41:15', b'0', null, null, '', b'1', '0', 'payafter', null, null, null, null, null, null, null, null, null, null, '0', 'user', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null), ('2', '2017-11-19 13:50:52', b'0', null, null, '', b'1', '0', 'payafter', null, null, null, null, null, null, null, null, null, null, '0', 'user', '2', null, null, null, null, null, null, null, null, null, null, null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `gs_predeposit`
-- ----------------------------
DROP TABLE IF EXISTS `gs_predeposit`;
CREATE TABLE `gs_predeposit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `pd_admin_info` longtext,
  `pd_amount` decimal(12,2) DEFAULT NULL,
  `pd_pay_status` int(11) NOT NULL,
  `pd_payment` varchar(255) DEFAULT NULL,
  `pd_remittance_bank` varchar(255) DEFAULT NULL,
  `pd_remittance_info` longtext,
  `pd_remittance_time` date DEFAULT NULL,
  `pd_remittance_user` varchar(255) DEFAULT NULL,
  `pd_sn` varchar(255) DEFAULT NULL,
  `pd_status` int(11) NOT NULL,
  `pd_admin_id` bigint(20) DEFAULT NULL COMMENT 'gs_user表主键',
  `pd_user_id` bigint(20) DEFAULT NULL COMMENT 'gs_user表主键',
  PRIMARY KEY (`id`),
  KEY `FK6586306CB8A08C38` (`pd_admin_id`),
  KEY `FK6586306CAAE7ED06` (`pd_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `gs_predeposit_cash`
-- ----------------------------
DROP TABLE IF EXISTS `gs_predeposit_cash`;
CREATE TABLE `gs_predeposit_cash` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `cash_account` varchar(255) DEFAULT NULL,
  `cash_admin_info` longtext,
  `cash_amount` decimal(12,2) DEFAULT NULL,
  `cash_bank` varchar(255) DEFAULT NULL,
  `cash_info` longtext,
  `cash_pay_status` int(11) NOT NULL,
  `cash_payment` varchar(255) DEFAULT NULL,
  `cash_sn` varchar(255) DEFAULT NULL,
  `cash_status` int(11) NOT NULL,
  `cash_userName` varchar(255) DEFAULT NULL,
  `cash_admin_id` bigint(20) DEFAULT NULL COMMENT 'gs_user表主键',
  `cash_user_id` bigint(20) DEFAULT NULL COMMENT 'gs_user表主键',
  PRIMARY KEY (`id`),
  KEY `FKC48C67263D30AB45` (`cash_user_id`),
  KEY `FKC48C67266F6F95D9` (`cash_admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `gs_predeposit_log`
-- ----------------------------
DROP TABLE IF EXISTS `gs_predeposit_log`;
CREATE TABLE `gs_predeposit_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `pd_log_amount` decimal(12,2) DEFAULT NULL,
  `pd_log_info` longtext,
  `pd_op_type` varchar(255) DEFAULT NULL,
  `pd_type` varchar(255) DEFAULT NULL,
  `pd_log_admin_id` bigint(20) DEFAULT NULL COMMENT 'gs_user表主键',
  `pd_log_user_id` bigint(20) DEFAULT NULL COMMENT 'gs_user表主键',
  `predeposit_id` bigint(20) DEFAULT NULL COMMENT 'gs_predeposit表主键',
  PRIMARY KEY (`id`),
  KEY `FKDD0C74D11B0A508B` (`pd_log_user_id`),
  KEY `FKDD0C74D1321DC511` (`predeposit_id`),
  KEY `FKDD0C74D14CCA9953` (`pd_log_admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `gs_refund_log`
-- ----------------------------
DROP TABLE IF EXISTS `gs_refund_log`;
CREATE TABLE `gs_refund_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `refund` decimal(12,2) DEFAULT NULL,
  `refund_id` varchar(255) DEFAULT NULL,
  `refund_log` varchar(255) DEFAULT NULL,
  `refund_type` varchar(255) DEFAULT NULL,
  `of_id` bigint(20) DEFAULT NULL COMMENT 'gs_orderform表主键',
  `refund_user_id` bigint(20) DEFAULT NULL COMMENT 'gs_user表主键',
  PRIMARY KEY (`id`),
  KEY `FKC075AFCE8E17FA8A` (`refund_user_id`),
  KEY `FKC075AFCE9F21119E` (`of_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
