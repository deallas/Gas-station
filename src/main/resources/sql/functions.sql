CREATE OR REPLACE FUNCTION getRoleParentsByRoleId(acl_roles."ROLk_1_Id"%TYPE) 
RETURNS TABLE (id acl_roles."ROLk_1_Id"%TYPE, name acl_roles."ROL_Name"%TYPE)
AS '
	WITH RECURSIVE path(id, name) AS (
		SELECT "ROLk_1_Id" as id, "ROL_Name" as name FROM acl_roles WHERE "ROLk_1_Id" = $1
  		UNION
  		SELECT
			rol."ROLk_1_Id",
    			rol."ROL_Name"
  		FROM acl_roles as rol,
		     acl_role_parents as parentRole,
		     path as parentPath
  		WHERE parentRole."ROL_1_Id" = parentPath.id 
			AND rol."ROLk_1_Id" = parentRole."ROL_2_Id_parent"
	)
	SELECT * FROM path;
' LANGUAGE 'sql';

CREATE OR REPLACE FUNCTION getRoleChildrenByRoleId(acl_roles."ROLk_1_Id"%TYPE) 
RETURNS TABLE (id acl_roles."ROLk_1_Id"%TYPE, name acl_roles."ROL_Name"%TYPE)
AS '
	
	WITH RECURSIVE path(id, name) AS (
		SELECT "ROLk_1_Id" as id, "ROL_Name" as name FROM acl_roles WHERE "ROLk_1_Id" < $1 AND "ROLk_1_Id" > 2
  		UNION
  		SELECT
			rol."ROLk_1_Id",
    			rol."ROL_Name"
  		FROM acl_roles as rol,
		     acl_role_parents as parentRole,
		     path as childPath
  		WHERE parentRole."ROL_1_Id" = childPath.id 
			AND rol."ROLk_1_Id" = parentRole."ROL_2_Id_parent"
			AND rol."ROLk_1_Id" <> 1
		)
	SELECT * FROM path;
' LANGUAGE 'sql';

CREATE OR REPLACE FUNCTION isChildRoleByRoleId(acl_roles."ROLk_1_Id"%TYPE, 
					       acl_roles."ROLk_1_Id"%TYPE) 
RETURNS BOOLEAN
AS '
	WITH RECURSIVE path(id, name) AS (
		SELECT "ROLk_1_Id" as id, "ROL_Name" as name 
			FROM acl_roles 
			WHERE "ROLk_1_Id" = $2
  		UNION
  		SELECT
			rol."ROLk_1_Id",
    			rol."ROL_Name"
  		FROM acl_roles as rol,
		     acl_role_parents as parentRole,
		     path as parentPath
  		WHERE parentRole."ROL_1_Id" = parentPath.id 
			AND rol."ROLk_1_Id" = parentRole."ROL_2_Id_parent"
			AND rol."ROLk_1_Id" != 1
	)
	SELECT CASE WHEN EXISTS (SELECT * FROM path WHERE id=$1 AND id!=$2)
		THEN TRUE
		ELSE FALSE
	END;
' LANGUAGE 'sql';

CREATE OR REPLACE FUNCTION isAllowed(acl_resources."RES_Name"%TYPE, 
				     acl_privileges."PRIV_Name"%TYPE,
				     acl_roles."ROLk_1_Id"%TYPE) 
RETURNS BOOLEAN
AS '
DECLARE
	r_rule SB_ACL_RULE_INFO;
	i_privilege_id INTEGER;	
	i_count_privileges INTEGER;
BEGIN
	SELECT ruls."RULk_1_Id", ruls."RUL_Action" INTO r_rule
		  FROM acl_rules ruls
		  INNER JOIN getRoleParentsByRoleId($3) tree ON ruls."ROL_1_Id"=tree.id
		  LEFT JOIN acl_roles rol ON rol."ROLk_1_Id"=ruls."ROL_1_Id"
		  LEFT JOIN acl_resources res ON ruls."RES_2_Id"=res."RESk_1_Id"
		  WHERE res."RES_Name"=$1
		  ORDER BY ruls."RUL_Priority" DESC
		  LIMIT 1;

	IF r_rule.action="DENIED" THEN
		SELECT COUNT(p."PRIVk_1_Id") INTO i_count_privileges 
			FROM acl_privileges p
			LEFT JOIN acl_rules_privileges rp ON rp."PRIV_2_Id" = p."PRIVk_1_Id"
			WHERE rp."RUL_1_Id" = r_rule.rule_id;
			
		IF i_count_privileges=0 THEN
			RETURN FALSE;
		END IF;

		SELECT p."PRIVk_1_Id" INTO i_privilege_id 
			FROM acl_privileges p
			LEFT JOIN acl_rules_privileges rp ON rp."PRIV_2_Id"=p."PRIVk_1_Id"
			WHERE rp."RUL_1_Id"=r_rule.rule_id
				AND p."PRIV_Name"=$2;	
	
		IF i_privilege_id IS NULL THEN
			RETURN TRUE;
		END IF;	

		RETURN FALSE;
	ELSIF r_rule.action="ALLOWED" THEN
		SELECT COUNT(p."PRIVk_1_Id") INTO i_count_privileges 
			FROM acl_privileges p
			LEFT JOIN acl_rules_privileges rp ON rp."PRIV_2_id" = p."PRIVk_1_Id"
			WHERE rp."RUL_1_Id" = r_rule.rule_id;
			
		IF i_count_privileges=0 THEN
			RETURN TRUE;
		END IF;

		SELECT p."PRIVk_1_Id" INTO i_privilege_id 
			FROM acl_privileges p
			LEFT JOIN acl_rules_privileges rp ON rp."PRIV_2_Id"=p."PRIVk_1_Id"
			WHERE rp."RUL_1_Id"=r_rule.rule_id
				AND p."PRIV_Name"=$2;	
	
		IF i_privilege_id IS NULL THEN
			RETURN FALSE;
		END IF;
		
		RETURN TRUE;
	ELSE
		RETURN FALSE;
	END IF;
END;	
' LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION triggerBIClients() RETURNS TRIGGER AS
'
BEGIN
    IF NEW."CLI_DateAdded" IS NULL THEN
        NEW."CLI_DateAdded" = NOW();
    END IF;
	
    RETURN NEW;
END;
' LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION triggerBIPetrol_container_logs() RETURNS TRIGGER AS
'
BEGIN
    IF NEW."PECL_DateAdded" IS NULL THEN
        NEW."PECL_DateAdded" = NOW();
    END IF;
	
    RETURN NEW;
END;
' LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION triggerBINews() RETURNS TRIGGER AS
'
BEGIN
    IF NEW."NEWS_DateAdded" IS NULL THEN
        NEW."NEWS_DateAdded" = NOW();
    END IF;
	
    RETURN NEW;
END;
' LANGUAGE 'plpgsql';

DROP TRIGGER IF EXISTS bi_clients ON clients;
CREATE TRIGGER bi_clients BEFORE INSERT ON clients
    FOR EACH ROW EXECUTE PROCEDURE triggerBIClients();
	
DROP TRIGGER IF EXISTS bi_petrol_container_logs ON petrol_container_logs;
CREATE TRIGGER bi_petrol_container_logs BEFORE INSERT ON petrol_container_logs
    FOR EACH ROW EXECUTE PROCEDURE triggerBIPetrol_container_logs();
	
DROP TRIGGER IF EXISTS bi_news ON news;
CREATE TRIGGER bi_news BEFORE INSERT ON news
    FOR EACH ROW EXECUTE PROCEDURE triggerBINews();