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