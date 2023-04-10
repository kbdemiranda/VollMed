create schema if not exists vollmed;

create table vollmed.medicos(
    id bigserial,
    nome text not null,
    email text not null,
    crm text not null,
    especialidade text not null,
    logradouro text not null,
    numero text,
    complemento text,
    bairro text not null,
    cidade text not null,
    uf text not null,
    cep text not null,

    constraint pk_medicos primary key (id)
);