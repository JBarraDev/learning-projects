# 💰 Challenge: FinTrack API (Personal Finance Tracker)

## 📝 Contexto
Has sido contratado por una startup de Fintech para desarrollar el MVP (Producto Mínimo Viable) de una herramienta de gestión financiera personal. La aplicación debe permitir a los usuarios registrar sus movimientos de dinero (ingresos y gastos) y consultar el estado de sus finanzas en tiempo real.

---
## 🎯 Objetivo
Desarrollar una REST API con Java y Spring Boot que gestione transacciones financieras y realice cálculos automáticos de saldo total.

--- 
## 🛠️ Stack Tecnológico y Dependencias
El proyecto debe utilizar Maven como gestor de dependencias. Para cumplir con los requisitos, es obligatorio incluir las siguientes librerías en tu `pom.xml`:
- **Spring Web**: Para la creación de los controladores REST.
- **Spring Data JPA**: Para la capa de persistencia.
- **H2 Database**: Base de datos en memoria para desarrollo y pruebas.
- **Lombok**: Para agilizar la creación de POJOs (Getters, Setters, Constructors).
- **Spring Boot Validation**: Fundamental para validar los datos de entrada (@NotNull, @Min, etc.).
- **Sring Boot DevTools**: (Opcional) Para recarga automática durante el desarrollo.

---
## Modelo de Datos: `Transaction`
Cada movimiento financiero debe tener los siguientes campos:

| Campo         | Tipo       | Restricción                                 |
|---------------|------------|---------------------------------------------|
| `id`          | Long       | Autoincremental (Primary Key)               |
| `description` | String     | Obligatorio, Máx. 100 caracteres            |
| `amount`      | BigDecimal | Obligatorio, debe ser mayor a 0             |
| `date`        | LocalDate  | Obligatorio (Fecha del movimiento)          |
| `type`        | Enum       | Valores: INCOME (Ingreso) o EXPENSE (Gasto) |
| `category`    | String     | Ejemplo: "Comida", "Sueldo", "Ocio"         |

---
## 🛣️ Contrato de la API (Endpoints)
| Método | Endpoint                   | Descripción                       | Status Esperado   |
|--------|----------------------------|-----------------------------------|-------------------|
| GET    | `api/transactions`         | Obtiene todos los registros.      | `200 OK`          |
| POST   | `api/transactions`         | Crea una nueva transacción.       | `201 Created`     |
| DELETE | `api/transactions/{id}`    | Elimina una transacción.          | `204 No Content ` |
| GET    | `api/transactions/summary` | Retorna el saldo total calculado. | `200 OK`          |

---
## ✅ Reglas de Negocio y Validaciones
Para que el proyecto se considere aprobado, debe cumplir con:
1. Validación de Entrada:
   - Si el `amount` es 0 o negativo, la API debe devolver un `400 Bad Request`.
   - La `description` no puede estar vacía ni ser nula.
2. Lógica del Summary:
   - El endpoint `/summary` debe iterar sobre las transacciones: los `INCOME` suman al total y los `EXPENSE` restan.
3. Manejo de Excepciones:
   - Si se solicita un `DELETE` de un ID inexistente, lanzar una excepción que resulte en un `404 Not Found`.

---
## ⭐ Bonus Points (Diferenciación)
Si terminas los requisitos básicos, puedes añadir:
- **Filtros:** Permitir filtrar por tipo (ej. `/api/transactions?type=EXPENSE`).
- **Documentación:** Configurar **Swagger UI** (SpringDoc OpenAPI) para visualizar la API en `/swagger-ui.html`.
- **Tests:** Crear un test unitario para el servicio que calcula el saldo.

---
🚀 Requisitos de Entrega
1. El código debe estar en la carpeta `src/main/java/` bajo un paquete base coherente (`ej. com.projects.learning`).
2. Incluir un archivo `README.md` propio dentro de la carpeta del proyecto con instrucciones de ejecución (`mvn spring-boot:run`) y ejemplos de JSON para las peticiones.

---
## 💪 ¡A por ello! 🚀
