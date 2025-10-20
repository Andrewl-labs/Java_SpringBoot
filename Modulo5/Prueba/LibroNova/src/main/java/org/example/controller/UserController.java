package org.example.controller;

import org.example.domain.Book;
import org.example.domain.Person;
import org.example.exceptions.DataAccessException;
import org.example.exceptions.MemberNotFound;
import org.example.service.UserService;
import org.example.utils.InputValidator;
import org.example.utils.UIHelper;

public class UserController {

    private static final UserService userService = new UserService();

    public static void searchMember(String idStr){
        try {
            int id = InputValidator.validateId(idStr);
            Person member = userService.searchMember(id);
            String info = member.getName() + ":\n" +
                    "- ID: " + member.getId() + "\n" +
                    "- Contacto: " + member.getContact() + "\n" +
                    "- Rol: " + member.getRole().getName();
            UIHelper.show(info);
        } catch (IllegalArgumentException e){
            UIHelper.showError(e.getMessage(), "Entrada inválida");
        } catch (MemberNotFound e){
            UIHelper.showError(e.getMessage(), "Socio no encontrado");
        } catch (DataAccessException e){
            UIHelper.showError(e.getMessage(), "Error de Base de datos");
        }
    }

    public static void addBook(String title, String author, String availabilityStr){
        try {
            int availability = InputValidator.validateQuantity(availabilityStr);
            userService.addBook(new Book(title,author,availability));
            UIHelper.show("Libro añadido");
        } catch (IllegalArgumentException e){
            UIHelper.showError(e.getMessage(), "Entrada inválida");
        } catch (DataAccessException e){
            UIHelper.showError(e.getMessage(), "Error de Base de datos");
        }
    }

    public static void viewLoans(){
        try {
            StringBuilder sb = new StringBuilder();
            userService.viewLoans().forEach(loan -> {
                sb.append("Préstamo ID: ").append(loan.getId()).append(" - Libro: ").append(loan.getBook().getTitle()).append(" ( ").append(loan.getBook().getAuthor()).append(")\n")

                  .append("   Prestado a: ").append(loan.getMember().getName()).append(" - Contacto: ").append(loan.getMember().getContact()).append("\n")
                  .append("   Fecha de Préstamo: ").append(loan.getDate()).append(" - Estado: ").append(loan.getStatus().getStatus()).append("\n\n");
            });
            UIHelper.show(!sb.isEmpty() ? sb.toString() : "No hay préstamos registrados.");
        } catch (DataAccessException e){
            UIHelper.showError(e.getMessage(), "Error de Base de datos");
        }
    }
    public static void registerMember(String idStr, String name, String contact, String password){
        try {
            int id = InputValidator.validateId(idStr);

            Person newMember = new Person(id, name, contact, password);
            userService.registerMember(newMember);
            UIHelper.show("Socio registrado con éxito:\n" +
                    "- Nombre: " + newMember.getName() + "\n" +
                    "- Contacto: " + newMember.getContact() + "\n" +
                    "- Rol: " + newMember.getRole().getName());
        } catch (IllegalArgumentException e){
            UIHelper.showError(e.getMessage(), "Entrada inválida");
        } catch (DataAccessException e){
            UIHelper.showError(e.getMessage(), "Error de Base de datos");
        }
    }
    public static void toggleMemberStatus(String idStr){
        try {
            int id = InputValidator.validateId(idStr);
            userService.toggleMemberStatus(id);

        } catch (IllegalArgumentException e){
            UIHelper.showError(e.getMessage(), "Entrada inválida");
        } catch (MemberNotFound e){
            UIHelper.showError(e.getMessage(), "Socio no encontrado");
        } catch (DataAccessException e){
            UIHelper.showError(e.getMessage(), "Error de Base de datos");
        }
    }

    public static void viewCatalog(){
        try {
            StringBuilder sb = new StringBuilder();
            userService.viewCatalog().forEach(book -> {
                sb.append("Título: ").append(book.getTitle()).append(" - Autor: ").append(book.getAuthor()).append("\n")
                  .append("   Disponibles: ").append(book.getAvailable()).append("\n\n");
            });
            UIHelper.show(!sb.isEmpty() ? sb.toString() : "No hay libros en el catálogo.");
        } catch (DataAccessException e){
            UIHelper.showError(e.getMessage(), "Error de Base de datos");
        }
    }

}
