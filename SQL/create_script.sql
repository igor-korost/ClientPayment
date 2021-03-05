CREATE DATABASE `client_payment` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE client_payment;

CREATE TABLE `client` (
    `id_client` INT NOT NULL AUTO_INCREMENT,
    `client_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id_client`))  
ENGINE=INNODB;

CREATE TABLE `payment` (
  `id_payment` INT NOT NULL AUTO_INCREMENT,
  `id_card` INT NOT NULL,
  `payment_date` DATETIME NOT NULL,
  `payment_sum` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id_payment`))
ENGINE = InnoDB;

CREATE TABLE `card` (
  `id_card` INT NOT NULL AUTO_INCREMENT,
  `id_client` INT NOT NULL,
  `card_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_card`),
  CONSTRAINT `fk_card_client`
    FOREIGN KEY (`id_client`)
    REFERENCES `client` (`id_client`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_card_payment`
    FOREIGN KEY (`id_card`)
    REFERENCES `payment` (`id_payment`)
    ON DELETE CASCADE
    ON UPDATE CASCADE) 
ENGINE = InnoDB;

CREATE INDEX `fk_card_client_idx` ON `card` (`id_client` ASC) ;
