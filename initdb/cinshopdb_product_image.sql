-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cinshopdb
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `product_image`
--

DROP TABLE IF EXISTS `product_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_image` (
  `image_id` int NOT NULL AUTO_INCREMENT,
  `image_name` varchar(256) NOT NULL,
  `detail_id` int DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  UNIQUE KEY `UK_b99hpiih6os61169kt1bpj610` (`image_name`),
  KEY `FKlrefhimoq7iklb2g1wcboe8an` (`detail_id`),
  CONSTRAINT `FKlrefhimoq7iklb2g1wcboe8an` FOREIGN KEY (`detail_id`) REFERENCES `product_detail` (`detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_image`
--

LOCK TABLES `product_image` WRITE;
/*!40000 ALTER TABLE `product_image` DISABLE KEYS */;
INSERT INTO `product_image` VALUES (14,'https://img.apim.abc-mart.biz/img/6296/6296360005/629636000502.jpg?sr.dw=713',6),(16,'https://img.apim.abc-mart.biz/img/6296/6296360005/629636000501.jpg?sr.dw=713',6),(19,'https://img.apim.abc-mart.biz/img/6296/6296360005/629636000503.jpg?sr.dw=713',6),(20,'https://img.apim.abc-mart.biz/img/6296/6296360005/629636000504.jpg?sr.dw=713',6),(45,'/product-images/6_uMbpn-vans.png',6),(46,'/product-images/1_fvwQH-admin.jpg',1),(55,'/product-images/10_1iHv6ZI4',10),(56,'/product-images/10_h8Uw45B3',10),(57,'/product-images/10_ZA1ajLch',10),(58,'/product-images/10_bsARPWu3',10),(59,'/product-images/11_4BBnDojK',11),(65,'https://img.apim.abc-mart.biz/img/6360/6360890001/636089000102.jpg',12),(66,'https://img.apim.abc-mart.biz/img/6360/6360890001/636089000103.jpg',12),(67,'https://img.apim.abc-mart.biz/img/6360/6360890001/636089000105.jpg',12),(68,'https://img.apim.abc-mart.biz/img/6360/6360890001/636089000106.jpg',12),(69,'https://img.apim.abc-mart.biz/img/6360/6360890001/636089000107.jpg',12),(70,'https://img.apim.abc-mart.biz/img/6371/6371440001/637144000101.jpg',13),(71,'https://img.apim.abc-mart.biz/img/6371/6371440001/637144000105.jpg',13),(72,'https://img.apim.abc-mart.biz/img/6371/6371440001/637144000104.jpg',13),(73,'https://img.apim.abc-mart.biz/img/6371/6371440001/637144000102.jpg',13),(74,'https://img.apim.abc-mart.biz/img/6288/6288260001/628826000107.jpg',14),(75,'https://img.apim.abc-mart.biz/img/6288/6288260001/628826000106.jpg',14),(76,'https://img.apim.abc-mart.biz/img/6288/6288260001/628826000105.jpg',14),(77,'https://img.apim.abc-mart.biz/img/6288/6288260001/628826000104.jpg',14),(78,'https://img.apim.abc-mart.biz/img/6288/6288260001/628826000103.jpg',14),(79,'https://img.apim.abc-mart.biz/img/6288/6288260001/628826000101.jpg',14),(80,'https://img.apim.abc-mart.biz/img/5940/5940500001/594050000102.jpg',15),(81,'https://img.apim.abc-mart.biz/img/5940/5940500001/594050000103.jpg',15),(82,'https://img.apim.abc-mart.biz/img/5940/5940500001/594050000104.jpg',15),(83,'https://img.apim.abc-mart.biz/img/5940/5940500001/594050000105.jpg',15),(84,'https://img.apim.abc-mart.biz/img/5940/5940500001/594050000106.jpg',15),(85,'https://img.apim.abc-mart.biz/img/6342/6342160001/634216000102.jpg',16),(86,'https://img.apim.abc-mart.biz/img/6342/6342160001/634216000103.jpg',16),(87,'https://img.apim.abc-mart.biz/img/6342/6342160001/634216000104.jpg',16),(88,'https://img.apim.abc-mart.biz/img/6342/6342160001/634216000105.jpg',16),(89,'https://img.apim.abc-mart.biz/img/6342/6342160001/634216000106.jpg',16),(90,'https://img.apim.abc-mart.biz/img/6158/6158450001/615845000102.jpg',17),(91,'https://img.apim.abc-mart.biz/img/6158/6158450001/615845000101.jpg',17),(92,'https://img.apim.abc-mart.biz/img/6158/6158450001/615845000103.jpg',17),(93,'https://img.apim.abc-mart.biz/img/6158/6158450001/615845000104.jpg',17),(94,'https://img.apim.abc-mart.biz/img/6158/6158450001/615845000105.jpg',17),(95,'https://img.apim.abc-mart.biz/img/6316/6316650001/631665000102.jpg',18),(96,'https://img.apim.abc-mart.biz/img/6316/6316650001/631665000101.jp',18),(97,'https://img.apim.abc-mart.biz/img/6316/6316650001/631665000103.jpg',18),(98,'https://img.apim.abc-mart.biz/img/6316/6316650001/631665000104.jpg',18),(99,'https://img.apim.abc-mart.biz/img/6316/6316650001/631665000105.jpg',18),(100,'/product-images/19_aT0vCFR0',19);
/*!40000 ALTER TABLE `product_image` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-02 21:29:42
