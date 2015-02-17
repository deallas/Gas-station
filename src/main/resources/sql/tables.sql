CREATE TABLE IF NOT EXISTS "acl_resources" (
  "RESk_1_Id" SERIAL PRIMARY KEY,
  "RES_Name" VARCHAR(50) NOT NULL
);

DROP INDEX IF EXISTS "un_acl_resources_RES_Name";
CREATE UNIQUE INDEX "un_acl_resources_RES_Name" ON "acl_resources" ("RES_Name");

CREATE TABLE IF NOT EXISTS "acl_privileges" (
  "PRIVk_1_Id" SERIAL PRIMARY KEY,
  "PRIV_Name" VARCHAR(30) NOT NULL,
  "RES_1_Id" INTEGER NOT NULL,
  CONSTRAINT "fk_acl_privileges_RES_1_Id" FOREIGN KEY ("RES_1_Id")
	REFERENCES "acl_resources" ("RESk_1_Id")
	ON DELETE CASCADE
	ON UPDATE CASCADE
);

DROP INDEX IF EXISTS "in_acl_privileges_PRIV_Name";
CREATE INDEX "in_acl_privileges_PRIV_Name" ON "acl_privileges" ("PRIV_Name");

CREATE TABLE IF NOT EXISTS "acl_resgroups" (
  "RESGk_1_Id" SERIAL PRIMARY KEY,
  "RESG_Name" VARCHAR(30) NOT NULL
);

DROP INDEX IF EXISTS "un_acl_resgroups_RESG_Name";
CREATE UNIQUE INDEX "un_acl_resgroups_RESG_Name" ON "acl_resgroups" ("RESG_Name");

