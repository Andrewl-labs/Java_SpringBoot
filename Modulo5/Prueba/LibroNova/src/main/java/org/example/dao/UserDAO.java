package org.example.dao;

import org.example.config.ConfigDB;
import org.example.domain.*;
import org.example.exceptions.DataAccessException;
import org.example.exceptions.MemberNotFound;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public Person searchMember(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ? AND rol_id = 2;";
        try (Connection conn = ConfigDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Person(
                        id,
                        rs.getString("nombre"),
                        rs.getString("contacto"),
                        new Role(rs.getInt("rol_id"))
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error al buscar el miembro con id: " + id, e);
        }
    }

    public void addBook(Book newBook){
        String sql = "INSERT INTO libros (titulo, autor, disponibles) VALUES (?,?,?)";
        try (Connection conn = ConfigDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setString(1,newBook.getTitle());
            stmt.setString(2,newBook.getAuthor());
            stmt.setInt(3,newBook.getAvailable());

            stmt.executeUpdate();
        } catch (SQLException e){
            throw new DataAccessException("Error al agregar el libro: "+newBook.getTitle(),e);
        }
    }

    public List<Loan> viewLoans(){
        String sql = "SELECT " +
                "p.id AS id, " +
                "u.nombre AS prestado_a, " +
                "u.contacto AS contacto, " +
                "u.rol_id AS rol_id, " +
                "l.titulo AS titulo_libro, " +
                "l.autor AS autor, " +
                "p.fecha AS fecha_prestamo, " +
                "p.id_estado AS estado_id " +
                "FROM prestamos p " +
                "INNER JOIN usuarios u ON p.id_usuario = u.id " +
                "INNER JOIN libros l ON p.id_libro = l.id " +
                "ORDER BY p.fecha DESC;";
        List<Loan> loans = new ArrayList<>();
        try (Connection conn = ConfigDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                loans.add(new Loan(
                        rs.getInt("id"),
                        new Person(rs.getString("prestado_a"),rs.getString("contacto"), new Role(rs.getInt("rol_id"))),
                        new Book(rs.getString("titulo_libro"), rs.getString("autor")),
                        rs.getDate("fecha_prestamo").toLocalDate(),
                        new LoanStatus(rs.getInt("estado_id"))
                ));
            }
            return loans;
        } catch (SQLException e){
            throw new DataAccessException("Error al obtener los préstamos",e);
        }
    }

    public void registerMember(Person newMember){
        String sql = "INSERT INTO usuarios (id, nombre, contacto, rol_id, contrasena) VALUES (?,?,?,?,?)";
        try (Connection conn = ConfigDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1,newMember.getId());
            stmt.setString(2,newMember.getName());
            stmt.setString(3,newMember.getContact());
            stmt.setInt(4,newMember.getRole().getId());
            stmt.setString(5,newMember.getPassword());

            stmt.executeUpdate();
        } catch (SQLException e){
            throw new DataAccessException("Error al registrar el socio: "+newMember.getName(),e);
        }
    }
    public boolean checkMemberStatus(int id){
        String sql = "SELECT activo FROM usuarios WHERE id = ? AND rol_id = 2;";
        try (Connection conn = ConfigDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("activo");
            } else {
                throw new MemberNotFound("El socio con id " + id + " no está registrado.");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error al verificar el estado del socio con id: " + id, e);
        }
    }

    public void toggleMemberStatus(int id, boolean newStatus){
        String sql = "UPDATE usuarios SET activo = ? WHERE id = ? AND rol_id = 2;";
        try (Connection conn = ConfigDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setBoolean(1, newStatus);
            stmt.setInt(2, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new MemberNotFound("El socio con id " + id + " no está registrado.");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error al actualizar el estado del socio con id: " + id, e);
        }
    }

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
}
