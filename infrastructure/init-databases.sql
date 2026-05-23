-- Создаем отдельные базы данных для каждого сервиса.
-- Этот скрипт выполняется один раз при первом запуске контейнера PostgreSQL.
CREATE DATABASE catalog_db;
CREATE DATABASE order_db;
CREATE DATABASE payment_db;
CREATE DATABASE keycloak_db;
