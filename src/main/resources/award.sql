/*
 Navicat Premium Data Transfer

 Source Server         : localhost_MySQL5.7
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : test2

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 07/06/2020 15:12:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for apartment_score
-- ----------------------------
DROP TABLE IF EXISTS `apartment_score`;
CREATE TABLE `apartment_score`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apartment_id` int(11) NOT NULL,
  `room` int(11) NOT NULL,
  `bed` int(11) NOT NULL,
  `score` decimal(4, 2) NOT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKexyka9mgc92wgnnwtqkmbctcc`(`apartment_id`) USING BTREE,
  CONSTRAINT `FKexyka9mgc92wgnnwtqkmbctcc` FOREIGN KEY (`apartment_id`) REFERENCES `sys_apartment` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for exam_score
-- ----------------------------
DROP TABLE IF EXISTS `exam_score`;
CREATE TABLE `exam_score`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_id` int(11) NOT NULL,
  `score` decimal(5, 2) NOT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `count` int(11) NOT NULL,
  `term_id` int(11) NOT NULL,
  `dept_id` int(11) NOT NULL,
  `grade_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `exam_score_ibfk_1`(`stu_id`) USING BTREE,
  INDEX `exam_score_ibfk_2`(`term_id`) USING BTREE,
  INDEX `exam_score_ibfk_3`(`dept_id`) USING BTREE,
  INDEX `exam_score_ibfk_4`(`grade_id`) USING BTREE,
  CONSTRAINT `exam_score_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `sys_user_stu` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `exam_score_ibfk_2` FOREIGN KEY (`term_id`) REFERENCES `sys_term` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `exam_score_ibfk_3` FOREIGN KEY (`dept_id`) REFERENCES `sys_dept` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `exam_score_ibfk_4` FOREIGN KEY (`grade_id`) REFERENCES `sys_grade` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for extra_score
-- ----------------------------
DROP TABLE IF EXISTS `extra_score`;
CREATE TABLE `extra_score`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `score` decimal(4, 2) NOT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` bigint(20) NULL DEFAULT NULL,
  `time_id` int(11) NOT NULL,
  `term_id` int(11) NOT NULL,
  `stu_id` int(11) NOT NULL,
  `dept_id` int(11) NOT NULL,
  `grade_id` int(11) NOT NULL,
  `status` int(3) NOT NULL DEFAULT -1,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `extra_score_ibfk_1`(`term_id`) USING BTREE,
  INDEX `extra_score_ibfk_2`(`stu_id`) USING BTREE,
  INDEX `extra_score_ibfk_3`(`dept_id`) USING BTREE,
  INDEX `extra_score_ibfk_4`(`grade_id`) USING BTREE,
  INDEX `extra_score_ibfk_5`(`time_id`) USING BTREE,
  CONSTRAINT `extra_score_ibfk_1` FOREIGN KEY (`term_id`) REFERENCES `sys_term` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `extra_score_ibfk_2` FOREIGN KEY (`stu_id`) REFERENCES `sys_user_stu` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `extra_score_ibfk_3` FOREIGN KEY (`dept_id`) REFERENCES `sys_dept` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `extra_score_ibfk_4` FOREIGN KEY (`grade_id`) REFERENCES `sys_grade` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `extra_score_ibfk_5` FOREIGN KEY (`time_id`) REFERENCES `extra_time` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for extra_time
-- ----------------------------
DROP TABLE IF EXISTS `extra_time`;
CREATE TABLE `extra_time`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `term_id` int(11) NOT NULL,
  `begin_time` bigint(20) NOT NULL,
  `end_time` bigint(20) NOT NULL,
  `dept_id` int(11) NOT NULL,
  `grade_id` int(11) NOT NULL,
  `tutor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `extra_time_ibfk_1`(`dept_id`) USING BTREE,
  INDEX `extra_time_ibfk_2`(`grade_id`) USING BTREE,
  INDEX `extra_time_ibfk_3`(`tutor_id`) USING BTREE,
  INDEX `extra_time_ibfk_4`(`term_id`) USING BTREE,
  CONSTRAINT `extra_time_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `sys_dept` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `extra_time_ibfk_2` FOREIGN KEY (`grade_id`) REFERENCES `sys_grade` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `extra_time_ibfk_3` FOREIGN KEY (`tutor_id`) REFERENCES `sys_user_tutor` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `extra_time_ibfk_4` FOREIGN KEY (`term_id`) REFERENCES `sys_term` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for notice_apartment
-- ----------------------------
DROP TABLE IF EXISTS `notice_apartment`;
CREATE TABLE `notice_apartment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `apartment_id` int(11) NOT NULL,
  `submitter` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `notice_apartment_ibfk_1`(`apartment_id`) USING BTREE,
  CONSTRAINT `notice_apartment_ibfk_1` FOREIGN KEY (`apartment_id`) REFERENCES `sys_apartment` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for notice_tutor
-- ----------------------------
DROP TABLE IF EXISTS `notice_tutor`;
CREATE TABLE `notice_tutor`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dept_id` int(11) NOT NULL,
  `grade_id` int(11) NOT NULL,
  `submitter` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `dept_id`(`dept_id`) USING BTREE,
  INDEX `notice_tutor_ibfk_2`(`grade_id`) USING BTREE,
  CONSTRAINT `notice_tutor_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `sys_dept` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `notice_tutor_ibfk_2` FOREIGN KEY (`grade_id`) REFERENCES `sys_grade` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for notice_union
-- ----------------------------
DROP TABLE IF EXISTS `notice_union`;
CREATE TABLE `notice_union`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dept_id` int(11) NOT NULL,
  `submitter` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `notice_union_ibfk_1`(`dept_id`) USING BTREE,
  CONSTRAINT `notice_union_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `sys_dept` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_apartment
-- ----------------------------
DROP TABLE IF EXISTS `sys_apartment`;
CREATE TABLE `sys_apartment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_dxoi0drkng2casc2sdifcnp3o`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_5vgnf47ptihlqghkfs1ud6ke0`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_grade
-- ----------------------------
DROP TABLE IF EXISTS `sys_grade`;
CREATE TABLE `sys_grade`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_m8xcsnv9f9q0tuhlr9qm7d89q`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_term
-- ----------------------------
DROP TABLE IF EXISTS `sys_term`;
CREATE TABLE `sys_term`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `begin_time` bigint(20) NOT NULL,
  `end_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_51bvuyvihefoh4kp5syh2jpi4`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$ncOygf6oZIv.LB.k9wiidOaREvffLbtFTZyp0MTxkR9u2e41bAoHK', 'ROLE_ADMIN', '超级管理员', 'admin@award');

-- ----------------------------
-- Table structure for sys_user_admin
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_admin`;
CREATE TABLE `sys_user_admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_roi5gudw335usq12jwb38f6hq`(`user_id`) USING BTREE,
  CONSTRAINT `FKq371wrvrrgfvmspgqbte37avh` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_admin
-- ----------------------------
INSERT INTO `sys_user_admin` VALUES (1, 1);

-- ----------------------------
-- Table structure for sys_user_houseparent
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_houseparent`;
CREATE TABLE `sys_user_houseparent`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apartment_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_ancf3b1slwephgbuoman0p8xd`(`apartment_id`) USING BTREE,
  INDEX `FKls0shwxsasd6omicbmyb8gu6f`(`user_id`) USING BTREE,
  CONSTRAINT `FKfdv7yp1upy1fq5wl7a5k6v3y7` FOREIGN KEY (`apartment_id`) REFERENCES `sys_apartment` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FKls0shwxsasd6omicbmyb8gu6f` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_stu
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_stu`;
CREATE TABLE `sys_user_stu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `dept_id` int(11) NOT NULL,
  `grade_id` int(11) NOT NULL,
  `apartment_id` int(11) NOT NULL,
  `room` int(11) NOT NULL,
  `bed` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_sm188mesavky9t90t82rqo627`(`user_id`) USING BTREE,
  INDEX `FKetqebdt91n5xi511qtbyr90jn`(`apartment_id`) USING BTREE,
  INDEX `FKqw4aptmluaa9t4vn7cw6j01go`(`dept_id`) USING BTREE,
  INDEX `FK7hnq9n8xm0mktogdunj8q1td1`(`grade_id`) USING BTREE,
  CONSTRAINT `FK7hnq9n8xm0mktogdunj8q1td1` FOREIGN KEY (`grade_id`) REFERENCES `sys_grade` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FKetqebdt91n5xi511qtbyr90jn` FOREIGN KEY (`apartment_id`) REFERENCES `sys_apartment` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FKlcjm2suvjv4ms2sf3gqp5jm5g` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FKqw4aptmluaa9t4vn7cw6j01go` FOREIGN KEY (`dept_id`) REFERENCES `sys_dept` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_tutor
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_tutor`;
CREATE TABLE `sys_user_tutor`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_id` int(11) NOT NULL,
  `grade_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_jt2j78se142w0np4pfcpiw71r`(`user_id`) USING BTREE,
  INDEX `FKt32ujf1a51skr64i4io16k7t6`(`dept_id`) USING BTREE,
  INDEX `FKak1hq5svfkah6bkdfri4ppuy6`(`grade_id`) USING BTREE,
  CONSTRAINT `FKak1hq5svfkah6bkdfri4ppuy6` FOREIGN KEY (`grade_id`) REFERENCES `sys_grade` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FKlnvyi8npmoc6kxy1x6xjwtekv` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FKt32ujf1a51skr64i4io16k7t6` FOREIGN KEY (`dept_id`) REFERENCES `sys_dept` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_union
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_union`;
CREATE TABLE `sys_user_union`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_ibm96q6aiqn6kys5tt2ge9f25`(`user_id`) USING BTREE,
  INDEX `FKdcmqafoxowf6fytxlqxwlijwc`(`dept_id`) USING BTREE,
  CONSTRAINT `FKdcmqafoxowf6fytxlqxwlijwc` FOREIGN KEY (`dept_id`) REFERENCES `sys_dept` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FKrjrlot7ih3v6ytbsbqifkw9ey` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for union_score
-- ----------------------------
DROP TABLE IF EXISTS `union_score`;
CREATE TABLE `union_score`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_id` int(11) NOT NULL,
  `score` decimal(4, 2) NOT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `union_id` int(11) NULL DEFAULT NULL,
  `dept_id` int(11) NOT NULL,
  `create_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK6fgyiwevgmueghcmc3d2nbd1f`(`stu_id`) USING BTREE,
  INDEX `FKehftcrgy92lvhi47lijmcmcr3`(`union_id`) USING BTREE,
  INDEX `FK1wwkllbx53fhft873484oc4ds`(`dept_id`) USING BTREE,
  CONSTRAINT `FK1wwkllbx53fhft873484oc4ds` FOREIGN KEY (`dept_id`) REFERENCES `sys_dept` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FK6fgyiwevgmueghcmc3d2nbd1f` FOREIGN KEY (`stu_id`) REFERENCES `sys_user_stu` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FKehftcrgy92lvhi47lijmcmcr3` FOREIGN KEY (`union_id`) REFERENCES `sys_user_union` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
