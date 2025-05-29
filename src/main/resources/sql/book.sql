-- 创建图书表
CREATE TABLE `book` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL COMMENT '书名',
    `author` VARCHAR(50) NOT NULL COMMENT '作者',
    `publisher` VARCHAR(100) COMMENT '出版社',
    `category` VARCHAR(50) COMMENT '分类',
    `price` DECIMAL(10,2) NOT NULL COMMENT '价格',
    `stock` INT NOT NULL DEFAULT 0 COMMENT '库存',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='图书信息表';

-- 添加索引
CREATE INDEX idx_name ON `book` (`name`);
CREATE INDEX idx_author ON `book` (`author`);
CREATE INDEX idx_category ON `book` (`category`);

-- 插入测试数据
INSERT INTO `book` (`name`, `author`, `publisher`, `category`, `price`, `stock`) VALUES
('Java编程思想', 'Bruce Eckel', '机械工业出版社', '计算机/编程', 108.00, 10),
('深入理解Java虚拟机', '周志明', '机械工业出版社', '计算机/编程', 89.00, 15),
('算法导论', 'Thomas H.Cormen', '机械工业出版社', '计算机/算法', 128.00, 8),
('Spring实战', 'Craig Walls', '人民邮电出版社', '计算机/框架', 89.00, 12),
('MyBatis从入门到精通', '刘增辉', '电子工业出版社', '计算机/框架', 79.00, 20);
