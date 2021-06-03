
create table favorite
(
    id serial not null
        constraint favorite_pkey
            primary key,
    ad_id integer,
    user_id integer
);

