
insert into user (nam_user, dat_creation, flg_enabled)
values ('João Almeida', '2018-06-13 15:45:00', 1);

insert into user (nam_user, dat_creation, flg_enabled)
values ('Francisco José', '2018-06-13 15:45:00', 1);

insert into user (nam_user, dat_creation, flg_enabled)
values ('Mariana Ribeiro', '2018-06-13 15:45:00', 1);

insert into category (nam_category, dat_creation, flg_enabled)
values ('ALIMENTACAO', '2018-06-13 15:45:00', 1);

insert into category (nam_category, dat_creation, flg_enabled)
values ('CASA', '2018-06-13 15:45:00', 1);

insert into category (nam_category, dat_creation, flg_enabled)
values ('EDUCACAO', '2018-06-13 15:45:00', 1);

insert into category (nam_category, dat_creation, flg_enabled)
values ('ELETRONICOS', '2018-06-13 15:45:00', 1);

insert into category (nam_category, dat_creation, flg_enabled)
values ('LAZER', '2018-06-13 15:45:00', 1);

insert into category (nam_category, dat_creation, flg_enabled)
values ('OUTROS', '2018-06-13 15:45:00', 1);

insert into category (nam_category, dat_creation, flg_enabled)
values ('SAUDE', '2018-06-13 15:45:00', 1);

insert into category (nam_category, dat_creation, flg_enabled)
values ('TRANSPORTE', '2018-06-13 15:45:00', 1);

insert into category (nam_category, dat_creation, flg_enabled)
values ('VESTUARIO', '2018-06-13 15:45:00', 1);

insert into category (nam_category, dat_creation, flg_enabled)
values ('VIAGEM', '2018-06-13 15:45:00', 1);

insert into entry (description, id_category, id_user, amount, dat_entry, dat_creation)
values ('Mc Donalds', 1, 1, 40.32, '2018-06-13', '2018-06-13 15:45:00');

insert into entry (description, id_category, id_user, amount, dat_entry, dat_creation)
values ('Colegio Visconde', 3, 1, 1399.99, '2018-06-13', '2018-06-13 15:45:00');

insert into entry (description, id_category, id_user, amount, dat_entry, dat_creation)
values ('FastShop', 4, 2, 399.99, '2018-06-13', '2018-06-13 15:45:00');

insert into entry (description, id_category, id_user, amount, dat_entry, dat_creation)
values ('Papelaria', 6, 2, 1.99, '2018-06-13', '2018-06-13 15:45:00');
