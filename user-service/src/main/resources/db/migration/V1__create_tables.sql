create table profile
(
    id serial not null
        constraint profile_pkey
            primary key,
    balance_hour integer,
    city varchar(255),
    created_at date,
    email varchar(255) UNIQUE,
    name varchar(255),
    password varchar(255),
    pending_transaction boolean,
    phone varchar(255),
    status varchar(255)
);

alter table profile owner to "user";

create table roles
(
    id serial not null
        constraint roles_pkey
            primary key,
    name varchar(20)
);

alter table roles owner to "user";

create table user_roles
(
    user_id integer not null
        constraint fk4uqp647kq7duk82q3bmobcxuw
            references profile,
    role_id integer not null
        constraint fkh8ciramu9cc9q3qcqiv4ue8a6
            references roles,
    constraint user_roles_pkey
        primary key (user_id, role_id)
);

alter table user_roles owner to "user";


INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

INSERT INTO public.profile (id, balance_hour, city, created_at, email, name, password, pending_transaction, phone, status) VALUES (1, 20, 'Madrid', '2021-03-10', 'nerea@nerea.com', 'Nerea Garcia', '$2a$10$Q2TPWDpys9RianK8JUQBV.2uFi4GCg0LKal/TD4kEVB7P9yzlcDzK', false, '616261626', 'ACTIVE');
INSERT INTO public.profile (id, balance_hour, city, created_at, email, name, password, pending_transaction, phone, status) VALUES (2, 20, 'Madrid', '2021-03-10', 'sergio@sergio.com', 'Sergio Fernandez', '$2a$10$Q2TPWDpys9RianK8JUQBV.2uFi4GCg0LKal/TD4kEVB7P9yzlcDzK', false, '655555555', 'ACTIVE');
INSERT INTO public.profile (id, balance_hour, city, created_at, email, name, password, pending_transaction, phone, status) VALUES (3, 20, 'Madrid', '2021-03-10', 'mar@mar.com', 'Maria del Mar', '$2a$10$Q2TPWDpys9RianK8JUQBV.2uFi4GCg0LKal/TD4kEVB7P9yzlcDzK', false, '644444444', 'ACTIVE');

INSERT INTO public.user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO public.user_roles (user_id, role_id) VALUES (2, 1);
INSERT INTO public.user_roles (user_id, role_id) VALUES (3, 1);

ALTER SEQUENCE profile_id_seq RESTART WITH 10;
ALTER SEQUENCE roles_id_seq RESTART WITH 10;
