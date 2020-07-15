CREATE TABLE if not exists categories(
    name varchar(100) NOT NULL PRIMARY KEY,
    is_active boolean DEFAULT TRUE
);
