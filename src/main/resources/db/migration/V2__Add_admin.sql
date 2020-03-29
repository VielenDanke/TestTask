insert into users (user_id, username, password, email) values (1, 'admin', 'admin', 'admin@admin.ru');
insert into users (user_id, username, password, email) values (2, 'user', 'user', 'user@user.ru');
insert into authorities(user_id, authorities) values (1, 'ROLE_ADMIN');
insert into authorities(user_id, authorities) values (2, 'ROLE_USER')