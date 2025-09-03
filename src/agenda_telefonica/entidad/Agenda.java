package agenda_telefonica.entidad;

import java.util.ArrayList;

public class Agenda {
    // Lista de contactos (atributo de la clase)
    private ArrayList<Contacto> contactos;

    // Constructor: inicializa la lista
    public Agenda() {
        contactos = new ArrayList<>();
    }

    // Método para agregar un contacto
    public void add(Contacto c) {
        contactos.add(c);
    }

    // Método para mostrar todos los contactos
    public void mostrarContactos() {
        for (Contacto c : contactos) {
            System.out.println(c);
        }
    }
}
