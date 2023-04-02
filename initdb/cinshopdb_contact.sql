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
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(4096) NOT NULL,
  `email` varchar(128) NOT NULL,
  `received_time` datetime(6) DEFAULT NULL,
  `is_replied` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (1,'こんにちは、','tranhuyduc6@gmail.com','2023-02-18 22:45:23.230000',_binary '\0'),(2,'おはようございます！','cin@gmail.com','2023-02-18 22:45:57.565000',_binary '\0'),(3,'あざっす','chient@gmail.com','2023-02-18 22:47:04.029000',_binary '\0'),(4,'he numbers in the table specify the first browser version that fully supports the property.\r\n\r\nNumbers followed by -webkit- or -moz- specify the first version that worked with a prefix.','chient@gmail.com','2023-02-18 22:47:55.045000',_binary '\0'),(5,'he numbers in the table specify the first browser version that fully supports the property.\r\nNumbers followed by -webkit- or -moz- specify the first version that worked with a prefix.','cin@gmail.com','2023-02-18 22:50:02.380000',_binary '\0'),(6,'明日どのインターネットの回線使う？メール転送使えないので。','iwakiri0319@gmail.com','2023-02-19 23:14:43.703000',_binary '\0'),(7,'明日どのインターネットの回線使う？メール転送使えないので。','iwakiri0319@gmail.com','2023-02-19 23:14:53.709000',_binary '\0'),(8,'aaa','tranhuyduc6@gmail.com','2023-02-20 10:16:48.706000',_binary '\0'),(9,'aaa','tranhuyduc6@gmail.com','2023-02-20 10:53:23.337000',_binary '\0'),(10,'abc','tranhuyduc6@gmail.com','2023-02-20 12:18:14.727000',_binary '\0'),(11,'Hallo!','yoshida.iori1005@gmail.com','2023-02-20 12:27:20.345000',_binary '\0'),(12,'スマートフォンからでもかなり使い勝手が良くて驚きました。','sunflower.jasmine0718@gmail.com','2023-02-20 12:28:44.706000',_binary '\0');
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
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
