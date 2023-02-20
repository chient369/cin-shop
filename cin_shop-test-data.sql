
USE `cinshopdb`;
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
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'船橋市','kopo taki 202','273'),(3,'千葉県船橋市','202','2222-22'),(4,'東京都新宿区','穴1-12-3','273-0005'),(5,'船橋市','kopo taki 202','273'),(6,'船橋市','kopo taki 202','273'),(7,'船橋市','kopo taki 202','273'),(8,'船橋市','kopo taki 202','273'),(9,'船橋市','kopo taki 202','273'),(10,'船橋市','1','2735555'),(16,'船橋市','1','273'),(17,'船橋市','kopo taki 202','273'),(18,'船橋市','kopo taki 202','273'),(19,'千葉県船橋市本町','2-29-22コーポ滝202号','273-0005'),(20,'千葉県船橋市本町','2-29-22コーポ滝202号','2730005'),(21,'船橋市','2-29-22コーポ滝202号','273'),(22,'船橋市','2-29-22コーポ滝202号','273'),(23,'船橋市','kopo taki 202','2730005'),(24,'船橋市','2-29-22コーポ滝202号','2730005'),(25,'船橋市','2-29-22コーポ滝202号','273'),(26,'船橋市','2-29-22コーポ滝202号','2730005'),(27,'船橋市','2-29-22コーポ滝202号','273'),(28,'船橋市','2-29-22コーポ滝202号','2730005'),(29,'船橋市','2-29-22コーポ滝202号','2730005'),(30,'船橋市','2-29-22コーポ滝202号','2730005'),(31,'船橋市','kopo taki 202','273'),(32,'船橋市','2-29-22コーポ滝202号','273'),(33,'千葉県成田市','0-0-0','123-4567'),(34,'船橋市','2-29-22コーポ滝202号','2730005'),(35,'船橋市','2-29-22コーポ滝202号','2730005'),(36,'船橋市','kopo taki 202','2730005'),(37,'東京都','2-29-22コーポ滝202号','2730005'),(38,'船橋市','kopo taki 202','273'),(39,'船橋市','kopo taki 202','273'),(40,'船橋市','kopo taki 202','273'),(41,'船橋市','kopo taki 202','273'),(42,'船橋市','2-29-22コーポ滝202号','273'),(43,'船橋市','1','273'),(44,'船橋市','2-29-22コーポ滝202号','273'),(45,'船橋市','2-29-22コーポ滝202号','273'),(46,'船橋市','2-29-22コーポ滝202号','273'),(47,'船橋市','2-29-22コーポ滝202号','273'),(48,'船橋市','2-29-22コーポ滝202号','273'),(49,'船橋市','2-29-22コーポ滝202号','273'),(50,'船橋市','2-29-22コーポ滝202号','273'),(51,'船橋市','2-29-22コーポ滝202号','273'),(52,'船橋市','2-29-22コーポ滝202号','273'),(53,'船橋市','2-29-22コーポ滝202号','273'),(54,'船橋市','2-29-22コーポ滝202号','273'),(55,'船橋市','2-29-22コーポ滝202号','273'),(56,'船橋市','2-29-22コーポ滝202号','273'),(57,'船橋市','2-29-22コーポ滝202号','273'),(58,'船橋市','2-29-22コーポ滝202号','273'),(59,'船橋市','2-29-22コーポ滝202号','273'),(60,'船橋市','2-29-22コーポ滝202号','273'),(61,'船橋市','kopo taki 202','273'),(62,'船橋市','kopo taki 202','273'),(63,'船橋市','kopo taki 202','273'),(64,'船橋市','2-29-22コーポ滝202号','273'),(65,'船橋市','kopo taki 202','273'),(66,'船橋市','kopo taki 202','273'),(67,'船橋市','kopo taki 202','273'),(68,'船橋市','kopo taki 202','273'),(69,'船橋市','kopo taki 202','273'),(70,'船橋市','kopo taki 202','273'),(71,'船橋市','kopo taki 202','273'),(72,'船橋市','kopo taki 202','273'),(73,'船橋市','kopo taki 202','273'),(76,'船橋市','kopo taki 202','273'),(77,'船橋市','2-29-22コーポ滝202号','273'),(78,'船橋市','kopo taki 202','273'),(79,'船橋市','2-29-22コーポ滝202号','273'),(80,'船橋市','kopo taki 202','273'),(81,'船橋市','2-29-22コーポ滝202号','273'),(82,'船橋市','2-29-22コーポ滝202号','273'),(83,'船橋市','2-29-22コーポ滝202号','2730005'),(85,'船橋市本町','2-29-22','2730005'),(87,'船橋市本町','2-29-22','2730005'),(88,'千葉成田市·','1','2730009'),(89,'Chibaken','Kopo','237898'),(90,'千葉市稲毛区天台','21','263-0016'),(93,'船橋市','2-29-22コーポ滝202号','273'),(94,'船橋市','2-29-22コーポ滝202号','273'),(95,'船橋市','2-29-22コーポ滝202号','273'),(96,'船橋市','2-29-22コーポ滝202号','273'),(97,'船橋市','2-29-22コーポ滝202号','273'),(98,'船橋市','2-29-22コーポ滝202号','273'),(99,'船橋市','2-29-22コーポ滝202号','273'),(100,'船橋市','kopo taki 202','273'),(101,'船橋市','kopo taki 202','273'),(102,'船橋市','2-29-22コーポ滝202号','273'),(103,'船橋市','kopo taki 202','273'),(104,'船橋市','2-29-22コーポ滝202号','273'),(105,'船橋市','2-29-22コーポ滝202号','273'),(106,'船橋市','2-29-22コーポ滝202号','273'),(107,'船橋市','kopo taki 202','273'),(108,'船橋市','kopo taki 202','273'),(109,'船橋市','2-29-22コーポ滝202号','273'),(110,'船橋市','2-29-22コーポ滝202号','273'),(111,'船橋市','2-29-22コーポ滝202号','273'),(112,'船橋市','2-29-22コーポ滝202号','273'),(113,'船橋市','kopo taki 202','273'),(114,'船橋市','2-29-22コーポ滝202号','273'),(115,'船橋市','2-29-22コーポ滝202号','273'),(116,'船橋市','2-29-22コーポ滝202号','2730005'),(117,'船橋市','2-29-22コーポ滝202号','2730005'),(118,'船橋市','2-29-22コーポ滝202号','273'),(119,'船橋市','kopo taki 202','273'),(120,'船橋市','kopo taki 202','273'),(121,'船橋市','kopo taki 202','273'),(122,'船橋市','kopo taki 202','2730005'),(124,'船橋市','コーポ滝202号','273'),(125,'船橋市','2-29-22コーポ滝202号','273'),(126,'船橋市','kopo taki 202','273'),(127,'船橋市','2-29-22コーポ滝202号','273'),(130,'船橋市','2-29-22コーポ滝202号','273'),(132,'船橋市','2-29-22コーポ滝202号','273'),(133,'船橋市','kopo taki 202','273'),(134,'船橋市','2-29-22コーポ滝202号','273'),(135,'船橋市','コーポ滝202号','2730005'),(136,'船橋市','コーポ滝202号','2730005'),(137,'船橋市','コーポ滝202号','273'),(138,'船橋市','kopo taki 202','273'),(139,'船橋市','kopo taki 202','273'),(140,'船橋市','2-29-22コーポ滝202号','273'),(141,'船橋市','2-29-22コーポ滝202号','273'),(142,'船橋市','1','273'),(143,'船橋市','2-29-22コーポ滝202号','273'),(144,'船橋市','2-29-22コーポ滝202号','273'),(145,'船橋市','2-29-22コーポ滝202号','273'),(146,'船橋市','2-29-22コーポ滝202号','273'),(147,'船橋市','2-29-22コーポ滝202号','273'),(148,'船橋市','2-29-22コーポ滝202号','273'),(149,'船橋市','2-29-22コーポ滝202号','273'),(150,'船橋市','2-29-22コーポ滝202号','273'),(151,'船橋市','2-29-22コーポ滝202号','273'),(152,'船橋市','2-29-22コーポ滝202号','273'),(153,'船橋市','1','273'),(154,'船橋市','kopo taki 202','273'),(156,'千葉県成田市','0-0-0','123-4567'),(157,'千葉県成田市','0-0-0','123-4567'),(158,'a','a','a'),(159,'千葉県成田市','0-0-0','123-4567'),(160,'あ','あ','あ'),(161,'船橋市','2-29-22コーポ滝202号','273'),(162,'千葉県成田市','0-0-0','123-4567'),(163,'船橋市','1','273'),(164,'船橋市','1','273'),(165,'船橋市','1','273'),(166,'船橋市','1-2-29','2730005'),(167,'地球','千葉','5555555'),(168,'千葉県','1-1-1-1','1234567'),(169,'ゆゅ','ゆな','やぬ');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'Nike','https://www.abc-mart.net/new_img/top/brand_nike.png'),(2,'Adidas','https://www.abc-mart.net/new_img/top/brand_adidas.png'),(3,'Vans','https://www.abc-mart.net/new_img/top/brand_vans.png'),(4,'Fila','https://www.abc-mart.net/new_img/top/brand_fila.png'),(5,'Converse','https://www.abc-mart.net/new_img/top/brand_converse.png');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `cart_item`
--

