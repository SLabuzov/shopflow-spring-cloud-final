--liquibase formatted sql

--changeset shopflow:001-create-orders
CREATE TABLE orders (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    customer_id UUID NOT NULL,
    status      VARCHAR(20) NOT NULL DEFAULT 'CREATED',
    created_at  TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at  TIMESTAMPTZ
);

CREATE TABLE order_items (
    id             UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    order_id       UUID NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
    product_id     UUID NOT NULL,
    product_name   VARCHAR(255) NOT NULL,
    price_snapshot NUMERIC(12,2) NOT NULL,
    quantity       INT NOT NULL CHECK (quantity > 0)
);

CREATE INDEX idx_orders_customer ON orders(customer_id);
CREATE INDEX idx_order_items_order ON order_items(order_id);
