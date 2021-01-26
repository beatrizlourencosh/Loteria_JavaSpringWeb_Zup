create table usuario
(
    id_usuario integer      not null,
    nm_email   varchar(100) not null,

    constraint pk_usuario primary key (id_usuario), -- criando chave primaria
    constraint uq_email unique (nm_email)           -- evita e-mails duplicados
);

create table numero_sorteado
(
    id_numero_sorteado integer not null,
    id_usuario         integer not null,
    nr_sorteado        integer not null,

    constraint pk_numero_sorteado primary key (id_numero_sorteado),                 -- criando chave primaria
    constraint fk_usuario foreign key (id_usuario) references usuario (id_usuario), -- criando chave estrangeira do usuário
    constraint uq_numeros_sorteado unique (id_usuario, nr_sorteado)                 -- evita repetir o mesmo número para o mesmo usuário
)