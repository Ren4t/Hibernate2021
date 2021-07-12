BEGIN;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), price int);
INSERT INTO products (title, price) VALUES
('Bread', 35),
('Milk', 50),
('Cheese', 350),
('Cola', 100),
('Coffee', 500);

DROP TABLE IF EXISTS consumers CASCADE;
CREATE TABLE consumers (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO consumers (name) VALUES
('Max'),
('Jax'),
('Sony');

DROP TABLE IF EXISTS basket CASCADE;
CREATE TABLE basket (consumer_id bigint, product_id bigint, FOREIGN KEY (consumer_id) REFERENCES consumers (id), FOREIGN KEY (product_id) REFERENCES products (id));
INSERT INTO basket (consumer_id, product_id) VALUES
(1, 1),
(1, 2),
(2, 1),
(3, 4),
(3, 5);