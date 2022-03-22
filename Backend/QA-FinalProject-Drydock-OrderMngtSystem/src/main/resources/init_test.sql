DROP TABLE IF EXISTS `ORDER_ENTITY`;

CREATE TABLE ORDER_ENTITY(
id long AUTO_INCREMENT,
customer VARCHAR(80) NOT NULL,
vehicle_type VARCHAR(80) NOT NULL,
displacement int NOT NULL,
military boolean NOT NULL,
weaponised boolean NOT NULL,
resources_required int NOT NULL,
cost float NOT NULL,
date date,
PRIMARY KEY (id)
);

INSERT INTO `ORDER_ENTITY` (`customer`, `vehicle_type`, `displacement`, `military`, `weaponised`, `resources_required`, `cost`, `date`) VALUES ('TEST_CUSTOMER1', 'TEST_VEHICLE1', 100, true, true, 50, 0, null);
INSERT INTO `ORDER_ENTITY` (`customer`, `vehicle_type`, `displacement`, `military`, `weaponised`, `resources_required`, `cost`, `date`) VALUES ('TEST_CUSTOMER2', 'TEST_VEHICLE2', 100, true, true, 50, 0, null);
