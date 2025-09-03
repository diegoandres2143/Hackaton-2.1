package agenda_telefonica.entidad;

import java.util.Scanner;
import java.util.List;

public class Main {
    private static Agenda agenda;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        inicializarAgenda();
        mostrarMenu();
    }

    //Inicializa la agenda y consulta el tamaño

    private static void inicializarAgenda() {
        System.out.println("");
        System.out.println("============= BIENVENIDO A TU AGENDA TELEFÓNICA ================\n");
        while (true) {
            System.out.print("""
            ¿Desea crear una agenda con tamaño personalizado?

            1. Sí
            2. No (Usará el tamaño por defecto: 10 contactos)
            """);
            System.out.print("-> ");

            int opcion;

            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("----------------------------------------------------");
                System.out.println("Opción inválida. Debe selecionar la opción 1 ó 2.");
                System.out.println("");
                continue;
            }

            if (opcion == 1) {
                System.out.println("");
                System.out.print("Ingrese el tamaño máximo de la agenda: ");
                try {
                    int tamano = Integer.parseInt(scanner.nextLine().trim());

                    agenda = new Agenda(tamano);
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Agenda creada con tamaño máximo de " + tamano + " contactos.");
                    System.out.println("");
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("-------------------------------------------------");
                    System.out.println("Número inválido. Debe ingresar un número entero.");
                    System.out.println("");
                    continue;
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                    continue;
                }
            } else if (opcion == 2) {
                agenda = new Agenda();
                System.out.println("-----------------------------------------------------");
                System.out.println("Agenda creada con tamaño por defecto (10 contactos).");
                break;
            } else {
                System.out.println("Opción inválida. Debe ingresar 1 ó 2.");
            }
        }
    }

    private static void mostrarMenu() {
        int opcion;

        do {
            System.out.println("");
            System.out.print("""
            ============= MENU PRINCIPAL ============
            
            1. Agregar contacto
            2. Eliminar contacto
            3. Modificar teléfono
            4. Verificar si la agenda esta llena
            5. Mostrar espacio disponible en la agenda
            6. Buscar contacto
            7. Mostrar todos los contactos
            8. Mostrar información de la agenda
            9. Salir
            
            Seleccione una opción:
            """);
            System.out.print("-> ");

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
                        System.out.println("------------------------------------------------");
                        System.out.println("¡Gracias por usar el Gestor de Agenda Telefónica!");
                        break;
                    default:
                        System.out.println("-------------------------------------------------------------");
                        System.out.println("Opción inválida. Por favor, seleccione una opcion del 1 al 9.");
                        System.out.println("");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                opcion = 0; // Para continuar el bucle
            }

        } while (opcion != 9);

        scanner.close();
    }

    //Agregar un nuevo contacto

    private static void agregarContacto() {
        System.out.println("\n=========== AGREGAR CONTACTO ===========\n");

        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Ingrese el apellido: ");
        String apellido = scanner.nextLine().trim();

        System.out.print("Ingrese el teléfono (10 numeros): ");
        String telefono = scanner.nextLine().trim();

        try {
            Contacto nuevoContacto = new Contacto(nombre, apellido, telefono);
            agenda.agregarContacto(nuevoContacto);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Permite al usuario eliminar un contacto

    private static void eliminarContacto() {
        System.out.println("\n========== ELIMINAR CONTACTO ===========");

        if (agenda.getNumeroContactos() == 0) {
            System.out.println("La agenda esta vacía. No hay contactos para eliminar.");
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
            System.out.println("No se encontró el contacto " + nombre + " " + apellido + " en la agenda.");
        }
    }

    //Permite al usuario modificar el telefono de un contacto

    private static void modificarTelefono() {
        System.out.println("\n========== MODIFICAR TELÉFONO ==========");

        if (agenda.getNumeroContactos() == 0) {
            System.out.println("La agenda esta vacía. No hay contactos para modificar.");
            return;
        }

        System.out.print("Ingrese el nombre del contacto: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Ingrese el apellido del contacto: ");
        String apellido = scanner.nextLine().trim();

        System.out.print("Ingrese el nuevo teléfono (10 números): ");
        String nuevoTelefono = scanner.nextLine().trim();

        agenda.modificarTelefono(nombre, apellido, nuevoTelefono);
    }

    //Verifica y muestra si la agenda está llena o no

    private static void verificarAgendaLlena() {
        System.out.println("\n=========== VERIFICAR AGENDA LLENA ===========");

        if (agenda.agendaLlena()) {
            System.out.println("---------------------------------------------------------------------");
            System.out.println("La agenda esta llena. No hay espacio disponible para nuevos contactos.");
        } else {
            System.out.println("---------------------------------------------------------------------");
            System.out.println("La agenda no esta llena. Aún hay espacio disponible para nuevos contactos.");
        }
    }

    // Muestra cuantos espacios libres hay en la agenda

    private static void mostrarEspaciosLibres() {
        System.out.println("\n============= ESPACIOS LIBRES ============");

        int espaciosLibres = agenda.espacioLibres();
        System.out.println("Se pueden agregar " + espaciosLibres + " contactos más a la agenda.");
        System.out.println("Tamaño máximo: " + agenda.getTamanoMaximo());
        System.out.println("Contactos actuales: " + agenda.getNumeroContactos());
    }

    // Permite al usuario buscar un contacto

    private static void buscarContacto() {
        System.out.println("\n============= BUSCAR CONTACTO ============");

        System.out.print("Ingrese el nombre del contacto: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Ingrese el apellido del contacto: ");
        String apellido = scanner.nextLine().trim();

        Contacto contacto = agenda.buscarContacto(nombre, apellido);
        if (contacto != null) {
            System.out.println("Contacto encontrado: " + contacto);
        } else {
            System.out.println("No se encontró el contacto " + nombre + " " + apellido + " en la agenda.");
        }
    }
}
