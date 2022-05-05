create table roles(
	id serial primary key,
	name text
);

create table users(
	id serial primary key,
	name text,
	role_id int references roles(id)
);

create table rules(
	id serial primary key,
	name text
);

create table role_rules(
	id serial primary key,
	roles_id int references roles(id),
	rules_id int references rules(id)
);

create table commentes(
	id serial primary key,
	comment text
);

create table attachs(
	id serial primary key,
	attach text
);

create table item(
	id serial primary key,
	name text,
	users_id int references users(id),
	attachs_id int references attachs(id),
	comments_id int references commentes(id)
);

create table states(
	id serial primary key,
	name text,
	item_id int references item(id)
);

create table category(
	id serial primary key,
	name text,
	item_id int references item(id)
);