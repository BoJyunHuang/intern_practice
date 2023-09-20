CREATE TABLE IF NOT EXISTS `intern_practice`.`news` (
  `news_id` INT NOT NULL AUTO_INCREMENT,
  `catalog` INT NOT NULL,
  `subcatalog` INT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `subtitle` VARCHAR(90) NOT NULL,
  `tags` VARCHAR(150) NULL DEFAULT 'null',
  `content` VARCHAR(1200) NOT NULL,
  `create_time` DATETIME NOT NULL,
  `publish_time` DATETIME NOT NULL,
  `edit_time` DATETIME NULL DEFAULT NULL,
  `expiration_time` DATETIME NOT NULL,
  `remove_time` DATETIME NULL DEFAULT NULL,
  `creator` VARCHAR(45) NOT NULL,
  `editor` VARCHAR(45) NULL DEFAULT 'null',
  `remover` VARCHAR(45) NULL DEFAULT 'null',
  `views` INT NOT NULL DEFAULT 0,
  `likes` INT NOT NULL DEFAULT 0,
  `dislikes` INT NOT NULL DEFAULT 0,
  `importance` INT NOT NULL,
  `audience_level` INT NOT NULL,
  `delete_flag` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`news_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `intern_practice`.`catalog` (
  `catalog_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `parent` VARCHAR(20) NOT NULL,
  `news_amount` INT NOT NULL DEFAULT 0,
  `delete_flag` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`catalog_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;