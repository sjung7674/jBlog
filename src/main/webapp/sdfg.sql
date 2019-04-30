/*
SQLyog Community v12.3.3 (64 bit)
MySQL - 5.7.17 : Database - jblog
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`jblog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `jblog`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(200) NOT NULL,
  `password` varchar(1000) NOT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(200) NOT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(200) NOT NULL,
  `password` varchar(1000) NOT NULL,
  `user_image` varchar(100) DEFAULT NULL,
  `nikc_name` varchar(500) NOT NULL,
  PRIMARY KEY (`idx`),
  UNIQUE KEY `UNIQUE` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `post` */

DROP TABLE IF EXISTS `post`;

CREATE TABLE `post` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(200) NOT NULL,
  `category` int(11) NOT NULL,
  `title` varchar(1000) NOT NULL,
  `sub_title` varchar(1000) DEFAULT NULL,
  `content` text,
  `header_image` varchar(1000) DEFAULT NULL,
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idx`),
  KEY `post_ibfk_1` (`userid`),
  KEY `post_ibfk_2` (`category`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `member` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `post_ibfk_2` FOREIGN KEY (`category`) REFERENCES `category` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

/*Table structure for table `visit_stat_all` */

DROP TABLE IF EXISTS `visit_stat_all`;

CREATE TABLE `visit_stat_all` (
  `idx` int(50) NOT NULL AUTO_INCREMENT,
  `YEAR_` int(10) DEFAULT NULL,
  `MONTH_` int(10) DEFAULT NULL,
  `DATE_` int(10) DEFAULT NULL,
  `HOUR_` int(10) DEFAULT NULL,
  `WEEK_` int(10) DEFAULT NULL,
  `USERIP` varchar(15) DEFAULT NULL,
  `USERID` varchar(50) DEFAULT NULL,
  `USERTYPE` varchar(1) DEFAULT NULL,
  `DEVICE` varchar(10) DEFAULT NULL,
  `FNAME` varchar(255) DEFAULT NULL,
  `LNAME` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PARAM` varchar(1000) DEFAULT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `PREPAGE` varchar(1000) DEFAULT NULL,
  `REGDATE` datetime DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=18898213 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
