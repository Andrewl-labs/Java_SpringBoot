package org.example.view;

import org.example.utils.UIHelper;

public class LoginView {
    public static int login(){
        String[] roles = {"Usuario","Socio"};
        return UIHelper.options("Inicio de sesión",roles);
    }
}
