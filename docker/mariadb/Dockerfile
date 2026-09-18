FROM mariadb:11.4

# Todo .sql/.sh/.sql.gz dentro dessa pasta e executado automaticamente
# apenas na PRIMEIRA inicializacao (quando o volume de dados esta vazio).
COPY init.sql /docker-entrypoint-initdb.d/init.sql

# utf8mb4 para suportar acentos e emojis corretamente.
CMD ["--character-set-server=utf8mb4", "--collation-server=utf8mb4_unicode_ci"]
