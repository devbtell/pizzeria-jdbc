-- Crear base de datos
CREATE DATABASE IF NOT EXISTS pizzeria;
USE pizzeria;

-- Tabla pizza
CREATE TABLE pizza (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    ingredientes VARCHAR(255) NOT NULL,
    precio DECIMAL(5,2) NOT NULL
);

-- Tabla usuario
CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL
);

-- Tabla pedido
CREATE TABLE pedido (
    idpedido INT AUTO_INCREMENT PRIMARY KEY,
    idpizza INT,
    idusuario INT,
    cantidad INT NOT NULL,
    FOREIGN KEY (idpizza) REFERENCES pizza(id),
    FOREIGN KEY (idusuario) REFERENCES usuario(id)
);

-- Insertar datos en pizza
INSERT INTO pizza (nombre, ingredientes, precio) VALUES
('Margarita', 'Tomate, mozzarella, albahaca', 8.50),
('Barbacoa', 'Carne, salsa barbacoa, mozzarella', 10.00),
('Cuatro Quesos', 'Mozzarella, gorgonzola, parmesano, cheddar', 9.50);

-- Insertar datos en usuario
INSERT INTO usuario (nombre, apellido) VALUES
('Joshua', 'Layton'),
('Andres', 'Quintero'),
('Carlos', 'Parra');