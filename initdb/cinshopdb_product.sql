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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `stock_amount` int DEFAULT NULL,
  `color_id` int DEFAULT NULL,
  `detail_id` int DEFAULT NULL,
  `size_id` int DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `FKjb0cih1rr1wsbsxmjo7bcd45i` (`detail_id`),
  KEY `FK7j8aci4xn0sahyhxk0fvqql6e` (`color_id`),
  KEY `FKsccbu8jiglqc6t5tjsp04amv7` (`size_id`),
  CONSTRAINT `FK7j8aci4xn0sahyhxk0fvqql6e` FOREIGN KEY (`color_id`) REFERENCES `color` (`color_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKjb0cih1rr1wsbsxmjo7bcd45i` FOREIGN KEY (`detail_id`) REFERENCES `product_detail` (`detail_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKsccbu8jiglqc6t5tjsp04amv7` FOREIGN KEY (`size_id`) REFERENCES `size` (`size_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (2,444,2,2,1),(6,100,1,5,1),(7,111,5,5,1),(8,11,2,5,2),(9,100,1,5,1),(10,111,5,5,1),(11,11,2,5,2),(12,100,1,5,1),(13,111,5,5,1),(14,11,2,5,2),(15,200,1,6,1),(16,160,2,6,2),(17,20,3,6,3),(19,200,3,6,2),(21,1,3,6,4),(22,NULL,4,6,5),(23,NULL,5,6,3),(24,1000,2,1,3),(25,200,1,15,1),(26,120,1,15,3),(27,300,1,15,6),(28,231,1,15,7),(29,360,1,15,5),(30,200,2,14,1),(31,200,3,14,5),(32,500,1,3,1),(33,60,1,10,1),(34,200,2,12,1),(35,200,2,13,2),(36,20,1,16,1),(37,2000,2,16,1),(38,20,1,16,2),(39,300,1,16,4),(40,200,1,17,1),(41,200,1,17,2),(42,20,1,17,4),(43,200,3,17,2),(44,20,3,17,4),(45,5000,1,19,1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-02 21:29:41
