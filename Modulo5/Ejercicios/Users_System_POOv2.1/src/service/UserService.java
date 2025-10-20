package service;

import model.User;
import model.Costumer;
import ui.UIHelper;

import javax.swing.*;
import java.util.List;

public class UserService {

    public static User login(List<User> users) {
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        Object[] message = {
                "Email:", userField,
                "Password:", passField
        };
        int option = UIHelper.confirm("Login", message);

        if (option == JOptionPane.OK_OPTION) {
            String email = userField.getText();
            String password = new String(passField.getPassword());

            for (User u : users) {
                if (u.authenticate(email, password)) {
                    return u;
                }
            }
            UIHelper.show("Credenciales inválidas");
        }
        return null;
    }

    public static void register(List<User> users) {
        JTextField newNameField = new JTextField();
        JTextField newEmailField = new JTextField();
        JPasswordField newPassField = new JPasswordField();
        Object[] signUpMessage = {
                "Nombre:", newNameField,
                "Email:", newEmailField,
                "Password:", newPassField
        };
        int option = UIHelper.confirm("Registrarse", signUpMessage);

        if (option == JOptionPane.OK_OPTION) {
            String user = newNameField.getText();
            String email = newEmailField.getText();
            String password = new String(newPassField.getPassword());
            users.add(new Costumer(user, email, password, true));
            UIHelper.show("Usuario creado correctamente.");
        }
    }
}
