# Prueba Técnica – Gestión de Libros

Este proyecto contiene una aplicación para la gestión de libros con un backend en Spring Boot y un frontend en Angular.

---

## Requisitos

Para ejecutar el proyecto en entorno local es necesario tener instalado:

- Java 17
- Maven
- Node.js (LTS)
- Angular CLI
- SQL Server
- Git

---

## Base de datos

El proyecto utiliza SQL Server.

Dentro del backend se incluye un script SQL para crear la base de datos y la tabla necesaria.

Ubicación del script:
libros-api\script.sql

Antes de ejecutar el backend, este script debe ejecutarse manualmente en SQL Server.

---

## Configuración del Backend

El backend requiere un archivo de configuración local con las credenciales de la base de datos.

Se incluye un archivo de ejemplo en:

backend/src/main/resources/application-example.yml

Pasos:

1. Copiar `application-example.yml`
2. Renombrarlo a `application.yml`
3. Configurar las credenciales de SQL Server

Ejemplo:

yaml
spring:
  datasource:
    url: jdbc:sqlserver://localhost;databaseName=libros_db;encrypt=false
    username: sa
    password: TU_PASSWORD
    driver-class-name: com.microsoft.sqlserver.jdbc.SQLServerDriver

  jpa:
    hibernate:
      ddl-auto: none
    show-sql: true

server:
  port: 8081

Ejecutar el Backend
Desde la carpeta backend:
mvn clean install
mvn spring-boot:run

La API quedará disponible en:
http://localhost:8081

Ejecutar el Frontend
Desde la carpeta frontend:
npm install
ng serve

La aplicación quedará disponible en:
http://localhost:4200

Funcionalidades:
CRUD completo de libros
Validaciones backend y frontend
Listado con paginación y ordenamiento
Interfaz web para gestión de libros
