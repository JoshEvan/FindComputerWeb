CREATE TABLE if not exists categories(
    id serial NOT NULL PRIMARY KEY,
    name varchar(100),
    description varchar,
    is_active boolean DEFAULT TRUE
);
