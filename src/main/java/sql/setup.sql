-- Create users

-- The ownwer and admin access to the database
create user javasrcadmin password 'xyzzy' login;

-- The application will have only select and insert privs
create user javasrcapp password 'plugh73' login;

-- Now the database
create database javasrcexamples owner javasrcadmin;

-- And a table for student records
create table students (
	id serial primary key,
	studentnumber varchar(15) not null,
	name varchar not null,
	address varchar not null,
	city varchar not null,
	mailcode varchar(9) not null;
);
insert into students values(1, '987654321', 'Robert Tables', '123 Main', 'Bigtown', '91024');
insert into students values(2, '290820900', 'Martha Smitten', '456 King', 'Bigtown', '91024');

grant select,insert on table students to javasrcapp;

-- Other stuff needs to be added here.
