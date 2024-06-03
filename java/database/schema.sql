BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS issue;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

--put table creation statements here


create table issue (
	id SERIAL,
	name VARCHAR(50),
	description VARCHAR (200),
	start_time TIMESTAMP,
	end_time TIMESTAMP
);

COMMIT TRANSACTION;
