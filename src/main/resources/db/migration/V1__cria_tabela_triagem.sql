CREATE TABLE triagem (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         texto TEXT NOT NULL,
                         categoria VARCHAR(40) NOT NULL,
                         prioridade VARCHAR(10) NOT NULL,
                         criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);