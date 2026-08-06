-- FRANCHISES

INSERT INTO franchise (name)
VALUES
('Franquicia Centro'),
('Franquicia Norte');

-- BRANCHES

INSERT INTO branch (name, franchise_id)
VALUES
('Bogotá', 1),
('Medellín', 1),
('Barranquilla', 2),
('Cartagena', 2);

-- PRODUCTS

INSERT INTO product (name, stock, branch_id)
VALUES
('Mouse Logitech', 35, 1),
('Teclado Mecánico', 18, 1),
('Monitor Samsung 27"', 12, 1),

('Mouse Logitech', 25, 2),
('Portátil Lenovo', 9, 2),
('Base Refrigerante', 14, 2),

('Impresora HP', 21, 3),
('Resma Carta', 90, 3),
('Tóner HP', 16, 3),

('Silla Ergonómica', 7, 4),
('Escritorio', 5, 4),
('Monitor LG', 13, 4);