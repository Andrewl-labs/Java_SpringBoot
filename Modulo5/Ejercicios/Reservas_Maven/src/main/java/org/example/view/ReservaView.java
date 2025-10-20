package org.example.view;

import org.example.controller.ReservaController;
import org.example.utils.UIHelper;

import javax.swing.*;
import java.util.Calendar;
import java.util.Date;

public class ReservaView {
    public static int verMenu() {
        String[] actions = {"Reservar", "Consultar reserva", "Cancelar reserva", "Ver Reservas", "Salir"};
        return UIHelper.menu("Bienvenido", actions);
    }
    public static void crearReserva(){
        JTextField idSalaField = new JTextField();

        // Fecha
        SpinnerDateModel fechaModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        JSpinner fechaSpinner = new JSpinner(fechaModel);
        JSpinner.DateEditor fechaEditor = new JSpinner.DateEditor(fechaSpinner, "dd/MM/yyyy");
        fechaSpinner.setEditor(fechaEditor);

        // Hora de inicio
        SpinnerDateModel inicioModel = new SpinnerDateModel(
                new Date(), null, null, Calendar.MINUTE // incremento por minutos
        );
        JSpinner inicioSpinner = new JSpinner(inicioModel);
        JSpinner.DateEditor inicioEditor = new JSpinner.DateEditor(inicioSpinner, "HH:mm");
        inicioSpinner.setEditor(inicioEditor);

        // Hora de fin
        SpinnerDateModel finModel = new SpinnerDateModel(
                new Date(), null, null, Calendar.MINUTE
        );
        JSpinner finSpinner = new JSpinner(finModel);
        JSpinner.DateEditor finEditor = new JSpinner.DateEditor(finSpinner, "HH:mm");
        finSpinner.setEditor(finEditor);

        Object[] newReservationFields = {
                "ID de la sala: ", idSalaField,
                "\nFecha de la reserva ", fechaSpinner,
                "\nHora de inicio: ", inicioSpinner,
                "Hora de fin: ", finSpinner
        };

        int confirm = UIHelper.confirm("Crear Reserva",newReservationFields);
        if (confirm == JOptionPane.OK_OPTION) {

            String idSala = idSalaField.getText();
            Date fecha = (Date) fechaSpinner.getValue();
            Date horaIni = (Date) inicioSpinner.getValue();
            Date horaFin= (Date) finSpinner.getValue();

            if (idSala.isEmpty()){
                UIHelper.show("Rellena todos los campos!");
                crearReserva();
            } else {
                ReservaController.crearReserva(idSala, fecha, horaIni, horaFin);
            }
        }
    }

    public static void consultarReserva(){
        String id = UIHelper.input("Ingrese el id de la reserva que desea consultar: ");
        ReservaController.consultarReserva(id);
    }

    public static void cancelarReserva(){
        String id = UIHelper.input("Ingrese el id de la reserva que desea cancelar: ");
        ReservaController.cancelarReserva(id);
    }

    public static void verReservas(){
        ReservaController.verReservas();
    }
}
