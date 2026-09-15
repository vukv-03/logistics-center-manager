-- ============================================================
-- LOGISTICS CENTER - MOCK DATA
-- ============================================================

SET FOREIGN_KEY_CHECKS = 0;

-- ------------------------------------------------------------
-- CLEAR EXISTING DATA
-- ------------------------------------------------------------

DELETE FROM outbound_order_item;
DELETE FROM inbound_delivery_item;
DELETE FROM outbound_order;
DELETE FROM inbound_delivery;
DELETE FROM product;
DELETE FROM customer;
DELETE FROM user;
DELETE FROM address;
DELETE FROM city;
DELETE FROM country;
DELETE FROM product_type;

-- ------------------------------------------------------------
-- COUNTRIES
-- ------------------------------------------------------------

INSERT INTO country (id, name) VALUES
(1, 'Serbia'),
(2, 'Croatia'),
(3, 'Hungary'),
(4, 'Austria'),
(5, 'Germany');

-- ------------------------------------------------------------
-- CITIES
-- ------------------------------------------------------------

INSERT INTO city (id, name, zip_code, country_id) VALUES
(1, 'Belgrade', 11000, 1),
(2, 'Novi Sad', 21000, 1),
(3, 'Nis', 18000, 1),
(4, 'Zagreb', 10000, 2),
(5, 'Budapest', 1000, 3),
(6, 'Vienna', 1010, 4),
(7, 'Munich', 80331, 5),
(8, 'Hamburg', 20095, 5),
(9, 'Subotica', 24000, 1),
(10, 'Kragujevac', 34000, 1);

-- ------------------------------------------------------------
-- ADDRESSES
-- ------------------------------------------------------------

INSERT INTO address (id, street, building_number, city_id) VALUES
(1, 'Knez Mihailova', 12, 1),
(2, 'Bulevar Despota Stefana', 45, 1),
(3, 'Vojvode Stepe', 101, 1),
(4, 'Bulevar Oslobodjenja', 23, 2),
(5, 'Futoska', 78, 2),
(6, 'Generala Milojka Lesjanina', 15, 3),
(7, 'Ilica', 55, 4),
(8, 'Vaci utca', 32, 5),
(9, 'Mariahilfer Strasse', 80, 6),
(10, 'Marienplatz', 7, 7),
(11, 'Reeperbahn', 21, 8),
(12, 'Korzo', 14, 9),
(13, 'Kralja Petra I', 33, 10),
(14, 'Nemanjina', 64, 1),
(15, 'Dunavska', 19, 2);

-- ------------------------------------------------------------
-- USERS
-- IDs 1-2 = admin/manager
-- IDs 3-8 = customers
-- ------------------------------------------------------------

INSERT INTO user (id, email, role) VALUES
(1, 'admin@logistics.com', 'ADMIN'),
(2, 'manager@logistics.com', 'MANAGER'),
(3, 'cocacola@example.com', 'CUSTOMER'),
(4, 'pepsi@example.com', 'CUSTOMER'),
(5, 'nestle@example.com', 'CUSTOMER'),
(6, 'unilever@example.com', 'CUSTOMER'),
(7, 'delhaize@example.com', 'CUSTOMER'),
(8, 'metalac@example.com', 'CUSTOMER');

-- ------------------------------------------------------------
-- CUSTOMERS
-- IMPORTANT: customer.id must match user.id
-- ------------------------------------------------------------

INSERT INTO customer (id, name) VALUES
(3, 'Coca Cola'),
(4, 'Pepsi'),
(5, 'Nestle'),
(6, 'Unilever'),
(7, 'Delhaize'),
(8, 'Metalac');

-- ------------------------------------------------------------
-- PRODUCT TYPES
-- ------------------------------------------------------------

INSERT INTO product_type (id, type) VALUES
(1, 'Beverages'),
(2, 'Food'),
(3, 'Cleaning Products'),
(4, 'Household Products'),
(5, 'Industrial Products');

-- ------------------------------------------------------------
-- PRODUCTS
-- ------------------------------------------------------------

INSERT INTO product
(id, name, description, measurement_unit, quantity_in_stock,
 amount_of_product, product_owner_id, product_type_id)
VALUES

(1, 'Coca Cola 0.5L',
 'Carbonated soft drink',
 'LITER',
 500,
 0.50,
 3,
 1),

(2, 'Coca Cola 2L',
 'Carbonated soft drink',
 'LITER',
 300,
 2.00,
 3,
 1),

