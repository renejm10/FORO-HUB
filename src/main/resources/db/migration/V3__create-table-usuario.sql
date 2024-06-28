create table usuario(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    email varchar(100) not null,
    contraseña varchar(300) not null,
    perfil bigint not null,

    primary key(id),

    constraint fk_usuario_perfil_id foreign key (perfil) references perfil(id)
)