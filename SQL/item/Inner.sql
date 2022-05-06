create table users_contact(
	id serial primary  key,
	city text,
	phone int
);

create table users(
	id serial primary key,
	name text,
	users_contact_id int references users_contact(id)
);

insert into users_contact(city, phone) values('Moscow', 649469);
insert into users_contact(city, phone) values('Kazan', 252525);
insert into users_contact(city, phone) values('Perm', 744535);
insert into users_contact(city, phone) values('Sochi', 324244);
insert into users_contact(city, phone) values('SPb', 98764534);

insert into users(name, users_contact_id) values('Pavel', 1);
insert into users(name, users_contact_id) values('Olga', 2);
insert into users(name, users_contact_id) values('Dima', 5);
insert into users(name, users_contact_id) values('Gena', 3);
insert into users(name, users_contact_id) values('Varvara', 4);
insert into users(name) values('Evgeniy');
insert into users(name) values('Gosha');

select * from users inner join users_contact uc
on users.users_contact_id = uc.id;

select * from users u
join users_contact uc
on u.users_contact_id = uc.id;

select u.name, uc.city, uc.phone
from users as u
join users_contact uc
on u.users_contact_id = uc.id;

select u.name as Имя, uc.city as город, uc.phone as телефон
from users as u
join users_contact as uc
on u.users_contact_id = uc.id;