create table school (
	id serial primary key,
	name text,
	surname text,
	age Integer
);
insert into school (name, surname, age) values('Pavel', 'Tsyurupa', 31);
select * from school;
update school set name = 'Olga';
select * from school;
delete from school;
select * from school;