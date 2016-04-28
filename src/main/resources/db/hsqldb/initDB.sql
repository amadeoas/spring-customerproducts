DROP TABLE product_locations IF EXISTS;
DROP TABLE categories IF EXISTS;
DROP TABLE products IF EXISTS;
DROP TABLE locations IF EXISTS;
DROP TABLE customers IF EXISTS;


CREATE TABLE locations (
  id	INTEGER IDENTITY PRIMARY KEY,
  name	VARCHAR(80)
);
CREATE INDEX location_name ON locations (name);

CREATE TABLE customers (
  id         	INTEGER IDENTITY PRIMARY KEY,
  location_id   INTEGER NOT NULL,
  first_name 	VARCHAR(30),
  last_name  	VARCHAR(30)
);
ALTER TABLE customers ADD CONSTRAINT fk_customers_locations FOREIGN KEY (location_id) REFERENCES locations (id);
CREATE INDEX customers_last_name ON customers (last_name);

CREATE TABLE categories (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(40)
);
CREATE INDEX categories_name ON categories (name);

CREATE TABLE products (
  id         	INTEGER IDENTITY PRIMARY KEY,
  category_id   INTEGER NOT NULL,
  location_id   INTEGER NOT NULL,
  name 			VARCHAR(80)
);
ALTER TABLE products ADD CONSTRAINT fk_products_categories FOREIGN KEY (category_id) REFERENCES categories (id);
ALTER TABLE products ADD CONSTRAINT fk_products_locations FOREIGN KEY (location_id) REFERENCES locations (id);
CREATE INDEX product_name ON products (name);
