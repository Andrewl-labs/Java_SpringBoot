package org.example.controller;
// Models

import org.example.dao.ReservaDAO;
import org.example.domain.Reserva;
import org.example.domain.Sala;
import org.example.exception.*;
import org.example.service.ReservaService;
import org.example.utils.InputValidator;
import org.example.utils.UIHelper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class ReservaController {

    private static final ReservaService service = new ReservaService(new ReservaDAO());

    public static void crearReserva(String idStr, Date fechaObj, Date inicioObj, Date finObj){
        try {
            int id = InputValidator.validarId(idStr);
            LocalDate fecha = InputValidator.validarFecha(fechaObj);
            LocalTime inicio = InputValidator.validarHora(inicioObj);
            LocalTime fin = InputValidator.validarHora(finObj);

            Reserva reserva = new Reserva(new Sala(id), fecha, inicio, fin);

            service.crearReserva(reserva);

            UIHelper.show("Reserva creada");
        } catch (IllegalArgumentException e){
            UIHelper.showError(e.getMessage(),"Entrada inválida");
        } catch (NoDisponibleException e){
            UIHelper.showError(e.getMessage(), "No disponible");
        } catch (HorarioInvalidoException e){
            UIHelper.showError(e.getMessage(), "Error de Horario");
        } catch (FechaException e){
            UIHelper.showError(e.getMessage(), "Error en la fecha");
        } catch (DuracionInvalidaException e){
            UIHelper.showError(e.getMessage(), "Error en la duración");
        } catch (SalaNoEncontradaException e){
            UIHelper.showError(e.getMessage(),"Sala no encontrada");
        } catch (DataAccessException e){
            UIHelper.showError(e.getMessage(),"Error de Base de datos");
        }
    }

    public static void consultarReserva(String idStr){
        try {
            int id = InputValidator.validarId(idStr);
            UIHelper.show(service.consultarReserva(id));
        } catch (IllegalArgumentException e){
            UIHelper.showError(e.getMessage(),"Entrada inválida");
        } catch (ReservaNoEncontradaException e){
            UIHelper.showError(e.getMessage(), "Reserva no encontrada");
        }
        catch (DataAccessException e){
            UIHelper.showError(e.getMessage(), "Error de Base de datos");
        }
    }

    public static void cancelarReserva(String idStr){
        try {
            int id = InputValidator.validarId(idStr);

            service.cancelarReserva(id);
            UIHelper.show("Reserva cancelada!");

        } catch (IllegalArgumentException e){
            UIHelper.showError(e.getMessage(),"Entrada inválida");
        } catch (ReservaNoEncontradaException e){
            UIHelper.showError(e.getMessage(), "Reserva no encontrada");
        } catch (DataAccessException e){
            UIHelper.showError(e.getMessage(), "Error de Base de datos");
        }
    }

    public static void verReservas(){
        try {
            List<String> reservas = service.verReservas();
            StringBuilder sb = new StringBuilder();
            for (String r : reservas) {
                sb.append(r).append("\n");
            }
            UIHelper.show(sb.toString());
        } catch (NoHayReservasException e){
            UIHelper.showError(e.getMessage(), "No hay información");
        } catch (DataAccessException e){
            UIHelper.showError(e.getMessage(), "Error de Base de datos");
        }
    }
}
