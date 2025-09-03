package agenda_telefonica.entidad;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una agenda telefónica
 * La agenda puede crearse con un tamaño máximo específico o usar un tamaño por defecto de 10 contactos
 */
public class Agenda {
    private List<Contacto> contactos;
    private int tamanoMaximo;
    private static final int TAMANO_POR_DEFECTO = 10;

    /**
     * Constructor que crea una agenda con tamaño por defecto (10 contactos)
     */
    public Agenda() {
        this.tamanoMaximo = TAMANO_POR_DEFECTO;
        this.contactos = new ArrayList<>();
    }

    /**
     * Constructor que crea una agenda con un tamaño máximo específico
     * @param tamanoMaximo El número máximo de contactos que puede contener la agenda
     * @throws IllegalArgumentException si el tamaño máximo es menor o igual a 0
     */
    public Agenda(int tamanoMaximo) {
        if (tamanoMaximo <= 0) {
            throw new IllegalArgumentException("El tamaño máximo debe ser mayor que 0");
        }
        this.tamanoMaximo = tamanoMaximo;
        this.contactos = new ArrayList<>();
    }

    /**
     * Agrega un nuevo contacto a la agenda
     * @param contacto El contacto a agregar
     * @return true si el contacto se agregó exitosamente, false si la agenda está llena o el contacto ya existe
     */
    public boolean agregarContacto(Contacto contacto) {
        if (contacto == null) {
            System.out.println("Error: No se puede agregar un contacto nulo");
            return false;
        }

        if (agendaLlena()) {
            System.out.println("Error: La agenda esta llena. No se puede agregar mas contactos.");
            return false;
        }

        if (contactos.contains(contacto)) {
            System.out.println("Error: Ya existe un contacto con el mismo nombre y apellido: " +
                    contacto.getNombre() + " " + contacto.getApellido());
            return false;
        }

        contactos.add(contacto);
        System.out.println("Contacto agregado exitosamente: " + contacto);
        return true;
    }

    /**
     * Elimina un contacto de la agenda
     * @param contacto El contacto a eliminar
     * @return true si el contacto se eliminó exitosamente, false si no se encontró
     */
    public boolean eliminarContacto(Contacto contacto) {
        if (contacto == null) {
            System.out.println("Error: No se puede eliminar un contacto nulo");
            return false;
        }

        if (contactos.remove(contacto)) {
            System.out.println("Contacto eliminado exitosamente: " + contacto.getNombre() + " " + contacto.getApellido());
            return true;
        } else {
            System.out.println("Error: No se encontro el contacto " + contacto.getNombre() + " " + contacto.getApellido() + " en la agenda");
            return false;
        }
    }

    /**
     * Modifica el telefono de un contacto existente
     * @param nombre El nombre del contacto
     * @param apellido El apellido del contacto
     * @param nuevoTelefono El nuevo numero de telefono
     * @return true si la modificacion fue exitosa, false si el contacto no existe
     */
    public boolean modificarTelefono(String nombre, String apellido, String nuevoTelefono) {
        if (nombre == null || nombre.trim().isEmpty() ||
                apellido == null || apellido.trim().isEmpty() ||
                nuevoTelefono == null || nuevoTelefono.trim().isEmpty()) {
            System.out.println("Error: Los datos no pueden estar vacíos");
            return false;
        }

        // Buscar el contacto por nombre y apellido
        for (Contacto contacto : contactos) {
            if (contacto.getNombre().equalsIgnoreCase(nombre.trim()) &&
                    contacto.getApellido().equalsIgnoreCase(apellido.trim())) {
                try {
                    contacto.setTelefono(nuevoTelefono);
                    System.out.println("Telefono modificado exitosamente para " + nombre + " " + apellido +
                            ". Nuevo telefono: " + nuevoTelefono);
                    return true;
                } catch (IllegalArgumentException e) {
                    System.out.println("Error al modificar el telefono: " + e.getMessage());
                    return false;
                }
            }
        }

        System.out.println("Error: No se encontro el contacto " + nombre + " " + apellido + " en la agenda");
        return false;
    }

    /**
     * Indica si la agenda esta llena
     * @return true si la agenda esta llena, false en caso contrario
     */
    public boolean agendaLlena() {
        return contactos.size() >= tamanoMaximo;
    }

    /**
     * Muestra cuántos contactos más se pueden agregar a la agenda
     * @return El número de espacios libres disponibles
     */
    public int espacioLibres() {
        return tamanoMaximo - contactos.size();
    }

    /**
     * Busca un contacto por nombre y apellido
     * @param nombre El nombre del contacto
     * @param apellido El apellido del contacto
     * @return El contacto encontrado o null si no existe
     */
    public Contacto buscarContacto(String nombre, String apellido) {
        if (nombre == null || apellido == null) {
            return null;
        }

        for (Contacto contacto : contactos) {
            if (contacto.getNombre().equalsIgnoreCase(nombre.trim()) &&
                    contacto.getApellido().equalsIgnoreCase(apellido.trim())) {
                return contacto;
            }
        }
        return null;
    }

    /**
     * Obtiene la lista de todos los contactos
     * @return Una copia de la lista de contactos
     */
    public List<Contacto> getContactos() {
        return new ArrayList<>(contactos);
    }

    /**
     * Obtiene el número actual de contactos en la agenda
     * @return El número de contactos
     */
    public int getNumeroContactos() {
        return contactos.size();
    }

    /**
     * Obtiene el tamaño máximo de la agenda
     * @return El tamaño máximo
     */
    public int getTamanoMaximo() {
        return tamanoMaximo;
    }

    /**
     * Muestra informacion sobre el estado de la agenda
     */
    public void mostrarInformacion() {
        System.out.println("\n=== INFORMACION DE LA AGENDA ===");
        System.out.println("Tamano maximo: " + tamanoMaximo);
        System.out.println("Contactos actuales: " + contactos.size());
        System.out.println("Espacios libres: " + espacioLibres());
        System.out.println("Agenda llena?: " + (agendaLlena() ? "Si" : "No"));
    }

    /**
     * Muestra todos los contactos de la agenda
     */
    public void mostrarContactos() {
        if (contactos.isEmpty()) {
            System.out.println("La agenda esta vacia");
            return;
        }

        System.out.println("\n=== CONTACTOS EN LA AGENDA ===");
        for (int i = 0; i < contactos.size(); i++) {
            System.out.println((i + 1) + ". " + contactos.get(i));
        }
    }

    /**
     * Representación en cadena de la agenda
     * @return Una cadena con la información de la agenda
     */
    @Override
    public String toString() {
        return String.format("Agenda [Contactos: %d/%d, Espacios libres: %d]",
                contactos.size(), tamanoMaximo, espacioLibres());
    }
}
