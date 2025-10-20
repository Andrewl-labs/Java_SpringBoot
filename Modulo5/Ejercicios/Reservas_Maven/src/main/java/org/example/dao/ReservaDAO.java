package org.example.dao;

import org.example.config.ConfigBD;
import org.example.domain.Reserva;
import org.example.domain.Sala;
import org.example.exception.DataAccessException;
import org.example.exception.ReservaNoEncontradaException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    public boolean existeSala(int idSala){
        String sql = "SELECT COUNT(*) FROM salas WHERE id = ?";
        try (Connection conn = ConfigBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1,idSala);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e){
            throw new DataAccessException("Fallo al consultar la sala con id: "+idSala,e);
        }
        return false;
    }

    public boolean verDisponibilidad(Reserva reserva){
        String sql = "SELECT COUNT(*) FROM reservas WHERE id_sala = ? AND fecha = ? " +
                "AND ((inicio < ? AND fin > ? ))";
        try (Connection conn = ConfigBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1, reserva.getSala().getId());
            stmt.setObject(2, reserva.getFecha());
            stmt.setObject(3, reserva.getFin());
            stmt.setObject(4, reserva.getInicio());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                return (rs.getInt(1) == 0);
            }
        } catch (SQLException e){
            throw new DataAccessException("Error al revisar disponibilidad",e);
        }
        return false;
    }

    public void crearReserva(Reserva reserva){
        String sql = "INSERT INTO reservas (id_sala, fecha, inicio, fin) VALUES (?,?,?,?)";
        try (Connection conn = ConfigBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, reserva.getSala().getId());
            stmt.setObject(2, reserva.getFecha());
            stmt.setObject(3, reserva.getInicio());
            stmt.setObject(4, reserva.getFin());

            stmt.executeUpdate();
        } catch (SQLException e){
            throw new DataAccessException("Error al crear la reserva",e);
        }
    }

    public Reserva consultarReserva(int id){
        String sql = "SELECT * FROM reservas WHERE id = ?";
        try (Connection conn = ConfigBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1,id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                return new Reserva(
                        rs.getInt("id"),
                        new Sala(rs.getInt("id_sala")),
                        rs.getObject("fecha", LocalDate.class),
                        rs.getObject("inicio", LocalTime.class),
                        rs.getObject("fin", LocalTime.class)
                );
            } else {
                throw new ReservaNoEncontradaException("No se encontró la reserva con ID: " + id);
            }

        } catch (SQLException e){
            throw new DataAccessException("Error al consultar la reserva con ID " + id, e);
        }
    }

    public void cancelarReserva(int id){
        String sql = "DELETE FROM reservas WHERE id = ?";

        try (Connection conn = ConfigBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1,id);

            int rows = stmt.executeUpdate();

            if (rows<=0) throw new ReservaNoEncontradaException("No se encontró la reserva con ID: " + id);

        } catch (SQLException e){
            throw new DataAccessException("Error al cancelar la reserva con ID " + id, e);
        }
    }

    public List<Reserva> verReservas(){
        String sql = "SELECT * FROM reservas";
        List<Reserva> reservas = new ArrayList<>();

        try (Connection conn = ConfigBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                reservas.add(new Reserva(
                        rs.getInt("id"),
                        new Sala(rs.getInt("id_sala")),
                        rs.getObject("fecha", LocalDate.class),
                        rs.getObject("inicio", LocalTime.class),
                        rs.getObject("fin", LocalTime.class)
                ));
            }

            return reservas;

        } catch (SQLException e){
            throw new DataAccessException("Error al consultar las reservas", e);
        }
    }
}