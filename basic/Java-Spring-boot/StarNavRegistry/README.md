# 🛸 StarNav Registry: Intergalactic Fleet Management
¡Bienvenido al sistema central de registro de la Federación Galáctica! Esta es una REST API robusta diseñada para gestionar la flota estelar en el año 2185, reemplazando los antiguos sistemas vulnerables a piratas informáticos.

---
## 🛠️ Stack Tecnológico
- **Java 21**
- **Spring Boot 4.0.2**
- **H2 Database** (Base de datos en memoria)
- **MapStruct**
- **Lombok**
- **Jakarta Validation**
- **JUnit 5 & Mockito** (Pruebas unitarias)

---
## 🚀 Instalación y Ejecución

1. **Clonar el repositorio:**
```bash
   git clone https://github.com/JBarraDev/learning-projects.git
```
2. **Ejecutar la aplicación:**
```bash
   mvn spring-boot:run
```
3. **Acceder a la consola H2:**
   - URL: `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:stardb`
   - User: `sa` | Password: (en blanco)

---
## 🛣️ API Endpoints
**Naves Estelares**

| Método   | Endpoint                 | Descripción                                   |
|----------|--------------------------|-----------------------------------------------|
| `GET`    | `/api/ships`             | Listar flota (opcional: `?quadrant=Sector-X`) |
| `GET`    | `/api/ships/{id}`        | Detalle de una nave específica                |
| `POST`   | `/api/ships`             | Registrar nueva nave                          |
| `PUT`    | `/api/ships/{id}/status` | Actualizar estado operativo                   |
| `DELETE` | `/api/ships/{id}`        | Retirar nave (Soft Delete -> DECOMMISSIONED)  |
| `GET`    | `/api/ships/stats`       | Estadísticas generales de la flota            |

---
## 📋 Ejemplos de Uso (JSON)
**Crear una nueva nave (`POST /api/ships`)**

```json
{
  "name": "Nova Explorer",
  "model": "Airbus X900",
  "crewCapacity": 30,
  "status": "OPERATIONAL",
  "currentQuadrant": "Sector-3C",
  "lastMaintenance": "2024-01-10"
}
```

**Actualizar estado (`PUT /api/ships/1/status`)**
```json
{
  "status": "UNDER_REPAIR"
}
```

---
## ⚖️ Reglas de Negocio Implementadas
1. **Protocolo de Seguridad de Mantenimiento:** Si una nave se registra como `OPERATIONAL` pero su último mantenimiento fue hace más de **2 años**, el sistema la marca automáticamente como `UNDER_REPAIR`.
2. **Validación de Tripulación:** No se permiten naves con capacidad menor a 1.
3. **Manejo de Errores Temático:** Las naves no encontradas devuelven un error 404 con el mensaje: *"La nave con ID [X] se ha perdido en el hiperespacio"*.
4. **Validación de Cuadrante:** Los sectores deben cumplir el formato estándar de la Federación (ej: `Sector-1A`). 

---
## 🧪 Tests
Para ejecutar las pruebas de lógica de negocio y seguridad:
```bash
   mvn test
```

---
## 🛰️¡Misión cumplida, comandante!🚀