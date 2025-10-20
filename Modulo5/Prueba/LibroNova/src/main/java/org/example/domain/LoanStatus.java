package org.example.domain;

public class LoanStatus {
    private int id;
    private String status;

    public LoanStatus() {}

    public LoanStatus(int id) {
        this.id = id;
        switch (id) {
            case 1 -> this.status = "activo";
            case 2 -> this.status = "entregado";
            case 3 -> this.status = "retrasado";
            default -> this.status = "desconocido";
        }
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
