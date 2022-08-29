create table if not exists account (
    id serial primary key,
    name varchar,
    balance float
);

insert into account (name, balance) VALUES ('Pavel', 1000);
insert into account (name, balance) VALUES ('Olga', 500);
insert into account (name, balance) VALUES ('Andrey', 700);

begin transaction;
update account set balance = balance + 100 where id = 2;
update account set balance = balance - 100 where id = 1;

begin transaction;
select * from account;

commit;

select * from account;
commit;

delete from account;
ALTER SEQUENCE account_id_seq RESTART WITH 1;

insert into account (name, balance) VALUES ('Pavel', 1000);
insert into account (name, balance) VALUES ('Olga', 500);
insert into account (name, balance) VALUES ('Andrey', 700);

begin transaction isolation level repeatable read;
update account set balance = balance + 100 where id = 2;

begin transaction isolation level repeatable read;
update account set balance = balance + 100 where id = 2;

commit;

commit;

delete from account;
ALTER SEQUENCE account_id_seq RESTART WITH 1;

insert into account (name, balance) VALUES ('Pavel', 1000);
insert into account (name, balance) VALUES ('Olga', 500);
insert into account (name, balance) VALUES ('Andrey', 700);

begin transaction isolation level serializable;

begin transaction isolation level serializable;

select * from account;

select * from account:

update account set balance = balance + 100 where id = 1;

update account set balance = balance - 1000 where id = 1;

commit;





