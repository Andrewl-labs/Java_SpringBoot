package model;

import javax.swing.*;
import java.util.List;

public class Costumer extends User {

    public Costumer(String name, String email, String password, boolean isActive) {
        super(name, email, password, isActive);
    }

    @Override
    public void enterProgram(List<User> users) {
        boolean logged = true;
        while (logged) {
            String[] costumerActions = {"Ver mi perfil", "Actualizar info", "Cerrar sesión"};
            int action = JOptionPane.showOptionDialog(
                    null, "¿Qué deseas hacer?", "Cliente - Menú Principal",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, costumerActions, costumerActions[0]
            );

            switch (action) {
                case 0 -> checkMyProfile();
                case 1 -> {
                    JTextField nameField = new JTextField();
                    JTextField emailField = new JTextField();
                    JPasswordField passField = new JPasswordField();
                    Object[] message = {
                            "Nombre:", nameField,
                            "Email:", emailField,
                            "Password:", passField
                    };
                    int option = JOptionPane.showConfirmDialog(
                            null, message, "Actualizar datos",
                            JOptionPane.OK_CANCEL_OPTION
                    );
                    if (option == JOptionPane.OK_OPTION) {
                        String user = nameField.getText();
                        String email = emailField.getText();
                        String password = new String(passField.getPassword());
                        setInfo(user, email, password);
                        JOptionPane.showMessageDialog(null, "Información actualizada correctamente.");
                    }
                }
                case 2 -> logged = false;
                default -> JOptionPane.showMessageDialog(null, "Opción incorrecta.");
            }
        }
    }
}
