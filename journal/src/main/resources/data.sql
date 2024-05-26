insert into lesson_type (id, type, price)
values (1, 'individual', 800);
insert into lesson_type (id, type, price)
values (2, 'group', 350);

-- SELECT setval ('lesson_type_seq', (SELECT MAX(id) FROM lesson_type));
