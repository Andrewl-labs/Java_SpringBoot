package org.example.view;

import org.example.controller.MemberController;
import org.example.utils.UIHelper;

public class MemberView {
    public static void menu() {
        boolean exit = false;
        while (!exit) {
            String[] options = {"Ver catalogo", "Prestar libro", "Devolver libro", "Salir"};
            int action = UIHelper.options("Menú", options);
            switch (action){
                case 0 -> {
                    //Catalog
                    viewCatalog();
                }
                case 1 -> {
                    //Loan book
                    loanBook();

                }
                case 2 -> {
                    // return book
                    returnBook();
                }
                default -> {
                    exit = true;
                }
            }
        }

    }

    public static void viewCatalog(){
        MemberController.viewCatalog();
    }

    public static void loanBook(){
        String memberId = UIHelper.input("Ingrese su ID de socio:");
        String bookId = UIHelper.input("Ingrese el ID del libro que desea prestar:");
        MemberController.loanBook(memberId, bookId);
    }

    public static void returnBook(){
        String memberId = UIHelper.input("Ingrese su ID de socio:");
        String bookId = UIHelper.input("Ingrese el ID del libro que desea devolver:");
        MemberController.returnBook(memberId, bookId);
    }
}
