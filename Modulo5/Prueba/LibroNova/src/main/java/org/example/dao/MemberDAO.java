package org.example.dao;

import org.example.config.ConfigDB;
import org.example.domain.Book;
import org.example.exceptions.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    public List<Book> viewCatalog(){
        String sql = "SELECT * FROM libros;";
        List<Book> catalog = new ArrayList<>();
        try (Connection conn = ConfigDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                catalog.add(new Book(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("disponibles"),
                        rs.getInt("prestados")
                ));
            }
            return catalog;
        } catch (SQLException e){
            throw new DataAccessException("Error al obtener el catálogo de libros",e);
        }
    }

    public boolean checkBookAvailability(int bookId){
        String sql = "SELECT disponibles FROM libros WHERE id = ?;";
        try (Connection conn = ConfigDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return rs.getInt("disponibles") > 0;
            } else {
                return false;
            }
        } catch (SQLException e){
            throw new DataAccessException("Error al verificar la disponibilidad del libro con id: " + bookId, e);
        }
    }

    public void loanBook(int memberId, int bookId, int statusId) {
        String sqlInsert = "INSERT INTO prestamos (id_libro, id_usuario, fecha, id_estado) VALUES (?, ?, CURDATE(), ?)";
        String sqlUpdate = "UPDATE libros SET disponibles = disponibles - 1, prestados = prestados + 1 WHERE id = ?";

        try (Connection conn = ConfigDB.getConnection();
             PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert);
             PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate)) {

            conn.setAutoCommit(false);

            stmtInsert.setInt(1, bookId);
            stmtInsert.setInt(2, memberId);
            stmtInsert.setInt(3, statusId);
            int filasInsertadas = stmtInsert.executeUpdate();
            if (filasInsertadas <= 0) {
                throw new DataAccessException("No se pudo agregar el préstamo",null);
            }

            stmtUpdate.setInt(1, bookId);
            int filasActualizadas = stmtUpdate.executeUpdate();
            if (filasActualizadas <= 0) {
                throw new DataAccessException("No se pudo actualizar el libro",null);
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                ConfigDB.getConnection().rollback();
            } catch (SQLException ex) {
                throw new DataAccessException("Error al agregar el préstamo", e);
            }
        }
    }

    public boolean checkLoanByUser(int memberId, int bookId) {
        String sql = "SELECT COUNT(*) AS total FROM prestamos WHERE id_usuario = ? AND id_libro = ? AND id_estado = 1";

        try (Connection conn = ConfigDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, memberId);
            stmt.setInt(2, bookId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total") > 0;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new DataAccessException(
                    "Error al verificar si el usuario con ID " + memberId + " tiene un préstamo activo del libro con ID " + bookId, e);
        }
    }


    public void returnBook(int memberId, int bookId) {
        int estadoDevuelto = 2;

        String sqlActualizarPrestamo = "UPDATE prestamos " +
                "SET id_estado = ? " +
                "WHERE id_usuario = ? AND id_libro = ? AND id_estado = 1 " +
                "LIMIT 1";
        String sqlActualizarLibro = "UPDATE libros SET disponibles = disponibles + 1, prestados = prestados - 1 WHERE id = ?";

        try (Connection conn = ConfigDB.getConnection();
             PreparedStatement stmtPrestamo = conn.prepareStatement(sqlActualizarPrestamo);
             PreparedStatement stmtLibro = conn.prepareStatement(sqlActualizarLibro)) {

            conn.setAutoCommit(false);

            stmtPrestamo.setInt(1, estadoDevuelto);
            stmtPrestamo.setInt(2, memberId);
            stmtPrestamo.setInt(3, bookId);

            int filasPrestamo = stmtPrestamo.executeUpdate();
            if (filasPrestamo <= 0) {
                throw new DataAccessException(
                        "No se encontró ningún préstamo activo para el usuario " + memberId + " y el libro " + bookId, null);
            }

            stmtLibro.setInt(1, bookId);
            int filasLibro = stmtLibro.executeUpdate();
            if (filasLibro <= 0) {
                throw new DataAccessException("No se pudo actualizar el libro con ID: " + bookId, null);
            }

            conn.commit();

        } catch (SQLException e) {
            try {
                ConfigDB.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new DataAccessException("Error al devolver el préstamo", e);
        }
    }
}
