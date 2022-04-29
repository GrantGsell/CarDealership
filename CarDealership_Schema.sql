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
 * Table Name: make
 *
 * Notes:
 *		nameMake has a max length of 35, which is base on the longest manufacturer (make)
 *      found on https://en.wikipedia.org/wiki/List_of_automobile_manufacturers.
 */
CREATE TABLE make(
	makeId int PRIMARY KEY,
    nameMake varchar(18),
    dateAdded date,
    userId int
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
	modelId int PRIMARY KEY,
    nameModel varchar(35),
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
	typeId tinyInt PRIMARY KEY,
    nameType varchar(4)
 );
 
 
/*
 * Table Name: bodyStyle
 *
 * Notes:
 *		Body Styles include: Car, SUV, Truck, Van. Fields are set accordingly.
 */
 CREATE TABLE bodyStyle(
	styleId smallInt PRIMARY KEY,
    nameStyle varchar(5),
    bodyDescription mediumtext
 );
 
 
/*
 * Table Name: transmission
 *
 * Notes:
 *		Transmissions include: Automatic, manual. Fields are set accordingly
 */
 CREATE TABLE transmission(
	transmissionId tinyint PRIMARY KEY,
    transmissionName varchar(9)
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
	colorId smallInt PRIMARY KEY,
    nameColor varchar(20)
 );
 

/*
 * Table Name: status
 *
 * Notes:
 *		Status includes: Sold, Available
 */
 CREATE TABLE status(
	statusId smallInt PRIMARY KEY,
    nameStatus varchar(9)
 );
 
 
/*
 * Table Name: userRole
 *
 * Notes:
 *		User Role can include customer, sales, admin. Fields are set appropriately
 */
 CREATE TABLE userRole(
	userRoleId smallInt PRIMARY KEY,
    userRole varchar(8)
 );


/*
 * Table Name: users
 *
 * Notes:
 *		userRoleId data type corrolates to userRole table data type.
 */
 CREATE TABLE users(
	userId int PRIMARY KEY,
    firstName varchar(32),
    lastName varchar(32),
    email varchar(64),
    userPassword varchar(32),
    userRoleId smallInt,
    CONSTRAINT fk_users_userRole
		FOREIGN KEY (userRoleId)
        REFERENCES userRole(userRoleId)
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
    mileage mediumInt,
    salePrice int,
    msrp int,
    carYear smallInt,
    carDescription mediumText,
    pictureUrl text,
    makeId int,
    modelId int,
    styleId int,
    transmissionId tinyint,
    colorId smallint,
    typeId tinyint,
    statusId smallint,
    userId int
 );
 
 
 /*
 * Table Name: purchaseType
 *
 * Notes: 
 *
 */
 CREATE TABLE purchaseType(
	purchaseTypeId int PRIMARY KEY,
    purchaseName varchar(15)
 );
 
 
 /*
 * Table Name: special
 *
 * Notes: 
 *
 */
 CREATE TABLE special(
	specialId int PRIMARY KEY,
    title varchar(50),
    specialDescription mediumtext,
    userId int,
    CONSTRAINT fk_special_users
		FOREIGN KEY (userId)
        REFERENCES users(userId)
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
	salesId int PRIMARY KEY,
    nameSales varchar(32),
    email varchar(32),
    street1 varchar(32),
    street2 varchar(32),
    city  varchar(32),
    state varchar(20),
    zipcode varchar(10),
    purchasePrice int,
    vin varchar(17),
    purchaseTypeId int,
    userId int,
    CONSTRAINT fk_salesInfo_vehicle
		FOREIGN KEY (vin)
        REFERENCES vehicle(vin),
    CONSTRAINT fk_salesInfo_purchasetype
		FOREIGN KEY (purchaseTypeId)
        REFERENCES purchasetype(purchaseTypeId),   
    CONSTRAINT fk_salesInfo_users
		FOREIGN KEY (userId)
        REFERENCES users(userId)
 );
 
 
 
/*
 * Table Name:
 *
 * Notes: 
 *
 */
 CREATE TABLE contact(
	contactId int,
    contactName varchar(50),
    email varchar(32),
    phone varchar(12),
    message mediumText
 );