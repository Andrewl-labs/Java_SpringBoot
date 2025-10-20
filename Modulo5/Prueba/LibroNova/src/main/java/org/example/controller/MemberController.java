package org.example.controller;

import org.example.exceptions.DataAccessException;
import org.example.service.MemberService;
import org.example.utils.UIHelper;

public class MemberController {
    private static final MemberService memberService = new MemberService();

    public static void viewCatalog(){
        try {
            StringBuilder sb = new StringBuilder();
            memberService.viewCatalog().forEach(book -> {
                sb.append("Título: ").append(book.getTitle()).append(" - Autor: ").append(book.getAuthor()).append("\n")
                        .append("   Disponibles: ").append(book.getAvailable()).append("\n\n");
            });
            UIHelper.show(!sb.isEmpty() ? sb.toString() : "No hay libros en el catálogo.");
        } catch (DataAccessException e){
            UIHelper.showError(e.getMessage(), "Error de Base de datos");
        }
    }

    public static void loanBook(String memberIdStr, String bookIdStr){
        try {
            int bookId = Integer.parseInt(bookIdStr);
            int memberId = Integer.parseInt(memberIdStr);
            memberService.loanBook(memberId, bookId);
            UIHelper.show("Libro prestado con éxito.");
        } catch (NumberFormatException e){
            UIHelper.showError("ID inválido. Debe ser un número.", "Entrada inválida");
        } catch (IllegalArgumentException e){
            UIHelper.showError(e.getMessage(), "Entrada inválida");
        } catch (DataAccessException e){
            UIHelper.showError(e.getMessage(), "Error de Base de datos");
        }
    }

    public static void returnBook(String memberIdStr, String bookIdStr){
        try {
            int bookId = Integer.parseInt(bookIdStr);
            int memberId = Integer.parseInt(memberIdStr);
            memberService.returnBook(memberId, bookId);
            UIHelper.show("Libro devuelto con éxito.");
        } catch (NumberFormatException e){
            UIHelper.showError("ID inválido. Debe ser un número.", "Entrada inválida");
        } catch (IllegalArgumentException e){
            UIHelper.showError(e.getMessage(), "Entrada inválida");
        } catch (DataAccessException e){
            UIHelper.showError(e.getMessage(), "Error de Base de datos");
        }
    }
}
