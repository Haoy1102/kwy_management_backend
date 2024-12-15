-- MySQL dump 10.13  Distrib 8.0.36, for macos14 (arm64)
--
-- Host: 117.72.115.108    Database: kwy
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `kwy`
--

CREATE DATABASE IF NOT EXISTS `kwy` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE `kwy`;

--
-- Table structure for table `tb_customer`
--

DROP TABLE IF EXISTS `tb_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_customer` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `customer` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '客户',
  `people` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '联系人',
  `phone_standby` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '备用联系人',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '联系电话',
  `address` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '默认地址',
  `note` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  `version` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10031 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='货源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_customer`
--

LOCK TABLES `tb_customer` WRITE;
/*!40000 ALTER TABLE `tb_customer` DISABLE KEYS */;
INSERT INTO `tb_customer` VALUES (10000,'公司1','王红','12345667899','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','xxxxx','2023-07-22 19:51:03','2023-07-22 19:51:03','超级管理员','超级管理员',0,1);
INSERT INTO `tb_customer` VALUES (10001,'公司2','李辉','12345667899','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','xxxx','2023-07-22 19:53:23','2023-07-22 19:53:23','超级管理员','超级管理员',0,1);
INSERT INTO `tb_customer` VALUES (10002,'公司3','张伟','12345667899','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','xxxx','2023-07-22 19:54:15','2023-07-22 19:54:15','超级管理员','超级管理员',0,1);
INSERT INTO `tb_customer` VALUES (10011,'好滋味','汤剑乔','','78911124454','河南省郑州市新郑市郭店镇紫荆山南路与轻工路交叉口北340米路西（泰德能源）','','2023-07-26 15:50:25','2023-07-26 15:50:25','超级管理员','超级管理员',0,1);
INSERT INTO `tb_customer` VALUES (10012,'泽洋食品   ','王俊祥 ','','78911124454','新郑市龙湖镇大赵村','','2023-07-26 15:51:04','2023-07-26 15:51:04','超级管理员','超级管理员',0,1);
INSERT INTO `tb_customer` VALUES (10013,'好家旺食品','潘经理 ','','78911124454','新郑市北靳楼村工业园区','','2023-07-26 15:51:52','2023-07-26 15:51:52','超级管理员','超级管理员',0,1);
INSERT INTO `tb_customer` VALUES (10014,'米立方','张佳佳  ','','78911124454','河南省鹤壁市淇县北阳镇工业园区','','2023-07-26 15:52:18','2023-07-26 15:52:18','超级管理员','超级管理员',0,1);
INSERT INTO `tb_customer` VALUES (10015,'福源鑫食品','牛树民  ','','78911124454','开封市尉氏县洧川镇四合村','','2023-07-26 15:52:51','2023-07-26 15:52:51','超级管理员','超级管理员',0,1);
INSERT INTO `tb_customer` VALUES (10016,'新密联楹 ','冯亚慧 ','','78911124454','新密大槐镇政府旁边','','2023-07-26 15:53:15','2023-07-26 15:53:15','超级管理员','超级管理员',0,1);
/*!40000 ALTER TABLE `tb_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_employee`
--

DROP TABLE IF EXISTS `tb_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_employee` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '姓名',
  `username` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '手机号',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态 0:禁用，1:正常',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '修改人',
  `version` int NOT NULL DEFAULT '1',
  `deleted` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10004 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='员工信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_employee`
--

LOCK TABLES `tb_employee` WRITE;
/*!40000 ALTER TABLE `tb_employee` DISABLE KEYS */;
INSERT INTO `tb_employee` VALUES (10001,'超级管理员','admin','21232f297a57a5a743894a0e4a801fc3','12345667899',2,'2023-07-13 16:20:40','2023-07-13 16:20:40',NULL,NULL,1,0);
INSERT INTO `tb_employee` VALUES (10002,'公司帐号-1','test1','e10adc3949ba59abbe56e057f20f883e','12345667899',1,'2023-07-22 21:25:47','2023-07-22 21:25:47','超级管理员','超级管理员',1,0);
INSERT INTO `tb_employee` VALUES (10003,'工厂帐号-1','test-2','14e1b600b1fd579f47433b88e8d85291','12345667899',0,'2023-07-22 21:26:15','2023-07-22 21:26:28','超级管理员','超级管理员',2,0);
/*!40000 ALTER TABLE `tb_employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_goods`
--

DROP TABLE IF EXISTS `tb_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_goods` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_id` bigint NOT NULL DEFAULT '0' COMMENT '货号',
  `category_id` bigint NOT NULL,
  `category` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '品类',
  `material_info_id` bigint DEFAULT NULL COMMENT '货品ID',
  `supplier` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '货源',
  `number` int NOT NULL DEFAULT '0' COMMENT '余量',
  `location` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '仓储位置',
  `produced_date` date DEFAULT NULL COMMENT '生产日期',
  `status` int NOT NULL DEFAULT '1' COMMENT '货物状态 1新鲜，2临期，3尽快使用',
  `note` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  `version` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10007 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='货源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_goods`
--

LOCK TABLES `tb_goods` WRITE;
/*!40000 ALTER TABLE `tb_goods` DISABLE KEYS */;
INSERT INTO `tb_goods` VALUES (10000,10000,10000,'玉米淀粉',NULL,NULL,200,'东仓','2023-07-11',1,'一条备注','2023-07-22 19:01:13','2023-07-22 19:01:13','超级管理员','超级管理员',0,1);
INSERT INTO `tb_goods` VALUES (10001,10001,10001,'食盐',NULL,NULL,500,'西厂','2023-07-09',2,'','2023-07-22 19:02:18','2023-07-22 19:02:35','超级管理员','超级管理员',0,2);
INSERT INTO `tb_goods` VALUES (10002,10002,10003,'辣椒',10000,'供货商1',0,'',NULL,1,'','2023-07-22 19:04:01','2023-07-22 19:35:13','超级管理员','超级管理员',1,4);
INSERT INTO `tb_goods` VALUES (10003,10003,10003,'辣椒',10002,'供货商2',100,'东厂',NULL,1,'','2023-07-22 19:05:51','2023-07-22 19:16:36','超级管理员','超级管理员',0,5);
INSERT INTO `tb_goods` VALUES (10004,10004,10003,'辣椒',10000,'供货商1',500,'东厂','2023-07-09',1,'','2023-07-22 19:42:43','2023-07-22 19:42:43','超级管理员','超级管理员',0,1);
INSERT INTO `tb_goods` VALUES (10005,10005,10003,'辣椒',10000,'供货商1',0,'','2024-12-11',1,'','2024-12-15 18:29:43','2024-12-15 18:29:43','超级管理员','超级管理员',0,1);
INSERT INTO `tb_goods` VALUES (10006,10006,10003,'辣椒',10000,'供货商1',7,'',NULL,1,'','2024-12-15 18:31:39','2024-12-15 18:32:23','超级管理员','超级管理员',0,2);
/*!40000 ALTER TABLE `tb_goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_goods_record`
--

DROP TABLE IF EXISTS `tb_goods_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_goods_record` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_id` bigint NOT NULL COMMENT '货号',
  `category_id` bigint NOT NULL,
  `category` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '品类',
  `material_info_id` bigint DEFAULT NULL,
  `supplier` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '货源',
  `operate_type` int NOT NULL COMMENT '1采购 2出库 3手动录入 4手动调整 5删除',
  `operate_number` int NOT NULL DEFAULT '0' COMMENT '操作数量',
  `remain_number` int NOT NULL DEFAULT '0' COMMENT '本批余量',
  `origin_number` int NOT NULL DEFAULT '0' COMMENT '原来的数量',
  `produced_date` date DEFAULT NULL COMMENT '生产日期',
  `location` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '仓储位置',
  `status` int DEFAULT '1' COMMENT '货物状态 1新鲜，2临期，3尽快使用',
  `note` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  `version` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10013 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='货源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_goods_record`
--

LOCK TABLES `tb_goods_record` WRITE;
/*!40000 ALTER TABLE `tb_goods_record` DISABLE KEYS */;
INSERT INTO `tb_goods_record` VALUES (10000,10000,10000,'玉米淀粉',NULL,NULL,3,200,200,0,'2023-07-11','东仓',1,'一条备注','2023-07-22 19:01:14','2023-07-22 19:01:14','超级管理员','超级管理员',0,1);
INSERT INTO `tb_goods_record` VALUES (10001,10001,10001,'食盐',NULL,NULL,3,500,500,0,'2023-07-09','西厂',1,'','2023-07-22 19:02:18','2023-07-22 19:02:18','超级管理员','超级管理员',0,1);
INSERT INTO `tb_goods_record` VALUES (10002,10001,10001,'食盐',NULL,NULL,4,0,500,500,'2023-07-09','西厂',2,'','2023-07-22 19:02:35','2023-07-22 19:02:35','超级管理员','超级管理员',0,1);
INSERT INTO `tb_goods_record` VALUES (10003,10002,10003,'辣椒',10000,'供货商1',1,500,500,0,NULL,'',1,'测试备注','2023-07-22 19:04:01','2023-07-22 19:10:48','超级管理员','超级管理员',0,2);
INSERT INTO `tb_goods_record` VALUES (10004,10003,10003,'辣椒',10002,'供货商2',1,100,100,0,NULL,'东厂',1,'','2023-07-22 19:05:51','2023-07-22 19:05:51','超级管理员','超级管理员',0,1);
INSERT INTO `tb_goods_record` VALUES (10005,10002,10003,'辣椒',10000,'供货商1',4,-100,400,500,NULL,'',1,'','2023-07-22 19:34:19','2023-07-22 19:34:19','超级管理员','超级管理员',0,1);
INSERT INTO `tb_goods_record` VALUES (10006,10002,10003,'辣椒',10000,'供货商1',2,200,200,400,NULL,'',1,'生产xx','2023-07-22 19:34:53','2023-07-22 19:34:53','超级管理员','超级管理员',0,1);
INSERT INTO `tb_goods_record` VALUES (10007,10002,10003,'辣椒',10000,'供货商1',2,200,0,200,NULL,'',1,'生产xx','2023-07-22 19:35:12','2023-07-22 19:35:12','超级管理员','超级管理员',0,1);
INSERT INTO `tb_goods_record` VALUES (10008,10002,10003,'辣椒',10000,'供货商1',5,0,0,0,NULL,'',1,'','2023-07-22 19:35:16','2023-07-22 19:35:16','超级管理员','超级管理员',0,4);
INSERT INTO `tb_goods_record` VALUES (10009,10004,10003,'辣椒',10000,'供货商1',1,500,500,0,'2023-07-09','东厂',1,'','2023-07-22 19:42:43','2023-07-22 19:42:43','超级管理员','超级管理员',0,1);
INSERT INTO `tb_goods_record` VALUES (10010,10005,10003,'辣椒',10000,'供货商1',1,0,0,0,'2024-12-11','',1,'','2024-12-15 18:29:43','2024-12-15 18:29:43','超级管理员','超级管理员',0,1);
INSERT INTO `tb_goods_record` VALUES (10011,10006,10003,'辣椒',10000,'供货商1',1,10,10,0,NULL,'',1,'','2024-12-15 18:31:40','2024-12-15 18:31:40','超级管理员','超级管理员',0,1);
INSERT INTO `tb_goods_record` VALUES (10012,10006,10003,'辣椒',10000,'供货商1',2,3,7,10,NULL,'',1,'生产xxx产品','2024-12-15 18:32:23','2024-12-15 18:32:23','超级管理员','超级管理员',0,1);
/*!40000 ALTER TABLE `tb_goods_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_material_info`
--

DROP TABLE IF EXISTS `tb_material_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_material_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` bigint NOT NULL,
  `category` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '品类',
  `supplier` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '货源',
  `default_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '默认单价',
  `latest_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `note` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  `version` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10003 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='货源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_material_info`
--

LOCK TABLES `tb_material_info` WRITE;
/*!40000 ALTER TABLE `tb_material_info` DISABLE KEYS */;
INSERT INTO `tb_material_info` VALUES (10000,10003,'辣椒','供货商1',10.00,10.00,'','2023-07-22 19:03:16','2024-12-15 18:31:40','超级管理员','超级管理员',0,5);
INSERT INTO `tb_material_info` VALUES (10001,10000,'玉米淀粉','供货商2',2.50,0.00,'','2023-07-22 19:04:30','2023-07-22 19:11:37','超级管理员','超级管理员',0,3);
INSERT INTO `tb_material_info` VALUES (10002,10003,'辣椒','供货商2',11.00,11.00,'','2023-07-22 19:05:17','2023-07-22 19:05:51','超级管理员','超级管理员',0,2);
/*!40000 ALTER TABLE `tb_material_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_material_overview`
--

DROP TABLE IF EXISTS `tb_material_overview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_material_overview` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '品类',
  `number` int NOT NULL DEFAULT '0' COMMENT '总量',
  `update_date` date DEFAULT NULL COMMENT '最近入库日期',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '默认单价',
  `value` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '估值',
  `number_tag` int NOT NULL DEFAULT '1' COMMENT '1正常 2偏少 3及时补货',
  `note` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `version` int NOT NULL DEFAULT '1',
  `deleted` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10005 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='货源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_material_overview`
--

LOCK TABLES `tb_material_overview` WRITE;
/*!40000 ALTER TABLE `tb_material_overview` DISABLE KEYS */;
INSERT INTO `tb_material_overview` VALUES (10000,'玉米淀粉',200,'2023-07-22',2.00,400.00,1,'','2023-07-22 18:57:40','2023-07-22 19:01:14','超级管理员','超级管理员',2,0);
INSERT INTO `tb_material_overview` VALUES (10001,'食盐',500,'2023-07-22',1.00,500.00,1,'','2023-07-22 18:58:05','2023-07-22 19:02:18','超级管理员','超级管理员',2,0);
INSERT INTO `tb_material_overview` VALUES (10003,'辣椒',607,'2024-12-15',10.00,6070.00,1,'','2023-07-22 18:59:17','2024-12-15 18:32:23','超级管理员','超级管理员',15,0);
INSERT INTO `tb_material_overview` VALUES (10004,'蔗糖',0,NULL,5.00,0.00,1,'','2023-07-22 18:59:30','2023-07-22 18:59:44','超级管理员','超级管理员',2,0);
/*!40000 ALTER TABLE `tb_material_overview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_order`
--

DROP TABLE IF EXISTS `tb_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_order` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '订单ID',
  `customer_id` bigint NOT NULL,
  `customer` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '客户',
  `people` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '联系人',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '联系电话',
  `address` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '地址',
  `create_date` date DEFAULT NULL COMMENT '下单日期',
  `status` int NOT NULL DEFAULT '1' COMMENT '订单状态 1未制作 2制作中 3部分交付 4待回款 5完成 6返厂 7作废',
  `amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '订单金额',
  `total_delivered` decimal(10,2) NOT NULL DEFAULT '0.00',
  `delivery_progress` int NOT NULL DEFAULT '0' COMMENT '交付进度',
  `total_payment` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '总回款金额',
  `note` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `content` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '订单内容',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `version` int NOT NULL DEFAULT '1',
  `deleted` int NOT NULL DEFAULT '0' COMMENT '0未删除 1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10014 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='货源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_order`
--

LOCK TABLES `tb_order` WRITE;
/*!40000 ALTER TABLE `tb_order` DISABLE KEYS */;
INSERT INTO `tb_order` VALUES (10000,'20230722190410000N2Q',10000,'公司1','王红','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','2023-07-18',5,6900.00,6900.00,100,6900.00,'xxxx','','2023-07-22 19:59:41','2023-07-22 20:09:08','超级管理员','超级管理员',7,0);
INSERT INTO `tb_order` VALUES (10001,'2023072220R110001VZ9',10000,'公司1','王红','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','2023-07-22',3,20400.00,13800.00,67,0.00,'','','2023-07-22 20:04:19','2023-07-22 20:08:05','超级管理员','超级管理员',3,0);
INSERT INTO `tb_order` VALUES (10002,'20230722203Q1000237E',10000,'公司1','王红','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','2023-07-01',2,9200.00,0.00,0,0.00,'','','2023-07-22 20:07:49','2023-07-22 20:08:20','超级管理员','超级管理员',2,0);
INSERT INTO `tb_order` VALUES (10003,'2023072220PN10003N0Z',10000,'公司1','王红','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','2023-07-26',6,0.00,0.00,0,0.00,'','','2023-07-22 20:10:16','2023-07-22 21:19:24','超级管理员','超级管理员',6,0);
INSERT INTO `tb_order` VALUES (10004,'2023072221JI10004762',10000,'公司1','王红','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','2023-07-18',4,4600.00,4600.00,100,2000.00,'','','2023-07-22 21:10:15','2023-07-22 22:55:03','超级管理员','超级管理员',4,0);
INSERT INTO `tb_order` VALUES (10005,'20230722218T100054JR',10000,'公司1','王红','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','2023-07-11',1,7000.00,0.00,0,0.00,'','','2023-07-22 21:11:27','2023-07-22 21:11:27','超级管理员','超级管理员',1,0);
INSERT INTO `tb_order` VALUES (10006,'2023072221YG10006Q8G',10000,'公司1','王红','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','2023-07-11',7,2300.00,0.00,0,0.00,'','','2023-07-22 21:11:53','2023-07-22 21:12:01','超级管理员','超级管理员',2,0);
INSERT INTO `tb_order` VALUES (10007,'2023072221E6100075PC',10001,'公司2','李辉','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','2023-07-01',1,4600.00,0.00,0,0.00,'','','2023-07-22 21:18:05','2023-07-22 21:18:05','超级管理员','超级管理员',1,0);
INSERT INTO `tb_order` VALUES (10010,'20230726156410010XYT',10003,'小陈美盛','孙总','18638287628','小陈乡政府南边','2023-07-25',1,0.00,0.00,0,0.00,'','','2023-07-26 15:35:30','2023-07-26 15:35:30','超级管理员','超级管理员',1,0);
INSERT INTO `tb_order` VALUES (10011,'2024121521U810011N0S',10000,'公司1','王红','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','2024-12-15',5,4600.00,4600.00,100,4600.00,'','','2024-12-15 21:03:28','2024-12-16 21:02:22','超级管理员','超级管理员',7,0);
INSERT INTO `tb_order` VALUES (10012,'20241216215010012JOF',10000,'公司1','王红','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','2024-12-16',4,6800.00,6800.00,100,0.00,'','','2024-12-16 21:03:26','2024-12-16 21:23:08','超级管理员','超级管理员',5,0);
INSERT INTO `tb_order` VALUES (10013,'2024121621WE10013ZJ8',10000,'公司1','王红','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','2024-12-16',2,9700.00,0.00,0,0.00,'','','2024-12-16 21:11:00','2024-12-16 21:11:11','超级管理员','超级管理员',2,0);
/*!40000 ALTER TABLE `tb_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_order_detail`
--

DROP TABLE IF EXISTS `tb_order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_order_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `product_id` bigint NOT NULL,
  `product_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '产品名称',
  `product_code` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '产品编号',
  `package_number` int NOT NULL COMMENT '件数',
  `unit` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '单位',
  `number` int NOT NULL DEFAULT '0' COMMENT '数量',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '单价',
  `amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '金额',
  `is_delivered` int NOT NULL DEFAULT '0' COMMENT '是否发货 0未发货 1发货 默认0',
  `delivered_date` date DEFAULT NULL COMMENT '发货日期',
  `note` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  `version` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10022 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='订单明细表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_order_detail`
--

LOCK TABLES `tb_order_detail` WRITE;
/*!40000 ALTER TABLE `tb_order_detail` DISABLE KEYS */;
INSERT INTO `tb_order_detail` VALUES (10000,'20230722190410000N2Q',10000,'乳酸菌风味调味料','L5045',5,'kg',100,23.00,2300.00,1,'2023-07-22','xxxxx','2023-07-22 19:59:41','2023-07-22 20:02:14','超级管理员','超级管理员',0,3);
INSERT INTO `tb_order_detail` VALUES (10001,'20230722190410000N2Q',10002,'苹果风味调味料','L50383',10,'kg',200,23.00,4600.00,1,'2023-07-22','','2023-07-22 19:59:41','2023-07-22 20:02:14','超级管理员','超级管理员',0,2);
INSERT INTO `tb_order_detail` VALUES (10002,'2023072220R110001VZ9',10004,'荔枝风味调味料','L5043-1',15,'kg',300,22.00,6600.00,0,NULL,'','2023-07-22 20:04:19','2023-07-22 20:04:19','超级管理员','超级管理员',0,1);
INSERT INTO `tb_order_detail` VALUES (10003,'2023072220R110001VZ9',10003,'葡萄风味调味料','L5042',15,'kg',300,23.00,6900.00,1,'2023-07-22','','2023-07-22 20:04:19','2023-07-22 20:04:43','超级管理员','超级管理员',0,2);
INSERT INTO `tb_order_detail` VALUES (10004,'2023072220R110001VZ9',10002,'苹果风味调味料','L50383',15,'kg',300,23.00,6900.00,1,'2023-07-22','','2023-07-22 20:04:20','2023-07-22 20:04:44','超级管理员','超级管理员',0,2);
INSERT INTO `tb_order_detail` VALUES (10005,'20230722203Q1000237E',10001,'青柠风味调味料','L50398',5,'kg',100,24.00,2400.00,0,NULL,'','2023-07-22 20:07:49','2023-07-22 20:07:49','超级管理员','超级管理员',0,1);
INSERT INTO `tb_order_detail` VALUES (10006,'20230722203Q1000237E',10002,'苹果风味调味料','L50383',10,'kg',200,23.00,4600.00,0,NULL,'','2023-07-22 20:07:49','2023-07-22 20:07:49','超级管理员','超级管理员',0,1);
INSERT INTO `tb_order_detail` VALUES (10007,'20230722203Q1000237E',10004,'荔枝风味调味料','L5043-1',5,'kg',100,22.00,2200.00,0,NULL,'','2023-07-22 20:07:49','2023-07-22 20:07:49','超级管理员','超级管理员',0,1);
INSERT INTO `tb_order_detail` VALUES (10008,'2023072220PN10003N0Z',10002,'苹果风味调味料','L50383',0,'kg',0,0.00,0.00,1,'2023-07-22','','2023-07-22 20:10:16','2023-07-22 21:19:24','超级管理员','超级管理员',0,3);
INSERT INTO `tb_order_detail` VALUES (10009,'2023072221JI10004762',10002,'苹果风味调味料','L50383',8,'kg',200,23.00,4600.00,1,'2023-07-22','','2023-07-22 21:10:15','2023-07-22 21:10:24','超级管理员','超级管理员',0,2);
INSERT INTO `tb_order_detail` VALUES (10010,'20230722218T100054JR',10002,'苹果风味调味料','L50383',8,'kg',200,23.00,4600.00,0,NULL,'','2023-07-22 21:11:27','2023-07-22 21:11:27','超级管理员','超级管理员',0,1);
INSERT INTO `tb_order_detail` VALUES (10011,'20230722218T100054JR',10001,'青柠风味调味料','L50398',5,'kg',100,24.00,2400.00,0,NULL,'','2023-07-22 21:11:27','2023-07-22 21:11:27','超级管理员','超级管理员',0,1);
INSERT INTO `tb_order_detail` VALUES (10012,'2023072221YG10006Q8G',10003,'葡萄风味调味料','L5042',5,'kg',100,23.00,2300.00,0,NULL,'','2023-07-22 21:11:53','2023-07-22 21:11:53','超级管理员','超级管理员',0,1);
INSERT INTO `tb_order_detail` VALUES (10013,'2023072221E6100075PC',10002,'苹果风味调味料','L50383',4,'kg',100,23.00,2300.00,0,NULL,'','2023-07-22 21:18:05','2023-07-22 21:18:05','超级管理员','超级管理员',0,1);
INSERT INTO `tb_order_detail` VALUES (10014,'2023072221E6100075PC',10000,'乳酸菌风味调味料','L5045',5,'kg',100,23.00,2300.00,0,NULL,'','2023-07-22 21:18:05','2023-07-22 21:18:05','超级管理员','超级管理员',0,1);
INSERT INTO `tb_order_detail` VALUES (10015,'20230726156410010XYT',10005,'麻辣风味调味料','M6033',25,'kg',125,0.00,0.00,0,NULL,'','2023-07-26 15:35:30','2023-07-26 15:35:30','超级管理员','超级管理员',0,1);
INSERT INTO `tb_order_detail` VALUES (10016,'2024121521U810011N0S',10000,'乳酸菌风味调味料','L5045-1',10,'kg',200,23.00,4600.00,1,'2024-12-15','','2024-12-15 21:03:28','2024-12-15 21:13:35','超级管理员','超级管理员',0,2);
INSERT INTO `tb_order_detail` VALUES (10017,'20241216215010012JOF',10002,'苹果风味调味料','L50386-1',8,'kg',200,23.00,4600.00,1,'2024-12-16','','2024-12-16 21:03:26','2024-12-16 21:03:50','超级管理员','超级管理员',0,2);
INSERT INTO `tb_order_detail` VALUES (10018,'20241216215010012JOF',10004,'荔枝风味调味料','L5043-1',5,'kg',100,22.00,2200.00,1,'2024-12-16','','2024-12-16 21:03:26','2024-12-16 21:22:38','超级管理员','超级管理员',0,2);
INSERT INTO `tb_order_detail` VALUES (10019,'2024121621WE10013ZJ8',10002,'苹果风味调味料','L50386-1',4,'kg',100,23.00,2300.00,0,NULL,'','2024-12-16 21:11:00','2024-12-16 21:11:00','超级管理员','超级管理员',0,1);
INSERT INTO `tb_order_detail` VALUES (10020,'2024121621WE10013ZJ8',10004,'荔枝风味调味料','L5043-1',10,'kg',200,22.00,4400.00,0,NULL,'','2024-12-16 21:11:00','2024-12-16 21:11:00','超级管理员','超级管理员',0,1);
INSERT INTO `tb_order_detail` VALUES (10021,'2024121621WE10013ZJ8',10005,'麻辣风味调味料','M6033',4,'kg',100,30.00,3000.00,0,NULL,'','2024-12-16 21:11:00','2024-12-16 21:11:00','超级管理员','超级管理员',0,1);
/*!40000 ALTER TABLE `tb_order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_payment_detail`
--

DROP TABLE IF EXISTS `tb_payment_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_payment_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '订单ID',
  `pay_date` date NOT NULL COMMENT '回款日期',
  `amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '总回款金额',
  `note` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  `version` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10004 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='货源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_payment_detail`
--

LOCK TABLES `tb_payment_detail` WRITE;
/*!40000 ALTER TABLE `tb_payment_detail` DISABLE KEYS */;
INSERT INTO `tb_payment_detail` VALUES (10000,'20230722190410000N2Q','2023-07-22',6900.00,'','2023-07-22 20:09:08','2023-07-22 20:09:08','超级管理员','超级管理员',0,1);
INSERT INTO `tb_payment_detail` VALUES (10001,'2023072221JI10004762','2023-07-22',2000.00,'','2023-07-22 22:55:03','2023-07-22 22:55:03','超级管理员','超级管理员',0,1);
INSERT INTO `tb_payment_detail` VALUES (10002,'2024121521U810011N0S','2024-12-16',0.00,'此单回款完成','2024-12-16 18:29:00','2024-12-16 18:29:00','超级管理员','超级管理员',1,1);
INSERT INTO `tb_payment_detail` VALUES (10003,'2024121521U810011N0S','2024-12-16',4600.00,'','2024-12-16 21:02:22','2024-12-16 21:02:22','超级管理员','超级管理员',0,1);
/*!40000 ALTER TABLE `tb_payment_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_product`
--

DROP TABLE IF EXISTS `tb_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_product` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `batch_id` bigint DEFAULT NULL,
  `product_id` bigint NOT NULL,
  `product_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '产品名称',
  `product_code` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '产品编号',
  `unit` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '单位',
  `number` int NOT NULL DEFAULT '0' COMMENT '数量',
  `location` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '仓储',
  `produced_date` date DEFAULT NULL COMMENT '生产日期',
  `status` int NOT NULL DEFAULT '1' COMMENT '产品状态 1新鲜，2临期，3尽快使用',
  `note` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  `version` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10006 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='产品表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product`
--

LOCK TABLES `tb_product` WRITE;
/*!40000 ALTER TABLE `tb_product` DISABLE KEYS */;
INSERT INTO `tb_product` VALUES (10000,10000,10002,'苹果风味调味料','L50383','kg',1900,'东仓','2023-07-21',1,NULL,'2023-07-22 19:43:40','2024-12-16 21:03:50','超级管理员','超级管理员',0,6);
INSERT INTO `tb_product` VALUES (10001,10001,10003,'葡萄风味调味料','L5042','kg',0,'西仓','2023-07-21',1,NULL,'2023-07-22 19:44:46','2023-07-22 20:04:42','超级管理员','超级管理员',0,2);
INSERT INTO `tb_product` VALUES (10002,10002,10003,'葡萄风味调味料','L5042','kg',300,'东厂','2023-07-22',1,NULL,'2023-07-22 19:45:16','2023-07-22 20:04:43','超级管理员','超级管理员',0,2);
INSERT INTO `tb_product` VALUES (10003,10003,10004,'荔枝风味调味料','L5043-1','kg',100,'东仓','2023-07-22',1,NULL,'2023-07-22 19:46:19','2024-12-16 21:22:38','超级管理员','超级管理员',0,2);
INSERT INTO `tb_product` VALUES (10004,10004,10000,'乳酸菌风味调味料','L5045','kg',200,'西仓','2023-07-22',1,'一条备注','2023-07-22 19:49:52','2024-12-15 21:13:35','超级管理员','超级管理员',0,3);
INSERT INTO `tb_product` VALUES (10005,10005,10005,'麻辣风味调味料','M6033','kg',800,'西仓','2024-12-16',1,'','2024-12-16 21:14:38','2024-12-16 21:14:38','超级管理员','超级管理员',0,1);
/*!40000 ALTER TABLE `tb_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_product_overview`
--

DROP TABLE IF EXISTS `tb_product_overview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_product_overview` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '产品',
  `product_code` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '货源',
  `produced_date_last` date DEFAULT NULL COMMENT '最近生产',
  `price_default` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '默认单价',
  `unit` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '单位',
  `number_per_box` int NOT NULL DEFAULT '0',
  `number` int NOT NULL DEFAULT '0' COMMENT '数量',
  `value` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '总价值',
  `number_tag` int NOT NULL DEFAULT '1' COMMENT '数量状态 1正常 2建议备货 3优先生产',
  `note` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  `version` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10006 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='产品总览表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product_overview`
--

LOCK TABLES `tb_product_overview` WRITE;
/*!40000 ALTER TABLE `tb_product_overview` DISABLE KEYS */;
INSERT INTO `tb_product_overview` VALUES (10000,'乳酸菌风味调味料','L5045-1','2023-07-22',23.00,'kg',20,200,4600.00,1,'','2023-07-22 19:40:24','2024-12-15 21:13:35','超级管理员','超级管理员',0,6);
INSERT INTO `tb_product_overview` VALUES (10001,'青柠风味调味料','L50398',NULL,24.00,'kg',30,0,0.00,3,'','2023-07-22 19:40:52','2024-12-16 21:13:53','超级管理员','超级管理员',0,3);
INSERT INTO `tb_product_overview` VALUES (10002,'苹果风味调味料','L50386-1','2023-07-21',23.00,'kg',25,1900,43700.00,1,'','2023-07-22 19:41:09','2024-12-16 21:03:50','超级管理员','超级管理员',0,10);
INSERT INTO `tb_product_overview` VALUES (10003,'葡萄风味调味料','L5042','2023-07-22',23.00,'kg',20,300,6900.00,2,'一条备注','2023-07-22 19:41:23','2023-07-22 20:11:36','超级管理员','超级管理员',0,8);
INSERT INTO `tb_product_overview` VALUES (10004,'荔枝风味调味料','L5043-1','2023-07-22',22.00,'kg',20,100,2200.00,1,'','2023-07-22 19:41:47','2024-12-16 21:22:38','超级管理员','超级管理员',0,4);
INSERT INTO `tb_product_overview` VALUES (10005,'麻辣风味调味料','M6033','2024-12-16',30.00,'kg',25,800,24000.00,1,'','2023-07-26 15:34:08','2024-12-16 21:14:38','超级管理员','超级管理员',0,2);
/*!40000 ALTER TABLE `tb_product_overview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_product_record`
--

DROP TABLE IF EXISTS `tb_product_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_product_record` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `batch_id` bigint DEFAULT NULL COMMENT '批次号',
  `product_id` bigint NOT NULL COMMENT '产品ID',
  `order_id` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '订单ID 当生产入库时没有该栏目',
  `product_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '产品名称',
  `product_code` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '产品编号',
  `unit` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '单位',
  `operate_type` int NOT NULL COMMENT '1生产 2出货 3手动调整 4删除',
  `operate_number` int NOT NULL DEFAULT '0' COMMENT '操作数量',
  `remain_number` int NOT NULL DEFAULT '0' COMMENT '本批余量',
  `origin_number` int NOT NULL DEFAULT '0',
  `produced_date` date DEFAULT NULL COMMENT '生产日期',
  `status` int NOT NULL DEFAULT '1' COMMENT '产品状态 1新鲜，2临期，3尽快使用',
  `customer` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '去向客户',
  `delivered_date` date DEFAULT NULL COMMENT '发货日期',
  `note` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  `version` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10017 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='产品记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product_record`
--

LOCK TABLES `tb_product_record` WRITE;
/*!40000 ALTER TABLE `tb_product_record` DISABLE KEYS */;
INSERT INTO `tb_product_record` VALUES (10000,10000,10002,NULL,'苹果风味调味料','L50383','kg',1,3000,3000,0,'2023-07-21',1,NULL,NULL,NULL,'2023-07-22 19:43:41','2023-07-22 19:43:41','超级管理员','超级管理员',0,1);
INSERT INTO `tb_product_record` VALUES (10001,10001,10003,NULL,'葡萄风味调味料','L5042','kg',1,100,100,0,'2023-07-21',1,NULL,NULL,NULL,'2023-07-22 19:44:46','2023-07-22 19:44:46','超级管理员','超级管理员',0,1);
INSERT INTO `tb_product_record` VALUES (10002,10002,10003,NULL,'葡萄风味调味料','L5042','kg',1,500,500,0,'2023-07-22',1,NULL,NULL,NULL,'2023-07-22 19:45:16','2023-07-22 19:45:16','超级管理员','超级管理员',0,1);
INSERT INTO `tb_product_record` VALUES (10003,10003,10004,NULL,'荔枝风味调味料','L5043-1','kg',1,200,200,0,'2023-07-22',1,NULL,NULL,NULL,'2023-07-22 19:46:19','2023-07-22 19:46:19','超级管理员','超级管理员',0,1);
INSERT INTO `tb_product_record` VALUES (10004,10004,10000,NULL,'乳酸菌风味调味料','L5045','kg',1,500,500,0,'2023-07-22',1,NULL,NULL,'一条备注','2023-07-22 19:49:53','2023-07-22 19:49:53','超级管理员','超级管理员',0,1);
INSERT INTO `tb_product_record` VALUES (10005,10004,10000,'20230722190410000N2Q','乳酸菌风味调味料','L5045','kg',2,100,400,500,'2023-07-22',1,'公司1','2023-07-22','一条备注','2023-07-22 20:02:14','2023-07-22 20:02:14','超级管理员','超级管理员',0,1);
INSERT INTO `tb_product_record` VALUES (10006,10000,10002,'20230722190410000N2Q','苹果风味调味料','L50383','kg',2,200,2800,3000,'2023-07-21',1,'公司1','2023-07-22',NULL,'2023-07-22 20:02:14','2023-07-22 20:02:14','超级管理员','超级管理员',0,1);
INSERT INTO `tb_product_record` VALUES (10007,10001,10003,'2023072220R110001VZ9','葡萄风味调味料','L5042','kg',2,100,0,100,'2023-07-21',1,'公司1','2023-07-22',NULL,'2023-07-22 20:04:42','2023-07-22 20:04:42','超级管理员','超级管理员',0,1);
INSERT INTO `tb_product_record` VALUES (10008,10002,10003,'2023072220R110001VZ9','葡萄风味调味料','L5042','kg',2,200,300,500,'2023-07-22',1,'公司1','2023-07-22',NULL,'2023-07-22 20:04:42','2023-07-22 20:04:42','超级管理员','超级管理员',0,1);
INSERT INTO `tb_product_record` VALUES (10009,10000,10002,'2023072220R110001VZ9','苹果风味调味料','L50383','kg',2,300,2500,2800,'2023-07-21',1,'公司1','2023-07-22',NULL,'2023-07-22 20:04:43','2023-07-22 20:04:43','超级管理员','超级管理员',0,2);
INSERT INTO `tb_product_record` VALUES (10010,10001,10003,NULL,'葡萄风味调味料','L5042','kg',4,0,0,0,'2023-07-21',1,NULL,NULL,NULL,'2023-07-22 20:05:55','2023-07-22 20:05:55','超级管理员','超级管理员',0,2);
INSERT INTO `tb_product_record` VALUES (10011,10000,10002,'2023072221JI10004762','苹果风味调味料','L50383','kg',2,200,2300,2500,'2023-07-21',1,'公司1','2023-07-22',NULL,'2023-07-22 21:10:23','2023-07-22 21:10:23','超级管理员','超级管理员',0,3);
INSERT INTO `tb_product_record` VALUES (10012,10000,10002,'2023072220PN10003N0Z','苹果风味调味料','L50383','kg',2,200,2100,2300,'2023-07-21',1,'公司1','2023-07-22',NULL,'2023-07-22 21:18:53','2023-07-22 21:18:53','超级管理员','超级管理员',0,4);
INSERT INTO `tb_product_record` VALUES (10013,10004,10000,'2024121521U810011N0S','乳酸菌风味调味料','L5045','kg',2,200,200,400,'2023-07-22',1,'公司1','2024-12-15','一条备注','2024-12-15 21:13:34','2024-12-15 21:13:34','超级管理员','超级管理员',0,2);
INSERT INTO `tb_product_record` VALUES (10014,10000,10002,'20241216215010012JOF','苹果风味调味料','L50383','kg',2,200,1900,2100,'2023-07-21',1,'公司1','2024-12-16',NULL,'2024-12-16 21:03:50','2024-12-16 21:03:50','超级管理员','超级管理员',0,5);
INSERT INTO `tb_product_record` VALUES (10015,10005,10005,NULL,'麻辣风味调味料','M6033','kg',1,800,800,0,'2024-12-16',1,NULL,NULL,'','2024-12-16 21:14:38','2024-12-16 21:14:38','超级管理员','超级管理员',0,1);
INSERT INTO `tb_product_record` VALUES (10016,10003,10004,'20241216215010012JOF','荔枝风味调味料','L5043-1','kg',2,100,100,200,'2023-07-22',1,'公司1','2024-12-16',NULL,'2024-12-16 21:22:38','2024-12-16 21:22:38','超级管理员','超级管理员',0,1);
/*!40000 ALTER TABLE `tb_product_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_purchase_record`
--

DROP TABLE IF EXISTS `tb_purchase_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_purchase_record` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_id` bigint NOT NULL,
  `category_id` bigint NOT NULL,
  `material_info_id` bigint NOT NULL,
  `category` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '品类',
  `supplier` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '货源',
  `number` int NOT NULL DEFAULT '0' COMMENT '货源',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '单价',
  `total_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '总金额',
  `location` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '仓储位置',
  `produced_date` date DEFAULT NULL COMMENT '生产日期',
  `status` int DEFAULT '1' COMMENT '货物状态 1新鲜，2临期，3尽快使用',
  `create_date` date DEFAULT NULL COMMENT '订单日期',
  `note` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  `version` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10005 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='购买记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_purchase_record`
--

LOCK TABLES `tb_purchase_record` WRITE;
/*!40000 ALTER TABLE `tb_purchase_record` DISABLE KEYS */;
INSERT INTO `tb_purchase_record` VALUES (10000,10002,10003,10000,'辣椒','供货商1',500,10.00,5000.00,'',NULL,1,'2023-07-22','','2023-07-22 19:04:01','2023-07-22 19:04:01','超级管理员','超级管理员',0,1);
INSERT INTO `tb_purchase_record` VALUES (10001,10003,10003,10002,'辣椒','供货商2',100,11.00,1100.00,'东厂',NULL,1,'2023-07-22','测试备注','2023-07-22 19:05:51','2023-07-22 19:10:04','超级管理员','超级管理员',0,2);
INSERT INTO `tb_purchase_record` VALUES (10002,10004,10003,10000,'辣椒','供货商1',500,10.00,5000.00,'东厂','2023-07-09',1,'2023-07-22','','2023-07-22 19:42:43','2023-07-22 19:42:43','超级管理员','超级管理员',0,1);
INSERT INTO `tb_purchase_record` VALUES (10003,10005,10003,10000,'辣椒','供货商1',0,10.00,0.00,'','2024-12-11',1,'2024-12-15','','2024-12-15 18:29:43','2024-12-15 18:29:43','超级管理员','超级管理员',0,1);
INSERT INTO `tb_purchase_record` VALUES (10004,10006,10003,10000,'辣椒','供货商1',10,10.00,100.00,'',NULL,1,'2024-12-15','','2024-12-15 18:31:40','2024-12-15 18:31:40','超级管理员','超级管理员',0,1);
/*!40000 ALTER TABLE `tb_purchase_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `kwy`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `kwy` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `kwy`;

--
-- Table structure for table `tb_customer`
--

DROP TABLE IF EXISTS `tb_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_customer` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `customer` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '客户',
  `people` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '联系人',
  `phone_standby` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '备用联系人',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '联系电话',
  `address` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '默认地址',
  `note` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  `version` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10031 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='货源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_customer`
--

LOCK TABLES `tb_customer` WRITE;
/*!40000 ALTER TABLE `tb_customer` DISABLE KEYS */;
INSERT INTO `tb_customer` VALUES (10000,'公司1','王红','12345667899','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','xxxxx','2023-07-22 19:51:03','2023-07-22 19:51:03','超级管理员','超级管理员',0,1);
INSERT INTO `tb_customer` VALUES (10001,'公司2','李辉','12345667899','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','xxxx','2023-07-22 19:53:23','2023-07-22 19:53:23','超级管理员','超级管理员',0,1);
INSERT INTO `tb_customer` VALUES (10002,'公司3','张伟','12345667899','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','xxxx','2023-07-22 19:54:15','2023-07-22 19:54:15','超级管理员','超级管理员',0,1);
INSERT INTO `tb_customer` VALUES (10011,'好滋味','汤剑乔','','78911124454','河南省郑州市新郑市郭店镇紫荆山南路与轻工路交叉口北340米路西（泰德能源）','','2023-07-26 15:50:25','2023-07-26 15:50:25','超级管理员','超级管理员',0,1);
INSERT INTO `tb_customer` VALUES (10012,'泽洋食品   ','王俊祥 ','','78911124454','新郑市龙湖镇大赵村','','2023-07-26 15:51:04','2023-07-26 15:51:04','超级管理员','超级管理员',0,1);
INSERT INTO `tb_customer` VALUES (10013,'好家旺食品','潘经理 ','','78911124454','新郑市北靳楼村工业园区','','2023-07-26 15:51:52','2023-07-26 15:51:52','超级管理员','超级管理员',0,1);
INSERT INTO `tb_customer` VALUES (10014,'米立方','张佳佳  ','','78911124454','河南省鹤壁市淇县北阳镇工业园区','','2023-07-26 15:52:18','2023-07-26 15:52:18','超级管理员','超级管理员',0,1);
INSERT INTO `tb_customer` VALUES (10015,'福源鑫食品','牛树民  ','','78911124454','开封市尉氏县洧川镇四合村','','2023-07-26 15:52:51','2023-07-26 15:52:51','超级管理员','超级管理员',0,1);
INSERT INTO `tb_customer` VALUES (10016,'新密联楹 ','冯亚慧 ','','78911124454','新密大槐镇政府旁边','','2023-07-26 15:53:15','2023-07-26 15:53:15','超级管理员','超级管理员',0,1);
/*!40000 ALTER TABLE `tb_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_employee`
--

DROP TABLE IF EXISTS `tb_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_employee` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '姓名',
  `username` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '手机号',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态 0:禁用，1:正常',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '修改人',
  `version` int NOT NULL DEFAULT '1',
  `deleted` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10004 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='员工信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_employee`
--

LOCK TABLES `tb_employee` WRITE;
/*!40000 ALTER TABLE `tb_employee` DISABLE KEYS */;
INSERT INTO `tb_employee` VALUES (10001,'超级管理员','admin','21232f297a57a5a743894a0e4a801fc3','12345667899',2,'2023-07-13 16:20:40','2023-07-13 16:20:40',NULL,NULL,1,0);
INSERT INTO `tb_employee` VALUES (10002,'公司帐号-1','test1','e10adc3949ba59abbe56e057f20f883e','12345667899',1,'2023-07-22 21:25:47','2023-07-22 21:25:47','超级管理员','超级管理员',1,0);
INSERT INTO `tb_employee` VALUES (10003,'工厂帐号-1','test-2','14e1b600b1fd579f47433b88e8d85291','12345667899',0,'2023-07-22 21:26:15','2023-07-22 21:26:28','超级管理员','超级管理员',2,0);
/*!40000 ALTER TABLE `tb_employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_goods`
--

DROP TABLE IF EXISTS `tb_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_goods` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_id` bigint NOT NULL DEFAULT '0' COMMENT '货号',
  `category_id` bigint NOT NULL,
  `category` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '品类',
  `material_info_id` bigint DEFAULT NULL COMMENT '货品ID',
  `supplier` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '货源',
  `number` int NOT NULL DEFAULT '0' COMMENT '余量',
  `location` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '仓储位置',
  `produced_date` date DEFAULT NULL COMMENT '生产日期',
  `status` int NOT NULL DEFAULT '1' COMMENT '货物状态 1新鲜，2临期，3尽快使用',
  `note` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  `version` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10007 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='货源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_goods`
--

LOCK TABLES `tb_goods` WRITE;
/*!40000 ALTER TABLE `tb_goods` DISABLE KEYS */;
INSERT INTO `tb_goods` VALUES (10000,10000,10000,'玉米淀粉',NULL,NULL,200,'东仓','2023-07-11',1,'一条备注','2023-07-22 19:01:13','2023-07-22 19:01:13','超级管理员','超级管理员',0,1);
INSERT INTO `tb_goods` VALUES (10001,10001,10001,'食盐',NULL,NULL,500,'西厂','2023-07-09',2,'','2023-07-22 19:02:18','2023-07-22 19:02:35','超级管理员','超级管理员',0,2);
INSERT INTO `tb_goods` VALUES (10002,10002,10003,'辣椒',10000,'供货商1',0,'',NULL,1,'','2023-07-22 19:04:01','2023-07-22 19:35:13','超级管理员','超级管理员',1,4);
INSERT INTO `tb_goods` VALUES (10003,10003,10003,'辣椒',10002,'供货商2',100,'东厂',NULL,1,'','2023-07-22 19:05:51','2023-07-22 19:16:36','超级管理员','超级管理员',0,5);
INSERT INTO `tb_goods` VALUES (10004,10004,10003,'辣椒',10000,'供货商1',500,'东厂','2023-07-09',1,'','2023-07-22 19:42:43','2023-07-22 19:42:43','超级管理员','超级管理员',0,1);
INSERT INTO `tb_goods` VALUES (10005,10005,10003,'辣椒',10000,'供货商1',0,'','2024-12-11',1,'','2024-12-15 18:29:43','2024-12-15 18:29:43','超级管理员','超级管理员',0,1);
INSERT INTO `tb_goods` VALUES (10006,10006,10003,'辣椒',10000,'供货商1',7,'',NULL,1,'','2024-12-15 18:31:39','2024-12-15 18:32:23','超级管理员','超级管理员',0,2);
/*!40000 ALTER TABLE `tb_goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_goods_record`
--

DROP TABLE IF EXISTS `tb_goods_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_goods_record` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_id` bigint NOT NULL COMMENT '货号',
  `category_id` bigint NOT NULL,
  `category` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '品类',
  `material_info_id` bigint DEFAULT NULL,
  `supplier` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '货源',
  `operate_type` int NOT NULL COMMENT '1采购 2出库 3手动录入 4手动调整 5删除',
  `operate_number` int NOT NULL DEFAULT '0' COMMENT '操作数量',
  `remain_number` int NOT NULL DEFAULT '0' COMMENT '本批余量',
  `origin_number` int NOT NULL DEFAULT '0' COMMENT '原来的数量',
  `produced_date` date DEFAULT NULL COMMENT '生产日期',
  `location` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '仓储位置',
  `status` int DEFAULT '1' COMMENT '货物状态 1新鲜，2临期，3尽快使用',
  `note` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  `version` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10013 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='货源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_goods_record`
--

LOCK TABLES `tb_goods_record` WRITE;
/*!40000 ALTER TABLE `tb_goods_record` DISABLE KEYS */;
INSERT INTO `tb_goods_record` VALUES (10000,10000,10000,'玉米淀粉',NULL,NULL,3,200,200,0,'2023-07-11','东仓',1,'一条备注','2023-07-22 19:01:14','2023-07-22 19:01:14','超级管理员','超级管理员',0,1);
INSERT INTO `tb_goods_record` VALUES (10001,10001,10001,'食盐',NULL,NULL,3,500,500,0,'2023-07-09','西厂',1,'','2023-07-22 19:02:18','2023-07-22 19:02:18','超级管理员','超级管理员',0,1);
INSERT INTO `tb_goods_record` VALUES (10002,10001,10001,'食盐',NULL,NULL,4,0,500,500,'2023-07-09','西厂',2,'','2023-07-22 19:02:35','2023-07-22 19:02:35','超级管理员','超级管理员',0,1);
INSERT INTO `tb_goods_record` VALUES (10003,10002,10003,'辣椒',10000,'供货商1',1,500,500,0,NULL,'',1,'测试备注','2023-07-22 19:04:01','2023-07-22 19:10:48','超级管理员','超级管理员',0,2);
INSERT INTO `tb_goods_record` VALUES (10004,10003,10003,'辣椒',10002,'供货商2',1,100,100,0,NULL,'东厂',1,'','2023-07-22 19:05:51','2023-07-22 19:05:51','超级管理员','超级管理员',0,1);
INSERT INTO `tb_goods_record` VALUES (10005,10002,10003,'辣椒',10000,'供货商1',4,-100,400,500,NULL,'',1,'','2023-07-22 19:34:19','2023-07-22 19:34:19','超级管理员','超级管理员',0,1);
INSERT INTO `tb_goods_record` VALUES (10006,10002,10003,'辣椒',10000,'供货商1',2,200,200,400,NULL,'',1,'生产xx','2023-07-22 19:34:53','2023-07-22 19:34:53','超级管理员','超级管理员',0,1);
INSERT INTO `tb_goods_record` VALUES (10007,10002,10003,'辣椒',10000,'供货商1',2,200,0,200,NULL,'',1,'生产xx','2023-07-22 19:35:12','2023-07-22 19:35:12','超级管理员','超级管理员',0,1);
INSERT INTO `tb_goods_record` VALUES (10008,10002,10003,'辣椒',10000,'供货商1',5,0,0,0,NULL,'',1,'','2023-07-22 19:35:16','2023-07-22 19:35:16','超级管理员','超级管理员',0,4);
INSERT INTO `tb_goods_record` VALUES (10009,10004,10003,'辣椒',10000,'供货商1',1,500,500,0,'2023-07-09','东厂',1,'','2023-07-22 19:42:43','2023-07-22 19:42:43','超级管理员','超级管理员',0,1);
INSERT INTO `tb_goods_record` VALUES (10010,10005,10003,'辣椒',10000,'供货商1',1,0,0,0,'2024-12-11','',1,'','2024-12-15 18:29:43','2024-12-15 18:29:43','超级管理员','超级管理员',0,1);
INSERT INTO `tb_goods_record` VALUES (10011,10006,10003,'辣椒',10000,'供货商1',1,10,10,0,NULL,'',1,'','2024-12-15 18:31:40','2024-12-15 18:31:40','超级管理员','超级管理员',0,1);
INSERT INTO `tb_goods_record` VALUES (10012,10006,10003,'辣椒',10000,'供货商1',2,3,7,10,NULL,'',1,'生产xxx产品','2024-12-15 18:32:23','2024-12-15 18:32:23','超级管理员','超级管理员',0,1);
/*!40000 ALTER TABLE `tb_goods_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_material_info`
--

DROP TABLE IF EXISTS `tb_material_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_material_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` bigint NOT NULL,
  `category` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '品类',
  `supplier` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '货源',
  `default_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '默认单价',
  `latest_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `note` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  `version` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10003 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='货源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_material_info`
--

LOCK TABLES `tb_material_info` WRITE;
/*!40000 ALTER TABLE `tb_material_info` DISABLE KEYS */;
INSERT INTO `tb_material_info` VALUES (10000,10003,'辣椒','供货商1',10.00,10.00,'','2023-07-22 19:03:16','2024-12-15 18:31:40','超级管理员','超级管理员',0,5);
INSERT INTO `tb_material_info` VALUES (10001,10000,'玉米淀粉','供货商2',2.50,0.00,'','2023-07-22 19:04:30','2023-07-22 19:11:37','超级管理员','超级管理员',0,3);
INSERT INTO `tb_material_info` VALUES (10002,10003,'辣椒','供货商2',11.00,11.00,'','2023-07-22 19:05:17','2023-07-22 19:05:51','超级管理员','超级管理员',0,2);
/*!40000 ALTER TABLE `tb_material_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_material_overview`
--

DROP TABLE IF EXISTS `tb_material_overview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_material_overview` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '品类',
  `number` int NOT NULL DEFAULT '0' COMMENT '总量',
  `update_date` date DEFAULT NULL COMMENT '最近入库日期',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '默认单价',
  `value` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '估值',
  `number_tag` int NOT NULL DEFAULT '1' COMMENT '1正常 2偏少 3及时补货',
  `note` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `version` int NOT NULL DEFAULT '1',
  `deleted` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10005 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='货源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_material_overview`
--

LOCK TABLES `tb_material_overview` WRITE;
/*!40000 ALTER TABLE `tb_material_overview` DISABLE KEYS */;
INSERT INTO `tb_material_overview` VALUES (10000,'玉米淀粉',200,'2023-07-22',2.00,400.00,1,'','2023-07-22 18:57:40','2023-07-22 19:01:14','超级管理员','超级管理员',2,0);
INSERT INTO `tb_material_overview` VALUES (10001,'食盐',500,'2023-07-22',1.00,500.00,1,'','2023-07-22 18:58:05','2023-07-22 19:02:18','超级管理员','超级管理员',2,0);
INSERT INTO `tb_material_overview` VALUES (10003,'辣椒',607,'2024-12-15',10.00,6070.00,1,'','2023-07-22 18:59:17','2024-12-15 18:32:23','超级管理员','超级管理员',15,0);
INSERT INTO `tb_material_overview` VALUES (10004,'蔗糖',0,NULL,5.00,0.00,1,'','2023-07-22 18:59:30','2023-07-22 18:59:44','超级管理员','超级管理员',2,0);
/*!40000 ALTER TABLE `tb_material_overview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_order`
--

DROP TABLE IF EXISTS `tb_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_order` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '订单ID',
  `customer_id` bigint NOT NULL,
  `customer` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '客户',
  `people` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '联系人',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '联系电话',
  `address` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '地址',
  `create_date` date DEFAULT NULL COMMENT '下单日期',
  `status` int NOT NULL DEFAULT '1' COMMENT '订单状态 1未制作 2制作中 3部分交付 4待回款 5完成 6返厂 7作废',
  `amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '订单金额',
  `total_delivered` decimal(10,2) NOT NULL DEFAULT '0.00',
  `delivery_progress` int NOT NULL DEFAULT '0' COMMENT '交付进度',
  `total_payment` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '总回款金额',
  `note` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `content` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '订单内容',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `version` int NOT NULL DEFAULT '1',
  `deleted` int NOT NULL DEFAULT '0' COMMENT '0未删除 1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10014 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='货源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_order`
--

LOCK TABLES `tb_order` WRITE;
/*!40000 ALTER TABLE `tb_order` DISABLE KEYS */;
INSERT INTO `tb_order` VALUES (10000,'20230722190410000N2Q',10000,'公司1','王红','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','2023-07-18',5,6900.00,6900.00,100,6900.00,'xxxx','','2023-07-22 19:59:41','2023-07-22 20:09:08','超级管理员','超级管理员',7,0);
INSERT INTO `tb_order` VALUES (10001,'2023072220R110001VZ9',10000,'公司1','王红','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','2023-07-22',3,20400.00,13800.00,67,0.00,'','','2023-07-22 20:04:19','2023-07-22 20:08:05','超级管理员','超级管理员',3,0);
INSERT INTO `tb_order` VALUES (10002,'20230722203Q1000237E',10000,'公司1','王红','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','2023-07-01',2,9200.00,0.00,0,0.00,'','','2023-07-22 20:07:49','2023-07-22 20:08:20','超级管理员','超级管理员',2,0);
INSERT INTO `tb_order` VALUES (10003,'2023072220PN10003N0Z',10000,'公司1','王红','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','2023-07-26',6,0.00,0.00,0,0.00,'','','2023-07-22 20:10:16','2023-07-22 21:19:24','超级管理员','超级管理员',6,0);
INSERT INTO `tb_order` VALUES (10004,'2023072221JI10004762',10000,'公司1','王红','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','2023-07-18',4,4600.00,4600.00,100,2000.00,'','','2023-07-22 21:10:15','2023-07-22 22:55:03','超级管理员','超级管理员',4,0);
INSERT INTO `tb_order` VALUES (10005,'20230722218T100054JR',10000,'公司1','王红','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','2023-07-11',1,7000.00,0.00,0,0.00,'','','2023-07-22 21:11:27','2023-07-22 21:11:27','超级管理员','超级管理员',1,0);
INSERT INTO `tb_order` VALUES (10006,'2023072221YG10006Q8G',10000,'公司1','王红','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','2023-07-11',7,2300.00,0.00,0,0.00,'','','2023-07-22 21:11:53','2023-07-22 21:12:01','超级管理员','超级管理员',2,0);
INSERT INTO `tb_order` VALUES (10007,'2023072221E6100075PC',10001,'公司2','李辉','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','2023-07-01',1,4600.00,0.00,0,0.00,'','','2023-07-22 21:18:05','2023-07-22 21:18:05','超级管理员','超级管理员',1,0);
INSERT INTO `tb_order` VALUES (10010,'20230726156410010XYT',10003,'小陈美盛','孙总','18638287628','小陈乡政府南边','2023-07-25',1,0.00,0.00,0,0.00,'','','2023-07-26 15:35:30','2023-07-26 15:35:30','超级管理员','超级管理员',1,0);
INSERT INTO `tb_order` VALUES (10011,'2024121521U810011N0S',10000,'公司1','王红','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','2024-12-15',5,4600.00,4600.00,100,4600.00,'','','2024-12-15 21:03:28','2024-12-16 21:02:22','超级管理员','超级管理员',7,0);
INSERT INTO `tb_order` VALUES (10012,'20241216215010012JOF',10000,'公司1','王红','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','2024-12-16',4,6800.00,6800.00,100,0.00,'','','2024-12-16 21:03:26','2024-12-16 21:23:08','超级管理员','超级管理员',5,0);
INSERT INTO `tb_order` VALUES (10013,'2024121621WE10013ZJ8',10000,'公司1','王红','12345667899','xx省xx市xx县城xx街道xx号xxxxxxx','2024-12-16',2,9700.00,0.00,0,0.00,'','','2024-12-16 21:11:00','2024-12-16 21:11:11','超级管理员','超级管理员',2,0);
/*!40000 ALTER TABLE `tb_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_order_detail`
--

DROP TABLE IF EXISTS `tb_order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_order_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `product_id` bigint NOT NULL,
  `product_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '产品名称',
  `product_code` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '产品编号',
  `package_number` int NOT NULL COMMENT '件数',
  `unit` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '单位',
  `number` int NOT NULL DEFAULT '0' COMMENT '数量',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '单价',
  `amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '金额',
  `is_delivered` int NOT NULL DEFAULT '0' COMMENT '是否发货 0未发货 1发货 默认0',
  `delivered_date` date DEFAULT NULL COMMENT '发货日期',
  `note` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  `version` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10022 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='订单明细表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_order_detail`
--

LOCK TABLES `tb_order_detail` WRITE;
/*!40000 ALTER TABLE `tb_order_detail` DISABLE KEYS */;
INSERT INTO `tb_order_detail` VALUES (10000,'20230722190410000N2Q',10000,'乳酸菌风味调味料','L5045',5,'kg',100,23.00,2300.00,1,'2023-07-22','xxxxx','2023-07-22 19:59:41','2023-07-22 20:02:14','超级管理员','超级管理员',0,3);
INSERT INTO `tb_order_detail` VALUES (10001,'20230722190410000N2Q',10002,'苹果风味调味料','L50383',10,'kg',200,23.00,4600.00,1,'2023-07-22','','2023-07-22 19:59:41','2023-07-22 20:02:14','超级管理员','超级管理员',0,2);
INSERT INTO `tb_order_detail` VALUES (10002,'2023072220R110001VZ9',10004,'荔枝风味调味料','L5043-1',15,'kg',300,22.00,6600.00,0,NULL,'','2023-07-22 20:04:19','2023-07-22 20:04:19','超级管理员','超级管理员',0,1);
INSERT INTO `tb_order_detail` VALUES (10003,'2023072220R110001VZ9',10003,'葡萄风味调味料','L5042',15,'kg',300,23.00,6900.00,1,'2023-07-22','','2023-07-22 20:04:19','2023-07-22 20:04:43','超级管理员','超级管理员',0,2);
INSERT INTO `tb_order_detail` VALUES (10004,'2023072220R110001VZ9',10002,'苹果风味调味料','L50383',15,'kg',300,23.00,6900.00,1,'2023-07-22','','2023-07-22 20:04:20','2023-07-22 20:04:44','超级管理员','超级管理员',0,2);
INSERT INTO `tb_order_detail` VALUES (10005,'20230722203Q1000237E',10001,'青柠风味调味料','L50398',5,'kg',100,24.00,2400.00,0,NULL,'','2023-07-22 20:07:49','2023-07-22 20:07:49','超级管理员','超级管理员',0,1);
INSERT INTO `tb_order_detail` VALUES (10006,'20230722203Q1000237E',10002,'苹果风味调味料','L50383',10,'kg',200,23.00,4600.00,0,NULL,'','2023-07-22 20:07:49','2023-07-22 20:07:49','超级管理员','超级管理员',0,1);
INSERT INTO `tb_order_detail` VALUES (10007,'20230722203Q1000237E',10004,'荔枝风味调味料','L5043-1',5,'kg',100,22.00,2200.00,0,NULL,'','2023-07-22 20:07:49','2023-07-22 20:07:49','超级管理员','超级管理员',0,1);
INSERT INTO `tb_order_detail` VALUES (10008,'2023072220PN10003N0Z',10002,'苹果风味调味料','L50383',0,'kg',0,0.00,0.00,1,'2023-07-22','','2023-07-22 20:10:16','2023-07-22 21:19:24','超级管理员','超级管理员',0,3);
INSERT INTO `tb_order_detail` VALUES (10009,'2023072221JI10004762',10002,'苹果风味调味料','L50383',8,'kg',200,23.00,4600.00,1,'2023-07-22','','2023-07-22 21:10:15','2023-07-22 21:10:24','超级管理员','超级管理员',0,2);
INSERT INTO `tb_order_detail` VALUES (10010,'20230722218T100054JR',10002,'苹果风味调味料','L50383',8,'kg',200,23.00,4600.00,0,NULL,'','2023-07-22 21:11:27','2023-07-22 21:11:27','超级管理员','超级管理员',0,1);
INSERT INTO `tb_order_detail` VALUES (10011,'20230722218T100054JR',10001,'青柠风味调味料','L50398',5,'kg',100,24.00,2400.00,0,NULL,'','2023-07-22 21:11:27','2023-07-22 21:11:27','超级管理员','超级管理员',0,1);
INSERT INTO `tb_order_detail` VALUES (10012,'2023072221YG10006Q8G',10003,'葡萄风味调味料','L5042',5,'kg',100,23.00,2300.00,0,NULL,'','2023-07-22 21:11:53','2023-07-22 21:11:53','超级管理员','超级管理员',0,1);
INSERT INTO `tb_order_detail` VALUES (10013,'2023072221E6100075PC',10002,'苹果风味调味料','L50383',4,'kg',100,23.00,2300.00,0,NULL,'','2023-07-22 21:18:05','2023-07-22 21:18:05','超级管理员','超级管理员',0,1);
INSERT INTO `tb_order_detail` VALUES (10014,'2023072221E6100075PC',10000,'乳酸菌风味调味料','L5045',5,'kg',100,23.00,2300.00,0,NULL,'','2023-07-22 21:18:05','2023-07-22 21:18:05','超级管理员','超级管理员',0,1);
INSERT INTO `tb_order_detail` VALUES (10015,'20230726156410010XYT',10005,'麻辣风味调味料','M6033',25,'kg',125,0.00,0.00,0,NULL,'','2023-07-26 15:35:30','2023-07-26 15:35:30','超级管理员','超级管理员',0,1);
INSERT INTO `tb_order_detail` VALUES (10016,'2024121521U810011N0S',10000,'乳酸菌风味调味料','L5045-1',10,'kg',200,23.00,4600.00,1,'2024-12-15','','2024-12-15 21:03:28','2024-12-15 21:13:35','超级管理员','超级管理员',0,2);
INSERT INTO `tb_order_detail` VALUES (10017,'20241216215010012JOF',10002,'苹果风味调味料','L50386-1',8,'kg',200,23.00,4600.00,1,'2024-12-16','','2024-12-16 21:03:26','2024-12-16 21:03:50','超级管理员','超级管理员',0,2);
INSERT INTO `tb_order_detail` VALUES (10018,'20241216215010012JOF',10004,'荔枝风味调味料','L5043-1',5,'kg',100,22.00,2200.00,1,'2024-12-16','','2024-12-16 21:03:26','2024-12-16 21:22:38','超级管理员','超级管理员',0,2);
INSERT INTO `tb_order_detail` VALUES (10019,'2024121621WE10013ZJ8',10002,'苹果风味调味料','L50386-1',4,'kg',100,23.00,2300.00,0,NULL,'','2024-12-16 21:11:00','2024-12-16 21:11:00','超级管理员','超级管理员',0,1);
INSERT INTO `tb_order_detail` VALUES (10020,'2024121621WE10013ZJ8',10004,'荔枝风味调味料','L5043-1',10,'kg',200,22.00,4400.00,0,NULL,'','2024-12-16 21:11:00','2024-12-16 21:11:00','超级管理员','超级管理员',0,1);
INSERT INTO `tb_order_detail` VALUES (10021,'2024121621WE10013ZJ8',10005,'麻辣风味调味料','M6033',4,'kg',100,30.00,3000.00,0,NULL,'','2024-12-16 21:11:00','2024-12-16 21:11:00','超级管理员','超级管理员',0,1);
/*!40000 ALTER TABLE `tb_order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_payment_detail`
--

DROP TABLE IF EXISTS `tb_payment_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_payment_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '订单ID',
  `pay_date` date NOT NULL COMMENT '回款日期',
  `amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '总回款金额',
  `note` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  `version` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10004 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='货源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_payment_detail`
--

LOCK TABLES `tb_payment_detail` WRITE;
/*!40000 ALTER TABLE `tb_payment_detail` DISABLE KEYS */;
INSERT INTO `tb_payment_detail` VALUES (10000,'20230722190410000N2Q','2023-07-22',6900.00,'','2023-07-22 20:09:08','2023-07-22 20:09:08','超级管理员','超级管理员',0,1);
INSERT INTO `tb_payment_detail` VALUES (10001,'2023072221JI10004762','2023-07-22',2000.00,'','2023-07-22 22:55:03','2023-07-22 22:55:03','超级管理员','超级管理员',0,1);
INSERT INTO `tb_payment_detail` VALUES (10002,'2024121521U810011N0S','2024-12-16',0.00,'此单回款完成','2024-12-16 18:29:00','2024-12-16 18:29:00','超级管理员','超级管理员',1,1);
INSERT INTO `tb_payment_detail` VALUES (10003,'2024121521U810011N0S','2024-12-16',4600.00,'','2024-12-16 21:02:22','2024-12-16 21:02:22','超级管理员','超级管理员',0,1);
/*!40000 ALTER TABLE `tb_payment_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_product`
--

DROP TABLE IF EXISTS `tb_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_product` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `batch_id` bigint DEFAULT NULL,
  `product_id` bigint NOT NULL,
  `product_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '产品名称',
  `product_code` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '产品编号',
  `unit` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '单位',
  `number` int NOT NULL DEFAULT '0' COMMENT '数量',
  `location` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '仓储',
  `produced_date` date DEFAULT NULL COMMENT '生产日期',
  `status` int NOT NULL DEFAULT '1' COMMENT '产品状态 1新鲜，2临期，3尽快使用',
  `note` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  `version` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10006 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='产品表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product`
--

LOCK TABLES `tb_product` WRITE;
/*!40000 ALTER TABLE `tb_product` DISABLE KEYS */;
INSERT INTO `tb_product` VALUES (10000,10000,10002,'苹果风味调味料','L50383','kg',1900,'东仓','2023-07-21',1,NULL,'2023-07-22 19:43:40','2024-12-16 21:03:50','超级管理员','超级管理员',0,6);
INSERT INTO `tb_product` VALUES (10001,10001,10003,'葡萄风味调味料','L5042','kg',0,'西仓','2023-07-21',1,NULL,'2023-07-22 19:44:46','2023-07-22 20:04:42','超级管理员','超级管理员',0,2);
INSERT INTO `tb_product` VALUES (10002,10002,10003,'葡萄风味调味料','L5042','kg',300,'东厂','2023-07-22',1,NULL,'2023-07-22 19:45:16','2023-07-22 20:04:43','超级管理员','超级管理员',0,2);
INSERT INTO `tb_product` VALUES (10003,10003,10004,'荔枝风味调味料','L5043-1','kg',100,'东仓','2023-07-22',1,NULL,'2023-07-22 19:46:19','2024-12-16 21:22:38','超级管理员','超级管理员',0,2);
INSERT INTO `tb_product` VALUES (10004,10004,10000,'乳酸菌风味调味料','L5045','kg',200,'西仓','2023-07-22',1,'一条备注','2023-07-22 19:49:52','2024-12-15 21:13:35','超级管理员','超级管理员',0,3);
INSERT INTO `tb_product` VALUES (10005,10005,10005,'麻辣风味调味料','M6033','kg',800,'西仓','2024-12-16',1,'','2024-12-16 21:14:38','2024-12-16 21:14:38','超级管理员','超级管理员',0,1);
/*!40000 ALTER TABLE `tb_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_product_overview`
--

DROP TABLE IF EXISTS `tb_product_overview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_product_overview` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '产品',
  `product_code` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '货源',
  `produced_date_last` date DEFAULT NULL COMMENT '最近生产',
  `price_default` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '默认单价',
  `unit` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '单位',
  `number_per_box` int NOT NULL DEFAULT '0',
  `number` int NOT NULL DEFAULT '0' COMMENT '数量',
  `value` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '总价值',
  `number_tag` int NOT NULL DEFAULT '1' COMMENT '数量状态 1正常 2建议备货 3优先生产',
  `note` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  `version` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10006 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='产品总览表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product_overview`
--

LOCK TABLES `tb_product_overview` WRITE;
/*!40000 ALTER TABLE `tb_product_overview` DISABLE KEYS */;
INSERT INTO `tb_product_overview` VALUES (10000,'乳酸菌风味调味料','L5045-1','2023-07-22',23.00,'kg',20,200,4600.00,1,'','2023-07-22 19:40:24','2024-12-15 21:13:35','超级管理员','超级管理员',0,6);
INSERT INTO `tb_product_overview` VALUES (10001,'青柠风味调味料','L50398',NULL,24.00,'kg',30,0,0.00,3,'','2023-07-22 19:40:52','2024-12-16 21:13:53','超级管理员','超级管理员',0,3);
INSERT INTO `tb_product_overview` VALUES (10002,'苹果风味调味料','L50386-1','2023-07-21',23.00,'kg',25,1900,43700.00,1,'','2023-07-22 19:41:09','2024-12-16 21:03:50','超级管理员','超级管理员',0,10);
INSERT INTO `tb_product_overview` VALUES (10003,'葡萄风味调味料','L5042','2023-07-22',23.00,'kg',20,300,6900.00,2,'一条备注','2023-07-22 19:41:23','2023-07-22 20:11:36','超级管理员','超级管理员',0,8);
INSERT INTO `tb_product_overview` VALUES (10004,'荔枝风味调味料','L5043-1','2023-07-22',22.00,'kg',20,100,2200.00,1,'','2023-07-22 19:41:47','2024-12-16 21:22:38','超级管理员','超级管理员',0,4);
INSERT INTO `tb_product_overview` VALUES (10005,'麻辣风味调味料','M6033','2024-12-16',30.00,'kg',25,800,24000.00,1,'','2023-07-26 15:34:08','2024-12-16 21:14:38','超级管理员','超级管理员',0,2);
/*!40000 ALTER TABLE `tb_product_overview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_product_record`
--

DROP TABLE IF EXISTS `tb_product_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_product_record` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `batch_id` bigint DEFAULT NULL COMMENT '批次号',
  `product_id` bigint NOT NULL COMMENT '产品ID',
  `order_id` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '订单ID 当生产入库时没有该栏目',
  `product_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '产品名称',
  `product_code` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '产品编号',
  `unit` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '单位',
  `operate_type` int NOT NULL COMMENT '1生产 2出货 3手动调整 4删除',
  `operate_number` int NOT NULL DEFAULT '0' COMMENT '操作数量',
  `remain_number` int NOT NULL DEFAULT '0' COMMENT '本批余量',
  `origin_number` int NOT NULL DEFAULT '0',
  `produced_date` date DEFAULT NULL COMMENT '生产日期',
  `status` int NOT NULL DEFAULT '1' COMMENT '产品状态 1新鲜，2临期，3尽快使用',
  `customer` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '去向客户',
  `delivered_date` date DEFAULT NULL COMMENT '发货日期',
  `note` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  `version` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10017 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='产品记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product_record`
--

LOCK TABLES `tb_product_record` WRITE;
/*!40000 ALTER TABLE `tb_product_record` DISABLE KEYS */;
INSERT INTO `tb_product_record` VALUES (10000,10000,10002,NULL,'苹果风味调味料','L50383','kg',1,3000,3000,0,'2023-07-21',1,NULL,NULL,NULL,'2023-07-22 19:43:41','2023-07-22 19:43:41','超级管理员','超级管理员',0,1);
INSERT INTO `tb_product_record` VALUES (10001,10001,10003,NULL,'葡萄风味调味料','L5042','kg',1,100,100,0,'2023-07-21',1,NULL,NULL,NULL,'2023-07-22 19:44:46','2023-07-22 19:44:46','超级管理员','超级管理员',0,1);
INSERT INTO `tb_product_record` VALUES (10002,10002,10003,NULL,'葡萄风味调味料','L5042','kg',1,500,500,0,'2023-07-22',1,NULL,NULL,NULL,'2023-07-22 19:45:16','2023-07-22 19:45:16','超级管理员','超级管理员',0,1);
INSERT INTO `tb_product_record` VALUES (10003,10003,10004,NULL,'荔枝风味调味料','L5043-1','kg',1,200,200,0,'2023-07-22',1,NULL,NULL,NULL,'2023-07-22 19:46:19','2023-07-22 19:46:19','超级管理员','超级管理员',0,1);
INSERT INTO `tb_product_record` VALUES (10004,10004,10000,NULL,'乳酸菌风味调味料','L5045','kg',1,500,500,0,'2023-07-22',1,NULL,NULL,'一条备注','2023-07-22 19:49:53','2023-07-22 19:49:53','超级管理员','超级管理员',0,1);
INSERT INTO `tb_product_record` VALUES (10005,10004,10000,'20230722190410000N2Q','乳酸菌风味调味料','L5045','kg',2,100,400,500,'2023-07-22',1,'公司1','2023-07-22','一条备注','2023-07-22 20:02:14','2023-07-22 20:02:14','超级管理员','超级管理员',0,1);
INSERT INTO `tb_product_record` VALUES (10006,10000,10002,'20230722190410000N2Q','苹果风味调味料','L50383','kg',2,200,2800,3000,'2023-07-21',1,'公司1','2023-07-22',NULL,'2023-07-22 20:02:14','2023-07-22 20:02:14','超级管理员','超级管理员',0,1);
INSERT INTO `tb_product_record` VALUES (10007,10001,10003,'2023072220R110001VZ9','葡萄风味调味料','L5042','kg',2,100,0,100,'2023-07-21',1,'公司1','2023-07-22',NULL,'2023-07-22 20:04:42','2023-07-22 20:04:42','超级管理员','超级管理员',0,1);
INSERT INTO `tb_product_record` VALUES (10008,10002,10003,'2023072220R110001VZ9','葡萄风味调味料','L5042','kg',2,200,300,500,'2023-07-22',1,'公司1','2023-07-22',NULL,'2023-07-22 20:04:42','2023-07-22 20:04:42','超级管理员','超级管理员',0,1);
INSERT INTO `tb_product_record` VALUES (10009,10000,10002,'2023072220R110001VZ9','苹果风味调味料','L50383','kg',2,300,2500,2800,'2023-07-21',1,'公司1','2023-07-22',NULL,'2023-07-22 20:04:43','2023-07-22 20:04:43','超级管理员','超级管理员',0,2);
INSERT INTO `tb_product_record` VALUES (10010,10001,10003,NULL,'葡萄风味调味料','L5042','kg',4,0,0,0,'2023-07-21',1,NULL,NULL,NULL,'2023-07-22 20:05:55','2023-07-22 20:05:55','超级管理员','超级管理员',0,2);
INSERT INTO `tb_product_record` VALUES (10011,10000,10002,'2023072221JI10004762','苹果风味调味料','L50383','kg',2,200,2300,2500,'2023-07-21',1,'公司1','2023-07-22',NULL,'2023-07-22 21:10:23','2023-07-22 21:10:23','超级管理员','超级管理员',0,3);
INSERT INTO `tb_product_record` VALUES (10012,10000,10002,'2023072220PN10003N0Z','苹果风味调味料','L50383','kg',2,200,2100,2300,'2023-07-21',1,'公司1','2023-07-22',NULL,'2023-07-22 21:18:53','2023-07-22 21:18:53','超级管理员','超级管理员',0,4);
INSERT INTO `tb_product_record` VALUES (10013,10004,10000,'2024121521U810011N0S','乳酸菌风味调味料','L5045','kg',2,200,200,400,'2023-07-22',1,'公司1','2024-12-15','一条备注','2024-12-15 21:13:34','2024-12-15 21:13:34','超级管理员','超级管理员',0,2);
INSERT INTO `tb_product_record` VALUES (10014,10000,10002,'20241216215010012JOF','苹果风味调味料','L50383','kg',2,200,1900,2100,'2023-07-21',1,'公司1','2024-12-16',NULL,'2024-12-16 21:03:50','2024-12-16 21:03:50','超级管理员','超级管理员',0,5);
INSERT INTO `tb_product_record` VALUES (10015,10005,10005,NULL,'麻辣风味调味料','M6033','kg',1,800,800,0,'2024-12-16',1,NULL,NULL,'','2024-12-16 21:14:38','2024-12-16 21:14:38','超级管理员','超级管理员',0,1);
INSERT INTO `tb_product_record` VALUES (10016,10003,10004,'20241216215010012JOF','荔枝风味调味料','L5043-1','kg',2,100,100,200,'2023-07-22',1,'公司1','2024-12-16',NULL,'2024-12-16 21:22:38','2024-12-16 21:22:38','超级管理员','超级管理员',0,1);
/*!40000 ALTER TABLE `tb_product_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_purchase_record`
--

DROP TABLE IF EXISTS `tb_purchase_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_purchase_record` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_id` bigint NOT NULL,
  `category_id` bigint NOT NULL,
  `material_info_id` bigint NOT NULL,
  `category` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '品类',
  `supplier` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '货源',
  `number` int NOT NULL DEFAULT '0' COMMENT '货源',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '单价',
  `total_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '总金额',
  `location` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '仓储位置',
  `produced_date` date DEFAULT NULL COMMENT '生产日期',
  `status` int DEFAULT '1' COMMENT '货物状态 1新鲜，2临期，3尽快使用',
  `create_date` date DEFAULT NULL COMMENT '订单日期',
  `note` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `update_user` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT '0',
  `version` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10005 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='购买记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_purchase_record`
--

LOCK TABLES `tb_purchase_record` WRITE;
/*!40000 ALTER TABLE `tb_purchase_record` DISABLE KEYS */;
INSERT INTO `tb_purchase_record` VALUES (10000,10002,10003,10000,'辣椒','供货商1',500,10.00,5000.00,'',NULL,1,'2023-07-22','','2023-07-22 19:04:01','2023-07-22 19:04:01','超级管理员','超级管理员',0,1);
INSERT INTO `tb_purchase_record` VALUES (10001,10003,10003,10002,'辣椒','供货商2',100,11.00,1100.00,'东厂',NULL,1,'2023-07-22','测试备注','2023-07-22 19:05:51','2023-07-22 19:10:04','超级管理员','超级管理员',0,2);
INSERT INTO `tb_purchase_record` VALUES (10002,10004,10003,10000,'辣椒','供货商1',500,10.00,5000.00,'东厂','2023-07-09',1,'2023-07-22','','2023-07-22 19:42:43','2023-07-22 19:42:43','超级管理员','超级管理员',0,1);
INSERT INTO `tb_purchase_record` VALUES (10003,10005,10003,10000,'辣椒','供货商1',0,10.00,0.00,'','2024-12-11',1,'2024-12-15','','2024-12-15 18:29:43','2024-12-15 18:29:43','超级管理员','超级管理员',0,1);
INSERT INTO `tb_purchase_record` VALUES (10004,10006,10003,10000,'辣椒','供货商1',10,10.00,100.00,'',NULL,1,'2024-12-15','','2024-12-15 18:31:40','2024-12-15 18:31:40','超级管理员','超级管理员',0,1);
/*!40000 ALTER TABLE `tb_purchase_record` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-16 21:45:13
