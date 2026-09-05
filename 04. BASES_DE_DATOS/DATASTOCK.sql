CREATE TABLE rol(
    id_rol SERIAL NOT NULL,
    nombre_rol VARCHAR(30) NOT NULL,
    CONSTRAINT pk_rol PRIMARY KEY (id_rol)
);

COMMENT ON TABLE rol IS 'almacena los tipos de roles de los usuarios en el sistema';
COMMENT ON COLUMN rol.id_rol IS 'Identificador unico del rol';
COMMENT ON  COLUMN rol.nombre_rol IS 'Nombre descriptivo del rol';

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

