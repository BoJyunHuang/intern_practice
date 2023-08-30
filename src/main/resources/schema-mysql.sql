CREATE TABLE IF NOT EXISTS `intern_practice`.`person_info` (
  `employee_number` INT NOT NULL AUTO_INCREMENT,
  `full_name` VARCHAR(45) NOT NULL,
  `kana_name` VARCHAR(45) NULL,
  `romanized_name` VARCHAR(45) NULL,
  `nationality` VARCHAR(45) NOT NULL,
  `gender` VARCHAR(20) NOT NULL,
  `birth_date` DATE NOT NULL,
  `join_date` DATE NOT NULL,
  `departure_date` DATE NULL,
  `deleted_flag` TINYINT NOT NULL,
  PRIMARY KEY (`employee_number`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `intern_practice`.`extended_profile` (
  `employee_number` INT NOT NULL DEFAULT 0,
  `telephone` VARCHAR(45) NULL,
  `mobile_phone` VARCHAR(45) NOT NULL,
  `company_email` VARCHAR(45) NOT NULL,
  `alternate_email` VARCHAR(45) NULL,
  `postal_code` VARCHAR(20) NULL,
  `address` VARCHAR(90) NOT NULL,
  `passport_number` VARCHAR(45) NOT NULL,
  `passport_expiry_date` DATE NOT NULL,
  `residence_card_number` VARCHAR(45) NOT NULL,
  `residence_card_start_date` DATE NOT NULL,
  `residence_card_expiration_date` DATE NOT NULL,
  `residence_card_status` VARCHAR(45) NOT NULL,
  `employment_insurance_number` VARCHAR(45) NOT NULL,
  `pension_number` VARCHAR(45) NOT NULL,
  `bank_name` VARCHAR(45) NOT NULL,
  `store_name` VARCHAR(45) NOT NULL,
  `account_number` VARCHAR(45) NOT NULL,
  `deleted_flag` TINYINT NOT NULL,
  PRIMARY KEY (`employee_number`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
