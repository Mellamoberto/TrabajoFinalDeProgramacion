drop database if exists dailyPlay;

create database dailyPlay;
use dailyPlay;

INSERT INTO usuario (pass, fechaRegistro, nombre, email) VALUES ('1234', '2023-05-18', 'alberto', 'app');
INSERT INTO usuario_videojuego (email_usuario, nombre_videojuego) VALUES ('App', 'God of War');

DROP table videojuego;
DROP table review_videojuego;
DROP table videojuegos_favoritos;

delete from usuario where email = 'App';
delete from videojuego where nombre = 'a';
delete from usuario_videojuego where nombre_videojuego = 'God of War';

create table usuario(
	nombre varchar(100),
    email varchar(200) primary key,
    pass varchar(50),
    fechaRegistro date,
    esModerador boolean
);

CREATE TABLE videojuego (
    nombre varchar(100) PRIMARY KEY,
    nota float,
    descripcion varchar(300),
    lanzamiento date,
	genero ENUM('ACCION','AVENTURA','ROL','DISPAROS','ESTRATEGIA','DEPORTES','CARRERAS','PLATAFORMAS',
    'LUCHA','SIMULACION'),
    plataforma ENUM('PC','PLAYSTATION_4','PLAYSTATION_5','XBOX_SERIES_X','XBOX_ONE','NINTENDO_SWITCH',
    'NINTENDO_3DS','MOBILE')
);

INSERT INTO videojuego (nombre, descripcion, lanzamiento, numComentarios, genero, plataforma) 
VALUES
('God of War (2018)','Vídeojuego de la saga God of War, que traslada su fórmula jugable desde la antigua Grecia a una ambientación nórdica, en una historia de padre e hijo', 
'20 de abril de 2018', 0, 'AVENTURAS', 'PLAYSTATION_4');


CREATE TABLE review (
    id int PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(100),
    calificacion float,
    comentario varchar(500),
    duracion int,
    videojuego_nombre varchar(100),
    usuario_email varchar(100),
    FOREIGN KEY (videojuego_nombre) REFERENCES videojuego(nombre),
    FOREIGN KEY (usuario_email) REFERENCES usuario(email)
);

CREATE TABLE usuario_videojuego (
    email_usuario varchar(200),
    nombre_videojuego varchar(100),
    FOREIGN KEY (email_usuario) REFERENCES usuario(email),
    FOREIGN KEY (nombre_videojuego) REFERENCES videojuego(nombre),
    PRIMARY KEY (email_usuario, nombre_videojuego)
);

CREATE TABLE videojuegos_favoritos (
  email VARCHAR(100),
  nombre_videojuego VARCHAR(100),
  PRIMARY KEY (email, nombre_videojuego),
  FOREIGN KEY (email) REFERENCES Usuario(email),
  FOREIGN KEY (nombre_videojuego) REFERENCES Videojuego(nombre)
);


create table desarrolladora(
nombre varchar(100)
);

create table distribuidora(
nombre varchar(100)
);

