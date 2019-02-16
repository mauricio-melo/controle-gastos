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
    tipo_categoria VARCHAR(255) NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    data_lancamento DATETIME NOT NULL,
    data_criacao DATETIME NOT NULL,
    data_alteracao DATETIME NULL,
    id_usuario BIGINT(20) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;