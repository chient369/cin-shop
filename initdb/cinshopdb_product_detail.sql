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
-- Table structure for table `product_detail`
--

DROP TABLE IF EXISTS `product_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_detail` (
  `detail_id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(15) NOT NULL,
  `name` varchar(256) NOT NULL,
  `description` longtext,
  `brand_id` int DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `price` int NOT NULL,
  `create_time` date NOT NULL,
  `enable` tinyint(1) DEFAULT '1',
  `main_image` varchar(256) NOT NULL,
  `avg_vote` float NOT NULL,
  `favorite_checked` bit(1) NOT NULL,
  PRIMARY KEY (`detail_id`),
  UNIQUE KEY `UK_ndx952w0v9kxawibxhciotx1w` (`code`),
  KEY `FKn29xx33y0vxapbc6ntf4kldxr` (`brand_id`),
  KEY `FKt8wqc6kjeeis257idvcbjvax3` (`category_id`),
  CONSTRAINT `FKn29xx33y0vxapbc6ntf4kldxr` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`brand_id`),
  CONSTRAINT `FKt8wqc6kjeeis257idvcbjvax3` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_detail`
--

LOCK TABLES `product_detail` WRITE;
/*!40000 ALTER TABLE `product_detail` DISABLE KEYS */;
INSERT INTO `product_detail` VALUES (1,'1212','nike jordan','jordan 0202',5,5,5000,'2022-12-12',0,'https://img.apim.abc-mart.biz/img/6024/6024850024/602485002401.jpg?sr.dw=713',0,_binary '\0'),(2,'144212','nike jordan','jordan 40202',1,1,5000,'2022-02-12',1,'https://img.apim.abc-mart.biz/img/6204/6204900010/620490001001.jpg?sr.dw=713',0,_binary '\0'),(3,'151551','エア ズーム ペガサス 39','ウィメンズモデル',1,1,14500,'2022-12-12',1,'https://img.apim.abc-mart.biz/img/6323/6323010005/632301000501.jpg?sr.dw=713',0,_binary '\0'),(5,'55555','ブレーザー','時代を超えるシンプルなシルエット\r\nupdated',5,2,10000,'2022-12-13',1,'https://img.apim.abc-mart.biz/img/6359/6359110001/635911000101.jpg?sr.dw=713',0,_binary '\0'),(6,'9999999','エア ズーム ペガサス 39','2000年代に登場したフィットネスランニングシューズを洗練されたファッションアイテムとして提案する「MR530」は、曲線を活かした流麗なアッパーデザイン、快適な履き心地を提供するABZORB搭載ミッドソールを採用した人気のモデルです。\r\n春夏シーズンに映えるシンセティックレザー/メッシュのコンビに春らしい色見にグレーの差し色が特徴なY2Kファッションの足元をレトロ＆スポーティに彩る。',1,1,14000,'2022-12-13',1,' https://img.apim.abc-mart.biz/img/6296/6296360005/629636000501.jpg?sr.dw=713 ',0,_binary '\0'),(10,'538937q3','addidas','asdadasdasd',1,5,1000044,'2023-02-24',1,'/product-images/_mJdRX20J',0,_binary '\0'),(11,'514616rr','gygy','rthfg',1,5,1000044,'2023-02-17',1,'/product-images/_JJpskhoY',0,_binary '\0'),(12,'6360890001043','フォーラムボールドJ','ABC-MART限定商品\r\n1984年にバスケットボールシューズとして登場したFORUM（フォーラム）に、厚底のソールを搭載させたレディースモデル「FORUM BOLD J（フォーラム ボールド J）」。\r\nシンプルな見た目ながらシュージュエル、サイドのトレフォイルロゴ、玉虫色に輝くヒールパッチ等、随所にアクセントを施したユニークな1足。',2,1,10670,'2023-02-19',1,'https://img.apim.abc-mart.biz/img/6360/6360890001/636089000101.jpg',0,_binary '\0'),(13,'6371440001043','スタンスミスJ','高い耐久性で広く知られているデニム素材とアイコニックなコートシューズのシルエットを融合したスタンスミス。\r\n上質な素材とクラフトマンシップが合わさり、合成皮革アッパー、ラバーアウトソール、メタリックゴールドのディテールをあしらった、このデザインが生まれた。\r\nこのタイムレスなルックスは50年以上にわたりチューニングされてきた、まさにデイリーに履けるシューズ。\r\nそして、永遠に続くデザイン。\r\n限りある資源を守り、プラスチック廃棄物を削減するというアディダスの取り組みの一環として、天然素材と再生可能素材で作られたシューズ。',2,1,8470,'2023-02-19',1,'https://img.apim.abc-mart.biz/img/6371/6371440001/637144000101.jpg',0,_binary '\0'),(14,'6288260001013','マーベリック','軽量感が特徴のVANS 新定番「CLASSIC LITE」にMARVERICK(マーベリック)がラインナップ。従来の定番品と比較し、軽量なEVAカップソールを搭載することで柔らかく弾力性があり、クッショニングUP。柔らかなレザー素材とニュアンスカラーのコンビネーションが上品なコーディネートのポイント。',3,5,8800,'2023-02-19',1,'https://img.apim.abc-mart.biz/img/6288/6288260001/628826000101.jpg',0,_binary '\0'),(15,'5940500001068','ステファノロッシ R S-T','ヨーロピアンナイズドのラウンドトゥーが新鮮なSTEFANO ROSSIの新作。ツヤ感のあるレザーが特徴です。\r\nキレイ目な内羽根S-TIPはビジネス・フォーマルシーンにおいて大活躍の1足になります。レザーアッパーでありながらこの価格は魅力的で、インソールに足当たりの良いフォームクッションを施しております。\r\n※ガラスレザーはレザーの表面に樹脂赤穂を施したレザーです。ケアがほぼ不要というメリットはございますが、経年変化からくるシワによるひびは防ぐことができませんので予めご了承ください。\r\n商品特徴---450g(片足EU42)、ヨーロピアンラウンドラスト、ガラスレザー、レザーライニング\r\n素材---ライニング:ファブリック+ピグスキン、インソール:ピグスキン＋フォーム',5,4,8790,'2023-02-19',1,' https://img.apim.abc-mart.biz/img/5940/5940500001/594050000101.jpg?sr.dw=713 ',0,_binary '\0'),(16,'6342160001049','ウルトラバウンス ワイド','足裏全体に配置したBounceミッドソールが、どれだけ走ってもバネのような反発力でエネルギッシュな走りを支える。\r\nまた、丈夫でグリップ力に優れたラバーアウトソールが、安定した足運びに貢献。\r\nこの製品は、アッパーに50%以上のリサイクル素材を使用するなど、プラスチック廃棄物ゼロを目指すアディダスの取り組みの一つをカタチにしたもの。',2,3,9350,'2023-02-25',1,'https://img.apim.abc-mart.biz/img/6342/6342160001/634216000101.jpg',0,_binary '\0'),(17,'6158450001045','フィラカタパルト','デイリーに使いやすいクッショニングタイプのランニングシューズ。\r\n踵にあしらったFILAのデザイングラフィックがアクセント。',4,3,5489,'2023-02-19',1,'https://img.apim.abc-mart.biz/img/6158/6158450001/615845000101.jpg',0,_binary '\0'),(18,'6316650001039','キッズ コアファイト','休み時間に誰よりも早く校庭に飛び出したり、ソフトボールのキャンプに出かけたり、自宅の庭で遊んだりするときも、この耐久性。\r\nサポート力に優れたキッズ用スニーカーが快適な履き心地を提供。\r\n通気性を高めるメッシュアッパーが、一日中動き回るキッズの足を爽やかに保つ。\r\nプラスチック廃棄物ゼロを目指すアディダスの取り組みの一つをカタチにした、リサイクル素材を50%以上使用したアイテム。\r\n\r\nリサイクル素材を一部使用した、元気なキッズ用デイリースニーカー。\r\n\r\n〇レギュラーフィット\r\n〇伸縮性シューレース/面ファスナーストラップ\r\n〇メッシュアッパー/サポート力を高める縫い目のないディテール\r\n〇テキスタイルライニング\r\n〇補強されたアイステイ\r\n〇立体的なミッドソール',2,3,10000,'2023-02-19',1,'https://img.apim.abc-mart.biz/img/6316/6316650001/631665000101.jpg',0,_binary '\0'),(19,'5665','demo ','test product',3,1,5000,'2023-02-20',1,'/product-images/_fay7kW20',0,_binary '\0');
/*!40000 ALTER TABLE `product_detail` ENABLE KEYS */;
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
