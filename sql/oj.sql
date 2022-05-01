-- MySQL dump 10.13  Distrib 8.0.28, for macos11 (x86_64)
--
-- Host: 127.0.0.1    Database: oj
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `competition`
--

DROP TABLE IF EXISTS `competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `competition` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                               `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除',
                               `NAME` varchar(255) NOT NULL COMMENT '名字',
                               `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `delete_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '删除时间',
                               `brief_introduction` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '简介',
                               `creator` bigint NOT NULL COMMENT '创建者',
                               `creator_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者姓名',
                               `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
                               `end_time` timestamp NULL DEFAULT NULL COMMENT '结束时间',
                               PRIMARY KEY (`id`) USING BTREE,
                               UNIQUE KEY `competition_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='比赛';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                            `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除',
                            `NAME` varchar(255) NOT NULL COMMENT '名字',
                            `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `delete_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '删除时间',
                            `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '题目内容',
                            `creator` bigint NOT NULL DEFAULT '0' COMMENT '创建者',
                            `creator_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建者姓名',
                            `memory_limit` int NOT NULL DEFAULT '0' COMMENT '内存限制',
                            `time_limit` int NOT NULL DEFAULT '0' COMMENT '时间限制',
                            `accept` bigint NOT NULL DEFAULT '0' COMMENT '通过次数',
                            `rate` varchar(20) NOT NULL DEFAULT '0.00%' COMMENT '通过率',
                            `total` bigint NOT NULL DEFAULT '0' COMMENT '总提交次数',
                            `tags` varchar(255) DEFAULT '',
                            `question_level` int NOT NULL DEFAULT '0' COMMENT '难度',
                            `is_hide` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否隐藏',
                            `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                            `modifier` bigint NOT NULL DEFAULT '0' COMMENT '修改人id',
                            `modifier_name` varchar(255) NOT NULL DEFAULT '' COMMENT '修改人姓名',
                            PRIMARY KEY (`id`) USING BTREE,
                            UNIQUE KEY `question_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='题目';
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `question_competition`
--

DROP TABLE IF EXISTS `question_competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question_competition` (
                                        `question_name` varchar(255) NOT NULL DEFAULT '' COMMENT 'question名字',
                                        `question_id` bigint DEFAULT NULL COMMENT '题目ID',
                                        `competition_id` bigint DEFAULT NULL COMMENT '比赛ID',
                                        `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除',
                                        `score` int DEFAULT NULL COMMENT '分数',
                                        `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                        PRIMARY KEY (`id`),
                                        UNIQUE KEY `question_competition_id_uindex` (`id`),
                                        KEY `question_competition_index` (`competition_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='题目-比赛映射';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `submit_records`
--

DROP TABLE IF EXISTS `submit_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `submit_records` (
                                  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
                                  `question_id` bigint DEFAULT '0' COMMENT '问题id',
                                  `question_name` varchar(255) NOT NULL DEFAULT '' COMMENT '题目名称',
                                  `user_id` bigint NOT NULL DEFAULT '0' COMMENT '用户id',
                                  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `lang` varchar(32) NOT NULL DEFAULT '' COMMENT '提交语言',
                                  `result` int NOT NULL DEFAULT '-1' COMMENT '结果状态',
                                  `time_cost` int NOT NULL DEFAULT '-1' COMMENT '消耗时间',
                                  `memory_cost` double DEFAULT '-1' COMMENT '内存消耗',
                                  `comments` varchar(255) DEFAULT '' COMMENT '备注',
                                  `codes` text NOT NULL COMMENT '代码',
                                  `competition_id` bigint DEFAULT NULL COMMENT '竞赛id',
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `submit_records_id_uindex` (`id`),
                                  KEY `submit_records_question_id_user_id_index` (`question_id`,`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='提交记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `testcase_question`
--

DROP TABLE IF EXISTS `testcase_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `testcase_question` (
                                     `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                     `question_id` bigint DEFAULT NULL COMMENT '题目ID',
                                     `testcase` text COMMENT '测试用例',
                                     `result` text COMMENT '测试用例结果',
                                     `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
                                     `creator` bigint NOT NULL COMMENT '创建者',
                                     `creator_name` varchar(20) NOT NULL COMMENT '创建者姓名',
                                     `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                     `modifier_name` varchar(255) NOT NULL DEFAULT '' COMMENT '修改人姓名',
                                     `modifier` bigint NOT NULL DEFAULT '0' COMMENT '修改人id',
                                     `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     `delete_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '删除时间',
                                     PRIMARY KEY (`id`),
                                     UNIQUE KEY `testcase_question_id_uindex` (`id`),
                                     KEY `testcase_question_question_id_index` (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='题目用例';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_base`
--

DROP TABLE IF EXISTS `user_base`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_base` (
                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                             `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除',
                             `NAME` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名字',
                             `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `delete_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '删除时间',
                             `mail` varchar(255) DEFAULT NULL COMMENT '邮箱',
                             `phone_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '电话号',
                             `score` int DEFAULT '0' COMMENT '分数',
                             `photo_hash` text COMMENT '头像hash',
                             `authority` tinyint DEFAULT NULL COMMENT '权限',
                             `password` varchar(128) DEFAULT NULL COMMENT '密码',
                             `ranking` int DEFAULT '0' COMMENT '排名',
                             `solved_number` int DEFAULT '0' COMMENT '解决问题数量',
                             `easy_number` int DEFAULT '0' COMMENT '简单题数量',
                             `medium_number` int DEFAULT '0' COMMENT '中等题数量',
                             `hard_number` int DEFAULT '0' COMMENT '困难题数量',
                             `profile_photo` text COMMENT '头像',
                             `intro` varchar(400) DEFAULT 'This is an self introduction editing by yourself.' COMMENT '自我介绍',
                             PRIMARY KEY (`id`) USING BTREE,
                             UNIQUE KEY `phone_number` (`phone_number`) USING BTREE,
                             KEY `user_id_index` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_competition`
--

DROP TABLE IF EXISTS `user_competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_competition` (
                                    `user_id` bigint DEFAULT NULL COMMENT '用户ID',
                                    `competition_id` bigint DEFAULT NULL COMMENT '比赛ID',
                                    `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
                                    `penalty` int NOT NULL DEFAULT 0 COMMENT '罚时',
                                    `wrong` int NOT NULL DEFAULT 0 COMMENT '错误次数',
                                    `score` int NOT NULL DEFAULT 0 COMMENT '分数',
                                    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                    PRIMARY KEY (`id`),
                                    KEY `user_competition_index` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户-比赛映射';
/*!40101 SET character_set_client = @saved_cs_client */;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-21 22:16:12
