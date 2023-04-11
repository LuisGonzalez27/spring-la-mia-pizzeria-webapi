INSERT INTO db_pizzeria.pizze (descrizione, nome, prezzo) VALUES('San Marzano, fior di latte, schiacciata, olivi pistati, origano, olio.', 'Calabrese', 7.00);
INSERT INTO db_pizzeria.pizze (descrizione, nome, prezzo) VALUES('San Marzano, fior di latte, prosciutto cotto, würstel, origano, olio.', 'Viennese', 7.00);
INSERT INTO db_pizzeria.pizze (descrizione, nome, prezzo) VALUES('San Marzano, fior di latte, cipolla rossa, filetti di tonno sott’olio, origano, olio.', 'Pugliese', 8.00);
INSERT INTO db_pizzeria.offerte (end_offer_date, name, start_offer_date, pizza_id) VALUES('2023-12-01', 'Meta prezzo', '2023-01-25', 1);
INSERT INTO db_pizzeria.offerte (end_offer_date, name, start_offer_date, pizza_id) VALUES('2023-06-01', 'Meta prezzo', '2023-02-25', 2);
INSERT INTO db_pizzeria.offerte (end_offer_date, name, start_offer_date, pizza_id) VALUES('2023-05-01', 'Meta prezzo', '2023-03-25', 3);

INSERT INTO db_pizzeria.users (username, password) VALUES('Luis', '{noop}luis');
INSERT INTO db_pizzeria.users (username, password) VALUES('Miguel','{noop}miguel');

INSERT INTO db_pizzeria.roles (id, name) VALUES(1, 'ADMIN');
INSERT INTO db_pizzeria.roles (id, name) VALUES(2, 'USER');

INSERT INTO db_pizzeria.users_roles(user_id, roles_id) VALUES(1, 1);
INSERT INTO db_pizzeria.users_roles(user_id, roles_id) VALUES(2, 2);

INSERT INTO db_pizzeria.ingredienti (description, name) VALUES('Fresco', 'Pomodoro');
INSERT INTO db_pizzeria.ingredienti (description, name) VALUES('Di tropea', 'Cipolla rossa');
INSERT INTO db_pizzeria.ingredienti (description, name) VALUES('Extravergine di oliva', 'Olio');