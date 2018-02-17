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

 Date: 02/16/2018 14:32:56 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `gs_address`
-- ----------------------------
DROP TABLE IF EXISTS `gs_address`;
CREATE TABLE `gs_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `area_info` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `trueName` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  `area_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9ABDBC32FB91D11` (`area_id`),
  KEY `FK9ABDBC3537B6C51` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `gs_cart_gsp`
-- ----------------------------
DROP TABLE IF EXISTS `gs_cart_gsp`;
CREATE TABLE `gs_cart_gsp` (
  `cart_id` bigint(20) NOT NULL COMMENT 'gs_goods_cart表主键',
  `gsp_id` bigint(20) NOT NULL COMMENT 'gs_goods_spec_property表主键',
  KEY `FK74DC65762455EE19` (`cart_id`),
  KEY `FK74DC657626F16245` (`gsp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车与商品规格属性关联';

-- ----------------------------
--  Table structure for `gs_coupon`
-- ----------------------------
DROP TABLE IF EXISTS `gs_coupon`;
CREATE TABLE `gs_coupon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `coupon_amount` decimal(12,2) DEFAULT NULL,
  `coupon_begin_time` date DEFAULT NULL,
  `coupon_count` int(11) NOT NULL,
  `coupon_end_time` date DEFAULT NULL,
  `coupon_name` varchar(255) DEFAULT NULL,
  `coupon_order_amount` decimal(12,2) DEFAULT NULL,
  `coupon_acc_id` bigint(20) DEFAULT NULL COMMENT 'gs_accessory表主键',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠券';

-- ----------------------------
--  Table structure for `gs_coupon_info`
-- ----------------------------
DROP TABLE IF EXISTS `gs_coupon_info`;
CREATE TABLE `gs_coupon_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `coupon_sn` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT '0' COMMENT '优惠券状态',
  `coupon_id` bigint(20) DEFAULT NULL COMMENT 'gs_coupon表主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT 'gs_user表主键',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKE4A2D7682F288B1` (`coupon_id`),
  KEY `FKE4A2D76537B6C51` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠券信息';

