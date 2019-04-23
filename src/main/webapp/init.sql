CREATE TABLE `jblog`.`member`(  
  `idx` INT(11) NOT NULL AUTO_INCREMENT,
  `userid` VARCHAR(200) NOT NULL,
  `password` VARCHAR(1000) NOT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1;


insert into member (userid, password) values ("sjung7674@naver.com","woxs9992")