--
-- DATABASE `eCommerceInception`
--

USE `eCommerceInception`;
--
-- Table structure for table `Customers`
--

DROP TABLE IF EXISTS `Customers`;
CREATE TABLE `Customers` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Age` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id_UNIQUE` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;

--
-- Table structure for table `Products`
--

DROP TABLE IF EXISTS `Products`;
CREATE TABLE `Products` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Sku` varchar(100) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Price` double NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id_UNIQUE` (`Id`),
  UNIQUE KEY `Sku_UNIQUE` (`Sku`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `Addresses`;
CREATE TABLE `Addresses` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `AddressLine` varchar(100) NOT NULL,
  `CustomerId` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `CustomerId_FK_idx` (`CustomerId`),
  CONSTRAINT `FK_CustomerId` FOREIGN KEY (`CustomerId`) REFERENCES `Customers` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;

--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
CREATE TABLE `Orders` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `OrderId` varchar(100) NOT NULL,
  `CustomerId` int(11) NOT NULL,
  `Date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `IsEditable` tinyint(1) NOT NULL DEFAULT '1',
  `BillingAddressId` int(11) NOT NULL,
  `ShippingAddressId` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id_UNIQUE` (`Id`),
  UNIQUE KEY `OrderId_UNIQUE` (`OrderId`),
  KEY `CustomerId_FK_idx` (`CustomerId`),
  KEY `BillingAddressId_FK_idx` (`BillingAddressId`),
  KEY `ShippingAddressId_FK_idx` (`ShippingAddressId`),
  CONSTRAINT `CustomerId_FK` FOREIGN KEY (`CustomerId`) REFERENCES `Customers` (`Id`) ON UPDATE CASCADE,
  CONSTRAINT `BillingAddressId_FK` FOREIGN KEY (`BillingAddressId`) REFERENCES `Addresses` (`Id`) ON UPDATE CASCADE,
  CONSTRAINT `ShippingAddressId_FK` FOREIGN KEY (`ShippingAddressId`) REFERENCES `Addresses` (`Id`) ON UPDATE CASCADE

) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;

--
-- Table structure for table `Items`
--

DROP TABLE IF EXISTS `Items`;
CREATE TABLE `Items` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `OrderId` int(11) NOT NULL,
  `ProductId` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Price` double NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `ProductId_FK_idx` (`ProductId`),
  KEY `OrderId_FK_idx` (`OrderId`),
  CONSTRAINT `OrderId_FK` FOREIGN KEY (`OrderId`) REFERENCES `Orders` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `ProductId_FK` FOREIGN KEY (`ProductId`) REFERENCES `Products` (`Id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;




INSERT INTO `Customers`
VALUES  (null,'Alfreds Futterkiste',42),
        (null,'Antonio Moreno',42),
        (null,'Christina Berglund', 42),
        (null,'Patricio Simpson', 29),
        (null,'Carine Schmitt',19);

INSERT INTO `Products`
VALUES  (null,'jeans40','jeans',40),
        (null,'hat25','hat',25),
        (null,'coat25','coat',80),
        (null,'shoes80','shoes',80),
        (null,'dress60','dress',60),
        (null,'gloves100','gloves',100),
        (null,'miniskirt65','miniskirt',65),
        (null,'handbag50','handbag',50),
        (null,'jacket90','jacket',90),
        (null,'gilet85','gilet',85);

INSERT INTO `Addresses`
VALUES (null, 'Addres line, Chisinau, Moldova', 1),
       (null, 'Chisinau', 2),
       (null, 'Chisinau', 3),
       (null, 'Chisinau', 4),
       (null, 'Str sudentilor, Chisinau, Moldova, 2002', 5),
       (null, 'Addres l123sd, Cahul, Moldova', 1);
INSERT INTO `Orders`(`OrderId`,`CustomerId`,`BillingAddressId`,`ShippingAddressId`)
VALUES  ('AlfredsFutterkisteHat25',1,1,1),
        ('AntonioMorenoShoes80',2,2,2),
        ('CarineSchmittJacket90',5,5,5),
        ('PatricioSimpsonJacket90',4,4,4);

INSERT INTO `Orders`(`OrderId`,`CustomerId`,`isEditable`,`BillingAddressId`,`ShippingAddressId`)
VALUES  ('AlfredAsahgqteJeans25',1,0,1,1);

INSERT INTO `Items`(`OrderId`,`ProductId`,`Quantity`,`Price`)
VALUES  (5,3,4, (select `price` from `Products` where `id` = 3)),
        (2,5,3, (select `price` from `Products` where `id` = 5)),
        (5,1,2, (select `price` from `Products` where `id` = 1));


