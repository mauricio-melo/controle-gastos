CREATE TABLE usuario (
    id_usuario BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    data_criacao DATETIME NOT NULL,
    data_alteracao DATETIME NULL,
    flg_ativo TINYINT(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE lancamento (
    id_lancamento BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(255) NOT NULL,
    tipo_categoria VARCHAR(255) NULL,
    valor DECIMAL(10,2) NOT NULL,
    data_lancamento DATE NOT NULL,
    data_criacao DATETIME NOT NULL,
    data_alteracao DATETIME NULL,
    id_usuario BIGINT(20) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into usuario (nome, data_criacao, flg_ativo)
values ('João Almeida', '2018-12-27 15:45:00', 1);

insert into usuario (nome, data_criacao, flg_ativo)
values ('Francisco José', '2018-12-27 15:45:00', 1);

insert into usuario (nome, data_criacao, flg_ativo)
values ('Mariana Ribeiro', '2018-12-27 15:45:00', 1);

insert into controle_gastos.lancamento (descricao, tipo_categoria, valor, data_lancamento, data_criacao, id_usuario)
values ('Carrefour', 'ALIMENTACAO', 400.32,  '2018-12-27', '2018-12-27 15:45:00' , 1);

insert into controle_gastos.lancamento (descricao, tipo_categoria, valor, data_lancamento, data_criacao, id_usuario)
values ('Colegio Visconde', 'EDUCACAO', 1399.99,  '2018-12-27', '2018-12-27 15:45:00' , 1);

insert into controle_gastos.lancamento (descricao, tipo_categoria, valor, data_lancamento, data_criacao, id_usuario)
values ('FastShop', 'ELETRONICOS', 2400,  '2018-12-27', '2018-12-27 15:45:00' , 2);

insert into controle_gastos.lancamento (descricao, tipo_categoria, valor, data_lancamento, data_criacao, id_usuario)
values ('Picole do Zé', 'LAZER', 2.30,  '2018-12-27', '2018-12-27 15:45:00' , 2);

insert into controle_gastos.lancamento (descricao, tipo_categoria, valor, data_lancamento, data_criacao, id_usuario)
values ('Autopeças Cristal', 'TRANSPORTE', 450.39,  '2018-12-27', '2018-12-27 15:45:00' , 3);

insert into controle_gastos.lancamento (descricao, tipo_categoria, valor, data_lancamento, data_criacao, id_usuario)
values ('Riachuelo', 'VESTUARIO', 139.87,  '2018-12-27', '2018-12-27 15:45:00' , 3);

