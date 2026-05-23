--liquibase formatted sql

--changeset shopflow:004-seed-products
-- Процессоры
INSERT INTO products (name, description, price, category_id, available, created_at, updated_at) VALUES
('Intel Core i9-13900K',  '24 ядра (8 P-cores + 16 E-cores), 32 потока, до 5.8 ГГц, 36 МБ кэш L3, без кулера',  589.99, (SELECT id FROM categories WHERE name = 'Процессоры'), true, now(), NULL),
('AMD Ryzen 7 7800X3D',  '8 ядер, 16 потоков, до 5.0 ГГц, 96 МБ 3D V-Cache, AM5',  449.00, (SELECT id FROM categories WHERE name = 'Процессоры'), true, now(), NULL),
('Intel Core i5-13600KF',  '14 ядер (6 P-cores + 8 E-cores), 20 потоков, до 5.1 ГГц, 24 МБ кэш L3, без встроенной графики',  299.99, (SELECT id FROM categories WHERE name = 'Процессоры'), true, now(), NULL);

-- Материнские платы
INSERT INTO products (name, description, price, category_id, available, created_at, updated_at) VALUES
('ASUS ROG Strix Z790-E Gaming WiFi',  'LGA1700, Intel Z790, DDR5, PCIe 5.0, 4x M.2, 2.5Gb LAN, WiFi 6E, RGB',  479.99, (SELECT id FROM categories WHERE name = 'Материнские платы'), true, now(), NULL),
('MSI B650 Tomahawk WiFi',  'AM5, AMD B650, DDR5, PCIe 4.0, 3x M.2, 2.5Gb LAN, WiFi 6E',  219.99, (SELECT id FROM categories WHERE name = 'Материнские платы'), true, now(), NULL),
('Gigabyte B660 DS3H AX',  'LGA1700, Intel B660, DDR4, PCIe 4.0, 2x M.2, Gb LAN, WiFi 5',  139.99, (SELECT id FROM categories WHERE name = 'Материнские платы'), true, now(), NULL);

-- Видеокарты
INSERT INTO products (name, description, price, category_id, available, created_at, updated_at) VALUES
('NVIDIA GeForce RTX 4090 24GB',  '24 ГБ GDDR6X, 16384 ядер CUDA, поддержка DLSS 3, 450W TDP',  1599.99, (SELECT id FROM categories WHERE name = 'Видеокарты'), true, now(), NULL),
('AMD Radeon RX 7900 XTX 24GB',  '24 ГБ GDDR6, 6144 потоковых процессоров, поддержка FSR 3, 355W TDP',  999.99, (SELECT id FROM categories WHERE name = 'Видеокарты'), true, now(), NULL),
('NVIDIA GeForce RTX 4070 Ti 12GB',  '12 ГБ GDDR6X, 7680 ядер CUDA, поддержка DLSS 3, 285W TDP',  799.99, (SELECT id FROM categories WHERE name = 'Видеокарты'), true, now(), NULL);

-- Оперативная память
INSERT INTO products (name, description, price, category_id, available, created_at, updated_at) VALUES
('Corsair Vengeance 32GB (2x16GB) DDR5 6000MHz',  'CL36, 1.35V, AMD EXPO / Intel XMP 3.0, черный радиатор',  114.99, (SELECT id FROM categories WHERE name = 'Оперативная память'), true, now(), NULL),
('Kingston Fury Beast 16GB (1x16GB) DDR4 3200MHz',  'CL16, 1.35V, XMP 2.0, низкопрофильный радиатор',  44.99, (SELECT id FROM categories WHERE name = 'Оперативная память'), true, now(), NULL),
('G.Skill Trident Z5 RGB 64GB (2x32GB) DDR5 6400MHz',  'CL32, 1.40V, RGB-подсветка, поддержка Intel XMP 3.0',  249.99, (SELECT id FROM categories WHERE name = 'Оперативная память'), true, now(), NULL);

