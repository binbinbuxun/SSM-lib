-- 创建图书表
CREATE TABLE `book` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `book_number` VARCHAR(20) UNIQUE COMMENT '图书序号',
    `name` VARCHAR(100) NOT NULL COMMENT '书名',
    `author` VARCHAR(50) NOT NULL COMMENT '作者',
    `publisher` VARCHAR(100) COMMENT '出版社',
    `publish_date` DATE COMMENT '出版日期',
    `category` VARCHAR(50) COMMENT '分类',
    `price` DECIMAL(10,2) NOT NULL COMMENT '价格',
    `stock` INT NOT NULL DEFAULT 0 COMMENT '库存',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='图书信息表';

-- 添加索引
CREATE INDEX idx_book_number ON `book` (`book_number`);
CREATE INDEX idx_name ON `book` (`name`);
CREATE INDEX idx_author ON `book` (`author`);
CREATE INDEX idx_category ON `book` (`category`);

-- 插入测试数据
INSERT INTO `book` (`book_number`, `name`, `author`, `publisher`, `publish_date`, `category`, `price`, `stock`) VALUES
('CP001', 'Java编程思想', 'Bruce Eckel', '机械工业出版社', '2007-06-01', '计算机/编程', 108.00, 10),
('CP002', '深入理解Java虚拟机', '周志明', '机械工业出版社', '2019-12-01', '计算机/编程', 89.00, 15),
('CP003', '算法导论', 'Thomas H.Cormen', '机械工业出版社', '2009-09-01', '计算机/算法', 128.00, 8),
('CP004', 'Spring实战', 'Craig Walls', '人民邮电出版社', '2020-03-01', '计算机/框架', 89.00, 12),
('CP005', 'MyBatis从入门到精通', '刘增辉', '电子工业出版社', '2019-07-01', '计算机/框架', 79.00, 20),
('CP006', '设计模式', 'Erich Gamma', '机械工业出版社', '2018-03-15', '计算机/设计', 98.00, 10),
('CP007', 'JavaScript高级程序设计', 'Nicholas C.Zakas', '人民邮电出版社', '2021-04-01', '计算机/编程', 119.00, 8),
('CP008', 'Python编程从入门到实践', 'Eric Matthes', '人民邮电出版社', '2020-06-15', '计算机/编程', 89.00, 15),
('WX001', '百年孤独', '加西亚·马尔克斯', '南海出版公司', '2011-06-01', '文学/小说', 55.00, 20),
('WX002', '活着', '余华', '作家出版社', '2012-08-01', '文学/小说', 45.00, 25),
('KH001', '三体（全集）', '刘慈欣', '重庆出版社', '2012-01-01', '科幻/小说', 168.00, 12),
('KP001', '人类简史', '尤瓦尔·赫拉利', '中信出版社', '2014-12-01', '科普/历史', 68.00, 18),
('KP002', '时间简史', '史蒂芬·霍金', '湖南科技出版社', '2010-04-01', '科普/物理', 45.00, 14),
('LS001', '中国历代政治得失', '钱穆', '生活·读书·新知三联书店', '2001-05-01', '历史/政治', 59.00, 8),
('LS002', '明朝那些事儿（全集）', '当年明月', '中国海关出版社', '2009-04-01', '历史/通俗', 288.00, 5),
('JJ001', '经济学原理', '曼昆', '北京大学出版社', '2015-05-01', '经济/教材', 108.00, 20),
('JJ002', '财务自由之路', '博多·舍费尔', '现代出版社', '2017-08-01', '经济/理财', 68.00, 15),
('JJ003', '营销管理', '菲利普·科特勒', '中国人民大学出版社', '2019-01-01', '经管/市场营销', 99.00, 10),
('WX003', '瓦尔登湖', '梭罗', '译林出版社', '2016-07-01', '文学/散文', 38.00, 22),
('WX004', '月亮与六便士', '毛姆', '上海译文出版社', '2017-03-01', '文学/小说', 45.00, 16),
('WX005', '围城', '钱钟书', '人民文学出版社', '2006-10-01', '文学/小说', 39.00, 25),
('CP009', '深度学习', 'Ian Goodfellow', '人民邮电出版社', '2022-01-15', '计算机/人工智能', 168.00, 8),
('CP010', '算法（第四版）', 'Robert Sedgewick', '人民邮电出版社', '2021-09-01', '计算机/算法', 128.00, 12),
('CP011', 'Redis设计与实现', '黄健宏', '机械工业出版社', '2020-08-01', '计算机/数据库', 79.00, 15),
('CP012', '高性能MySQL', 'Baron Schwartz', '电子工业出版社', '2021-03-01', '计算机/数据库', 128.00, 10);
