insert into users (user_id, username, password, email) values (100000, 'admin', 'admin', 'admin@admin.ru');
insert into users (user_id, username, password, email) values (100001, 'user', 'user', 'user@user.ru');
insert into authorities(user_id, authorities) values (100000, 'ROLE_ADMIN');
insert into authorities(user_id, authorities) values (100001, 'ROLE_USER')