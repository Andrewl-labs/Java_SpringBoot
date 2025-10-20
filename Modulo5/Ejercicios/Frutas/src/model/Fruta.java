package model;

public class Fruta {
    private int id;
    private String nombre;
    private double pesoKg;
    private String color;
    private double precio;
    private String origen;
    private boolean esOrganica;

    public Fruta(int id, String nombre, double pesoKg, String color, double precio, String origen, boolean esOrganica) {
        this.id = id;
        this.nombre = nombre;
        this.pesoKg = pesoKg;
        this.color = color;
        this.precio = precio;
        this.origen = origen;
        this.esOrganica = esOrganica;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setEsOrganica(boolean esOrganica) {
        this.esOrganica = esOrganica;
    }

    public String toString() {
        return "Fruta { " +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", pesoKg=" + pesoKg +
                ", color='" + color + '\'' +
                ", precio=" + precio +
                (origen != null && !origen.isEmpty() ? ", origen='" + origen + '\'' : "") +
                ", esOrganica=" + esOrganica +
                " }";
    }
}
