CREATE TABLE `jblog`.`member`(  
  `idx` INT(11) NOT NULL AUTO_INCREMENT,
  `userid` VARCHAR(200) NOT NULL,
  `password` VARCHAR(1000) NOT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1;
ALTER TABLE `jblog`.`member` ADD UNIQUE INDEX `UNIQUE` (`userid`); 

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