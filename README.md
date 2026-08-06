# Franquicias API

API REST desarrollada en **Spring Boot** para la gestión de franquicias, sucursales y productos.

El proyecto fue desarrollado siguiendo una arquitectura basada en **Ports & Adapters (Arquitectura Hexagonal)**, utilizando MySQL como base de datos, Flyway para el versionamiento del esquema y Docker para la persistencia.

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
- Terraform
- Postman

---

# Arquitectura

El proyecto sigue una arquitectura hexagonal separando claramente el dominio de la infraestructura.

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

---

# Funcionalidades

## Franquicias

- Crear franquicia
- Consultar franquicia
- Listar franquicias
- Actualizar nombre

## Sucursales

- Crear sucursal
- Consultar sucursal
- Listar sucursales por franquicia
- Actualizar nombre

## Productos

- Crear producto
- Consultar producto
- Eliminar producto
- Actualizar stock
- Actualizar nombre
- Consultar producto con mayor stock por sucursal para una franquicia

---

# Base de datos

La aplicación utiliza **MySQL**.

Las migraciones son administradas mediante **Flyway**.

```
backend/src/main/resources/db/migration
```

Las tablas creadas son:

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

Desde la carpeta:

```
docker/
```

Ejecutar:

```bash
docker compose up -d
```

Verificar el contenedor:

```bash
docker ps
```

---

# Ejecución del Backend

Ubicarse en:

```
backend/
```

Ejecutar:

```bash
./mvnw spring-boot:run
```

o desde Visual Studio Code utilizando **Run**.

---

# Endpoints

## Franquicias

| Método | Endpoint |
|---------|----------|
| POST | /api/franchises |
| GET | /api/franchises |
| GET | /api/franchises/{id} |
| PUT | /api/franchises/{id}/name |
| GET | /api/franchises/{id}/branches |
| GET | /api/franchises/{id}/top-stock-products |

---

## Sucursales

| Método | Endpoint |
|---------|----------|
| POST | /api/branches |
| GET | /api/branches/{id} |
| PUT | /api/branches/{id}/name |

---

## Productos

| Método | Endpoint |
|---------|----------|
| POST | /api/products |
| GET | /api/products/{id} |
| PUT | /api/products/{id}/name |
| PATCH | /api/products/{id}/stock |
| DELETE | /api/products/{id} |

---

# Validaciones

La API valida automáticamente:

- Campos obligatorios
- Longitud máxima de nombres
- Recursos inexistentes
- Recursos duplicados

Respuestas HTTP utilizadas:

| Código | Descripción |
|---------|-------------|
| 200 | OK |
| 201 | Created |
| 204 | No Content |
| 400 | Bad Request |
| 404 | Resource Not Found |
| 409 | Duplicate Resource |
| 500 | Internal Server Error |

---

# Colección Postman

En la carpeta:

```
postman/
```

se encuentra la colección completa para probar todos los endpoints de la API.

---

# Docker

La base de datos MySQL se ejecuta mediante Docker Compose.

Persistencia mediante volumen Docker.

---

# Autor

**Jaime Flórez Saldaña** - **Ingeniero de Software**

- GitHub: https://github.com/jfs-code
- LinkedIn: https://www.linkedin.com/in/jaimeflorezsaldana/