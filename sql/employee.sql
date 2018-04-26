/*
 Navicat Premium Data Transfer

 Source Server         : Localhost Mysql
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : employeemanager

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 26/04/2018 09:47:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for application
-- ----------------------------
DROP TABLE IF EXISTS `application`;
CREATE TABLE `application`  (
  `app_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `app_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '申请修改考勤理由',
  `app_result` tinyint(1) DEFAULT 0 COMMENT '审批结果  0为未通过 1为通过',
  `attend_id` int(11) NOT NULL COMMENT '对应的考勤记录ID',
  `type_id` int(11) NOT NULL COMMENT '希望修改的考勤类型',
  PRIMARY KEY (`app_id`) USING BTREE,
  INDEX `FK_9wx0gm9e04sxa4spsnnvshr2v`(`attend_id`) USING BTREE,
  INDEX `FK_ncy74ck30mee5rdkk2uhl76r4`(`type_id`) USING BTREE,
  CONSTRAINT `FK_9wx0gm9e04sxa4spsnnvshr2v` FOREIGN KEY (`attend_id`) REFERENCES `attend` (`attend_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_ncy74ck30mee5rdkk2uhl76r4` FOREIGN KEY (`type_id`) REFERENCES `attend_type` (`type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of application
-- ----------------------------
INSERT INTO `application` VALUES (8, '我迟到了', 0, 378, 6);
INSERT INTO `application` VALUES (9, '我迟到了', 0, 380, 6);
INSERT INTO `application` VALUES (10, '我迟到了', 0, 381, 6);
INSERT INTO `application` VALUES (11, '我迟到了', 0, 382, 6);
INSERT INTO `application` VALUES (12, '我迟到了', 0, 383, 6);

-- ----------------------------
-- Table structure for attend
-- ----------------------------
DROP TABLE IF EXISTS `attend`;
CREATE TABLE `attend`  (
  `attend_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `emp_id` int(11) DEFAULT NULL COMMENT '对应的考勤人',
  `duty_day` datetime(0) NOT NULL COMMENT '考勤的开始时间点',
  `is_come` tinyint(1) DEFAULT NULL COMMENT '是否考勤  0为没有 1为签到',
  `punch_time` datetime(0) DEFAULT NULL COMMENT '考勤的时间',
  `type_id` int(11) NOT NULL COMMENT '考勤的类型',
  PRIMARY KEY (`attend_id`) USING BTREE,
  INDEX `FK_sxsglsafwbl3kjyu20pulbv7`(`type_id`) USING BTREE,
  INDEX `FK_e5w782m7lqroclf83divt40iv`(`emp_id`) USING BTREE,
  CONSTRAINT `FK_e5w782m7lqroclf83divt40iv` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_sxsglsafwbl3kjyu20pulbv7` FOREIGN KEY (`type_id`) REFERENCES `attend_type` (`type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 388 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attend
-- ----------------------------
INSERT INTO `attend` VALUES (378, 101, '2018-04-26 09:35:51', 0, NULL, 6);
INSERT INTO `attend` VALUES (379, 102, '2018-04-25 09:38:04', 1, '2018-04-26 09:38:11', 6);
INSERT INTO `attend` VALUES (380, 101, '2018-04-26 09:35:51', 0, NULL, 7);
INSERT INTO `attend` VALUES (381, 102, '2018-04-26 09:35:51', 0, NULL, 7);
INSERT INTO `attend` VALUES (382, 103, '2018-04-26 09:35:51', 0, NULL, 7);
INSERT INTO `attend` VALUES (383, 104, '2018-04-26 09:35:51', 0, NULL, 7);
INSERT INTO `attend` VALUES (384, 102, '2018-04-25 09:38:04', 1, '2018-04-26 09:38:11', 6);
INSERT INTO `attend` VALUES (385, 103, '2018-04-25 09:38:04', 1, '2018-04-26 09:38:11', 6);
INSERT INTO `attend` VALUES (386, 104, '2018-04-25 09:38:04', 1, '2018-04-26 09:38:11', 6);
INSERT INTO `attend` VALUES (387, 105, '2018-04-25 09:38:04', 1, '2018-04-26 09:38:11', 6);

-- ----------------------------
-- Table structure for attend_type
-- ----------------------------
DROP TABLE IF EXISTS `attend_type`;
CREATE TABLE `attend_type`  (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attend_type
-- ----------------------------
INSERT INTO `attend_type` VALUES (6, '正常');
INSERT INTO `attend_type` VALUES (7, '缺勤');
INSERT INTO `attend_type` VALUES (8, '迟到');
INSERT INTO `attend_type` VALUES (9, '请假');

-- ----------------------------
-- Table structure for check_back
-- ----------------------------
DROP TABLE IF EXISTS `check_back`;
CREATE TABLE `check_back`  (
  `check_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `check_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '审批理由',
  `check_result` tinyint(1) NOT NULL COMMENT '审批结果  0为不通过 1为通过',
  `app_id` int(11) DEFAULT NULL COMMENT '审批记录ID',
  `mgr_id` int(11) NOT NULL COMMENT '审批的管理员ID',
  PRIMARY KEY (`check_id`) USING BTREE,
  INDEX `FK_939cwdph0r4ia2okhjqn7f92y`(`app_id`) USING BTREE,
  INDEX `FK_cfkusgfp28so2n7lyvat4byju`(`mgr_id`) USING BTREE,
  CONSTRAINT `FK_939cwdph0r4ia2okhjqn7f92y` FOREIGN KEY (`app_id`) REFERENCES `application` (`app_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_cfkusgfp28so2n7lyvat4byju` FOREIGN KEY (`mgr_id`) REFERENCES `employee` (`emp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of check_back
-- ----------------------------
INSERT INTO `check_back` VALUES (42, '不同意', 0, 8, 101);
INSERT INTO `check_back` VALUES (44, '不同意', 0, 9, 101);
INSERT INTO `check_back` VALUES (45, '不同意', 0, 10, 101);
INSERT INTO `check_back` VALUES (46, '不同意', 0, 11, 101);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `emp_account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号名',
  `emp_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '123456' COMMENT '密码 ',
  `emp_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '中文名字',
  `emp_className` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '班级名字',
  `emp_tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话号码',
  `emp_bedroom` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '寝室号',
  `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门名字',
  `mgr_id` int(11) DEFAULT NULL COMMENT '管理员ID,引用的是本张表中的emp_Id',
  `emp_type` int(11) NOT NULL DEFAULT 1 COMMENT '身份类型',
  PRIMARY KEY (`emp_id`) USING BTREE,
  UNIQUE INDEX `UK_5tltp1u3mevdum1ilp1ft71rg`(`emp_account`) USING BTREE,
  INDEX `FK_5f44y8jo1j00uhenw2naboii8`(`mgr_id`) USING BTREE,
  CONSTRAINT `FK_5f44y8jo1j00uhenw2naboii8` FOREIGN KEY (`mgr_id`) REFERENCES `employee` (`emp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (101, '10000', '123456', 'name', '测试班级', '10086', '1#101', 'deptName', NULL, 1);
INSERT INTO `employee` VALUES (102, 'admin', 'admin', 'name', '测试班级', '10086', '1#101', 'deptName', NULL, 2);
INSERT INTO `employee` VALUES (103, 'super', 'super', 'superName', '测试班级', '10086', '1#101', 'deptName', NULL, 1);
INSERT INTO `employee` VALUES (104, 'student', 'student', 'studentName', '测试班级', '10086', '1#101', 'deptName', NULL, 1);
INSERT INTO `employee` VALUES (105, 'teacher', 'teacher', 'teacherName', '测试班级', '10086', '1#101', 'deptName', NULL, 1);

SET FOREIGN_KEY_CHECKS = 1;
