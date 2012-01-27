-- MySQL dump 10.13  Distrib 5.1.54, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: randi2_development
-- ------------------------------------------------------
-- Server version	5.1.54-1ubuntu4

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
-- Table structure for table `AbstractConstraint`
--

DROP TABLE IF EXISTS `AbstractConstraint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AbstractConstraint` (
  `DTYPE` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `version` int(11) NOT NULL,
  `expectedValue` varchar(255) DEFAULT NULL,
  `firstDate` datetime DEFAULT NULL,
  `secondDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AbstractConstraint`
--

LOCK TABLES `AbstractConstraint` WRITE;
/*!40000 ALTER TABLE `AbstractConstraint` DISABLE KEYS */;
/*!40000 ALTER TABLE `AbstractConstraint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AbstractConstraint_expectedValues`
--

DROP TABLE IF EXISTS `AbstractConstraint_expectedValues`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AbstractConstraint_expectedValues` (
  `AbstractConstraint_id` bigint(20) NOT NULL,
  `element` varchar(255) DEFAULT NULL,
  KEY `FK26FD227AAF0BD07A` (`AbstractConstraint_id`),
  CONSTRAINT `FK26FD227AAF0BD07A` FOREIGN KEY (`AbstractConstraint_id`) REFERENCES `AbstractConstraint` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AbstractConstraint_expectedValues`
--

LOCK TABLES `AbstractConstraint_expectedValues` WRITE;
/*!40000 ALTER TABLE `AbstractConstraint_expectedValues` DISABLE KEYS */;
/*!40000 ALTER TABLE `AbstractConstraint_expectedValues` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AccessControlEntryHibernate`
--

DROP TABLE IF EXISTS `AccessControlEntryHibernate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AccessControlEntryHibernate` (
  `id` bigint(20) NOT NULL,
  `granting` bit(1) NOT NULL,
  `code` char(1) NOT NULL,
  `mask` int(11) NOT NULL,
  `roleName` varchar(255) DEFAULT NULL,
  `acl_id` bigint(20) DEFAULT NULL,
  `sid_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK40F1A399ABD3754E` (`acl_id`),
  KEY `FK40F1A3993069E2C6` (`sid_id`),
  CONSTRAINT `FK40F1A3993069E2C6` FOREIGN KEY (`sid_id`) REFERENCES `SidHibernate` (`id`),
  CONSTRAINT `FK40F1A399ABD3754E` FOREIGN KEY (`acl_id`) REFERENCES `AclHibernate` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AccessControlEntryHibernate`
--

LOCK TABLES `AccessControlEntryHibernate` WRITE;
/*!40000 ALTER TABLE `AccessControlEntryHibernate` DISABLE KEYS */;
INSERT INTO `AccessControlEntryHibernate` VALUES (1,'','C',4,'ROLE_ADMIN',1,1),(2,'','C',4,'ROLE_ADMIN',2,1),(3,'','R',1,'ROLE_ADMIN',3,1),(4,'','R',1,'ROLE_ADMIN',4,1),(5,'','W',2,'ROLE_ADMIN',3,1),(6,'','W',2,'ROLE_ADMIN',4,1),(7,'','A',16,'ROLE_ADMIN',3,1),(8,'','A',16,'ROLE_ADMIN',4,1),(9,'','C',4,'ROLE_ADMIN',5,1),(10,'','R',1,'ROLE_ADMIN',6,1),(11,'','W',2,'ROLE_ADMIN',6,1),(12,'','A',16,'ROLE_ADMIN',6,1),(13,'','R',1,'ROLE_USER',3,1),(14,'','W',2,'ROLE_USER',3,1),(15,'','R',1,'ROLE_USER',4,1),(16,'','W',2,'ROLE_USER',4,1),(17,'','R',1,'ROLE_ANONYMOUS',7,2),(18,'','R',1,'ROLE_ANONYMOUS',8,2),(19,'','W',2,'ROLE_ADMIN',3,1),(20,'','W',2,'ROLE_ADMIN',4,1),(21,'','R',1,'ROLE_ADMIN',3,1),(22,'','R',1,'ROLE_ADMIN',4,1),(23,'','A',16,'ROLE_ADMIN',3,1),(24,'','A',16,'ROLE_ADMIN',4,1),(25,'','R',1,'ROLE_ANONYMOUS',9,2),(26,'','A',16,'ROLE_ADMIN',6,1),(27,'','R',1,'ROLE_ADMIN',6,1);
/*!40000 ALTER TABLE `AccessControlEntryHibernate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AclHibernate`
--

DROP TABLE IF EXISTS `AclHibernate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AclHibernate` (
  `id` bigint(20) NOT NULL,
  `entriesInheriting` bit(1) NOT NULL,
  `objectIdentity_id` bigint(20) DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  `parentAcl_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD9188F0856FBD678` (`parentAcl_id`),
  KEY `FKD9188F08C2DC041C` (`objectIdentity_id`),
  KEY `FKD9188F08C957A6C1` (`owner_id`),
  CONSTRAINT `FKD9188F08C957A6C1` FOREIGN KEY (`owner_id`) REFERENCES `SidHibernate` (`id`),
  CONSTRAINT `FKD9188F0856FBD678` FOREIGN KEY (`parentAcl_id`) REFERENCES `AclHibernate` (`id`),
  CONSTRAINT `FKD9188F08C2DC041C` FOREIGN KEY (`objectIdentity_id`) REFERENCES `ObjectIdentityHibernate` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AclHibernate`
--

LOCK TABLES `AclHibernate` WRITE;
/*!40000 ALTER TABLE `AclHibernate` DISABLE KEYS */;
INSERT INTO `AclHibernate` VALUES (1,'',1,1,NULL),(2,'',2,1,NULL),(3,'',3,1,NULL),(4,'',4,1,NULL),(5,'',5,1,NULL),(6,'',6,1,NULL),(7,'',3,2,NULL),(8,'',4,2,NULL),(9,'',6,2,NULL);
/*!40000 ALTER TABLE `AclHibernate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Block`
--

DROP TABLE IF EXISTS `Block`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Block` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Block`
--

LOCK TABLES `Block` WRITE;
/*!40000 ALTER TABLE `Block` DISABLE KEYS */;
/*!40000 ALTER TABLE `Block` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Block_Treatmentarm`
--

DROP TABLE IF EXISTS `Block_Treatmentarm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Block_Treatmentarm` (
  `Block_id` bigint(20) NOT NULL,
  `Treatmentarm_id` bigint(20) NOT NULL,
  KEY `FKDB68D196C89948D2` (`Treatmentarm_id`),
  KEY `FKDB68D19611070FF5` (`Block_id`),
  CONSTRAINT `FKDB68D19611070FF5` FOREIGN KEY (`Block_id`) REFERENCES `Block` (`id`),
  CONSTRAINT `FKDB68D196C89948D2` FOREIGN KEY (`Treatmentarm_id`) REFERENCES `TreatmentArm` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Block_Treatmentarm`
--

LOCK TABLES `Block_Treatmentarm` WRITE;
/*!40000 ALTER TABLE `Block_Treatmentarm` DISABLE KEYS */;
/*!40000 ALTER TABLE `Block_Treatmentarm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Criterion`
--

DROP TABLE IF EXISTS `Criterion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Criterion` (
  `DTYPE` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `option1` varchar(255) DEFAULT NULL,
  `option2` varchar(255) DEFAULT NULL,
  `inclusionConstraint_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8E542E8112FA816F` (`inclusionConstraint_id`),
  CONSTRAINT `FK8E542E8112FA816F` FOREIGN KEY (`inclusionConstraint_id`) REFERENCES `AbstractConstraint` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Criterion`
--

LOCK TABLES `Criterion` WRITE;
/*!40000 ALTER TABLE `Criterion` DISABLE KEYS */;
/*!40000 ALTER TABLE `Criterion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Criterion_AbstractConstraint`
--

DROP TABLE IF EXISTS `Criterion_AbstractConstraint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Criterion_AbstractConstraint` (
  `Criterion_id` bigint(20) NOT NULL,
  `strata_id` bigint(20) NOT NULL,
  UNIQUE KEY `strata_id` (`strata_id`),
  KEY `FKCFB2BA3D6DAACAC1` (`Criterion_id`),
  KEY `FKCFB2BA3DF129B42B` (`strata_id`),
  CONSTRAINT `FKCFB2BA3DF129B42B` FOREIGN KEY (`strata_id`) REFERENCES `AbstractConstraint` (`id`),
  CONSTRAINT `FKCFB2BA3D6DAACAC1` FOREIGN KEY (`Criterion_id`) REFERENCES `Criterion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Criterion_AbstractConstraint`
--

LOCK TABLES `Criterion_AbstractConstraint` WRITE;
/*!40000 ALTER TABLE `Criterion_AbstractConstraint` DISABLE KEYS */;
/*!40000 ALTER TABLE `Criterion_AbstractConstraint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Criterion_elements`
--

DROP TABLE IF EXISTS `Criterion_elements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Criterion_elements` (
  `Criterion_id` bigint(20) NOT NULL,
  `element` varchar(255) DEFAULT NULL,
  KEY `FKA06784F56CD64AE0` (`Criterion_id`),
  CONSTRAINT `FKA06784F56CD64AE0` FOREIGN KEY (`Criterion_id`) REFERENCES `Criterion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Criterion_elements`
--

LOCK TABLES `Criterion_elements` WRITE;
/*!40000 ALTER TABLE `Criterion_elements` DISABLE KEYS */;
/*!40000 ALTER TABLE `Criterion_elements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LogEntry`
--

DROP TABLE IF EXISTS `LogEntry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LogEntry` (
  `id` bigint(20) NOT NULL,
  `action` varchar(255) DEFAULT NULL,
  `clazz` varchar(255) DEFAULT NULL,
  `identifier` bigint(20) NOT NULL,
  `time` datetime DEFAULT NULL,
  `uiName` longtext,
  `username` varchar(255) DEFAULT NULL,
  `value` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LogEntry`
--

LOCK TABLES `LogEntry` WRITE;
/*!40000 ALTER TABLE `LogEntry` DISABLE KEYS */;
/*!40000 ALTER TABLE `LogEntry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Login`
--

DROP TABLE IF EXISTS `Login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Login` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `version` int(11) NOT NULL,
  `active` bit(1) NOT NULL,
  `lastLoggedIn` datetime DEFAULT NULL,
  `lockTime` datetime DEFAULT NULL,
  `numberWrongLogins` tinyint(4) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prefLocale` varchar(255) DEFAULT NULL,
  `username` varchar(100) NOT NULL,
  `person_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `FK462FF49A5A14952` (`person_id`),
  CONSTRAINT `FK462FF49A5A14952` FOREIGN KEY (`person_id`) REFERENCES `Person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Login`
--

LOCK TABLES `Login` WRITE;
/*!40000 ALTER TABLE `Login` DISABLE KEYS */;
INSERT INTO `Login` VALUES (1,'2011-09-07 17:57:11','2011-09-07 17:57:11',0,'\0',NULL,NULL,0,'b110305f87eac883525c414568c3a847290039f30a3aa534b5e603a3ac604264','de','administrator@example.com',1);
/*!40000 ALTER TABLE `Login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Login_Role`
--

DROP TABLE IF EXISTS `Login_Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Login_Role` (
  `Login_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  PRIMARY KEY (`Login_id`,`roles_id`),
  KEY `FKE97D3CC52C66262` (`Login_id`),
  KEY `FKE97D3CCACE4300B` (`roles_id`),
  CONSTRAINT `FKE97D3CCACE4300B` FOREIGN KEY (`roles_id`) REFERENCES `Role` (`id`),
  CONSTRAINT `FKE97D3CC52C66262` FOREIGN KEY (`Login_id`) REFERENCES `Login` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Login_Role`
--

LOCK TABLES `Login_Role` WRITE;
/*!40000 ALTER TABLE `Login_Role` DISABLE KEYS */;
INSERT INTO `Login_Role` VALUES (1,2),(1,7);
/*!40000 ALTER TABLE `Login_Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MinimizationMapElementWrapper`
--

DROP TABLE IF EXISTS `MinimizationMapElementWrapper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MinimizationMapElementWrapper` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MinimizationMapElementWrapper`
--

LOCK TABLES `MinimizationMapElementWrapper` WRITE;
/*!40000 ALTER TABLE `MinimizationMapElementWrapper` DISABLE KEYS */;
/*!40000 ALTER TABLE `MinimizationMapElementWrapper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MinimizationMapElementWrapper_map`
--

DROP TABLE IF EXISTS `MinimizationMapElementWrapper_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MinimizationMapElementWrapper_map` (
  `MinimizationMapElementWrapper_id` bigint(20) NOT NULL,
  `element` double DEFAULT NULL,
  `map_KEY` bigint(20) NOT NULL,
  PRIMARY KEY (`MinimizationMapElementWrapper_id`,`map_KEY`),
  KEY `FK51441962C4B80FD5` (`MinimizationMapElementWrapper_id`),
  KEY `FK514419626952E578` (`map_KEY`),
  CONSTRAINT `FK514419626952E578` FOREIGN KEY (`map_KEY`) REFERENCES `TreatmentArm` (`id`),
  CONSTRAINT `FK51441962C4B80FD5` FOREIGN KEY (`MinimizationMapElementWrapper_id`) REFERENCES `MinimizationMapElementWrapper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MinimizationMapElementWrapper_map`
--

LOCK TABLES `MinimizationMapElementWrapper_map` WRITE;
/*!40000 ALTER TABLE `MinimizationMapElementWrapper_map` DISABLE KEYS */;
/*!40000 ALTER TABLE `MinimizationMapElementWrapper_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MinimizationTempData_CountConstraints`
--

DROP TABLE IF EXISTS `MinimizationTempData_CountConstraints`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MinimizationTempData_CountConstraints` (
  `constraints_id` bigint(20) NOT NULL,
  `minimizationMapElementWrapper_id` bigint(20) NOT NULL,
  `countConstraints_KEY` bigint(20) NOT NULL,
  PRIMARY KEY (`constraints_id`,`countConstraints_KEY`),
  UNIQUE KEY `minimizationMapElementWrapper_id` (`minimizationMapElementWrapper_id`),
  KEY `FKD3B8791AC4B80FD5` (`minimizationMapElementWrapper_id`),
  KEY `FKD3B8791AB11DD235` (`countConstraints_KEY`),
  KEY `FKD3B8791AAE3E0175` (`constraints_id`),
  CONSTRAINT `FKD3B8791AAE3E0175` FOREIGN KEY (`constraints_id`) REFERENCES `RandomisationTempData` (`id`),
  CONSTRAINT `FKD3B8791AB11DD235` FOREIGN KEY (`countConstraints_KEY`) REFERENCES `AbstractConstraint` (`id`),
  CONSTRAINT `FKD3B8791AC4B80FD5` FOREIGN KEY (`minimizationMapElementWrapper_id`) REFERENCES `MinimizationMapElementWrapper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MinimizationTempData_CountConstraints`
--

LOCK TABLES `MinimizationTempData_CountConstraints` WRITE;
/*!40000 ALTER TABLE `MinimizationTempData_CountConstraints` DISABLE KEYS */;
/*!40000 ALTER TABLE `MinimizationTempData_CountConstraints` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MinimizationTempData_Probabilities`
--

DROP TABLE IF EXISTS `MinimizationTempData_Probabilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MinimizationTempData_Probabilities` (
  `treatmentArm_id` bigint(20) NOT NULL,
  `minimizationMapElementWrapper_id` bigint(20) NOT NULL,
  `probabilitiesPerPreferredTreatment_KEY` bigint(20) NOT NULL,
  PRIMARY KEY (`treatmentArm_id`,`probabilitiesPerPreferredTreatment_KEY`),
  UNIQUE KEY `minimizationMapElementWrapper_id` (`minimizationMapElementWrapper_id`),
  KEY `FKA5B100C4B80FD5` (`minimizationMapElementWrapper_id`),
  KEY `FKA5B100C1F43207` (`treatmentArm_id`),
  KEY `FKA5B10017BF90DD` (`probabilitiesPerPreferredTreatment_KEY`),
  CONSTRAINT `FKA5B10017BF90DD` FOREIGN KEY (`probabilitiesPerPreferredTreatment_KEY`) REFERENCES `TreatmentArm` (`id`),
  CONSTRAINT `FKA5B100C1F43207` FOREIGN KEY (`treatmentArm_id`) REFERENCES `RandomisationTempData` (`id`),
  CONSTRAINT `FKA5B100C4B80FD5` FOREIGN KEY (`minimizationMapElementWrapper_id`) REFERENCES `MinimizationMapElementWrapper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MinimizationTempData_Probabilities`
--

LOCK TABLES `MinimizationTempData_Probabilities` WRITE;
/*!40000 ALTER TABLE `MinimizationTempData_Probabilities` DISABLE KEYS */;
/*!40000 ALTER TABLE `MinimizationTempData_Probabilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MinimizationTempData_countTrialSites`
--

DROP TABLE IF EXISTS `MinimizationTempData_countTrialSites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MinimizationTempData_countTrialSites` (
  `trialSite_id` bigint(20) NOT NULL,
  `minimizationMapElementWrapper_id` bigint(20) NOT NULL,
  `countTrialSites_KEY` bigint(20) NOT NULL,
  PRIMARY KEY (`trialSite_id`,`countTrialSites_KEY`),
  UNIQUE KEY `minimizationMapElementWrapper_id` (`minimizationMapElementWrapper_id`),
  KEY `FK60BF9152C4B80FD5` (`minimizationMapElementWrapper_id`),
  KEY `FK60BF9152AB57CEEE` (`trialSite_id`),
  KEY `FK60BF91529309DEEA` (`countTrialSites_KEY`),
  CONSTRAINT `FK60BF91529309DEEA` FOREIGN KEY (`countTrialSites_KEY`) REFERENCES `TrialSite` (`id`),
  CONSTRAINT `FK60BF9152AB57CEEE` FOREIGN KEY (`trialSite_id`) REFERENCES `RandomisationTempData` (`id`),
  CONSTRAINT `FK60BF9152C4B80FD5` FOREIGN KEY (`minimizationMapElementWrapper_id`) REFERENCES `MinimizationMapElementWrapper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MinimizationTempData_countTrialSites`
--

LOCK TABLES `MinimizationTempData_countTrialSites` WRITE;
/*!40000 ALTER TABLE `MinimizationTempData_countTrialSites` DISABLE KEYS */;
/*!40000 ALTER TABLE `MinimizationTempData_countTrialSites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ObjectIdentityHibernate`
--

DROP TABLE IF EXISTS `ObjectIdentityHibernate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ObjectIdentityHibernate` (
  `id` bigint(20) NOT NULL,
  `identifier` bigint(20) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ObjectIdentityHibernate`
--

LOCK TABLES `ObjectIdentityHibernate` WRITE;
/*!40000 ALTER TABLE `ObjectIdentityHibernate` DISABLE KEYS */;
INSERT INTO `ObjectIdentityHibernate` VALUES (1,-2147483648,'de.randi2.org.randi2.installer.model.Login'),(2,-2147483648,'de.randi2.org.randi2.installer.model.Person'),(3,1,'de.randi2.org.randi2.installer.model.Login'),(4,1,'de.randi2.org.randi2.installer.model.Person'),(5,-2147483648,'de.randi2.org.randi2.installer.model.TrialSite'),(6,1,'de.randi2.org.randi2.installer.model.TrialSite');
/*!40000 ALTER TABLE `ObjectIdentityHibernate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Person`
--

DROP TABLE IF EXISTS `Person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Person` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `firstname` varchar(50) NOT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `phone` varchar(255) NOT NULL,
  `sex` varchar(255) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `title` varchar(20) DEFAULT NULL,
  `assistant_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8E4887751B265929` (`assistant_id`),
  CONSTRAINT `FK8E4887751B265929` FOREIGN KEY (`assistant_id`) REFERENCES `Person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Person`
--

LOCK TABLES `Person` WRITE;
/*!40000 ALTER TABLE `Person` DISABLE KEYS */;
INSERT INTO `Person` VALUES (1,'2011-09-07 17:57:11','2011-09-07 17:57:11',0,'administrator@example.com','','Max','','1234567','MALE','Administrator','',NULL),(2,'2011-09-07 17:57:11','2011-09-07 17:57:11',0,'randi2@action.ms','','Contact','','1234567','MALE','Person','',NULL);
/*!40000 ALTER TABLE `Person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RandomisationTempData`
--

DROP TABLE IF EXISTS `RandomisationTempData`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RandomisationTempData` (
  `DTYPE` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RandomisationTempData`
--

LOCK TABLES `RandomisationTempData` WRITE;
/*!40000 ALTER TABLE `RandomisationTempData` DISABLE KEYS */;
/*!40000 ALTER TABLE `RandomisationTempData` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RandomisationTempData_Block`
--

DROP TABLE IF EXISTS `RandomisationTempData_Block`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RandomisationTempData_Block` (
  `RandomisationTempData_id` bigint(20) NOT NULL,
  `blocks_id` bigint(20) NOT NULL,
  `mapkey` varchar(255) NOT NULL,
  PRIMARY KEY (`RandomisationTempData_id`,`mapkey`),
  UNIQUE KEY `blocks_id` (`blocks_id`),
  KEY `FKED3A86746CB2385C` (`blocks_id`),
  KEY `FKED3A86747BC3928B` (`RandomisationTempData_id`),
  CONSTRAINT `FKED3A86747BC3928B` FOREIGN KEY (`RandomisationTempData_id`) REFERENCES `RandomisationTempData` (`id`),
  CONSTRAINT `FKED3A86746CB2385C` FOREIGN KEY (`blocks_id`) REFERENCES `Block` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RandomisationTempData_Block`
--

LOCK TABLES `RandomisationTempData_Block` WRITE;
/*!40000 ALTER TABLE `RandomisationTempData_Block` DISABLE KEYS */;
/*!40000 ALTER TABLE `RandomisationTempData_Block` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RandomisationTempData_Urn`
--

DROP TABLE IF EXISTS `RandomisationTempData_Urn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RandomisationTempData_Urn` (
  `RandomisationTempData_id` bigint(20) NOT NULL,
  `urns_id` bigint(20) NOT NULL,
  `mapkey` varchar(255) NOT NULL,
  PRIMARY KEY (`RandomisationTempData_id`,`mapkey`),
  UNIQUE KEY `urns_id` (`urns_id`),
  KEY `FKA169D518B77AC2E4` (`urns_id`),
  KEY `FKA169D518D36F3EBC` (`RandomisationTempData_id`),
  CONSTRAINT `FKA169D518D36F3EBC` FOREIGN KEY (`RandomisationTempData_id`) REFERENCES `RandomisationTempData` (`id`),
  CONSTRAINT `FKA169D518B77AC2E4` FOREIGN KEY (`urns_id`) REFERENCES `Urn` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RandomisationTempData_Urn`
--

LOCK TABLES `RandomisationTempData_Urn` WRITE;
/*!40000 ALTER TABLE `RandomisationTempData_Urn` DISABLE KEYS */;
/*!40000 ALTER TABLE `RandomisationTempData_Urn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RandomizationConfig`
--

DROP TABLE IF EXISTS `RandomizationConfig`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RandomizationConfig` (
  `DTYPE` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `version` int(11) NOT NULL,
  `maximum` int(11) DEFAULT NULL,
  `minimum` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `biasedCoinMinimization` bit(1) DEFAULT NULL,
  `p` double DEFAULT NULL,
  `withRandomizedSubjects` bit(1) DEFAULT NULL,
  `countReplacedBalls` int(11) DEFAULT NULL,
  `initializeCountBalls` int(11) DEFAULT NULL,
  `tempData_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKEBFF50C3AA393BD4` (`tempData_id`),
  CONSTRAINT `FKEBFF50C3AA393BD4` FOREIGN KEY (`tempData_id`) REFERENCES `RandomisationTempData` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RandomizationConfig`
--

LOCK TABLES `RandomizationConfig` WRITE;
/*!40000 ALTER TABLE `RandomizationConfig` DISABLE KEYS */;
/*!40000 ALTER TABLE `RandomizationConfig` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Role`
--

DROP TABLE IF EXISTS `Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Role` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `version` int(11) NOT NULL,
  `adminOtherUser` bit(1) NOT NULL,
  `adminOwnUser` bit(1) NOT NULL,
  `adminTrial` bit(1) NOT NULL,
  `adminTrialSite` bit(1) NOT NULL,
  `adminTrialSubject` bit(1) NOT NULL,
  `createRole` bit(1) NOT NULL,
  `createTrial` bit(1) NOT NULL,
  `createTrialSite` bit(1) NOT NULL,
  `createTrialSubject` bit(1) NOT NULL,
  `createUser` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `readOtherUser` bit(1) NOT NULL,
  `readOwnUser` bit(1) NOT NULL,
  `readTrial` bit(1) NOT NULL,
  `readTrialSite` bit(1) NOT NULL,
  `readTrialSubject` bit(1) NOT NULL,
  `scopeTrialCreate` bit(1) NOT NULL,
  `scopeTrialRead` bit(1) NOT NULL,
  `scopeTrialSiteView` bit(1) NOT NULL,
  `scopeTrialSiteWrite` bit(1) NOT NULL,
  `scopeTrialWrite` bit(1) NOT NULL,
  `scopeUserCreate` bit(1) NOT NULL,
  `scopeUserRead` bit(1) NOT NULL,
  `scopeUserWrite` bit(1) NOT NULL,
  `writeOtherUser` bit(1) NOT NULL,
  `writeOwnUser` bit(1) NOT NULL,
  `writeTrial` bit(1) NOT NULL,
  `writeTrialSite` bit(1) NOT NULL,
  `writeTrialSubject` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
INSERT INTO `Role` VALUES (1,'2011-09-07 17:57:11','2011-09-07 17:57:11',1,'\0','\0','\0','\0','\0','\0','\0','\0','','\0','ROLE_INVESTIGATOR','','','','','\0','','','\0','','','','','','\0','','\0','\0','\0'),(2,'2011-09-07 17:57:11','2011-09-07 17:57:11',0,'\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','ROLE_USER','\0','','\0','\0','\0','','','','','','','','','\0','','\0','\0','\0'),(3,'2011-09-07 17:57:11','2011-09-07 17:57:11',0,'\0','','\0','\0','\0','\0','\0','\0','\0','\0','ROLE_STATISTICAN','','','','','','','','\0','','','','','','\0','','\0','\0','\0'),(4,'2011-09-07 17:57:11','2011-09-07 17:57:11',0,'\0','','\0','\0','\0','\0','\0','\0','\0','\0','ROLE_MONITOR','','','','','','','','\0','','','','','','\0','','\0','\0','\0'),(5,'2011-09-07 17:57:11','2011-09-07 17:57:11',0,'\0','\0','\0','\0','\0','\0','','\0','\0','','ROLE_P_INVESTIGATOR','','','','','','','','\0','','','','','','','','','\0','\0'),(6,'2011-09-07 17:57:11','2011-09-07 17:57:11',0,'\0','\0','\0','\0','\0','\0','\0','\0','\0','','ROLE_ANONYMOUS','','\0','\0','','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0'),(7,'2011-09-07 17:57:11','2011-09-07 17:57:11',0,'','\0','\0','','\0','','\0','','\0','','ROLE_ADMIN','','','','','\0','\0','\0','\0','','\0','\0','\0','\0','','','\0','','\0');
/*!40000 ALTER TABLE `Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Role_Role`
--

DROP TABLE IF EXISTS `Role_Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Role_Role` (
  `Role_id` bigint(20) NOT NULL,
  `rolesToAssign_id` bigint(20) NOT NULL,
  KEY `FK8B6A269FB5E88DA1` (`rolesToAssign_id`),
  KEY `FK8B6A269FECBB7B2` (`Role_id`),
  CONSTRAINT `FK8B6A269FECBB7B2` FOREIGN KEY (`Role_id`) REFERENCES `Role` (`id`),
  CONSTRAINT `FK8B6A269FB5E88DA1` FOREIGN KEY (`rolesToAssign_id`) REFERENCES `Role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role_Role`
--

LOCK TABLES `Role_Role` WRITE;
/*!40000 ALTER TABLE `Role_Role` DISABLE KEYS */;
INSERT INTO `Role_Role` VALUES (5,1),(5,3),(5,4),(5,2),(7,1),(7,4),(7,5),(7,3),(7,2),(7,7),(1,1);
/*!40000 ALTER TABLE `Role_Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SidHibernate`
--

DROP TABLE IF EXISTS `SidHibernate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SidHibernate` (
  `id` bigint(20) NOT NULL,
  `sidname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SidHibernate`
--

LOCK TABLES `SidHibernate` WRITE;
/*!40000 ALTER TABLE `SidHibernate` DISABLE KEYS */;
INSERT INTO `SidHibernate` VALUES (1,'administrator@example.com'),(2,'ROLE_ANONYMOUS');
/*!40000 ALTER TABLE `SidHibernate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SubjectProperty`
--

DROP TABLE IF EXISTS `SubjectProperty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SubjectProperty` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `version` int(11) NOT NULL,
  `value` longblob,
  `criterion_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK88363016DAACAC1` (`criterion_id`),
  CONSTRAINT `FK88363016DAACAC1` FOREIGN KEY (`criterion_id`) REFERENCES `Criterion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SubjectProperty`
--

LOCK TABLES `SubjectProperty` WRITE;
/*!40000 ALTER TABLE `SubjectProperty` DISABLE KEYS */;
/*!40000 ALTER TABLE `SubjectProperty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TreatmentArm`
--

DROP TABLE IF EXISTS `TreatmentArm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TreatmentArm` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `version` int(11) NOT NULL,
  `description` longtext NOT NULL,
  `name` varchar(255) NOT NULL,
  `plannedSubjects` int(11) NOT NULL,
  `trial_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKADB66AE433ECA742` (`trial_id`),
  CONSTRAINT `FKADB66AE433ECA742` FOREIGN KEY (`trial_id`) REFERENCES `Trial` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TreatmentArm`
--

LOCK TABLES `TreatmentArm` WRITE;
/*!40000 ALTER TABLE `TreatmentArm` DISABLE KEYS */;
/*!40000 ALTER TABLE `TreatmentArm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Trial`
--

DROP TABLE IF EXISTS `Trial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Trial` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `version` int(11) NOT NULL,
  `abbreviation` varchar(255) DEFAULT NULL,
  `description` longtext,
  `endDate` datetime DEFAULT NULL,
  `generateIds` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `protocol` tinyblob,
  `startDate` datetime DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `stratifyTrialSite` bit(1) NOT NULL,
  `leadingSite_id` bigint(20) NOT NULL,
  `randomConf_id` bigint(20) DEFAULT NULL,
  `sponsorInvestigator_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4D51EF6D5402E24` (`sponsorInvestigator_id`),
  KEY `FK4D51EF6E2BD5412` (`leadingSite_id`),
  KEY `FK4D51EF68114226F` (`randomConf_id`),
  CONSTRAINT `FK4D51EF68114226F` FOREIGN KEY (`randomConf_id`) REFERENCES `RandomizationConfig` (`id`),
  CONSTRAINT `FK4D51EF6D5402E24` FOREIGN KEY (`sponsorInvestigator_id`) REFERENCES `Person` (`id`),
  CONSTRAINT `FK4D51EF6E2BD5412` FOREIGN KEY (`leadingSite_id`) REFERENCES `TrialSite` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Trial`
--

LOCK TABLES `Trial` WRITE;
/*!40000 ALTER TABLE `Trial` DISABLE KEYS */;
/*!40000 ALTER TABLE `Trial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TrialSite`
--

DROP TABLE IF EXISTS `TrialSite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TrialSite` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `version` int(11) NOT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `postcode` varchar(10) NOT NULL,
  `street` varchar(255) NOT NULL,
  `contactPerson_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `FK46C8075D27E0C932` (`contactPerson_id`),
  CONSTRAINT `FK46C8075D27E0C932` FOREIGN KEY (`contactPerson_id`) REFERENCES `Person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TrialSite`
--

LOCK TABLES `TrialSite` WRITE;
/*!40000 ALTER TABLE `TrialSite` DISABLE KEYS */;
INSERT INTO `TrialSite` VALUES (1,'2011-09-07 17:57:11','2011-09-07 17:57:11',0,'Heidelberg','Germany','Trial Site 1','700b1a555f4ffbf50a558bbd1589cdafe2ab94b1e4626cc1af48dff64b0055ae','69120','INF',2);
/*!40000 ALTER TABLE `TrialSite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TrialSite_Person`
--

DROP TABLE IF EXISTS `TrialSite_Person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TrialSite_Person` (
  `TrialSite_id` bigint(20) NOT NULL,
  `members_id` bigint(20) NOT NULL,
  UNIQUE KEY `members_id` (`members_id`),
  KEY `FK1CF7EA9722E4150E` (`members_id`),
  KEY `FK1CF7EA97B617FE02` (`TrialSite_id`),
  CONSTRAINT `FK1CF7EA97B617FE02` FOREIGN KEY (`TrialSite_id`) REFERENCES `TrialSite` (`id`),
  CONSTRAINT `FK1CF7EA9722E4150E` FOREIGN KEY (`members_id`) REFERENCES `Person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TrialSite_Person`
--

LOCK TABLES `TrialSite_Person` WRITE;
/*!40000 ALTER TABLE `TrialSite_Person` DISABLE KEYS */;
INSERT INTO `TrialSite_Person` VALUES (1,1);
/*!40000 ALTER TABLE `TrialSite_Person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TrialSubject`
--

DROP TABLE IF EXISTS `TrialSubject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TrialSubject` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `version` int(11) NOT NULL,
  `counter` int(11) NOT NULL,
  `identification` varchar(255) NOT NULL,
  `randNumber` varchar(255) NOT NULL,
  `arm_id` bigint(20) NOT NULL,
  `investigator_id` bigint(20) DEFAULT NULL,
  `trialSite_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKFB112D56C32A2E22` (`investigator_id`),
  KEY `FKFB112D56B617FE02` (`trialSite_id`),
  KEY `FKFB112D56E36E0A5A` (`arm_id`),
  CONSTRAINT `FKFB112D56E36E0A5A` FOREIGN KEY (`arm_id`) REFERENCES `TreatmentArm` (`id`),
  CONSTRAINT `FKFB112D56B617FE02` FOREIGN KEY (`trialSite_id`) REFERENCES `TrialSite` (`id`),
  CONSTRAINT `FKFB112D56C32A2E22` FOREIGN KEY (`investigator_id`) REFERENCES `Login` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TrialSubject`
--

LOCK TABLES `TrialSubject` WRITE;
/*!40000 ALTER TABLE `TrialSubject` DISABLE KEYS */;
/*!40000 ALTER TABLE `TrialSubject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TrialSubject_SubjectProperty`
--

DROP TABLE IF EXISTS `TrialSubject_SubjectProperty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TrialSubject_SubjectProperty` (
  `TrialSubject_id` bigint(20) NOT NULL,
  `properties_id` bigint(20) NOT NULL,
  PRIMARY KEY (`TrialSubject_id`,`properties_id`),
  UNIQUE KEY `properties_id` (`properties_id`),
  KEY `FK6BCC7A1835E03A10` (`properties_id`),
  KEY `FK6BCC7A183D50EB92` (`TrialSubject_id`),
  CONSTRAINT `FK6BCC7A183D50EB92` FOREIGN KEY (`TrialSubject_id`) REFERENCES `TrialSubject` (`id`),
  CONSTRAINT `FK6BCC7A1835E03A10` FOREIGN KEY (`properties_id`) REFERENCES `SubjectProperty` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TrialSubject_SubjectProperty`
--

LOCK TABLES `TrialSubject_SubjectProperty` WRITE;
/*!40000 ALTER TABLE `TrialSubject_SubjectProperty` DISABLE KEYS */;
/*!40000 ALTER TABLE `TrialSubject_SubjectProperty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Trial_Criterion`
--

DROP TABLE IF EXISTS `Trial_Criterion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Trial_Criterion` (
  `Trial_id` bigint(20) NOT NULL,
  `subjectCriteria_id` bigint(20) NOT NULL,
  UNIQUE KEY `subjectCriteria_id` (`subjectCriteria_id`),
  KEY `FKD4E08C7833ECA742` (`Trial_id`),
  KEY `FKD4E08C78F73E5097` (`subjectCriteria_id`),
  CONSTRAINT `FKD4E08C78F73E5097` FOREIGN KEY (`subjectCriteria_id`) REFERENCES `Criterion` (`id`),
  CONSTRAINT `FKD4E08C7833ECA742` FOREIGN KEY (`Trial_id`) REFERENCES `Trial` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Trial_Criterion`
--

LOCK TABLES `Trial_Criterion` WRITE;
/*!40000 ALTER TABLE `Trial_Criterion` DISABLE KEYS */;
/*!40000 ALTER TABLE `Trial_Criterion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Trial_TrialSite`
--

DROP TABLE IF EXISTS `Trial_TrialSite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Trial_TrialSite` (
  `trials_id` bigint(20) NOT NULL,
  `participatingSites_id` bigint(20) NOT NULL,
  PRIMARY KEY (`trials_id`,`participatingSites_id`),
  KEY `FK8D546554AA0BF0DB` (`trials_id`),
  KEY `FK8D546554DD32770E` (`participatingSites_id`),
  CONSTRAINT `FK8D546554DD32770E` FOREIGN KEY (`participatingSites_id`) REFERENCES `TrialSite` (`id`),
  CONSTRAINT `FK8D546554AA0BF0DB` FOREIGN KEY (`trials_id`) REFERENCES `Trial` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Trial_TrialSite`
--

LOCK TABLES `Trial_TrialSite` WRITE;
/*!40000 ALTER TABLE `Trial_TrialSite` DISABLE KEYS */;
/*!40000 ALTER TABLE `Trial_TrialSite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Urn`
--

DROP TABLE IF EXISTS `Urn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Urn` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Urn`
--

LOCK TABLES `Urn` WRITE;
/*!40000 ALTER TABLE `Urn` DISABLE KEYS */;
/*!40000 ALTER TABLE `Urn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Urn_Treatmentarm`
--

DROP TABLE IF EXISTS `Urn_Treatmentarm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Urn_Treatmentarm` (
  `Urn_id` bigint(20) NOT NULL,
  `Treatmentarm_id` bigint(20) NOT NULL,
  KEY `FK26C6EB32C89948D2` (`Treatmentarm_id`),
  KEY `FK26C6EB328F965035` (`Urn_id`),
  CONSTRAINT `FK26C6EB328F965035` FOREIGN KEY (`Urn_id`) REFERENCES `Urn` (`id`),
  CONSTRAINT `FK26C6EB32C89948D2` FOREIGN KEY (`Treatmentarm_id`) REFERENCES `TreatmentArm` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Urn_Treatmentarm`
--

LOCK TABLES `Urn_Treatmentarm` WRITE;
/*!40000 ALTER TABLE `Urn_Treatmentarm` DISABLE KEYS */;
/*!40000 ALTER TABLE `Urn_Treatmentarm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('Role',1),('Login',1),('Person',1),('TrialSite',1),('ObjectIdentityHibernate',1),('SidHibernate',1),('AclHibernate',1),('AccessControlEntryHibernate',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-09-07 17:58:11
