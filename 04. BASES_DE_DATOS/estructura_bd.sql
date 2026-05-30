CREATE DATABASE IF NOT EXISTS datastock_db;
USE datastock_db;
-- Creo base de datos del proyecto
-- Ahora creo las tablas
CREATE TABLE rol (
    id_rol INT PRIMARY KEY,
    nombre_rol VARCHAR (30) NOT NULL
);
CREATE TABLE sitio (
    id_sitio INT PRIMARY KEY,
    nombre_sitio VARCHAR(50) NOT NULL,
    tipo_sitio VARCHAR(50)NOT NULL,
    direccion VARCHAR(150)
);
CREATE TABLE categoria (
    id_categoria INT PRIMARY KEY,
    nombre_categoria VARCHAR(50) NOT NULL
);
CREATE TABLE marca(
    id_marca INT PRIMARY KEY,
    nombre_marca VARCHAR(50) NOT NULL
);
CREATE TABLE usuario (
    id_usuario VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    celular VARCHAR(15),
    correo VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    fk_id_rol INT NOT NULL,
    fk_id_sitio INT NOT NULL,
    FOREIGN KEY (fk_id_rol) REFERENCES rol(id_rol),
    FOREIGN KEY (fk_id_sitio) REFERENCES sitio(id_sitio)


);
CREATE TABLE producto (
    id_producto INT PRIMARY KEY,
    nombre_producto VARCHAR(100) NOT NULL,
    presentacion VARCHAR(50),
    fk_id_categoria INT NOT NULL,
    fk_id_marca INT NOT NULL,
    FOREIGN KEY (fk_id_categoria) REFERENCES categoria(id_categoria),
    FOREIGN KEY (fk_id_marca) REFERENCES marca(id_marca)
);
CREATE TABLE inventario_actual(
    fk_id_producto INT,
    fk_id_sitio INT,
    stock_disponible INT DEFAULT 0,
    PRIMARY KEY (fk_id_sitio, fk_id_producto),
    FOREIGN KEY (fk_id_producto) REFERENCES producto(id_producto),
    FOREIGN KEY (fk_id_sitio) REFERENCES sitio(id_sitio)

);
CREATE TABLE lote_entrada (
    id_lote INT PRIMARY KEY AUTO_INCREMENT,
    fk_id_producto INT NOT NULL,
    fk_id_sitio INT NOT NULL,
    fecha_ingreso DATE NOT NULL,
    fecha_vencimiento DATE,
    cantidad_ingresada INT NOT NULL,
    factura_proveedor VARCHAR(50),
    FOREIGN KEY (fk_id_producto) REFERENCES producto(id_producto),
    FOREIGN KEY (fk_id_sitio) REFERENCES sitio(id_sitio)
);

CREATE TABLE historial_movimiento (
    id_movimiento INT PRIMARY KEY AUTO_INCREMENT,
    fk_id_producto INT NOT NULL,
    fk_id_sitio INT NOT NULL,
    fk_id_usuario VARCHAR(20) NOT NULL,
    tipo_movimiento VARCHAR(30) NOT NULL,
    cantidad INT NOT NULL,
    fecha_hora TIMESTAMP DEFAULT  CURRENT_TIMESTAMP,
    FOREIGN KEY (fk_id_producto) REFERENCES producto(id_producto),
    FOREIGN KEY (fk_id_sitio) REFERENCES  sitio(id_sitio),
    FOREIGN KEY (fk_id_usuario) REFERENCES usuario(id_usuario)

);