package model;

public class User {
    private int id;
    private String nombre;
    private String email;
    private String password;
    private String estado;
    private int id_rol;

    public User(int id, String nombre, String email, String password, String estado, int id_rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.estado = estado;
        this.id_rol = id_rol;
    }

    public User(String nombre, String email, String password, String estado, int id_rol) {
        this.id = 0;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.estado = estado;
        this.id_rol = id_rol;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getEstado() {
        return estado;
    }

    public int getId_rol() {
        return id_rol;
    }
}
