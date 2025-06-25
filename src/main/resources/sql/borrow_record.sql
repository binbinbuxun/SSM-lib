/*
 Navicat Premium Dump SQL

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80040 (8.0.40)
 Source Host           : localhost:3306
 Source Schema         : gyklibrary

 Target Server Type    : MySQL
 Target Server Version : 80040 (8.0.40)
 File Encoding         : 65001

 Date: 24/06/2025 23:46:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for borrow_record
-- ----------------------------
DROP TABLE IF EXISTS `borrow_record`;
CREATE TABLE `borrow_record`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `book_id` int NOT NULL,
  `borrow_date` datetime NOT NULL,
  `due_date` datetime NOT NULL,
  `return_date` datetime NULL DEFAULT NULL,
  `status` int NOT NULL DEFAULT 0,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `renew_count` int NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `book_id`(`book_id` ASC) USING BTREE,
  CONSTRAINT `borrow_record_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `borrow_record_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrow_record
-- ----------------------------
INSERT INTO `borrow_record` VALUES (1, 2, 1, '2024-01-01 10:00:00', '2024-02-01 10:00:00', '2025-06-24 20:51:29', 1, '2024-01-01 10:00:00', '2025-06-24 20:51:28', 0);
INSERT INTO `borrow_record` VALUES (2, 2, 3, '2024-01-15 14:30:00', '2024-02-15 14:30:00', '2025-02-10 16:00:00', 1, '2024-01-15 14:30:00', '2024-02-10 16:00:00', 0);
INSERT INTO `borrow_record` VALUES (3, 3, 2, '2024-01-05 09:15:00', '2024-02-05 09:15:00', '2025-06-24 20:52:17', 1, '2024-01-05 09:15:00', '2025-06-24 20:52:16', 0);
INSERT INTO `borrow_record` VALUES (4, 3, 4, '2024-01-10 11:20:00', '2024-02-10 11:20:00', '2025-02-05 15:30:00', 1, '2024-01-10 11:20:00', '2024-02-05 15:30:00', 0);
INSERT INTO `borrow_record` VALUES (5, 3, 6, '2024-01-20 16:45:00', '2024-02-20 16:45:00', '2025-06-11 09:36:19', 2, '2024-01-20 16:45:00', '2024-01-20 16:45:00', 0);
INSERT INTO `borrow_record` VALUES (6, 4, 5, '2024-01-08 13:40:00', '2024-02-08 13:40:00', '2025-02-01 10:20:00', 1, '2024-01-08 13:40:00', '2024-02-01 10:20:00', 0);
INSERT INTO `borrow_record` VALUES (7, 4, 7, '2024-01-25 15:50:00', '2024-03-26 15:50:00', '2025-06-24 20:53:19', 1, '2024-01-25 15:50:00', '2025-06-24 20:53:19', 1);
INSERT INTO `borrow_record` VALUES (8, 5, 9, '2024-01-12 10:30:00', '2024-02-12 10:30:00', '2025-03-01 14:15:00', 1, '2024-01-12 10:30:00', '2024-02-10 14:15:00', 0);
INSERT INTO `borrow_record` VALUES (9, 5, 11, '2024-01-18 11:45:00', '2024-03-19 11:45:00', '2025-06-24 20:54:08', 1, '2024-01-18 11:45:00', '2025-06-24 20:54:08', 1);
INSERT INTO `borrow_record` VALUES (10, 6, 13, '2024-01-03 09:00:00', '2024-02-03 09:00:00', '2025-03-14 16:30:00', 1, '2024-01-03 09:00:00', '2024-01-28 16:30:00', 0);
INSERT INTO `borrow_record` VALUES (11, 6, 15, '2024-01-22 14:20:00', '2024-02-22 14:20:00', '2025-06-12 10:13:14', 1, '2024-01-22 14:20:00', '2025-06-12 10:13:14', 0);
INSERT INTO `borrow_record` VALUES (12, 6, 35, '2025-06-12 10:13:18', '2025-07-12 10:13:18', '2025-06-12 10:13:34', 1, '2025-06-12 10:13:18', '2025-06-12 10:13:33', 0);
INSERT INTO `borrow_record` VALUES (13, 6, 35, '2025-06-12 10:13:21', '2025-07-12 10:13:21', '2025-06-12 10:13:37', 1, '2025-06-12 10:13:21', '2025-06-12 10:13:36', 0);
INSERT INTO `borrow_record` VALUES (14, 6, 35, '2025-06-12 10:13:23', '2025-07-12 10:13:23', '2025-06-12 10:21:02', 1, '2025-06-12 10:13:22', '2025-06-12 10:21:01', 0);
INSERT INTO `borrow_record` VALUES (15, 6, 35, '2025-06-12 10:18:38', '2025-07-12 10:18:38', '2025-06-12 10:21:04', 1, '2025-06-12 10:18:38', '2025-06-12 10:21:04', 0);
INSERT INTO `borrow_record` VALUES (16, 6, 35, '2025-06-12 10:21:35', '2025-07-19 10:21:35', '2025-06-24 23:03:12', 1, '2025-06-12 10:21:35', '2025-06-24 23:03:11', 1);
INSERT INTO `borrow_record` VALUES (17, 2, 36, '2025-06-24 20:51:32', '2025-07-24 20:51:32', NULL, 0, '2025-06-24 20:51:32', '2025-06-24 20:51:32', 0);
INSERT INTO `borrow_record` VALUES (18, 2, 14, '2025-06-24 20:51:41', '2025-07-24 20:51:41', NULL, 0, '2025-06-24 20:51:40', '2025-06-24 20:51:40', 0);
INSERT INTO `borrow_record` VALUES (19, 2, 24, '2025-06-24 20:51:43', '2025-07-24 20:51:43', NULL, 0, '2025-06-24 20:51:43', '2025-06-24 20:51:43', 0);
INSERT INTO `borrow_record` VALUES (20, 3, 8, '2025-06-24 20:52:26', '2025-07-24 20:52:26', NULL, 0, '2025-06-24 20:52:25', '2025-06-24 20:52:25', 0);
INSERT INTO `borrow_record` VALUES (21, 3, 12, '2025-06-24 20:52:30', '2025-07-24 20:52:30', NULL, 0, '2025-06-24 20:52:29', '2025-06-24 20:52:29', 0);
INSERT INTO `borrow_record` VALUES (22, 3, 17, '2025-06-24 20:52:33', '2025-07-24 20:52:33', NULL, 0, '2025-06-24 20:52:33', '2025-06-24 20:52:33', 0);
INSERT INTO `borrow_record` VALUES (23, 4, 8, '2025-06-24 20:53:23', '2025-07-24 20:53:23', NULL, 0, '2025-06-24 20:53:23', '2025-06-24 20:53:23', 0);
INSERT INTO `borrow_record` VALUES (24, 4, 36, '2025-06-24 20:53:27', '2025-07-24 20:53:27', NULL, 0, '2025-06-24 20:53:27', '2025-06-24 20:53:27', 0);
INSERT INTO `borrow_record` VALUES (25, 4, 12, '2025-06-24 20:53:31', '2025-07-24 20:53:31', NULL, 0, '2025-06-24 20:53:31', '2025-06-24 20:53:31', 0);
INSERT INTO `borrow_record` VALUES (26, 5, 3, '2025-06-24 20:54:15', '2025-07-24 20:54:15', NULL, 0, '2025-06-24 20:54:15', '2025-06-24 20:54:15', 0);
INSERT INTO `borrow_record` VALUES (27, 5, 20, '2025-06-24 20:54:18', '2025-07-24 20:54:18', NULL, 0, '2025-06-24 20:54:18', '2025-06-24 20:54:18', 0);
INSERT INTO `borrow_record` VALUES (28, 5, 19, '2025-06-24 20:54:20', '2025-07-24 20:54:20', NULL, 0, '2025-06-24 20:54:20', '2025-06-24 20:54:20', 0);
INSERT INTO `borrow_record` VALUES (29, 6, 19, '2025-05-24 23:57:25', '2025-06-24 23:57:25', NULL, 0, '2025-06-24 20:57:24', '2025-06-24 20:57:24', 0);
INSERT INTO `borrow_record` VALUES (30, 6, 25, '2025-05-24 23:57:28', '2025-06-24 23:57:28', '2025-06-24 23:44:18', 1, '2025-05-24 20:57:27', '2025-06-24 21:06:44', 0);
INSERT INTO `borrow_record` VALUES (31, 6, 1, '2025-05-25 11:59:31', '2025-06-25 11:59:31', NULL, 0, '2025-05-25 22:59:31', '2025-06-24 22:59:31', 0);

SET FOREIGN_KEY_CHECKS = 1;
