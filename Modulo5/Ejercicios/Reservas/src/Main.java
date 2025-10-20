import utils.UIHelper;
import view.ReservaView;

public class Main {
    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            int action = ReservaView.verMenu();
            switch (action){
                case 0 ->{
                    ReservaView.crearReserva();
                }
                case 1 -> {
                    ReservaView.consultarReserva();
                }
                case 2 -> {
                    ReservaView.cancelarReserva();
                }
                case 3 -> {
                    ReservaView.verReservas();
                }
                default -> {
                    UIHelper.show("Gracias por usar el sistema, Adios!");
                    exit = true;
                }
            }
        }
    }
}