create table country(
	id serial primary key,
	name text
);

create table capital(
	id	serial primary key,
	name text,
	country_id int references country(id) unique
);