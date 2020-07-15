CREATE TABLE if not exists users(
    username varchar NOT NULL PRIMARY KEY,
    password varchar(100),
    is_active boolean DEFAULT TRUE
);
