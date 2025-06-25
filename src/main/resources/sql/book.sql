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

 Date: 24/06/2025 23:45:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `book_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图书序号',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '书名',
  `author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作者',
  `publisher` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '出版社',
  `publish_date` date NULL DEFAULT NULL COMMENT '出版日期',
  `category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分类',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `stock` int NOT NULL DEFAULT 0 COMMENT '库存',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `book_number`(`book_number` ASC) USING BTREE,
  INDEX `idx_book_number`(`book_number` ASC) USING BTREE,
  INDEX `idx_name`(`name` ASC) USING BTREE,
  INDEX `idx_author`(`author` ASC) USING BTREE,
  INDEX `idx_category`(`category` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC)
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '图书信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, 'CP001', 'Java编程思想', 'Bruce Eckel', '机械工业出版社', '2007-06-01', '计算机/编程', 108.00, 5, '2025-05-29 15:19:49', '2025-06-24 22:59:31', NULL);
INSERT INTO `book` VALUES (2, 'CP002', '深入理解Java虚拟机', '周志明', '机械工业出版社', '2019-12-01', '计算机/编程', 89.00, 4, '2025-05-29 15:19:49', '2025-06-24 20:52:16', NULL);
INSERT INTO `book` VALUES (3, 'CP003', '算法导论', 'Thomas H.Cormen', '机械工业出版社', '2009-09-01', '计算机/算法', 128.00, 7, '2025-05-29 15:19:49', '2025-06-24 20:54:15', NULL);
INSERT INTO `book` VALUES (4, 'CP004', 'Spring实战', 'Craig Walls', '人民邮电出版社', '2020-03-01', '计算机/框架', 89.00, 4, '2025-05-29 15:19:49', '2025-06-12 10:15:23', NULL);
INSERT INTO `book` VALUES (5, 'CP005', 'MyBatis从入门到精通', '刘增辉', '电子工业出版社', '2019-07-01', '计算机/框架', 79.00, 2, '2025-05-29 15:19:49', '2025-06-12 10:15:25', NULL);
INSERT INTO `book` VALUES (6, 'CP006', '设计模式', 'Erich Gamma', '机械工业出版社', '2018-03-15', '计算机/设计', 98.00, 1, '2025-05-29 15:19:49', '2025-06-12 10:15:28', NULL);
INSERT INTO `book` VALUES (7, 'CP007', 'JavaScript高级程序设计', 'Nicholas C.Zakas', '人民邮电出版社', '2021-04-01', '计算机/编程', 119.00, 5, '2025-05-29 15:19:49', '2025-06-24 20:53:19', NULL);
INSERT INTO `book` VALUES (8, 'CP008', 'Python编程从入门到实践', 'Eric Matthes', '人民邮电出版社', '2020-06-15', '计算机/编程', 89.00, 3, '2025-05-29 15:19:49', '2025-06-24 20:53:23', NULL);
INSERT INTO `book` VALUES (9, 'WX001', '百年孤独', '加西亚·马尔克斯', '南海出版公司', '2011-06-01', '文学/小说', 55.00, 2, '2025-05-29 15:19:49', '2025-06-12 10:15:35', NULL);
INSERT INTO `book` VALUES (10, 'WX002', '活着', '余华', '作家出版社', '2012-08-01', '文学/小说', 45.00, 2, '2025-05-29 15:19:49', '2025-06-12 10:15:36', NULL);
INSERT INTO `book` VALUES (11, 'KH001', '三体（全集）', '刘慈欣', '重庆出版社', '2012-01-01', '科幻/小说', 168.00, 3, '2025-05-29 15:19:49', '2025-06-24 20:54:08', NULL);
INSERT INTO `book` VALUES (12, 'KP001', '人类简史', '尤瓦尔·赫拉利', '中信出版社', '2014-12-01', '科普/历史', 68.00, 6, '2025-05-29 15:19:49', '2025-06-24 20:53:31', NULL);
INSERT INTO `book` VALUES (13, 'KP002', '时间简史', '史蒂芬·霍金', '湖南科技出版社', '2010-04-01', '科普/物理', 45.00, 4, '2025-05-29 15:19:49', '2025-06-12 10:15:42', NULL);
INSERT INTO `book` VALUES (14, 'LS001', '中国历代政治得失', '钱穆', '生活·读书·新知三联书店', '2001-05-01', '历史/政治', 59.00, 7, '2025-05-29 15:19:49', '2025-06-24 20:51:40', NULL);
INSERT INTO `book` VALUES (15, 'LS002', '明朝那些事儿（全集）', '当年明月', '中国海关出版社', '2009-04-01', '历史/通俗', 288.00, 6, '2025-05-29 15:19:49', '2025-06-12 10:13:14', NULL);
INSERT INTO `book` VALUES (16, 'JJ001', '经济学原理', '曼昆', '北京大学出版社', '2015-05-01', '经济/教材', 108.00, 2, '2025-05-29 15:19:49', '2025-06-12 10:15:44', NULL);
INSERT INTO `book` VALUES (17, 'JJ002', '财务自由之路', '博多·舍费尔', '现代出版社', '2017-08-01', '经济/理财', 68.00, 0, '2025-05-29 15:19:49', '2025-06-24 20:52:33', NULL);
INSERT INTO `book` VALUES (18, 'JJ003', '营销管理', '菲利普·科特勒', '中国人民大学出版社', '2019-01-01', '经管/市场营销', 99.00, 1, '2025-05-29 15:19:49', '2025-06-12 10:15:48', NULL);
INSERT INTO `book` VALUES (19, 'WX003', '瓦尔登湖', '梭罗', '译林出版社', '2016-07-01', '文学/散文', 38.00, 0, '2025-05-29 15:19:49', '2025-06-24 20:57:24', NULL);
INSERT INTO `book` VALUES (20, 'WX004', '月亮与六便士', '毛姆', '上海译文出版社', '2017-03-01', '文学/小说', 45.00, 5, '2025-05-29 15:19:49', '2025-06-24 20:54:18', NULL);
INSERT INTO `book` VALUES (21, 'WX005', '围城', '钱钟书', '人民文学出版社', '2006-10-01', '文学/小说', 39.00, 5, '2025-05-29 15:19:49', '2025-06-12 10:15:53', NULL);
INSERT INTO `book` VALUES (22, 'CP009', '深度学习', 'Ian Goodfellow', '人民邮电出版社', '2022-01-15', '计算机/人工智能', 168.00, 4, '2025-05-29 15:19:49', '2025-06-12 10:15:55', NULL);
INSERT INTO `book` VALUES (23, 'CP010', '算法（第四版）', 'Robert Sedgewick', '人民邮电出版社', '2021-09-01', '计算机/算法', 128.00, 2, '2025-05-29 15:19:49', '2025-06-12 10:15:57', NULL);
INSERT INTO `book` VALUES (24, 'CP011', 'Redis设计与实现', '黄健宏', '机械工业出版社', '2020-08-01', '计算机/数据库', 79.00, 4, '2025-05-29 15:19:49', '2025-06-24 20:51:43', NULL);
INSERT INTO `book` VALUES (25, 'CP012', '高性能MySQL', 'Baron Schwartz', '电子工业出版社', '2021-03-01', '计算机/数据库', 128.00, 6, '2025-05-29 15:19:49', '2025-06-24 21:06:44', NULL);
INSERT INTO `book` VALUES (35, 'CP013', '均衡价格', '老大', '测试出版社', '2025-05-31', '计算机/编程', 54.00, 1, '2025-06-05 09:14:27', '2025-06-24 23:03:11', NULL);
INSERT INTO `book` VALUES (36, 'WX006', '追风筝的人', '你好', '你好出版社', '2017-03-08', '文学/小说', 34.00, 0, '2025-06-24 20:35:56', '2025-06-24 20:53:27', NULL);
INSERT INTO `book` VALUES (26, 'CP014', 'C语言程序设计', '谭浩强', '高等教育出版社', '2018-09-01', '计算机/编程', 59.00, 6, '2025-06-25 10:00:00', '2025-06-25 10:00:00', NULL);
INSERT INTO `book` VALUES (27, 'CP015', 'Go语言实战', 'William Kennedy', '人民邮电出版社', '2020-05-01', '计算机/编程', 88.00, 3, '2025-06-25 10:01:00', '2025-06-25 10:01:00', NULL);
INSERT INTO `book` VALUES (28, 'CP016', 'Effective Java', 'Joshua Bloch', '机械工业出版社', '2018-10-01', '计算机/编程', 99.00, 2, '2025-06-25 10:02:00', '2025-06-25 10:02:00', NULL);
INSERT INTO `book` VALUES (29, 'CP017', 'C++ Primer', 'Stanley B.Lippman', '电子工业出版社', '2016-08-01', '计算机/编程', 128.00, 4, '2025-06-25 10:03:00', '2025-06-25 10:03:00', NULL);
INSERT INTO `book` VALUES (30, 'CP018', '算法竞赛入门经典', '刘汝佳', '清华大学出版社', '2017-03-01', '计算机/算法', 66.00, 5, '2025-06-25 10:04:00', '2025-06-25 10:04:00', NULL);
INSERT INTO `book` VALUES (31, 'CP019', '数据结构与算法分析', 'Mark Allen Weiss', '机械工业出版社', '2019-11-01', '计算机/算法', 88.00, 3, '2025-06-25 10:05:00', '2025-06-25 10:05:00', NULL);
INSERT INTO `book` VALUES (32, 'CP020', 'Vue.js实战', '梁灏', '电子工业出版社', '2021-07-01', '计算机/框架', 79.00, 2, '2025-06-25 10:06:00', '2025-06-25 10:06:00', NULL);
INSERT INTO `book` VALUES (33, 'CP021', '深入浅出React和Redux', '程墨', '人民邮电出版社', '2022-02-01', '计算机/框架', 89.00, 3, '2025-06-25 10:07:00', '2025-06-25 10:07:00', NULL);
INSERT INTO `book` VALUES (34, 'CP022', '数据库系统概论', '王珊', '高等教育出版社', '2019-09-01', '计算机/数据库', 69.00, 5, '2025-06-25 10:08:00', '2025-06-25 10:08:00', NULL);
INSERT INTO `book` VALUES (37, 'WX007', '平凡的世界', '路遥', '人民文学出版社', '2013-01-01', '文学/小说', 58.00, 3, '2025-06-25 10:09:00', '2025-06-25 10:09:00', NULL);
INSERT INTO `book` VALUES (38, 'WX008', '红楼梦', '曹雪芹', '人民文学出版社', '2008-05-01', '文学/小说', 88.00, 2, '2025-06-25 10:10:00', '2025-06-25 10:10:00', NULL);
INSERT INTO `book` VALUES (39, 'WX009', '文化苦旅', '余秋雨', '作家出版社', '2010-10-01', '文学/散文', 36.00, 4, '2025-06-25 10:11:00', '2025-06-25 10:11:00', NULL);
INSERT INTO `book` VALUES (40, 'WX010', '目送', '龙应台', '生活·读书·新知三联书店', '2012-03-01', '文学/散文', 42.00, 2, '2025-06-25 10:12:00', '2025-06-25 10:12:00', NULL);
INSERT INTO `book` VALUES (41, 'KH002', '流浪地球', '刘慈欣', '重庆出版社', '2013-05-01', '科幻/小说', 49.00, 3, '2025-06-25 10:13:00', '2025-06-25 10:13:00', NULL);
INSERT INTO `book` VALUES (42, 'KH003', '银河帝国', '阿西莫夫', '译林出版社', '2015-09-01', '科幻/小说', 99.00, 2, '2025-06-25 10:14:00', '2025-06-25 10:14:00', NULL);
INSERT INTO `book` VALUES (43, 'KH004', '基地', '阿西莫夫', '译林出版社', '2014-06-01', '科幻/小说', 88.00, 2, '2025-06-25 10:15:00', '2025-06-25 10:15:00', NULL);
INSERT INTO `book` VALUES (44, 'KP003', '自私的基因', '理查德·道金斯', '中信出版社', '2016-11-01', '科普/历史', 59.00, 3, '2025-06-25 10:16:00', '2025-06-25 10:16:00', NULL);
INSERT INTO `book` VALUES (45, 'KP004', '万物简史', '比尔·布莱森', '接力出版社', '2012-09-01', '科普/历史', 66.00, 2, '2025-06-25 10:17:00', '2025-06-25 10:17:00', NULL);
INSERT INTO `book` VALUES (46, 'KP005', '果壳中的宇宙', '史蒂芬·霍金', '湖南科技出版社', '2011-05-01', '科普/物理', 55.00, 2, '2025-06-25 10:18:00', '2025-06-25 10:18:00', NULL);
INSERT INTO `book` VALUES (47, 'KP006', '大设计', '史蒂芬·霍金', '湖南科技出版社', '2012-08-01', '科普/物理', 49.00, 2, '2025-06-25 10:19:00', '2025-06-25 10:19:00', NULL);
INSERT INTO `book` VALUES (48, 'LS003', '全球通史', '斯塔夫里阿诺斯', '北京大学出版社', '2010-01-01', '历史/政治', 88.00, 2, '2025-06-25 10:20:00', '2025-06-25 10:20:00', NULL);
INSERT INTO `book` VALUES (49, 'LS004', '世界简史', 'H.G.威尔斯', '商务印书馆', '2009-06-01', '历史/政治', 66.00, 2, '2025-06-25 10:21:00', '2025-06-25 10:21:00', NULL);
INSERT INTO `book` VALUES (50, 'LS005', '大秦帝国', '孙皓晖', '长江文艺出版社', '2011-10-01', '历史/通俗', 99.00, 2, '2025-06-25 10:22:00', '2025-06-25 10:22:00', NULL);
INSERT INTO `book` VALUES (51, 'LS006', '资治通鉴', '司马光', '中华书局', '2008-03-01', '历史/通俗', 128.00, 2, '2025-06-25 10:23:00', '2025-06-25 10:23:00', NULL);
INSERT INTO `book` VALUES (52, 'JJ004', '货币金融学', '弗雷德里克·米什金', '中国人民大学出版社', '2014-09-01', '经济/教材', 88.00, 2, '2025-06-25 10:24:00', '2025-06-25 10:24:00', NULL);
INSERT INTO `book` VALUES (53, 'JJ005', '穷爸爸富爸爸', '罗伯特·清崎', '南海出版公司', '2013-05-01', '经济/理财', 39.00, 2, '2025-06-25 10:25:00', '2025-06-25 10:25:00', NULL);
INSERT INTO `book` VALUES (54, 'JJ006', '管理学', '斯蒂芬·罗宾斯', '中国人民大学出版社', '2015-07-01', '经管/市场营销', 79.00, 2, '2025-06-25 10:26:00', '2025-06-25 10:26:00', NULL);


SET FOREIGN_KEY_CHECKS = 1;
