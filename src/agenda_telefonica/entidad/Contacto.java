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
     * @param nombre El nombre del contacto (no puede estar vacío)
     * @param apellido El apellido del contacto (no puede estar vacío)
     * @param telefono El número de teléfono (debe tener formato válido)
     * @throws IllegalArgumentException si los datos no son válidos
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
     * @param telefono El teléfono a validar
     * @throws IllegalArgumentException si algún dato no es válido
     */
    private void validarDatos(String nombre, String apellido, String telefono) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        }
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede estar vacío");
        }
        if (!validarFormatoTelefono(telefono.trim())) {
            throw new IllegalArgumentException("El formato del teléfono no es válido. Use el formato: +XX XXX XXX XXX o XXX XXX XXX");
        }
    }

    /**
     * Valida el formato del número de teléfono
     * Acepta formatos como: +34 123 456 789 o 123 456 789
     * @param telefono El número de teléfono a validar
     * @return true si el formato es válido, false en caso contrario
     */
    private boolean validarFormatoTelefono(String telefono) {
        // Patrón para números con código de país: +XX XXX XXX XXX
        String patronConCodigo = "^\\+\\d{1,3}\\s\\d{3}\\s\\d{3}\\s\\d{3}$";
        // Patrón para números sin código de país: XXX XXX XXX
        String patronSinCodigo = "^\\d{3}\\s\\d{3}\\s\\d{3}$";

        return telefono.matches(patronConCodigo) || telefono.matches(patronSinCodigo);
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

    // Setter para teléfono (con validación)
    public void setTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede estar vacío");
        }
        if (!validarFormatoTelefono(telefono.trim())) {
            throw new IllegalArgumentException("El formato del teléfono no es válido. Use el formato: +XX XXX XXX XXX o XXX XXX XXX");
        }
        this.telefono = telefono.trim();
    }

    /**
     * Compara dos contactos para determinar si son iguales
     * Dos contactos son iguales si tienen el mismo nombre y apellido (sin distinguir mayúsculas/minúsculas)
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
     * Genera un hash code basado en el nombre y apellido (sin distinguir mayúsculas/minúsculas)
     * @return El hash code del contacto
     */
    @Override
    public int hashCode() {
        return (nombre.toLowerCase() + apellido.toLowerCase()).hashCode();
    }

    /**
     * Representación en cadena del contacto
     * @return Una cadena con la información del contacto
     */
    @Override
    public String toString() {
        return String.format("Nombre: %s %s, Teléfono: %s", nombre, apellido, telefono);
    }
}
