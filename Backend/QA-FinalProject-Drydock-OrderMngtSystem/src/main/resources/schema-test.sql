DROP TABLE IF EXISTS `ORDER_ENTITY` CASCADE;

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