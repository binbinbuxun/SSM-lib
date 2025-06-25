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

 Date: 24/06/2025 23:41:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `gender` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
  `role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'USER' COMMENT '角色：ADMIN-管理员，USER-普通用户',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE,
  INDEX `idx_user_status`(`status` ASC)
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123456', 25, '男', 1, 'ADMIN', '2025-06-05 10:31:04', '2025-06-05 10:31:04');
INSERT INTO `user` VALUES (2, 'zhangsan', 'abc123', 22, '男', 1, 'USER', '2025-06-05 10:31:04', '2025-06-05 11:10:43');
INSERT INTO `user` VALUES (3, 'lisi', 'password123', 28, '女', 1, 'USER', '2025-06-05 10:31:04', '2025-06-05 10:31:04');
INSERT INTO `user` VALUES (4, 'wangwu', 'test888', 30, '男', 1, 'USER', '2025-06-05 10:31:04', '2025-06-05 10:31:04');
INSERT INTO `user` VALUES (5, 'xiaohong', 'red123', 24, '女', 1, 'USER', '2025-06-05 10:31:04', '2025-06-05 10:31:04');
INSERT INTO `user` VALUES (6, 'laoda', '123456', 24, '男', 1, 'USER', '2025-06-05 10:31:04', '2025-06-24 20:02:54');

SET FOREIGN_KEY_CHECKS = 1;
