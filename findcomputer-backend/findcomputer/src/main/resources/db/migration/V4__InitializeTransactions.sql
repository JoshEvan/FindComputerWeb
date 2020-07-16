CREATE TABLE if not exists transactions(
    id uuid NOT NULL PRIMARY KEY,
    item_id uuid REFERENCES items(id),
    username varchar REFERENCES users(username),
    transaction_time timestamp default current_timestamp,
    is_active boolean DEFAULT TRUE
);
