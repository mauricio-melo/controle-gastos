CREATE TABLE usuario (
    id_usuario BIGINT AUTO_INCREMENT PRIMARY KEY ,
    nome VARCHAR(255) NOT NULL,
    data_criacao DATETIME NOT NULL,
    data_alteracao DATETIME NULL,
    flg_ativo BIT NOT NULL
);

CREATE TABLE categoria (
    id_categoria BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    data_criacao DATETIME NOT NULL,
    data_alteracao DATETIME NULL,
    flg_ativo BIT NOT NULL
);

CREATE TABLE lancamento (
    id_lancamento BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    id_categoria BIGINT NULL,
    valor DECIMAL(10,2) NOT NULL,
    data_lancamento DATE NOT NULL,
    data_criacao DATETIME NOT NULL,
    data_alteracao DATETIME NULL,
    id_usuario BIGINT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;