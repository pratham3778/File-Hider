create database FileHider;
use FileHider;
create table users(id int primary key auto_increment, name varchar(100), email varchar(100) unique);
desc users;
create table data(id int primary key auto_increment, name varchar(100), path varchar(255), email varchar(100), bin_data blob);
desc data;