LOCK TABLES `cart_item` WRITE;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
INSERT INTO `cart_item` VALUES (14,1,15,23),(84,1,22,16);
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (5,'カジュアル'),(1,'スニーカー'),(3,'スポーツ'),(4,'ビジネス'),(2,'ランニング');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `color`
--

LOCK TABLES `color` WRITE;
/*!40000 ALTER TABLE `color` DISABLE KEYS */;
INSERT INTO `color` VALUES (1,'ブラック','#000000'),(2,'ホワイト','#fffff8'),(3,'グレー','#b3b3b3'),(4,'ブルー','#4272d7'),(5,'レッド','#fa4251');
/*!40000 ALTER TABLE `color` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (1,'こんにちは、','tranhuyduc6@gmail.com','2023-02-18 22:45:23.230000',_binary '\0'),(2,'おはようございます！','cin@gmail.com','2023-02-18 22:45:57.565000',_binary '\0'),(3,'あざっす','chient@gmail.com','2023-02-18 22:47:04.029000',_binary '\0'),(4,'he numbers in the table specify the first browser version that fully supports the property.\r\n\r\nNumbers followed by -webkit- or -moz- specify the first version that worked with a prefix.','chient@gmail.com','2023-02-18 22:47:55.045000',_binary '\0'),(5,'he numbers in the table specify the first browser version that fully supports the property.\r\nNumbers followed by -webkit- or -moz- specify the first version that worked with a prefix.','cin@gmail.com','2023-02-18 22:50:02.380000',_binary '\0'),(6,'明日どのインターネットの回線使う？メール転送使えないので。','iwakiri0319@gmail.com','2023-02-19 23:14:43.703000',_binary '\0'),(7,'明日どのインターネットの回線使う？メール転送使えないので。','iwakiri0319@gmail.com','2023-02-19 23:14:53.709000',_binary '\0'),(8,'aaa','tranhuyduc6@gmail.com','2023-02-20 10:16:48.706000',_binary '\0'),(9,'aaa','tranhuyduc6@gmail.com','2023-02-20 10:53:23.337000',_binary '\0'),(10,'abc','tranhuyduc6@gmail.com','2023-02-20 12:18:14.727000',_binary '\0'),(11,'Hallo!','yoshida.iori1005@gmail.com','2023-02-20 12:27:20.345000',_binary '\0'),(12,'スマートフォンからでもかなり使い勝手が良くて驚きました。','sunflower.jasmine0718@gmail.com','2023-02-20 12:28:44.706000',_binary '\0');
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `credit`
--

LOCK TABLES `credit` WRITE;
/*!40000 ALTER TABLE `credit` DISABLE KEYS */;
INSERT INTO `credit` VALUES (1,'11','0011-12-01','chien tran van','111',21);
/*!40000 ALTER TABLE `credit` ENABLE KEYS */;
UNLOCK TABLES;

