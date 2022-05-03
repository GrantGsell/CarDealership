/**
 *
 * Notes:
 * 		The naming scheme for foreign keys is: fk_childTable_parentTable
 */

-- Delete then create a clean database
DROP DATABASE IF EXISTS cardealershiptest;
CREATE DATABASE IF NOT EXISTS cardealershiptest;

USE cardealershiptest;

/*
 * Table Name: userRole
 *
 * Notes:
 *		User Role can include customer, sales, admin. Fields are set appropriately
 */
 CREATE TABLE userRole(
	userRoleId smallInt auto_increment PRIMARY KEY,
    name varchar(8) not null
 );


/*
 * Table Name: users
 *
 * Notes:
 *		userRoleId data type corrolates to userRole table data type.
 */
 CREATE TABLE user(
	userId int auto_increment PRIMARY KEY,
    firstName varchar(32),
    lastName varchar(32) not null,
    email varchar(64) not null,
    userPassword varchar(32) not null,
    userRoleId smallInt not null,
    CONSTRAINT fk_users_userRole
		FOREIGN KEY (userRoleId)
        REFERENCES userRole(userRoleId)
 );
 

/*
 * Table Name: make
 *
 * Notes:
 *		nameMake has a max length of 35, which is base on the longest manufacturer (make)
 *      found on https://en.wikipedia.org/wiki/List_of_automobile_manufacturers.
 */
CREATE TABLE make(
	makeId int auto_increment PRIMARY KEY,
    nameMake varchar(18) not null,
    dateAdded date not null,
    userId int,
    CONSTRAINT fk_make_user
		FOREIGN KEY (userId)
        REFERENCES user(userId)    
);


/*
 * Table Name: model
 *
 * Notes: 
 *		nameModel has a max length of 35, which is based on the longest model foound on
 *			https://www.kbb.com/car-make-model-list/used/ and
 *          https://www.kbb.com/car-make-model-list/new/view-all/
 */
 CREATE TABLE model(
	modelId int auto_increment PRIMARY KEY,
    nameModel varchar(35) not null,
    makeId int,
    CONSTRAINT fk_model_make
		FOREIGN KEY (makeId)
        REFERENCES make(makeId)
 );

 
/*
 * Table Name: type
 *
 * Notes:
 * 		Car types include: New, Used. Fields are set accordingly.
 */
 CREATE TABLE type(
	typeId tinyInt auto_increment PRIMARY KEY,
    nameType varchar(4) not null
 );
 
 
/*
 * Table Name: bodyStyle
 *
 * Notes:
 *		Body Styles include: Car, SUV, Truck, Van. Fields are set accordingly.
 */
 CREATE TABLE bodyStyle(
	styleId smallInt auto_increment PRIMARY KEY,
    nameStyle varchar(5) not null
    -- bodyDescription mediumtext
 );

  
/*
 * Table Name: transmission
 *
 * Notes:
 *		Transmissions include: Automatic, manual. Fields are set accordingly
 */
 CREATE TABLE transmission(
	transmissionId tinyint auto_increment PRIMARY KEY,
    transmissionName varchar(9) not null
 );
 

/*
 * Table Name: Color
 *
 * Notes:
 * 		Along with standard colors we have included car enthusiast colors from
 *		https://www.thecoatingstore.com/car-paint-colors/. Colorid and colorName
 *		are set accordingly
 */
 CREATE TABLE color(
	colorId smallInt auto_increment PRIMARY KEY,
    nameColor varchar(20) not null
 );


/*
 * Table Name: interiorColor
 *
 * Notes:
 * 		Along with standard colors we have included car enthusiast colors from
 *		https://www.thecoatingstore.com/car-paint-colors/. Colorid and colorName
 *		are set accordingly
 */
 CREATE TABLE interiorColor(
	interiorColorId smallInt auto_increment PRIMARY KEY,
    nameInteriorColor varchar(20) not null
 );


/*
 * Table Name: status
 *
 * Notes:
 *		Status includes: Sold, Available
 */
 CREATE TABLE status(
	statusId smallInt auto_increment PRIMARY KEY,
    nameStatus varchar(9) not null
 );

  
