package org.example.domain;

public class Role {
    private int id;
    private String name;

    public Role(int id) {
        this.id = id;
        switch (id) {
            case 1 -> this.name = "usuario";
            case 2 -> this.name = "socio";
            default -> this.name = "unknown";
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
