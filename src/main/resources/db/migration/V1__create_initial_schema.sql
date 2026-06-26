CREATE TABLE bank_account ( id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            balance DOUBLE NOT NULL );

CREATE TABLE categories ( id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          type VARCHAR(50) NOT NULL );

CREATE TABLE users ( id BIGINT AUTO_INCREMENT PRIMARY KEY,
                     username VARCHAR(255) NOT NULL UNIQUE,
                     email VARCHAR(255) NOT NULL UNIQUE,
                     password VARCHAR(255) NOT NULL,
                     first_name VARCHAR(255),
                     last_name VARCHAR(255),
                     active BOOLEAN,
                     created_at TIMESTAMP,
                     updated_at TIMESTAMP );

CREATE TABLE transaction ( id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           title VARCHAR(255) NOT NULL,
                           amount DOUBLE NOT NULL,
                           date DATE NOT NULL,
                           intern_note VARCHAR(255),
                           created_at TIMESTAMP NOT NULL,
                           updated_at TIMESTAMP );