-- DELETE CASCADE: elimina en cascada, es decir si eliminas una fila de la tabla independiente(externa) tambien se eliminaran 
--                 las filas de la tabla dependiente (interna) que la estaban usando
-- UPDATE CASCADE: actualiza en cascada, es decir si actualizas el PK de una fila de la tabla independiente(externa) tambien se 
--                 actualizará estas referencias en las filas de la tabla dependiente (interna) que las necesita para su definicion.
--
-- NOTA: 
-- 1.    es comun modificar todos los campos de la tabla independiente (externa) excepto el PK, mientras no modifiquemos la llave,
--       los cambios no afectaran en absoluto a las tablas dependiente (internas) por lo que en estos casos UPDATE CASCADE no seria 
--       no seria necesario.
-- 2.    estas restricciones se añaden en la tabla dependiente al definir la FK que hace referencia a la tabla independiente.

DROP DATABASE IF EXISTS ShoesForMen;
CREATE DATABASE  ShoesForMen;
USE ShoesForMen;


CREATE TABLE distrito(
cod_distrito    char(4),
descripcion     varchar(25) NOT NULL,
PRIMARY KEY (cod_distrito),
UNIQUE INDEX distrito_unicos (descripcion ASC)
);

CREATE TABLE estado(
cod_estado     char(1),
descripcion    varchar(25) NOT NULL,
PRIMARY KEY(cod_estado),
UNIQUE INDEX estado_unicos (descripcion ASC)
);

CREATE TABLE moneda(
cod_moneda     char(4),
descripcion    varchar(15) NOT NULL,
PRIMARY KEY(cod_moneda),
UNIQUE INDEX moneda_unicos (descripcion ASC)
);

CREATE TABLE talla(
cod_talla      char(4),
numero_talla   double NOT NULL,
PRIMARY KEY (cod_talla),
UNIQUE INDEX talla_unicos (numero_talla ASC)
);

CREATE TABLE color(
cod_color      char(4),
nombre_color   varchar(25) NOT NULL,
PRIMARY KEY (cod_color),
UNIQUE INDEX color_unicos (nombre_color ASC)
);

CREATE TABLE categoria(
cod_categoria   char(4),
descripcion     varchar(25) NOT NULL,
PRIMARY KEY (cod_categoria),
UNIQUE INDEX categoria_unicos (descripcion ASC)
);

CREATE TABLE marca(
cod_marca      char(7),
nombre_marca   varchar(25) NOT NULL,
PRIMARY KEY (cod_marca),
UNIQUE INDEX marca_unicos (nombre_marca ASC)
);

CREATE TABLE modelo(
cod_modelo     char(7),
cod_marca      char(7),
cod_categoria  char(4),
nombre_modelo  varchar(25) NOT NULL,
precio_compra  double NOT NULL,
precio_venta   double NOT NULL,
PRIMARY KEY (cod_modelo),
UNIQUE INDEX modelo_unicos (nombre_modelo ASC),
FOREIGN KEY (cod_marca) references marca(cod_marca) ON DELETE CASCADE,
FOREIGN KEY (cod_categoria) references categoria(cod_categoria) ON DELETE CASCADE
);

CREATE TABLE rol(
cod_rol         char(4),
nombre          varchar(20),
PRIMARY KEY (cod_rol),
UNIQUE INDEX nombre_unico (nombre ASC)
);

CREATE TABLE empleado(
cod_empleado    char(7),
cod_distrito    char(4),
cod_estado      char(1),
nombre          varchar(25) NOT NULL,-- CHECK (nombre != "") ,
apellidos       varchar(25) NOT NULL,
dni             char(8) NOT NULL,
direccion       varchar(45),
telefono        varchar(12),
email           varchar(45),
usuario         varchar(25) NOT NULL,
contrasena      varchar(100) NOT NULL,
PRIMARY KEY (cod_empleado),
UNIQUE INDEX empleado_unicos (dni ASC, email ASC, usuario ASC),
FOREIGN KEY (cod_distrito) references distrito(cod_distrito) ON DELETE CASCADE,
FOREIGN KEY (cod_estado) references estado(cod_estado) ON DELETE CASCADE
);

CREATE TABLE empleado_rol(
cod_rol         char(4),
cod_empleado    char(7),
PRIMARY KEY (cod_rol, cod_empleado),
FOREIGN KEY (cod_rol) references rol(cod_rol) ON DELETE CASCADE,
FOREIGN KEY (cod_empleado) references empleado(cod_empleado) ON DELETE CASCADE
);

