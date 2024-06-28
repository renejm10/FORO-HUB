create table topico(
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(500) not null,
    fechaCreacion datetime not null,
    status tinyint not null,
    autor bigint not null,
    curso bigint not null,
    respuestas bigint not null,

    primary key (id),

    constraint fk_topico_autor_id foreign key(autor) references usuario(id),
    constraint fk_topico_curso_id foreign key(curso) references curso(id)
);

