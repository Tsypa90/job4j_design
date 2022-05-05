create table item(
	id serial primary key,
	name text
);

create table client(
	id serial primary key,
	name text
);

create table booking(
	id serial primary key,
	item_id int references item(id),
	client_id int references client(id)
);