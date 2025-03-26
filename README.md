# Desarrollo de App de Gestión de Productos
Esta aplicación es un ejemplo práctico de una solución para la gestión de productos, implementada en Java 11 y utilizando una base de datos en memoria (H2). La base de datos se restablece al finalizar la aplicación, lo que permite un entorno de pruebas ágil y sin persistencia a largo plazo.

## Descripción General
La app está diseñada para demostrar el manejo de una base de datos básica, permitiendo realizar operaciones CRUD (creación, lectura, actualización y eliminación) sobre los productos. A continuación, se describen las funcionalidades implementadas y algunas recomendaciones para futuras mejoras.

## Funcionalidades Implementadas
**Creación de Productos**

- Atributos

  - ID del producto
  
  - Nombre
  
  - Descripción
  
  - Cantidad
  
  - Precio

**Listado de Productos**
- Se muestra una lista completa de todos los productos creados, ordenados por precio de menor a mayor.

**Búsqueda de Productos**
- Por ID o Nombre: Permite la búsqueda de un producto específico mediante el ingreso del ID o nombre.

**Actualización de Productos**
- Una vez localizado el producto, se pueden modificar cualquiera de sus atributos: nombre, descripción, cantidad o precio.

**Eliminación de Productos**
- Elimina de forma definitiva el registro del producto de la base de datos utilizando su ID.

## Recomendaciones de Mejora a Futuro
  - Listado de Búsqueda por Nombre:
    - Implementar un listado que muestre todas las coincidencias de un nombre determinado para facilitar la identificación de productos similares o duplicados.

  - Borrado Lógico y Restauración:
    - Desarrollar un mecanismo de borrado lógico que evite la eliminación accidental de productos, permitiendo además su restauración sin pérdida de información.

## Herramientas Utilizadas
  - Lenguaje: Java 11 (OpenLogic)
  
  - Framework: Spring Boot
  
  - Base de Datos: H2 (Base de datos en memoria)
  
  - Cliente de Pruebas: Postman

## Dependencias
  - Lombok: Facilita la generación de código repetitivo (getters, setters, constructores, etc.).
  
  - Spring Starter Web: Configura de manera rápida y sencilla el entorno web.
  
  - H2database: Base de datos en memoria para un entorno de desarrollo rápido y sin configuración compleja.

  --------------------------------------------------------------------------------------------------

# Product Management App Development
  This application is a practical example of a solution for managing products, implemented in Java 11 and using an in-memory database (H2). The database resets when the application terminates, allowing for a quick testing environment without long-term persistence.

## Overview
The app is designed to demonstrate the management of a basic database, enabling CRUD operations (create, read, update, and delete) for products. Below, the implemented functionalities are described, along with some recommendations for future improvements.

## Implemented Functionalities
**Product Creation**

- Attributes:
  
  - Product ID
  
  - Name
  
  - Description
  
  - Quantity
  
  - Price

**Product Listing**
  - Sorting:
    - A complete list of all created products is displayed, sorted by price in ascending order.

**Product Search**
  - By ID or Name:
    - Allows searching for a specific product by entering its ID or name.

**Product Update**
  - Editing:
    - Once the product is located, any of its attributes (name, description, quantity, or price) can be modified.

**Product Deletion**
  - Physical Deletion:
    - Permanently removes the product record from the database by using its ID.

## Future Improvement Recommendations
  - Name-Based Listing for Search:
    - Implement a listing that shows all matches for a given name to facilitate the identification of similar or duplicate products.

## Logical Deletion and Restoration:
  - Develop a logical deletion mechanism to prevent accidental removal of products, while allowing for restoration without data loss.

## Tools Used
  - Language: Java 11 (OpenLogic)
  
  - Framework: Spring Boot
  
  - Database: H2 (In-memory database)

  - Testing Client: Postman

## Dependencies
  - Lombok: Simplifies the generation of repetitive code (getters, setters, constructors, etc.).
  
  - Spring Starter Web: Quickly and easily sets up the web environment.
  
  - H2database: Provides an in-memory database for a rapid development environment without complex configuration.
