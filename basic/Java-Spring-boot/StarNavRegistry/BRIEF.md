# 🛸 Challenge: StarNav Registry (Intergalactic Fleet Management)

## 📝 Contexto
Año 2185. La humanidad se ha expandido por la Vía Láctea y tú has sido contratado por la **Federación Galáctica** para crear el sistema central de registro de naves espaciales. Hasta ahora usaban Excel intergaláctico, pero tras un ataque de piratas informáticos, necesitan una REST API robusta para gestionar su flota.

---
## 🎯 Objetivo
Desarrollar una API que gestione el registro de naves espaciales, su estado operativo y su ubicación en los cuadrantes galácticos.

--- 
## 🛠️ Stack Tecnológico y Dependencias
- Java 21 + Spring Boot 4.0.2.
- Spring Data JPA + H2 Database.
- Lombok + MapStruct.
- Jakarta Validation + SpringDoc OpenAPI.
- JUnit 5 / Mockito (Para testear la lógica de seguridad).

---
## Modelo de Datos: `Starship`

| Campo              | Tipo       | Restricción                                     |
|--------------------|------------|-------------------------------------------------|
| `id`               | Long       | Autoincremental (Primary Key)                   |
| `name`             | String     | Obligatorio, Ej: "Millennium Falcon"            |
| `model`            | String     | Obligatorio.                                    |
| `crewCapacity`     | Integer    | Obligatorio, debe ser > 0                       |
| `status`           | Enum       | `OPERATIONAL`, `UNDER_REPAIR`, `DECOMMISSIONED` |
| `currentQuadrant`  | String     | Formato: "Sector-X" (ej: "Sector-7G")           |
| `lastMaintenance`  | LocalDate  | No puede ser una fecha futura                   |

---
## 🛣️ Contrato de la API (Endpoints)
- `GET /api/ships`: Listar toda la flota.
- `GET /api/ships/{id}`: Ver detalles de una nave específica.
- `POST /api/ships`: Registrar una nueva nave en la flota.
- `PUT /api/ships/{id}/status`: Actualizar el estado (solo el estado, no toda la nave).
- `GET /api/ships/stats`: Un resumen que devuelva:
  - Total de naves en la flota
  - Porcentaje de naves operativas.
  - Nave con mayor capacidad de tripulación.

---
## ✅ Reglas de Negocio y Validaciones
1. **Validación de Tripulación:** Si intentas registrar una nave con `crewCapacity` menor a 1, la API debe rechazarlo con un `400 Bad Request`.
2. **Lógica de Mantenimiento:** Al registrar una nave, si el status es `OPERATIONAL`, la fecha de `lastMaintenance` no puede tener más de 2 años de antigüedad. Si la tiene, debe guardarse automáticamente como `UNDER_REPAIR`.
3. **Manejo de Errores:** Si buscas o intentas actualizar una nave que no existe, la API debe responder con un error 404 y un mensaje temático: *"La nave con ID [X] se ha perdido en el hiperespacio".*

---
## ⭐ Bonus Points (Diferenciación)
- **Búsqueda por Cuadrante:** Añadir un endpoint o filtro para listar naves que estén en un sector específico (ej: `/api/ships?quadrant=Sector-7G`).
- **Soft Delete:** En lugar de borrar la nave con `DELETE`, cambia su estado a `DECOMMISSIONED`.
- **Tests:** Un test unitario que verifique que si la fecha de mantenimiento es muy antigua, el estado cambia a `UNDER_REPAIR` automáticamente.

---
🚀 Requisitos de Entrega
1. El código debe estar en la carpeta `src/main/java/` bajo un paquete base coherente (`ej. com.projects.learning`).
2. Incluir un archivo `README.md` propio dentro de la carpeta del proyecto con instrucciones de ejecución (`mvn spring-boot:run`) y ejemplos de JSON para las peticiones.

---
## 💡 ¿Por qué este proyecto es chulo?
1. **Sales de lo común:** Trabajar con "Cuadrantes" y "Naves" es más motivador que "Productos" y "Precios".
2. **Lógica Condicional:** La regla de negocio de cambiar el estado según la fecha de mantenimiento te obliga a escribir lógica real dentro del Service, no solo guardar datos.
3. **Estadísticas:** El endpoint de `/stats` te obliga a jugar con operaciones de Streams de Java un poco más avanzadas.

---
## 👩‍🚀☄️ ¡Adelante desarrollador intelestelar! 🚀🛰️
