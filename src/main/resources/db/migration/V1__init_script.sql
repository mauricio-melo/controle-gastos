CREATE TABLE usuario (
    id_usuario BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    data_criacao DATETIME NOT NULL,
    data_alteracao DATETIME NULL,
    flg_ativo TINYINT(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE categoria (
    id_categoria BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    data_criacao DATETIME NOT NULL,
    data_alteracao DATETIME NULL,
    flg_ativo TINYINT(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE lancamento (
    id_lancamento BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(255) NOT NULL,
    id_categoria BIGINT(20) NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    data_lancamento DATE NOT NULL,
    data_criacao DATETIME NOT NULL,
    data_alteracao DATETIME NULL,
    id_usuario BIGINT(20) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into usuario (nome, data_criacao, flg_ativo)
values ('João Almeida', '2018-12-27 15:45:00', 1);

insert into usuario (nome, data_criacao, flg_ativo)
values ('Francisco José', '2018-12-27 15:45:00', 1);

insert into usuario (nome, data_criacao, flg_ativo)
values ('Mariana Ribeiro', '2018-12-27 15:45:00', 1);

insert into categoria (nome, data_criacao, flg_ativo)
values ('ALIMENTACAO', '2018-12-27 15:45:00', 1);

insert into categoria (nome, data_criacao, flg_ativo)
values ('CASA', '2018-12-27 15:45:00', 1);

insert into categoria (nome, data_criacao, flg_ativo)
values ('EDUCACAO', '2018-12-27 15:45:00', 1);

insert into categoria (nome, data_criacao, flg_ativo)
values ('ELETRONICOS', '2018-12-27 15:45:00', 1);

insert into categoria (nome, data_criacao, flg_ativo)
values ('LAZER', '2018-12-27 15:45:00', 1);

insert into categoria (nome, data_criacao, flg_ativo)
values ('OUTROS', '2018-12-27 15:45:00', 1);

insert into categoria (nome, data_criacao, flg_ativo)
values ('SAUDE', '2018-12-27 15:45:00', 1);

insert into categoria (nome, data_criacao, flg_ativo)
values ('TRANSPORTE', '2018-12-27 15:45:00', 1);

insert into categoria (nome, data_criacao, flg_ativo)
values ('VESTUARIO', '2018-12-27 15:45:00', 1);

insert into categoria (nome, data_criacao, flg_ativo)
values ('VIAGEM', '2018-12-27 15:45:00', 1);

insert into lancamento (descricao, id_categoria, valor, data_lancamento, data_criacao, id_usuario)
values ('Carrefour', 1, 400.32,  '2018-12-27', '2018-12-27 15:45:00' , 1);

insert into lancamento (descricao, id_categoria, valor, data_lancamento, data_criacao, id_usuario)
values ('Colegio Visconde', 3, 1399.99,  '2018-12-27', '2018-12-27 15:45:00' , 1);

insert into lancamento (descricao, id_categoria, valor, data_lancamento, data_criacao, id_usuario)
values ('FastShop', 4, 2400,  '2018-12-27', '2018-12-27 15:45:00' , 2);

insert into lancamento (descricao, id_categoria, valor, data_lancamento, data_criacao, id_usuario)
values ('Picole do Zé', 5, 2.30,  '2018-12-27', '2018-12-27 15:45:00' , 2);

