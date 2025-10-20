package org.example.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class InputValidator {
    public static int validarId(String idStr){
        try {
            return Integer.parseInt(idStr);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("El ID debe ser un número válido.");
        }
    }

    public static LocalDate validarFechaStr(String date){
        final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");
        try {
            return LocalDate.parse(date, FORMAT);
        } catch (DateTimeParseException e){
            throw new IllegalArgumentException("La fecha debe ser un dato válido.");
        }
    }

    public static LocalDate validarFecha(Date fecha){
        if (fecha == null) {
            throw new IllegalArgumentException("Formato de fecha inválido");
        }
        return fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    }

    public static LocalTime validarHoraStr(String hora){
        final DateTimeFormatter FORMAT_24H = DateTimeFormatter.ofPattern("H:mm");
        try {
            return LocalTime.parse(hora, FORMAT_24H);
        } catch (DateTimeParseException e){
            throw new IllegalArgumentException("La hora debe estar en un formato válido de 24 horas");
        }
    }

    public static LocalTime validarHora(Date hora){
        if (hora == null) {
            throw new IllegalArgumentException("Formato de hora inválido");
        }
        return hora.toInstant().atZone(ZoneId.systemDefault()).toLocalTime().withSecond(0).withNano(0);
    }
}