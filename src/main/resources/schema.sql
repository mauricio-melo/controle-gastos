CREATE TABLE user (
  id_user                       BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  nam_user  	                VARCHAR(255)  NOT NULL,
  flg_enabled                   BIT           NOT NULL,
  dat_creation                  DATETIME      NOT NULL,
  dat_update                    DATETIME      NULL
);

CREATE TABLE category (
  id_category                   BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  nam_category	                VARCHAR(255)  NOT NULL,
  flg_enabled                   BIT           NOT NULL,
  dat_creation                  DATETIME      NOT NULL,
  dat_update                    DATETIME      NULL
);

CREATE TABLE entry (
  id_entry                      BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  id_category                   BIGINT(20)    NULL,
  id_user                       BIGINT(20)    NOT NULL,
  description	                VARCHAR(255)  NOT NULL,
  amount                        DECIMAL(10,2) NOT NULL,
  dat_entry                     DATE          NOT NULL,
  dat_creation                  DATETIME      NOT NULL,
  dat_update                    DATETIME      NULL,
  FOREIGN KEY (id_category)     REFERENCES category(id_category),
  FOREIGN KEY (id_user)         REFERENCES user(id_user)
);