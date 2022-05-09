create table departments(
	id serial primary key,
	name text
);

create table employees(
	id serial primary key,
	name text,
	department_id int references departments(id)
);

insert into departments(name) values('1');
insert into departments(name) values('2');
insert into departments(name) values('3');

insert into employees(name, department_id) values('Pavel', 1);
insert into employees(name, department_id) values('Andrey', 1);
insert into employees(name, department_id) values('Olga', 1);
insert into employees(name, department_id) values('Platon', 1);
insert into employees(name, department_id) values('Pavel', 2);
insert into employees(name, department_id) values('Dmitriy', 2);
insert into employees(name, department_id) values('Gena', 2);
insert into employees(name, department_id) values('Evgeniy', 2);

select * from employees e left join departments d on e.department_id = d.id;

select * from employees e right join departments d on e.department_id = d.id;

select * from departments d full join employees e on e.department_id = d.id;

select * from departments d cross join employees e;

select * from departments d left join employees e on e.department_id = d.id where department_id is null;

select * from employees e left join departments d on e.department_id = d.id;
select e.id, e.name, e.department_id, d.id, d.name from departments d right join employees e on e.department_id = d.id;

create table teens(
	name text,
	gender int
);

insert into teens(name, gender) values('Pavel', 1);
insert into teens(name, gender) values('Andrey', 1);
insert into teens(name, gender) values('Dmitry', 1);
insert into teens(name, gender) values('Evgeniy', 1);
insert into teens(name, gender) values('Olga', 0);
insert into teens(name, gender) values('Katya', 0);
insert into teens(name, gender) values('Dasha', 0);
insert into teens(name, gender) values('Veronika', 0);
insert into teens(name, gender) values('Liza', 0);

select n1.name, n2.name from teens n1 cross join teens n2 where (n1.gender + n2.gender) = 1;

