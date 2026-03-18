CREATE DATABASE  IF NOT EXISTS `robotdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `robotdb`;
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: robotdb
-- ------------------------------------------------------
-- Server version	8.4.7

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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `category_id` int NOT NULL,
  `category_name` varchar(50) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Courses'),(2,'Drinks'),(3,'Sides');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_locations`
--

DROP TABLE IF EXISTS `delivery_locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery_locations` (
  `location_id` int NOT NULL AUTO_INCREMENT,
  `building_name` varchar(100) NOT NULL,
  `location_type` enum('Intersection','DropOff') NOT NULL,
  `latitude` decimal(9,6) NOT NULL,
  `longitude` decimal(9,6) NOT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_locations`
--

LOCK TABLES `delivery_locations` WRITE;
/*!40000 ALTER TABLE `delivery_locations` DISABLE KEYS */;
INSERT INTO `delivery_locations` VALUES (1,'Residence Hall No. 1','DropOff',37.578760,-77.538425),(2,'Residence Hall No. 2','DropOff',37.577971,-77.539555),(3,'Residence Hall No. 3','DropOff',37.578421,-77.539051),(4,'Dennis Hall','DropOff',37.578985,-77.538698),(5,'Gateway Village 155','DropOff',37.572067,-77.539537),(6,'Gateway Village 151','DropOff',37.571595,-77.539222),(7,'Gateway Village 153','DropOff',37.572067,-77.539537),(8,'Gray Court','DropOff',37.575145,-77.541886),(9,'Keller Hall','DropOff',37.574511,-77.543166),(10,'Lakeview Hall','DropOff',37.578167,-77.540225),(11,'Lora Robins Court','DropOff',37.573475,-77.541145),(12,'Marsh Hall','DropOff',37.578593,-77.540356),(13,'Moore Hall','DropOff',37.578855,-77.539378),(14,'North Court','DropOff',37.575467,-77.543207),(15,'Robins Hall','DropOff',37.579360,-77.538282),(16,'South Court','DropOff',37.574848,-77.544025),(17,'Forest Apartments 160','DropOff',37.571232,-77.541349),(18,'Forest Apartments 162','DropOff',37.571232,-77.541349),(19,'Forest Apartments 164','DropOff',37.571168,-77.540772),(20,'Forest Apartments 166','DropOff',37.570881,-77.540105),(21,'Forest Apartments 168','DropOff',37.570512,-77.539487),(22,'Forest Apartments 170','DropOff',37.570455,-77.539313),(23,'Forest Apartments 172','DropOff',37.570429,-77.539115),(24,'Forest Apartments 191','DropOff',37.569737,-77.537508),(25,'Forest Apartments 193','DropOff',37.569590,-77.537068),(26,'Forest Apartments 470','DropOff',37.572159,-77.542011),(27,'Forest Apartments 472','DropOff',37.571765,-77.542217),(28,'Forest Apartments 474','DropOff',37.571700,-77.542212),(29,'Forest Apartments 476','DropOff',37.571421,-77.542416),(30,'Forest Apartments 481','DropOff',37.572739,-77.540446),(31,'Forest Apartments 483','DropOff',37.572567,-77.540843),(32,'Forest Apartments 486','DropOff',37.571770,-77.541123),(33,'Westhampton Hall','DropOff',37.574883,-77.544699),(34,'Wood Hall','DropOff',37.578346,-77.539587),(35,'Tyler Haynes Commons','DropOff',37.576077,-77.538452),(36,'N1','Intersection',37.576961,-77.539338),(37,'N2','Intersection',37.577962,-77.538795),(38,'N3','Intersection',37.578250,-77.538491),(39,'N4','Intersection',37.578109,-77.539154),(40,'N5','Intersection',37.578220,-77.539201),(41,'N6','Intersection',37.578213,-77.539288),(42,'N7','Intersection',37.578071,-77.539353),(43,'N8','Intersection',37.577872,-77.540311),(44,'N9','Intersection',37.578142,-77.540666),(45,'N10','Intersection',37.578202,-77.540634),(46,'N11','Intersection',37.578421,-77.540741),(47,'N12','Intersection',37.578352,-77.539394),(48,'N13','Intersection',37.578628,-77.539448),(49,'N14','Intersection',37.578643,-77.539410),(50,'N15','Intersection',37.578544,-77.538971),(51,'N16','Intersection',37.578681,-77.538831),(52,'N17','Intersection',37.578731,-77.538400),(53,'N18','Intersection',37.578932,-77.538880),(54,'N19','Intersection',37.579003,-77.538423),(55,'N20','Intersection',37.579092,-77.538439),(56,'S1','Intersection',37.575516,-77.539403),(57,'S2','Intersection',37.575368,-77.539303),(58,'S3','Intersection',37.574815,-77.539475),(59,'S4','Intersection',37.574864,-77.539978),(60,'S5','Intersection',37.575295,-77.540001),(61,'S6','Intersection',37.575461,-77.540372),(62,'S7','Intersection',37.575818,-77.540844),(63,'S8','Intersection',37.574604,-77.540424),(64,'S9','Intersection',37.574528,-77.540461),(65,'S10','Intersection',37.573882,-77.539220),(66,'S11','Intersection',37.573512,-77.539719),(67,'S12','Intersection',37.574243,-77.540965),(68,'S13','Intersection',37.573954,-77.541013),(69,'S14','Intersection',37.573740,-77.541212),(70,'S15','Intersection',37.574686,-77.541372),(71,'S16','Intersection',37.574758,-77.541325),(72,'S17','Intersection',37.574771,-77.542250),(73,'S18','Intersection',37.575848,-77.542207),(74,'S19','Intersection',37.575797,-77.542355),(75,'S20','Intersection',37.576132,-77.543254),(76,'S21','Intersection',37.575178,-77.544176),(77,'S22','Intersection',37.574648,-77.543813),(78,'S23','Intersection',37.574821,-77.544525),(79,'S24','Intersection',37.572531,-77.541890),(80,'S25','Intersection',37.572340,-77.541407),(81,'S26','Intersection',37.572187,-77.540889),(82,'S27','Intersection',37.572018,-77.539872),(83,'S28','Intersection',37.571599,-77.540000),(84,'S29','Intersection',37.571298,-77.540201),(85,'S30','Intersection',37.571181,-77.540183),(86,'S31','Intersection',37.571059,-77.540445),(87,'S32','Intersection',37.571289,-77.539506),(88,'S33','Intersection',37.570837,-77.539722),(89,'S34','Intersection',37.570601,-77.537800),(90,'S35','Intersection',37.570294,-77.537758),(91,'S36','Intersection',37.570209,-77.537388),(92,'S37','Intersection',37.569747,-77.537496),(93,'S38','Intersection',37.575076,-77.539470),(94,'S39','Intersection',37.572079,-77.539891),(95,'Gateway Village 157','DropOff',37.572067,-77.539537);
/*!40000 ALTER TABLE `delivery_locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `order_item_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`order_item_id`),
  KEY `order_id` (`order_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `order_items_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE,
  CONSTRAINT `order_items_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (1,1,2003,1,2.99),(2,1,1001,1,11.99),(3,1,1003,1,9.99),(4,2,2004,2,2.49),(5,2,1005,1,11.49),(6,2,1006,1,10.99),(7,3,1006,1,10.99);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `order_date` datetime NOT NULL,
  `location_id` int NOT NULL,
  `total_price` decimal(8,2) NOT NULL,
  `order_progress` enum('PENDING','IN_PROGRESS','COMPLETED') DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `user_id` (`user_id`),
  KEY `location_id` (`location_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `profiles` (`user_id`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`location_id`) REFERENCES `delivery_locations` (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,2,'2026-03-17 12:59:38',1,28.47,'COMPLETED'),(2,2,'2026-03-18 11:04:32',34,31.11,'COMPLETED'),(3,2,'2026-03-18 11:14:12',35,13.65,'COMPLETED');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paths`
--

DROP TABLE IF EXISTS `paths`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paths` (
  `path_id` int NOT NULL AUTO_INCREMENT,
  `from_location_id` int NOT NULL,
  `to_location_id` int NOT NULL,
  `distance_meters` int NOT NULL,
  `path_type` enum('Sidewalk','Road') NOT NULL,
  PRIMARY KEY (`path_id`),
  KEY `from_location_id` (`from_location_id`),
  KEY `to_location_id` (`to_location_id`),
  CONSTRAINT `paths_ibfk_1` FOREIGN KEY (`from_location_id`) REFERENCES `delivery_locations` (`location_id`),
  CONSTRAINT `paths_ibfk_2` FOREIGN KEY (`to_location_id`) REFERENCES `delivery_locations` (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=225 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paths`
--

LOCK TABLES `paths` WRITE;
/*!40000 ALTER TABLE `paths` DISABLE KEYS */;
INSERT INTO `paths` VALUES (1,35,36,141,'Road'),(2,36,37,132,'Road'),(3,37,38,42,'Road'),(4,38,52,54,'Sidewalk'),(5,52,1,4,'Sidewalk'),(6,37,39,38,'Sidewalk'),(7,39,40,12,'Sidewalk'),(8,40,3,26,'Sidewalk'),(9,3,50,16,'Sidewalk'),(10,50,51,19,'Sidewalk'),(11,51,53,28,'Sidewalk'),(12,53,4,17,'Sidewalk'),(13,51,54,51,'Sidewalk'),(14,54,55,10,'Sidewalk'),(15,55,15,33,'Sidewalk'),(16,40,47,22,'Sidewalk'),(17,47,48,31,'Sidewalk'),(18,48,49,3,'Sidewalk'),(19,49,13,24,'Sidewalk'),(20,40,41,8,'Sidewalk'),(21,41,34,31,'Sidewalk'),(22,39,42,20,'Sidewalk'),(23,42,2,21,'Sidewalk'),(24,36,43,141,'Road'),(25,43,10,44,'Road'),(26,43,44,44,'Road'),(27,44,45,7,'Road'),(28,45,46,27,'Sidewalk'),(29,46,12,39,'Sidewalk'),(30,35,56,124,'Sidewalk'),(31,56,60,58,'Sidewalk'),(32,60,61,39,'Sidewalk'),(33,61,62,61,'Sidewalk'),(34,62,73,124,'Sidewalk'),(35,73,74,15,'Sidewalk'),(36,74,75,117,'Road'),(37,75,76,141,'Road'),(38,76,78,58,'Road'),(39,78,33,19,'Road'),(40,57,93,38,'Sidewalk'),(41,93,59,52,'Sidewalk'),(42,59,70,129,'Sidewalk'),(43,70,71,9,'Road'),(44,71,8,61,'Road'),(45,70,72,79,'Sidewalk'),(46,72,9,86,'Sidewalk'),(47,9,77,67,'Road'),(48,77,16,29,'Sidewalk'),(49,9,14,107,'Sidewalk'),(50,59,63,50,'Sidewalk'),(51,63,64,10,'Sidewalk'),(52,64,67,65,'Sidewalk'),(53,67,68,34,'Sidewalk'),(54,68,69,30,'Sidewalk'),(55,69,11,28,'Sidewalk'),(56,93,58,32,'Sidewalk'),(57,58,65,109,'Sidewalk'),(58,65,66,61,'Road'),(59,66,94,163,'Road'),(60,94,81,88,'Road'),(61,81,80,50,'Road'),(62,80,31,56,'Road'),(63,31,30,40,'Road'),(64,82,79,46,'Road'),(65,79,26,43,'Road'),(66,26,27,48,'Road'),(67,27,28,6,'Road'),(68,28,29,35,'Road'),(69,81,32,50,'Road'),(70,94,82,7,'Road'),(71,82,5,28,'Road'),(72,82,83,60,'Road'),(73,83,84,39,'Road'),(74,84,85,14,'Road'),(75,85,86,27,'Road'),(76,86,19,32,'Road'),(77,19,18,53,'Road'),(78,86,20,37,'Road'),(79,20,88,35,'Road'),(80,88,21,42,'Road'),(81,21,22,56,'Road'),(82,22,23,21,'Road'),(83,82,87,90,'Road'),(84,87,89,173,'Road'),(85,89,90,37,'Road'),(86,80,91,35,'Road'),(87,91,92,53,'Road'),(88,92,24,1,'Road'),(89,92,25,42,'Road'),(90,5,95,63,'Road'),(91,5,6,0,'Road'),(92,82,6,29,'Road'),(93,18,17,0,'Road'),(94,36,35,141,'Road'),(95,37,36,132,'Road'),(96,38,37,42,'Road'),(97,52,38,54,'Sidewalk'),(98,1,52,4,'Sidewalk'),(99,39,37,38,'Sidewalk'),(100,40,39,12,'Sidewalk'),(101,3,40,26,'Sidewalk'),(102,50,3,16,'Sidewalk'),(103,51,50,19,'Sidewalk'),(104,53,51,28,'Sidewalk'),(105,4,53,17,'Sidewalk'),(106,54,51,51,'Sidewalk'),(107,55,54,10,'Sidewalk'),(108,15,55,33,'Sidewalk'),(109,47,40,22,'Sidewalk'),(110,48,47,31,'Sidewalk'),(111,49,48,3,'Sidewalk'),(112,13,49,24,'Sidewalk'),(113,41,40,8,'Sidewalk'),(114,34,41,31,'Sidewalk'),(115,42,39,20,'Sidewalk'),(116,2,42,21,'Sidewalk'),(117,43,36,141,'Road'),(118,10,43,44,'Road'),(119,44,43,44,'Road'),(120,45,44,7,'Road'),(121,46,45,27,'Sidewalk'),(122,12,46,39,'Sidewalk'),(123,56,35,124,'Sidewalk'),(124,60,56,58,'Sidewalk'),(125,61,60,39,'Sidewalk'),(126,62,61,61,'Sidewalk'),(127,73,62,124,'Sidewalk'),(128,74,73,15,'Sidewalk'),(129,75,74,117,'Road'),(130,76,75,141,'Road'),(131,78,76,58,'Road'),(132,33,78,19,'Road'),(133,93,57,38,'Sidewalk'),(134,59,93,52,'Sidewalk'),(135,70,59,129,'Sidewalk'),(136,71,70,9,'Road'),(137,8,71,61,'Road'),(138,72,70,79,'Sidewalk'),(139,9,72,86,'Sidewalk'),(140,77,9,67,'Road'),(141,16,77,29,'Sidewalk'),(142,14,9,107,'Sidewalk'),(143,63,59,50,'Sidewalk'),(144,64,63,10,'Sidewalk'),(145,67,64,65,'Sidewalk'),(146,68,67,34,'Sidewalk'),(147,69,68,30,'Sidewalk'),(148,11,69,28,'Sidewalk'),(149,58,93,32,'Sidewalk'),(150,65,58,109,'Sidewalk'),(151,66,65,61,'Road'),(152,94,66,163,'Road'),(153,81,94,88,'Road'),(154,80,81,50,'Road'),(155,31,80,56,'Road'),(156,30,31,40,'Road'),(157,79,82,46,'Road'),(158,26,79,43,'Road'),(159,27,26,48,'Road'),(160,28,27,6,'Road'),(161,29,28,35,'Road'),(162,32,81,50,'Road'),(163,82,94,7,'Road'),(164,5,82,28,'Road'),(165,83,82,60,'Road'),(166,84,83,39,'Road'),(167,85,84,14,'Road'),(168,86,85,27,'Road'),(169,19,86,32,'Road'),(170,18,19,53,'Road'),(171,20,86,37,'Road'),(172,88,20,35,'Road'),(173,21,88,42,'Road'),(174,22,21,56,'Road'),(175,23,22,21,'Road'),(176,87,82,90,'Road'),(177,89,87,173,'Road'),(178,90,89,37,'Road'),(179,91,80,35,'Road'),(180,92,91,53,'Road'),(181,24,92,1,'Road'),(182,25,92,42,'Road'),(183,95,5,63,'Road'),(184,6,5,0,'Road'),(185,6,82,29,'Road'),(186,17,18,0,'Road'),(221,78,94,100,'Road'),(222,94,78,100,'Road'),(223,33,82,120,'Road'),(224,82,33,120,'Road');
/*!40000 ALTER TABLE `paths` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `product_id` int NOT NULL,
  `category_id` int NOT NULL,
  `item_name` varchar(100) NOT NULL,
  `price` decimal(5,2) NOT NULL,
  `description` text,
  `diet_type` varchar(50) NOT NULL,
  `prep_time` int NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1001,1,'Chicken Teriyaki Bowl',11.99,'Grilled chicken served over rice with teriyaki sauce and vegetables','Non-Vegetarian',10,'Images/ProductImages/chicken-teriyaki-bowl.jpeg'),(1002,1,'Beef Quesadilla',10.49,'Seasoned beef with melted cheese in a toasted tortilla','Non-Vegetarian',10,'Images/ProductImages/beef-quesadilla.jpeg'),(1003,1,'Veggie Fried Rice',9.99,'Stir-fried rice with mixed vegetables and soy seasoning','Vegetarian',10,'Images/ProductImages/veggie-fried-rice.jpeg'),(1004,1,'Spaghetti Marinara',9.49,'Spaghetti pasta topped with house-made marinara sauce','Vegan',10,'Images/ProductImages/spaghetti-marinara.jpeg'),(1005,1,'Chicken Alfredo Pasta',11.49,'Creamy alfredo pasta with grilled chicken','Non-Vegetarian',10,'Images/ProductImages/chicken-alfredo-pasta.jpeg'),(1006,1,'Cheeseburger',10.99,'Beef patty with melted cheese, lettuce, and tomato on a bun','Non-Vegetarian',10,'Images/ProductImages/cheeseburger.jpeg'),(1007,1,'Grilled Chicken Wrap',9.99,'Grilled chicken with lettuce and sauce wrapped in a tortilla','Non-Vegetarian',10,'Images/ProductImages/grilled-chicken-wrap.jpeg'),(1008,1,'BBQ Chicken Flatbread',10.49,'Flatbread topped with BBQ sauce, chicken, and cheese','Non-Vegetarian',10,'Images/ProductImages/bbq-chicken-flatbread.jpeg'),(1009,1,'Shrimp Tacos',11.99,'Seasoned shrimp served in soft tortillas with slaw','Non-Vegetarian',10,'Images/ProductImages/shrimp-tacos.jpeg'),(1010,1,'Veggie Burrito Bowl',9.99,'Rice bowl with beans, vegetables, and fresh toppings','Vegetarian',10,'Images/ProductImages/veggie-burrito-bowl.jpeg'),(1011,1,'Turkey Panini',10.49,'Grilled panini with sliced turkey and melted cheese','Non-Vegetarian',10,'Images/ProductImages/turkey-panini.jpeg'),(1012,1,'Pepperoni Flatbread',10.99,'Flatbread topped with pepperoni and melted mozzarella','Non-Vegetarian',10,'Images/ProductImages/pepperoni-flatbread.jpeg'),(2001,2,'Bottled Water',1.99,'Pure, chilled bottled water for quick refreshment.','Vegan',2,'Images/ProductImages/bottled-water.jpeg'),(2002,2,'Iced Tea',2.49,'Freshly brewed tea served chilled over ice.','Vegan',2,'Images/ProductImages/iced-tea.jpeg'),(2003,2,'Lemonade',2.99,'Refreshing lemonade made with real lemon flavor.','Vegan',2,'Images/ProductImages/lemonade.jpeg'),(2004,2,'Cola',2.49,'Classic carbonated cola served chilled.','Vegan',2,'Images/ProductImages/cola.jpeg'),(2005,2,'Orange Juice',2.99,'Cold-pressed orange juice with natural sweetness.','Vegan',2,'Images/ProductImages/orange-juice.jpeg'),(2006,2,'Apple Juice',2.99,'Crisp and refreshing apple juice served cold.','Vegan',2,'Images/ProductImages/apple-juice.jpeg'),(2007,2,'Iced Coffee',3.49,'Chilled brewed coffee served over ice.','Vegetarian',2,'Images/ProductImages/iced-coffee.jpeg'),(2008,2,'Sparkling Water',2.49,'Carbonated sparkling water with a clean, crisp finish.','Vegan',2,'Images/ProductImages/sparkling-water.jpeg'),(3001,3,'French Fries',3.49,'Crispy, golden-brown fries lightly seasoned and cooked fresh for a perfect crunch.','Vegan',5,'Images/ProductImages/french-fries.jpeg'),(3002,3,'Side Salad',3.99,'A fresh mix of crisp greens, tomatoes, and cucumbers served with your choice of dressing.','Vegan',5,'Images/ProductImages/side-salad.jpeg'),(3003,3,'Garlic Bread',2.99,'Warm toasted bread brushed with garlic butter and herbs, lightly crisp on the outside.','Vegetarian',5,'Images/ProductImages/garlic-bread.jpeg'),(3004,3,'Steamed Veggies',3.49,'A healthy blend of seasonal vegetables steamed to retain flavor and nutrients.','Vegan',5,'Images/ProductImages/steamed-veggies.jpeg'),(3005,3,'Onion Rings',3.99,'Thick-cut onion rings battered and fried until crispy and golden.','Vegetarian',5,'Images/ProductImages/onion-rings.jpeg'),(3006,3,'Mac and Cheese',3.99,'Creamy macaroni tossed in a rich, melted cheese sauce.','Vegetarian',5,'Images/ProductImages/mac-and-cheese.jpeg'),(3007,3,'Mashed Potatoes',3.49,'Smooth and buttery mashed potatoes whipped to a creamy consistency.','Vegetarian',5,'Images/ProductImages/mashed-potatoes.jpeg'),(3008,3,'Coleslaw',2.99,'Freshly shredded cabbage and carrots tossed in a light, tangy dressing.','Vegetarian',5,'Images/ProductImages/coleslaw.jpeg'),(3009,3,'Fruit Cup',3.49,'A refreshing mix of fresh, seasonal fruits, chilled and ready to enjoy.','Vegan',5,'Images/ProductImages/fruit-cup.jpeg'),(3010,3,'Mozzarella Sticks',4.49,'Breaded mozzarella cheese sticks fried until crispy with a gooey center.','Vegetarian',5,'Images/ProductImages/mozzarella-sticks.jpeg');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profiles`
--

DROP TABLE IF EXISTS `profiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profiles` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(150) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profiles`
--

LOCK TABLES `profiles` WRITE;
/*!40000 ALTER TABLE `profiles` DISABLE KEYS */;
INSERT INTO `profiles` VALUES (2,'','','','','','','','');
/*!40000 ALTER TABLE `profiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `hashed_password` varchar(255) NOT NULL,
  `role` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'testuser','$2a$10$Wpj5xd3tGiN4aN45Wg0ds.A0rnMr6YMMYUE5ODqC.sNdnlGIutU9C','ROLE_USER');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-18 11:24:35
