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


CREATE TABLE issue (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(200),
    start_time TEXT,
    end_time TEXT,
    option1 TEXT,
    option2 TEXT,
    option3 TEXT,
    option4 TEXT
);

CREATE TABLE Votes (
    vote_id SERIAL PRIMARY KEY,
    user_id INT,
    issue_id INT,
    selected_option INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (issue_id) REFERENCES issue(id),
    UNIQUE (user_id, issue_id)
);

COMMIT TRANSACTION;
