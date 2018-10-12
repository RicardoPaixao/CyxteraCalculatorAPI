CREATE DATABASE `cyxtera_calculator` /*!40100 DEFAULT CHARACTER SET latin1 */;

CREATE TABLE `sessions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

CREATE TABLE `operators` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `value` float NOT NULL,
  `sessionId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `operatorsCreatedBy_idx` (`sessionId`),
  CONSTRAINT `operatorsCreatedBy` FOREIGN KEY (`sessionId`) REFERENCES `sessions` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

CREATE TABLE `operations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `operation` varchar(25) NOT NULL,
  `sessionId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `opetarionsCreatedBy_idx` (`sessionId`),
  CONSTRAINT `opetarionsCreatedBy` FOREIGN KEY (`sessionId`) REFERENCES `sessions` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sessionId` int(11) NOT NULL,
  `entry` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `historyCreatedBy_idx` (`sessionId`),
  CONSTRAINT `historyCreatedBy` FOREIGN KEY (`sessionId`) REFERENCES `sessions` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;