/*
 * Table Name: vehicle
 *
 * Notes:
 *		Vin numbers have a max length of 17 characters.
 *		Odometers (used for mileage) max out at 999,999, which is int the range of by 
 *			medium int which has a max of 8,388,607.
 *		Price is set to int which contains the most expensive car currently whihc is the
 *			Bugatti La Voiture Noire at 18.7 million dollars. MSRP is set accordingly.
 */
  CREATE TABLE vehicle(
	vin varchar(17) PRIMARY KEY,
    mileage mediumInt not null,
    salePrice decimal(10,2) not null,
    msrp decimal(10,2) not null,
    carYear smallInt not null,
    carDescription mediumText not null,
    pictureUrl varchar(256),
    modelId int not null,
    styleId smallInt not null,
    transmissionId tinyint not null,
    colorId smallint not null,
    typeId tinyint not null,
    statusId smallint not null,
    userId int not null,
    interiorColorId smallint not null,
    
    CONSTRAINT fk_vehicle_model FOREIGN KEY (modelId) REFERENCES make(makeId),
	CONSTRAINT fk_vehicle_bodyStyle FOREIGN KEY (styleId) REFERENCES bodyStyle(styleId),
	CONSTRAINT fk_vehicle_transmission FOREIGN KEY (transmissionId) REFERENCES transmission(transmissionId),
    CONSTRAINT fk_vehicle_color FOREIGN KEY (colorId) REFERENCES color(colorId),
    CONSTRAINT fk_vehicle_interior FOREIGN KEY (interiorColorId) REFERENCES interiorColor(interiorColorId),
	CONSTRAINT fk_vehicle_type FOREIGN KEY (typeId) REFERENCES type(typeId),
	CONSTRAINT fk_vehicle_status FOREIGN KEY (statusId) REFERENCES status(statusId),
    CONSTRAINT fk_vehicle_user FOREIGN KEY (userId) REFERENCES user(userId)    
 );
 
 
  /*
 * Table Name: purchaseType
 *
 * Notes: 
 *		Bank Finance, Cash, Dealer Finance
 */
 CREATE TABLE purchaseType(
	purchaseTypeId tinyint auto_increment PRIMARY KEY,
    purchaseName varchar(15) not null
 );
 

 /*
 * Table Name: special
 *
 * Notes: 
 *
 */
 CREATE TABLE special(
	specialId int auto_increment PRIMARY KEY,
    title varchar(50) not null,
    specialDescription mediumtext not null,
    userId int not null,
    CONSTRAINT fk_special_user
		FOREIGN KEY (userId)
        REFERENCES user(userId)
 );

 
/*
 * Table Name:
 *
 * Notes: 
 *		zipcode has a max length of 10 becuase some zipcodes include a dash and an extra
 *			four digits. Ex: 12345-6789
 *
 */
 CREATE TABLE UsState (
   stateId tinyint auto_increment PRIMARY KEY,
   stateName varchar(32) not null,
   stateAbbrev char(2) not null
);

 
 /*
 * Table Name:
 *
 * Notes: 
 *		zipcode has a max length of 10 becuase some zipcodes include a dash and an extra
 *			four digits. Ex: 12345-6789
 *
 */
 CREATE TABLE salesInfo(
	salesId int auto_increment PRIMARY KEY,
    nameSales varchar(32) not null,
    phone varchar(20) not null,
    email varchar(64) not null,
    street1 varchar(64) not null,
    street2 varchar(64),
    city  varchar(32) not null,
    stateId tinyint not null,
    zipcode varchar(10) not null,
    purchasePrice decimal(10,2) not null,
    vin varchar(17) not null,
    purchaseTypeId tinyint not null,
    userId int not null,
    CONSTRAINT fk_salesInfo_UsState
		FOREIGN KEY (stateId)
        REFERENCES UsState(stateId), 
    CONSTRAINT fk_salesInfo_vehicle
		FOREIGN KEY (vin)
        REFERENCES vehicle(vin),
    CONSTRAINT fk_salesInfo_purchasetype
		FOREIGN KEY (purchaseTypeId)
        REFERENCES purchaseType(purchaseTypeId),   
    CONSTRAINT fk_salesInfo_user
		FOREIGN KEY (userId)
        REFERENCES user(userId)
 );
 
 
/*
 * Table Name:
 *
 * Notes: 
 *
 */
 CREATE TABLE contact(
	contactId int auto_increment PRIMARY KEY,
    contactName varchar(50) not null,
    email varchar(64) not null,
    phone varchar(20),
    message mediumText not null
 );
 
 /*
  * Data input for testing
  */
  
USE cardealershiptest;
 
insert into userRole (name)
	values ('customer'), ('sales'), ('admin')
;

insert into user (lastName, firstName, email, userPassword, userRoleId)
	values ('Magpantay', 'Nicole', 'nicole@test.com', '1', 3),
    ('Gsell', 'Grant', 'grant@test.com', '1', 2),
    ('Lee', 'Jeonghoon', 'jh@test.com', '1', 1)
