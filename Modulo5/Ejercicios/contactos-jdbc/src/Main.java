import dao.ContactoDAO;
import model.Contacto;

public class Main {
    public static void main(String[] args) {
        ContactoDAO dao = new ContactoDAO();

        // Insertar contacto
        Contacto c1 = new Contacto("Juan Pérez", "123456789", "juan@mail.com");
        dao.insertar(c1);

        // Listar
        System.out.println("📋 Lista de contactos:");
        dao.listar().forEach(c ->
                System.out.println(c.getId() + " - " + c.getNombre() + " - " + c.getTelefono() + " - " + c.getEmail())
        );

        // Actualizar
        dao.actualizarTelefono(1, "987654321");

        // Eliminar
        dao.eliminar(1);
    }
}

