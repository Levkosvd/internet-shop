create database internet_shop;
CREATE TABLE `internet_shop`.`items` (
  `item_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `price` DECIMAL(9,4) NOT NULL,
  PRIMARY KEY (`item_id`));
INSERT INTO `internet_shop`.`items` (`name`, `price`) VALUES ('Samsung A7', '5200.0');
INSERT INTO `internet_shop`.`items` (`name`, `price`) VALUES ('Asus x75 vd', '9800.0');
INSERT INTO `internet_shop`.`items` (`name`, `price`) VALUES ('Iphone 11', '24000.0');