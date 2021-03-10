create table category
(
    id serial not null
        constraint category_pkey
            primary key,
    name varchar(255),
    photo varchar(255)
);

alter table category owner to ad;

create table ad
(
    id serial not null
        constraint ad_pkey
            primary key,
    availability varchar(2500),
    create_ad date,
    description varchar(5000),
    status varchar(255),
    title varchar(255),
    user_id integer,
    category_id integer
        constraint fkb4xjnvgyd7go1ehcnjgg7usqi
            references category
);

alter table ad owner to ad;

INSERT INTO public.category (id, name, photo) VALUES (1, 'Clases particulares', 'assets/img/clases-particulares.jpg');
INSERT INTO public.category (id, name, photo) VALUES (2, 'Fotografos', 'assets/img/fotografos.jpg');
INSERT INTO public.category (id, name, photo) VALUES (3, 'Mudanzas', 'assets/img/mudanzas.jpg');
INSERT INTO public.category (id, name, photo) VALUES (4, 'Belleza', 'assets/img/belleza.jpg');
INSERT INTO public.category (id, name, photo) VALUES (5, 'Reformas', 'assets/img/reformas.jpg');
INSERT INTO public.category (id, name, photo) VALUES (6, 'Mascotas', 'assets/img/mascotas.jpg');
INSERT INTO public.category (id, name, photo) VALUES (7, 'Cuidadores', 'assets/img/cuidadores.jpg');

INSERT INTO public.ad (id, availability, create_ad, description, status, title, user_id, category_id) VALUES (1, 'Mi disponibilidad son los Lunes y Miércoles entre las 20 y las 23 horas. Los domingos también podría pero consultar la franja con anterioridad.', '2021-03-09', 'Doy clases de matemáticas. Estoy licenciado en Matematicas y me ofrezco para ayudar hasta segundo de bachillerato', 'ACTIVE', 'Clases de matematicas online', 1, 1);

INSERT INTO public.ad (id, availability, create_ad, description, status, title, user_id, category_id) VALUES (2,'Mi disponibilidad son los Jueves y Viernes entre las 20 y las 23 horas. Los domingos también podría pero consultar la franja con anterioridad.', '2021-03-09', 'Doy clases de física. Estoy licenciado en Matematicas y me ofrezco para ayudar hasta segundo de bachillerato', 'ACTIVE', 'Clases de fisica online', 2, 1);

INSERT INTO public.ad (id, availability, create_ad, description, status, title, user_id, category_id) VALUES (3, 'Mi disponibilidad son los Sábados y Domingos entre las 20 y las 23 horas. Los lunes también podría pero consultar la franja con anterioridad.', '2021-03-09', 'Doy clases de física. Estoy licenciado en Matematicas y me ofrezco para ayudar hasta segundo de bachillerato', 'ACTIVE', 'Clases de Java online', 3, 1);

ALTER SEQUENCE ad_id_seq RESTART WITH 20;
ALTER SEQUENCE category_id_seq RESTART WITH 20;



