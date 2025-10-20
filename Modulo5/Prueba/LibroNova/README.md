
# Proyecto LibroNova
**Sistema de gestión de préstamos de libros**

---

## Descripción general

**LibroNova** es una aplicación desarrollada en **Java** que permite administrar el préstamo y devolución de libros en una biblioteca o librería.

El sistema gestiona usuarios, roles, libros, préstamos y estados, garantizando la integridad de la información mediante una base de datos **MySQL**.

---

## ⚙️ Arquitectura del proyecto

El proyecto sigue una arquitectura **MVC simplificada (Modelo – Vista – Controlador)**, estructurada de la siguiente forma:

```
src/
├── org/example/config/       → Configuración de conexión a BD
├── org/example/dao/          → Acceso a datos (Data Access Objects)
├── org/example/model/        → Clases modelo o entidades
├── org/example/service/      → Lógica de negocio
├── org/example/ui/           → Interfaz (interacción con usuario)
└── org/example/utils/        → Utilidades auxiliares
```

---

## Configuración de la Base de Datos

### Tabla de ejemplo

Para usar el programa se deberá crear la siguiente base de datos:

```
CREATE DATABASE IF NOT EXISTS libroNovaDB;
USE libroNovaDB;

CREATE TABLE roles (
    id INT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE usuarios (
    id INT PRIMARY KEY,                -- ahora representa la identificación
    nombre VARCHAR(100) NOT NULL,
    contacto VARCHAR(15),              -- número de teléfono
    rol_id INT,
    contrasena VARCHAR(50),
    FOREIGN KEY (rol_id) REFERENCES roles(id)
);

CREATE TABLE libros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    autor VARCHAR(100),
    disponibles INT NOT NULL,
    prestados INT NOT NULL
);

CREATE TABLE estados_prestamos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    estado VARCHAR(50) NOT NULL
);

CREATE TABLE prestamos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_libro INT,
    id_usuario INT,
    fecha DATE,
    id_estado INT,
    FOREIGN KEY (id_libro) REFERENCES libros(id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    FOREIGN KEY (id_estado) REFERENCES estados_prestamos(id)
);

-- DATOS INICIALES ====================================================================

-- Roles
INSERT INTO roles (id, nombre) VALUES
(1, 'usuario'),
(2, 'socio');

-- Usuarios (2 usuarios + 5 socios)
INSERT INTO usuarios (id, nombre, contacto, rol_id, contrasena) VALUES
(1001, 'Ana Torres', '3104567890', 1, 'usr1'),
(1002, 'Carlos Vega', '3129876543', 1, 'usr2'),
(2001, 'María Gómez', '3111111111', 2, 'socio1'),
(2002, 'Luis Pérez', '3112222222', 2, 'socio2'),
(2003, 'Sofía Ramírez', '3113333333', 2, 'socio3'),
(2004, 'Pedro López', '3114444444', 2, 'socio4'),
(2005, 'Laura Díaz', '3115555555', 2, 'socio5');

-- Libros
INSERT INTO libros (titulo, autor, disponibles, prestados) VALUES
('Cien Años de Soledad', 'Gabriel García Márquez', 3, 2),
('1984', 'George Orwell', 4, 1),
('Don Quijote de la Mancha', 'Miguel de Cervantes', 2, 3),
('El Principito', 'Antoine de Saint-Exupéry', 5, 0),
('Rayuela', 'Julio Cortázar', 3, 2);

-- Estados de préstamo
INSERT INTO estados_prestamos (estado) VALUES
('activo'),
('en curso'),
('retrasado');

-- Préstamos (solo socios)
INSERT INTO prestamos (id_libro, id_usuario, fecha, id_estado) VALUES
(1, 2001, '2025-10-01', 1),
(2, 2002, '2025-10-02', 2),
(3, 2003, '2025-09-28', 3),
(4, 2004, '2025-10-03', 1),
(5, 2005, '2025-09-30', 2),
(1, 2002, '2025-10-05', 1),
(2, 2001, '2025-10-06', 2),
(3, 2004, '2025-09-25', 3),
(5, 2003, '2025-10-07', 1),
(4, 2005, '2025-10-08', 2);

-- MODIFICACIONES ====================================================================

ALTER TABLE libros
MODIFY prestados INT NOT NULL DEFAULT 0;

ALTER TABLE usuarios
ADD COLUMN activo TINYINT(1) DEFAULT 1;

```

### 🔧 Clase: `ConfigDB`

Ubicación: `src/org/example/config/ConfigDB.java`

Configurar usuario y contraseña

```java
public class ConfigDB {
    private static final String URL = "jdbc:mysql://localhost:3306/libroNovaDB";
    private static final String USER = "";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
```

#### 📘 Descripción

Esta clase administra la conexión con la base de datos MySQL.  
Centraliza las credenciales de acceso (`URL`, `USER`, `PASSWORD`) y ofrece el método `getConnection()` para obtener un objeto `Connection` activo.

#### 🔍 Detalles técnicos

| Propiedad | Descripción |
|------------|--------------|
| **URL** | Dirección de conexión al servidor MySQL y nombre de la base de datos. |
| **USER** | Usuario autorizado a conectarse. |
| **PASSWORD** | Contraseña del usuario. |
| **getConnection()** | Retorna un objeto `java.sql.Connection` activo o lanza una excepción `SQLException` si ocurre un error. |

---

## 💾 Capa DAO

Los DAO (`Data Access Object`) son las clases responsables de comunicarse directamente con la base de datos.  
Ejemplo: `PrestamoDAO`, `LibroDAO`, `UsuarioDAO`.

Cada DAO:
- Usa `ConfigDB.getConnection()` para conectarse.
- Implementa métodos CRUD (`crear`, `leer`, `actualizar`, `eliminar`).
- Puede manejar transacciones (`commit`, `rollback`) para asegurar consistencia.

---

## 🧠 Capa Modelo

Representa las **entidades** del sistema:
- `Usuario`
- `Libro`
- `Prestamo`
- `Rol`
- `Estado`

Cada clase tiene atributos, constructores, getters/setters y, opcionalmente, métodos `toString()` para depuración.

---

## Capa Servicio (Lógica de negocio)

Coordina las operaciones entre los DAOs y las reglas del sistema.  
Por ejemplo:
- Validar que un libro esté disponible antes de prestarlo.
- Cambiar el estado del préstamo a “Devuelto” al registrar una devolución.

---

## Interfaz o Consola (UI)

Permite la interacción con el usuario a través de menús o formularios, dependiendo del enfoque del proyecto.  
Ejemplo:
```text
1. Registrar préstamo
2. Devolver libro
3. Listar préstamos activos
4. Salir
```

---

## Manejo de Errores y Excepciones

El proyecto utiliza una clase personalizada `DataAccessException` para encapsular los errores de base de datos, ofreciendo mensajes más claros y controlados.

---

## 🚀 Ejecución del proyecto

1. Configura la base de datos en MySQL (`libroNovaDB`).
2. Ajusta usuario y contraseña en `ConfigDB.java`.
3. Ejecuta la clase principal `Main.java`.

---

## 📄 Créditos

- **Autor:** Andres
- **Versión:** 1.0
- **Lenguaje:** Java
- **Base de datos:** MySQL 8.x
- **Conexión JDBC:** MySQL Connector/J  