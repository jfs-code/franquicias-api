# Franquicias API

API REST desarrollada con **Spring Boot** para la administración de **franquicias, sucursales y productos**.

La solución implementa una **Arquitectura Hexagonal (Ports & Adapters)**, separando la lógica de negocio de la infraestructura para facilitar el mantenimiento, la escalabilidad y las pruebas.

La persistencia se realiza sobre **MySQL**, utilizando **Flyway** para el versionamiento de la base de datos y **Docker Compose** para levantar el entorno local.

---

# Características

- Gestión de franquicias.
- Gestión de sucursales.
- Gestión de productos.
- Arquitectura Hexagonal (Ports & Adapters).
- Casos de uso desacoplados mediante puertos.
- Manejo centralizado de excepciones.
- Validaciones mediante Bean Validation.
- Migraciones automáticas con Flyway.
- Persistencia en MySQL.
- Base de datos ejecutándose mediante Docker.
- Pruebas unitarias con JUnit 5 y Mockito.
- Reporte de cobertura mediante JaCoCo.

---

# Tecnologías

- Java 17
- Spring Boot 3.5.5
- Spring Data JPA
- Spring Validation
- MySQL 8
- Flyway
- Docker & Docker Compose
- Maven
- JUnit 5
- Mockito
- JaCoCo
- Postman

---

# Estructura del proyecto

```
backend/
docker/
postman/
README.md
```

| Carpeta | Descripción |
|----------|-------------|
| backend | Código fuente de la API |
| docker | Configuración de MySQL mediante Docker Compose |
| postman | Colección de Postman para probar todos los endpoints |

---

# Arquitectura

El proyecto sigue una **Arquitectura Hexagonal (Ports & Adapters)**.

```
backend
│
├── domain
│   ├── model
│   ├── ports
│   ├── usecases
│   └── exception
│
├── infrastructure
│   ├── adapter
│   │   ├── in
│   │   └── out
│   ├── config
│   └── handler
│
└── resources
    ├── application.yml
    └── db/migration
```

La lógica de negocio permanece completamente aislada de la infraestructura mediante el uso de **Ports & Adapters**.

Los casos de uso dependen únicamente de interfaces (Ports), mientras que la infraestructura proporciona sus implementaciones mediante adaptadores. Esta separación facilita el mantenimiento del código y la realización de pruebas unitarias utilizando mocks.

---

# Funcionalidades

## Franquicias

- Crear una franquicia.
- Consultar una franquicia por identificador.
- Consultar todas las franquicias.
- Actualizar el nombre de una franquicia.
- Obtener las sucursales pertenecientes a una franquicia.
- Obtener el producto con mayor stock por sucursal para una franquicia.

## Sucursales

- Crear una sucursal.
- Consultar una sucursal.
- Consultar todas las sucursales de una franquicia.
- Actualizar el nombre de una sucursal.

## Productos

- Crear un producto.
- Consultar un producto.
- Actualizar el nombre del producto.
- Actualizar el stock del producto.
- Eliminar un producto.

---

# Base de datos

La aplicación utiliza **MySQL 8** como motor de base de datos.

El esquema y los datos iniciales son administrados mediante **Flyway**, ejecutándose automáticamente al iniciar la aplicación.

Las migraciones se encuentran en:

```
backend/src/main/resources/db/migration
```

Tablas creadas:

- franchise
- branch
- product

---

# Variables de entorno

Crear un archivo `.env` tomando como referencia el archivo `.env.example`.

Ejemplo:

```env
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/franquicias
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=root
```

---

# Ejecución con Docker

Ubicarse en la carpeta:

```
docker/
```

Ejecutar:

```bash
docker compose up -d
```

Verificar que el contenedor se encuentre en ejecución:

```bash
docker ps
```

Esto iniciará:

- MySQL 8
- Volumen persistente para la base de datos

---

# Ejecución del Backend

## Requisitos

- Java 17
- Maven Wrapper

Ubicarse en:

```
backend/
```

Ejecutar:

```bash
./mvnw spring-boot:run
```

También puede ejecutarse directamente desde Visual Studio Code mediante la opción **Run**.

---

