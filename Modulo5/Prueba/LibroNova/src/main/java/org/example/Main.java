package org.example;

import org.example.utils.UIHelper;
import org.example.view.LoginView;
import org.example.view.MemberView;
import org.example.view.UserView;

public class Main {
    public static void main(String[] args) {
        switch (LoginView.login()) {
            case 0 -> UserView.menu();
            case 1 -> MemberView.menu();
            default -> UIHelper.showError("Rol desconocido", "Error");
        }
    }
}