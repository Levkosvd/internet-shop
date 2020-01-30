create database internet_shop;
CREATE TABLE `internet_shop`.`items` (
  `item_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `price` DECIMAL(9,4) NOT NULL,
  PRIMARY KEY (`item_id`));
INSERT INTO `internet_shop`.`items` (`name`, `price`) VALUES ('Samsung A7', '5200.0');
INSERT INTO `internet_shop`.`items` (`name`, `price`) VALUES ('Asus x75 vd', '9800.0');
INSERT INTO `internet_shop`.`items` (`name`, `price`) VALUES ('Iphone 11', '24000.0');

CREATE TABLE `internet_shop`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `account_balance` FLOAT NOT NULL,
  `token` INT NULL,
  PRIMARY KEY (`user_id`));

CREATE TABLE `internet_shop`.`roles` (
                                         `role_id` INT NOT NULL,
                                         `role_name` VARCHAR(45) NOT NULL,
                                         PRIMARY KEY (`role_id`));

CREATE TABLE `internet_shop`.`role_user` (
                                             `role_user_id` INT NOT NULL AUTO_INCREMENT,
                                             `role_id` INT NOT NULL,
                                             `user_id` INT NOT NULL,
                                             PRIMARY KEY (`role_user_id`));
ALTER TABLE `internet_shop`.`role_user`
    ADD INDEX `user_role_user_id_fk_idx` (`user_id` ASC) VISIBLE,
    ADD INDEX `role_user_role_id_fk_idx` (`role_id` ASC) VISIBLE;
;
ALTER TABLE `internet_shop`.`role_user`
    ADD CONSTRAINT `role_user_user_id_fk`
        FOREIGN KEY (`user_id`)
            REFERENCES `internet_shop`.`users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    ADD CONSTRAINT `role_user_role_id_fk`
        FOREIGN KEY (`role_id`)
            REFERENCES `internet_shop`.`roles` (`role_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

CREATE TABLE `internet_shop`.`buckets` (
                                           `bucket_id` INT NOT NULL AUTO_INCREMENT,
                                           PRIMARY KEY (`bucket_id`));
CREATE TABLE `internet_shop`.`order_item` (
                                              `order_item_id` BIGINT NOT NULL AUTO_INCREMENT,
                                              `order_id` BIGINT NOT NULL,
                                              `item_id` BIGINT NOT NULL,
                                              PRIMARY KEY (`order_item_id`),
                                              INDEX `order_id_fk_idx` (`order_id` ASC) VISIBLE,
                                              INDEX `item_id_fk_idx` (`item_id` ASC) VISIBLE,
                                              CONSTRAINT `order_Item_order_id_fk`
                                                  FOREIGN KEY (`order_id`)
                                                      REFERENCES `internet_shop`.`orders` (`order_id`)
                                                      ON DELETE CASCADE
                                                      ON UPDATE CASCADE,
                                              CONSTRAINT `order_item_item_id_fk`
                                                  FOREIGN KEY (`item_id`)
                                                      REFERENCES `internet_shop`.`items` (`item_id`)
                                                      ON DELETE CASCADE
                                                      ON UPDATE CASCADE);