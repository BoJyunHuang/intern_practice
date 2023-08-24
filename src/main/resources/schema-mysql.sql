CREATE TABLE IF NOT EXISTS `intern_practice`.`latest_news` (
  `serial_number` INT NOT NULL AUTO_INCREMENT,
  `catalog` VARCHAR(20) NOT NULL,
  `subcatalog` VARCHAR(45) NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `content` VARCHAR(300) NOT NULL,
  `release_time` DATETIME NOT NULL,
  `reveal` TINYINT NOT NULL,
  PRIMARY KEY (`serial_number`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;