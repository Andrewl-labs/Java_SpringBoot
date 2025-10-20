import security.Autenticable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

abstract class User implements Autenticable {
    String name;
    String email;
    String password;
    boolean isActive;

    public User(String name, String email, String password, boolean isActive) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
    }

    @Override
    public boolean authenticate(String email,String password) {
        return this.password.equals(password) && this.email.equals(email) && isActive;
    }

    @Override
    public void setPassword(String password) {

    }

    abstract void enterProgram(List<User> users);

    void checkMyProfile(){
        JOptionPane.showMessageDialog(null,"Mi perfil:\n - Nombre: "+name+"\n - Email: "+email+(isActive ? "\n - Activo":"\n - Inactivo"));
    }
    String checkProfile(){
        return "- Nombre: "+name+", Email: "+email+(isActive ? ", Activo\n":", Inactivo\n");
    }

    public String getEmail() {
        return email;
    }

    public String setInactive() {
        if (this.isActive){
            this.isActive = false;
            return "Estado cambiado a inactivo.";
        } else {
            return "Este usuario ya se encuentra inactivo.";
        }
    }

    public void setInfo(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}

class Admin extends User {
    public Admin(String name, String email, String password, boolean isActive) {
        super(name, email, password, isActive);
    }

    @Override
    public void enterProgram(List<User> users){
        boolean loged = true;
        while (loged) {
            String[] adminActions = {"Ver mi perfil", "Bloquear usuario", "Lista de usuarios", "Cerrar sesión"};
            int action = JOptionPane.showOptionDialog(
                    null,
                    "¿Qué deseas hacer?",
                    "Admin - Menú Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    adminActions,
                    adminActions[0]
            );
            switch (action) {
                case 0:
                    checkMyProfile();
                    break;
                case 1:
                    boolean modified = false;
                    String email = JOptionPane.showInputDialog(null, "Ingrese el email de la persona que quiere bloquear.");
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
                    break;
                case 2:
                    String usersList = "";
                    for (User u : users) {
                        usersList += u.checkProfile();
                    }
                    JOptionPane.showMessageDialog(null, usersList);
                    break;
                case 3:
                    loged = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción incorrecta.");
            }
        }
    }
}

class Costumer extends User {
    public Costumer(String name, String email, String password, boolean isActive) {
        super(name, email, password, isActive);
    }



    @Override
    public void enterProgram(List<User> users){
        boolean loged = true;
        while (loged) {
            String[] costumerActions = {"Ver mi perfil", "Actualizar info", "Cerrar sesión"};

            int action = JOptionPane.showOptionDialog(
                    null,
                    "¿Qué deseas hacer?",
                    "Cliente - Menú Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    costumerActions,
                    costumerActions[0]
            );
            switch (action) {
                case 0:
                    checkMyProfile();
                    break;
                case 1:
                    JTextField nameField = new JTextField();
                    JTextField emailField = new JTextField();
                    JPasswordField passField = new JPasswordField();
                    Object[] message = {
                            "Username:", nameField,
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
                    break;
                case 2:
                    loged = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción incorrecta.");
            }
        }
    }
}

public class Main {

    public static void main(String[] args) {
        final List<User> users = new ArrayList<>();
        users.add(new Admin("Andres Londono","admin@system.com","123456",true));
        users.add(new Costumer("YOhan exneeider","exneaider@system.com","234567",true));
        users.add(new Costumer("Pablo Jimenez","pajimora@system.com","345678",true));
        User authUser = null;
        boolean exit = false;
        while (!exit) {
            String[] actions = {"Ingresar", "Registrarse", "Salir"};
            int action = JOptionPane.showOptionDialog(
                    null,
                    "¿Qué quieres hacer?",
                    "Bienvenido.",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    actions,
                    actions[0]
            );
            switch (action){
                case 0:
                    JTextField userField = new JTextField();
                    JPasswordField passField = new JPasswordField();
                    Object[] message = {
                            "Username:", userField,
                            "Password:", passField
                    };
                    int option = JOptionPane.showConfirmDialog(
                            null, message, "Login",
                            JOptionPane.OK_CANCEL_OPTION
                    );
                    if (option == JOptionPane.OK_OPTION) {
                        String user = userField.getText();
                        String password = new String(passField.getPassword());
                        for (User u : users) {
                            if (u.authenticate(user, password)) {
                                authUser = u;
                                break;
                            }
                        }
                        if (authUser == null){
                            JOptionPane.showMessageDialog(null, "Credenciales inválidas");
                        }
                        else {
                            authUser.enterProgram(users);
                            authUser = null;
                        }
                    }
                    break;
                case 1:
                    JTextField newNameField = new JTextField();
                    JTextField newEmailField = new JTextField();
                    JPasswordField newPassField = new JPasswordField();
                    Object[] singUpMessage = {
                            "Nombre:", newNameField,
                            "Email:", newEmailField,
                            "Password:", newPassField
                    };
                    int registerOption = JOptionPane.showConfirmDialog(
                            null, singUpMessage, "Registrarse",
                            JOptionPane.OK_CANCEL_OPTION
                    );
                    if (registerOption == JOptionPane.OK_OPTION){
                        String user = newNameField.getText();
                        String email = newEmailField.getText();
                        String password = new String(newPassField.getPassword());
                        users.add(new Costumer(user, email, password, true));
                        JOptionPane.showMessageDialog(null, "Usuario creado correctamente.");
                    }
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null,"Hasta pronto!");
                    exit = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Opción inválida, intenta de nuevo.");
                    break;
            }
        }
    }
}