(3, 'Fanta Orange',
 'Orange flavored soft drink',
 'LITER',
 250,
 2.00,
 3,
 1),

(4, 'Pepsi 0.5L',
 'Carbonated soft drink',
 'LITER',
 400,
 0.50,
 4,
 1),

(5, 'Pepsi 2L',
 'Carbonated soft drink',
 'LITER',
 200,
 2.00,
 4,
 1),

(6, 'Mineral Water',
 'Natural mineral water',
 'LITER',
 800,
 1.50,
 5,
 1),

(7, 'Orange Juice',
 '100 percent orange juice',
 'LITER',
 180,
 1.00,
 5,
 1),

(8, 'Chocolate Bar',
 'Milk chocolate bar',
 'GRAM',
 600,
 100.00,
 5,
 2),

(9, 'Corn Flakes',
 'Breakfast cereal',
 'GRAM',
 350,
 500.00,
 5,
 2),

(10, 'Pasta',
 'Durum wheat pasta',
 'GRAM',
 500,
 500.00,
 6,
 2),

(11, 'Rice',
 'Long grain rice',
 'KILOGRAM',
 200,
 1.00,
 6,
 2),

(12, 'Dishwashing Liquid',
 'Concentrated dishwashing detergent',
 'LITER',
 150,
 0.75,
 6,
 3),

(13, 'Laundry Detergent',
 'Universal laundry detergent',
 'KILOGRAM',
 120,
 3.00,
 7,
 3),

(14, 'Glass Cleaner',
 'Glass and window cleaner',
 'MILLILITER',
 200,
 750.00,
 7,
 3),

(15, 'Paper Towels',
 'Two-ply paper towels',
 'PIECE',
 450,
 1.00,
 7,
 4),

(16, 'Toilet Paper',
 'Three-ply toilet paper',
 'PIECE',
 700,
 1.00,
 7,
 4),

(17, 'Steel Plate',
 'Industrial steel plate',
 'KILOGRAM',
 1000,
 25.00,
 8,
 5),

(18, 'Steel Pipe',
 'Industrial steel pipe',
 'KILOGRAM',
 750,
 10.00,
 8,
 5),

(19, 'Aluminum Sheet',
 'Industrial aluminum sheet',
 'KILOGRAM',
 500,
 15.00,
 8,
 5),

(20, 'Copper Wire',
 'Industrial copper wire',
 'KILOGRAM',
 300,
 5.00,
 8,
 5);

-- ------------------------------------------------------------
-- INBOUND DELIVERIES
--
-- Completed = past
-- Processing = current/recent
-- Pending = future
-- ------------------------------------------------------------

INSERT INTO inbound_delivery
(id, created_at, arrival_date, docking_time, status, customer_delivery_id)
VALUES

(1, '2026-08-20 09:15:00', '2026-08-22', '08:00:00',
 'COMPLETED', 3),

(2, '2026-08-21 10:30:00', '2026-08-23', '09:30:00',
 'COMPLETED', 4),

(3, '2026-08-25 11:00:00', '2026-08-27', '11:00:00',
 'COMPLETED', 5),

(4, '2026-08-28 12:20:00', '2026-08-30', '13:30:00',
 'COMPLETED', 6),

(5, '2026-09-01 14:00:00', '2026-09-03', '15:00:00',
 'COMPLETED', 7),

(6, '2026-09-12 08:30:00', '2026-09-14', '09:00:00',
 'PROCESSING', 8),

(7, '2026-09-12 09:00:00', '2026-09-15', '10:30:00',
 'PROCESSING', 3),

(8, '2026-09-13 10:00:00', '2026-09-16', '12:00:00',
 'PROCESSING', 4),

(9, '2026-09-13 11:00:00', '2026-09-17', '08:30:00',
 'PENDING', 5),

(10, '2026-09-13 12:00:00', '2026-09-18', '10:00:00',
 'PENDING', 6),

(11, '2026-09-13 13:00:00', '2026-09-19', '11:30:00',
 'PENDING', 7),

(12, '2026-09-13 14:00:00', '2026-09-20', '14:00:00',
 'PENDING', 8);

-- ------------------------------------------------------------
-- INBOUND DELIVERY ITEMS
-- ------------------------------------------------------------

INSERT INTO inbound_delivery_item
(id, quantity, product_id, inbound_delivery_id)
VALUES

(1, 200, 1, 1),
(2, 100, 2, 1),

(3, 150, 4, 2),
(4, 100, 5, 2),

(5, 300, 6, 3),
(6, 100, 7, 3),
(7, 200, 8, 3),

