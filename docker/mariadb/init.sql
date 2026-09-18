-- ============================================================
-- Script de inicializacao do banco de dados.
--
-- Executado automaticamente pelo MariaDB SOMENTE na primeira vez
-- que o container sobe com o volume vazio. Para recriar do zero:
--     docker compose down -v && docker compose up --build
--
-- O banco e o usuario ja sao criados pelas variaveis
-- MARIADB_DATABASE / MARIADB_USER do docker-compose.yml.
-- Aqui ficam apenas as tabelas (e dados iniciais, se precisar).
-- ============================================================

SET NAMES utf8mb4;

USE formulario;

-- Tabela de exemplo: substitua/expanda conforme o projeto evoluir.
CREATE TABLE IF NOT EXISTS pessoa (
    id        BIGINT       NOT NULL AUTO_INCREMENT,
    nome      VARCHAR(150) NOT NULL,
    email     VARCHAR(150) NOT NULL,
    criado_em TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uk_pessoa_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
