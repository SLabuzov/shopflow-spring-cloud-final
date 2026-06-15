--liquibase formatted sql

--changeset shopflow:001-create-payments
CREATE TABLE payments (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    order_id    UUID UNIQUE NOT NULL,
    amount      decimal(19,2) NOT NULL,
    status      VARCHAR(20) NOT NULL DEFAULT 'CREATED',
    created_at  TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at  TIMESTAMPTZ
);
