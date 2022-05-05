insert into roles(name) values('role 1');
insert into rules(name) values('rule 1');
insert into role_rules(roles_id, rules_id) values(4, 4);
insert into users(name, role_id) values('Pavel', 4);

insert into commentes(comment) values('Comment');
insert into attachs(attach) values('attach');
insert into item(name, users_id, attachs_id, comments_id) values('Item', 2, 4, 4);
insert into states(name, item_id) values('State', 4);
insert into category(name, item_id) values('Category', 4);