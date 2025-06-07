DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `username` VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    `password` VARCHAR(100) NOT NULL COMMENT '密码',
    `age` INT COMMENT '年龄',
    `gender` VARCHAR(10) COMMENT '性别',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    `role` VARCHAR(20) NOT NULL DEFAULT 'USER' COMMENT '角色：ADMIN-管理员，USER-普通用户',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

-- 插入示例数据
INSERT INTO `user` (`username`, `password`, `age`, `gender`, `status`, `role`) VALUES
('admin', '123456', 25, '男', 1, 'ADMIN'),
('zhangsan', 'abc123', 22, '男', 1, 'USER'),
('lisi', 'password123', 28, '女', 1, 'USER'),
('wangwu', 'test888', 30, '男', 1, 'USER'),
('xiaohong', 'red123', 24, '女', 1, 'USER'),
('laoda', '123456', 38, '男', 1, 'USER');
