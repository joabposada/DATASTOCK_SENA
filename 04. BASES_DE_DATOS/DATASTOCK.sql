CREATE TABLE rol(
    id_rol SERIAL NOT NULL,
    nombre_rol VARCHAR(30) NOT NULL,
    CONSTRAINT pk_rol PRIMARY KEY (id_rol)
);

COMMENT ON TABLE rol IS 'almacena los tipos de roles de los usuarios en el sistema';
COMMENT ON COLUMN rol.id_rol IS 'Identificador unico del rol';
COMMENT ON COLUMN rol.nombre_rol IS 'Nombre descriptivo del rol';

CREATE TABLE usuario(
    id_usuario SERIAL NOT NULL,
    numero_documento VARCHAR (11) unique NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    celular VARCHAR(15) NOT NULL ,
    correo VARCHAR(100) unique  NOT NULL ,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT pk_usuario PRIMARY KEY (id_usuario)
);

COMMENT ON TABLE usuario IS 'Identificación de los usuarios en el sistema';
COMMENT ON COLUMN usuario.id_usuario IS 'Identificador unico del usuario';
COMMENT ON COLUMN usuario.numero_documento IS 'Numero de documento del usuario';
COMMENT ON COLUMN usuario.nombre IS 'Nombre del usuario';
COMMENT ON COLUMN usuario.apellido IS 'Apellido del usuario';
COMMENT ON COLUMN usuario.celular IS 'Numero telefonico del usuario';
COMMENT ON COLUMN usuario.correo IS 'Correo del usuario';
COMMENT ON COLUMN usuario.password IS 'Contraseña del usuario';

CREATE TABLE categoria(
    id_categoria SERIAL NOT NULL,
    nombre_categoria VARCHAR(50) NOT NULL ,
    CONSTRAINT pk_categoria PRIMARY KEY (id_categoria)
);

COMMENT ON TABLE categoria IS 'Catalogo de los productos';
COMMENT ON COLUMN categoria.id_categoria IS 'Identificador unico de la categoria';
COMMENT ON COLUMN categoria.nombre_categoria IS 'Nombre de la categoria del producto';

CREATE TABLE marca(
    id_marca SERIAL NOT NULL,
    nombre_marca VARCHAR(50) NOT NULL ,
    CONSTRAINT pk_marca PRIMARY KEY (id_marca)
);

COMMENT ON TABLE marca IS 'Marca pertenenciente al producto';
COMMENT ON COLUMN marca.id_marca IS 'Identificador de la marca';
COMMENT ON COLUMN marca.nombre_marca IS 'Nombre de la marca';

CREATE TABLE sitio(
    id_sitio SERIAL NOT NULL,
    nombre_sitio VARCHAR(100) NOT NULL,
    tipo_sitio VARCHAR(50) NOT NULL,
    direccion VARCHAR(150) NOT NULL,
    CONSTRAINT pk_sitio PRIMARY KEY (id_sitio)
);

COMMENT ON TABLE sitio IS 'Datos del sitio';
COMMENT ON COLUMN sitio.id_sitio IS 'Identificador del sitio';
COMMENT ON COLUMN sitio.nombre_sitio IS 'Nombre del lugar';
COMMENT ON COLUMN sitio.tipo_sitio IS 'Infraesttructura del lugar';
COMMENT ON COLUMN sitio.direccion IS 'Direccion del lugar';

CREATE TABLE producto(
    id_producto SERIAL NOT NULL,
    nombre_producto VARCHAR(100) NOT NULL,
    presentacion VARCHAR(50) NOT NULL,
    id_categoria INTEGER NOT NULL,
    id_marca INTEGER NOT NULL,
    CONSTRAINT pk_producto PRIMARY KEY (id_producto),
    CONSTRAINT fk_producto_categoria FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria),
    CONSTRAINT fk_producto_marca FOREIGN KEY (id_marca) REFERENCES marca(id_marca)
);

COMMENT ON TABLE producto IS 'Almacena la información del producto';
COMMENT ON COLUMN producto.id_producto IS 'Identificador unico del producto';
COMMENT ON COLUMN producto.nombre_producto IS 'Nombre del producto';
COMMENT ON COLUMN producto.presentacion IS 'Presentacion fisica del producto';
COMMENT ON COLUMN producto.id_categoria IS 'Llave foranea que relaciona el producto con su categoria';
COMMENT ON COLUMN producto.id_marca IS 'Llave foranea que relaciona el producto con su marca';

