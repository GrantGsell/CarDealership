/**
 *
 * Notes:
 * 		The naming scheme for foreign keys is: fk_childTable_parentTable
 */

-- Delete then create a clean database
DROP DATABASE IF EXISTS cardealership;
CREATE DATABASE IF NOT EXISTS cardealership;

USE cardealership;

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
    interiorColorId int not null,
    
    CONSTRAINT fk_vehicle_model FOREIGN KEY (modelId) REFERENCES make(makeId),
	CONSTRAINT fk_vehicle_bodyStyle FOREIGN KEY (styleId) REFERENCES bodyStyle(styleId),
	CONSTRAINT fk_vehicle_transmission FOREIGN KEY (transmissionId) REFERENCES transmission(transmissionId),
    CONSTRAINT fk_vehicle_color FOREIGN KEY (colorId) REFERENCES color(colorId),
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
 
 