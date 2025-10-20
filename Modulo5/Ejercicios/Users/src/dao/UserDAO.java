package dao;

import model.User;
import util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    public void crear (User u){
        String sql = "INSERT INTO usuarios (nombre, email, password, estado, rol_id) VALUES (?,?,?,?,?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setString(1,u.getNombre());
            stmt.setString(2,u.getEmail());
            stmt.setString(3, u.getPassword());
            stmt.setString(4,u.getEstado());
            stmt.setInt(5, u.getId_rol());

            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
