/*
 Navicat Premium Data Transfer

 Source Server         : local_judge
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : 127.0.0.1
 Source Database       : goshop_assets

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : utf-8

 Date: 12/15/2017 17:32:50 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `gs_accessory`
-- ----------------------------
DROP TABLE IF EXISTS `gs_accessory`;
CREATE TABLE `gs_accessory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `ext` varchar(255) DEFAULT NULL,
  `height` int(11) NOT NULL,
  `info` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `size` float NOT NULL,
  `width` int(11) NOT NULL,
  `album_id` bigint(20) DEFAULT NULL COMMENT 'gs_album表主键',
  `user_id` bigint(20) DEFAULT NULL,
  `config_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9BF2D7218D8B425` (`config_id`),
  KEY `FK9BF2D72130E5FE9C` (`user_id`),
  KEY `FK9BF2D72155EB9AD8` (`album_id`)
) ENGINE=InnoDB AUTO_INCREMENT=426146 DEFAULT CHARSET=utf8 COMMENT='物品附件';

-- ----------------------------
--  Records of `gs_accessory`
-- ----------------------------
BEGIN;
INSERT INTO `gs_accessory` VALUES ('426079', '2017-11-24 15:15:41', b'0', 'jpg', '1206', null, '8e95c220-3459-4b31-b490-b5512f10c5fb.jpg', 'upload/store/12/2017/11/24', '0.19253', '750', null, '65', null), ('426080', '2017-11-24 16:03:08', b'0', 'jpg', '1206', null, 'aba56163-7c9d-4d81-9a76-4d8c1ce117e0.jpg', 'upload/store/12/2017/11/24', '0.0998096', '750', null, '65', null), ('426081', '2017-11-24 16:10:12', b'0', 'jpg', '1206', null, '03cd28f4-be63-4e7a-8a43-fac9410685b9.jpg', 'upload/store/12/2017/11/24', '0.0998096', '750', null, '65', null), ('426082', '2017-11-24 16:10:44', b'0', 'jpg', '1206', null, 'ddf4909c-a437-4b8d-90b5-1849f17a1fa2.jpg', 'upload/store/12/2017/11/24', '0.0998096', '750', null, '65', null), ('426083', '2017-11-24 16:20:33', b'0', 'jpg', '1206', null, 'e0de41d2-dfe8-40f7-8e44-2ce0db9b3984.jpg', 'upload/store/12/2017/11/24', '0.0998096', '750', null, '65', null), ('426084', '2017-11-24 22:18:29', b'0', 'jpg', '1206', null, 'bbf21945-0132-48cf-9cba-c613aafd3448.jpg', 'upload/store/12/2017/11/24', '0.19253', '750', null, '65', null), ('426085', '2017-11-24 22:19:49', b'0', 'jpg', '1206', null, '3d49cc94-bd85-4c4d-99fa-30b0b19aec11.jpg', 'upload/store/12/2017/11/24', '0.0998096', '750', null, '65', null), ('426086', '2017-11-24 22:23:44', b'0', 'jpg', '1206', null, '5978c185-007a-4f76-9110-289a5d3c58ea.jpg', 'upload/store/12/2017/11/24', '0.0998096', '750', null, '65', null), ('426087', '2017-11-24 22:23:53', b'0', 'jpg', '1206', null, '5ebb95d9-2143-4210-93f3-71872c9bdde1.jpg', 'upload/store/12/2017/11/24', '0.19253', '750', null, '65', null), ('426088', '2017-11-24 22:33:35', b'0', 'jpg', '1206', null, '160588ab-0e3a-43f7-9f76-765ce06ada77.jpg', 'upload/store/12/2017/11/24', '0.0998096', '750', null, '65', null), ('426089', '2017-11-24 22:33:50', b'0', 'jpg', '1206', null, 'a08327e8-f924-4960-abee-b306ab32de20.jpg', 'upload/store/12/2017/11/24', '0.19253', '750', null, '65', null), ('426090', '2017-11-24 22:36:26', b'0', 'jpg', '1206', null, 'e9550a65-5a05-4228-afc0-43ae12a1d870.jpg', 'upload/store/12/2017/11/24', '0.19253', '750', null, '65', null), ('426091', '2017-11-24 22:37:38', b'0', 'jpg', '1206', null, 'f11474dd-1bc3-4305-bd56-a9293bec3bd4.jpg', 'upload/store/12/2017/11/24', '0.19253', '750', null, '65', null), ('426092', '2017-11-24 22:44:23', b'0', 'jpg', '1206', null, '74772a89-cfc9-4d53-a8ea-ee81a8a6bbe8.jpg', 'upload/store/12/2017/11/24', '0.0998096', '750', null, '65', null), ('426093', '2017-11-24 22:44:30', b'0', 'jpg', '1206', null, 'c70433d6-fd65-4e86-9eb7-3661e999fbee.jpg', 'upload/store/12/2017/11/24', '0.19253', '750', null, '65', null), ('426094', '2017-11-24 22:44:53', b'0', 'jpg', '1206', null, '7014cda0-9946-4968-92fb-4da0c593ec6b.jpg', 'upload/store/12/2017/11/24', '0.19253', '750', null, '65', null), ('426095', '2017-11-24 22:45:05', b'0', 'jpg', '1206', null, 'f8c2f59f-889f-4f4b-b32a-959de5cbfbe1.jpg', 'upload/store/12/2017/11/24', '0.19253', '750', null, '65', null), ('426096', '2017-11-24 22:55:17', b'0', 'jpg', '1206', null, '2aea37af-2062-40c6-b24a-df2d9cb5cf63.jpg', 'upload/store/12/2017/11/24', '0.19253', '750', '20', '65', null), ('426097', '2017-11-24 22:55:42', b'0', 'jpg', '1206', null, '677d487f-f9b8-4bc0-82e3-c5ad795597a1.jpg', 'upload/store/12/2017/11/24', '0.19253', '750', '20', '65', null), ('426098', '2017-11-25 00:24:52', b'0', 'jpg', '1206', null, '4f857006-ad6e-41e4-a4fe-5ff575faba5f.jpg', 'upload/store/12/2017/11/25', '0.19253', '750', '20', '65', null), ('426099', '2017-11-25 00:53:11', b'0', 'jpg', '1206', null, '2aee7867-3cbc-4e5d-a290-9b16602f550e.jpg', 'upload/store/12/2017/11/25', '0.19253', '750', '20', '65', null), ('426100', '2017-11-25 00:53:23', b'0', 'jpg', '1206', null, 'fb66bdbe-06e4-4d46-97c2-233fca1fbb10.jpg', 'upload/store/12/2017/11/25', '0.19253', '750', '20', '65', null), ('426101', '2017-11-25 01:00:55', b'0', 'jpg', '1206', null, '3cc28e99-bcd4-40e3-8d02-8b0e100b02e5.jpg', 'upload/store/12/2017/11/25', '0.19253', '750', '20', '65', null), ('426102', '2017-11-25 01:02:34', b'0', 'jpg', '1206', null, '5859fb54-a09c-4901-9403-08e629b0418e.jpg', 'upload/store/12/2017/11/25', '0.19253', '750', '20', '65', null), ('426103', '2017-11-25 01:02:46', b'0', 'jpg', '1206', null, '226eb046-baaa-4d52-99f6-4633e5b9ff79.jpg', 'upload/store/12/2017/11/25', '0.19253', '750', '20', '65', null), ('426104', '2017-11-25 01:13:09', b'0', 'jpg', '1206', null, '599cf357-8999-445b-8e5f-91e8908522dd.jpg', 'upload/store/12/2017/11/25', '0.19253', '750', '20', '65', null), ('426105', '2017-11-25 01:23:28', b'0', 'jpg', '1206', null, '91d43475-a8a6-4a13-9d1f-cd606dfd6227.jpg', 'upload/store/12/2017/11/25', '0.19253', '750', '20', '65', null), ('426106', '2017-11-25 01:27:09', b'0', 'jpg', '1206', null, 'c76a096c-0314-49c1-8374-c4e820fdeee9.jpg', 'upload/store/12/2017/11/25', '0.19253', '750', '20', '65', null), ('426108', '2017-11-25 01:38:18', b'0', 'jpg', '1206', null, '70b8ac55-16f0-4397-a3f2-dfddea7dbd89.jpg', 'upload/store/12/2017/11/25', '0.19253', '750', '20', '65', null), ('426109', '2017-11-25 22:13:09', b'0', 'jpg', '1206', null, '32e31e4f-f7fc-4d0e-8323-efe98f8fc791.jpg', 'upload/store/12/2017/11/25', '0.19253', '750', '20', '65', null), ('426111', '2017-11-25 22:44:24', b'0', 'jpg', '1206', null, '2a87f33d-edce-43a4-9d72-84f992e8e417.jpg', 'upload/store/12/2017/11/25', '0.0998096', '750', '20', '65', null), ('426112', '2017-11-25 22:44:50', b'0', 'jpg', '1206', null, 'a3c5f92c-449d-476f-a609-77c287c15658.jpg', 'upload/store/12/2017/11/25', '0.0998096', '750', '20', '65', null), ('426113', '2017-11-25 22:46:57', b'0', 'jpg', '1206', null, '9068cb04-e4a8-4c87-9a84-c6e99d7bebed.jpg', 'upload/store/12/2017/11/25', '0.19253', '750', '20', '65', null), ('426114', '2017-11-25 22:47:23', b'0', 'jpg', '1206', null, 'cec93884-ac05-46d2-b9f9-baf66717c2a9.jpg', 'upload/store/12/2017/11/25', '0.19253', '750', '20', '65', null), ('426118', '2017-12-06 14:34:12', b'0', 'png', '3', null, '95b7d05b-f890-4bfb-8b34-473d61618b40.png', 'upload/brand', '76', '3', null, null, null), ('426119', '2017-12-06 15:02:36', b'0', 'png', '3', null, 'dc112f76-8909-40aa-9eaf-91e9ff65d429.png', 'upload/brand', '76', '3', null, null, null), ('426120', '2017-12-06 15:06:06', b'0', 'png', '3', null, '266e22f2-3dbf-479b-a939-c6cf1fe66278.png', 'upload/brand', '76', '3', null, null, null), ('426121', '2017-12-06 16:01:10', b'0', 'png', '10', null, 'ca1bf8e0-750c-4ddb-88eb-c61d5b35fa8e.png', 'upload/brand', '187', '4', null, null, null), ('426122', '2017-12-06 18:02:22', b'0', 'png', '8', null, '2de70922-a10d-4c50-997e-2e2212368ad2.png', 'upload/brand', '183', '5', null, null, null), ('426123', '2017-12-06 18:07:57', b'0', 'png', '3', null, 'a2cb194e-db0e-41bd-ac0a-53b454677923.png', 'upload/brand', '76', '3', null, null, null), ('426124', '2017-12-14 22:55:41', b'0', 'png', '14', null, 'ab44915d-8470-42da-a5c0-3c30a9aea7d0.png', 'upload/store/store_join/2017/12/14', '225', '5', null, '46', null), ('426125', '2017-12-14 22:55:41', b'0', 'png', '14', null, 'a71621b8-785d-4a65-bdae-3de2ab5b741d.png', 'upload/store/store_join/2017/12/14', '178', '5', null, '46', null), ('426126', '2017-12-14 22:55:41', b'0', 'png', '8', null, '23889455-9db8-4018-a9c8-c07d68185341.png', 'upload/store/store_join/2017/12/14', '222', '5', null, '46', null), ('426127', '2017-12-14 22:57:06', b'0', 'png', '8', null, 'd79020a2-8386-45ce-8cd6-9aac725f9c4b.png', 'upload/store/store_join/2017/12/14', '225', '5', null, '46', null), ('426128', '2017-12-14 22:57:06', b'0', 'png', '8', null, '0e890781-da58-4c54-a46d-f5d4cf279008.png', 'upload/store/store_join/2017/12/14', '225', '5', null, '46', null), ('426129', '2017-12-14 23:01:02', b'0', 'png', '8', null, '18f17156-8003-43c7-9eeb-9f52fb6c68fa.png', 'upload/store/store_join/2017/12/14', '225', '5', null, '46', null), ('426130', '2017-12-14 23:01:02', b'0', 'png', '8', null, '8d9c4db1-968e-4a01-8909-ac662357b30f.png', 'upload/store/store_join/2017/12/14', '225', '5', null, '46', null), ('426131', '2017-12-14 23:18:54', b'0', 'png', '14', null, 'e88c1913-ef8c-4909-b031-454c405dcc72.png', 'upload/store/store_join/2017/12/14', '225', '5', null, '46', null), ('426132', '2017-12-14 23:18:54', b'0', 'png', '8', null, '4d1d916a-dae3-4203-9553-96f059b42f27.png', 'upload/store/store_join/2017/12/14', '225', '5', null, '46', null), ('426133', '2017-12-14 23:18:54', b'0', 'png', '8', null, '16cd955b-819e-4cf1-bc2b-a555a5acf8fc.png', 'upload/store/store_join/2017/12/14', '183', '5', null, '46', null), ('426134', '2017-12-14 23:19:38', b'0', 'png', '8', null, 'a940e761-4b93-454e-9076-f0efa42b357b.png', 'upload/store/store_join/2017/12/14', '222', '5', null, '46', null), ('426135', '2017-12-14 23:19:38', b'0', 'png', '14', null, '2339c444-1223-4b63-894e-f0b4b1f5e625.png', 'upload/store/store_join/2017/12/14', '178', '5', null, '46', null), ('426136', '2017-12-14 23:24:40', b'0', 'png', '8', null, 'd0d782f7-f390-4479-99bb-04bd74c878f7.png', 'upload/store/store_join/2017/12/14', '222', '5', null, '46', null), ('426137', '2017-12-14 23:24:40', b'0', 'png', '14', null, '0421442e-1cc3-4eef-ba3e-97dc6db19594.png', 'upload/store/store_join/2017/12/14', '178', '5', null, '46', null), ('426138', '2017-12-14 23:44:10', b'0', 'png', '14', null, 'a29e1480-086b-481c-a6d5-0ca4a4a85359.png', 'upload/store/store_join/2017/12/14', '225', '5', null, '46', null), ('426139', '2017-12-14 23:44:10', b'0', 'png', '8', null, '1c5e327e-6133-44fc-9624-80e5c8fc7f0c.png', 'upload/store/store_join/2017/12/14', '183', '5', null, '46', null), ('426140', '2017-12-14 23:44:10', b'0', 'png', '14', null, '7295fcc2-0d63-4a71-a689-0b55216aa150.png', 'upload/store/store_join/2017/12/14', '225', '5', null, '46', null), ('426141', '2017-12-14 23:44:36', b'0', 'png', '14', null, '455aa272-71bd-420b-8de2-00ef16963c58.png', 'upload/store/store_join/2017/12/14', '225', '5', null, '46', null), ('426142', '2017-12-14 23:44:36', b'0', 'png', '8', null, 'ff09e58e-51a0-4fdf-801e-d024f770726c.png', 'upload/store/store_join/2017/12/14', '222', '5', null, '46', null), ('426143', '2017-12-15 01:04:09', b'0', 'png', '14', null, '763d46d5-2051-4fb4-8baf-35e41886ee54.png', 'upload/store_logo', '225', '5', null, null, null), ('426144', '2017-12-15 01:31:32', b'0', 'png', '14', null, 'be5287b7-b9cc-4bac-bfcb-c8f6b4629c02.png', 'upload/store/12/2017/12/15', '178', '5', '20', '65', null), ('426145', '2017-12-15 01:39:22', b'0', 'png', '8', null, '1bfbf073-1181-4639-8be9-224c210dfe8a.png', 'upload/store/12/2017/12/15', '225', '5', '20', '65', null);
COMMIT;

-- ----------------------------
--  Table structure for `gs_album`
-- ----------------------------
DROP TABLE IF EXISTS `gs_album`;
CREATE TABLE `gs_album` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `alblum_info` longtext,
  `album_default` bit(1) NOT NULL,
  `album_name` varchar(255) DEFAULT NULL,
  `album_sequence` int(11) NOT NULL,
  `album_cover_id` bigint(20) DEFAULT NULL COMMENT 'gs_accessory表主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT 'gs_user表主键',
  PRIMARY KEY (`id`),
  KEY `FK2FF965FE537B6C51` (`user_id`),
  KEY `FK2FF965FE58AB9D6E` (`album_cover_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `gs_album`
-- ----------------------------
BEGIN;
INSERT INTO `gs_album` VALUES ('20', '2017-11-24 22:44:53', b'0', null, b'1', '默认相册', '-10000', null, '65');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
