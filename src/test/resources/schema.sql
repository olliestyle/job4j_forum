create table if not exists posts (
    id serial primary key,
    name varchar(2000),
    description text,
    created timestamp without time zone not null default now()
);

CREATE TABLE if not exists authorities (
    id serial primary key,
    authority VARCHAR(50) NOT NULL unique
);

CREATE TABLE if not exists users (
    id serial primary key,
    username VARCHAR(50) NOT NULL unique,
    email VARCHAR(50) NOT NULL unique,
    password VARCHAR(100) NOT NULL,
    enabled boolean default true,
    authority_id int not null references authorities(id)
);

insert into authorities (authority) values ('ROLE_USER') on conflict do nothing ;
insert into authorities (authority) values ('ROLE_ADMIN') on conflict do nothing ;

insert into users (username, email, password, enabled, authority_id) VALUES
('admin', 'admin@mail.ru', '$2a$10$lUDAW/3P5JWDXA3O6Lb85OYX7EDGEAfNsjBYq7NfM82X1ot.KKu66', true,
 (select id from authorities where authority = 'ROLE_ADMIN'));