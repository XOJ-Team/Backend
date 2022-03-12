/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : oj

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 12/03/2022 10:10:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for competition
-- ----------------------------
DROP TABLE IF EXISTS `competition`;
CREATE TABLE `competition`  (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `is_delete` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除',
  `NAME` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名字',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `delete_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '删除时间',
  `brief_introduction` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '简介',
  `creator` bigint(11) NOT NULL COMMENT '创建者',
  `creator_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者姓名',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `competition_id_index`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '比赛' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `is_delete` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除',
  `NAME` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名字',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `delete_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '删除时间',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '题目内容',
  `creator` bigint(11) NOT NULL COMMENT '创建者',
  `creator_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者姓名',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `question_id_index`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '题目' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question_competition
-- ----------------------------
DROP TABLE IF EXISTS `question_competition`;
CREATE TABLE `question_competition`  (
  `question_id` bigint(11) NULL DEFAULT NULL COMMENT '题目ID',
  `competition_id` bigint(11) NULL DEFAULT NULL COMMENT '比赛ID',
  `is_delete` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除',
  `score` tinyint(4) NULL DEFAULT NULL COMMENT '分数',
  INDEX `question_competition_index`(`competition_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '题目-比赛映射' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for testcase_question
-- ----------------------------
DROP TABLE IF EXISTS `testcase_question`;
CREATE TABLE `testcase_question`  (
  `question_id` bigint(11) NULL DEFAULT NULL COMMENT '题目ID',
  `case` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '测试用例',
  `result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '测试用例结果',
  `is_delete` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除',
  INDEX `testcase_question_index`(`question_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '题目用例' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_base
-- ----------------------------
DROP TABLE IF EXISTS `user_base`;
CREATE TABLE `user_base`  (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `is_delete` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除',
  `NAME` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名字',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `delete_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '删除时间',
  `mail` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话号',
  `score` int(11) NULL DEFAULT NULL COMMENT '分数',
  `rank` int(11) NULL DEFAULT NULL COMMENT '排名',
  `authority` tinyint(4) NULL DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `phone_number`(`phone_number`) USING BTREE,
  INDEX `user_id_index`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_competition
-- ----------------------------
DROP TABLE IF EXISTS `user_competition`;
CREATE TABLE `user_competition`  (
  `user_id` bigint(11) NULL DEFAULT NULL COMMENT '用户ID',
  `competition_id` bigint(11) NULL DEFAULT NULL COMMENT '比赛ID',
  `is_delete` tinyint(4) NULL DEFAULT NULL COMMENT '是否删除',
  INDEX `user_competition_index`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户-比赛映射' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
