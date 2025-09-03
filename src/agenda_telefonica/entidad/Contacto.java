package agenda_telefonica.entidad;


//Atributos de contacto

public class Contacto {
    private String nombre;
    private String apellido;
    private String telefono;

//    Se crea constructor con parametros ya que se solicita que no tenga campos vacios
//    @throws IllegalArgumentException si los datos no son validos

    public Contacto(String nombre, String apellido, String telefono) {
        validarDatos(nombre, apellido, telefono);
        this.nombre = nombre.trim();
        this.apellido = apellido.trim();
        this.telefono = telefono.trim();
    }


//    Metodo para validar campos nombre, apellido y telefono

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

//    Metodo para válidar formato de telefono

    private boolean validarFormatoTelefono(String telefono) {
        // Expresion regular patron para 10 numeros
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
            throw new IllegalArgumentException("Ingrese un número de telefono");
        }
        if (!validarFormatoTelefono(telefono.trim())) {
            throw new IllegalArgumentException("Formato de telefono inválido. Debe contener 10 números (ej: 321 2583654)");
        }
        this.telefono = telefono.trim();
    }

    /**
     * Compara dos contactos para determinar si son iguales (sin distinguir mayusculas/minusculas )
     * Dos contactos son iguales si tienen el mismo nombre y apellido ()
     * obj El objeto a comparar
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


//     Representa en cadena el contacto

    @Override
    public String toString() {
        return String.format("Nombre: %s %s, Telefono: %s", nombre, apellido, telefono);
    }
}
