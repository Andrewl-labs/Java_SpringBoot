package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    public static Connection objConnection = null;
    public static Connection openConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql//localhost:3306/usuarios_db";
            String user = "Andresl";
            String password = "idQ6kwx+";

            objConnection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa.");
        } catch (ClassNotFoundException error){
            System.out.println("Driver no instalado."+error.getMessage());
        } catch (SQLException error){
            System.out.println("Error al conectar al db."+error.getMessage());
        }
        return objConnection;
    }
}
