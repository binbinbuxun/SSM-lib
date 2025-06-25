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

 Date: 25/06/2025 15:35:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `from_user_id` int NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'SYSTEM',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `read_flag` tinyint NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (10, 1, NULL, '消息推送测试', 'ADMIN', '2025-06-25 15:12:18', 0);
INSERT INTO `message` VALUES (11, 2, NULL, '消息推送测试', 'ADMIN', '2025-06-25 15:12:18', 0);
INSERT INTO `message` VALUES (12, 3, NULL, '消息推送测试', 'ADMIN', '2025-06-25 15:12:18', 0);
INSERT INTO `message` VALUES (13, 4, NULL, '消息推送测试', 'ADMIN', '2025-06-25 15:12:18', 0);
INSERT INTO `message` VALUES (14, 5, NULL, '消息推送测试', 'ADMIN', '2025-06-25 15:12:18', 0);
INSERT INTO `message` VALUES (15, 6, NULL, '消息推送测试', 'ADMIN', '2025-06-25 15:12:18', 0);
INSERT INTO `message` VALUES (16, 1, 6, '管理员 消息推送测试', 'USER_TO_ADMIN', '2025-06-25 15:15:42', 0);

SET FOREIGN_KEY_CHECKS = 1;
