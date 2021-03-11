create table category
(
    id serial not null
        constraint category_pkey
            primary key,
    name varchar(255),
    photo varchar(255),
    icon varchar(255)
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

INSERT INTO public.category (id, name, photo,icon) VALUES (1, 'Clases particulares', 'assets/img/clases-particulares.jpg','lni lni-graduation');
INSERT INTO public.category (id, name, photo,icon) VALUES (2, 'Fotografía', 'assets/img/fotografos.jpg','lni lni-camera');
INSERT INTO public.category (id, name, photo,icon) VALUES (3, 'Mudanzas', 'assets/img/mudanzas.jpg','lni lni-delivery');
INSERT INTO public.category (id, name, photo,icon) VALUES (4, 'Belleza', 'assets/img/belleza.jpg','lni lni-heart');
INSERT INTO public.category (id, name, photo,icon) VALUES (5, 'Reformas', 'assets/img/reformas.jpg','lni lni-construction');
INSERT INTO public.category (id, name, photo,icon) VALUES (6, 'Mascotas', 'assets/img/mascotas.jpg','lni lni-bug');
INSERT INTO public.category (id, name, photo,icon) VALUES (7, 'Cuidados', 'assets/img/cuidadores.jpg','lni lni-first-aid');
INSERT INTO public.category (id, name, photo,icon) VALUES (8, 'Jardineria', 'assets/img/jardineria.jpg','lni lni-flower');
INSERT INTO public.category (id, name, photo,icon) VALUES (9, 'Electronica', 'assets/img/electronica.jpg','lni lni-laptop');
INSERT INTO public.category (id, name, photo,icon) VALUES (10, 'Modista', 'assets/img/modistas.jpg','lni lni-tshirt');
INSERT INTO public.category (id, name, photo,icon) VALUES (11, 'Motor', 'assets/img/motor.jpg','lni lni-car');
INSERT INTO public.category (id, name, photo,icon) VALUES (12, 'Idiomas', 'assets/img/idiomas.jpg','lni lni-flag');
INSERT INTO public.category (id, name, photo,icon) VALUES (13, 'Otros', 'assets/img/otros.jpg','lni lni-more');

INSERT INTO public.ad (id, availability, create_ad, description, status, title, user_id, category_id) VALUES (1, 'Mi disponibilidad son los Lunes y Miércoles entre las 20 y las 23 horas. Los domingos también podría pero consultar la franja con anterioridad.', '2021-03-09', 'Doy clases de matemáticas. Estoy licenciado en Matematicas y me ofrezco para ayudar hasta segundo de bachillerato', 'ACTIVE', 'Clases de matematicas online', 1, 1);

INSERT INTO public.ad (id, availability, create_ad, description, status, title, user_id, category_id) VALUES (2,'Mi disponibilidad son los Jueves y Viernes entre las 20 y las 23 horas. Los domingos también podría pero consultar la franja con anterioridad.', '2021-03-09', 'Doy clases de física. Estoy licenciado en Matematicas y me ofrezco para ayudar hasta segundo de bachillerato', 'ACTIVE', 'Clases de fisica online', 2, 1);

INSERT INTO public.ad (id, availability, create_ad, description, status, title, user_id, category_id) VALUES (3, 'Mi disponibilidad son los Sábados y Domingos entre las 20 y las 23 horas. Los lunes también podría pero consultar la franja con anterioridad.', '2021-03-09', 'Doy clases de física. Estoy licenciado en Matematicas y me ofrezco para ayudar hasta segundo de bachillerato', 'ACTIVE', 'Clases de Java online', 3, 1);

ALTER SEQUENCE ad_id_seq RESTART WITH 20;
ALTER SEQUENCE category_id_seq RESTART WITH 20;