;

 
insert into make (nameMake, dateAdded, userId)
	values ('Ford', '2021-12-09', 1),
    ('Honda', '2021-12-21', 1),
    ('BMW', '2022-01-31', 1),
    ('Kia', '2022-02-21', 1)
;
 
insert into model (nameModel, makeId)
	values ('Focus', 1)
;
insert into model (nameModel, makeId)
	values ('530ia', 3 )
;

insert into type (nameType)
	values ('New'), ('Used');
 
insert into bodyStyle (nameStyle)
	values ('Car'), ('SUV'), ('Truck'), ('Van');
    
insert into transmission (transmissionName)
	values ('Automatic'), ('Manual'); 
  
 insert into color (nameColor)
	values ('White'), ('Black'), ('Silver'), ('Gold'), ('Red');
    
 insert into interiorColor (nameInteriorColor)
	values ('Black'), ('Orange'), ('Blue'), ('Green'), ('Red');
 
 insert into status (nameStatus)
	values ('Sold'), ('Available');

 insert into vehicle (vin, mileage, salePrice, msrp, carYear, carDescription, modelId, styleId, transmissionId, colorId, typeId, statusId, userId, interiorColorId)
	values ('1234567890ABCDEFG', 100000, 10000.00, 11000.00, 2016, 'Good condition', 1, 1, 1, 1, 1, 1, 1, 5),
    ('2234567890ABCDEFG', 50000, 18900.00, 20000.00, 2018, 'Good performance', 1, 2, 1, 4, 1, 2, 1, 5),
    ('3234567890ABCDEFG', 10000, 25555.00, 27000.00, 2021, 'Almost new', 1, 2, 1, 3, 2, 2, 1, 5);

insert into purchaseType (purchaseName)
	values ('Bank Finance'), ('Cash'), ('Dealer Finance');
  
insert into special (title, specialDescription, userId)
	values ('Big Black Friday Sale', '15% OFF!!!!', 2);

INSERT INTO UsState (stateName, stateAbbrev)
VALUES ('Alabama', 'AL'),
       ('Alaska', 'AK'),
       ('Arizona', 'AZ'),
       ('Arkansas', 'AR'),
       ('California', 'CA'),
       ('Colorado', 'CO'),
       ('Connecticut', 'CT'),
       ('Delaware', 'DE'),
       ('District of Columbia', 'DC'),
       ('Florida', 'FL'),
       ('Georgia', 'GA'),
       ('Hawaii', 'HI'),
       ('Idaho', 'ID'),
       ('Illinois', 'IL'),
       ('Indiana', 'IN'),
       ('Iowa', 'IA'),
       ('Kansas', 'KS'),
       ('Kentucky', 'KY'),
       ('Louisiana', 'LA'),
       ('Maine', 'ME'),
       ('Maryland', 'MD'),
       ('Massachusetts', 'MA'),
       ('Michigan', 'MI'),
       ('Minnesota', 'MN'),
       ('Mississippi', 'MS'),
       ('Missouri', 'MO'),
       ('Montana', 'MT'),
       ('Nebraska', 'NE'),
       ('Nevada', 'NV'),
       ('New Hampshire', 'NH'),
       ('New Jersey', 'NJ'),
       ('New Mexico', 'NM'),
       ('New York', 'NY'),
       ('North Carolina', 'NC'),
       ('North Dakota', 'ND'),
       ('Ohio', 'OH'),
       ('Oklahoma', 'OK'),
       ('Oregon', 'OR'),
       ('Pennsylvania', 'PA'),
       ('Rhode Island', 'RI'),
       ('South Carolina', 'SC'),
       ('South Dakota', 'SD'),
       ('Tennessee', 'TN'),
       ('Texas', 'TX'),
       ('Utah', 'UT'),
       ('Vermont', 'VT'),
       ('Virginia', 'VA'),
       ('Washington', 'WA'),
       ('West Virginia', 'WV'),
       ('Wisconsin', 'WI'),
       ('Wyoming', 'WY')
;

insert into salesInfo (nameSales, phone, email, street1, city, stateId, zipcode, purchasePrice, vin, purchaseTypeId, userId)
	values ('Marine Jackson', '123-456-7890', 'marine@test.com', '123 1st ave.', 'New York', 36, 12345, 9800.0, '1234567890ABCDEFG', 1, 2);
    
insert into contact (contactName, phone, email, message)
	values ('Marine Jackson', '123-456-7890', 'marine@test.com', 'Please call me!');  
