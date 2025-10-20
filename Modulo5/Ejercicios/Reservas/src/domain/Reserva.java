package domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva {
    private int id;
    private Sala sala;
    private LocalDate fecha;
    private LocalTime inicio;
    private LocalTime fin;

    public Reserva(int id, Sala sala, LocalDate fecha, LocalTime inicio, LocalTime fin) {
        this.id = id;
        this.sala = sala;
        this.fecha = fecha;
        this.inicio = inicio;
        this.fin = fin;
    }

    public Reserva(Sala sala, LocalDate fecha, LocalTime inicio, LocalTime fin) {
        this.sala = sala;
        this.fecha = fecha;
        this.inicio = inicio;
        this.fin = fin;
    }

    public int getId() {
        return id;
    }

    public Sala getSala() {
        return sala;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getInicio() {
        return inicio;
    }

    public LocalTime getFin() {
        return fin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setInicio(LocalTime inicio) {
        this.inicio = inicio;
    }

    public void setFin(LocalTime fin) {
        this.fin = fin;
    }
}
