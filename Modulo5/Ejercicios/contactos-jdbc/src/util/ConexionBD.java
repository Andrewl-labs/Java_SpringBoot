package util;
// El paquete donde está la clase.
// Normalmente se pone en "util" porque esta clase es una utilidad compartida.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// Importamos las clases necesarias para trabajar con JDBC:
// - Connection: representa la conexión activa con la BD.
// - DriverManager: permite obtener la conexión usando el driver adecuado.
// - SQLException: excepción que ocurre si hay error al conectar o ejecutar SQL.

public class ConexionBD {
    // URL de conexión a MySQL.
    // Formato: jdbc:mysql://host:puerto/nombreBD
    // En este caso: base de datos "contactos_db" en el servidor local (localhost) puerto 3306.
    private static final String URL = "jdbc:mysql://localhost:3306/contactos_db";

    // Usuario de la base de datos (puede ser root o un usuario creado para tu app).
    private static final String USER = "Andresl";

    // Contraseña del usuario de la base de datos.
    private static final String PASSWORD = "idQ6kwx+";

    // Metodo público y estático que devuelve una conexión a la base de datos.
    // - static: para poder llamarlo sin necesidad de instanciar la clase.
    // - throws SQLException: obliga a manejar la excepción si falla la conexión.
    public static Connection getConnection() throws SQLException {
        // DriverManager se encarga de buscar el driver JDBC correcto
        // y devolver un objeto Connection usando la URL, usuario y contraseña.
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
