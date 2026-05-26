# ShopFlow

<p>
  <a href="https://openjdk.org/"><img src="https://img.shields.io/badge/Java-21-ED8B00?style=flat&logo=openjdk&logoColor=white" alt="Java 21"></a>
  <a href="https://spring.io/projects/spring-boot"><img src="https://img.shields.io/badge/Spring%20Boot-4.0.6-6DB33F?style=flat&logo=springboot&logoColor=white" alt="Spring Boot 4.0.6"></a>
  <a href="https://spring.io/projects/spring-cloud"><img src="https://img.shields.io/badge/Spring%20Cloud-2025.1.1-6DB33F?style=flat&logo=spring&logoColor=white" alt="Spring Cloud 2025.1.1"></a>  
  <a href="https://gradle.org/"><img src="https://img.shields.io/badge/Gradle-Build%20Tool-02303A?style=flat&logo=gradle&logoColor=white" alt="Gradle 9.4.1"></a>
  <a href="https://mapstruct.org/"><img src="https://img.shields.io/badge/MapStruct-Code%20Generator-A020F0?style=flat" alt="MapStruct Code Generator"></a>
  <a href="https://www.postgresql.org/"><img src="https://img.shields.io/badge/PostgreSQL-Database-4169E1?style=flat&logo=postgresql&logoColor=white" alt="PostgreSQL 17"></a>
  <a href="https://www.liquibase.org/"><img src="https://img.shields.io/badge/Liquibase-DB%20Migrations-2962FF?style=flat&logo=liquibase&logoColor=white" alt="Liquibase"></a>
  <a href="https://www.docker.com/"><img src="https://img.shields.io/badge/Docker%20Compose-Infrastructure-2496ED?style=flat&logo=docker&logoColor=white" alt="Docker Compose"></a>
  <a href="https://projectlombok.org/"><img src="https://img.shields.io/badge/Lombok-Annotation%20Processor-6DB33F?style=flat" alt="Lombok"></a>

</p>

Микросервисная e-commerce платформа — сквозной проект курса **«Spring Cloud in a Nutshell»**.

---

## Содержание

- [Технологический стек](#технологический-стек)
- [Модули проекта](#модули-проекта)
- [Инфраструктура (Docker)](#инфраструктура-docker)
- [REST API](#rest-api)
- [Сборка и запуск](#сборка-и-запуск)
- [Структура репозитория](#структура-репозитория)

---

## Технологический стек

| Компонент    | Версия / решение |
|--------------|------------------|
| Java         | 21               |
| Spring Boot  | 4.0.6            |
| Spring Cloud | 2025.1.1         |
| Gradle       | 9.4.1 (wrapper)  |
| PostgreSQL   | 17 (Alpine)      |
| MapStruct    | 1.6.3            |
| Lombok       | 1.18.32          |

---

## Модули проекта

### Gradle multi-module проект (`settings.gradle.kts`):

| Модуль                 | Порт | Описание        |
|------------------------|------|-----------------|
| `catalog-service`      | 8081 | Каталог товаров |
| `notification-service` | 8083 | Уведомления     |
| `order-service`        | 8082 | Заказы          |
| `payment-service`      | 8084 | Платежи         |

### Базы данных PostgreSQL

Создаются скриптом `infrastructure/init-databases.sql` при первом старте контейнера:

| База          | Сервис          |
|---------------|-----------------|
| `catalog_db`  | catalog-service |
| `order_db`    | order-service   |
| `payment_db`  | payment-service |
| `keycloak_db` | Keycloak        |

---

## Инфраструктура (Docker)

```bash
docker compose up -d
```

| Сервис     | URL / порт       | Назначение  |
|------------|------------------|-------------|
| PostgreSQL | `localhost:5432` | БД сервисов |

Остановка:

```bash
docker compose down
```

Данные PostgreSQL сохраняются в Docker volumes (`postgres-data`).

---

## REST API

### Каталог (`catalog-service`)

| Метод | Путь                                    | Доступ    | Описание                                                      |
|-------|-----------------------------------------|-----------|---------------------------------------------------------------|
| `GET` | `/api/v1/products`                      | Публичный | Список товаров (пагинация, фильтры `categoryId`, `available`) |
| `GET` | `/api/v1/products/{id}`                 | Публичный | Товар по ID                                                   |
| `GET` | `/api/v1/products/batch?id=id1,id2,...` | Публичный | Товары по списку IDs                                          |
| `GET` | `/api/v1/categories`                    | Публичный | Список категорий                                              |

### Заказы (`order-service`)

| Метод  | Путь                         | Доступ    | Описание            |
|--------|------------------------------|-----------|---------------------|
| `POST` | `/api/v1/orders`             | Публичный | Создание заказа     |
| `POST` | `/api/v1/orders/{id}/cancel` | Публичный | Отмена заказа по ID |
| `GET`  | `/api/v1/orders/{id}`        | Публичный | Заказ по ID         |
| `GET`  | `/api/v1/orders?customer=`   | Публичный | Заказы клиента      |

---

## Сборка и запуск

```bash
./gradlew build                           # собрать всё
./gradlew :catalog-service:bootRun        # запустить каталог
./gradlew :notification-service:bootRun   # запустить уведомления
./gradlew :order-service:bootRun          # запустить заказы
./gradlew :payment-service:bootRun        # запустить платежи
./gradlew test                            # все тесты
```

---

## Структура репозитория

```
ShopFlow/
├── catalog-service/              # Каталог
├── order-service/                # Заказы
├── payment-service/              # Платежи
├── notification-service/         # Уведомления
├── infrastructure/               # Скрипт инициализации БД
├── docker-compose.yaml           # Локальная инфраструктура
└── gradle/libs.versions.toml     # Версии зависимостей
```

---
