/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.24 : Database - service_rpc_kafka
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`service_rpc_kafka` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `service_rpc_kafka`;

/*Table structure for table `mall_mq_log` */

DROP TABLE IF EXISTS `mall_mq_log`;

CREATE TABLE `mall_mq_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `topic` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '主题',
  `mq_key` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '消息键',
  `mq_value` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '消息内容',
  `resend_times` smallint(6) DEFAULT NULL COMMENT '重发次数',
  `mq_status` tinyint(4) DEFAULT NULL COMMENT '状态,1:未完成处理,2:已完成处理',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `op_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最后操作时间',
  PRIMARY KEY (`id`),
  KEY `idx_topic` (`topic`)
) ENGINE=InnoDB AUTO_INCREMENT=8028 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='消息处理失败记录表';

/*Data for the table `mall_mq_log` */

insert  into `mall_mq_log`(`id`,`topic`,`mq_key`,`mq_value`,`resend_times`,`mq_status`,`create_time`,`op_time`) values (8027,'test','','{\"age\":18,\"name\":\"yangzheng\"}',7,1,'2020-06-03 16:32:14','2020-06-03 17:18:45');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
