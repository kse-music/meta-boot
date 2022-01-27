CREATE TABLE `tb_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `nickname` varchar(128) NOT NULL,
  `email` varchar(128) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL COMMENT '1:男，2:女',
  `company` varchar(128) DEFAULT NULL,
  `status` char(1) DEFAULT '1' NOT NULL COMMENT '0:待审核,1:正常,2:禁用,3:删除',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_general_ci;