CREATE TABLE lote_entrada(
    id_lote SERIAL NOT NULL,
    id_producto INTEGER NOT NULL,
    id_sitio INTEGER NOT NULL,
    fecha_ingreso DATE NOT NULL,
    fecha_vencimiento DATE NOT NULL,
    cantidad_ingresada INTEGER NOT NULL,
    factura_proveedor VARCHAR(50) NOT NULL,
    CONSTRAINT pk_lote_entrada PRIMARY KEY (id_lote),
    CONSTRAINT fk_lote_producto FOREIGN KEY (id_producto) REFERENCES producto(id_producto),
    CONSTRAINT fk_lote_sitio FOREIGN KEY (id_sitio) REFERENCES sitio(id_sitio)
);

COMMENT ON TABLE lote_entrada IS 'Registro de entrada de lotes de productos';
COMMENT ON COLUMN lote_entrada.id_lote IS 'Identificador unico del lote';
COMMENT ON COLUMN lote_entrada.id_producto IS 'Llave foranea del producto ingresado';
COMMENT ON COLUMN lote_entrada.id_sitio IS 'Llave foranea del sitio donde ingresa';
COMMENT ON COLUMN lote_entrada.fecha_ingreso IS 'Fecha de ingreso del lote al sitio';
COMMENT ON COLUMN lote_entrada.fecha_vencimiento IS 'Fecha de caducidad del lote';
COMMENT ON COLUMN lote_entrada.cantidad_ingresada IS 'Cantidad de unidades que ingresaron en el lote';
COMMENT ON COLUMN lote_entrada.factura_proveedor IS 'Numero de factura del proveedor';

CREATE TABLE inventario_actual(
    id_producto INTEGER NOT NULL,
    id_sitio INTEGER NOT NULL,
    stock_disponible INTEGER NOT NULL,
    CONSTRAINT pk_inventario_actual PRIMARY KEY (id_producto, id_sitio),
    CONSTRAINT fk_inventario_producto FOREIGN KEY (id_producto) REFERENCES producto(id_producto),
    CONSTRAINT fk_inventario_sitio FOREIGN KEY (id_sitio) REFERENCES sitio(id_sitio)
);

COMMENT ON TABLE inventario_actual IS 'Tabla puente que almacena el stock actual por sitio';
COMMENT ON COLUMN inventario_actual.id_producto IS 'Llave foranea del producto en inventario';
COMMENT ON COLUMN inventario_actual.id_sitio IS 'Llave foranea del sitio donde esta el producto';
COMMENT ON COLUMN inventario_actual.stock_disponible IS 'Cantidad actual disponible del producto en el sitio';

CREATE TABLE historial_rol_usuario(
    id_usuario INTEGER NOT NULL,
    id_rol INTEGER NOT NULL,
    fecha_asignacion DATE NOT NULL,
    estado VARCHAR(15) NOT NULL,
    CONSTRAINT pk_historial_rol_usuario PRIMARY KEY (id_usuario, id_rol),
    CONSTRAINT fk_historial_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    CONSTRAINT fk_historial_rol FOREIGN KEY (id_rol) REFERENCES rol(id_rol)
);

COMMENT ON TABLE historial_rol_usuario IS 'Tabla puente que relaciona roles y usuarios';
COMMENT ON COLUMN historial_rol_usuario.id_usuario IS 'Llave foranea del usuario';
COMMENT ON COLUMN historial_rol_usuario.id_rol IS 'Llave foranea del rol asignado';
COMMENT ON COLUMN historial_rol_usuario.fecha_asignacion IS 'Fecha en la que se le asigno el rol al usuario';
COMMENT ON COLUMN historial_rol_usuario.estado IS 'Estado actual del rol para ese usuario';

