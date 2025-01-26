# Используем официальный образ PostgreSQL как базовый
FROM postgres:latest

# Устанавливаем переменные окружения
ENV POSTGRES_DB=spring_test
ENV POSTGRES_USER=myuser
ENV POSTGRES_PASSWORD=mypassword

# Открываем порт 5432 для подключений к PostgreSQL
EXPOSE 5432

# Точка монтирования для сохранения данных
VOLUME [ "/var/lib/postgresql/data" ]

# Команда запускается автоматически, используя entrypoint скрипт базового образа
CMD ["postgres"]

# wsl docker run --name my-postgres-container -e POSTGRES_PASSWORD=mypassword -d postgres
# wsl docker exec -it my-postgres-container psql -U postgres\ве\di\di