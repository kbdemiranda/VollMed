create table vollmed.pacientes(

    id bigserial,
    nome text not null,
    email text not null,
    telefone text not null,
    cpf text not null,
    logradouro text not null,
    numero text,
    complemento text,
    bairro text not null,
    cidade text not null,
    uf text not null,
    cep text not null,

    constraint pk_pacientes primary key (id)
);