CREATE TABLE historial_movimiento(
    id_movimiento SERIAL NOT NULL,
    id_producto INTEGER NOT NULL,
    id_sitio INTEGER NOT NULL,
    id_sitio_destino INTEGER,
    id_usuario INTEGER NOT NULL,
    tipo_movimiento VARCHAR(30) NOT NULL,
    cantidad INTEGER NOT NULL,
    fecha_hora TIMESTAMP NOT NULL,
    CONSTRAINT pk_historial_movimiento PRIMARY KEY (id_movimiento),
    CONSTRAINT fk_movimiento_producto FOREIGN KEY (id_producto) REFERENCES producto(id_producto),
    CONSTRAINT fk_movimiento_sitio_origen FOREIGN KEY (id_sitio) REFERENCES sitio(id_sitio),
    CONSTRAINT fk_movimiento_sitio_destino FOREIGN KEY (id_sitio_destino) REFERENCES sitio(id_sitio),
    CONSTRAINT fk_movimiento_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

COMMENT ON TABLE historial_movimiento IS 'Registro de transacciones de inventario';
COMMENT ON COLUMN historial_movimiento.id_movimiento IS 'Identificador unico del movimiento';
COMMENT ON COLUMN historial_movimiento.id_producto IS 'Llave foranea del producto movido';
COMMENT ON COLUMN historial_movimiento.id_sitio IS 'Llave foranea del sitio de origen del movimiento';
COMMENT ON COLUMN historial_movimiento.id_sitio_destino IS 'Llave foranea del sitio destino (opcional, null si es entrada o salida simple)';
COMMENT ON COLUMN historial_movimiento.id_usuario IS 'Llave foranea del usuario que realizo el movimiento';
COMMENT ON COLUMN historial_movimiento.tipo_movimiento IS 'Tipo de movimiento (Entrada, Salida, Traslado)';
COMMENT ON COLUMN historial_movimiento.cantidad IS 'Cantidad de producto movido';
COMMENT ON COLUMN historial_movimiento.fecha_hora IS 'Fecha y hora exacta en la que ocurrio el movimiento';

INSERT INTO rol (nombre_rol) VALUES
('Administrador'), ('Almacenista'), ('Supervisor'), ('Cajero'), ('Auditor'), ('Rol Obsoleto');

INSERT INTO usuario (numero_documento, nombre, apellido, celular, correo, password) VALUES
('1010101010', 'Joab', 'Posada', '3001112233', 'joab@correo.com', 'hash123'),
('2020202020', 'Dayana', 'Gomez', '3102223344', 'dayana@correo.com', 'hash456'),
('3030303030', 'Thomas', 'Perez', '3203334455', 'thomas@correo.com', 'hash789'),
('4040404040', 'Hector', 'Ramirez', '3154445566', 'hector@correo.com', 'hash012'),
('5050505050', 'Miguel', 'Lopez', '3185556677', 'miguel@correo.com', 'hash345');

INSERT INTO categoria (nombre_categoria) VALUES
('Aseo'), ('Lácteos'), ('Carnes'), ('Bebidas'), ('Snacks'), ('Categoría de Prueba');

INSERT INTO marca (nombre_marca) VALUES
('Ariel'), ('Alpina'), ('Zenú'), ('Postobón'), ('Margarita'), ('Marca Fantasma');

INSERT INTO sitio (nombre_sitio, tipo_sitio, direccion) VALUES
('Bodega Principal', 'Bodega', 'Calle 10 # 5-20, Madrid'),
('Bodega Sur', 'Bodega', 'Carrera 15 # 8-45, Mosquera'),
('Cuarto Frío 1', 'Refrigeración', 'Calle 10 # 5-20, Madrid'),
('Tienda Centro', 'Punto de Venta', 'Carrera 7 # 12-30, Bogotá'),
('Tienda Quiriguá', 'Punto de Venta', 'Transversal 91 # 80-50, Bogotá');

INSERT INTO producto (nombre_producto, presentacion, id_categoria, id_marca) VALUES
('Jabón en polvo', 'Bolsa 1kg', 1, 1),
('Leche Deslactosada', 'Caja 1 Litro', 2, 2),
('Salchicha Manguera', 'Paquete 500g', 3, 3),
('Gaseosa Manzana', 'Botella 2.5 Litros', 4, 4),
('Papas de Limón', 'Paquete 150g', 5, 5);

INSERT INTO lote_entrada (id_producto, id_sitio, fecha_ingreso, fecha_vencimiento, cantidad_ingresada, factura_proveedor) VALUES
(1, 1, '2026-08-01', '2028-08-01', 500, 'FAC-1001'),
(2, 3, '2026-09-01', '2026-10-15', 200, 'FAC-1002'),
(3, 3, '2026-09-05', '2026-11-20', 150, 'FAC-1003'),
(4, 1, '2026-08-15', '2027-02-15', 300, 'FAC-1004'),
(5, 2, '2026-09-06', '2027-01-10', 400, 'FAC-1005'),
(5, 2, '2026-09-07', '2027-01-10', 50, 'FAC-ERROR');

INSERT INTO inventario_actual (id_producto, id_sitio, stock_disponible) VALUES
(1, 1, 500),
(2, 3, 200),
(3, 3, 150),
(4, 4, 100),
(5, 5, 120);

INSERT INTO historial_rol_usuario (id_usuario, id_rol, fecha_asignacion, estado) VALUES
(1, 1, '2026-01-10', 'Activo'),
(2, 2, '2026-02-15', 'Activo'),
(3, 3, '2026-03-20', 'Activo'),
(4, 4, '2026-04-25', 'Inactivo'),
(5, 5, '2026-05-30', 'Activo'),
(5, 6, '2026-06-01', 'Inactivo');

INSERT INTO historial_movimiento (id_producto, id_sitio, id_sitio_destino, id_usuario, tipo_movimiento, cantidad, fecha_hora) VALUES
(1, 1, NULL, 1, 'Entrada inicial', 500, '2026-08-01 08:30:00'),
(2, 1, 3, 2, 'Traslado a frío', 200, '2026-09-01 09:15:00'),
(3, 3, NULL, 2, 'Entrada a frío', 150, '2026-09-05 10:20:00'),
(4, 1, 4, 3, 'Traslado a tienda', 100, '2026-08-16 14:00:00'),
(5, 2, 5, 3, 'Traslado a tienda', 120, '2026-09-06 16:45:00'),
(5, 2, NULL, 4, 'Movimiento Erroneo', 50, '2026-09-07 10:00:00');

SELECT p.nombre_producto, p.presentacion, c.nombre_categoria
FROM producto p
INNER JOIN categoria c ON p.id_categoria = c.id_categoria;

SELECT p.nombre_producto, m.nombre_marca
FROM producto p
INNER JOIN marca m ON p.id_marca = m.id_marca;

SELECT pr.nombre_producto, s.nombre_sitio, i.stock_disponible
FROM inventario_actual i
INNER JOIN producto pr ON i.id_producto = pr.id_producto
INNER JOIN sitio s ON i.id_sitio = s.id_sitio;

SELECT u.nombre, u.apellido, r.nombre_rol, h.estado
FROM historial_rol_usuario h
INNER JOIN usuario u ON h.id_usuario = u.id_usuario
INNER JOIN rol r ON h.id_rol = r.id_rol
WHERE h.estado = 'Activo';

SELECT hm.tipo_movimiento, pr.nombre_producto, s.nombre_sitio AS origen, hm.cantidad
FROM historial_movimiento hm
INNER JOIN producto pr ON hm.id_producto = pr.id_producto
INNER JOIN sitio s ON hm.id_sitio = s.id_sitio;

SELECT nombre_producto, presentacion
FROM producto
WHERE id_categoria = (SELECT id_categoria FROM categoria WHERE nombre_categoria = 'Aseo');

SELECT stock_disponible, id_sitio
FROM inventario_actual
WHERE id_producto = (SELECT id_producto FROM producto WHERE nombre_producto = 'Leche Deslactosada');

SELECT id_producto, stock_disponible
FROM inventario_actual
WHERE stock_disponible = (SELECT MAX(stock_disponible) FROM inventario_actual);

SELECT id_rol, fecha_asignacion
FROM historial_rol_usuario
WHERE id_usuario = (SELECT id_usuario FROM usuario WHERE numero_documento = '1010101010');

SELECT nombre_sitio, direccion
FROM sitio
WHERE id_sitio IN (SELECT id_sitio FROM lote_entrada WHERE fecha_ingreso >= '2026-09-01');

UPDATE usuario
SET celular = '3009998877'
WHERE numero_documento = '1010101010';

UPDATE producto
SET presentacion = 'Paquete 600g Familiar'
WHERE id_producto = 3;

UPDATE inventario_actual
SET stock_disponible = stock_disponible + 10
WHERE id_producto = 1 AND id_sitio = 1;

UPDATE historial_rol_usuario
SET estado = 'Inactivo'
WHERE id_usuario = 3;

UPDATE categoria
SET nombre_categoria = 'Mecatos y Snacks'
WHERE id_categoria = 5;

DELETE FROM historial_movimiento
WHERE id_movimiento = 6;

DELETE FROM lote_entrada
WHERE factura_proveedor = 'FAC-ERROR';

DELETE FROM historial_rol_usuario
WHERE id_usuario = 5 AND id_rol = 6;

DELETE FROM categoria
WHERE nombre_categoria = 'Categoría de Prueba';

DELETE FROM marca
WHERE nombre_marca = 'Marca Fantasma';
