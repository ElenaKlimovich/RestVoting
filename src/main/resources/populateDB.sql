DELETE
FROM user_roles;
DELETE
FROM users;
DELETE
FROM restaurants;
DELETE
FROM menus;
DELETE
FROM votes;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User1', 'user1@yandex.ru', 'user'),
       ('User2', 'user2@yandex.ru', 'user'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ADMIN', 100000),
       ('USER', 100000),
       ('USER', 100001),
       ('USER', 100002);

INSERT INTO restaurants (name, address)
VALUES ('MAXIMA PIZZA', 'Arbat st., 15'),
       ('HOLLYWOOD', 'Nikitsky b., 8'),
       ('CHINATOWN', 'Michurinsky p-t., 1'),
       ('BEEF&BEER', 'Novocheremushkinskaya st., 28'),
       ('BURGERMAC', 'Petrovka st., 3');

INSERT INTO menus(name, price, rest_id, date_time)
VALUES ('Meal1', 100, 100003, '2020-11-11'),
       ('Meal2', 120, 100003, '2020-11-12'),
       ('Meal3', 130, 100003, '2020-11-13'),
       ('Meal4', 140, 100004, '2020-11-11'),
       ('Meal5', 150, 100004, '2020-11-12'),
       ('Meal6', 160, 100004, '2020-11-13'),
       ('Meal7', 170, 100005, '2020-11-11'),
       ('Meal8', 180, 100005, '2020-11-12'),
       ('Meal9', 190, 100005, '2020-11-13'),
       ('Meal10', 200, 100006, '2020-11-11'),
       ('Meal11', 210, 100006, '2020-11-12'),
       ('Meal12', 220, 100006, '2020-11-13'),
       ('Meal13', 230, 100007, '2020-11-11'),
       ('Meal14', 240, 100007, '2020-11-12'),
       ('Meal15', 250, 100007, '2020-11-13');

INSERT INTO votes (user_id, rest_id, date_time)
VALUES (100000, 100003, '2020-11-11'),
       (100000, 100004, '2020-11-12'),
       (100000, 100005, '2020-11-13'),
       (100001, 100003, '2020-11-11'),
       (100001, 100004, '2020-11-12'),
       (100002, 100005, '2020-11-11'),
       (100002, 100006, '2020-11-12'),
       (100002, 100007, '2020-11-13');