# ShopFlow

<p>
  <a href="https://openjdk.org/"><img src="https://img.shields.io/badge/Java-21-ED8B00?style=flat&logo=openjdk&logoColor=white" alt="Java 21"></a>
  <a href="https://spring.io/projects/spring-boot"><img src="https://img.shields.io/badge/Spring%20Boot-4.0.6-6DB33F?style=flat&logo=springboot&logoColor=white" alt="Spring Boot 4.0.6"></a>
  <a href="https://gradle.org/"><img src="https://img.shields.io/badge/Gradle-9.4.1-02303A?style=flat&logo=gradle&logoColor=white" alt="Gradle 9.4.1"></a>
</p>

Микросервисная e-commerce платформа — сквозной проект курса **«Spring Cloud in a Nutshell»**.

---

## Содержание

- [Технологический стек](#технологический-стек)
- [Модули проекта](#модули-проекта)
- [Сборка и запуск](#сборка-и-запуск)
- [Структура репозитория](#структура-репозитория)

---

## Технологический стек

| Компонент   | Версия / решение |
|-------------|------------------|
| Java        | 21               |
| Spring Boot | 4.0.6            |
| Gradle      | 9.4.1 (wrapper)  |

---

## Модули проекта

| Модуль                 | Порт | Описание        |
|------------------------|------|-----------------|
| `catalog-service`      | 8081 | Каталог товаров |
| `notification-service` | 8083 | Уведомления     |
| `order-service`        | 8082 | Заказы          |
| `payment-service`      | 8084 | Платежи         |

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
└── gradle/libs.versions.toml     # Версии зависимостей
```

---
