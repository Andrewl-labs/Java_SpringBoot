package model;

import javax.swing.*;
import java.util.List;

public class Admin extends User {

    public Admin(String name, String email, String password, boolean isActive) {
        super(name, email, password, isActive);
    }

    @Override
    public void enterProgram(List<User> users) {
        boolean logged = true;
        while (logged) {
            String[] adminActions = {"Ver mi perfil", "Bloquear usuario", "Lista de usuarios", "Cerrar sesión"};
            int action = JOptionPane.showOptionDialog(
                    null, "¿Qué deseas hacer?", "Admin - Menú Principal",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, adminActions, adminActions[0]
            );

            switch (action) {
                case 0 -> checkMyProfile();
                case 1 -> {
                    boolean modified = false;
                    String email = JOptionPane.showInputDialog(null, "Ingrese el email del usuario a bloquear:");
                    for (User u : users) {
                        if (u.getEmail().equals(email)) {
                            JOptionPane.showMessageDialog(null, u.setInactive());
                            modified = true;
                            break;
                        }
                    }
                    if (!modified) {
                        JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
                    }
                }
                case 2 -> {
                    StringBuilder usersList = new StringBuilder();
                    for (User u : users) {
                        usersList.append(u.checkProfile());
                    }
                    JOptionPane.showMessageDialog(null, usersList.toString());
                }
                case 3 -> logged = false;
                default -> JOptionPane.showMessageDialog(null, "Opción incorrecta.");
            }
        }
    }
}