CREATE TABLE cliente(
cod_cliente    char(8),
cod_distrito   char(4),
nombre         varchar(25) NOT NULL,
apellidos      varchar(25) NOT NULL,
dni            char(8) NOT NULL,
direccion      varchar(45),
telefono       varchar(12),
email          varchar(45),
PRIMARY KEY (cod_cliente),
UNIQUE INDEX cliente_unicos (dni ASC),
FOREIGN KEY (cod_distrito) references distrito(cod_distrito) ON DELETE CASCADE
);

CREATE TABLE calzado(
cod_calzado    char(8),
cod_modelo     char(7),
cod_talla      char(4),
cod_color      char(4),
codigo         varchar(20),
descripcion    char(100) ,
stock          int NOT NULL,
stock_minimo   int DEFAULT 2,
PRIMARY KEY (cod_calzado),
UNIQUE INDEX calzado_unicos (codigo ASC),
FOREIGN KEY (cod_modelo) references modelo(cod_modelo) ON DELETE CASCADE,
FOREIGN KEY (cod_talla) references talla(cod_talla) ON DELETE CASCADE,
FOREIGN KEY (cod_color) references color(cod_color) ON DELETE CASCADE
);

CREATE TABLE boleta(
cod_boleta         char(8),
cod_cliente        char(8),
cod_empleado       char(7),
cod_moneda         char(4),
fecha_hora_emision timestamp NOT NULL,
igv                double NOT NULL DEFAULT 0.18,
total              double NOT NULL,
PRIMARY KEY (cod_boleta),
FOREIGN KEY (cod_cliente) references cliente(cod_cliente) ON DELETE CASCADE,
FOREIGN KEY (cod_empleado) references empleado(cod_empleado) ON DELETE CASCADE,
FOREIGN KEY (cod_moneda) references moneda(cod_moneda) ON DELETE CASCADE
);

CREATE TABLE detalle_boleta(
cod_boleta     char(8),
cod_calzado    char(8),
cantidad       int NOT NULL,
descuento      double,
subtotal       double NOT NULL,
PRIMARY KEY (cod_boleta, cod_calzado),
FOREIGN KEY (cod_boleta) references boleta(cod_boleta) ON DELETE CASCADE,
FOREIGN KEY (cod_calzado) references calzado(cod_calzado) ON DELETE CASCADE
);


