create table body(
	id serial primary key,
	name text
);

create table engine(
	id serial primary key,
	name text
);

create table gearbox(
	id serial primary key,
	name text
);

create table cars(
	id serial primary key,
	name text,
	body_id int references body(id),
	engine_id int references engine(id),
	gearbox_id int references gearbox(id)
);

insert into body(name) values('bmw 5');
insert into body(name) values('mb 223');
insert into body(name) values('toyota hl');
insert into body(name) values('toyota p04');
insert into engine(name) values('1jz-gt');
insert into engine(name) values('3GR-Fe');
insert into engine(name) values('5md56');
insert into engine(name) values('1nz');
insert into gearbox(name) values('R154');
insert into gearbox(name) values('ZF-5H');
insert into gearbox(name) values('getrag');
insert into cars(name, body_id, engine_id) values ('Toyota Prius', 4, 4);
insert into cars(name, body_id, engine_id, gearbox_id) values ('Toyota LC', 3, 2, 1);
insert into cars(name, body_id, engine_id, gearbox_id) values ('BMW', 1, 3, 3);

select c.name as car, b.name as body, e.name as engine, g.name as gearbox from cars c 
left join body b on c.body_id = b.id
left join engine e on c.engine_id = e.id
left join gearbox g on c.engine_id = g.id;

select * from body b full join cars c on c.body_id = b.id where c.name is null limit 1;
select * from engine e full join cars c on c.engine_id = e.id where c.name is null limit 1;
select * from gearbox g full join cars c on c.gearbox_id = g.id where c.name is null limit 1;

