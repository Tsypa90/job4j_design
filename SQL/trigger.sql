create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace function tax()
returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return new;
    end;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
execute procedure tax();

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 10, 80);
insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 5, 100);

drop trigger tax_trigger on products;
drop function tax();

create or replace function tax()
    returns trigger as
$$
BEGIN
    new.price = new.price + new.price * 0.2;
    return new;
end;
$$
    language 'plpgsql';

create trigger tax_trigger_before
    before insert on products
    for each row
execute procedure tax();

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 10, 80);
insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 5, 100);

drop trigger tax_trigger_before on products;
drop function tax();

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function history()
returns trigger as
$$
    BEGIN
        insert into history_of_price
        (name, price, date)
        values (new.name, new.price, now());
        return new;
    end;
$$
language 'plpgsql';

create trigger history_trigger
    before insert on products
    for each row
    execute procedure history();

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 10, 80);
insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 5, 100);