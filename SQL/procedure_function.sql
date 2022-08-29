create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace procedure delete_data_if_count_less_then(u_count integer)
    language 'plpgsql'
as $$
BEGIN
    if u_count > 0 then
        delete from products where count < u_count;
    end if;
end;
$$;

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 10, 80);
insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 5, 100);

call delete_data_if_count_less_then(6);

drop procedure delete_data_if_count_less_then(u_count integer);

delete from products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;

create or replace function delete_data_if_count_less_then(u_count integer)
returns void
language 'plpgsql'
as
    $$
    BEGIN
        if u_count > 0 then
            delete from products where count < u_count;
        end if;
end;
$$

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 10, 80);
insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 5, 100);

select delete_data_if_count_less_then(6);

delete from products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;