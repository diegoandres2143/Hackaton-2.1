package agenda_telefonica.entidad;

import java.util.List;

/**
 * Clase que demuestra los métodos de las clases Contacto y Agenda
 * Este archivo muestra el uso programático de las clases sin menú interactivo
 */
public class Metodos {
    public static void main(String[] args) {
        System.out.println("=== EJEMPLO DE USO DEL SISTEMA DE AGENDA ===\n");

        // Crear una agenda con tamaño personalizado
        Agenda agenda = new Agenda(5);
        System.out.println("Agenda creada con capacidad de 5 contactos");
        agenda.mostrarInformacion();

        // Crear algunos contactos
        System.out.println("\n--- Agregando contactos ---");
        try {
            Contacto contacto1 = new Contacto("Juan", "Pérez", "+34 123 456 789");
            Contacto contacto2 = new Contacto("María", "García", "987 654 321");
            Contacto contacto3 = new Contacto("Carlos", "López", "+1 555 123 456");

            agenda.agregarContacto(contacto1);
            agenda.agregarContacto(contacto2);
            agenda.agregarContacto(contacto3);

        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear contacto: " + e.getMessage());
        }

        // Mostrar todos los contactos
        agenda.mostrarContactos();

        // Verificar espacios libres
        System.out.println("\n--- Verificando espacios libres ---");
        System.out.println("Espacios libres: " + agenda.espacioLibres());
        System.out.println("¿Agenda llena?: " + agenda.agendaLlena());

        // Buscar un contacto
        System.out.println("\n--- Buscando contacto ---");
        Contacto encontrado = agenda.buscarContacto("María", "García");
        if (encontrado != null) {
            System.out.println("Contacto encontrado: " + encontrado);
        } else {
            System.out.println("Contacto no encontrado");
        }

        // Modificar teléfono
        System.out.println("\n--- Modificando teléfono ---");
        agenda.modificarTelefono("María", "García", "+34 999 888 777");

        // Mostrar contactos actualizados
        agenda.mostrarContactos();

        // Intentar agregar contacto duplicado
        System.out.println("\n--- Intentando agregar contacto duplicado ---");
        try {
            Contacto duplicado = new Contacto("juan", "pérez", "111 222 333"); // Mismo nombre pero en minúsculas
            agenda.agregarContacto(duplicado);
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear contacto: " + e.getMessage());
        }

        // Eliminar un contacto
        System.out.println("\n--- Eliminando contacto ---");
        Contacto aEliminar = agenda.buscarContacto("Carlos", "López");
        if (aEliminar != null) {
            agenda.eliminarContacto(aEliminar);
        }

        // Mostrar estado final
        System.out.println("\n--- Estado final de la agenda ---");
        agenda.mostrarInformacion();
        agenda.mostrarContactos();

        // Probar validaciones
        System.out.println("\n--- Probando validaciones ---");
        try {
            Contacto contactoInvalido = new Contacto("", "Apellido", "123 456 789");
        } catch (IllegalArgumentException e) {
            System.out.println("Validación de nombre vacío: " + e.getMessage());
        }

        try {
            Contacto telefonoInvalido = new Contacto("Nombre", "Apellido", "123456789");
        } catch (IllegalArgumentException e) {
            System.out.println("Validación de formato de teléfono: " + e.getMessage());
        }

        System.out.println("\n=== FIN DEL EJEMPLO ===");
    }
}