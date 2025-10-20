package utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormatHelper {

    // Formatos
    private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("EEE d 'de' MMMM 'del' yyyy", Locale.of("es", "ES"));

    private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("hh:mm a");

    // Metodos de formateo
    public static String formatDate(LocalDate date){
        return date.format(FORMATO_FECHA);
    }

    public static String formatHora(LocalTime hora){
        return hora.format(FORMATO_HORA);
    }
}