(8, 100, 10, 4),
(9, 80, 11, 4),
(10, 50, 12, 4),

(11, 100, 13, 5),
(12, 150, 14, 5),
(13, 200, 15, 5),

(14, 100, 17, 6),
(15, 75, 18, 6),

(16, 150, 3, 7),
(17, 100, 6, 7),

(18, 100, 9, 8),
(19, 100, 10, 8),

(20, 200, 12, 9),
(21, 100, 14, 9),

(22, 150, 15, 10),
(23, 200, 16, 10),

(24, 100, 19, 11),
(25, 50, 20, 11),

(26, 100, 17, 12),
(27, 50, 18, 12);

-- ------------------------------------------------------------
-- OUTBOUND ORDERS
--
-- Completed = past
-- Processing = current/recent
-- Pending = future
-- ------------------------------------------------------------

INSERT INTO outbound_order
(id, created_at, shipping_date, shipping_time, status,
 customer_order_id, delivery_address_id)
VALUES

(1, '2026-08-25 08:30:00', '2026-08-28', '09:00:00',
 'COMPLETED', 3, 1),

(2, '2026-08-27 10:15:00', '2026-08-30', '10:30:00',
 'COMPLETED', 4, 4),

(3, '2026-08-29 12:00:00', '2026-09-02', '13:00:00',
 'COMPLETED', 5, 7),

(4, '2026-09-01 09:00:00', '2026-09-05', '08:30:00',
 'COMPLETED', 6, 9),

(5, '2026-09-03 11:30:00', '2026-09-07', '11:00:00',
 'COMPLETED', 7, 10),

(6, '2026-09-05 13:45:00', '2026-09-10', '14:30:00',
 'COMPLETED', 8, 13),

(7, '2026-09-12 08:00:00', '2026-09-14', '10:00:00',
 'PROCESSING', 3, 2),

(8, '2026-09-12 09:30:00', '2026-09-15', '11:30:00',
 'PROCESSING', 4, 5),

(9, '2026-09-13 10:00:00', '2026-09-16', '09:30:00',
 'PROCESSING', 5, 8),

(10, '2026-09-13 11:00:00', '2026-09-17', '13:00:00',
 'PENDING', 6, 11),

(11, '2026-09-13 12:00:00', '2026-09-18', '08:00:00',
 'PENDING', 7, 12),

(12, '2026-09-13 13:00:00', '2026-09-19', '10:30:00',
 'PENDING', 8, 14),

(13, '2026-09-13 14:00:00', '2026-09-20', '15:00:00',
 'PENDING', 3, 15);

-- ------------------------------------------------------------
-- OUTBOUND ORDER ITEMS
-- ------------------------------------------------------------

INSERT INTO outbound_order_item
(id, quantity, product_id, outbound_order_id)
VALUES

(1, 50, 1, 1),
(2, 30, 2, 1),

(3, 40, 4, 2),
(4, 25, 5, 2),

(5, 80, 6, 3),
(6, 30, 7, 3),
(7, 50, 8, 3),

(8, 30, 10, 4),
(9, 20, 11, 4),

(10, 30, 13, 5),
(11, 40, 15, 5),

(12, 100, 17, 6),
(13, 50, 18, 6),

(14, 40, 3, 7),
(15, 50, 6, 7),

(16, 30, 4, 8),
(17, 20, 5, 8),

(18, 50, 9, 9),
(19, 30, 10, 9),

(20, 20, 12, 10),
(21, 25, 14, 10),

(22, 50, 15, 11),
(23, 70, 16, 11),

(24, 40, 19, 12),
(25, 20, 20, 12),

(26, 30, 1, 13),
(27, 20, 2, 13);

-- ------------------------------------------------------------
-- RESET AUTO-INCREMENT COUNTERS
-- ------------------------------------------------------------

ALTER TABLE country AUTO_INCREMENT = 11;
ALTER TABLE city AUTO_INCREMENT = 16;
ALTER TABLE address AUTO_INCREMENT = 16;
ALTER TABLE user AUTO_INCREMENT = 9;
ALTER TABLE product_type AUTO_INCREMENT = 6;
ALTER TABLE product AUTO_INCREMENT = 21;
ALTER TABLE inbound_delivery AUTO_INCREMENT = 13;
ALTER TABLE inbound_delivery_item AUTO_INCREMENT = 28;
ALTER TABLE outbound_order AUTO_INCREMENT = 14;
ALTER TABLE outbound_order_item AUTO_INCREMENT = 28;

SET FOREIGN_KEY_CHECKS = 1;