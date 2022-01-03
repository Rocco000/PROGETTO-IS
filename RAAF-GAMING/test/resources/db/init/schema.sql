-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: raaf_gaming
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `abbonamento`
--

DROP TABLE IF EXISTS `abbonamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `abbonamento` (
  `codice` char(11) NOT NULL,
  `prodotto` int unsigned NOT NULL,
  `durata_abbonamento` smallint unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`codice`,`prodotto`),
  KEY `prodotto` (`prodotto`),
  CONSTRAINT `abbonamento_ibfk_1` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`codice_prodotto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cartadicredito`
--

DROP TABLE IF EXISTS `cartadicredito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cartadicredito` (
  `codicecarta` varchar(16) NOT NULL,
  `data_scadenza` date NOT NULL,
  `codice_cvv` int NOT NULL,
  PRIMARY KEY (`codicecarta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cartafedelta`
--

DROP TABLE IF EXISTS `cartafedelta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cartafedelta` (
  `codice` char(10) NOT NULL,
  `punti` smallint DEFAULT '0',
  PRIMARY KEY (`codice`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `nome` varchar(20) NOT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `email` varchar(30) NOT NULL,
  `nome` varchar(15) NOT NULL,
  `cognome` varchar(15) NOT NULL,
  `data_di_nascita` date NOT NULL,
  `password` varchar(50) NOT NULL,
  `carta_fedelta` char(10) NOT NULL,
  `cartadicredito` varchar(16) NOT NULL,
  PRIMARY KEY (`email`),
  KEY `carta_fedeltà` (`carta_fedelta`),
  KEY `cliente_ibfk_2` (`cartadicredito`),
  CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`carta_fedelta`) REFERENCES `cartafedelta` (`codice`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cliente_ibfk_2` FOREIGN KEY (`cartadicredito`) REFERENCES `cartadicredito` (`codicecarta`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `console`
--

DROP TABLE IF EXISTS `console`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `console` (
  `prodotto` int unsigned NOT NULL,
  `specifica` varchar(20) NOT NULL,
  `colore` varchar(8) NOT NULL,
  PRIMARY KEY (`prodotto`),
  CONSTRAINT `console_ibfk_1` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`codice_prodotto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `corriereespresso`
--

DROP TABLE IF EXISTS `corriereespresso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `corriereespresso` (
  `nome` varchar(10) NOT NULL,
  `sito` varchar(60) NOT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `disponibile`
--

DROP TABLE IF EXISTS `disponibile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `disponibile` (
  `videogioco` int unsigned NOT NULL,
  `console` int unsigned NOT NULL,
  PRIMARY KEY (`videogioco`,`console`),
  KEY `console` (`console`),
  CONSTRAINT `disponibile_ibfk_1` FOREIGN KEY (`videogioco`) REFERENCES `videogioco` (`prodotto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `disponibile_ibfk_2` FOREIGN KEY (`console`) REFERENCES `console` (`prodotto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dlc`
--

DROP TABLE IF EXISTS `dlc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dlc` (
  `prodotto` int unsigned NOT NULL,
  `dimensione` decimal(3,1) NOT NULL,
  `descrizione` varchar(50) NOT NULL,
  PRIMARY KEY (`prodotto`),
  CONSTRAINT `dlc_ibfk_1` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`codice_prodotto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fornitore`
--

DROP TABLE IF EXISTS `fornitore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fornitore` (
  `nome` varchar(20) NOT NULL,
  `indirizzo` varchar(50) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gestore`
--

DROP TABLE IF EXISTS `gestore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gestore` (
  `email` varchar(45) NOT NULL,
  `ruolo` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `magazzino`
--

DROP TABLE IF EXISTS `magazzino`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `magazzino` (
  `indirizzo` varchar(50) NOT NULL,
  `capienza` int DEFAULT '1000',
  PRIMARY KEY (`indirizzo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ordine`
--

DROP TABLE IF EXISTS `ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordine` (
  `codice` char(11) NOT NULL,
  `data_acquisto` date NOT NULL,
  `indirizzo_di_consegna` varchar(200) NOT NULL,
  `cliente` varchar(30) NOT NULL,
  `prezzo_totale` double NOT NULL DEFAULT '0',
  `gestore` varchar(45) DEFAULT NULL,
  `stato` varchar(45) NOT NULL,
  `metodo_di_pagamento` varchar(16) NOT NULL,
  PRIMARY KEY (`codice`),
  KEY `cliente` (`cliente`),
  KEY `ordine_ibfk_2` (`gestore`),
  CONSTRAINT `ordine_ibfk_1` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`email`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ordine_ibfk_2` FOREIGN KEY (`gestore`) REFERENCES `gestore` (`email`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `parte_di`
--

DROP TABLE IF EXISTS `parte_di`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parte_di` (
  `videogioco` int unsigned NOT NULL,
  `categoria` varchar(20) NOT NULL,
  PRIMARY KEY (`videogioco`,`categoria`),
  KEY `categoria` (`categoria`),
  CONSTRAINT `parte_di_ibfk_1` FOREIGN KEY (`videogioco`) REFERENCES `videogioco` (`prodotto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `parte_di_ibfk_2` FOREIGN KEY (`categoria`) REFERENCES `categoria` (`nome`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `presente_in`
--

DROP TABLE IF EXISTS `presente_in`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `presente_in` (
  `magazzino` varchar(50) NOT NULL,
  `prodotto` int unsigned NOT NULL,
  `quantita_disponibile` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`magazzino`,`prodotto`),
  KEY `prodotto` (`prodotto`),
  CONSTRAINT `presente_in_ibfk_1` FOREIGN KEY (`magazzino`) REFERENCES `magazzino` (`indirizzo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `presente_in_ibfk_2` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`codice_prodotto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `prodotto`
--

DROP TABLE IF EXISTS `prodotto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prodotto` (
  `codice_prodotto` int unsigned NOT NULL AUTO_INCREMENT,
  `prezzo` double NOT NULL,
  `copertina` longblob,
  `sconto` decimal(2,0) DEFAULT '0',
  `data_uscita` date NOT NULL,
  `nome` varchar(50) NOT NULL,
  `quantita_fornitura` int NOT NULL,
  `data_fornitura` date NOT NULL,
  `fornitore` varchar(20) DEFAULT NULL,
  `gestore` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codice_prodotto`),
  KEY `fornitore` (`fornitore`),
  KEY `prodotto_ibfk_2` (`gestore`),
  CONSTRAINT `prodotto_ibfk_1` FOREIGN KEY (`fornitore`) REFERENCES `fornitore` (`nome`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `prodotto_ibfk_2` FOREIGN KEY (`gestore`) REFERENCES `gestore` (`email`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `recensisce`
--

DROP TABLE IF EXISTS `recensisce`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recensisce` (
  `cliente` varchar(30) NOT NULL,
  `prodotto` int unsigned NOT NULL,
  `voto` smallint NOT NULL,
  `commento` varchar(45) NOT NULL,
  PRIMARY KEY (`cliente`,`prodotto`),
  KEY `prodotto` (`prodotto`),
  CONSTRAINT `recensisce_ibfk_1` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`codice_prodotto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `recensisce_ibfk_2` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `riguarda`
--

DROP TABLE IF EXISTS `riguarda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `riguarda` (
  `prodotto` int unsigned NOT NULL,
  `ordine` char(11) NOT NULL,
  `quantita_acquistata` smallint NOT NULL DEFAULT '1',
  PRIMARY KEY (`prodotto`,`ordine`),
  KEY `ordine` (`ordine`),
  CONSTRAINT `riguarda_ibfk_1` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`codice_prodotto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `riguarda_ibfk_2` FOREIGN KEY (`ordine`) REFERENCES `ordine` (`codice`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `softwarehouse`
--

DROP TABLE IF EXISTS `softwarehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `softwarehouse` (
  `nomesfh` varchar(30) NOT NULL,
  `logo` longblob,
  PRIMARY KEY (`nomesfh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `spedito`
--

DROP TABLE IF EXISTS `spedito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spedito` (
  `ordine` char(11) NOT NULL,
  `corriere_espresso` varchar(10) NOT NULL,
  `data_consegna` date DEFAULT NULL,
  PRIMARY KEY (`ordine`,`corriere_espresso`),
  KEY `corriere_espresso` (`corriere_espresso`),
  CONSTRAINT `spedito_ibfk_1` FOREIGN KEY (`ordine`) REFERENCES `ordine` (`codice`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `spedito_ibfk_2` FOREIGN KEY (`corriere_espresso`) REFERENCES `corriereespresso` (`nome`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `videogioco`
--

DROP TABLE IF EXISTS `videogioco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videogioco` (
  `prodotto` int unsigned NOT NULL,
  `dimensione` decimal(4,1) NOT NULL,
  `pegi` int NOT NULL,
  `edizione_limitata` tinyint(1) NOT NULL,
  `ncd` int DEFAULT NULL,
  `vkey` char(14) DEFAULT NULL,
  `software_house` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`prodotto`),
  KEY `software_house` (`software_house`),
  CONSTRAINT `videogioco_ibfk_1` FOREIGN KEY (`prodotto`) REFERENCES `prodotto` (`codice_prodotto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `videogioco_ibfk_2` FOREIGN KEY (`software_house`) REFERENCES `softwarehouse` (`nomesfh`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-03 20:07:29
