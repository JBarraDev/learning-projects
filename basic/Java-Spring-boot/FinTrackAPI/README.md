# 💰 FinTrack API - Personal Finance Tracker

Este proyecto es una REST API desarrollada con **Spring Boot** diseñada para gestionar finanzas personales. 
Permite el registro de ingresos y gastos, proporcionando un balance en tiempo real del estado financiero.

Este es el primer proyecto del nivel **Basic** en mi roadmap de aprendizaje.

---
## 🛠️ Tecnologías utilizadas
* **Java 21**
* **Spring Boot 4.0.2**
* **Spring Data JPA** (Persistencia)
* **H2 Database** (Base de datos en memoria)
* **Lombok** (Productividad)
* **Maven** (Gestión de dependencias)

---
## 🚀 Instalación y Ejecución

1. **Clonar el repositorio:**
   ```bash
   git clone [https://github.com/JBarraDev/learning-projects](https://github.com/JBarraDev/learning-projects.git)
   cd learning-projects/basic/Java-Spring-boot/FinTrackAPI
   ```

2. **Ejecutar con Maven**
   ```bash
    ./mvnw spring-boot:run
   ```
   La API estará disponible en: http://localhost:8080


3. **Acceso a la Base de Datos (H2 Console):** Puedes ver las tablas en tiempo real en: `http://localhost:8080/h2-console`
- JDBC URL: jdbc:h2:mem:fintrackdb
- User: sa
- Password: (vacío)

---
## 📑 Documentación de la API (Endpoints)

| Acción   | Método   | Endpoint                    | Descripción                        |
|----------|----------|-----------------------------|------------------------------------|
| Listar   | `GET`    | `/api/transactions`         | Obtiene el historial completo.     |
| Crear    | `POST`   | `/api/transactions`         | Registra un nuevo ingreso o gasto. |
| Eliminar | `DELETE` | `/api/transactions/{id}`    | Borra un registro por su ID.       |
| Resumen  | `GET`    | `/api/transactions/summary` | Muestra el saldo total acumulado.  |

### 📖 Documentación Interactiva
Una vez que la aplicación esté en marcha, puedes explorar y probar todos los endpoints desde la interfaz visual de **Swagger UI**:
👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### 📝 Ejemplo de JSON para POST (Crear Transacción)

```json
{
  "description": "Sueldo mensual",
  "amount": 2500.00,
  "date": "2024-03-01",
  "TransactionType": "INCOME",
  "category": "Trabajo"
}
```

### 📝 Ejemplo de Respuesta Summary
```json
{
  "totalBalance": 1850.50,
  "totalIncomes": 2500.00,
  "totalExpenses": 649.50
}
```

---
## 🧪 Pruebas de funcionamiento
Para probar la API usamos **Postman**

---
🧠## 🧠 Aprendizajes Clave
En este proyecto he consolidado conceptos fundamentales de desarrollo backend:

- ✅ **Arquitectura de capas:** Separación clara de responsabilidades (Controller, Service, Repository).
- ✅ **Mapeo de objetos (MapStruct):** Implementación de DTOs para proteger la integridad de las entidades de base de datos.
- ✅ **Precisión financiera:** Uso de `BigDecimal` para evitar errores de redondeo en cálculos monetarios.
- ✅ **Validaciones robustas:** Uso de Jakarta Bean Validation para asegurar que los datos de entrada son correctos antes de procesarlos.
- ✅ **Testing Unitario:** Pruebas de lógica de negocio utilizando **Mockito** para simular comportamientos del repositorio.
- ✅ **Documentación Automática:** Integración de Swagger UI para facilitar las pruebas de los endpoints.

---
## 💡 Un último detalle

Para que el proyecto esté completo, asegúrate de que en tu `src/main/resources/application.properties` tengas configurado el acceso a la consola H2, de lo contrario no podrás entrar a ver las tablas:

```properties
spring.application.name=FinTrackAPI
spring.datasource.url=jdbc:h2:mem:fintrackdb
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
```
---
