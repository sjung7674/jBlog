CREATE TABLE `jblog`.`member`(  
  `idx` INT(11) NOT NULL AUTO_INCREMENT,
  `userid` VARCHAR(200) NOT NULL,
  `password` VARCHAR(1000) NOT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1;
ALTER TABLE `jblog`.`member` ADD UNIQUE INDEX `UNIQUE` (`userid`); 
CREATE TABLE `member` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(200) NOT NULL,
  `password` varchar(1000) NOT NULL,
  `user_image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  UNIQUE KEY `UNIQUE` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8


CREATE TABLE `jblog`.`post`(
 `idx` INT(11) NOT NULL AUTO_INCREMENT, 
 `userid` VARCHAR(200) NOT NULL, 
 `category` INT(11) NOT NULL, 
 `title` VARCHAR(1000) NOT NULL, 
 `content` TEXT, 
 `reg_date` TIMESTAMP, PRIMARY KEY (`idx`), 
 FOREIGN KEY (`userid`) REFERENCES member (`userid`) ON DELETE RESTRICT ON UPDATE CASCADE,
 FOREIGN KEY (`category`) REFERENCES category (`idx`) ON DELETE RESTRICT ON UPDATE CASCADE
 ) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci 
 AUTO_INCREMENT=1; 
ALTER TABLE `jblog`.`post` ADD COLUMN `sub_title` VARCHAR(1000) NULL AFTER `title`; 
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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8



CREATE TABLE `jblog`.`admin`( 
`idx` INT(11) NOT NULL AUTO_INCREMENT, 
`userid` VARCHAR(200) NOT NULL, 
`password` VARCHAR(1000) NOT NULL, PRIMARY KEY (`idx`) 
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci 
AUTO_INCREMENT=1; 

CREATE TABLE `jblog`.`category`(
`idx` INT(11) NOT NULL AUTO_INCREMENT, `category` VARCHAR(200) NOT NULL, PRIMARY KEY (`idx`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci 
AUTO_INCREMENT=1; 

insert into member (userid, password) values ("sjung7674@naver.com","woxs9992")