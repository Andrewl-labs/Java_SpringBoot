package model;
// Paquete "model": aquí guardamos las clases que representan entidades de negocio
// o tablas de la base de datos. En este caso: "Contacto".

public class Contacto {
    // Atributos privados: representan las columnas de la tabla "contactos".
    private int id;          // ID único del contacto (clave primaria en BD).
    private String nombre;   // Nombre completo del contacto.
    private String telefono; // Número de teléfono.
    private String email;    // Correo electrónico.

    // Constructor completo: se usa cuando ya tenemos un contacto con ID
    // (por ejemplo, cuando leemos desde la base de datos).
    public Contacto(int id, String nombre, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    // Constructor alternativo: cuando vamos a insertar un nuevo contacto
    // y todavía no tiene ID (la BD lo genera automáticamente con AUTO_INCREMENT).
    public Contacto(String nombre, String telefono, String email) {
        this(0, nombre, telefono, email); // llama al otro constructor con id=0.
    }

    // Getters y setters: permiten acceder y modificar los atributos privados.
    // (Se siguen las buenas prácticas de encapsulación en Java).
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }
}
