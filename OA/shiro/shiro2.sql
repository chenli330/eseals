/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50637
 Source Host           : localhost:3306
 Source Schema         : shiro2

 Target Server Type    : MySQL
 Target Server Version : 50637
 File Encoding         : 65001

 Date: 13/08/2018 13:47:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization`  (
  `oId` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `parentid` bigint(20) DEFAULT NULL,
  `parentids` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `available` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`oId`) USING BTREE,
  INDEX `idx_sys_organization_parent_id`(`parentid`) USING BTREE,
  INDEX `idx_sys_organization_parent_ids`(`parentids`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of organization
-- ----------------------------
INSERT INTO `organization` VALUES (1, '总公司', 0, '0/', 1);
INSERT INTO `organization` VALUES (3, '分公司2', 1, '0/1/', 1);
INSERT INTO `organization` VALUES (5, '分公司21', 3, NULL, NULL);
INSERT INTO `organization` VALUES (6, '分公司11', 7, NULL, NULL);
INSERT INTO `organization` VALUES (7, '分公司1', 1, NULL, NULL);

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`  (
  `reId` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `permission` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `available` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`reId`) USING BTREE,
  INDEX `idx_sys_resource_parent_id`(`parent_id`) USING BTREE,
  INDEX `idx_sys_resource_parent_ids`(`parent_ids`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES (1, '资源', 'menu', '', 0, '0/', '', 1);
INSERT INTO `resource` VALUES (11, '组织机构管理', 'menu', '/organization', 1, '0/1/', 'organization:view', 1);
INSERT INTO `resource` VALUES (12, '组织机构新增', 'button', '', 11, '0/1/11/', 'organization:create', 1);
INSERT INTO `resource` VALUES (13, '组织机构修改', 'button', '', 11, '0/1/11/', 'organization:update', 1);
INSERT INTO `resource` VALUES (14, '组织机构删除', 'button', '', 11, '0/1/11/', 'organization:delete', 1);
INSERT INTO `resource` VALUES (15, '组织机构查看', 'button', '', 11, '0/1/11/', 'organization:view', 1);
INSERT INTO `resource` VALUES (21, '用户管理', 'menu', '/user/findAllUser', 1, '0/1/', 'user:view', 1);
INSERT INTO `resource` VALUES (22, '用户新增', 'button', '', 21, '0/1/21/', 'user:create', 1);
INSERT INTO `resource` VALUES (23, '用户修改', 'button', '', 21, '0/1/21/', 'user:update', 1);
INSERT INTO `resource` VALUES (24, '用户删除', 'button', '', 21, '0/1/21/', 'user:delete', 1);
INSERT INTO `resource` VALUES (25, '用户查看', 'button', '', 21, '0/1/21/', 'resource:view', 1);
INSERT INTO `resource` VALUES (31, '资源管理（错误页面）', 'menu', '/resource', 1, '0/1/', 'resource:view', 1);
INSERT INTO `resource` VALUES (32, '资源新增', 'button', '', 31, '0/1/31/', 'resource:create', 1);
INSERT INTO `resource` VALUES (33, '资源修改', 'button', '', 31, '0/1/31/', 'resource:update', 1);
INSERT INTO `resource` VALUES (34, '资源删除', 'button', '', 31, '0/1/31/', 'resource:delete', 1);
INSERT INTO `resource` VALUES (35, '资源查看', 'button', '', 31, '0/1/31/', 'resource:view', 1);
INSERT INTO `resource` VALUES (41, '角色管理', 'menu', '/role/showRole', 1, '0/1/', 'role:view', 1);
INSERT INTO `resource` VALUES (42, '角色新增', 'button', '', 41, '0/1/41/', 'role:create', 1);
INSERT INTO `resource` VALUES (43, '角色修改', 'button', '', 41, '0/1/41/', 'role:update', 1);
INSERT INTO `resource` VALUES (44, '角色删除', 'button', '', 41, '0/1/41/', 'role:delete', 1);
INSERT INTO `resource` VALUES (45, '角色查看', 'button', '', 41, '0/1/41/', 'role:view', 1);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `rId` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `resource_ids` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `available` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`rId`) USING BTREE,
  INDEX `idx_sys_role_resource_ids`(`resource_ids`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin', '超级管理员', NULL, NULL);
INSERT INTO `role` VALUES (3, 'common', '用户', NULL, NULL);

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(255) DEFAULT NULL,
  `resource_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 644 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES (185, 3, 11);
INSERT INTO `role_resource` VALUES (186, 3, 15);
INSERT INTO `role_resource` VALUES (187, 3, 21);
INSERT INTO `role_resource` VALUES (188, 3, 25);
INSERT INTO `role_resource` VALUES (189, 3, 31);
INSERT INTO `role_resource` VALUES (190, 3, 35);
INSERT INTO `role_resource` VALUES (191, 3, 41);
INSERT INTO `role_resource` VALUES (192, 3, 45);
INSERT INTO `role_resource` VALUES (625, 1, 11);
INSERT INTO `role_resource` VALUES (626, 1, 12);
INSERT INTO `role_resource` VALUES (627, 1, 13);
INSERT INTO `role_resource` VALUES (628, 1, 15);
INSERT INTO `role_resource` VALUES (629, 1, 21);
INSERT INTO `role_resource` VALUES (630, 1, 22);
INSERT INTO `role_resource` VALUES (631, 1, 23);
INSERT INTO `role_resource` VALUES (632, 1, 24);
INSERT INTO `role_resource` VALUES (633, 1, 25);
INSERT INTO `role_resource` VALUES (634, 1, 31);
INSERT INTO `role_resource` VALUES (635, 1, 32);
INSERT INTO `role_resource` VALUES (636, 1, 33);
INSERT INTO `role_resource` VALUES (637, 1, 34);
INSERT INTO `role_resource` VALUES (638, 1, 35);
INSERT INTO `role_resource` VALUES (639, 1, 41);
INSERT INTO `role_resource` VALUES (640, 1, 42);
INSERT INTO `role_resource` VALUES (641, 1, 43);
INSERT INTO `role_resource` VALUES (642, 1, 44);
INSERT INTO `role_resource` VALUES (643, 1, 45);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `organizationId` bigint(20) DEFAULT NULL,
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `salt` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `roleIds` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `locked` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_sys_user_username`(`username`) USING BTREE,
  INDEX `idx_sys_user_organization_id`(`organizationId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 1, 'admin', '77fdb002b1fcfe689d7f4357b997ffe794ac5dfc', '2464admin', '3', 0);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) DEFAULT NULL,
  `role_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `qwe`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1);
INSERT INTO `user_role` VALUES (14, 9, 1);
INSERT INTO `user_role` VALUES (15, 10, 1);
INSERT INTO `user_role` VALUES (16, 11, 1);
INSERT INTO `user_role` VALUES (17, 12, 1);
INSERT INTO `user_role` VALUES (18, 13, 1);

SET FOREIGN_KEY_CHECKS = 1;
