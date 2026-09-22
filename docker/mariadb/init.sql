-- Executado apenas na primeira vez que o volume e criado.
-- Aqui so criamos os bancos; as tabelas ficam por conta do Hibernate (ddl-auto=update).

SET NAMES utf8mb4;

-- Um banco por microsservico (adicione uma linha por servico)
CREATE DATABASE IF NOT EXISTS `formulario`
    CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
-- CREATE DATABASE IF NOT EXISTS `outro_servico`
--     CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Permissoes do usuario da aplicacao (criado pelo docker-compose)
GRANT ALL PRIVILEGES ON `formulario`.* TO 'formulario'@'%';
-- GRANT ALL PRIVILEGES ON `outro_servico`.* TO 'formulario'@'%';
FLUSH PRIVILEGES;