# Pruebas Unitarias

El proyecto incluye pruebas unitarias para la capa de dominio, validando la lógica de negocio de los principales casos de uso.

Se utilizaron las siguientes herramientas:

- JUnit 5
- Mockito
- JaCoCo

Casos de uso cubiertos:

- FranchiseUseCaseImpl
- BranchUseCaseImpl
- ProductUseCaseImpl

Para ejecutar las pruebas:

```bash
./mvnw test
```

Para generar el reporte de cobertura:

```bash
./mvnw verify
```

El reporte HTML de cobertura queda disponible en:

```
backend/target/site/jacoco/index.html
```

Actualmente el proyecto cuenta con **33 pruebas unitarias**, todas ejecutándose satisfactoriamente.

---

# Endpoints

Todos los endpoints consumen y retornan información en formato **JSON**.

## Franquicias

| Método | Endpoint | Descripción |
|---------|----------|-------------|
| POST | `/api/franchises` | Crear franquicia |
| GET | `/api/franchises` | Listar franquicias |
| GET | `/api/franchises/{id}` | Consultar franquicia |
| PUT | `/api/franchises/{id}/name` | Actualizar nombre |
| GET | `/api/franchises/{id}/branches` | Obtener sucursales |
| GET | `/api/franchises/{id}/top-stock-products` | Producto con mayor stock por sucursal |

---

## Sucursales

| Método | Endpoint | Descripción |
|---------|----------|-------------|
| POST | `/api/branches` | Crear sucursal |
| GET | `/api/branches/{id}` | Consultar sucursal |
| PUT | `/api/branches/{id}/name` | Actualizar nombre |
| GET | `/api/branches/{id}/products` | Obtener productos |

---

## Productos

| Método | Endpoint | Descripción |
|---------|----------|-------------|
| POST | `/api/products` | Crear producto |
| GET | `/api/products/{id}` | Consultar producto |
| PUT | `/api/products/{id}/name` | Actualizar nombre |
| PATCH | `/api/products/{id}/stock` | Actualizar stock |
| DELETE | `/api/products/{id}` | Eliminar producto |

---

# Manejo de errores

La API implementa un manejo centralizado de excepciones, retornando respuestas consistentes para errores de negocio y validaciones.

Se controlan escenarios como:

- Recursos inexistentes.
- Recursos duplicados.
- Campos obligatorios.
- Longitud máxima de nombres.
- Validaciones de entrada.

## Códigos HTTP

| Código | Descripción |
|---------|-------------|
| 200 | OK |
| 201 | Created |
| 204 | No Content |
| 400 | Bad Request |
| 404 | Resource Not Found |
| 409 | Conflict |
| 500 | Internal Server Error |

### Ejemplo

```json
{
  "timestamp": "2026-08-06T12:30:25",
  "status": 404,
  "error": "Not Found",
  "message": "Franchise with id 100 not found.",
  "path": "/api/franchises/100"
}
```

---

# Colección Postman

La colección de Postman incluida en el proyecto contiene todos los endpoints necesarios para probar la API.

Ubicación:

```
postman/
```

Variable:

| Variable | Valor |
|----------|-------|
| baseUrl | http://localhost:8080/api |

---

# Consideraciones

- La base de datos es creada automáticamente mediante Flyway.
- Se incluyen datos iniciales para facilitar las pruebas.
- La lógica de negocio se encuentra completamente desacoplada de la infraestructura.
- La aplicación implementa Arquitectura Hexagonal (Ports & Adapters).
- El acceso a datos se realiza mediante Spring Data JPA.
- La validación de solicitudes se realiza mediante Bean Validation.
- Los casos de uso son verificados mediante pruebas unitarias con Mockito.
- La cobertura del código puede generarse mediante JaCoCo.
- Docker se utiliza para la ejecución de la base de datos MySQL.

---

# Autor

**Jaime Flórez Saldaña**

Software Engineer

- GitHub: https://github.com/jfs-code
- LinkedIn: https://www.linkedin.com/in/jaimeflorezsaldana/

---

# Licencia

Este proyecto fue desarrollado como solución a una prueba técnica y tiene fines exclusivamente demostrativos.