package service;

import model.Fruta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FrutaService {
    private final List<Fruta> frutas = new ArrayList<>();
    private int nextId = 1;

    public Fruta crear(Fruta fruta){
        fruta.setId(nextId++);
        frutas.add(fruta);
        return fruta;
    }

    public List<Fruta> listar(){
        return new ArrayList<>(frutas);
    }

    public Optional<Fruta> buscarPorId(int id){
        return frutas.stream().filter(f -> f.getId() == id).findFirst();
    }

    public List<Fruta> buscarPorNombre(String nombre){
        List<Fruta> busqueda = new ArrayList<>();
        for (Fruta fruta : frutas){
            if (fruta.getNombre().equals(nombre)){
                busqueda.add(fruta);
            }
        }
        return busqueda;
    }

    public boolean actualizar(int id, String nombre, Double pesoKg, String color, Double precio,
                              String origen, Boolean esOrganica) {
        Optional<Fruta> opt = buscarPorId(id);
        if (opt.isEmpty()) return false;

        Fruta f = opt.get();
        if (nombre != null) f.setNombre(nombre);
        if (pesoKg != null) f.setPesoKg(pesoKg);
        if (color != null) f.setColor(color);
        if (precio != null) f.setPrecio(precio);
        if (origen != null) f.setOrigen(origen);
        if (esOrganica != null) f.setEsOrganica(esOrganica);

        return true;
    }

    public boolean eliminar(int id) {
        return frutas.removeIf(f -> f.getId() == id);
    }

}
