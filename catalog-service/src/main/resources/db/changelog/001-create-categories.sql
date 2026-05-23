--liquibase formatted sql

--changeset shopflow:001-create-categories
CREATE TABLE categories (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name        VARCHAR(100) NOT NULL UNIQUE,
    parent_id   UUID REFERENCES categories(id),
    description VARCHAR(500)
);
