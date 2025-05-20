-- ums."user" definition

-- Drop table

-- DROP TABLE ums."user";

CREATE TABLE ums."user" (
	id int8 NOT NULL,
	created_at int8 NULL,
	updated_at int8 NULL,
	"version" int4 NULL,
	email varchar NULL,
	"name" varchar NULL
);

-- ums.ums_credentials definition

-- Drop table

-- DROP TABLE ums.ums_credentials;

CREATE TABLE ums.ums_credentials (
	credentials_id int8 NOT NULL,
	access_key varchar NULL,
	secret_key varchar NULL,
	"token" varchar NULL,
	expired_at int8 NULL,
	created_at int8 NULL,
	updated_at int8 NULL,
	password_hash varchar NULL,
	"version" int4 NULL
);