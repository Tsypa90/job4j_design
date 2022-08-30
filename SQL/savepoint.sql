begin transaction;
select * from account;
insert into account (name, balance) values('Pavel', 100);
select * from account;
savepoint first;
update account set balance = 0 where id = 1;
drop table account;
select * from account;
rollback to first;
select * from account;
commit;

begin transaction;
select * from account;
savepoint first;
update account set balance = 0 where id = 1;
select * from account;
savepoint second;
delete from account;
select * from account;
rollback to second;
select * from account;
rollback to first;
select * from account;
commit;

begin transaction;
update account set balance = 0 where id = 1;
savepoint first;
delete from account;
savepoint second;
insert into account (name, balance) values('Olga', 1000);
select * from account;
rollback to first;
select * from account;
commit;
select * from account;