-- ----------------------------
--  Table structure for `gs_express_company`
-- ----------------------------
DROP TABLE IF EXISTS `gs_express_company`;
CREATE TABLE `gs_express_company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `company_mark` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `company_status` int(11) DEFAULT '0',
  `company_sequence` int(11) DEFAULT '0',
  `company_type` varchar(255) DEFAULT 'EXPRESS',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `gs_express_company`
-- ----------------------------
BEGIN;
INSERT INTO `gs_express_company` VALUES ('1', '2014-08-12 11:49:16', b'0', 'shunfeng', '顺丰速递', '0', '0', 'EXPRESS'), ('2', '2014-08-12 11:51:18', b'0', 'shentong', '申通', '0', '1', 'EXPRESS'), ('3', '2014-08-12 11:52:55', b'0', 'tiantian', '天天快递', '0', '2', 'EXPRESS'), ('4', '2014-08-12 11:53:27', b'0', 'youzhengguonei', '包裹/平邮/挂号信', '0', '4', 'POST'), ('6', '2014-08-12 11:55:31', b'0', 'bangsongwuliu', '邦送物流', '0', '5', 'EXPRESS'), ('7', '2014-08-12 12:01:12', b'0', 'cces', '希伊艾斯（CCES）', '0', '6', 'EXPRESS'), ('8', '2014-08-12 12:01:37', b'0', 'coe', '中国东方（COE）', '0', '7', 'EXPRESS'), ('9', '2014-08-12 12:02:10', b'0', 'city100', '城市100', '0', '8', 'EXPRESS'), ('10', '2014-08-12 12:02:27', b'0', 'chuanxiwuliu', '传喜物流', '0', '9', 'EXPRESS'), ('11', '2014-08-12 12:02:46', b'0', 'datianwuliu', '大田物流', '0', '10', 'EXPRESS'), ('12', '2014-08-12 12:03:02', b'0', 'debangwuliu', '德邦物流', '0', '11', 'EXPRESS'), ('13', '2014-08-12 12:03:19', b'0', 'dsukuaidi', 'D速快递', '0', '12', 'EXPRESS'), ('14', '2014-08-12 12:03:37', b'0', 'disifang', '递四方', '0', '13', 'EXPRESS'), ('15', '2014-08-12 12:04:02', b'0', 'ems', 'EMS', '0', '14', 'EMS'), ('16', '2014-08-12 12:04:20', b'0', 'feikangda', '飞康达物流', '0', '15', 'EXPRESS'), ('17', '2014-08-12 12:04:36', b'0', 'feikuaida', '飞快达', '0', '16', 'EXPRESS'), ('18', '2014-08-12 12:04:53', b'0', 'rufengda', '凡客如风达', '0', '17', 'EXPRESS'), ('19', '2014-08-12 12:05:12', b'0', 'fengxingtianxia', '风行天下', '0', '18', 'EXPRESS'), ('20', '2014-08-12 12:05:26', b'0', 'feibaokuaidi', '飞豹快递', '0', '19', 'EXPRESS'), ('21', '2014-08-12 12:05:59', b'0', 'ganzhongnengda', '港中能达', '0', '20', 'EXPRESS'), ('22', '2014-08-12 12:06:21', b'0', 'guotongkuaidi', '国通快递', '0', '21', 'EXPRESS'), ('23', '2014-08-12 12:06:43', b'0', 'guangdongyouzhengwuliu', '广东邮政', '0', '22', 'EXPRESS'), ('24', '2014-08-12 12:07:07', b'0', 'gls', 'GLS', '0', '23', 'EXPRESS'), ('25', '2014-08-12 12:07:33', b'0', 'gongsuda', '共速达', '0', '24', 'EXPRESS'), ('26', '2014-08-12 12:08:05', b'0', 'huitongkuaidi', '汇通快运', '0', '25', 'EXPRESS'), ('27', '2014-08-12 12:08:31', b'0', 'huiqiangkuaidi', '汇强快递', '0', '26', 'EXPRESS'), ('28', '2014-08-12 12:08:51', b'0', 'tiandihuayu', '华宇物流', '0', '27', 'EXPRESS');
COMMIT;

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
  `goods_id` bigint(20) DEFAULT NULL COMMENT 'gs_goods表主键',
  `of_id` bigint(20) DEFAULT NULL COMMENT 'gs_orderform表主键',
  `cart_type` varchar(255) DEFAULT NULL,
  `sc_id` bigint(20) DEFAULT NULL COMMENT 'gs_store_cart表主键',
  PRIMARY KEY (`id`),
  KEY `FK6D29FF85797D9D78` (`goods_id`),
  KEY `FK6D29FF85B3E31589` (`sc_id`),
  KEY `FK6D29FF85377D3633` (`of_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `gs_integral_goods`
-- ----------------------------
DROP TABLE IF EXISTS `gs_integral_goods`;
CREATE TABLE `gs_integral_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `ig_begin_time` datetime DEFAULT NULL,
  `ig_click_count` int(11) NOT NULL,
  `ig_content` varchar(255) DEFAULT NULL,
  `ig_end_time` datetime DEFAULT NULL,
  `ig_exchange_count` int(11) NOT NULL,
  `ig_goods_count` int(11) NOT NULL,
  `ig_goods_integral` int(11) NOT NULL,
  `ig_goods_name` varchar(255) DEFAULT NULL,
  `ig_goods_price` decimal(12,2) DEFAULT NULL,
  `ig_goods_sn` varchar(255) DEFAULT NULL,
  `ig_goods_tag` varchar(255) DEFAULT NULL,
  `ig_limit_count` int(11) NOT NULL,
  `ig_limit_type` bit(1) NOT NULL,
  `ig_recommend` bit(1) NOT NULL,
  `ig_seo_description` varchar(255) DEFAULT NULL,
  `ig_seo_keywords` varchar(255) DEFAULT NULL,
  `ig_sequence` int(11) NOT NULL,
  `ig_show` bit(1) NOT NULL,
  `ig_time_type` bit(1) NOT NULL,
  `ig_transfee` decimal(12,2) DEFAULT NULL,
  `ig_transfee_type` int(11) NOT NULL,
  `ig_goods_img_id` bigint(20) DEFAULT NULL COMMENT 'gs_accessory表主键',
  PRIMARY KEY (`id`),
  KEY `FKDCFD08548B82A931` (`ig_goods_img_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `gs_integral_goodscart`
