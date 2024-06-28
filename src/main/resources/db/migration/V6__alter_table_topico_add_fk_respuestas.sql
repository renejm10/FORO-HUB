alter table topico
    add constraint fk_topico_respuestas_id
        foreign key (respuestas) references respuestas(id)