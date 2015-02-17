DELETE FROM "client_companies";
DELETE FROM "client_people";
DELETE FROM "refueling";
DELETE FROM "prizes";
DELETE FROM "clients";
DELETE FROM "employees";
DELETE FROM "blocker_attempts";
DELETE FROM "acl_role_parents";
DELETE FROM "acl_roles";
DELETE FROM "acl_resgroups";
DELETE FROM "cities";
DELETE FROM "regions";
DELETE FROM "countries";
DELETE FROM "car_wash";
DELETE FROM "car_wash_types";
DELETE FROM "refueling_types";
DELETE FROM "petrol_providers";
DELETE FROM "petrol_container_measurements";
DELETE FROM "petrol_containers";
DELETE FROM "prize_categories";
DELETE FROM "bills";
DELETE FROM "bill_elements";


INSERT INTO "countries" ("COUk_1_Id", "COU_Name", "COU_Iso2Name", "COU_Iso3Name") VALUES (1,'Poland','10','100');
INSERT INTO "regions" ("REGk_1_Id", "REG_Name", "REG_FIPScode" , "COU_1_Id") VALUES (1,'Malopolska','30','1');
INSERT INTO "cities" ("CITk_1_Id", "CIT_Name", "REG_1_Id", "COU_2_Id") VALUES (1,'Krakow', '1', '1');

INSERT INTO "acl_resgroups" ("RESGk_1_Id", "RESG_Name") VALUES
(1,'guest_pages'),
(2,'client_panel'),
(3,'employee_panel');

INSERT INTO "acl_roles" ("ROLk_1_Id", "ROL_Name") VALUES
(1,'GUE'),
(2,'USR'),
(3,'EMP'),
(4,'SEMP'),
(5,'OWN'),
(6,'GOD');

INSERT INTO "acl_role_parents" ("ROL_1_Id", "ROL_2_Id_parent") VALUES
(2, 1),
(3, 1),
(4, 3),
(5, 4),
(6, 5);

INSERT INTO "blocker_attempts"
("BLOAk_1_Id", "BLOA_Ip", "BLOA_Attempts", "BLOA_DateExpired", "BLOA_Status", "RESG_1_Id") VALUES
(1,'127.0.0.1', 3, '2013-01-03 12:30:00', 'EXPIRED', 2),
(2,'192.168.123.100', 1, '2013-03-01 12:30:50', 'ACTIVE', 3),
(3,'192.168.123.101', 1, '2013-02-01 12:30:50', 'ACTIVE', 3);

ALTER SEQUENCE blocker_attempts_bloak_1_id_seq RESTART WITH 4;

INSERT INTO "employees"
("EMPk_1_Id", "EMP_Name","EMP_Surname","EMP_PESEL","EMP_NIP","EMP_Gender","EMP_Email","EMP_PhoneNumber","EMP_Password","EMP_Status","EMP_Address","CIT_1_Id","ROL_2_Id") VALUES 
(1,'Zbigniew','NowakA','49040501580','1060000062','MALE','test@test.pl',NULL,'$2a$12$WPReseVZeosMqZ6psPQwOeOc62pFgg7M4SwfWb9K3087HRAJ1lOsy','ACTIVE',NULL,NULL,6),
(2,'Zbigniew','NowakB','88041002652','1234567819',NULL,'test2@test.pl',NULL,'$2a$12$WPReseVZeosMqZ6psPQwOeOc62pFgg7M4SwfWb9K3087HRAJ1lOsy','ACTIVE',NULL,NULL,5),
(3,'Zbigniew','NowakC','85081813266','1111111111',NULL,'test3@test.pl',NULL,'$2a$12$WPReseVZeosMqZ6psPQwOeOc62pFgg7M4SwfWb9K3087HRAJ1lOsy','BANNED',NULL,NULL,3),
(4,'Zbigniew','NowakD','88091412504','2222222222',NULL,'test4@test.pl',NULL,'$2a$12$WPReseVZeosMqZ6psPQwOeOc62pFgg7M4SwfWb9K3087HRAJ1lOsy','INACTIVE',NULL,NULL,3),
(5,'Zbigniew','NowakE','88100413036','3333333333',NULL,'test5@test.pl',NULL,'$2a$12$WPReseVZeosMqZ6psPQwOeOc62pFgg7M4SwfWb9K3087HRAJ1lOsy','ACTIVE',NULL,NULL,4);

ALTER SEQUENCE employees_empk_1_id_seq RESTART WITH 6;

INSERT INTO "clients"
("CLIk_1_Id", "CLI_NIP","CLI_Password","CLI_Email","CLI_PhoneNumber","CLI_Status","CLI_Points","CLI_DateAdded","CIT_1_Id","ROL_2_Id") VALUES
(1,NULL,'$2a$12$WPReseVZeosMqZ6psPQwOeOc62pFgg7M4SwfWb9K3087HRAJ1lOsy','test20@test.pl','545333111','ACTIVE',0,'2013-01-03 12:30:00',1,2),
(2,'7370003477','$2a$12$WPReseVZeosMqZ6psPQwOeOc62pFgg7M4SwfWb9K3087HRAJ1lOsy','test21@test.pl','676888999','ACTIVE',0,'2013-01-03 12:30:00',1,2);

ALTER SEQUENCE clients_clik_1_id_seq RESTART WITH 3;

INSERT INTO "client_companies"
("CLICk_1_Id","CLIC_CompanyName","CLIC_REGON","CLI_1_Id") VALUES
(1,'TYMBARK GMW SP. Z O.O.','490540969',2);

ALTER SEQUENCE client_companies_click_1_id_seq RESTART WITH 2;

