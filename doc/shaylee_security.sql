/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : shaylee_security

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 14/03/2020 19:32:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据ID',
  `no` varchar(8) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '会员号唯一标识',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '会员类型(1普通)',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `area_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '手机区域编号',
  `phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '手机号码',
  `gender` tinyint(1) NULL DEFAULT NULL COMMENT '性别(1男 2女)',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(3) NULL DEFAULT NULL COMMENT '创建日期',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(3) NULL DEFAULT NULL COMMENT '更新日期',
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标志(0正常 1删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_no`(`no`) USING BTREE,
  UNIQUE INDEX `uk_email`(`email`) USING BTREE,
  UNIQUE INDEX `uk_phone`(`area_code`, `phone`) USING BTREE,
  INDEX `idx_create_by`(`create_by`) USING BTREE,
  INDEX `idx_create_date`(`create_date`) USING BTREE,
  INDEX `idx_update_by`(`update_by`) USING BTREE,
  INDEX `idx_update_date`(`update_date`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '会员信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for member_extend
-- ----------------------------
DROP TABLE IF EXISTS `member_extend`;
CREATE TABLE `member_extend`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据ID',
  `member_id` int(11) NULL DEFAULT NULL COMMENT '会员ID',
  `country_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '国家ID',
  `province_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '省份/州ID',
  `city_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '城市ID',
  `birthday` datetime(3) NULL DEFAULT NULL COMMENT '出生日期',
  `height` int(8) NULL DEFAULT NULL COMMENT '身高(cm)',
  `weight` int(4) NULL DEFAULT NULL COMMENT '体重(kg)',
  `introduction` varchar(512) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '自我介绍',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(3) NULL DEFAULT NULL COMMENT '更新日期',
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标志(0正常 1删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_member_id`(`member_id`) USING BTREE,
  INDEX `idx_city_id`(`city_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '会员信息扩张表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for member_login_log
-- ----------------------------
DROP TABLE IF EXISTS `member_login_log`;
CREATE TABLE `member_login_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据ID',
  `member_id` int(11) NULL DEFAULT NULL COMMENT '会员ID',
  `member_no` varchar(8) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '会员编号',
  `area_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '手机区域编号',
  `phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `login_type` int(11) NULL DEFAULT NULL COMMENT '登录类型枚举',
  `login_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '登录名',
  `operation` tinyint(4) NULL DEFAULT NULL COMMENT '用户操作(0登录 1退出)',
  `operate_ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户操作IP',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态(0失败 1成功 2账号锁定)',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(3) NULL DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_member_id`(`member_id`) USING BTREE,
  INDEX `idx_member_no`(`member_no`) USING BTREE,
  INDEX `idx_create_date`(`create_date`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '会员登录日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for member_security
-- ----------------------------
DROP TABLE IF EXISTS `member_security`;
CREATE TABLE `member_security`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据ID',
  `member_id` int(11) NULL DEFAULT NULL COMMENT '会员ID',
  `member_no` varchar(8) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '会员号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '盐值',
  `area_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '手机区域编号',
  `phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '账号状态(1发布 2编辑 3冻结 4删除)',
  `abnormal` tinyint(4) NULL DEFAULT NULL COMMENT '异常状态(1正常 2异常)',
  `online` tinyint(1) NULL DEFAULT NULL COMMENT '在线状态(0离线 1在线)',
  `register_ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '注册IP',
  `register_date` datetime(3) NULL DEFAULT NULL COMMENT '注册时间',
  `last_login_ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '最后登录IP',
  `last_login_date` datetime(3) NULL DEFAULT NULL COMMENT '最后登录时间',
  `last_login_os` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '最后登录系统',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(3) NULL DEFAULT NULL COMMENT '创建日期',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(3) NULL DEFAULT NULL COMMENT '更新日期',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标志(0正常 1删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_member_security_no`(`member_no`) USING BTREE,
  UNIQUE INDEX `uk_member_security_member_id`(`member_id`) USING BTREE,
  UNIQUE INDEX `uk_member_security_email`(`email`) USING BTREE,
  UNIQUE INDEX `uk_member_security_phone`(`area_code`, `phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '会员登录表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
