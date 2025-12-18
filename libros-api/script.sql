CREATE DATABASE libros_db;
GO

USE libros_db;
GO

CREATE TABLE libro (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(150) NOT NULL UNIQUE,
    descripcion NVARCHAR(300),
    autor NVARCHAR(150) NOT NULL,
    fecha_publicacion DATE NOT NULL,
    numero_ejemplares INT NOT NULL,
    costo DECIMAL(10,2) NOT NULL
);
GO