-- DISTRITO
insert distrito (cod_distrito,descripcion) value ('DI01','Comas');
insert distrito (cod_distrito,descripcion) value ('DI02','Lince');
insert distrito (cod_distrito,descripcion) value ('DI03','Miraflores');
insert distrito (cod_distrito,descripcion) value ('DI04','Los olivos');
insert distrito (cod_distrito,descripcion) value ('DI05','S.J.L');
insert distrito (cod_distrito,descripcion) value ('DI06','Independencia');
-- ESTADO
insert estado (cod_estado,descripcion) value ('0','INACTIVO');
insert estado (cod_estado,descripcion) value ('1','ACTIVO');
-- MONEDA
insert moneda (cod_moneda,descripcion) value ('MN01','SOL');
insert moneda (cod_moneda,descripcion) value ('MN02','DOLAR');
-- TALLA
insert talla (cod_talla,numero_talla) value ('TL01','40');
insert talla (cod_talla,numero_talla) value ('TL02','41');
insert talla (cod_talla,numero_talla) value ('TL03','42');
-- COLOR
insert color (cod_color,nombre_color) value ('CR01','Negro');
insert color (cod_color,nombre_color) value ('CR02','Marron');
insert color (cod_color,nombre_color) value ('CR03','Azul');
insert color (cod_color,nombre_color) value ('CR04','Canela');
insert color (cod_color,nombre_color) value ('CR05','Azul Marino');
-- CATEGORIA
insert categoria (cod_categoria,descripcion) value ('CT01','Casual');
insert categoria (cod_categoria,descripcion) value ('CT02','Vestir');
-- MARCA
insert marca (cod_marca,nombre_marca) value ('MA10001','Calimod');
insert marca (cod_marca,nombre_marca) value ('MA10002','Basement');
insert marca (cod_marca,nombre_marca) value ('MA10003','Call It Spring');
-- MODELO
insert modelo (cod_modelo,cod_marca,cod_categoria,nombre_modelo,precio_compra,precio_venta) value ('MD10001','MA10001','CT01','1CEA003',50.00,90.00);
insert modelo (cod_modelo,cod_marca,cod_categoria,nombre_modelo,precio_compra,precio_venta) value ('MD10002','MA10002','CT01','1CSG001',65.00,124.00);
insert modelo (cod_modelo,cod_marca,cod_categoria,nombre_modelo,precio_compra,precio_venta) value ('MD10003','MA10001','CT02','5VCS001',53.00,110.00);
insert modelo (cod_modelo,cod_marca,cod_categoria,nombre_modelo,precio_compra,precio_venta) value ('MD10004','MA10002','CT02','Bartic Ne',87.00,142.00);
insert modelo (cod_modelo,cod_marca,cod_categoria,nombre_modelo,precio_compra,precio_venta) value ('MD10005','MA10003','CT02','Burkos Cl',73.00,135.00);
insert modelo (cod_modelo,cod_marca,cod_categoria,nombre_modelo,precio_compra,precio_venta) value ('MD10006','MA10002','CT01','Bart Gr',55.00,112.00);
insert modelo (cod_modelo,cod_marca,cod_categoria,nombre_modelo,precio_compra,precio_venta) value ('MD10007','MA10003','CT01','FRESIEN-EMB009',49.00,99.00);
insert modelo (cod_modelo,cod_marca,cod_categoria,nombre_modelo,precio_compra,precio_venta) value ('MD10008','MA10001','CT02','ZALITH001',78.00,149.00);
insert modelo (cod_modelo,cod_marca,cod_categoria,nombre_modelo,precio_compra,precio_venta) value ('MD10009','MA10003','CT01','IMBROS410',67.00,111.00);
-- ROL
insert rol (cod_rol,nombre) value ('RL01','ROLE_ADMIN');
insert rol (cod_rol,nombre) value ('RL02','ROLE_USER');
-- EMPLEADO
insert empleado (cod_empleado,cod_distrito,cod_estado,nombre,apellidos,dni,direccion,telefono,email,usuario,contrasena) value ('EM10001','DI01',1,'KEVIN','B','00000000','DIRECCION','999999999','paledot01@gmail.com','kevinB','$2a$10$Jtfxa0EuEjZrfQ4OvR4WbuqD00OBIfIp.5Sv33A7G8ya3xTI542nq');/*
insert empleado (cod_empleado,cod_distrito,cod_estado,nombre,apellidos,dni,direccion,telefono,email,usuario,contrasena) value ('EM10002','DI05',0,'ANGELICA','ANDRADE CERNA','40138356','JR.M ANUEL PRADO #250','970315487','ANDRADE@gmail.com','angelicaa','40138356');
insert empleado (cod_empleado,cod_distrito,cod_estado,nombre,apellidos,dni,direccion,telefono,email,usuario,contrasena) value ('EM10003','DI02',1,'ROSAURA','TARAZONA RIVAS','40457140','JR. LOS ROSALES #350', '993315487','TARANOZA@gmail.com','rosaurat','40457140');
insert empleado (cod_empleado,cod_distrito,cod_estado,nombre,apellidos,dni,direccion,telefono,email,usuario,contrasena) value ('EM10004','DI04',0,'RUTH','TARDIO HUAMAN','40198740','AV. JOSE BALTA #170', '990115487','TARDIO@gmail.com','rutht','40198740');
insert empleado (cod_empleado,cod_distrito,cod_estado,nombre,apellidos,dni,direccion,telefono,email,usuario,contrasena) value ('EM10005','DI04',1,'GLORIA','ZELADA VELA','78538140','AV. CARLOS MARIATEGUI #5000', '900315487','ZELADA@gmail.com','gloriaz','78538140');
insert empleado (cod_empleado,cod_distrito,cod_estado,nombre,apellidos,dni,direccion,telefono,email,usuario,contrasena) value ('EM10006','DI03',1,'SOLEDAD','ZARATE LOPEZ','35638140','AV. ALFONSO UGARTE #3420', '990316687','ZARATE@gmail.com','soledadz','35638140');
insert empleado (cod_empleado,cod_distrito,cod_estado,nombre,apellidos,dni,direccion,telefono,email,usuario,contrasena) value ('EM10007','DI01',1,'BRENDA','QUISPE RAMOS','40138140','JR. PACHITEA #140', '990387487','QUISPE@gmail.com','brendaq','40138140');
insert empleado (cod_empleado,cod_distrito,cod_estado,nombre,apellidos,dni,direccion,telefono,email,usuario,contrasena) value ('EM10008','DI02',1,'JUDITH','ALEGRE FABRA','64265741','ABELARDO QUIÑONES #60', '968265993','ALEGRE@gmail.com','juditha','64265741');
insert empleado (cod_empleado,cod_distrito,cod_estado,nombre,apellidos,dni,direccion,telefono,email,usuario,contrasena) value ('EM10009','DI02',0,'ROBERTO','BALLESTEROS PALACIOS','45297583','AV. 2 DE OCTUBRE #24', '957395865','BALLESTEROS@gmail.com','robertob','45297583');
insert empleado (cod_empleado,cod_distrito,cod_estado,nombre,apellidos,dni,direccion,telefono,email,usuario,contrasena) value ('EM10010','DI05',0,'ELADIO','BASTIDA RODA','69930584','AV. MARAÑON #84', '963960957','BASTIDA@gmail.com','eladiob','69930584');
insert empleado (cod_empleado,cod_distrito,cod_estado,nombre,apellidos,dni,direccion,telefono,email,usuario,contrasena) value ('EM10011','DI01',1,'VERA','IGLESIAS PINEDA','82748573','AV. LAS PALMERAS #35', '968265993','IGLESIAS@gmail.com','verai','82748573');
insert empleado (cod_empleado,cod_distrito,cod_estado,nombre,apellidos,dni,direccion,telefono,email,usuario,contrasena) value ('EM10012','DI01',0,'NELIDA','PEDRERO CAÑELLAS','83757482','AV. UNIVERSITARIA #53', '968947261','PEDRERO@gmail.com','nelidap','83757482');
insert empleado (cod_empleado,cod_distrito,cod_estado,nombre,apellidos,dni,direccion,telefono,email,usuario,contrasena) value ('EM10013','DI04',1,'JACINTO','QUINTANILLA GUERRERO','23146264','AV. LOS ALISOS #967', '998621463','QUINTANILLA@gmail.com','jacintoq','23146264');
insert empleado (cod_empleado,cod_distrito,cod_estado,nombre,apellidos,dni,direccion,telefono,email,usuario,contrasena) value ('EM10014','DI05',1,'VICTORIANO','NICOLAS MANZANO','37538456','AV. 18 DE ENERO #45', '989375666','NICOLAS@gmail.com','victorianon','37538456');
insert empleado (cod_empleado,cod_distrito,cod_estado,nombre,apellidos,dni,direccion,telefono,email,usuario,contrasena) value ('EM10015','DI04',1,'JIMENA','MARQUES ELIAS','73857485','AV. LOS HEROES #607', '964823546','MARQUES@gmail.com','jimenam','73857485');
insert empleado (cod_empleado,cod_distrito,cod_estado,nombre,apellidos,dni,direccion,telefono,email,usuario,contrasena) value ('EM10016','DI02',1,'YESICA','SANDOBAL CUSTODIA','58483059','AV. ARICA #485', '967254637','SANDOBAL@gmail.com','yesicas','58483059');
insert empleado (cod_empleado,cod_distrito,cod_estado,nombre,apellidos,dni,direccion,telefono,email,usuario,contrasena) value ('EM10017','DI04',0,'FLORENTINA','SOTO MACHADO','67859454','JR. RESTAURACION #23', '986958693','SOTO@gmail.com','florentinas','67859454');
insert empleado (cod_empleado,cod_distrito,cod_estado,nombre,apellidos,dni,direccion,telefono,email,usuario,contrasena) value ('EM10018','DI02',1,'MARITZA','PEREZ VENDRELL','59326152','AV. LA ALBORADA #565', '987562112','PEREZ@gmail.com','maritzap','59326152');
insert empleado (cod_empleado,cod_distrito,cod_estado,nombre,apellidos,dni,direccion,telefono,email,usuario,contrasena) value ('EM10019','DI05',1,'RAFAELA','CAÑETE BURGOS','21536473','AV. LA ALBORADA #235', '922237467','CAÑETE@gmail.com','rafaelac','21536473');
insert empleado (cod_empleado,cod_distrito,cod_estado,nombre,apellidos,dni,direccion,telefono,email,usuario,contrasena) value ('EM10020','DI01',1,'MARTINA','VILLENA ARTEAGA','36736289','AV. UNIVERSITARIA #125', '964515466','VILLENA@gmail.com','martinav','36736289');
insert empleado (cod_empleado,cod_distrito,cod_estado,nombre,apellidos,dni,direccion,telefono,email,usuario,contrasena) value ('EM10021','DI04',1,'LEOPOLDO','NEIRA GOMEZ','46285944','AV. LA MARINA #578', '978525553','LEOPOLDO@gmail.com','leopoldon','46285944');
insert empleado (cod_empleado,cod_distrito,cod_estado,nombre,apellidos,dni,direccion,telefono,email,usuario,contrasena) value ('EM10022','DI01',0,'ANGELA','APARICIO SEGURA','53617235','AV. BRASIL #446', '985783623','ANGELA@gmail.com','angelaa','53617235');
insert empleado (cod_empleado,cod_distrito,cod_estado,nombre,apellidos,dni,direccion,telefono,email,usuario,contrasena) value ('EM10023','DI03',1,'MARIO','ALVARO IBAÑEZ','43758266','AV. TACNA #43', '974612534','ALVARO@gmail.com','marioa','43758266');
insert empleado (cod_empleado,cod_distrito,cod_estado,nombre,apellidos,dni,direccion,telefono,email,usuario,contrasena) value ('EM10024','DI05',1,'CATALINA','MENDEZ HUERTA','44672834','AV. BERTOLOTTO #532', '973625367','MENDEZ@gmail.com','catalinam','44672834');
insert empleado (cod_empleado,cod_distrito,cod_estado,nombre,apellidos,dni,direccion,telefono,email,usuario,contrasena) value ('EM10025','DI01',1,'JOSE','SAEZ RAYA','28678543','AV. LIMA #51', '964732876','SAEZ@gmail.com','joses','28678543');*/
-- EMPLEADO-ROL
insert empleado_rol (cod_rol,cod_empleado) value ('RL01','EM10001');
insert empleado_rol (cod_rol,cod_empleado) value ('RL02','EM10001');
-- insert empleado_rol (cod_rol,cod_empleado) value ('RL02','EM10002');
-- CLIENTE
insert cliente (cod_cliente,cod_distrito,nombre,apellidos,dni,direccion,telefono,email) value ('CL100001','DI02','ANETH LUANA','TINEO URIBE','32425643','AV. LOS GIRASOLES # 1800','990990230','LUANITAHERMOSA@GMAIL.COM');
insert cliente (cod_cliente,cod_distrito,nombre,apellidos,dni,direccion,telefono,email) value ('CL100002','DI03','JOSE LUIS','TARAZONA ZELA','78395021','AV. LAS FLORES # 1800',null,'JOSESITO@GMAIL.COM');
insert cliente (cod_cliente,cod_distrito,nombre,apellidos,dni,direccion,telefono,email) value ('CL100003','DI05','ANA MARIA','VILLAVICENCIO CASTRO','48502717','AV. LAS FLORES # 2560','989434228','ANITAMARIA@GMAIL.COM');
insert cliente (cod_cliente,cod_distrito,nombre,apellidos,dni,direccion,telefono,email) value ('CL100004','DI06','JOSE ANTONIO','ENCISO NOLASCO','86294711','AV. PROCERES DE LA INDEPENDENCIA # 5000','987845874','JOSANTONIO@GMAIL.COM');
insert cliente (cod_cliente,cod_distrito,nombre,apellidos,dni,direccion,telefono,email) value ('CL100005','DI05','ALEJANDRA','CHUCO HUERTA','97537923','AV. GRAN CHIMU # 3500','963245874','ALEJANDRITA5245@HOTMAIL.COM');
-- CALZADO
insert calzado (cod_calzado,cod_modelo,cod_talla,cod_color,codigo,descripcion,stock,stock_minimo) value ('CZ100001','MD10001','TL01','CR02',null,null,14,0);
insert calzado (cod_calzado,cod_modelo,cod_talla,cod_color,codigo,descripcion,stock,stock_minimo) value ('CZ100002','MD10001','TL01','CR03',null,null,10,0);
insert calzado (cod_calzado,cod_modelo,cod_talla,cod_color,codigo,descripcion,stock,stock_minimo) value ('CZ100003','MD10002','TL02','CR02',null,null,9,0);
insert calzado (cod_calzado,cod_modelo,cod_talla,cod_color,codigo,descripcion,stock,stock_minimo) value ('CZ100004','MD10002','TL03','CR02',null,null,6,0);
insert calzado (cod_calzado,cod_modelo,cod_talla,cod_color,codigo,descripcion,stock,stock_minimo) value ('CZ100005','MD10003','TL01','CR01',null,null,4,0);
insert calzado (cod_calzado,cod_modelo,cod_talla,cod_color,codigo,descripcion,stock,stock_minimo) value ('CZ100006','MD10003','TL03','CR04',null,null,8,0);
insert calzado (cod_calzado,cod_modelo,cod_talla,cod_color,codigo,descripcion,stock,stock_minimo) value ('CZ100007','MD10004','TL03','CR05',null,null,6,0);
insert calzado (cod_calzado,cod_modelo,cod_talla,cod_color,codigo,descripcion,stock,stock_minimo) value ('CZ100008','MD10004','TL03','CR04',null,null,8,0);
insert calzado (cod_calzado,cod_modelo,cod_talla,cod_color,codigo,descripcion,stock) value ('CZ100009','MD10006','TL02','CR01',null,null,3); -- <--------- DEFAULT
-- BOLETA   omitimos el IGV porque se añade por defecto
insert boleta (cod_boleta,cod_cliente,cod_empleado,cod_moneda,fecha_hora_emision,total) value ('BL100001','CL100001','EM10001','MN01',NOW(),0);/*
insert boleta (cod_boleta,cod_cliente,cod_empleado,cod_moneda,fecha_hora_emision,total) value ('BL100002','CL100002','EM10002','MN01',NOW(),0);
insert boleta (cod_boleta,cod_cliente,cod_empleado,cod_moneda,fecha_hora_emision,total) value ('BL100003','CL100003','EM10003','MN01',NOW(),0);
insert boleta (cod_boleta,cod_cliente,cod_empleado,cod_moneda,fecha_hora_emision,total) value ('BL100004','CL100003','EM10003','MN01',NOW(),0);*/
-- DETALLE BOLETA   colocamos el descuento
insert detalle_boleta (cod_boleta,cod_calzado,cantidad,descuento,subtotal) value ('BL100001','CZ100001',2,0,180.00);
insert detalle_boleta (cod_boleta,cod_calzado,cantidad,descuento,subtotal) value ('BL100001','CZ100006',1,0,110.00);/*
insert detalle_boleta (cod_boleta,cod_calzado,cantidad,descuento,subtotal) value ('BL100002','CZ100002',1,0,90.00);
insert detalle_boleta (cod_boleta,cod_calzado,cantidad,descuento,subtotal) value ('BL100002','CZ100008',1,0,142.00);
insert detalle_boleta (cod_boleta,cod_calzado,cantidad,descuento,subtotal) value ('BL100003','CZ100009',2,0,224.00);
insert detalle_boleta (cod_boleta,cod_calzado,cantidad,descuento,subtotal) value ('BL100003','CZ100007',1,0,142.00);
insert detalle_boleta (cod_boleta,cod_calzado,cantidad,descuento,subtotal) value ('BL100004','CZ100003',2,0,248.00);
insert detalle_boleta (cod_boleta,cod_calzado,cantidad,descuento,subtotal) value ('BL100004','CZ100005',3,0,330.00);
insert detalle_boleta (cod_boleta,cod_calzado,cantidad,descuento,subtotal) value ('BL100004','CZ100002',1,0,90.00);
insert detalle_boleta (cod_boleta,cod_calzado,cantidad,descuento,subtotal) value ('BL100004','CZ100004',2,0,248.00);*/

-- select * from distrito;
-- select * from estado;
-- select * from moneda;
-- select * from talla;
-- select * from color;
-- select * from categoria;
-- select * from marca;
-- select * from modelo;
-- select * from rol;
-- select * from empleado;
-- select * from empleado_rol;
-- select * from cliente;
-- select * from calzado;
-- select * from boleta;
-- select * from detalle_boleta;




-- 1 --> select timediff(now(),convert_tz(now(),@@session.time_zone,'+00:00'));
-- 2 --> SELECT @@global.time_zone, @@session.time_zone;
-- 3 --> set global time_zone='-00:00';
-- 4 --> set time_zone='-00:00';
-- ERROR CREO --> SELECT IF(@@session.time_zone = 'SYSTEM', @@system_time_zone, @@session.time_zone);