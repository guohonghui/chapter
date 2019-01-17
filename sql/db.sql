/*
Navicat MySQL Data Transfer

Source Server         : Mysql
Source Server Version : 50140
Source Host           : localhost:3306
Source Database       : db

Target Server Type    : MYSQL
Target Server Version : 50140
File Encoding         : 65001

Date: 2019-01-17 16:05:27
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` bigint(20) DEFAULT NULL COMMENT '上级ID',
  `name` varchar(50) DEFAULT NULL COMMENT '权限名',
  `type` smallint(6) DEFAULT NULL COMMENT '类型 0、菜单 1、功能',
  `sort` smallint(6) DEFAULT NULL COMMENT '排序',
  `url` varchar(100) DEFAULT NULL COMMENT '地址',
  `perm_code` varchar(30) DEFAULT NULL COMMENT '权限编码',
  `icon` varchar(30) DEFAULT NULL COMMENT '图标',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `status` smallint(6) DEFAULT NULL COMMENT '状态 0、禁用 1、正常',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='permission 权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '0', '系统管理', '0', '100', '-', '-', 'fa fa-cog fa-fw', '系统管理员目录', '1', '2017-09-12 16:48:53', '2017-09-21 16:57:15');
INSERT INTO `permission` VALUES ('2', '1', '用户管理', '1', '110', '/admin/user/list', 'user:show', 'fa fa-user', '用户管理', '1', '2017-09-12 16:52:28', '2017-09-21 16:57:31');
INSERT INTO `permission` VALUES ('3', '1', '角色管理', '1', '120', '/admin/role/list', '1', 'fa fa-circle', '角色管理', '1', '2017-09-12 16:52:34', '2017-09-21 16:58:33');
INSERT INTO `permission` VALUES ('4', '1', '权限管理', '1', '130', '/admin/permission/list', '-', 'fa fa-shirtsinbulk', '权限管理', '1', '2017-09-12 16:52:40', '2017-09-21 16:58:40');
INSERT INTO `permission` VALUES ('5', '0', '测试', '0', '200', '/user/list', '-', 'fa fa-columns', '-', '1', '2017-09-12 16:52:45', '2017-11-05 14:37:16');
INSERT INTO `permission` VALUES ('10', '0', '业务管理', '0', '300', '-', '-', 'fa fa-tag', '-', '1', '2017-09-21 09:34:26', '2017-09-21 16:59:01');
INSERT INTO `permission` VALUES ('11', '10', '批次管理', '1', '310', '/admin/ossStudybatch/list', '-', 'fa fa-bank', '-', '1', '2017-09-21 10:57:25', '2017-09-21 17:01:04');
INSERT INTO `permission` VALUES ('12', '10', '课程管理', '1', '320', '/admin/ossCourseinfo/list', '-', 'fa fa-book', '-', '1', '2017-09-21 10:58:33', '2017-09-21 17:01:19');
INSERT INTO `permission` VALUES ('14', '10', '成绩管理', '1', '340', '-', '-', 'fa fa-file-excel-o', '-', '1', '2017-09-21 11:00:53', '2017-09-21 17:01:58');
INSERT INTO `permission` VALUES ('15', '10', '添加课件', '1', '330', '/admin/ossCourseware/addCoursewareView', '-', 'fa fa-folder', '-', '1', '2017-09-21 17:04:07', '2017-09-21 17:07:42');
INSERT INTO `permission` VALUES ('16', '10', '课件管理', '1', '335', '/admin/ossCourseware/list', '-', '-', '课件管理', '1', '2017-09-26 16:41:49', null);
INSERT INTO `permission` VALUES ('17', '2', '添加用户', '2', '110', '-', 'user:add', '-', '-', '1', '2017-10-12 00:58:21', null);
INSERT INTO `permission` VALUES ('18', '2', '修改用户', '2', '110', '-', 'user:update', '-', '-', '1', '2017-10-12 00:58:21', null);
INSERT INTO `permission` VALUES ('19', '2', '分配角色', '2', '110', '-', 'user:distribution', '-', '-', '1', '2017-10-12 00:58:21', null);
INSERT INTO `permission` VALUES ('20', '2', '审核通过', '2', '110', '-', 'user:optionUserStatus', '-', '-', '1', '2017-10-12 00:58:21', null);
INSERT INTO `permission` VALUES ('21', '2', '删除用户', '2', '110', '-', 'user:delete', '-', '-', '1', '2017-10-12 00:58:21', null);
INSERT INTO `permission` VALUES ('22', '5', '测试用户无权限', '1', '1', '/admin/user/list', '-', 'fa fa-telegram', '测试用户无权限', '1', '2017-11-05 14:38:16', null);

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30) DEFAULT NULL COMMENT '角色名',
  `sort` smallint(6) DEFAULT NULL COMMENT '排序',
  `description` varchar(60) DEFAULT NULL COMMENT '描述',
  `status` smallint(5) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='role 角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('2', '超级管理员', '1', '超级管理员', '1', '2017-09-12 17:04:33', '2017-09-12 17:04:35');
INSERT INTO `role` VALUES ('9', '审批', '900', '审批员', '1', '2017-10-12 15:53:14', null);
INSERT INTO `role` VALUES ('10', '测试无权限', '3000', '测试无权限', '1', '2017-11-05 14:35:16', '2017-11-05 14:35:24');

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rid` bigint(20) unsigned DEFAULT NULL COMMENT '角色ID',
  `pid` bigint(20) unsigned DEFAULT NULL COMMENT '权限ID',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1085806161511645189 DEFAULT CHARSET=utf8 COMMENT='role_permission 角色权限表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('133', '9', '1', '2017-10-12 15:53:33', null);
INSERT INTO `role_permission` VALUES ('134', '9', '2', '2017-10-12 15:53:33', null);
INSERT INTO `role_permission` VALUES ('135', '9', '20', '2017-10-12 15:53:33', null);
INSERT INTO `role_permission` VALUES ('151', '10', '5', '2017-11-05 14:38:27', null);
INSERT INTO `role_permission` VALUES ('152', '10', '22', '2017-11-05 14:38:27', null);
INSERT INTO `role_permission` VALUES ('1085806161490673665', '2', '1', null, null);
INSERT INTO `role_permission` VALUES ('1085806161499062274', '2', '2', null, null);
INSERT INTO `role_permission` VALUES ('1085806161499062275', '2', '19', null, null);
INSERT INTO `role_permission` VALUES ('1085806161503256578', '2', '18', null, null);
INSERT INTO `role_permission` VALUES ('1085806161503256579', '2', '17', null, null);
INSERT INTO `role_permission` VALUES ('1085806161503256580', '2', '20', null, null);
INSERT INTO `role_permission` VALUES ('1085806161503256581', '2', '21', null, null);
INSERT INTO `role_permission` VALUES ('1085806161503256582', '2', '3', null, null);
INSERT INTO `role_permission` VALUES ('1085806161507450882', '2', '4', null, null);
INSERT INTO `role_permission` VALUES ('1085806161507450883', '2', '5', null, null);
INSERT INTO `role_permission` VALUES ('1085806161507450884', '2', '22', null, null);
INSERT INTO `role_permission` VALUES ('1085806161507450885', '2', '10', null, null);
INSERT INTO `role_permission` VALUES ('1085806161507450886', '2', '11', null, null);
INSERT INTO `role_permission` VALUES ('1085806161507450887', '2', '12', null, null);
INSERT INTO `role_permission` VALUES ('1085806161511645186', '2', '15', null, null);
INSERT INTO `role_permission` VALUES ('1085806161511645187', '2', '16', null, null);
INSERT INTO `role_permission` VALUES ('1085806161511645188', '2', '14', null, null);

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `salt` varchar(50) DEFAULT NULL COMMENT '盐',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` smallint(6) DEFAULT NULL COMMENT '0、禁用 1、正常',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8 COMMENT='user 用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', 'admin', 'admin', '18b7dc5ec128550a28b08e90f897fa35', '1547453300320', 'admin@qq.com', '1', '2019-01-13 09:40:18', '2019-01-13 09:40:20');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` bigint(20) unsigned DEFAULT NULL COMMENT '用户ID',
  `rid` bigint(20) unsigned DEFAULT NULL COMMENT '角色ID',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=1085806193992335362 DEFAULT CHARSET=utf8 COMMENT='user_role 用户角色关联表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('21', '116', '9', '2017-10-12 15:53:52', null);
INSERT INTO `user_role` VALUES ('22', '115', '10', '2017-11-05 14:35:36', null);
INSERT INTO `user_role` VALUES ('1085806193992335361', '2', '2', null, null);
