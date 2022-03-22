DROP TABLE IF EXISTS `order_entity`;

CREATE TABLE order_entity(
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

INSERT INTO `order_entity` (`customer`, `vehicle_type`, `displacement`, `military`, `weaponised`, `resources_required`, `cost`) VALUES ('TEST_CUSTOMER1', 'TEST_VEHICLE1', 100, true, true, 50, 0);
INSERT INTO `order_entity` (`customer`, `vehicle_type`, `displacement`, `military`, `weaponised`, `resources_required`, `cost`) VALUES ('TEST_CUSTOMER2', 'TEST_VEHICLE2', 100, true, true, 50, 0);
