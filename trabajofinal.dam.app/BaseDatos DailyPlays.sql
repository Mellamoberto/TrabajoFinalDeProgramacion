drop database if exists dailyPlay;

create database dailyPlay;
use dailyPlay;

/*
INSERT INTO usuario (pass, fechaRegistro, nombre, email) VALUES ('1234', '2023-05-18', 'alberto', 'app');
INSERT INTO usuario_videojuego (email_usuario, nombre_videojuego) VALUES ('App', 'God of War');
DROP table videojuego;
DROP table review;
DROP table review_videojuego;
DROP table videojuegos_favoritos;
delete from usuario where email = 'App';
delete from videojuego where nombre = 'a';
delete from usuario_videojuego where nombre_videojuego = 'God of War';
*/


create table usuario(
	nombre varchar(100),
    email varchar(200) primary key,
    pass varchar(50),
    fechaRegistro date,
    esModerador boolean
);

INSERT INTO usuario (nombre, email, pass, fechaRegistro, esModerador) 
VALUES
('Juan', 'jom', 1234, '2023-06-02', 0),
('Berto', 'app', 1234, '2023-06-02', 1);



CREATE TABLE videojuego (
    nombre varchar(100) PRIMARY KEY,
    nota float,
    descripcion varchar(300),
    lanzamiento date,
    desarrolladora varchar (100),
    distribuidora varchar (100),
	genero ENUM('ACCION','AVENTURA','ROL','DISPAROS','ESTRATEGIA','DEPORTES','CARRERAS','PLATAFORMAS',
    'LUCHA','SIMULACION'),
    plataforma ENUM('PC','PLAYSTATION_4','PLAYSTATION_5','XBOX_SERIES_X','XBOX_ONE','NINTENDO_SWITCH',
    'NINTENDO_3DS','MOBILE'),
     imagen varchar(100)
);


INSERT INTO videojuego (nombre, nota, descripcion, lanzamiento, desarrolladora, distribuidora, genero, plataforma, imagen)
VALUES
    ('The Last of Us', NULL, 'Un videojuego de acción y aventura postapocalíptico en tercera persona', '2013-06-14', 'Naughty Dog', 'Sony', 'AVENTURA', 'PLAYSTATION_4', 'TLOU.jpg'),
    ('Grand Theft Auto V', NULL, 'Un juego de acción y aventura en mundo abierto', '2013-09-17', 'Rockstar North', 'Rockstar Games', 'ACCION', 'PLAYSTATION_4', 'GTA5.jpg'),
    ('Minecraft', NULL, 'Un juego de construcción y aventura en un mundo abierto', '2011-11-18', 'Mojang Studios', 'Mojang Studios', 'AVENTURA', 'PC', 'Minecraft.jpg'),
    ('The Legend of Zelda: Breath of the Wild', NULL, 'Un videojuego de acción y aventura con mundo abierto', '2017-03-03', 'Nintendo EPD', 'Nintendo', 'AVENTURA', 'NINTENDO_SWITCH', 'Zelda.jpg'),
    ('Fortnite', NULL, 'Un juego de batalla real con elementos de construcción', '2017-07-25', 'Epic Games', 'Epic Games', 'ACCION', 'PLAYSTATION_4', 'Fortnite.jpg'),
    ('Call of Duty: Modern Warfare', NULL, 'Un juego de disparos en primera persona ambientado en la guerra moderna', '2019-10-25', 'Infinity Ward', 'Activision', 'ACCION', 'XBOX_ONE', 'CODMW.jpg'),
    ('FIFA 22', NULL, 'Un juego de simulación de fútbol', '2021-10-01', 'EA Vancouver', 'Electronic Arts', 'DEPORTES', 'PLAYSTATION_5', 'FIFA22.jpg'),
    ('Among Us', NULL, 'Un juego de deducción social en el que los jugadores deben descubrir quién es el impostor', '2018-06-15', 'InnerSloth', 'InnerSloth', 'ESTRATEGIA', 'PC', 'AmongUs.jpg'),
    ('God of War', NULL, 'Vídeojuego de la saga God of War, que traslada su fórmula jugable desde la antigua Grecia a una ambientación nórdica, en una historia de padre e hijo',
        '2018-05-05', 'Santa Monica Studio', 'Sony', 'AVENTURA', 'PLAYSTATION_4', 'GoW.jpg');





CREATE TABLE review (
	id int PRIMARY KEY auto_increment,
	usuario_email varchar(100),
    videojuego_nombre varchar(100),
    nombre varchar(100),
    calificacion float,
    fecha_calificacion datetime,
    comentario varchar(1000),
    duracion int, 
    FOREIGN KEY (videojuego_nombre) REFERENCES videojuego(nombre),
    FOREIGN KEY (usuario_email) REFERENCES usuario(email)
);
ALTER TABLE review AUTO_INCREMENT = 1;

