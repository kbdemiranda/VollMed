create table vollmed.consultas(
    id                  bigserial,
    medico_id           bigint      not null,
    paciente_id         bigint      not null,
    data                timestamptz not null,
    motivo_cancelamento text,

    constraint pk_consultas primary key (id),
    constraint fk_consultas_medico_id foreign key (medico_id) references vollmed.medicos (id),
    constraint fk_consultas_paciente_id foreign key (paciente_id) references vollmed.pacientes (id)
);