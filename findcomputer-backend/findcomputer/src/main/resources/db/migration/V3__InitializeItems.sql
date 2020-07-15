CREATE TABLE if not exists items(
    id serial NOT NULL PRIMARY KEY,
    category_id int REFERENCES categories(id),
    owner varchar REFERENCES users(username),
    name varchar(100),
    description varchar,
    price numeric,
    timestamp timestamp default current_timestamp,
    is_active boolean DEFAULT TRUE
);