-- ----------------------------
DROP TABLE IF EXISTS `gs_integral_goodscart`;
CREATE TABLE `gs_integral_goodscart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `count` int(11) NOT NULL,
  `integral` int(11) NOT NULL,
  `trans_fee` decimal(12,2) DEFAULT NULL,
  `goods_id` bigint(20) DEFAULT NULL COMMENT 'gs_integral_goods表主键',
  `order_id` bigint(20) DEFAULT NULL COMMENT 'gs_integral_goodsorder表主键',
  PRIMARY KEY (`id`),
  KEY `FK8C073974B96C5317` (`goods_id`),
  KEY `FK8C07397494B72647` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `gs_integral_goodsorder`
-- ----------------------------
DROP TABLE IF EXISTS `gs_integral_goodsorder`;
CREATE TABLE `gs_integral_goodsorder` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `igo_msg` longtext,
  `igo_order_sn` varchar(255) DEFAULT NULL,
  `igo_pay_msg` longtext,
  `igo_pay_time` datetime DEFAULT NULL,
  `igo_payment` varchar(255) DEFAULT NULL,
  `igo_ship_code` varchar(255) DEFAULT NULL,
  `igo_ship_content` longtext,
  `igo_ship_time` date DEFAULT NULL,
  `igo_status` int(11) NOT NULL,
  `igo_total_integral` int(11) NOT NULL,
  `igo_trans_fee` decimal(12,2) DEFAULT NULL,
  `igo_addr_id` bigint(20) DEFAULT NULL COMMENT 'gs_address表主键',
  `igo_user_id` bigint(20) DEFAULT NULL COMMENT 'gs_user表主键',
  PRIMARY KEY (`id`),
  KEY `FKF590937A26B00318` (`igo_addr_id`),
  KEY `FKF590937A85110923` (`igo_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `gs_integrallog`
-- ----------------------------
DROP TABLE IF EXISTS `gs_integrallog`;
CREATE TABLE `gs_integrallog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `content` longtext,
  `integral` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `integral_user_id` bigint(20) DEFAULT NULL COMMENT 'gs_user表主键',
  `operate_user_id` bigint(20) DEFAULT NULL COMMENT 'gs_user表主键',
  PRIMARY KEY (`id`),
  KEY `FKEC2A9E67F65B7CBE` (`integral_user_id`),
  KEY `FKEC2A9E67C8F25896` (`operate_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `gs_integrallog`
