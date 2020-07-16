CREATE TABLE if not exists users(
    username varchar NOT NULL PRIMARY KEY,
    password varchar,
    profile_info varchar,
    is_active boolean DEFAULT TRUE
);
