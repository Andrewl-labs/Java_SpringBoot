import model.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final List<User> users = new ArrayList<>();
        users.add(new Admin("Andres Londono", "admin@system.com", "123456", true));
        users.add(new Costumer("Yohan Exneeider", "exneaider@system.com", "234567", true));
        users.add(new Costumer("Pablo Jimenez", "pajimora@system.com", "345678", true));

        User authUser = null;
        boolean exit = false;

        while (!exit) {
            String[] actions = {"Ingresar", "Registrarse", "Salir"};
            int action = JOptionPane.showOptionDialog(
                    null, "¿Qué quieres hacer?", "Bienvenido.",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, actions, actions[0]
            );

            switch (action) {
                case 0 -> {
                    JTextField userField = new JTextField();
                    JPasswordField passField = new JPasswordField();
                    Object[] message = {
                            "Email:", userField,
                            "Password:", passField
                    };
                    int option = JOptionPane.showConfirmDialog(
                            null, message, "Login",
                            JOptionPane.OK_CANCEL_OPTION
                    );
                    if (option == JOptionPane.OK_OPTION) {
                        String email = userField.getText();
                        String password = new String(passField.getPassword());
                        for (User u : users) {
                            if (u.authenticate(email, password)) {
                                authUser = u;
                                break;
                            }
                        }
                        if (authUser == null) {
                            JOptionPane.showMessageDialog(null, "Credenciales inválidas");
                        } else {
                            authUser.enterProgram(users);
                            authUser = null;
                        }
                    }
                }
                case 1 -> {
                    JTextField newNameField = new JTextField();
                    JTextField newEmailField = new JTextField();
                    JPasswordField newPassField = new JPasswordField();
                    Object[] signUpMessage = {
                            "Nombre:", newNameField,
                            "Email:", newEmailField,
                            "Password:", newPassField
                    };
                    int registerOption = JOptionPane.showConfirmDialog(
                            null, signUpMessage, "Registrarse",
                            JOptionPane.OK_CANCEL_OPTION
                    );
                    if (registerOption == JOptionPane.OK_OPTION) {
                        String user = newNameField.getText();
                        String email = newEmailField.getText();
                        String password = new String(newPassField.getPassword());
                        users.add(new Costumer(user, email, password, true));
                        JOptionPane.showMessageDialog(null, "Usuario creado correctamente.");
                    }
                }
                case 2 -> {
                    JOptionPane.showMessageDialog(null, "Hasta pronto!");
                    exit = true;
                }
                default -> JOptionPane.showMessageDialog(null, "Opción inválida, intenta de nuevo.");
            }
        }
    }
}
