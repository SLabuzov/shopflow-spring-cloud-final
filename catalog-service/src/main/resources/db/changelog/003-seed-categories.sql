--liquibase formatted sql

--changeset shopflow:003-seed-categories
-- 1. Вставка корневой категории
INSERT INTO categories (name, parent_id, description) VALUES
('Компьютерные комплектующие', NULL, 'Все основные компоненты для сборки и апгрейда персонального компьютера');

-- 2. Вставка категорий первого уровня (родитель – корневая категория)
INSERT INTO categories (name, parent_id, description) VALUES
('Процессоры', (SELECT id FROM categories WHERE name = 'Компьютерные комплектующие'), 'Центральные процессоры (CPU) для настольных ПК'),
('Материнские платы', (SELECT id FROM categories WHERE name = 'Компьютерные комплектующие'), 'Материнские платы для различных сокетов и чипсетов'),
('Видеокарты', (SELECT id FROM categories WHERE name = 'Компьютерные комплектующие'), 'Графические ускорители для игр и профессиональных задач'),
('Оперативная память', (SELECT id FROM categories WHERE name = 'Компьютерные комплектующие'), 'Модули ОЗУ DDR4 и DDR5'),
('Накопители', (SELECT id FROM categories WHERE name = 'Компьютерные комплектующие'), 'Устройства хранения данных: SSD и HDD'),
('Блоки питания', (SELECT id FROM categories WHERE name = 'Компьютерные комплектующие'), 'Блоки питания для стабильной работы всех компонентов'),
('Корпуса', (SELECT id FROM categories WHERE name = 'Компьютерные комплектующие'), 'Корпуса для ПК различных форм-факторов'),
('Системы охлаждения', (SELECT id FROM categories WHERE name = 'Компьютерные комплектующие'), 'Воздушные и жидкостные системы охлаждения');

-- 3. Вставка подкатегорий для накопителей (родитель – категория "Накопители")
INSERT INTO categories (name, parent_id, description) VALUES
('SSD', (SELECT id FROM categories WHERE name = 'Накопители'), 'Твердотельные накопители (NVMe, SATA)'),
('HDD', (SELECT id FROM categories WHERE name = 'Накопители'), 'Классические жёсткие диски');