INSERT INTO "client_people"
("CLIPk_1_Id","CLIP_Name","CLIP_Surname","CLIP_PESEL","CLIP_Gender","CLI_1_Id") VALUES
(1,'Stanisław','Kowalski','90061113257','MALE',1);

ALTER SEQUENCE client_people_clipk_1_id_seq RESTART WITH 2;

INSERT INTO "car_wash_types"
("CAWTk_1_Id", "CAWT_Name", "CAWT_Cost", "CAWT_LoyalityPoints") VALUES
(1,'Automatyczna',29.99,1),
(2,'Ręczna samoobsługowa',19.99,1),
(3,'Ręczna niesamoobsługowa',25.00,1);

INSERT INTO "car_wash"
("CARWk_1_Id", "CARW_DateBeginWash", "CARW_DateEndWash", "CAWT_1_Id") VALUES
(1,'2013-05-19','2013-05-19',1),
(2,'2013-05-19','2013-05-19',3);

ALTER SEQUENCE car_wash_carwk_1_id_seq RESTART WITH 3;

ALTER SEQUENCE car_wash_types_cawtk_1_id_seq RESTART WITH 4;

INSERT INTO "refueling_types"
("REFTk_1_Id", "REFT_Name", "REFT_Cost", "REFT_LoyalityPoints") VALUES
(1,'E95',5.64,1),
(2,'E98',5.84,1),
(3,'ON',5.56,1),
(4,'LPG',2.54,1);

ALTER SEQUENCE refueling_types_reftk_1_id_seq RESTART WITH 5;

INSERT INTO "petrol_providers" 
("PETPk_1_Id", "PETP_CompanyName", "PETP_NIP", "PETP_REGON", "PETP_Address", "PETP_Status", "PETP_PhoneNumber", "PETP_Email") VALUES 
(1, 'DastwacaA', '8578880390', '27167764686033', 'ul. A 1/1', 'ACTIVE', '123456789', 'provider1@test.pl');

ALTER SEQUENCE petrol_providers_petpk_1_id_seq RESTART WITH 2;

INSERT INTO "petrol_containers" ("PETCk_1_Id", "PETC_Type") VALUES
(1, 'E95'),
(2, 'E98'),
(3, 'ON'),
(4, 'LPG');

ALTER SEQUENCE petrol_containers_petck_1_id_seq RESTART WITH 7;

INSERT INTO "refueling" ("REFk_1_Id", "REF_Cost", "REF_Fuel", "REF_RefuelingDate", "PETC_1_Id", "CLI_2_Id", "REFT_3_Id") VALUES
(1, '3.50', '3.50', '2013-01-01', 1, 1, 1);

ALTER SEQUENCE refueling_refk_1_id_seq RESTART WITH 2;

INSERT INTO "petrol_container_measurements" ("PECMk_1_Id", "PECM_MeasurementDate", "PECM_Type", "PECM_Value", "PETC_1_Id") VALUES
(1, '2013-05-19', 'PETROL_LEVEL', '3.50', 1),
(2, '2013-05-19', 'PETROL_LEVEL', '10.50', 2),
(3, '2013-05-19', 'PETROL_LEVEL', '15.00', 3),
(4, '2013-05-19', 'PETROL_LEVEL', '5.00', 4),
(5, '2013-05-20', 'PETROL_LEVEL', '17.50', 1),
(6, '2013-05-20', 'PETROL_LEVEL', '20.00', 2),
(7, '2013-05-20', 'PETROL_LEVEL', '25.00', 3),
(8, '2013-05-20', 'PETROL_LEVEL', '10.00', 4),
(9, '2013-05-21', 'PETROL_LEVEL', '4.50', 1),
(10, '2013-05-21', 'PETROL_LEVEL', '12.50', 2),
(11, '2013-05-21', 'PETROL_LEVEL', '24.50', 3),
(12, '2013-05-21', 'PETROL_LEVEL', '5.50', 4);

ALTER SEQUENCE petrol_container_measurements_pecmk_1_id_seq RESTART WITH 19;

INSERT INTO "prize_categories" ("PRCTk_1_Id", "PRCT_Name", "PRCT_MinPoints", "PRCT_Status") VALUES
(1, 'Benzyna ON', 100, 'ACTIVE'),
(2, 'LPG', 50, 'ACTIVE'),
(3, 'Mycie standardowe', 300, 'ACTIVE'),
(4, 'mycie z woskowaniem', 400, 'ACTIVE'),
(5, 'Benzyna E95', 200, 'INACTIVE');

ALTER SEQUENCE prize_categories_prctk_1_id_seq RESTART WITH 6;

INSERT INTO "bills" ("BILk_1_Id", "BIL_InvoiceDate", "BIL_Amount", "BIL_VAT", "BIL_Total", "CLI_1_Id", "EMP_2_Id") VALUES
(1, '2013-05-20', '5.00', '23.00', '9001.00', 1, 2),
(2, '2013-05-20', '7.00', '23.00', '50.00', 1, 3),
(3, '2013-05-19', '1.00', '23.00', '70.00', 2, 4);

ALTER SEQUENCE bills_bilk_1_id_seq RESTART WITH 4;

INSERT INTO "bill_elements" ("BILEk_1_Id", "PRO_1_Id", "REF_2_Id", "CARW_3_Id", "BIL_4_Id") VALUES
(1, null, 1,null,1),
(2, null, null,1,1),
(3, null, null,1,2),
(4, null, 1,null,2),
(5, null, null,1,3),
(6, null, 1,null,3);

ALTER SEQUENCE bill_elements_bilek_1_id_seq RESTART WITH 7;


