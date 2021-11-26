1. Insert into console database:
   create database videobase; create table users
   (
   email varchar(40)  not null, user_name varchar(20)  not null, status varchar(50)  null, role varchar(100) null,
   password longblob not null, registration_date date null, id int auto_increment primary key, avatar blob null,
   first_name varchar(20)  null, last_name varchar(20)  null, address varchar(100) null
   ); INSERT INTO users
   (email, user_name, status, role, password, registration_date)
   VALUES ('admin@mail.ru', 'admin', 'ACTIVE', 'ADMIN', '$2a$12$6jRtD3AcDpLFanA8fQ2IheTRhV41U4C5LxzJrMkw13cAZZv85fWXC',
   CURDATE());

2.