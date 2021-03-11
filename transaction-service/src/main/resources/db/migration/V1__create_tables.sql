create table transaction
(
    id serial not null
        constraint transaction_pkey
            primary key,
    amount integer,
    description varchar(1000),
    origin_user_id integer,
    receiver_user_id integer,
    status integer
);

alter table transaction owner to transaction;

