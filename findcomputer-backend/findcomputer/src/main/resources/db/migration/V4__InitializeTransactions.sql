CREATE TABLE if not exists items(
    id serial NOT NULL PRIMARY KEY,
    item_id int REFERENCES items(id),
    username varchar REFERENCES users(username),
    transaction_time timestamp default current_timestamp,
    is_active boolean DEFAULT TRUE
);