-- ----------------------------
BEGIN;
INSERT INTO `gs_integrallog` VALUES ('1', '2017-11-15 21:45:17', b'0', '用户注册增加0分', '0', 'reg', '32776', null), ('2', '2017-11-15 21:45:17', b'0', '用户2017-11-15 21:45:17登录增加0分', '0', 'login', '32776', null), ('3', '2017-11-16 00:16:01', b'0', '用户注册增加0分', '0', 'reg', '32777', null), ('4', '2017-11-16 00:16:01', b'0', '用户2017-11-16 00:16:01登录增加0分', '0', 'login', '32777', null), ('5', '2017-11-19 13:49:20', b'0', '用户2017-11-19 13:49:20登录增加0分', '0', 'login', '32777', null), ('6', '2017-11-23 21:30:59', b'0', '用户2017-11-23 21:30:59登录增加0分', '0', 'login', '32777', null), ('7', '2017-11-24 22:54:00', b'0', '用户2017-11-24 22:54:00登录增加0分', '0', 'login', '32777', null), ('8', '2017-11-25 00:36:03', b'0', '用户2017-11-25 00:36:03登录增加0分', '0', 'login', '32777', null), ('9', '2017-11-27 11:15:56', b'0', '用户2017-11-27 11:15:56登录增加0分', '0', 'login', '32777', null), ('10', '2017-11-28 02:34:48', b'0', '用户2017-11-28 02:34:48登录增加0分', '0', 'login', '32777', null), ('11', '2017-11-29 13:15:22', b'0', '用户2017-11-29 13:15:22登录增加0分', '0', 'login', '32777', null), ('12', '2017-11-30 00:40:43', b'0', '用户2017-11-30 00:40:43登录增加0分', '0', 'login', '32777', null), ('13', '2017-11-30 18:06:45', b'0', '用户2017-11-30 18:06:45登录增加0分', '0', 'login', '32776', null), ('14', '2017-12-01 00:41:06', b'0', '用户2017-12-01 00:41:06登录增加0分', '0', 'login', '32777', null), ('15', '2017-12-01 15:21:39', b'0', '用户2017-12-01 15:21:39登录增加0分', '0', 'login', '1', null), ('16', '2017-12-01 15:37:56', b'0', '用户2017-12-01 15:37:56登录增加0分', '0', 'login', '32776', null), ('17', '2017-12-01 15:39:30', b'0', '用户2017-12-01 15:39:30登录增加0分', '0', 'login', '32775', null), ('18', '2017-12-02 13:21:46', b'0', '用户2017-12-02 13:21:46登录增加0分', '0', 'login', '32777', null), ('19', '2017-12-03 12:49:19', b'0', '用户2017-12-03 12:49:19登录增加0分', '0', 'login', '32777', null), ('20', '2017-12-04 00:11:55', b'0', '用户2017-12-04 00:11:55登录增加0分', '0', 'login', '32777', null), ('21', '2017-12-05 01:36:22', b'0', '用户2017-12-05 01:36:22登录增加0分', '0', 'login', '32777', null), ('22', '2017-12-06 02:21:18', b'0', '用户2017-12-06 02:21:18登录增加0分', '0', 'login', '32777', null), ('23', '2017-12-07 02:26:00', b'0', '用户2017-12-07 02:26:00登录增加0分', '0', 'login', '32777', null), ('24', '2017-12-08 13:47:39', b'0', '用户2017-12-08 13:47:39登录增加0分', '0', 'login', '32777', null), ('25', '2017-12-09 20:39:29', b'0', '用户2017-12-09 20:39:29登录增加0分', '0', 'login', '32777', null), ('26', '2017-12-12 18:25:48', b'0', '用户2017-12-12 18:25:48登录增加0分', '0', 'login', '32777', null), ('27', '2017-12-12 20:36:12', b'0', '用户2017-12-12 20:36:12登录增加0分', '0', 'login', '32775', null), ('28', '2017-12-13 17:46:54', b'0', '用户2017-12-13 17:46:54登录增加0分', '0', 'login', '32777', null), ('29', '2017-12-14 01:41:17', b'0', '用户2017-12-14 01:41:17登录增加0分', '0', 'login', '32777', null), ('30', '2017-12-15 23:46:05', b'0', '用户2017-12-15 23:46:05登录增加0分', '0', 'login', '32777', null), ('31', '2017-12-16 11:37:53', b'0', '用户2017-12-16 11:37:53登录增加0分', '0', 'login', '32777', null), ('32', '2017-12-19 20:24:58', b'0', '用户2017-12-19 20:24:58登录增加0分', '0', 'login', '32777', null), ('33', '2017-12-19 21:03:44', b'0', '用户2017-12-19 21:03:44登录增加0分', '0', 'login', '32775', null), ('34', '2017-12-20 13:27:23', b'0', '用户2017-12-20 13:27:23登录增加0分', '0', 'login', '32777', null), ('35', '2017-12-26 15:18:20', b'0', '用户2017-12-26 15:18:20登录增加0分', '0', 'login', '32777', null), ('36', '2017-12-27 01:48:53', b'0', '用户2017-12-27 01:48:53登录增加0分', '0', 'login', '32777', null), ('37', '2017-12-28 02:01:32', b'0', '用户2017-12-28 02:01:32登录增加0分', '0', 'login', '32777', null), ('38', '2018-01-09 23:06:39', b'0', '用户2018-01-09 23:06:39登录增加0分', '0', 'login', '32777', null), ('39', '2018-01-16 15:45:23', b'0', '用户2018-01-16 15:45:23登录增加0分', '0', 'login', '32777', null), ('40', '2018-01-17 15:44:55', b'0', '用户2018-01-17 15:44:55登录增加0分', '0', 'login', '32777', null), ('41', '2018-01-18 02:42:44', b'0', '用户2018-01-18 02:42:44登录增加0分', '0', 'login', '32777', null), ('42', '2018-01-31 01:38:26', b'0', '用户2018-01-31 01:38:26登录增加0分', '0', 'login', '32777', null), ('43', '2018-02-05 18:39:52', b'0', '用户2018-02-05 18:39:52登录增加0分', '0', 'login', '32777', null), ('44', '2018-02-05 18:42:37', b'0', '用户2018-02-05 18:42:37登录增加0分', '0', 'login', '32775', null);
COMMIT;

