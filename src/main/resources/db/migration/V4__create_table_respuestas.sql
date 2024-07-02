create table respuestas(
    id bigint not null auto_increment,
    mensaje varchar(500) not null,
    topico_id bigint not null,
    fechaCreacion datetime not null,
    usuario_id bigint not null,

    primary key(id),

    constraint fk_respuestas_topico_id foreign key(topico_id) references topico(id),
    constraint fk_respuestas_autor_id foreign key(usuario_id) references usuario(id)
)