package org.example.config;

import java.time.LocalTime;

public class SystemConfig {
    public static final LocalTime HORA_APERTURA = LocalTime.of(7, 0);
    public static final LocalTime HORA_CIERRE = LocalTime.of(21, 0);
    public static final int MIN_DURACION_MIN = 30;
    public static final int MAX_DURACION_MIN = 240;
    public static final int ANTICIPACION_MIN_HORAS = 2;
}

