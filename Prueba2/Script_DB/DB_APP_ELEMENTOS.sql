CREATE DATABASE DB_APP_ELEMENTOS;

USE DB_APP_ELEMENTOS;

CREATE TABLE Usuario(
	Id_Usuario INT PRIMARY KEY IDENTITY,
	Nombre_Usuario VARCHAR(100) NOT NULL,
	Email_Usuario NVARCHAR(200) UNIQUE NOT NULL,
	Password_Usuario VARCHAR(100) NOT NULL,
	Edad_Usuario INT NOT NULL
	);

CREATE TABLE UsuarioGeneral(
	Id_Usuario INT PRIMARY KEY REFERENCES Usuario(Id_Usuario)
	);

CREATE TABLE UsuarioPremium(
	Id_Usuario INT PRIMARY KEY REFERENCES Usuario(Id_Usuario),
	Fecha_Suscripcion DATE,
	Fecha_Limite DATE
	);


CREATE TABLE Elemento(
	Id_Elemento INT PRIMARY KEY IDENTITY,
	Id_Usuario INT FOREIGN KEY (Id_Usuario) REFERENCES Usuario(Id_Usuario),
	Titulo_Elemento VARCHAR(100) NOT NULL,
	Descripcion_Elemento NVARCHAR(500) NOT NULL, 
	Prioridad_Elemento VARCHAR(20) CHECK(Prioridad_Elemento IN ('ALTA', 'MEDIA', 'BAJA')) NOT NULL,
	Fecha_Creacion DATE NOT NULL,
	Fecha_Limite_Elemento DATE NOT NULL
	);

	CREATE TABLE Elemento_Tarea(
	Id_Elemento_Tarea INT PRIMARY KEY IDENTITY,
	Id_Elemento INT FOREIGN KEY (Id_Elemento) REFERENCES Elemento(Id_Elemento),
	Estado_Elemento VARCHAR(20) CHECK(Estado_Elemento IN ('EN_PROGRESO', 'COMPLETADO', 'VENCIDA', 'CANCELADA', 'PENDIENTE')) NOT NULL
	);

CREATE TABLE Elemento_Recordatorio(
	Id_Elemento_Recordatorio INT PRIMARY KEY IDENTITY,
	Id_Elemento INT FOREIGN KEY (Id_Elemento) REFERENCES Elemento(Id_Elemento),
	Fecha_Recordatorio DATE NOT NULL
	);

CREATE TABLE Tarjeta(
	Id_Tarjeta INT PRIMARY KEY IDENTITY,
	Id_Usuario INT FOREIGN KEY (Id_Usuario) REFERENCES Usuario(Id_Usuario),
	Titular_Tarjeta VARCHAR(100) NOT NULL,
	Numero_Tarjeta VARCHAR(16) NOT NULL,
	CVV_Tarjeta VARCHAR(4) NOT NULL,
	Fecha_Vencimiento DATE NOT NULL
	);

CREATE TABLE Bitcoin(
    Id_Usuario INT PRIMARY KEY,
	DUI_Bitcoin NVARCHAR(10)
	);

CREATE TABLE Pagos(
	Id_Pago INT PRIMARY KEY IDENTITY,
	Id_Usuario INT FOREIGN KEY (Id_Usuario) REFERENCES Usuario(Id_Usuario),
	Id_Tarjeta INT NULL FOREIGN KEY (Id_Tarjeta) REFERENCES Tarjeta(Id_Tarjeta),
	Monto DECIMAL(10,2) NOT NULL,
	Metodo_Pago VARCHAR(20) CHECK (Metodo_Pago IN ('TARJETA','BITCOIN')) NOT NULL,
	Fecha_Pago DATE NOT NULL
	);

CREATE TABLE Elementos_Compartidos(
	Id_Compartido INT PRIMARY KEY IDENTITY,
	Id_Usuario_Origen INT FOREIGN KEY (Id_Usuario_Origen) REFERENCES Usuario(Id_Usuario),
	Id_Usuario_Destino INT FOREIGN KEY (Id_Usuario_Destino) REFERENCES Usuario(Id_Usuario),
	Id_Elemento INT FOREIGN KEY (Id_Elemento) REFERENCES Elemento(Id_Elemento),
	Fecha_Compartido DATE NOT NULL
	);

	SELECT @@SERVERNAME;
GO

SELECT @@SERVICENAME;
GO