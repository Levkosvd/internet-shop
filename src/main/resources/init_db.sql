DROP DATABASE IF EXISTS internet_shop;
CREATE DATABASE internet_shop;
USE internet_shop;
CREATE TABLE `bucket_item` (
                               `bucket_item_id` bigint NOT NULL AUTO_INCREMENT,
                               `bucket_id` bigint NOT NULL,
                               `item_id` bigint NOT NULL,
                               PRIMARY KEY (`bucket_item_id`),
                               KEY `bucket_id_fk_idx` (`bucket_id`),
                               KEY `item_id_fk_idx` (`item_id`),
                               CONSTRAINT `bucket_id_fk` FOREIGN KEY (`bucket_id`) REFERENCES `buckets` (`bucket_id`) ON DELETE CASCADE ON UPDATE CASCADE,
                               CONSTRAINT `item_id_fk` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `buckets` (
                           `bucket_id` bigint NOT NULL AUTO_INCREMENT,
                           `user_id` bigint NOT NULL,
                           PRIMARY KEY (`bucket_id`),
                           KEY `bucket_user_id_fk_idx` (`user_id`),
                           CONSTRAINT `bucket_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `items` (
                         `item_id` bigint NOT NULL AUTO_INCREMENT,
                         `name` varchar(255) NOT NULL,
                         `price` decimal(9,4) NOT NULL,
                         PRIMARY KEY (`item_id`)
);

CREATE TABLE `order_item` (
                              `order_item_id` bigint NOT NULL AUTO_INCREMENT,
                              `order_id` bigint NOT NULL,
                              `item_id` bigint NOT NULL,
                              PRIMARY KEY (`order_item_id`),
                              KEY `order_id_fk_idx` (`order_id`),
                              KEY `item_id_fk_idx` (`item_id`),
                              CONSTRAINT `order_item_item_id_fk` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE,
                              CONSTRAINT `order_Item_order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `orders` (
                          `order_id` bigint NOT NULL AUTO_INCREMENT,
                          `user_id` bigint NOT NULL,
                          PRIMARY KEY (`order_id`),
                          KEY `order_user_id_fk_idx` (`user_id`),
                          CONSTRAINT `order_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ;

CREATE TABLE `role_user` (
                             `role_user_id` bigint NOT NULL AUTO_INCREMENT,
                             `role_id` bigint NOT NULL,
                             `user_id` bigint NOT NULL,
                             PRIMARY KEY (`role_user_id`),
                             KEY `user_role_user_id_fk_idx` (`user_id`),
                             KEY `role_user_role_id_fk_idx` (`role_id`),
                             CONSTRAINT `role_user_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
                             CONSTRAINT `role_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `roles` (
                         `role_id` bigint NOT NULL AUTO_INCREMENT,
                         `role_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                         PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=ujis;

CREATE TABLE `users` (
                         `user_id` bigint NOT NULL AUTO_INCREMENT,
                         `first_name` varchar(45) DEFAULT NULL,
                         `surname` varchar(45) DEFAULT NULL,
                         `login` varchar(45) NOT NULL,
                         `password` varchar(512) NOT NULL,
                         `account_balance` float NOT NULL,
                         `token` varchar(50) NOT NULL,
                         `salt` varbinary(64) NOT NULL,
                         PRIMARY KEY (`user_id`)
);
INSERT INTO roles(role_name) VALUES ('ADMIN'),('USER');