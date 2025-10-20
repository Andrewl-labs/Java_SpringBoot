package dao;

// Importamos el modelo y la clase de conexión
import model.Contacto;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactoDAO {

    // ------------------------------ CREATE
    // Inserta un nuevo contacto en la base de datos
    public void insertar(Contacto c) {
        String sql = "INSERT INTO contactos (nombre, telefono, email) VALUES (?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();  // Abre la conexión
             // stmt significa statement, es decir sentencia SQL.
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara la sentencia con parámetros
            // Asignamos los valores a los "?" en el mismo orden
            stmt.setString(1, c.getNombre());
            stmt.setString(2, c.getTelefono());
            stmt.setString(3, c.getEmail());

            // Ejecutamos el INSERT
            stmt.executeUpdate();
            System.out.println("✅ Contacto insertado correctamente");
        } catch (SQLException e) {
            e.printStackTrace(); // Si algo falla, imprime el error
        }
    }

    // ------------------------------ READ
    // Devuelve una lista con todos los contactos almacenados
    public List<Contacto> listar() {
        List<Contacto> lista = new ArrayList<>();
        String sql = "SELECT * FROM contactos";
        try (Connection conn = ConexionBD.getConnection(); // Abrimos conexión
             Statement stmt = conn.createStatement();      // Creamos un Statement simple
             ResultSet rs = stmt.executeQuery(sql)) {      // Ejecutamos el SELECT
            // Recorremos los resultados
            while (rs.next()) {
                lista.add(new Contacto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // ------------------------------ UPDATE
    // Cambia el teléfono de un contacto usando su ID
    public void actualizarTelefono(int id, String nuevoTelefono) {
        String sql = "UPDATE contactos SET telefono=? WHERE id=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nuevoTelefono);
            stmt.setInt(2, id);

            int filas = stmt.executeUpdate(); // Ejecuta el UPDATE
            if (filas > 0) {
                System.out.println("📞 Teléfono actualizado correctamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ------------------------------ DELETE
    // Borra un contacto de la base de datos según su ID
    public void eliminar(int id) {
        String sql = "DELETE FROM contactos WHERE id=?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int filas = stmt.executeUpdate(); // Ejecuta el DELETE
            if (filas > 0) {
                System.out.println("🗑️ Contacto eliminado correctamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