-- SSD
INSERT INTO products (name, description, price, category_id, available, created_at, updated_at) VALUES
('Samsung 990 Pro 1TB NVMe M.2',  'PCIe 4.0 x4, чтение до 7450 МБ/с, запись до 6900 МБ/с, контроллер Samsung Pascal',  119.99, (SELECT id FROM categories WHERE name = 'SSD'), true, now(), NULL),
('WD Black SN850X 2TB NVMe M.2',  'PCIe 4.0 x4, чтение до 7300 МБ/с, запись до 6600 МБ/с, с радиатором',  149.99, (SELECT id FROM categories WHERE name = 'SSD'), true, now(), NULL),
('Kingston KC3000 512GB NVMe M.2',  'PCIe 4.0 x4, чтение до 7000 МБ/с, запись до 3900 МБ/с, 800 TBW',  54.99, (SELECT id FROM categories WHERE name = 'SSD'), true, now(), NULL);

-- HDD
INSERT INTO products (name, description, price, category_id, available, created_at, updated_at) VALUES
('Seagate BarraCuda 2TB 3.5" SATA',  '7200 об/мин, 256 МБ кэш, скорость передачи до 210 МБ/с',  59.99, (SELECT id FROM categories WHERE name = 'HDD'), true, now(), NULL),
('Western Digital Blue 4TB 3.5" SATA',  '5400 об/мин, 256 МБ кэш, технология IntelliPower',  89.99, (SELECT id FROM categories WHERE name = 'HDD'), true, now(), NULL),
('Toshiba X300 6TB 3.5" SATA',  '7200 об/мин, 256 МБ кэш, для игр и рабочих станций',  139.99, (SELECT id FROM categories WHERE name = 'HDD'), true, now(), NULL);

-- Блоки питания
INSERT INTO products (name, description, price, category_id, available, created_at, updated_at) VALUES
('Corsair RM850x 850W 80 PLUS Gold',  'Полностью модульный, 135 мм вентилятор, японские конденсаторы, 10 лет гарантии',  139.99, (SELECT id FROM categories WHERE name = 'Блоки питания'), true, now(), NULL),
('Seasonic Focus GX-750 750W 80 PLUS Gold',  'Полностью модульный, 120 мм вентилятор с гибридным режимом, 10 лет гарантии',  119.99, (SELECT id FROM categories WHERE name = 'Блоки питания'), true, now(), NULL),
('be quiet! Dark Power 13 1000W 80 PLUS Titanium',  'Полностью модульный, 135 мм вентилятор Silent Wings, цифровое управление, 10 лет гарантии',  349.99, (SELECT id FROM categories WHERE name = 'Блоки питания'), true, now(), NULL);

-- Корпуса
INSERT INTO products (name, description, price, category_id, available, created_at, updated_at) VALUES
('Fractal Design Meshify 2 Black',  'ATX, сталь/стекло, 7 слотов, 2x USB-A 3.0, 1x USB-C, 3 вентилятора Dynamic X2 GP-14',  139.99, (SELECT id FROM categories WHERE name = 'Корпуса'), true, now(), NULL),
('Lian Li O11 Dynamic EVO White',  'Mid-Tower, двойное стекло, поддержка E-ATX, 4x USB 3.0, 1x USB-C, без вентиляторов',  159.99, (SELECT id FROM categories WHERE name = 'Корпуса'), true, now(), NULL),
('NZXT H5 Flow Black',  'ATX, перфорированная передняя панель, 2 вентилятора F120Q, поддержка до 360 мм радиатора',  94.99, (SELECT id FROM categories WHERE name = 'Корпуса'), true, now(), NULL);

-- Системы охлаждения
INSERT INTO products (name, description, price, category_id, available, created_at, updated_at) VALUES
('Noctua NH-D15',  'Двухбашенный воздушный кулер, 2 вентилятора NF-A15 140 мм, поддержка LGA1700/AM5',  99.99, (SELECT id FROM categories WHERE name = 'Системы охлаждения'), true, now(), NULL),
('Arctic Liquid Freezer II 360',  'Жидкостное охлаждение 360 мм, 3 вентилятора 120 мм, встроенный вентилятор на помпе VRM',  109.99, (SELECT id FROM categories WHERE name = 'Системы охлаждения'), true, now(), NULL),
('Cooler Master Hyper 212 Black Edition',  'Башенный кулер, 1 вентилятор 120 мм, 4 тепловые трубки, поддержка LGA1700/AM5',  44.99, (SELECT id FROM categories WHERE name = 'Системы охлаждения'), true, now(), NULL);