-- ----------------------------
--  Table structure for `gs_order_log`
-- ----------------------------
DROP TABLE IF EXISTS `gs_order_log`;
CREATE TABLE `gs_order_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `log_info` varchar(255) DEFAULT NULL,
  `state_info` longtext,
  `log_user_id` bigint(20) DEFAULT NULL COMMENT 'gs_user表主键',
  `of_id` bigint(20) DEFAULT NULL COMMENT 'gs_orderform表主键',
  PRIMARY KEY (`id`),
  KEY `FK2B18AE243E73256` (`log_user_id`),
  KEY `FK2B18AE29F21119E` (`of_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `gs_orderform`
-- ----------------------------
DROP TABLE IF EXISTS `gs_orderform`;
CREATE TABLE `gs_orderform` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `finishTime` datetime DEFAULT NULL,
  `goods_amount` decimal(12,2) DEFAULT NULL,
  `invoice` varchar(255) DEFAULT NULL,
  `invoiceType` int(11) NOT NULL,
  `msg` longtext,
  `order_id` varchar(255) DEFAULT NULL,
  `order_status` int(11) NOT NULL,
  `payTime` datetime DEFAULT NULL,
  `pay_msg` longtext,
  `refund` decimal(12,2) DEFAULT NULL,
  `refund_type` varchar(255) DEFAULT NULL,
  `shipCode` varchar(255) DEFAULT NULL,
  `shipTime` datetime DEFAULT NULL,
  `ship_price` decimal(12,2) DEFAULT NULL,
  `totalPrice` decimal(12,2) DEFAULT NULL,
  `addr_id` bigint(20) DEFAULT NULL COMMENT 'gs_address表主键',
  `payment_id` bigint(20) DEFAULT NULL,
  `store_id` bigint(20) DEFAULT NULL COMMENT 'gs_store表主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT 'gs_user表主键',
  `auto_confirm_email` bit(1) DEFAULT b'0',
  `auto_confirm_sms` bit(1) DEFAULT b'0',
  `transport` varchar(255) DEFAULT NULL,
  `out_order_id` varchar(255) DEFAULT NULL,
  `ec_id` bigint(20) DEFAULT NULL COMMENT 'gs_express_company表主键',
  `ci_id` bigint(20) DEFAULT NULL,
  `order_seller_intro` longtext,
  `return_shipCode` varchar(255) DEFAULT NULL,
  `return_ec_id` bigint(20) DEFAULT NULL COMMENT 'gs_express_company表主键',
  `return_content` longtext,
  `return_shipTime` datetime DEFAULT NULL,
  `order_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2B4C521240077F8` (`payment_id`),
  KEY `FK2B4C521F3377CCA` (`ci_id`),
  KEY `FK2B4C521D3DE598B` (`ec_id`),
  KEY `FK2B4C52130E5FE9C` (`user_id`),
  KEY `FK2B4C521131255BC` (`return_ec_id`),
  KEY `FK2B4C52169F0AC1B` (`addr_id`),
  KEY `FK2B4C52161F52D98` (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
