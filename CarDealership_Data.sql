/**
 *
 * Notes:
 * 		Testing data for Car dealership
 */

USE cardealership;
 
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

insert into type (nameType)
	values ('New'), ('Used');
 
insert into bodyStyle (nameStyle)
	values ('Car'), ('SUV'), ('Truck'), ('Van');
    
insert into transmission (transmissionName)
	values ('Automatic'), ('Manual'); 
  
 insert into color (nameColor)
	values ('White'), ('Black'), ('Silver'), ('Gold'), ('Red');
 
 insert into status (nameStatus)
	values ('Sold'), ('Available');

 insert into vehicle (vin, mileage, salePrice, msrp, carYear, carDescription, modelId, styleId, transmissionId, colorId, typeId, statusId, userId)
	values ('1234567890ABCDEFG', 100000, 10000.00, 11000.00, 2016, 'Good condition', 1, 1, 1, 1, 1, 1, 1),
    ('2234567890ABCDEFG', 50000, 18900.00, 20000.00, 2018, 'Good performance', 1, 2, 1, 4, 1, 2, 1),
    ('3234567890ABCDEFG', 10000, 25555.00, 27000.00, 2021, 'Almost new', 1, 2, 1, 3, 2, 2, 1);

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
	values ('Marine Jackson', '123-456-7890', 'marine@test.com', '123 1st ave.', 'New York', 33, 12345, 9800.0, '1234567890ABCDEFG', 1, 2);
    
insert into contact (contactName, phone, email, message)
	values ('Marine Jackson', '123-456-7890', 'marine@test.com', 'Please call me!');  
    
select * from salesInfo;
select * from UsState;

select * from salesInfo s
	join UsState u on s.stateId = u.stateId
    where s.stateId = u.stateId
;