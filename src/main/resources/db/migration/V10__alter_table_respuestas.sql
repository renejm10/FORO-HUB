alter table respuestas
    change topico topico_id bigint not null;

alter table respuestas
    change autor autor_id bigint not null;