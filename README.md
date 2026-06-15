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
  <a href="https://kafka.apache.org/"><img src="https://img.shields.io/badge/Apache%20Kafka-Message%20broker-231F20?style=flat&logo=apachekafka&logoColor=white" alt="Apache Kafka 4.0"></a>
</p>

Микросервисная e-commerce платформа — сквозной проект курса **«Spring Cloud in a Nutshell»**.

---

## Содержание

1. [Технологический стек](#1-технологический-стек)
2. [Модули проекта](#2-модули-проекта)
3. [Инфраструктура (Docker)](#3-инфраструктура-docker)
4. [REST API](#4-rest-api)
5. [Сборка и запуск](#5-сборка-и-запуск)
6. [Структура репозитория](#6-структура-репозитория)
7. [Service Discovery: что это и зачем](#7-service-discovery-что-это-и-зачем)
8. [Config Server: что это и зачем](#8-config-server-что-это-и-зачем)
9. [Событийная модель и Saga](#9-событийная-модель-и-saga)

---

## 1. Технологический стек

| Компонент    | Версия / решение |
|--------------|------------------|
| Java         | 21               |
| Spring Boot  | 4.0.6            |
| Spring Cloud | 2025.1.1         |
| Gradle       | 9.4.1 (wrapper)  |
| PostgreSQL   | 17 (Alpine)      |
| MapStruct    | 1.6.3            |
| Lombok       | 1.18.32          |
| Kafka        | 4.0.0            |

---

## 2. Модули проекта

### Gradle multi-module проект (`settings.gradle.kts`):

| Модуль                 | Порт | Описание                                           |
|------------------------|------|----------------------------------------------------|
| `config-server`        | 8888 | Сервис конфигурации                                |
| `discovery-server`     | 8761 | Eureka Server — регистрация и обнаружение сервисов |
| `catalog-service`      | 8081 | Каталог товаров                                    |
| `notification-service` | 8083 | Уведомления                                        |
| `order-service`        | 8082 | Заказы                                             |
| `payment-service`      | 8084 | Платежи                                            |

### Базы данных PostgreSQL

Создаются скриптом `infrastructure/init-databases.sql` при первом старте контейнера:

| База          | Сервис          |
|---------------|-----------------|
| `catalog_db`  | catalog-service |
| `order_db`    | order-service   |
| `payment_db`  | payment-service |
| `keycloak_db` | Keycloak        |

---

## 3. Инфраструктура (Docker)

```bash
docker compose up -d
```

| Сервис     | URL / порт            | Назначение                           |
|------------|-----------------------|--------------------------------------|
| PostgreSQL | `localhost:5432`      | БД сервисов                          |
| Kafka      | `localhost:9092`      | Брокер (EXTERNAL listener для хоста) |
| Kafka UI   | http://localhost:9080 | Просмотр топиков и сообщений         |

Остановка:

```bash
docker compose down
```

Данные PostgreSQL сохраняются в Docker volumes (`postgres-data`).

---

## 4. REST API

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

## 5. Сборка и запуск

```bash
./gradlew build                           # собрать всё
./gradlew :discovery-server:bootRun       # запустить Service Discovery
./gradlew :config-server:bootRun          # запустить сервис конфигурации
./gradlew :catalog-service:bootRun        # запустить каталог
./gradlew :notification-service:bootRun   # запустить уведомления
./gradlew :order-service:bootRun          # запустить заказы
./gradlew :payment-service:bootRun        # запустить платежи
./gradlew test                            # все тесты
```

---

## 6. Структура репозитория

```
ShopFlow/
├── config-server/                # Сервис конфигурации
├── discovery-server/             # Service Discovery
├── catalog-service/              # Каталог
├── order-service/                # Заказы
├── payment-service/              # Платежи
├── notification-service/         # Уведомления
├── infrastructure/               # Скрипт инициализации БД
├── shopflow-common/              # DTO, события, исключения
├── docker-compose.yaml           # Локальная инфраструктура
└── gradle/libs.versions.toml     # Версии зависимостей
```

---

## 7. Service Discovery: что это и зачем

### 7.1. Определение

> **Service Discovery** — механизм автоматического обнаружения сетевых
> адресов инстансов сервисов. Каждый сервис при старте **регистрируется**
> в реестре, а потребители **запрашивают** реестр, чтобы узнать актуальные
> адреса.

### 7.2. Компоненты Service Discovery

```
┌───────────────────────────────────────────────────────────────┐
│                    Service Discovery                          │
│                                                               │
│  ┌─────────────────┐                                          │
│  │  Service        │  Центральный реестр:                     │
│  │  Registry       │  хранит пары «имя → [адрес1, адрес2]»    │ 
│  └─────────────────┘                                          │
│           ▲                          │                        │
│           │ регистрация              │ запрос                 │
│           │ heartbeat                │ адресов                │
│           │ deregistration           ▼                        │
│  ┌──────────────────┐       ┌─────────────────┐               │
│  │  Service         │       │  Service        │               │
│  │  Provider        │       │  Consumer       │               │
│  │  (регистрируется)│       │  (ищет адреса)  │               │
│  └──────────────────┘       └─────────────────┘               │
│                                                               │
└───────────────────────────────────────────────────────────────┘
```

**Три роли:**

1. **Service Registry** — хранит реестр `serviceName → [instance1, instance2, ...]`.
2. **Service Provider** — при старте регистрируется, периодически шлёт heartbeat.
3. **Service Consumer** — запрашивает реестр, получает список адресов, выбирает один.

**Существуют два основных подхода к реализации Service Discovery:**
1. **Server-side Discovery** - Потребитель отправляет запрос к **балансировщику** (роутер), который сам знает адреса и перенаправляет запрос.
2. **Client-side Discovery** - Потребитель **сам** запрашивает реестр, получает список адресов и **сам** выбирает инстанс

### 7.3. Netflix Eureka: архитектура

#### 7.3.1. Что такое Eureka

**Netflix Eureka** — реестр сервисов, разработанный Netflix и включённый
в экосистему Spring Cloud Netflix. Состоит из двух компонентов:

| Компонент     | Роль                                          | Spring-аннотация         |
|---------------|-----------------------------------------------|--------------------------|
| Eureka Server | Центральный реестр, хранит адреса             | `@EnableEurekaServer`    |
| Eureka Client | Библиотека в каждом сервисе: регистрация + SD | `@EnableDiscoveryClient` |

В нашем проекте все сервисы — Java + Spring Boot. Мы выбираем
**client-side discovery** с **Netflix Eureka**, потому что:

1. Полная интеграция с Spring Cloud.
2. OpenFeign автоматически использует Eureka для резолва имён.
3. Встроенный Spring Cloud LoadBalancer для балансировки.

#### 7.3.2. Протокол взаимодействия

Eureka использует **REST API** для всех операций:

| Операция       | HTTP-метод | Эндпоинт                              | Описание                     |
|----------------|------------|---------------------------------------|------------------------------|
| Register       | POST       | `/eureka/apps/{appName}`              | Регистрация нового инстанса  |
| Heartbeat      | PUT        | `/eureka/apps/{appName}/{instanceId}` | Подтверждение «я жив»        |
| Deregister     | DELETE     | `/eureka/apps/{appName}/{instanceId}` | Снятие с регистрации         |
| Fetch registry | GET        | `/eureka/apps`                        | Получить весь реестр         |
| Fetch delta    | GET        | `/eureka/apps/delta`                  | Получить изменения           |
| Fetch app      | GET        | `/eureka/apps/{appName}`              | Инстансы конкретного сервиса |
| Instance info  | GET        | `/eureka/apps/{appName}/{instanceId}` | Информация об инстансе       |

#### 7.3.3. Временные параметры по умолчанию

```
┌──────────────────────────────────────────────────────────────┐
│                    Временная шкала Eureka                    │
│                                                              │
│  Клиент:                                                     │
│  ├── Регистрация:        при старте                          │
│  ├── Heartbeat:          каждые 30 секунд                    │
│  ├── Fetch registry:     каждые 30 секунд                    │
│  └── Shutdown:           deregister (graceful)               │
│                                                              │
│  Сервер:                                                     │
│  ├── Eviction check:     каждые 60 секунд                    │
│  ├── Eviction threshold: 90 секунд без heartbeat             │
│  └── Self-preservation:  если < 85% heartbeat-ов пришло      │
│                                                              │
└──────────────────────────────────────────────────────────────┘
```

> **Важно** — между падением инстанса и его удалением из реестра может пройти
> до **90 секунд** (при дефолтных настройках). Это осознанный компромисс
> между скоростью обнаружения и стабильностью (защита от ложных срабатываний).

---

## 8. Config Server: что это и зачем

### 8.1. Определение

> **Spring Cloud Config** — это инструмент для централизованного управления
> конфигурацией распределённых систем. Вместо того чтобы хранить одинаковые
> параметры в каждом микросервисе, мы выносим их в единое хранилище и
> раздаём через HTTP API.

### 8.2. Введение — проблема разбросанной конфигурации

#### 8.2.1. Типичная ситуация

Представьте: у вас пять микросервисов. Каждый содержит `application.yml`
с параметрами подключения к базе данных, адресом Kafka, настройками Eureka
и endpoint-ами Actuator. Конфигурации **частично совпадают** — и это
создаёт ряд проблем.

```
┌─────────────────┐   ┌─────────────────┐   ┌─────────────────┐
│ catalog-service │   │  order-service  │   │ payment-service │
│                 │   │                 │   │                 │
│ application.yml │   │ application.yml │   │ application.yml │
│ ┌─────────────┐ │   │ ┌─────────────┐ │   │ ┌─────────────┐ │
│ │ DB URL      │ │   │ │ DB URL      │ │   │ │ DB URL      │ │
│ │ Kafka addr  │ │   │ │ Kafka addr  │ │   │ │ Kafka addr  │ │
│ │ Eureka URL  │ │   │ │ Eureka URL  │ │   │ │ Eureka URL  │ │
│ │ Actuator    │ │   │ │ Actuator    │ │   │ │ Actuator    │ │
│ └─────────────┘ │   │ └─────────────┘ │   │ └─────────────┘ │
└─────────────────┘   └─────────────────┘   └─────────────────┘
         ▲                     ▲                     ▲
         │                     │                     │
         └─────── Дублирование конфигурации ─────────┘
```

#### 8.2.2. Какие проблемы возникают

| Проблема             | Описание                                                    |
|----------------------|-------------------------------------------------------------|
| **Дублирование**     | Одни и те же строки повторяются в каждом `application.yml`  |
| **Рассинхронизация** | Обновили Kafka-адрес в трёх сервисах, но забыли о четвёртом |
| **Нет аудита**       | Непонятно, кто и когда менял конфигурацию                   |
| **Перезапуск**       | Любое изменение требует пересборки и передеплоя             |
| **Секреты**          | Пароли в открытом виде лежат в Git-репозитории сервиса      |

#### 8.2.3. Что мы хотим

- **Единый источник правды** для всей конфигурации
- **Разделение** общих и сервис-специфичных настроек
- **Профили** для разных окружений (`dev`, `staging`, `prod`)
- **Динамическое обновление** без перезапуска сервисов
- **Шифрование** секретов (пароли, токены, ключи)

> **Ответ** — Spring Cloud Config Server решает все перечисленные задачи.

### 8.3. Spring Cloud Config: архитектура

#### 8.3.1. Высокоуровневая схема

```
┌─────────────────────────────────────────────────────────┐
│                    Config Backend                       │
│                                                         │
│   ┌──────────┐   ┌──────────┐   ┌──────────────────┐    │
│   │   Git    │   │  Native  │   │   HashiCorp      │    │
│   │  Repo    │   │  (Files) │   │     Vault        │    │
│   └────┬─────┘   └────┬─────┘   └────────┬─────────┘    │
│        │              │                  │              │
│        └──────────────┼──────────────────┘              │
│                       │                                 │
└───────────────────────┼─────────────────────────────────┘
                        │
                        ▼
          ┌──────────────────────────┐
          │   Config Server          │
          │   (port 8888)            │
          │                          │
          │  REST API:               │
          │  /{app}/{profile}        │
          │  /{app}/{profile}/{label}│
          │                          │
          └────────────┬─────────────┘
                       │
          ┌────────────┼────────────┐
          │            │            │
          ▼            ▼            ▼
   ┌────────────┐ ┌────────────┐ ┌────────────┐
   │  catalog-  │ │  order-    │ │  payment-  │
   │  service   │ │  service   │ │  service   │
   │            │ │            │ │            │
   │  Config    │ │  Config    │ │  Config    │
   │  Client    │ │  Client    │ │  Client    │
   └────────────┘ └────────────┘ └────────────┘
```

### 8.3.2. Как работает взаимодействие

1. **Config Server** запускается и подключается к бэкенду (Git, файловая система или Vault).
2. Config Server **регистрируется** в Eureka (опционально).
3. **Config Client** (каждый микросервис) при старте обращается к Config Server по HTTP: `GET http://config-server:8888/{application}/{profile}`.
4. Config Server **находит** конфигурацию для данного приложения и профиля и возвращает JSON/YAML.
5. Config Client **объединяет** полученные свойства со своими локальными и создаёт `Environment`.

---

## 9. Событийная модель и Saga

### 9.1. Kafka-топики

| Топик                                     | Издатель        | Потребители                             |
|-------------------------------------------|-----------------|-----------------------------------------|
| `order-events`                            | order-service   | payment-service, notification-service   |
| `payment-events`                          | payment-service | order-service, notification-service     |
| `order-events.DLT` / `payment-events.DLT` | —               | Dead Letter Queue при ошибках обработки |

### 9.2. Доменные события (`shopflow-common`)

| Событие                 | Поля (кратко)                               | Когда            |
|-------------------------|---------------------------------------------|------------------|
| `OrderCreatedEvent`     | orderId, customerId, totalAmount, createdAt | Заказ создан     |
| `OrderCancelledEvent`   | orderId, customerId, reason, cancelledAt    | Компенсация Saga |
| `PaymentCompletedEvent` | paymentId, orderId, amount, completedAt     | Оплата успешна   |
| `PaymentFailedEvent`    | paymentId, orderId, reason, failedAt        | Оплата отклонена |

### 9.3. Статусы заказа

`CREATED` → `PAID` (при успешной оплате) или `CANCELLED` (при `PaymentFailedEvent`).

---