--略--
-- Dumping data for table `customer`
-
--
-- Dumping data for table `favorite_product`
--
--
-- Dumping data for table `order_detail`
--
--
-- Dumping data for table `orders`
--
--
-- Dumping data for table `payment`
--略--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,'BANK_TRANSFER','振込'),(2,'COMBINI','コンビニ払い'),(3,'COD','着払い'),(4,'CREDIT','クレジットカード');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (2,444,2,2,1),(6,100,1,5,1),(7,111,5,5,1),(8,11,2,5,2),(9,100,1,5,1),(10,111,5,5,1),(11,11,2,5,2),(12,100,1,5,1),(13,111,5,5,1),(14,11,2,5,2),(15,200,1,6,1),(16,160,2,6,2),(17,20,3,6,3),(19,200,3,6,2),(21,1,3,6,4),(22,NULL,4,6,5),(23,NULL,5,6,3),(24,1000,2,1,3),(25,200,1,15,1),(26,120,1,15,3),(27,300,1,15,6),(28,231,1,15,7),(29,360,1,15,5),(30,200,2,14,1),(31,200,3,14,5),(32,500,1,3,1),(33,60,1,10,1),(34,200,2,12,1),(35,200,2,13,2),(36,20,1,16,1),(37,2000,2,16,1),(38,20,1,16,2),(39,300,1,16,4),(40,200,1,17,1),(41,200,1,17,2),(42,20,1,17,4),(43,200,3,17,2),(44,20,3,17,4),(45,5000,1,19,1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product_detail`
--

LOCK TABLES `product_detail` WRITE;
/*!40000 ALTER TABLE `product_detail` DISABLE KEYS */;
INSERT INTO `product_detail` VALUES (1,'1212','nike jordan','jordan 0202',5,5,5000,'2022-12-12',0,'https://img.apim.abc-mart.biz/img/6024/6024850024/602485002401.jpg?sr.dw=713',0,_binary '\0'),(2,'144212','nike jordan','jordan 40202',1,1,5000,'2022-02-12',1,'https://img.apim.abc-mart.biz/img/6204/6204900010/620490001001.jpg?sr.dw=713',0,_binary '\0'),(3,'151551','エア ズーム ペガサス 39','ウィメンズモデル',1,1,14500,'2022-12-12',1,'https://img.apim.abc-mart.biz/img/6323/6323010005/632301000501.jpg?sr.dw=713',0,_binary '\0'),(5,'55555','ブレーザー','時代を超えるシンプルなシルエット\r\nupdated',5,2,10000,'2022-12-13',1,'https://img.apim.abc-mart.biz/img/6359/6359110001/635911000101.jpg?sr.dw=713',0,_binary '\0'),(6,'9999999','エア ズーム ペガサス 39','2000年代に登場したフィットネスランニングシューズを洗練されたファッションアイテムとして提案する「MR530」は、曲線を活かした流麗なアッパーデザイン、快適な履き心地を提供するABZORB搭載ミッドソールを採用した人気のモデルです。\r\n春夏シーズンに映えるシンセティックレザー/メッシュのコンビに春らしい色見にグレーの差し色が特徴なY2Kファッションの足元をレトロ＆スポーティに彩る。',1,1,14000,'2022-12-13',1,' https://img.apim.abc-mart.biz/img/6296/6296360005/629636000501.jpg?sr.dw=713 ',0,_binary '\0'),(10,'538937q3','addidas','asdadasdasd',1,5,1000044,'2023-02-24',1,'/product-images/_mJdRX20J',0,_binary '\0'),(11,'514616rr','gygy','rthfg',1,5,1000044,'2023-02-17',1,'/product-images/_JJpskhoY',0,_binary '\0'),(12,'6360890001043','フォーラムボールドJ','ABC-MART限定商品\r\n1984年にバスケットボールシューズとして登場したFORUM（フォーラム）に、厚底のソールを搭載させたレディースモデル「FORUM BOLD J（フォーラム ボールド J）」。\r\nシンプルな見た目ながらシュージュエル、サイドのトレフォイルロゴ、玉虫色に輝くヒールパッチ等、随所にアクセントを施したユニークな1足。',2,1,10670,'2023-02-19',1,'https://img.apim.abc-mart.biz/img/6360/6360890001/636089000101.jpg',0,_binary '\0'),(13,'6371440001043','スタンスミスJ','高い耐久性で広く知られているデニム素材とアイコニックなコートシューズのシルエットを融合したスタンスミス。\r\n上質な素材とクラフトマンシップが合わさり、合成皮革アッパー、ラバーアウトソール、メタリックゴールドのディテールをあしらった、このデザインが生まれた。\r\nこのタイムレスなルックスは50年以上にわたりチューニングされてきた、まさにデイリーに履けるシューズ。\r\nそして、永遠に続くデザイン。\r\n限りある資源を守り、プラスチック廃棄物を削減するというアディダスの取り組みの一環として、天然素材と再生可能素材で作られたシューズ。',2,1,8470,'2023-02-19',1,'https://img.apim.abc-mart.biz/img/6371/6371440001/637144000101.jpg',0,_binary '\0'),(14,'6288260001013','マーベリック','軽量感が特徴のVANS 新定番「CLASSIC LITE」にMARVERICK(マーベリック)がラインナップ。従来の定番品と比較し、軽量なEVAカップソールを搭載することで柔らかく弾力性があり、クッショニングUP。柔らかなレザー素材とニュアンスカラーのコンビネーションが上品なコーディネートのポイント。',3,5,8800,'2023-02-19',1,'https://img.apim.abc-mart.biz/img/6288/6288260001/628826000101.jpg',0,_binary '\0'),(15,'5940500001068','ステファノロッシ R S-T','ヨーロピアンナイズドのラウンドトゥーが新鮮なSTEFANO ROSSIの新作。ツヤ感のあるレザーが特徴です。\r\nキレイ目な内羽根S-TIPはビジネス・フォーマルシーンにおいて大活躍の1足になります。レザーアッパーでありながらこの価格は魅力的で、インソールに足当たりの良いフォームクッションを施しております。\r\n※ガラスレザーはレザーの表面に樹脂赤穂を施したレザーです。ケアがほぼ不要というメリットはございますが、経年変化からくるシワによるひびは防ぐことができませんので予めご了承ください。\r\n商品特徴---450g(片足EU42)、ヨーロピアンラウンドラスト、ガラスレザー、レザーライニング\r\n素材---ライニング:ファブリック+ピグスキン、インソール:ピグスキン＋フォーム',5,4,8790,'2023-02-19',1,' https://img.apim.abc-mart.biz/img/5940/5940500001/594050000101.jpg?sr.dw=713 ',0,_binary '\0'),(16,'6342160001049','ウルトラバウンス ワイド','足裏全体に配置したBounceミッドソールが、どれだけ走ってもバネのような反発力でエネルギッシュな走りを支える。\r\nまた、丈夫でグリップ力に優れたラバーアウトソールが、安定した足運びに貢献。\r\nこの製品は、アッパーに50%以上のリサイクル素材を使用するなど、プラスチック廃棄物ゼロを目指すアディダスの取り組みの一つをカタチにしたもの。',2,3,9350,'2023-02-25',1,'https://img.apim.abc-mart.biz/img/6342/6342160001/634216000101.jpg',0,_binary '\0'),(17,'6158450001045','フィラカタパルト','デイリーに使いやすいクッショニングタイプのランニングシューズ。\r\n踵にあしらったFILAのデザイングラフィックがアクセント。',4,3,5489,'2023-02-19',1,'https://img.apim.abc-mart.biz/img/6158/6158450001/615845000101.jpg',0,_binary '\0'),(18,'6316650001039','キッズ コアファイト','休み時間に誰よりも早く校庭に飛び出したり、ソフトボールのキャンプに出かけたり、自宅の庭で遊んだりするときも、この耐久性。\r\nサポート力に優れたキッズ用スニーカーが快適な履き心地を提供。\r\n通気性を高めるメッシュアッパーが、一日中動き回るキッズの足を爽やかに保つ。\r\nプラスチック廃棄物ゼロを目指すアディダスの取り組みの一つをカタチにした、リサイクル素材を50%以上使用したアイテム。\r\n\r\nリサイクル素材を一部使用した、元気なキッズ用デイリースニーカー。\r\n\r\n〇レギュラーフィット\r\n〇伸縮性シューレース/面ファスナーストラップ\r\n〇メッシュアッパー/サポート力を高める縫い目のないディテール\r\n〇テキスタイルライニング\r\n〇補強されたアイステイ\r\n〇立体的なミッドソール',2,3,10000,'2023-02-19',1,'https://img.apim.abc-mart.biz/img/6316/6316650001/631665000101.jpg',0,_binary '\0'),(19,'5665','demo ','test product',3,1,5000,'2023-02-20',1,'/product-images/_fay7kW20',0,_binary '\0');
/*!40000 ALTER TABLE `product_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product_image`
--

LOCK TABLES `product_image` WRITE;
/*!40000 ALTER TABLE `product_image` DISABLE KEYS */;
INSERT INTO `product_image` VALUES (14,'https://img.apim.abc-mart.biz/img/6296/6296360005/629636000502.jpg?sr.dw=713',6),(16,'https://img.apim.abc-mart.biz/img/6296/6296360005/629636000501.jpg?sr.dw=713',6),(19,'https://img.apim.abc-mart.biz/img/6296/6296360005/629636000503.jpg?sr.dw=713',6),(20,'https://img.apim.abc-mart.biz/img/6296/6296360005/629636000504.jpg?sr.dw=713',6),(45,'/product-images/6_uMbpn-vans.png',6),(46,'/product-images/1_fvwQH-admin.jpg',1),(55,'/product-images/10_1iHv6ZI4',10),(56,'/product-images/10_h8Uw45B3',10),(57,'/product-images/10_ZA1ajLch',10),(58,'/product-images/10_bsARPWu3',10),(59,'/product-images/11_4BBnDojK',11),(65,'https://img.apim.abc-mart.biz/img/6360/6360890001/636089000102.jpg',12),(66,'https://img.apim.abc-mart.biz/img/6360/6360890001/636089000103.jpg',12),(67,'https://img.apim.abc-mart.biz/img/6360/6360890001/636089000105.jpg',12),(68,'https://img.apim.abc-mart.biz/img/6360/6360890001/636089000106.jpg',12),(69,'https://img.apim.abc-mart.biz/img/6360/6360890001/636089000107.jpg',12),(70,'https://img.apim.abc-mart.biz/img/6371/6371440001/637144000101.jpg',13),(71,'https://img.apim.abc-mart.biz/img/6371/6371440001/637144000105.jpg',13),(72,'https://img.apim.abc-mart.biz/img/6371/6371440001/637144000104.jpg',13),(73,'https://img.apim.abc-mart.biz/img/6371/6371440001/637144000102.jpg',13),(74,'https://img.apim.abc-mart.biz/img/6288/6288260001/628826000107.jpg',14),(75,'https://img.apim.abc-mart.biz/img/6288/6288260001/628826000106.jpg',14),(76,'https://img.apim.abc-mart.biz/img/6288/6288260001/628826000105.jpg',14),(77,'https://img.apim.abc-mart.biz/img/6288/6288260001/628826000104.jpg',14),(78,'https://img.apim.abc-mart.biz/img/6288/6288260001/628826000103.jpg',14),(79,'https://img.apim.abc-mart.biz/img/6288/6288260001/628826000101.jpg',14),(80,'https://img.apim.abc-mart.biz/img/5940/5940500001/594050000102.jpg',15),(81,'https://img.apim.abc-mart.biz/img/5940/5940500001/594050000103.jpg',15),(82,'https://img.apim.abc-mart.biz/img/5940/5940500001/594050000104.jpg',15),(83,'https://img.apim.abc-mart.biz/img/5940/5940500001/594050000105.jpg',15),(84,'https://img.apim.abc-mart.biz/img/5940/5940500001/594050000106.jpg',15),(85,'https://img.apim.abc-mart.biz/img/6342/6342160001/634216000102.jpg',16),(86,'https://img.apim.abc-mart.biz/img/6342/6342160001/634216000103.jpg',16),(87,'https://img.apim.abc-mart.biz/img/6342/6342160001/634216000104.jpg',16),(88,'https://img.apim.abc-mart.biz/img/6342/6342160001/634216000105.jpg',16),(89,'https://img.apim.abc-mart.biz/img/6342/6342160001/634216000106.jpg',16),(90,'https://img.apim.abc-mart.biz/img/6158/6158450001/615845000102.jpg',17),(91,'https://img.apim.abc-mart.biz/img/6158/6158450001/615845000101.jpg',17),(92,'https://img.apim.abc-mart.biz/img/6158/6158450001/615845000103.jpg',17),(93,'https://img.apim.abc-mart.biz/img/6158/6158450001/615845000104.jpg',17),(94,'https://img.apim.abc-mart.biz/img/6158/6158450001/615845000105.jpg',17),(95,'https://img.apim.abc-mart.biz/img/6316/6316650001/631665000102.jpg',18),(96,'https://img.apim.abc-mart.biz/img/6316/6316650001/631665000101.jp',18),(97,'https://img.apim.abc-mart.biz/img/6316/6316650001/631665000103.jpg',18),(98,'https://img.apim.abc-mart.biz/img/6316/6316650001/631665000104.jpg',18),(99,'https://img.apim.abc-mart.biz/img/6316/6316650001/631665000105.jpg',18),(100,'/product-images/19_aT0vCFR0',19);
/*!40000 ALTER TABLE `product_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,'its is good shoes. i like it;',5,6,22),(2,'it is so bad for me.',1,6,22),(3,'vjhvhjvb',0,6,22),(4,'てすとてすとー',4,6,33),(5,'aaaaa',3,6,34);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sex`
--

LOCK TABLES `sex` WRITE;
/*!40000 ALTER TABLE `sex` DISABLE KEYS */;
INSERT INTO `sex` VALUES (1,'男'),(2,'女'),(3,'他');
/*!40000 ALTER TABLE `sex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `size`
--

