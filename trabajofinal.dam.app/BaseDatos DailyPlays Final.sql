CREATE DATABASE  IF NOT EXISTS `dailyplay` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dailyplay`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: dailyplay
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `calificacion` float DEFAULT NULL,
  `comentario` varchar(1000) DEFAULT NULL,
  `duracion` int DEFAULT NULL,
  `videojuego_nombre` varchar(100) DEFAULT NULL,
  `usuario_email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `videojuego_nombre` (`videojuego_nombre`),
  KEY `usuario_email` (`usuario_email`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`videojuego_nombre`) REFERENCES `videojuego` (`nombre`),
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`usuario_email`) REFERENCES `usuario` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,NULL,6,NULL,NULL,'Pokemon','App'),(2,NULL,3,NULL,NULL,'Pokemon','Avl'),(7,NULL,6,NULL,NULL,'God of War (2018)','App'),(8,NULL,6,NULL,NULL,'God of War (2018)','Avl');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `nombre` varchar(100) DEFAULT NULL,
  `email` varchar(200) NOT NULL,
  `pass` varchar(50) DEFAULT NULL,
  `fechaRegistro` date DEFAULT NULL,
  `esModerador` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('','','','2023-05-31',0),('a','a','','2023-05-31',0),('','aa','a','2023-05-31',0),('Alberto','App','1234',NULL,NULL),('Alvaro','Avl',NULL,NULL,NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videojuego`
--

DROP TABLE IF EXISTS `videojuego`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videojuego` (
  `nombre` varchar(100) NOT NULL,
  `nota` float DEFAULT NULL,
  `descripcion` varchar(300) DEFAULT NULL,
  `lanzamiento` date DEFAULT NULL,
  `genero` enum('ACCION','AVENTURA','ROL','DISPAROS','ESTRATEGIA','DEPORTES','CARRERAS','PLATAFORMAS','LUCHA','SIMULACION') DEFAULT NULL,
  `plataforma` enum('PC','PLAYSTATION_4','PLAYSTATION_5','XBOX_SERIES_X','XBOX_ONE','NINTENDO_SWITCH','NINTENDO_3DS','MOBILE') DEFAULT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videojuego`
--

LOCK TABLES `videojuego` WRITE;
/*!40000 ALTER TABLE `videojuego` DISABLE KEYS */;
INSERT INTO `videojuego` VALUES ('asf',NULL,'asdf','2000-10-10','ROL','PLAYSTATION_5'),('God of War (2018)',9.5,'Vídeojuego de la saga God of War, que traslada su fórmula jugable desde la antigua Grecia a una ambientación nórdica, en una historia de padre e hijo','2018-05-05','AVENTURA','PLAYSTATION_4'),('Pokemon',4,'Aaaaa','1996-06-06','AVENTURA','PLAYSTATION_4');
/*!40000 ALTER TABLE `videojuego` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-02 12:25:59
