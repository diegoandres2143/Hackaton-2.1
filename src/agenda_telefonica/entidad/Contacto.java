package agenda_telefonica.entidad;

/**
 * Clase que representa un contacto en la agenda telefónica
 * Un contacto está definido por: nombre, apellido y número de teléfono
 */
public class Contacto {
    private String nombre;
    private String apellido;
    private String telefono;

    /**
     * Constructor de la clase Contacto
     * @param nombre El nombre del contacto (no puede estar vacio)
     * @param apellido El apellido del contacto (no puede estar vacio)
     * @param telefono El numero de telefono (debe tener formato valido)
     * @throws IllegalArgumentException si los datos no son validos
     */
    public Contacto(String nombre, String apellido, String telefono) {
        validarDatos(nombre, apellido, telefono);
        this.nombre = nombre.trim();
        this.apellido = apellido.trim();
        this.telefono = telefono.trim();
    }

    /**
     * Valida que los datos del contacto sean correctos
     * @param nombre El nombre a validar
     * @param apellido El apellido a validar
     * @param telefono El telefono a validar
     * @throws IllegalArgumentException si algun dato no es valido
     */
    private void validarDatos(String nombre, String apellido, String telefono) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacio");
        }
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El telefono no puede estar vacio");
        }
        if (!validarFormatoTelefono(telefono.trim())) {
            throw new IllegalArgumentException("El formato del telefono no es valido. Debe contener exactamente 10 numeros (ejemplo: 1234567890)");
        }
    }

    /**
     * Valida el formato del numero de telefono
     * Acepta exactamente 10 numeros sin espacios ni indicativo
     * @param telefono El numero de telefono a validar
     * @return true si el formato es valido, false en caso contrario
     */
    private boolean validarFormatoTelefono(String telefono) {
        // Patron para exactamente 10 numeros: 1234567890
        String patron = "^\\d{10}$";
        return telefono.matches(patron);
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    // Setter para telefono (con validacion)
    public void setTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El telefono no puede estar vacio");
        }
        if (!validarFormatoTelefono(telefono.trim())) {
            throw new IllegalArgumentException("El formato del telefono no es valido. Debe contener exactamente 10 numeros (ejemplo: 1234567890)");
        }
        this.telefono = telefono.trim();
    }

    /**
     * Compara dos contactos para determinar si son iguales
     * Dos contactos son iguales si tienen el mismo nombre y apellido (sin distinguir mayusculas/minusculas)
     * @param obj El objeto a comparar
     * @return true si los contactos son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Contacto contacto = (Contacto) obj;
        return nombre.equalsIgnoreCase(contacto.nombre) &&
                apellido.equalsIgnoreCase(contacto.apellido);
    }

    /**
     * Genera un hash code basado en el nombre y apellido (sin distinguir mayusculas/minusculas)
     * @return El hash code del contacto
     */
    @Override
    public int hashCode() {
        return (nombre.toLowerCase() + apellido.toLowerCase()).hashCode();
    }

    /**
     * Representacion en cadena del contacto
     * @return Una cadena con la informacion del contacto
     */
    @Override
    public String toString() {
        return String.format("Nombre: %s %s, Telefono: %s", nombre, apellido, telefono);
    }
}
