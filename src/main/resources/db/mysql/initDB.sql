CREATE DATABASE IF NOT EXISTS cp;

ALTER DATABASE cp
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON cp.* TO cp@localhost IDENTIFIED BY 'cp';

USE cp;

CREATE TABLE IF NOT EXISTS locations (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(60),
  INDEX(name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS customers (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  location_id INT(4) UNSIGNED NOT NULL,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  FOREIGN KEY (location_id) REFERENCES locations(id),
  INDEX(last_name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS categories (
  id INT(4) UNSIGNED NOT NULL,
  name VARCHAR(40),
  INDEX(name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS products (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  category_id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  location_id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(80),
  FOREIGN KEY (category_id) REFERENCES categories(id),
  FOREIGN KEY (location_id) REFERENCES locations(id),
  INDEX(name)
) engine=InnoDB;

CREATE TABLE subscription_products (
  id       		INTEGER IDENTITY PRIMARY KEY,
  customer_id	INTEGER NOT NULL,
  product_id	INTEGER NOT NULL,
  created		TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (customer_id) REFERENCES customers(id),
  FOREIGN KEY (product_id) REFERENCES products(id)
) engine=InnoDB;