CREATE TABLE IF NOT EXISTS "blocker_records" (
  "BLORk_1_Id" SERIAL PRIMARY KEY ,
  "BLOR_Ip" VARCHAR(30) NOT NULL,
  "BLOR_DateCreated" TIMESTAMP NOT NULL,
  "BLOR_DateExpired" TIMESTAMP NULL DEFAULT NULL,
  "BLOR_Message" TEXT NULL DEFAULT NULL,
  "BLOR_Priority" VARCHAR(45) NOT NULL,
  "BLOR_Status" SB_BLOCKER_RECORDS_STATUS NOT NULL,
  "RESG_1_Id" INTEGER NOT NULL,
  CONSTRAINT "fk_blocker_list_RESG_1_Id"
    FOREIGN KEY ("RESG_1_Id")
    REFERENCES "acl_resgroups" ("RESGk_1_Id")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

DROP INDEX IF EXISTS "in_blocker_list_BLOR_Status";
DROP INDEX IF EXISTS "in_blocker_list_BLOR_Ip";
CREATE INDEX "in_blocker_list_BLOR_Status" ON "blocker_records" ("BLOR_Status");
CREATE INDEX "in_blocker_list_BLOR_Ip" ON "blocker_records" ("BLOR_Ip");

CREATE TABLE IF NOT EXISTS "blocker_attempts" (
  "BLOAk_1_Id" SERIAL PRIMARY KEY ,
  "BLOA_Ip" VARCHAR(30) NOT NULL,
  "BLOA_Attempts" INTEGER NOT NULL,
  "BLOA_DateExpired" TIMESTAMP NOT NULL,
  "BLOA_Status" SB_BLOCKER_ATTEMPTS_STATUS NOT NULL,
  "RESG_1_Id" INTEGER DEFAULT NULL,
  CONSTRAINT "fk_blocker_attempts_RESG_1_Id"
    FOREIGN KEY ("RESG_1_Id" )
    REFERENCES "acl_resgroups" ("RESGk_1_Id" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

DROP INDEX IF EXISTS "in_blocker_attempts_BLOA_Status";
DROP INDEX IF EXISTS "in_blocker_attempts_BLOA_Ip";
CREATE INDEX "in_blocker_attempts_BLOA_Status" ON "blocker_attempts" ("BLOA_Status");
CREATE INDEX "in_blocker_attempts_BLOA_Ip" ON "blocker_attempts" ("BLOA_Ip");

CREATE TABLE IF NOT EXISTS "acl_resources_resgroups" (
  "REGPk_1_Id" SERIAL PRIMARY KEY,
  "RES_1_Id" INTEGER NOT NULL,
  "RESG_2_Id" INTEGER NOT NULL,
  CONSTRAINT "fk_acl_resources_resgroups_RES_1_Id" FOREIGN KEY ("RES_1_Id")
	REFERENCES "acl_resources" ("RESk_1_Id")
	ON DELETE CASCADE
	ON UPDATE CASCADE,
  CONSTRAINT "fk_acl_resources_resgroups_RESG_2_Id" FOREIGN KEY ("RESG_2_Id")
	REFERENCES "acl_resgroups" ("RESGk_1_Id")
	ON DELETE CASCADE
	ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS "acl_roles" (
  "ROLk_1_Id" SERIAL PRIMARY KEY,
  "ROL_Name" VARCHAR(30) NOT NULL
);

DROP INDEX IF EXISTS "un_acl_roles_ROL_Name";
CREATE UNIQUE INDEX "un_acl_roles_ROL_Name" ON "acl_roles" ("ROL_Name");

CREATE TABLE IF NOT EXISTS "acl_role_parents" (
  "ROLPk_1_Id" SERIAL PRIMARY KEY,
  "ROL_1_Id" INTEGER NOT NULL,
  "ROL_2_Id_parent" INTEGER NOT NULL,
  CONSTRAINT "fk_acl_role_parents_ROL_1_Id" FOREIGN KEY ("ROL_1_Id")
	REFERENCES "acl_roles" ("ROLk_1_Id")
	ON DELETE CASCADE
	ON UPDATE CASCADE,
  CONSTRAINT "fk_acl_role_parents_ROL_2_Id_parent" FOREIGN KEY ("ROL_2_Id_parent")
	REFERENCES "acl_roles" ("ROLk_1_Id")
	ON DELETE CASCADE
	ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS "acl_rules" (
  "RULk_1_Id" SERIAL PRIMARY KEY,
  "RUL_Action" SB_ACL_RULE_TYPE NOT NULL,
  "RUL_Priority" INTEGER NOT NULL,
  "ROL_1_Id" INTEGER DEFAULT NULL,
  "RES_2_Id" INTEGER DEFAULT NULL,
  CONSTRAINT "fk_acl_rules_ROL_1_Id" FOREIGN KEY ("ROL_1_Id")
	REFERENCES "acl_roles" ("ROLk_1_Id")
	ON DELETE CASCADE
	ON UPDATE CASCADE,
  CONSTRAINT "fk_acl_rules_RES_2_Id" FOREIGN KEY ("RES_2_Id")
	REFERENCES "acl_resources" ("RESk_1_Id")
	ON DELETE CASCADE
	ON UPDATE CASCADE
);

DROP INDEX IF EXISTS "in_acl_rules_RUL_Priority";
CREATE INDEX "in_acl_rules_RUL_Priority" ON "acl_rules" ("RUL_Priority");

CREATE TABLE IF NOT EXISTS "acl_rules_privileges" (
  "RULPk_1_Id" SERIAL PRIMARY KEY,
  "RUL_1_Id" INTEGER NOT NULL,
  "PRIV_2_Id" INTEGER NOT NULL,
  CONSTRAINT "fk_acl_rules_privileges_RUL_1_Id" FOREIGN KEY ("RUL_1_Id")
	REFERENCES "acl_rules" ("RULk_1_Id")
	ON DELETE CASCADE
	ON UPDATE CASCADE,
  CONSTRAINT "fk_acl_rules_privileges_PRIV_2_Id" FOREIGN KEY ("PRIV_2_Id")
	REFERENCES "acl_privileges" ("PRIVk_1_Id")
	ON DELETE CASCADE
	ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS "countries" (
  "COUk_1_Id" SERIAL PRIMARY KEY,
  "COU_Name" VARCHAR(50) NOT NULL,
  "COU_Iso2Name" VARCHAR(2) NOT NULL,
  "COU_Iso3Name" VARCHAR(3) NOT NULL
);

DROP INDEX IF EXISTS "in_countries_COU_Name";
DROP INDEX IF EXISTS "in_countries_COU_Iso2Name";
DROP INDEX IF EXISTS "in_countries_COU_Iso3Name";
CREATE INDEX "in_countries_COU_Name" ON "countries" ("COU_Name");
CREATE INDEX "in_countries_COU_Iso2Name" ON "countries" ("COU_Iso2Name");
CREATE INDEX "in_countries_COU_Iso3Name" ON "countries" ("COU_Iso3Name");

CREATE TABLE IF NOT EXISTS "regions" (
  "REGk_1_Id" SERIAL PRIMARY KEY,
  "REG_Name" VARCHAR(100) NOT NULL,
  "REG_FIPScode" VARCHAR(2) NOT NULL,
  "COU_1_Id" INTEGER NOT NULL,
  CONSTRAINT "fk_regions_COU_1_Id" 
	FOREIGN KEY ("COU_1_Id")
	REFERENCES "countries" ("COUk_1_Id")
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
);

DROP INDEX IF EXISTS "in_regions_REG_Name";
DROP INDEX IF EXISTS "in_regions_REG_FIPScode";
CREATE INDEX "in_regions_REG_Name" ON "regions" ("REG_Name");
CREATE INDEX "in_regions_REG_FIPScode" ON "regions" ("REG_FIPScode");

CREATE TABLE IF NOT EXISTS "cities" (
  "CITk_1_Id" SERIAL PRIMARY KEY,
  "CIT_Name" VARCHAR(100) DEFAULT NULL,
  "CIT_Urlname" VARCHAR(100) DEFAULT NULL,
  "CIT_AccentCityName" VARCHAR(100) DEFAULT NULL,
  "CIT_Latitude" DECIMAL(8,5) DEFAULT NULL,
  "CIT_Longitude" DECIMAL(8,5) DEFAULT NULL,
  "CIT_Population" INTEGER DEFAULT NULL,
  "REG_1_Id" INTEGER NOT NULL,
  "COU_2_Id" INTEGER NOT NULL,
  CONSTRAINT "fk_cities_REG_1_Id" FOREIGN KEY ("REG_1_Id")
	REFERENCES "regions" ("REGk_1_Id")
	ON DELETE CASCADE
	ON UPDATE CASCADE,
  CONSTRAINT "fk_cities_COU_2_Id" FOREIGN KEY ("COU_2_Id")
	REFERENCES "countries" ("COUk_1_Id")
	ON DELETE CASCADE
	ON UPDATE CASCADE
);

DROP INDEX IF EXISTS "in_cities_CIT_Name";
DROP INDEX IF EXISTS "in_cities_CIT_AccentCityName";
CREATE INDEX "in_cities_CIT_Name" ON "cities" ("CIT_Name");
CREATE INDEX "in_cities_CIT_AccentCityName" ON "cities" ("CIT_AccentCityName");

CREATE TABLE IF NOT EXISTS "clients" (
  "CLIk_1_Id" SERIAL PRIMARY KEY,
  "CLI_NIP" VARCHAR(10) NULL DEFAULT NULL,
  "CLI_Password" VARCHAR(60) NOT NULL,
  "CLI_Email" VARCHAR(100) NOT NULL,
  "CLI_Address" VARCHAR(150) NULL DEFAULT NULL,
  "CLI_PhoneNumber" VARCHAR(9) NULL DEFAULT NULL,
  "CLI_Status" SB_USER_STATUS DEFAULT NULL,
  "CLI_Points" INTEGER NULL DEFAULT 0 ,
  "CLI_DateAdded" TIMESTAMP NOT NULL,
  "CIT_1_Id" INTEGER NULL DEFAULT NULL,
  "ROL_2_Id" INTEGER NOT NULL,
  CONSTRAINT "fk_clients_CIT_1_Id"
    FOREIGN KEY ("CIT_1_Id" )
    REFERENCES "cities" ("CITk_1_Id" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT "fk_clients_ROL_2_Id"
    FOREIGN KEY ("ROL_2_Id")
    REFERENCES "acl_roles" ("ROLk_1_Id" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

DROP INDEX IF EXISTS "un_clients_CLI_Email";
DROP INDEX IF EXISTS "in_clients_CLI_Status";
CREATE UNIQUE INDEX "un_clients_CLI_Email" ON "clients" ("CLI_Email");
CREATE INDEX "in_clients_CLI_Status" ON "clients" ("CLI_Status");

CREATE TABLE IF NOT EXISTS "client_people" (
  "CLIPk_1_Id" SERIAL PRIMARY KEY ,
  "CLIP_Name" VARCHAR(50) NOT NULL,
  "CLIP_Surname" VARCHAR(50) NOT NULL,
  "CLIP_PESEL" VARCHAR(11) NOT NULL,
  "CLIP_Gender" SB_CLIENT_PEOPLE_GENDER NOT NULL,
  "CLI_1_Id" INTEGER NOT NULL,
  CONSTRAINT "fk_clients_people_CLI_1_Id"
    FOREIGN KEY ("CLI_1_Id")
    REFERENCES "clients" ("CLIk_1_Id")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

DROP INDEX IF EXISTS "un_client_people_CLIP_PESEL";
CREATE UNIQUE INDEX "un_client_people_CLIP_PESEL" ON "client_people" ("CLIP_PESEL");

CREATE TABLE IF NOT EXISTS "client_companies" (
  "CLICk_1_Id" SERIAL PRIMARY KEY ,
  "CLIC_CompanyName" VARCHAR(50) NOT NULL,
  "CLIC_REGON" VARCHAR(14) NOT NULL,
  "CLI_1_Id" INTEGER NOT NULL,
  CONSTRAINT "fk_clients_companies_CLI_1_Id"
    FOREIGN KEY ("CLI_1_Id" )
    REFERENCES "clients" ("CLIk_1_Id" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

DROP INDEX IF EXISTS "un_client_companies_CLIC_REGON";
CREATE UNIQUE INDEX "un_client_companies_CLIC_REGON" ON "client_companies" ("CLIC_REGON");	

CREATE TABLE IF NOT EXISTS "employees" (
  "EMPk_1_Id" SERIAL PRIMARY KEY ,
  "EMP_Name" VARCHAR(50) NOT NULL,
  "EMP_Surname" VARCHAR(50) NOT NULL,
  "EMP_PESEL" VARCHAR(11) NOT NULL,
  "EMP_NIP" VARCHAR(10) NULL DEFAULT NULL,
  "EMP_Gender" SB_EMPLOYEES_GENDER DEFAULT NULL,
  "EMP_Email" VARCHAR(100) NOT NULL,
  "EMP_PhoneNumber" VARCHAR(9) DEFAULT NULL,
  "EMP_Password" VARCHAR(60) NOT NULL,
  "EMP_Status" SB_EMPLOYEES_STATUS NOT NULL,
  "EMP_Address" VARCHAR(150) DEFAULT NULL,
  "CIT_1_Id" INTEGER NULL DEFAULT NULL,
  "ROL_2_Id" INTEGER NOT NULL,
  CONSTRAINT "fk_employees_CIT_1_Id"
    FOREIGN KEY ("CIT_1_Id" )
    REFERENCES "cities" ("CITk_1_Id" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT "fk_employees_ROL_2_Id"
    FOREIGN KEY ("ROL_2_Id" )
    REFERENCES "acl_roles" ("ROLk_1_Id" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

DROP INDEX IF EXISTS "un_employees_EMP_PESEL";
DROP INDEX IF EXISTS "un_employees_EMP_NIP";
DROP INDEX IF EXISTS "un_employees_EMP_Email";
CREATE UNIQUE INDEX "un_employees_EMP_PESEL" ON "employees" ("EMP_PESEL");
CREATE UNIQUE INDEX "un_employees_EMP_NIP" ON "employees" ("EMP_NIP");
CREATE UNIQUE INDEX "un_employees_EMP_Email" ON "employees" ("EMP_Email");

CREATE TABLE IF NOT EXISTS "sessions" (
  "SESk_1_Id" SERIAL PRIMARY KEY ,
  "SES_Key" VARCHAR(50) NOT NULL,
  "SES_Value" TEXT NULL DEFAULT NULL,
  "SES_DateCreated" TIMESTAMP NOT NULL,
  "SES_DateExpired" TIMESTAMP NOT NULL,
  "SES_Ip" VARCHAR(30) NOT NULL,
  "SES_Useragent" VARCHAR(150) NULL DEFAULT NULL,
  "SES_Status" SB_SESSION_STATUS NOT NULL,
  "CLI_1_Id" INTEGER NULL DEFAULT NULL,
  "EMP_2_Id" INTEGER NULL DEFAULT NULL,
  CONSTRAINT "fk_sessions_CLI_1_Id"
    FOREIGN KEY ("CLI_1_Id" )
    REFERENCES "clients" ("CLIk_1_Id" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT "fk_sessions_EMP_2_Id"
    FOREIGN KEY ("EMP_2_Id" )
    REFERENCES "employees" ("EMPk_1_Id" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
DROP INDEX IF EXISTS "un_sessions_SES_Key";
DROP INDEX IF EXISTS "in_sessions_SES_DateCreated";
DROP INDEX IF EXISTS "in_sessions_SES_DateExpired";
DROP INDEX IF EXISTS "in_sessions_SES_Ip";
DROP INDEX IF EXISTS "in_sessions_SES_Useragent";
DROP INDEX IF EXISTS "in_sessions_SES_Status";
CREATE UNIQUE INDEX "un_sessions_SES_Key" ON "sessions" ("SES_Key");
CREATE INDEX "in_sessions_SES_DateCreated" ON "sessions" ("SES_DateCreated");
CREATE INDEX "in_sessions_SES_DateExpired" ON "sessions" ("SES_DateExpired");
CREATE INDEX "in_sessions_SES_Ip" ON "sessions" ("SES_Ip");
CREATE INDEX "in_sessions_SES_Useragent" ON "sessions" ("SES_Useragent");
CREATE INDEX "in_sessions_SES_Status" ON "sessions" ("SES_Status");

CREATE TABLE IF NOT EXISTS "products" (
  "PROk_1_Id" SERIAL PRIMARY KEY ,
  "PRO_Name" VARCHAR(100) NOT NULL,
  "PRO_Cost" DECIMAL(8,2) NOT NULL 
);

CREATE TABLE IF NOT EXISTS "car_wash_types" (
  "CAWTk_1_Id" SERIAL PRIMARY KEY ,
  "CAWT_Name" VARCHAR(50) NOT NULL,
  "CAWT_Cost" DECIMAL(5,2) NOT NULL,
  "CAWT_LoyalityPoints" INTEGER NOT NULL
);
  
CREATE TABLE IF NOT EXISTS "car_wash" (
  "CARWk_1_Id" SERIAL PRIMARY KEY ,
  "CARW_DateBeginWash" TIMESTAMP NOT NULL,
  "CARW_DateEndWash" TIMESTAMP NOT NULL,
  "CAWT_1_Id" INTEGER NOT NULL,
  CONSTRAINT "fk_car_wash_CAWT_1_Id"
    FOREIGN KEY ("CAWT_1_Id" )
    REFERENCES "car_wash_types" ("CAWTk_1_Id" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS "petrol_providers" (
  "PETPk_1_Id" SERIAL PRIMARY KEY ,
  "PETP_CompanyName" VARCHAR(50) NOT NULL,
  "PETP_NIP" VARCHAR(10) NULL DEFAULT NULL,
  "PETP_REGON" VARCHAR(14) NULL DEFAULT NULL,
  "PETP_Address" VARCHAR(150) NULL DEFAULT NULL,
  "PETP_Status" SB_PETROL_PROVIDERS_STATUS NULL DEFAULT NULL,
  "PETP_PhoneNumber" VARCHAR(9) NULL DEFAULT NULL,
  "PETP_Email" VARCHAR(50) NULL DEFAULT NULL,
  "CIT_1_Id" INTEGER NULL DEFAULT NULL,
  CONSTRAINT "fk_petrol_providers_CIT_1_Id"
    FOREIGN KEY ("CIT_1_Id" )
    REFERENCES "cities" ("CITk_1_Id" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

DROP INDEX IF EXISTS "un_petrol_providers_PETP_NIP";
DROP INDEX IF EXISTS "un_petrol_providers_PETP_REGON";
DROP INDEX IF EXISTS "un_petrol_providers_PETP_Email";
CREATE UNIQUE INDEX "un_petrol_providers_PETP_NIP" ON "petrol_providers" ("PETP_NIP");
CREATE UNIQUE INDEX "un_petrol_providers_PETP_REGON" ON "petrol_providers" ("PETP_REGON");
CREATE UNIQUE INDEX "un_petrol_providers_PETP_Email" ON "petrol_providers" ("PETP_Email");

CREATE TABLE IF NOT EXISTS "petrol_deliveries" (
  "PETDk_1_Id" SERIAL PRIMARY KEY ,
  "PETD_DeliveryDate" TIMESTAMP NOT NULL,
  "PETD_Quantity" INTEGER NOT NULL,
  "PETD_Cost" DECIMAL(8,2) NOT NULL,
  "PETD_OrderDate" TIMESTAMP NOT NULL,
  "PETD_Status" SB_PETROL_DELIVERIES_STATUS NOT NULL,
  "PETP_1_Id" INTEGER NULL DEFAULT NULL,
  CONSTRAINT "fk_petrol_deliveries_PETP_1_Id"
    FOREIGN KEY ("PETP_1_Id" )
    REFERENCES "petrol_providers" ("PETPk_1_Id" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS "petrol_containers" (
  "PETCk_1_Id" SERIAL PRIMARY KEY ,
  "PETC_Type" SB_PETROL_CONTAINERS_TYPE NOT NULL 
);

CREATE TABLE IF NOT EXISTS "petrol_containers_deliveries" (
  "PECDk_1_Id" SERIAL PRIMARY KEY ,
  "PETD_1_Id" INTEGER NOT NULL,
  "PETC_2_Id" INTEGER NOT NULL,
  CONSTRAINT "fk_petrol_containers_deliveries_PETD_1_Id"
    FOREIGN KEY ("PETD_1_Id" )
    REFERENCES "petrol_deliveries" ("PETDk_1_Id" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT "fk_petrol_containers_deliveries_PETC_2_Id"
    FOREIGN KEY ("PETC_2_Id" )
    REFERENCES "petrol_containers" ("PETCk_1_Id" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS "petrol_container_measurements" (
  "PECMk_1_Id" SERIAL PRIMARY KEY ,
  "PECM_MeasurementDate" TIMESTAMP NOT NULL,
  "PECM_Type" SB_PETROL_CONTAINERS_MEASUREMENTS_TYPE NOT NULL,
  "PECM_Value" DECIMAL(10,2) NOT NULL,
  "PETC_1_Id" INTEGER NOT NULL,
  CONSTRAINT "fk_petrol_container_measurements_PETC_1_Id"
    FOREIGN KEY ("PETC_1_Id" )
    REFERENCES "petrol_containers" ("PETCk_1_Id" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS "petrol_container_logs" (
  "PECLk_1_Id" SERIAL PRIMARY KEY ,
  "PECL_DateAdded" TIMESTAMP NOT NULL,
  "PECL_Message" TEXT NOT NULL,
  "PETC_1_Id" INTEGER NOT NULL,
  CONSTRAINT "fk_petrol_container_logs_PETC_1_Id"
    FOREIGN KEY ("PETC_1_Id" )
    REFERENCES "petrol_containers" ("PETCk_1_Id" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS "refueling_types" (
  "REFTk_1_Id" SERIAL PRIMARY KEY ,
  "REFT_Name" VARCHAR(50) NOT NULL,
  "REFT_Cost" DECIMAL(5,2) NOT NULL,
  "REFT_LoyalityPoints" INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS "refueling" (
  "REFk_1_Id" SERIAL PRIMARY KEY ,
  "REF_Cost" DECIMAL(8,2) NOT NULL,
  "REF_Fuel" DECIMAL(8,2) NOT NULL,
  "REF_RefuelingDate" TIMESTAMP NOT NULL,
  "PETC_1_Id" INTEGER NOT NULL,
  "CLI_2_Id" INTEGER NULL DEFAULT NULL,
  "REFT_3_Id" INTEGER NOT NULL,
  CONSTRAINT "fk_refueling_PETC_1_Id"
    FOREIGN KEY ("PETC_1_Id" )
    REFERENCES "petrol_containers" ("PETCk_1_Id" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT "fk_refueling_CLI_2_Id"
    FOREIGN KEY ("CLI_2_Id" )
    REFERENCES "clients" ("CLIk_1_Id" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT "fk_refueling_REFT_3_Id"
    FOREIGN KEY ("REFT_3_Id" )
    REFERENCES "refueling_types" ("REFTk_1_Id" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS "bills" (
  "BILk_1_Id" SERIAL PRIMARY KEY ,
  "BIL_InvoiceDate" TIMESTAMP NOT NULL,
  "BIL_Amount" DECIMAL(8,2) NOT NULL,
  "BIL_VAT" DECIMAL(7,2) NOT NULL,
  "BIL_Total" DECIMAL(8,2) NOT NULL,
  "CLI_1_Id" INTEGER NULL DEFAULT NULL,
  "EMP_2_Id" INTEGER NOT NULL,
  CONSTRAINT "fk_bills_CLI_1_Id"
    FOREIGN KEY ("CLI_1_Id" )
    REFERENCES "clients" ("CLIk_1_Id" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT "fk_bills_EMP_2_Id"
    FOREIGN KEY ("EMP_2_Id" )
    REFERENCES "employees" ("EMPk_1_Id" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS "bill_elements" (
  "BILEk_1_Id" SERIAL PRIMARY KEY ,
  "PRO_1_Id" INTEGER NULL DEFAULT NULL,
  "REF_2_Id" INTEGER NULL DEFAULT NULL,
  "CARW_3_Id" INTEGER NULL DEFAULT NULL,
  "BIL_4_Id" INTEGER NULL DEFAULT NULL,
  CONSTRAINT "fk_bill_elements_PRO_1_Id"
    FOREIGN KEY ("PRO_1_Id" )
    REFERENCES "products" ("PROk_1_Id" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT "fk_bill_elements_REF_2_Id"
    FOREIGN KEY ("REF_2_Id" )
    REFERENCES "refueling" ("REFk_1_Id" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT "fk_bill_elements_CARW_3_Id"
    FOREIGN KEY ("CARW_3_Id" )
    REFERENCES "car_wash" ("CARWk_1_Id" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT "fk_bill_elements_BIL_4_Id"
    FOREIGN KEY ("BIL_4_Id" )
    REFERENCES "bills" ("BILk_1_Id" )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS "prize_categories" (
  "PRCTk_1_Id" SERIAL PRIMARY KEY ,
  "PRCT_Name" VARCHAR(50) NOT NULL,
  "PRCT_MinPoints" INTEGER NOT NULL,
  "PRCT_Status" SB_PRIZE_CATEGORIES_STATUS NOT NULL
);

DROP INDEX IF EXISTS "in_prizes_category_PRCT_Status";
DROP INDEX IF EXISTS "in_prizes_category_PRCT_Name";
CREATE INDEX "in_prizes_category_PRCT_Status" ON "prize_categories" ("PRCT_Status");
CREATE INDEX "in_prizes_category_PRCT_Name" ON "prize_categories" ("PRCT_Name");
  
CREATE TABLE IF NOT EXISTS "prizes" (
  "PRIZk_1_Id" SERIAL PRIMARY KEY ,
  "PRIZ_DateOfSelection" TIMESTAMP NOT NULL,
  "PRIZ_Status" SB_PRIZE_STATUS NOT NULL,
  "CLI_1_Id" INTEGER NOT NULL,
  "PRCT_2_Id" INTEGER NOT NULL,
  CONSTRAINT "fk_prizes_PRCT_2_Id"
    FOREIGN KEY ("PRCT_2_Id" )
    REFERENCES "prize_categories" ("PRCTk_1_Id")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT "fk_prizes_CLI_1_Id"
    FOREIGN KEY ("CLI_1_Id" )
    REFERENCES "clients" ("CLIk_1_Id")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

DROP INDEX IF EXISTS "in_prizes_PRCT_Status";
DROP INDEX IF EXISTS "in_prizes_PRIZ_DateOfSelection";
CREATE INDEX "in_prizes_PRCT_Status" ON "prizes" ("PRIZ_Status");
CREATE INDEX "in_prizes_PRIZ_DateOfSelection" ON "prizes" ("PRIZ_DateOfSelection");

CREATE TABLE IF NOT EXISTS "news" (
  "NEWSk_1_Id" SERIAL PRIMARY KEY ,
  "NEWS_Name" VARCHAR(50) NOT NULL,
  "NEWS_ShortDescription" VARCHAR(200) NULL DEFAULT NULL,
  "NEWS_Text" TEXT NULL DEFAULT NULL,
  "NEWS_DateAdded" TIMESTAMP NOT NULL,
  "NEWS_Status" SB_NEWS_STATUS NOT NULL,
  "EMP_1_Id" INTEGER NOT NULL,
   CONSTRAINT "fk_news_EMP_1_Id"
    FOREIGN KEY ("EMP_1_Id" )
    REFERENCES "employees" ("EMPk_1_Id")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

DROP INDEX IF EXISTS "in_news_NEWS_DateAdded";
DROP INDEX IF EXISTS "in_news_NEWS_Status";
CREATE INDEX "in_news_NEWS_DateAdded" ON "news" ("NEWS_Name");
CREATE INDEX "in_news_NEWS_Status" ON "news" ("NEWS_Status");
    
DO '
BEGIN
	IF EXISTS (SELECT 1 FROM pg_class where relname = ''acl_privileges_PRIVk_1_Id_seq'' ) THEN
		ALTER SEQUENCE "acl_privileges_PRIVk_1_Id_seq" RENAME TO "acl_privileges_privk_1_id_seq";
		ALTER TABLE "acl_privileges" ALTER COLUMN "PRIVk_1_Id" SET DEFAULT NEXTVAL(''public.acl_privileges_privk_1_id_seq'');
		ALTER SEQUENCE "acl_resgroups_RESGk_1_Id_seq" RENAME TO "acl_resgroups_resgk_1_id_seq";
		ALTER TABLE "acl_resgroups" ALTER COLUMN "RESGk_1_Id" SET DEFAULT NEXTVAL(''public.acl_resgroups_resgk_1_id_seq'');
		ALTER SEQUENCE "acl_resources_resgroups_REGPk_1_Id_seq" RENAME TO "acl_resources_resgroups_regpk_1_id_seq";
		ALTER TABLE "acl_resources_resgroups" ALTER COLUMN "REGPk_1_Id" SET DEFAULT NEXTVAL(''public.acl_resources_resgroups_regpk_1_id_seq'');
		ALTER SEQUENCE "acl_resources_RESk_1_Id_seq" RENAME TO "acl_resources_resk_1_id_seq";
		ALTER TABLE "acl_resources" ALTER COLUMN "RESk_1_Id" SET DEFAULT NEXTVAL(''public.acl_resources_resk_1_id_seq'');
		ALTER SEQUENCE "acl_role_parents_ROLPk_1_Id_seq" RENAME TO "acl_role_parents_rolpk_1_id_seq";
		ALTER TABLE "acl_role_parents" ALTER COLUMN "ROLPk_1_Id" SET DEFAULT NEXTVAL(''public.acl_role_parents_rolpk_1_id_seq'');
		ALTER SEQUENCE "acl_roles_ROLk_1_Id_seq" RENAME TO "acl_roles_rolk_1_id_seq";
		ALTER TABLE "acl_roles" ALTER COLUMN "ROLk_1_Id" SET DEFAULT NEXTVAL(''public.acl_roles_rolk_1_id_seq'');
		ALTER SEQUENCE "acl_rules_RULk_1_Id_seq" RENAME TO "acl_rules_rulk_1_id_seq";
		ALTER TABLE "acl_rules" ALTER COLUMN "RULk_1_Id" SET DEFAULT NEXTVAL(''public.acl_rules_rulk_1_id_seq'');
		ALTER SEQUENCE "acl_rules_privileges_RULPk_1_Id_seq" RENAME TO "acl_rules_privileges_rulpk_1_id_seq";
		ALTER TABLE "acl_rules_privileges" ALTER COLUMN "RULPk_1_Id" SET DEFAULT NEXTVAL(''public.acl_rules_privileges_rulpk_1_id_seq'');
		ALTER SEQUENCE "bill_elements_BILEk_1_Id_seq" RENAME TO "bill_elements_bilek_1_id_seq";
		ALTER TABLE "bill_elements" ALTER COLUMN "BILEk_1_Id" SET DEFAULT NEXTVAL(''public.bill_elements_bilek_1_id_seq'');
		ALTER SEQUENCE "bills_BILk_1_Id_seq" RENAME TO "bills_bilk_1_id_seq";
		ALTER TABLE "bills" ALTER COLUMN "BILk_1_Id" SET DEFAULT NEXTVAL(''public.bills_bilk_1_id_seq'');
		ALTER SEQUENCE "blocker_attempts_BLOAk_1_Id_seq" RENAME TO "blocker_attempts_bloak_1_id_seq";
		ALTER TABLE "blocker_attempts" ALTER COLUMN "BLOAk_1_Id" SET DEFAULT NEXTVAL(''public.blocker_attempts_bloak_1_id_seq'');
		ALTER SEQUENCE "blocker_records_BLORk_1_Id_seq" RENAME TO "blocker_records_blork_1_id_seq";
		ALTER TABLE "blocker_records" ALTER COLUMN "BLORk_1_Id" SET DEFAULT NEXTVAL(''public.blocker_records_blork_1_id_seq'');
		ALTER SEQUENCE "car_wash_CARWk_1_Id_seq" RENAME TO "car_wash_carwk_1_id_seq";
		ALTER TABLE "car_wash" ALTER COLUMN "CARWk_1_Id" SET DEFAULT NEXTVAL(''public.car_wash_carwk_1_id_seq'');
		ALTER SEQUENCE "car_wash_types_CAWTk_1_Id_seq" RENAME TO "car_wash_types_cawtk_1_id_seq";
		ALTER TABLE "car_wash_types" ALTER COLUMN "CAWTk_1_Id" SET DEFAULT NEXTVAL(''public.car_wash_types_cawtk_1_id_seq'');
		ALTER SEQUENCE "cities_CITk_1_Id_seq" RENAME TO "cities_citk_1_id_seq";
		ALTER TABLE "cities" ALTER COLUMN "CITk_1_Id" SET DEFAULT NEXTVAL(''public.cities_citk_1_id_seq'');
		ALTER SEQUENCE "client_companies_CLICk_1_Id_seq" RENAME TO "client_companies_click_1_id_seq";
		ALTER TABLE "client_companies" ALTER COLUMN "CLICk_1_Id" SET DEFAULT NEXTVAL(''public.client_companies_click_1_id_seq'');
		ALTER SEQUENCE "client_people_CLIPk_1_Id_seq" RENAME TO "client_people_clipk_1_id_seq";
		ALTER TABLE "client_people" ALTER COLUMN "CLIPk_1_Id" SET DEFAULT NEXTVAL(''public.client_people_clipk_1_id_seq'');
		ALTER SEQUENCE "clients_CLIk_1_Id_seq" RENAME TO "clients_clik_1_id_seq";
		ALTER TABLE "clients" ALTER COLUMN "CLIk_1_Id" SET DEFAULT NEXTVAL(''public.clients_clik_1_id_seq'');
		ALTER SEQUENCE "countries_COUk_1_Id_seq" RENAME TO "countries_couk_1_id_seq";
		ALTER TABLE "countries" ALTER COLUMN "COUk_1_Id" SET DEFAULT NEXTVAL(''public.countries_couk_1_id_seq'');
		ALTER SEQUENCE "employees_EMPk_1_Id_seq" RENAME TO "employees_empk_1_id_seq";
		ALTER TABLE "employees" ALTER COLUMN "EMPk_1_Id" SET DEFAULT NEXTVAL(''public.employees_empk_1_id_seq'');
		ALTER SEQUENCE "news_NEWSk_1_Id_seq" RENAME TO "news_newsk_1_id_seq";
		ALTER TABLE "news" ALTER COLUMN "NEWSk_1_Id" SET DEFAULT NEXTVAL(''public.news_newsk_1_id_seq'');
		ALTER SEQUENCE "petrol_container_logs_PECLk_1_Id_seq" RENAME TO "petrol_container_logs_peclk_1_id_seq";
		ALTER TABLE "petrol_container_logs" ALTER COLUMN "PECLk_1_Id" SET DEFAULT NEXTVAL(''public.petrol_container_logs_peclk_1_id_seq'');
		ALTER SEQUENCE "petrol_container_measurements_PECMk_1_Id_seq" RENAME TO "petrol_container_measurements_pecmk_1_id_seq";
		ALTER TABLE "petrol_container_measurements" ALTER COLUMN "PECMk_1_Id" SET DEFAULT NEXTVAL(''public.petrol_container_measurements_pecmk_1_id_seq'');
		ALTER SEQUENCE "petrol_containers_PETCk_1_Id_seq" RENAME TO "petrol_containers_petck_1_id_seq";
		ALTER TABLE "petrol_containers" ALTER COLUMN "PETCk_1_Id" SET DEFAULT NEXTVAL(''public.petrol_containers_petck_1_id_seq'');
		ALTER SEQUENCE "petrol_containers_deliveries_PECDk_1_Id_seq" RENAME TO "petrol_containers_deliveries_pecdk_1_id_seq";
		ALTER TABLE "petrol_containers_deliveries" ALTER COLUMN "PECDk_1_Id" SET DEFAULT NEXTVAL(''public.petrol_containers_deliveries_pecdk_1_id_seq'');
		ALTER SEQUENCE "petrol_deliveries_PETDk_1_Id_seq" RENAME TO "petrol_deliveries_petdk_1_id_seq";
		ALTER TABLE "petrol_deliveries" ALTER COLUMN "PETDk_1_Id" SET DEFAULT NEXTVAL(''public.petrol_deliveries_petdk_1_id_seq'');
		ALTER SEQUENCE "petrol_providers_PETPk_1_Id_seq" RENAME TO "petrol_providers_petpk_1_id_seq";
		ALTER TABLE "petrol_providers" ALTER COLUMN "PETPk_1_Id" SET DEFAULT NEXTVAL(''public.petrol_providers_petpk_1_id_seq'');
		ALTER SEQUENCE "prize_categories_PRCTk_1_Id_seq" RENAME TO "prize_categories_prctk_1_id_seq";
		ALTER TABLE "prize_categories" ALTER COLUMN "PRCTk_1_Id" SET DEFAULT NEXTVAL(''public.prize_categories_prctk_1_id_seq'');
		ALTER SEQUENCE "prizes_PRIZk_1_Id_seq" RENAME TO "prizes_prizk_1_id_seq";
		ALTER TABLE "prizes" ALTER COLUMN "PRIZk_1_Id" SET DEFAULT NEXTVAL(''public.prizes_prizk_1_id_seq'');
		ALTER SEQUENCE "products_PROk_1_Id_seq" RENAME TO "products_prok_1_id_seq";
		ALTER TABLE "products" ALTER COLUMN "PROk_1_Id" SET DEFAULT NEXTVAL(''public.products_prok_1_id_seq'');
		ALTER SEQUENCE "refueling_REFk_1_Id_seq" RENAME TO "refueling_refk_1_id_seq";
		ALTER TABLE "refueling" ALTER COLUMN "REFk_1_Id" SET DEFAULT NEXTVAL(''public.refueling_refk_1_id_seq'');
		ALTER SEQUENCE "refueling_types_REFTk_1_Id_seq" RENAME TO "refueling_types_reftk_1_id_seq";
		ALTER TABLE "refueling_types" ALTER COLUMN "REFTk_1_Id" SET DEFAULT NEXTVAL(''public.refueling_types_reftk_1_id_seq'');
		ALTER SEQUENCE "regions_REGk_1_Id_seq" RENAME TO "regions_regk_1_id_seq";
		ALTER TABLE "regions" ALTER COLUMN "REGk_1_Id" SET DEFAULT NEXTVAL(''public.regions_regk_1_id_seq'');
		ALTER SEQUENCE "sessions_SESk_1_Id_seq" RENAME TO "sessions_sesk_1_id_seq";
		ALTER TABLE "sessions" ALTER COLUMN "SESk_1_Id" SET DEFAULT NEXTVAL(''public.sessions_sesk_1_id_seq'');
	END IF;
END';