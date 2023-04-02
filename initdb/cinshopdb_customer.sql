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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `e-mail` varchar(128) NOT NULL,
  `enable` tinyint DEFAULT '0',
  `first_name` varchar(64) NOT NULL,
  `image` varchar(64) DEFAULT NULL,
  `last_name` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `phone_number` varchar(25) DEFAULT NULL,
  `point` int DEFAULT NULL,
  `role` varchar(10) NOT NULL,
  `sex_id` int DEFAULT NULL,
  `address_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_135ynecdapff8a1sjtmsek2o9` (`e-mail`),
  KEY `FKa1alqq12mivohq6dtggb8v1dw` (`sex_id`),
  KEY `FKglkhkmh2vyn790ijs6hiqqpi` (`address_id`),
  CONSTRAINT `FKa1alqq12mivohq6dtggb8v1dw` FOREIGN KEY (`sex_id`) REFERENCES `sex` (`sex_id`),
  CONSTRAINT `FKglkhkmh2vyn790ijs6hiqqpi` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (6,'Abc',0,'Tran',NULL,'Chien','1672052193184','09012345678',NULL,'ROLE_GUEST',1,3),(7,'123@gmail.com',1,'Tom',NULL,'Guice','1672054727498','0900000000',NULL,'ROLE_GUEST',1,4),(9,'2',1,'チエン',NULL,'トラン　バン','1672193693383','08073686174',NULL,'ROLE_GUEST',1,6),(10,'8',1,'チエン',NULL,'トラン　バン','1672193984390','08073686174',NULL,'ROLE_GUEST',1,7),(12,'cin@gmail.com',1,'cin',NULL,'shop','1672564398475','0101111111',NULL,'ROLE_GUEST',1,37),(13,'cinshop@gmail.com',1,'cin',NULL,'shop','1672641426319','012011100022',NULL,'ROLE_GUEST',1,83),(15,'chient@gmail.com',1,'Tran Van','img/xxx.jpg','Chien','$2a$10$9XWQ.tWN0aIqdKNXhB2NY.AgJlFUnQOvuPzL1So51aH3DfkYuqgr2','08073686174',NULL,'ROLE_USER',1,85),(17,'ctct@gmail.com',1,'cin ','img/xxx.jpg','cin','$2a$10$MSLw3I1p3GNff22B5seeReaEBj9bsNFqaCnma64wRXK7z6mcTV3RO','090123456789',NULL,'ROLE_USER',1,87),(18,'chient36u9@gmail.com',1,'チエン','img/xxx.jpg','トラン　バン','$2a$10$QGzzU0qDkadpLgZ13M5a9u7IC/BSghzJ.8K7SX5o2B0DEjjZqejg.','08073686174',NULL,'ROLE_USER',1,88),(19,'Abc@gmail.com',0,'Tran van ',NULL,'Chien','1672658809379','0000000009',NULL,'ROLE_GUEST',1,89),(20,'Kktc@gmail.com',0,'ニアツト',NULL,'フアン','1672659045724','+817032584411',NULL,'ROLE_GUEST',1,90),(21,'tranhuyduc69@gmail.com',1,'CHIEN','img/xxx.jpg','Admin','$2a$10$ehw/3FJix6MFSTsDSeDRp.CX7BswfaKpu0JTq3U6eFaF8Tl7oFlU6','08073686174',0,'ROLE_USER',1,21),(22,'chient369@gmail.com',1,'チエン',NULL,'トラン　バン','$2a$10$eG5MplfCQNP9FHAr2Z3cuOdaMMogRui7cVRqufrCyvstfS0iJQK5i','08073686174',NULL,'ROLE_USER',1,165),(23,'chien@gmail.com',1,'チエン',NULL,'トラン　バン','1675489314709','08073686174',NULL,'ROLE_GUEST',1,125),(24,'chient4369@gmail.com',0,'チエン',NULL,'トラン　バン','1675489467877','08073686174',NULL,'ROLE_GUEST',1,126),(25,'hello,am',1,'asd','img/xxx.jpg','asd','$2a$10$gE9WnEzGVR7nAhAd2NbvDOs.X/Hz/2zft3mzbRE83rDIH3OKh625O','334342',0,'ROLE_USER',1,135),(26,'htlaosda',1,'sdfsdf','img/xxx.jpg','sfsdfsdf','$2a$10$bzRdYVttxML4Lhhrgo2rZuP3K4lf5e9wv.CdjgxmNjrP5MkELsWkK','08073686174',0,'ROLE_USER',1,136),(27,'chient36asda9@gmail.com',1,'チエン','img/xxx.jpg','ト','$2a$10$BvanD6miUv3F7Wtkh8Iex.xXQGmOwMGJKyIOwRFt5v8XH8uKv0gNW','08073686174',0,'ROLE_USER',1,137),(28,'ádsdfsd',NULL,'チエン',NULL,'トラン','1675949004313','08073686174',NULL,'ROLE_GUEST',1,138),(29,'chient369@gmail.coms',NULL,'チエン',NULL,'トラン　バン','1676289167472','08073686174',NULL,'ROLE_GUEST',1,141),(30,'ujg',NULL,'チエン',NULL,'トラン　バン','1676289248767','08073686174',NULL,'ROLE_GUEST',1,142),(33,'iwakiri0319@gmail.com',1,'イワキリ','img/xxx.jpg','ユウマ','$2a$10$XVrb5Nl0MBl3QS7HT4/3Q.UQ3vYp7X.fNaK5/0alEuJ2opmx.GaQm','000-0000-0000',0,'ROLE_USER',1,33),(34,'tranhuyduc6@gmail.com',1,'テスト','img/xxx.jpg','ユーザー','$2a$10$ZMCAdM6/E5xiEQBxxOAOBOo38MexumtX7LnhgLVW6Zz2DYoZTiH2e','080736861746',0,'ROLE_USER',1,166),(35,'ifsasaki1b@gmail.com',1,'佐々木','img/xxx.jpg','柾也','$2a$10$UYvklE/mr0Ls83z.rlLt0O3iqk977qP0zo1f9c1QLclygGuPT0Ram','55555555555',0,'ROLE_USER',1,167),(36,'canopus06tmtm@gmail.com',1,'ヒラノ','img/xxx.jpg','トモヤ','$2a$10$MCtM02OS1bSIOw9bNIUNkuXnPGGJdGll./F.Bo25B9PxkCq/0L3/6','12345678901',0,'ROLE_USER',1,168),(37,'なゆ',NULL,'かやかわ',NULL,'なやかわ','1676863808209','088553863966966666666',NULL,'ROLE_GUEST',1,169);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-02 21:29:43
