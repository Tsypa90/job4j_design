insert into devices(name, price) values('Iphone 12', 75000);
insert into devices(name, price) values('Iphone 13 ProMax', 125000);
insert into devices(name, price) values('Iphone 8', 15000);
insert into devices(name, price) values('Samsung S10', 55000);
insert into devices(name, price) values('Apple Watch', 25000);
insert into devices(name, price) values('Samsung Watch', 20000);
insert into devices(name, price) values('Alcatel A2', 3000);

insert into people(name) values ('Pavel');
insert into people(name) values ('Boris');
insert into people(name) values ('Olga');
insert into people(name) values ('Genadiy');
insert into people(name) values ('Maksim');

insert into devices_people(device_id, people_id) values (3, 1);
insert into devices_people(device_id, people_id) values (7, 1);
insert into devices_people(device_id, people_id) values (2, 2);
insert into devices_people(device_id, people_id) values (5, 2);
insert into devices_people(device_id, people_id) values (3, 3);
insert into devices_people(device_id, people_id) values (6, 3);
insert into devices_people(device_id, people_id) values (5, 4);
insert into devices_people(device_id, people_id) values (4, 4);
insert into devices_people(device_id, people_id) values (2, 5);
insert into devices_people(device_id, people_id) values (3, 5);

select avg(price) from devices;

select pp.name, avg(ss.price)
from devices_people as s
join devices as ss
on s.device_id = ss.id
join people as pp
on s.people_id = pp.id
group by pp.name;

select pp.name, avg(ss.price)
from devices_people as s
join devices as ss
on s.device_id = ss.id
join people as pp
on s.people_id = pp.id
group by pp.name
having avg(ss.price) > 50000;