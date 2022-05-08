create table type(
	id serial primary key,
	name text
);

create table product(
	id serial primary key,
	name text,
	type_id int references type(id),
	expired_date date,
	price int
);

insert into type(name) values('сыр');
insert into type(name) values('молоко');
insert into type(name) values('мороженое');
insert into type(name) values('колбаса');
insert into type(name) values('вода');

insert into product(name, type_id, expired_date, price) values('Сыр плавленный', 1, '20.05.2022', 150);
insert into product(name, type_id, expired_date, price) values('Сыр дружба', 1, '24.05.2022', 50);
insert into product(name, type_id, expired_date, price) values('Сыр масдам', 1, '29.06.2022', 350);
insert into product(name, type_id, expired_date, price) values('Сыр моцарелла', 1, '20.08.2022', 350);
insert into product(name, type_id, expired_date, price) values('Сыр пармезан', 1, '20.09.2022', 750);
insert into product(name, type_id, expired_date, price) values('Молоко простоквашино', 2, '18.05.2022', 80);
insert into product(name, type_id, expired_date, price) values('Молоко домик в деревне', 2, '12.05.2022', 90);
insert into product(name, type_id, expired_date, price) values('Молоко вила', 2, '16.05.2022', 75);
insert into product(name, type_id, expired_date, price) values('Мороженое рожок', 3, '20.06.2022', 50);
insert into product(name, type_id, expired_date, price) values('Мороженое стаканчик', 3, '20.06.2022', 50);
insert into product(name, type_id, expired_date, price) values('Мороженое экстерм', 3, '20.08.2022', 60);
insert into product(name, type_id, expired_date, price) values('Мороженое бонпари', 3, '18.10.2022', 70);
insert into product(name, type_id, expired_date, price) values('Мороженое куфшин', 3, '16.09.2022', 90);
insert into product(name, type_id, expired_date, price) values('Мороженое рожок', 3, '27.08.2022', 30);
insert into product(name, type_id, expired_date, price) values('Мороженое рожок', 3, '23.07.2022', 50);
insert into product(name, type_id, expired_date, price) values('Колбаса вязанка', 4, '20.06.2022', 700);
insert into product(name, type_id, expired_date, price) values('Колбаса мироторг', 4, '15.06.2022', 900);
insert into product(name, type_id, expired_date, price) values('Колбаса простоквашино', 4, '20.08.2022', 600);
insert into product(name, type_id, expired_date, price) values('Колбаса ливерная', 4, '30.05.2022', 400);
insert into product(name, type_id, expired_date, price) values('Колбаса вкусная', 4, '27.09.2022', 300);
insert into product(name, type_id, expired_date, price) values('Вода 5л', 5, '20.010.2022', 70);
insert into product(name, type_id, expired_date, price) values('Вода 2л', 5, '20.06.2022', 50);
insert into product(name, type_id, expired_date, price) values('Вода 0,5л', 5, '20.06.2022', 10);
insert into product(name, type_id, expired_date, price) values('Вода 10л', 5, '20.06.2022', 150);

select * from product where type_id = (select id from type where name like 'сыр');

select * from product where name like '%Мороженое%';

select * from product where expired_date < current_date;

select * from product where price = (select max(price) from product);

select s.name, count(s.name)
from product as ss
join type as s
on ss.type_id = s.id
group by s.name;

select * from product where type_id = (select id from type where name like 'сыр') or type_id = (select id from type where name like 'молоко');

select s.name, count(s.name)
from product as ss
join type as s
on ss.type_id = s.id
group by s.name
having count(s.name) < 10;

select * from product
join type as ss
on product.type_id = ss.id