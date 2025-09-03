package agenda_telefonica.entidad;

import java.util.Scanner;
import java.util.List;

/**
 * Clase principal que contiene el menú de consola para gestionar la agenda telefónica
 * Permite probar todas las funcionalidades requeridas
 */
public class Main {
    private static Agenda agenda;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        inicializarAgenda();
        mostrarMenu();
    }

    /**
     * Inicializa la agenda con el tamaño especificado por el usuario
     */
    private static void inicializarAgenda() {
        System.out.println("=== GESTOR DE AGENDA TELEFONICA ===");
        System.out.println("Desea crear una agenda con tamano personalizado? (s/n)");
        System.out.print("Si responde 'n', se usara el tamano por defecto (10 contactos): ");

        String respuesta = scanner.nextLine().trim().toLowerCase();

        if (respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("si")) {
            System.out.print("Ingrese el tamano maximo de la agenda: ");
            try {
                int tamano = Integer.parseInt(scanner.nextLine().trim());
                agenda = new Agenda(tamano);
                System.out.println("Agenda creada con tamano maximo de " + tamano + " contactos.");
            } catch (NumberFormatException e) {
                System.out.println("Numero invalido. Se creara la agenda con tamano por defecto (10 contactos).");
                agenda = new Agenda();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + ". Se creara la agenda con tamano por defecto (10 contactos).");
                agenda = new Agenda();
            }
        } else {
            agenda = new Agenda();
            System.out.println("Agenda creada con tamano por defecto (10 contactos).");
        }

        System.out.println();
    }

    /**
     * Muestra el menú principal y maneja las opciones del usuario
     */
    private static void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Eliminar contacto");
            System.out.println("3. Modificar telefono");
            System.out.println("4. Verificar si la agenda esta llena");
            System.out.println("5. Mostrar espacios libres");
            System.out.println("6. Buscar contacto");
            System.out.println("7. Mostrar todos los contactos");
            System.out.println("8. Mostrar informacion de la agenda");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opcion (1-9): ");

            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());

                switch (opcion) {
                    case 1:
                        agregarContacto();
                        break;
                    case 2:
                        eliminarContacto();
                        break;
                    case 3:
                        modificarTelefono();
                        break;
                    case 4:
                        verificarAgendaLlena();
                        break;
                    case 5:
                        mostrarEspaciosLibres();
                        break;
                    case 6:
                        buscarContacto();
                        break;
                    case 7:
                        agenda.mostrarContactos();
                        break;
                    case 8:
                        agenda.mostrarInformacion();
                        break;
                    case 9:
                        System.out.println("Gracias por usar el Gestor de Agenda Telefonica!");
                        break;
                    default:
                        System.out.println("Opcion invalida. Por favor, seleccione una opcion del 1 al 9.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un numero valido.");
                opcion = 0; // Para continuar el bucle
            }

        } while (opcion != 9);

        scanner.close();
    }

    /**
     * Permite al usuario agregar un nuevo contacto
     */
    private static void agregarContacto() {
        System.out.println("\n=== AGREGAR CONTACTO ===");

        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Ingrese el apellido: ");
        String apellido = scanner.nextLine().trim();

        System.out.print("Ingrese el telefono (10 numeros): ");
        String telefono = scanner.nextLine().trim();

        try {
            Contacto nuevoContacto = new Contacto(nombre, apellido, telefono);
            agenda.agregarContacto(nuevoContacto);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Permite al usuario eliminar un contacto
     */
    private static void eliminarContacto() {
        System.out.println("\n=== ELIMINAR CONTACTO ===");

        if (agenda.getNumeroContactos() == 0) {
            System.out.println("La agenda esta vacia. No hay contactos para eliminar.");
            return;
        }

        System.out.print("Ingrese el nombre del contacto a eliminar: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Ingrese el apellido del contacto a eliminar: ");
        String apellido = scanner.nextLine().trim();

        Contacto contacto = agenda.buscarContacto(nombre, apellido);
        if (contacto != null) {
            agenda.eliminarContacto(contacto);
        } else {
            System.out.println("No se encontro el contacto " + nombre + " " + apellido + " en la agenda.");
        }
    }

    /**
     * Permite al usuario modificar el telefono de un contacto
     */
    private static void modificarTelefono() {
        System.out.println("\n=== MODIFICAR TELEFONO ===");

        if (agenda.getNumeroContactos() == 0) {
            System.out.println("La agenda esta vacia. No hay contactos para modificar.");
            return;
        }

        System.out.print("Ingrese el nombre del contacto: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Ingrese el apellido del contacto: ");
        String apellido = scanner.nextLine().trim();

        System.out.print("Ingrese el nuevo telefono (10 numeros): ");
        String nuevoTelefono = scanner.nextLine().trim();

        agenda.modificarTelefono(nombre, apellido, nuevoTelefono);
    }

    /**
     * Verifica y muestra si la agenda está llena
     */
    private static void verificarAgendaLlena() {
        System.out.println("\n=== VERIFICAR AGENDA LLENA ===");

        if (agenda.agendaLlena()) {
            System.out.println("La agenda esta llena. No hay espacio disponible para nuevos contactos.");
        } else {
            System.out.println("La agenda no esta llena. Aun hay espacio disponible para nuevos contactos.");
        }
    }

    /**
     * Muestra cuantos espacios libres hay en la agenda
     */
    private static void mostrarEspaciosLibres() {
        System.out.println("\n=== ESPACIOS LIBRES ===");

        int espaciosLibres = agenda.espacioLibres();
        System.out.println("Se pueden agregar " + espaciosLibres + " contactos mas a la agenda.");
        System.out.println("Tamano maximo: " + agenda.getTamanoMaximo());
        System.out.println("Contactos actuales: " + agenda.getNumeroContactos());
    }

    /**
     * Permite al usuario buscar un contacto
     */
    private static void buscarContacto() {
        System.out.println("\n=== BUSCAR CONTACTO ===");

        System.out.print("Ingrese el nombre del contacto: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Ingrese el apellido del contacto: ");
        String apellido = scanner.nextLine().trim();

        Contacto contacto = agenda.buscarContacto(nombre, apellido);
        if (contacto != null) {
            System.out.println("Contacto encontrado: " + contacto);
        } else {
            System.out.println("No se encontro el contacto " + nombre + " " + apellido + " en la agenda.");
        }
    }
}
