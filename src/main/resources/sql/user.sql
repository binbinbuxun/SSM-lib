-- 创建数据库
CREATE DATABASE IF NOT EXISTS gyklibrary DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- 使用数据库
USE library;

-- 创建用户表
CREATE TABLE `user` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `username` VARCHAR(50) NOT NULL COMMENT '用户名',
    `password` VARCHAR(100) NOT NULL COMMENT '密码',
    `age` INT COMMENT '年龄',
    `sex` VARCHAR(10) COMMENT '性别',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

-- 添加索引
CREATE INDEX idx_username ON `user` (`username`);

-- 插入测试数据
INSERT INTO `user` (`username`, `password`, `age`, `sex`, `create_time`, `update_time`) VALUES
('admin', '123456', 25, '男', '2025-05-29 10:00:00', '2025-05-29 10:00:00'),
('zhangsan', 'abc123', 22, '男', '2025-05-29 10:15:00', '2025-05-29 10:15:00'),
('lisi', 'password123', 28, '女', '2025-05-29 11:00:00', '2025-05-29 11:00:00'),
('wangwu', 'test888', 30, '男', '2025-05-29 11:30:00', '2025-05-29 11:30:00'),
('xiaohong', 'red123', 24, '女', '2025-05-29 12:00:00', '2025-05-29 12:00:00');
