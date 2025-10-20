package model;

import security.Autenticable;
import javax.swing.*;
import java.util.List;

public abstract class User implements Autenticable {
    protected String name;
    protected String email;
    protected String password;
    protected boolean isActive;

    public User(String name, String email, String password, boolean isActive) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
    }

    @Override
    public boolean authenticate(String email, String password) {
        return this.password.equals(password) && this.email.equals(email) && isActive;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public abstract void enterProgram(List<User> users);

    public void checkMyProfile() {
        JOptionPane.showMessageDialog(null,
                "Mi perfil:\n - Nombre: " + name +
                        "\n - Email: " + email +
                        (isActive ? "\n - Activo" : "\n - Inactivo"));
    }

    public String checkProfile() {
        return "- Nombre: " + name +
                ", Email: " + email +
                (isActive ? ", Activo\n" : ", Inactivo\n");
    }

    public String getEmail() {
        return email;
    }

    public String setInactive() {
        if (this.isActive) {
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
