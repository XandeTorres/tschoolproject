CREATE TABLE users (
                       id bigserial not null primary key,
                       name varchar(50),
                       surname varchar(50),
                       email varchar(150),
                       password varchar(50)
);
INSERT INTO users (name, surname, email, password)
values ( 'Admin', 'Main', 'admin@moby.com', 'password');

CREATE TABLE customers (
                           id bigserial not null primary key ,
                           name varchar(50),
                           surname varchar(50),
                           dateofbirth date,
                           passport varchar(50),
                           address varchar(200),
                           email varchar(150),
                           password varchar(50)
);

--ALTER TABLE customers ADD CONSTRAINT unique_id UNIQUE (id);
--drop table customers;

CREATE TABLE contracts (
                           id bigserial not null primary key,
                           number varchar(50)
);
--drop table contracts cascade;

--ALTER TABLE customers ADD contract_id BIGINT unique REFERENCES contracts(id);
--ALTER TABLE customers ADD contract_id BIGINT REFERENCES contracts(id);

-- set manytoone as a owning side as recommended practice
ALTER TABLE contracts ADD customer_id BIGINT REFERENCES customers(id);

CREATE TABLE roles (
                           id bigserial not null primary key,
                           role varchar(50)
);

-- auto-generated definition
create sequence hibernate_sequence;
alter sequence hibernate_sequence owner to postgres;


INSERT INTO customers (id, name, surname, dateofbirth, passport, address, email, password)
VALUES (1, 'Jhon', 'Doe', '01-01-1991','5409343731', 'Warm av. 13', 'jd@yahoo.com', 'kickura77');

INSERT INTO contracts (id, number, customer_id )
VALUES (1, '55512345', 1);