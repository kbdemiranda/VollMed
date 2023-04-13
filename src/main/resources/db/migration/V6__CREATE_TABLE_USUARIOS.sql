create table vollmed.usuarios(

    id bigserial,
    login text not null,
    senha text not null,

    constraint pk_usuarios primary key (id)
);