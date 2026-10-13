# DOSW_LAB6_FLR


## Diagrama de clases
![Diagrama](/src/main/resources/docs/images/DiagramaDeClases.png)

## API
![Diagrama](/src/main/resources/docs/images/APIOFICIOYA.png)


¿Para qué sirve el paquete Controller en la estructura Spring Boot?

Sirve para recibir y gestionar las peticiones HTTP que hacen los clientes y devolver una respuesta adecudada.

¿Para qué sirve el paquete Service en la estructura Spring Boot?

Centraliza y organizar la lógica del negocio de la aplicación.

¿Para qué sirve el paquete Model en la estructura Spring Boot?

Sirve para almacenar las clases que representan los datos y la estructura del negocio de la aplicación.

¿Para qué sirve el paquete Repository en la estructura Spring Boot?

Para organizar las interfaces de acceso a datos y comunicación directa con las bases de datos.

¿Para qué sirve el paquete Entity en la estructura Spring Boot?

Sirve  para almacenar las clases de java que representar las tablas de una base de datos relacional.

¿Para qué sirve el paquete DTO en la estructura Spring Boot?

DTO, tambien comocido como Data Transfer Object, sirve para organizar las clases simples encargadas de transportar datos entre el cliente, o entre las diferentes capas.

¿Para qué sirve el paquete Exception en la estructura Spring Boot?

Organizar y centralizar el manejo de errores y excepciones de la aplicación en un solo lugar.


Broadcom. (2026). Structuring your code. Spring Boot Reference Documentation. https://spring.io

Farakate, R. (2024, 22 de julio). Understanding DTOs in Spring Boot: A comprehensive guide. Medium. https://medium.com

GeeksforGeeks. (2026, 9 de abril). JPA - Creating an entity. GeeksforGeeks. https://geeksforgeeks.org

Spring Source. (2013). Working with Spring Data repositories. Spring Data Commons Reference Documentation. https://spring.io

Stack Overflow en español. (s. f.). Cuál es la diferencia entre los paquetes controller, services, repository y model. Stack Overflow. https://stackoverflow.com

Stack Overflow. (2019). What is the use of service layer in Spring Boot applications [Hilo de foro]. Stack Overflow. https://stackoverflow.com
---

# Microservicio de Imágenes (MongoDB)

## Descripción
Microservicio REST para almacenar, consultar y eliminar imágenes en MongoDB. Cada imagen puede asociarse a una referencia externa (por ejemplo, un oficio).

## Tecnologías
- Java 21, Spring Boot 3.2.4, Maven
- Spring Web, Spring Data MongoDB, Validation
- MongoDB (Docker)

## Cómo ejecutar
1. Levantar MongoDB:
```bash
   docker run --name mongo-lab7 -p 27017:27017 -d mongo
```
2. Compilar y arrancar:
```bash
   mvn clean compile
   mvn spring-boot:run
```
3. La aplicación queda en `http://localhost:8081`.

## Endpoints

| Método | Ruta | Descripción | Respuesta |
|--------|------|-------------|-----------|
| POST | `/imagenes` | Sube una imagen (form-data: `file`, `referenciaExterna` opcional) | 201 |
| GET | `/imagenes` | Lista los metadatos de las imágenes | 200 |
| GET | `/imagenes/{id}` | Devuelve la imagen | 200 / 404 |
| GET | `/imagenes/referencia/{referenciaExterna}` | Lista imágenes por referencia | 200 |
| DELETE | `/imagenes/{id}` | Elimina una imagen | 204 / 404 |

## Evidencias (Postman)

| Prueba | Evidencia |
|--------|-----------|
| POST subir imagen (201) | ![POST](docs/evidencias/01-post.png) |
| GET listar (200) | ![Lista](docs/evidencias/02-get-lista.png) |
| GET por id (200) | ![Id](docs/evidencias/03-get-id.png) |
| GET por referencia (200) | ![Referencia](docs/evidencias/04-get-referencia.png) |
| DELETE (204) | ![Delete](docs/evidencias/05-delete.png) |
| GET id borrado (404) | ![404](docs/evidencias/06-get-404.png) |

## Integrantes
Juan José, Laverde, Brian, Santiago, Diego
