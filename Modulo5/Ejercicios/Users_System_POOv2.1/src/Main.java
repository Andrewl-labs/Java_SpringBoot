import model.*;
import service.UserService;
import ui.UIHelper;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final List<User> users = new ArrayList<>();
        users.add(new Admin("Andres Londono", "admin@system.com", "123456", true));
        users.add(new Costumer("Yohan Exneeider", "exneaider@system.com", "234567", true));
        users.add(new Costumer("Pablo Jimenez", "pajimora@system.com", "345678", true));

        boolean exit = false;

        while (!exit) {
            String[] actions = {"Ingresar", "Registrarse", "Salir"};
            int action = UIHelper.menu("Bienvenido", actions);

            switch (action) {
                case 0 -> {
                    User authUser = UserService.login(users);
                    if (authUser != null) {
                        authUser.enterProgram(users);
                    }
                }
                case 1 -> UserService.register(users);
                case 2 -> {
                    UIHelper.show("Hasta pronto!");
                    exit = true;
                }
                default -> UIHelper.show("Opción inválida, intenta de nuevo.");
            }
        }
    }
}