INSERT INTO review (usuario_email, videojuego_nombre, nombre, calificacion, fecha_calificacion, comentario, duracion)
VALUES
('app', 'God of War', null, 8, '2022-06-01 12:30:00', null, null),
('app', 'God of War', null, 9, '2022-06-01 12:30:00', null, null),
('jom', 'God of War', null, 7, '2022-06-01 12:30:00', null, null);


INSERT INTO review (usuario_email, videojuego_nombre, nombre, calificacion, fecha_calificacion, comentario, duracion)
VALUES ('app', 'The Last of Us', 'Increíble experiencia narrativa', 10, '2022-06-02 09:00:00', 'Gráficos impresionantes', null);

INSERT INTO review (usuario_email, videojuego_nombre,nombre, calificacion, fecha_calificacion, comentario, duracion)
VALUES ('jom', 'Minecraft', 'Libertad creativa sin límites', 9, '2022-06-02 14:45:00', 'Mundo infinito para explorar', null);

INSERT INTO review (usuario_email, videojuego_nombre, nombre, calificacion, fecha_calificacion, comentario, duracion)
VALUES ('app', 'Fortnite', 'Adictivo y emocionante', 8, '2022-06-03 17:20:00', 'Gran variedad de modos de juego', null);

INSERT INTO review (usuario_email, videojuego_nombre, nombre, calificacion, fecha_calificacion, comentario, duracion)
VALUES ('jom', 'FIFA 22', 'Realismo futbolístico', 7, '2022-06-04 10:15:00', 'Licencias oficiales de equipos y jugadores', null);



CREATE TABLE desarrolladora (
nombre varchar(100) PRIMARY KEY
);

INSERT INTO desarrolladora (nombre)
VALUES ('Naughty Dog'),
       ('Nintendo EPD'),
       ('Rockstar North'),
       ('Ubisoft Montreal'),
       ('Bethesda Game Studios'),
       ('CD Projekt Red'),
       ('Blizzard Entertainment'),
       ('Square Enix'),
       ('Capcom'),
       ('Kojima Productions');


CREATE TABLE distribuidora (
nombre varchar(100) PRIMARY KEY
);

INSERT INTO distribuidora (nombre)
VALUES ('Sony'),
       ('Nintendo'),
       ('Microsoft'),
       ('Electronic Arts'),
       ('Activision'),
       ('Ubisoft'),
       ('Square Enix'),
       ('Capcom'),
       ('Take-Two Interactive'),
       ('CD Projekt');




CREATE TABLE amigos (
    usuario_email varchar(100),
    amigo_email varchar(100),
    PRIMARY KEY (usuario_email, amigo_email),
    FOREIGN KEY (usuario_email) REFERENCES usuario(email),
    FOREIGN KEY (amigo_email) REFERENCES usuario(email)
);



CREATE TABLE usuario_videojuego (
    email_usuario varchar(200),
    nombre_videojuego varchar(100),
    FOREIGN KEY (email_usuario) REFERENCES usuario(email),
    FOREIGN KEY (nombre_videojuego) REFERENCES videojuego(nombre),
    PRIMARY KEY (email_usuario, nombre_videojuego)
);


CREATE TABLE videojuego_actual (
    email_usuario varchar(100),
    nombre_videojuego varchar(100),
    PRIMARY KEY (email_usuario, nombre_videojuego),
    FOREIGN KEY (email_usuario) REFERENCES usuario(email),
    FOREIGN KEY (nombre_videojuego) REFERENCES videojuego(nombre)
);


CREATE TABLE videojuego_jugado (
    email_usuario varchar(100),
    nombre_videojuego varchar(100),
    PRIMARY KEY (email_usuario, nombre_videojuego),
    FOREIGN KEY (email_usuario) REFERENCES usuario(email),
    FOREIGN KEY (nombre_videojuego) REFERENCES videojuego(nombre)
);

CREATE TABLE videojuego_pendiente (
    email_usuario varchar(100),
    nombre_videojuego varchar(100),
    PRIMARY KEY (email_usuario, nombre_videojuego),
    FOREIGN KEY (email_usuario) REFERENCES usuario(email),
    FOREIGN KEY (nombre_videojuego) REFERENCES videojuego(nombre)
);


CREATE TABLE videojuego_favorito (
    email varchar(100),
    nombre_videojuego varchar(100),
    PRIMARY KEY (email, nombre_videojuego),
    FOREIGN KEY (email) REFERENCES usuario(email),
    FOREIGN KEY (nombre_videojuego) REFERENCES videojuego(nombre)
);

