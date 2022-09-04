CREATE DATABASE  IF NOT EXISTS `simple_home_insurance`;
USE `simple_home_insurance`;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(10) NOT NULL UNIQUE,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `username` varchar(50) NOT NULL UNIQUE,
  `password` char(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL UNIQUE,
  `authority` varchar(50) NOT NULL,
  PRIMARY KEY (`username`),
  FOREIGN KEY (`username`) REFERENCES `users` (`username`)
);

DROP TABLE IF EXISTS `policyholder`;
CREATE TABLE `policyholder` (
  `id` varchar(13) NOT NULL UNIQUE,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone_number` varchar(45) NOT NULL,
  `address` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `insured_property`;
CREATE TABLE `insured_property` (
	`property_number` varchar(13) NOT NULL UNIQUE,
    `property_address` varchar(100) NOT NULL,
    PRIMARY KEY (`property_number`)
);

DROP TABLE IF EXISTS `policy`;
CREATE TABLE `policy` (
  `policy_number` varchar(13) NOT NULL UNIQUE,
  `policyholder_number` varchar(13) NOT NULL,
  `property_number` varchar(13) NOT NULL,
  `inception_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `policy_type` enum('A', 'B', 'C') NOT NULL,
  `in_force` tinyint(1) NOT NULL,
  PRIMARY KEY (`policy_number`),
  FOREIGN KEY (`policyholder_number`) REFERENCES `policyholder` (`id`),
  FOREIGN KEY (`property_number`) REFERENCES `insured_property` (`property_number`)
);

DROP TABLE IF EXISTS `claim`;
CREATE TABLE `claim` (
  `claim_number` varchar(13) NOT NULL UNIQUE,
  `adjuster_number` int(10) DEFAULT NULL,
  `policyholder_number` varchar(13) NOT NULL,
  `policy_number` varchar(13) NOT NULL,
  `loss_type` enum('Damage', 'Fire', 'Theft') NOT NULL,
  `incident_date` date NOT NULL,
  `filing_date` date NOT NULL,
  `status` enum('First Notice', 'In Progress', 'Finalised', 'Declined') DEFAULT 'First Notice',
  `notes` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`claim_number`),
  FOREIGN KEY (`policyholder_number`) REFERENCES `policyholder` (`id`),
  FOREIGN KEY (`policy_number`) REFERENCES `policy` (`policy_number`),
  FOREIGN KEY (`adjuster_number`) REFERENCES `users` (`id`)
);

DROP TABLE IF EXISTS `claims_sequence`;
CREATE TABLE `claims_sequence` (
  `next_val` int NOT NULL
);

DROP TABLE IF EXISTS `claim_payment`;
CREATE TABLE `claim_payment` (
  `id` int(10) NOT NULL AUTO_INCREMENT UNIQUE,
  `claim_number` varchar(13) NOT NULL,
  `payment_amount` numeric(10, 2) NOT NULL,
  `payment_date` date NOT NULL,
  `notes` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`claim_number`) REFERENCES `claim` (`claim_number`)
) AUTO_INCREMENT=1204;

DROP TABLE IF EXISTS `declined_claims`;
CREATE TABLE `declined_claims` (
  `id` int(10) NOT NULL AUTO_INCREMENT UNIQUE,
  `claim_number` varchar(13) NOT NULL UNIQUE,
  `declined_date` date NOT NULL,
  `reason` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`claim_number`) REFERENCES `claim` (`claim_number`)
) AUTO_INCREMENT=11096;
