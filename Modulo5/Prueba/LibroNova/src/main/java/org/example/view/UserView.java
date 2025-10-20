package org.example.view;

import org.example.controller.UserController;
import org.example.utils.UIHelper;

import javax.swing.*;

public class UserView {
    public static void menu(){
        boolean exit = false;
        while (!exit) {
            String[] options = {"Buscar socio", "Agregar libro", "Ver prestamos", "Regisrar usuario",
                    "Activar/Desactiva socio", "Ver catálogo", "Salir"};
            int action = UIHelper.options("Menú", options);
            switch (action){
                case 0 -> {
                    // Search member
                    searchMember();
                }
                case 1 -> {
                    //Add book
                    addBook();
                }
                case 2 -> {
                    //View loans
                    viewLoans();
                }
                case 3 -> {
                    //Register user
                    registerMember();
                }
                case 4 -> {
                    //Activar/Desactiva socio
                    toggleMemberStatus();
                }
                case 5 -> {
                    //Ver catálogo
                    viewCatalog();
                }
                default -> {
                    exit = true;
                }
            }
        }
    }
    private static void searchMember(){
        String id = UIHelper.input("Ingrese el ID del socio:");
        UserController.searchMember(id);
    }
    private static void addBook(){
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField availabilityField = new JTextField();
        Object[] fields = {
                "Título:", titleField,
                "Autor:", authorField,
                "Cantidad: ", availabilityField
        };
        int result = UIHelper.form("Agregar libro", fields);
        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            String author = authorField.getText();
            String availability = availabilityField.getText();
            UserController.addBook(title, author, availability);
        }
    }
    private static void viewLoans(){
        UserController.viewLoans();
    }
    private static void registerMember(){
        JTextField nameField = new JTextField();
        JTextField contactField = new JTextField();
        JTextField passwordField = new JTextField();
        JTextField idField = new JTextField();
        Object[] fields = {
                "ID:", idField,
                "Nombre:", nameField,
                "Contacto:", contactField,
                "Contraseña: ", passwordField
        };
        int result = UIHelper.form("Registrar usuario", fields);
        if (result == JOptionPane.OK_OPTION) {
            String id = idField.getText();
            String name = nameField.getText();
            String contact = contactField.getText();
            String password = passwordField.getText();

            UserController.registerMember(id, name, contact, password);
        }
    }

    private static void toggleMemberStatus(){
        String id = UIHelper.input("Ingrese el ID del socio:");
        UserController.toggleMemberStatus(id);
        UIHelper.show("Estado del socio actualizado.");
    }
    private static void viewCatalog(){
        UserController.viewCatalog();
    }
}
