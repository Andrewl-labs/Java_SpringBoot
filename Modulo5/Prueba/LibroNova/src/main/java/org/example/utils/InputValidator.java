package org.example.utils;

public class InputValidator {
    public static int validateId(String input) {
        try {
            int id = Integer.parseInt(input);
            if (id > 0) {
                return id;
            } else {
                throw new IllegalArgumentException("ID inválido, debe ser un número entero positivo.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID inválido, debe ser un número entero positivo.");
        }
    }

    public static int validateQuantity(String input) {
        try {
            int id = Integer.parseInt(input);
            if (id > 0) {
                return id;
            } else {
                throw new IllegalArgumentException("ID inválido, debe ser un número entero positivo.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Cantidad inválida, debe ser un número entero positivo.");
        }
    }
}
