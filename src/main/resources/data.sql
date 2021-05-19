DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM restaurants;
DELETE FROM menus;
DELETE FROM votes;
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

INSERT INTO menus(name, price, date, rest_id)
VALUES ('Meal1', 100, '2020-11-11', 100003),
       ('Meal2', 120, '2020-11-12', 100003),
       ('Meal3', 130, '2020-11-13', 100003),
       ('Meal4', 140, '2020-11-11', 100004),
       ('Meal5', 150, '2020-11-12', 100004),
       ('Meal6', 160, '2020-11-13', 100004),
       ('Meal7', 170, '2020-11-11', 100005),
       ('Meal8', 180, '2020-11-12', 100005),
       ('Meal9', 190, '2020-11-13', 100005),
       ('Meal10', 200, '2020-11-11', 100006),
       ('Meal11', 210, '2020-11-12', 100006),
       ('Meal12', 220, '2020-11-13', 100006),
       ('Meal13', 230, '2020-11-11', 100007),
       ('Meal14', 240, '2020-11-12', 100007),
       ('Meal15', 250, '2020-11-13', 100007);

INSERT INTO votes (user_id, rest_id, date)
VALUES (100000, 100003, '2020-11-11'),
       (100000, 100004, '2020-11-12'),
       (100000, 100005, '2020-11-13'),
       (100001, 100003, '2020-11-11'),
       (100001, 100004, '2020-11-12'),
       (100002, 100005, '2020-11-11'),
       (100002, 100006, '2020-11-12'),
       (100002, 100007, '2020-11-13');