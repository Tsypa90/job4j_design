create table author(
	id serial primary key,
	name text	
);

create table book(
	id serial primary key,
	name text,
	author_id int references author(id)
);