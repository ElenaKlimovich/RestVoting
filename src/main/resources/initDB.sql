DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS menus;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS votes;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id       INTEGER DEFAULT nextval('global_seq') PRIMARY KEY,
    name     VARCHAR NOT NULL,
    email    VARCHAR NOT NULL,
    password VARCHAR NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
    id      INTEGER DEFAULT nextval('global_seq') PRIMARY KEY,
    name    VARCHAR NOT NULL,
    address VARCHAR NOT NULL,
    CONSTRAINT restaurants_unique_name_address_idx UNIQUE (name, address)
);

CREATE TABLE menus
(
    id        INTEGER   DEFAULT nextval('global_seq') PRIMARY KEY,
    name      VARCHAR                 NOT NULL,
    price     INTEGER                 NOT NULL,
    date_time TIMESTAMP DEFAULT now() NOT NULL,
    rest_id   INTEGER                 NOT NULL,
    CONSTRAINT menus_unique_name_date_rest_idx UNIQUE (name, date_time, rest_id),
    FOREIGN KEY (rest_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE votes
(
    id        INTEGER   DEFAULT nextval('global_seq') PRIMARY KEY,
    date_time TIMESTAMP DEFAULT now() NOT NULL,
    user_id   INTEGER                 NOT NULL,
    rest_id   INTEGER                 NOT NULL,
    CONSTRAINT votes_unique_date_user_rest_idx UNIQUE (date_time, user_id, rest_id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (rest_id) REFERENCES restaurants (id) ON DELETE CASCADE
);