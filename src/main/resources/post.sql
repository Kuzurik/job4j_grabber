CREATE DATABASE parser;

CREATE TABLE post(
id serial primary key not null,
name varchar(100),
text text,
link varchar(400) UNIQUE,
created date
);

