package org.example.service;

import org.example.config.SystemConfig;
import org.example.dao.ReservaDAO;
import org.example.domain.Reserva;
import org.example.exception.*;
import org.example.utils.FormatHelper;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaService {

    private final ReservaDAO reservaDAO;

    public ReservaService(ReservaDAO reservaDAO) {
        this.reservaDAO = reservaDAO;
    }

    public void crearReserva(Reserva reserva) {

        final LocalDate HOY = LocalDate.now();
        final LocalTime HORA = LocalTime.now();

        if (!reservaDAO.existeSala(reserva.getSala().getId()))
            throw new SalaNoEncontradaException("La sala que intentas reserva no existe.");
        if (reserva.getInicio().isAfter(reserva.getFin()))
            throw new HorarioInvalidoException("La hora de inicio no puede ser después de la hora de finalización");
        if (reserva.getFecha().isBefore(HOY))
            throw new FechaException("La fecha que ingresó ya pasó");
        if (HOY.equals(reserva.getFecha()) && reserva.getInicio().isBefore(HORA))
            throw new HorarioInvalidoException("No puedes reservar para una hora que ya pasó");
        if (HOY.equals(reserva.getFecha()) && reserva.getInicio().isBefore(HORA.plusHours(SystemConfig.ANTICIPACION_MIN_HORAS)))
            throw new HorarioInvalidoException("La reserva debe hacerse con al menos dos horas de anticipación");
        if (Duration.between(reserva.getInicio(), reserva.getFin()).toMinutes() < SystemConfig.MIN_DURACION_MIN)
            throw new DuracionInvalidaException("La reserva debe durar al menos 30 minutos");
        if (Duration.between(reserva.getInicio(), reserva.getFin()).toMinutes() > SystemConfig.MAX_DURACION_MIN)
            throw new DuracionInvalidaException("Las reservas no pueden durar más de 4 horas");
        if (reserva.getInicio().isBefore(SystemConfig.HORA_APERTURA) || reserva.getFin().isAfter(SystemConfig.HORA_CIERRE))
            throw new HorarioInvalidoException("Las reservas empiezan desde las 7am y terminan a las 9pm");

        boolean disponible = reservaDAO.verDisponibilidad(reserva);

        if (disponible) {
            reservaDAO.crearReserva(reserva);
        } else {
            throw new NoDisponibleException("Esta sala ya esta ocupada en esta fecha y hora.");
        }
    }

    public String consultarReserva(int id) {
        Reserva reserva = reservaDAO.consultarReserva(id);
        String fecha = FormatHelper.formatDate(reserva.getFecha());
        String horaIni = FormatHelper.formatHora(reserva.getInicio());
        String horaFin = FormatHelper.formatHora(reserva.getFin());

        return """
                Reserva:
                - Sala # %d
                - Fecha: %s
                - Desde: %s
                - Hasta: %s
                """.formatted(reserva.getSala().getId(), fecha, horaIni, horaFin);
    }

    public void cancelarReserva(int id) {
        reservaDAO.cancelarReserva(id);
    }

    public List<String> verReservas() {
        List<Reserva> reservasList = reservaDAO.verReservas();

        if (reservasList == null || reservasList.isEmpty()) {
            throw new NoHayReservasException("No hay reservas pendientes.");
        }

        List<String> reservas = new ArrayList<>();

        for (Reserva r : reservasList) {
            String fecha = FormatHelper.formatDate(r.getFecha());
            String horaIni = FormatHelper.formatHora(r.getInicio());
            String horaFin = FormatHelper.formatHora(r.getFin());

            reservas.add(
                    String.format(
                            "Reserva ID: %d\n- Sala # %d | Fecha: %s | Desde: %s | Hasta: %s\n",
                            r.getId(), r.getSala().getId(), fecha, horaIni, horaFin
                    )
            );
        }
        return reservas;
    }
}