-- MySQL dump 10.13  Distrib 5.7.20, for Win64 (x86_64)
--
-- Host: localhost    Database: sah_db
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `body_type`
--

DROP TABLE IF EXISTS `body_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `body_type` (
  `id_bt` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_bt`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `body_type`
--

LOCK TABLES `body_type` WRITE;
/*!40000 ALTER TABLE `body_type` DISABLE KEYS */;
INSERT INTO `body_type` (`id_bt`) VALUES (1),(2),(3),(4);
/*!40000 ALTER TABLE `body_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `body_type_translations`
--

DROP TABLE IF EXISTS `body_type_translations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `body_type_translations` (
  `id_bt_translations` int(11) NOT NULL AUTO_INCREMENT,
  `language` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `is_default` bit(1) DEFAULT NULL,
  `description` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `id_bt` int(11) NOT NULL,
  PRIMARY KEY (`id_bt_translations`),
  KEY `id_bt_idx` (`id_bt_translations`),
  KEY `id_bt_idx1` (`id_bt`),
  CONSTRAINT `id_bt` FOREIGN KEY (`id_bt`) REFERENCES `body_type` (`id_bt`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `body_type_translations`
--

LOCK TABLES `body_type_translations` WRITE;
/*!40000 ALTER TABLE `body_type_translations` DISABLE KEYS */;
INSERT INTO `body_type_translations` (`id_bt_translations`, `language`, `is_default`, `description`, `id_bt`) VALUES (1,'en-UK','','none',1),(2,'en-UK','','ectomorph',2),(3,'en-UK','','mesomorph',3),(4,'en-UK','','endomorph',4),(5,'pl-PL','\0','nieokreślono',1),(6,'pl-PL','\0','ektomorficzny',2),(7,'pl-PL','\0','mezomorficzny',3),(8,'pl-PL','\0','endomorficzny',4);
/*!40000 ALTER TABLE `body_type_translations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `frequency_of_activity`
--

DROP TABLE IF EXISTS `frequency_of_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `frequency_of_activity` (
  `id_foa` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_foa`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frequency_of_activity`
--

LOCK TABLES `frequency_of_activity` WRITE;
/*!40000 ALTER TABLE `frequency_of_activity` DISABLE KEYS */;
INSERT INTO `frequency_of_activity` (`id_foa`) VALUES (1),(2),(3),(4),(5),(6);
/*!40000 ALTER TABLE `frequency_of_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `frequency_of_activity_translations`
--

DROP TABLE IF EXISTS `frequency_of_activity_translations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `frequency_of_activity_translations` (
  `id_foa_translations` int(11) NOT NULL AUTO_INCREMENT,
  `language` varchar(45) DEFAULT NULL,
  `is_default` bit(1) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `id_foa` int(11) NOT NULL,
  PRIMARY KEY (`id_foa_translations`),
  KEY `id_foa_idx` (`id_foa`),
  CONSTRAINT `id_foa` FOREIGN KEY (`id_foa`) REFERENCES `frequency_of_activity` (`id_foa`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frequency_of_activity_translations`
--

LOCK TABLES `frequency_of_activity_translations` WRITE;
/*!40000 ALTER TABLE `frequency_of_activity_translations` DISABLE KEYS */;
INSERT INTO `frequency_of_activity_translations` (`id_foa_translations`, `language`, `is_default`, `description`, `id_foa`) VALUES (1,'en-UK','','none',1),(2,'en-UK','','very low',2),(3,'en-UK','','low',3),(4,'en-UK','','medium',4),(5,'en-UK','','high',5),(6,'en-UK','','very high',6),(7,'pl-PL','\0','nieokreślono',1),(8,'pl-PL','\0','bardzo mała',2),(9,'pl-PL','\0','mała',3),(10,'pl-PL','\0','średnia',4),(11,'pl-PL','\0','duża',5),(12,'pl-PL','\0','bardzo duża',6);
/*!40000 ALTER TABLE `frequency_of_activity_translations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `posts` (
  `id_post` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_post`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` (`id_post`) VALUES (6),(7),(8),(9),(10),(11),(12);
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts_translations`
--

DROP TABLE IF EXISTS `posts_translations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `posts_translations` (
  `id_post_translations` int(11) NOT NULL AUTO_INCREMENT,
  `language` varchar(45) DEFAULT NULL,
  `is_default` bit(1) DEFAULT NULL,
  `content` mediumtext NOT NULL,
  `id_post` int(11) NOT NULL,
  PRIMARY KEY (`id_post_translations`),
  KEY `id_post_idx` (`id_post`),
  CONSTRAINT `id_post` FOREIGN KEY (`id_post`) REFERENCES `posts` (`id_post`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts_translations`
--

LOCK TABLES `posts_translations` WRITE;
/*!40000 ALTER TABLE `posts_translations` DISABLE KEYS */;
/*!40000 ALTER TABLE `posts_translations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id_role`, `role`) VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sex`
--

DROP TABLE IF EXISTS `sex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sex` (
  `id_sex` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_sex`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sex`
--

LOCK TABLES `sex` WRITE;
/*!40000 ALTER TABLE `sex` DISABLE KEYS */;
INSERT INTO `sex` (`id_sex`) VALUES (1),(2),(3);
/*!40000 ALTER TABLE `sex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sex_translations`
--

DROP TABLE IF EXISTS `sex_translations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sex_translations` (
  `id_sex_translations` int(11) NOT NULL AUTO_INCREMENT,
  `language` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `is_default` bit(1) DEFAULT NULL,
  `description` varchar(45) CHARACTER SET utf8mb4 DEFAULT NULL,
  `id_sex` int(11) NOT NULL,
  PRIMARY KEY (`id_sex_translations`),
  KEY `id_sex_idx` (`id_sex`),
  CONSTRAINT `id_sex` FOREIGN KEY (`id_sex`) REFERENCES `sex` (`id_sex`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sex_translations`
--

LOCK TABLES `sex_translations` WRITE;
/*!40000 ALTER TABLE `sex_translations` DISABLE KEYS */;
INSERT INTO `sex_translations` (`id_sex_translations`, `language`, `is_default`, `description`, `id_sex`) VALUES (1,'en-UK','','none',1),(2,'en-UK','','male',2),(3,'en-UK','','female',3),(4,'pl-PL','\0','nieokreślono',1),(5,'pl-PL','\0','mężczyzna',2),(6,'pl-PL','\0','kobieta',3);
/*!40000 ALTER TABLE `sex_translations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) COLLATE utf8mb4_polish_ci NOT NULL,
  `password` varchar(80) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `email` varchar(45) COLLATE utf8mb4_polish_ci NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  `confirmation_token` varchar(80) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id_user`, `username`, `password`, `email`, `enabled`, `confirmation_token`) VALUES (55,'admin','$2a$10$HuufP/.UKfz.u8zPKkYJFO/b8NFhdO4tViauqAmPCXfAQajETUAFW','inzo666@gmail.com',1,NULL),(57,'user','$2a$10$fQvSssA1bMXictRUpWnFA.cnAf4Tf1st4aqaXxnWidApB8rR49eRe','adamdebski33@gmail.com',1,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_details`
--

DROP TABLE IF EXISTS `users_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_details` (
  `id_user` int(11) NOT NULL,
  `age` int(3) DEFAULT NULL,
  `height` int(3) DEFAULT NULL,
  `weight` int(3) DEFAULT NULL,
  `id_foa` int(11) NOT NULL,
  `id_sex` int(11) NOT NULL,
  `id_bt` int(11) NOT NULL,
  `BMI` double DEFAULT NULL,
  `BMR` double DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  KEY `fk_Users_Details_Sex1_idx` (`id_sex`),
  KEY `fk_Users_Details_Body_Type1_idx` (`id_bt`),
  KEY `fk_Users_Details_Frequency_of_Activity1_idx` (`id_foa`),
  CONSTRAINT `fk_Users_Details_Body_Type1` FOREIGN KEY (`id_bt`) REFERENCES `body_type` (`id_bt`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Users_Details_Frequency_of_Activity1` FOREIGN KEY (`id_foa`) REFERENCES `frequency_of_activity` (`id_foa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Users_Details_Sex1` FOREIGN KEY (`id_sex`) REFERENCES `sex` (`id_sex`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Users_Details_Users1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_details`
--

LOCK TABLES `users_details` WRITE;
/*!40000 ALTER TABLE `users_details` DISABLE KEYS */;
INSERT INTO `users_details` (`id_user`, `age`, `height`, `weight`, `id_foa`, `id_sex`, `id_bt`, `BMI`, `BMR`) VALUES (55,29,193,105,2,2,2,28.19,2539.5);
/*!40000 ALTER TABLE `users_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_has_roles`
--

DROP TABLE IF EXISTS `users_has_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_has_roles` (
  `id_user` int(11) NOT NULL,
  `id_role` int(11) NOT NULL,
  PRIMARY KEY (`id_user`,`id_role`),
  KEY `fk_Users_has_Roles_Roles1_idx` (`id_role`),
  KEY `fk_Users_has_Roles_Users_idx` (`id_user`),
  CONSTRAINT `fk_Users_has_Roles_Roles1` FOREIGN KEY (`id_role`) REFERENCES `roles` (`id_role`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Users_has_Roles_Users` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_has_roles`
--

LOCK TABLES `users_has_roles` WRITE;
/*!40000 ALTER TABLE `users_has_roles` DISABLE KEYS */;
INSERT INTO `users_has_roles` (`id_user`, `id_role`) VALUES (57,1),(55,2);
/*!40000 ALTER TABLE `users_has_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-21 20:36:01
