package domain;

public class Sala {
    private int id;
    private String name;
    private String ubicacion;
    private int capacidad;

    public Sala(int id, String name, String ubicacion, int capacidad) {
        this.id = id;
        this.name = name;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
    }

    public Sala(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