LOCK TABLES `size` WRITE;
/*!40000 ALTER TABLE `size` DISABLE KEYS */;
INSERT INTO `size` VALUES (1,23),(2,23.5),(3,24),(4,24.5),(5,25),(6,25.5),(7,26),(8,26.5),(9,27),(10,27.5),(11,28);
/*!40000 ALTER TABLE `size` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tax`
--

LOCK TABLES `tax` WRITE;
/*!40000 ALTER TABLE `tax` DISABLE KEYS */;
INSERT INTO `tax` VALUES (31,'2020-10-01',8);
/*!40000 ALTER TABLE `tax` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

/*ユーザーに権限を付与*/

grant select on cinshopdb.brand to 'cinshop_user'@'localhost';
grant select on cinshopdb.category to 'cinshop_user'@'localhost';
grant select on cinshopdb.color to 'cinshop_user'@'localhost';
grant select on cinshopdb.size to 'cinshop_user'@'localhost';
grant select on cinshopdb.tax to 'cinshop_user'@'localhost';
grant select on cinshopdb.payment to 'cinshop_user'@'localhost';
grant select on cinshopdb.sex to 'cinshop_user'@'localhost';

grant select on cinshopdb.product to 'cinshop_user'@'localhost';
grant select on cinshopdb.product_detail to 'cinshop_user'@'localhost';
grant select on cinshopdb.product_image to 'cinshop_user'@'localhost';

grant insert on cinshopdb.contact to 'cinshop_user'@'localhost'

grant select,insert,update,delete on cinshopdb.cart_item to 'cinshop_user'@'localhost';
grant select,insert,update,delete on cinshopdb.favorite_product to 'cinshop_user'@'localhost';
grant select,insert,update,delete on cinshopdb.credit to 'cinshop_user'@'localhost';
grant select,insert,update,delete on cinshopdb.review to 'cinshop_user'@'localhost';

grant select,insert on cinshopdb.order_detail to 'cinshop_user'@'localhost';
grant select,insert on cinshopdb.orders to 'cinshop_user'@'localhost';

grant select,insert,update on cinshopdb.customer to 'cinshop_user'@'localhost';
grant select,insert,update on cinshopdb.address to 'cinshop_user'@'localhost';

-- Admin の権限
grant all privileges on cinshopdb.* to 'cinshop_admin'@'localhost';

##ShopAdmin

-- Dump completed on 2023-02-20 13:26:09
