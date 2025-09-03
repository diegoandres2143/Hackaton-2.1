package agenda_telefonica.entidad;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Interfaz gráfica moderna para el sistema de agenda telefónica
 * Desarrollada con Java Swing para proporcionar una experiencia de usuario intuitiva
 */
public class VentanaAgenda extends JFrame {
    private Agenda agenda;
    private JTable tablaContactos;
    private DefaultTableModel modelo;
    private JLabel labelInfo;
    private JLabel labelTitulo;

    // Colores del tema
    private static final Color COLOR_PRINCIPAL = new Color(41, 128, 185);
    private static final Color COLOR_SECUNDARIO = new Color(52, 152, 219);
    private static final Color COLOR_EXITO = new Color(39, 174, 96);
    private static final Color COLOR_PELIGRO = new Color(231, 76, 60);
    private static final Color COLOR_ADVERTENCIA = new Color(241, 196, 15);
    private static final Color COLOR_INFO = new Color(52, 152, 219);
    private static final Color COLOR_FONDO = new Color(236, 240, 241);

    public VentanaAgenda() {
        inicializarAgenda();
        inicializarComponentes();
        configurarVentana();
        actualizarTabla();
        actualizarInfo();
    }

    private void inicializarAgenda() {
        // Diálogo simple para configurar el tamaño de la agenda
        String input = JOptionPane.showInputDialog(
                null,
                "Ingrese el tamano maximo de la agenda (numero de contactos):\n\n" +
                        "Si deja vacio, se usara el tamano por defecto (10 contactos)",
                "Configuracion de Agenda",
                JOptionPane.QUESTION_MESSAGE
        );

        if (input == null) {
            // Usuario cancelo, usar tamano por defecto
            agenda = new Agenda();
            JOptionPane.showMessageDialog(null,
                    "Agenda creada con tamano por defecto (10 contactos).",
                    "Configuracion", JOptionPane.INFORMATION_MESSAGE);
        } else if (input.trim().isEmpty()) {
            // Campo vacio, usar tamano por defecto
            agenda = new Agenda();
            JOptionPane.showMessageDialog(null,
                    "Agenda creada con tamano por defecto (10 contactos).",
                    "Configuracion", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Intentar crear agenda con tamano personalizado
            try {
                int tamano = Integer.parseInt(input.trim());
                if (tamano <= 0) {
                    JOptionPane.showMessageDialog(null,
                            "El tamano debe ser mayor que 0. Se creara con tamano por defecto (10 contactos).",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    agenda = new Agenda();
                } else {
                    agenda = new Agenda(tamano);
                    JOptionPane.showMessageDialog(null,
                            "Agenda creada con tamano maximo de " + tamano + " contactos.",
                            "Configuracion Exitosa", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Numero invalido. Se creara con tamano por defecto (10 contactos).",
                        "Error", JOptionPane.ERROR_MESSAGE);
                agenda = new Agenda();
            }
        }
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        setBackground(COLOR_FONDO);

        // Panel superior con título e información
        JPanel panelSuperior = crearPanelSuperior();
        add(panelSuperior, BorderLayout.NORTH);

        // Panel central con tabla de contactos
        JPanel panelCentral = crearPanelCentral();
        add(panelCentral, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel panelInferior = crearPanelInferior();
        add(panelInferior, BorderLayout.SOUTH);
    }

    private JPanel crearPanelSuperior() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));

        // Titulo principal
        labelTitulo = new JLabel("Sistema de Agenda Telefonica", JLabel.CENTER);
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        labelTitulo.setForeground(COLOR_PRINCIPAL);
        panel.add(labelTitulo, BorderLayout.NORTH);

        // Panel de informacion
        JPanel panelInfo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelInfo.setBackground(COLOR_FONDO);

        labelInfo = new JLabel();
        labelInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        labelInfo.setForeground(Color.DARK_GRAY);
        labelInfo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_SECUNDARIO, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        panelInfo.add(labelInfo);

        panel.add(panelInfo, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelCentral() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // Titulo de la tabla
        JLabel tituloTabla = new JLabel("Lista de Contactos");
        tituloTabla.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tituloTabla.setForeground(COLOR_PRINCIPAL);
        tituloTabla.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panel.add(tituloTabla, BorderLayout.NORTH);

        // Crear tabla
        String[] columnas = {"Nombre", "Apellido", "Telefono"};
        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla de solo lectura
            }
        };

        tablaContactos = new JTable(modelo);
        tablaContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaContactos.setRowHeight(30);
        tablaContactos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tablaContactos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        tablaContactos.setGridColor(COLOR_SECUNDARIO);
        tablaContactos.setSelectionBackground(COLOR_SECUNDARIO);
        tablaContactos.setSelectionForeground(Color.WHITE);

        // Personalizar encabezados
        tablaContactos.getTableHeader().setBackground(COLOR_PRINCIPAL);
        tablaContactos.getTableHeader().setForeground(Color.WHITE);
        tablaContactos.getTableHeader().setPreferredSize(new Dimension(0, 35));

        JScrollPane scrollPane = new JScrollPane(tablaContactos);
        scrollPane.setPreferredSize(new Dimension(600, 250));
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_SECUNDARIO, 1));
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelInferior() {
        JPanel panel = new JPanel(new GridLayout(2, 3, 10, 10));
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));

        // Crear botones con estilos modernos
        JButton btnAgregar = crearBoton("Agregar", COLOR_EXITO, "agregar");
        JButton btnModificar = crearBoton("Modificar", COLOR_INFO, "modificar");
        JButton btnEliminar = crearBoton("Eliminar", COLOR_PELIGRO, "eliminar");
        JButton btnBuscar = crearBoton("Buscar", COLOR_ADVERTENCIA, "buscar");
        JButton btnLimpiar = crearBoton("Limpiar", new Color(155, 89, 182), "limpiar");
        JButton btnSalir = crearBoton("Salir", new Color(149, 165, 166), "salir");

        panel.add(btnAgregar);
        panel.add(btnModificar);
        panel.add(btnEliminar);
        panel.add(btnBuscar);
        panel.add(btnLimpiar);
        panel.add(btnSalir);

        return panel;
    }

    private JButton crearBoton(String texto, Color color, String accion) {
        JButton boton = new JButton(texto);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 11));
        boton.setPreferredSize(new Dimension(120, 35));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createRaisedBevelBorder());
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(color.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(color);
            }
        });

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarAccion(accion);
            }
        });

        return boton;
    }

    private void ejecutarAccion(String accion) {
        switch (accion) {
            case "agregar":
                mostrarDialogoAgregar();
                break;
            case "modificar":
                mostrarDialogoModificar();
                break;
            case "eliminar":
                eliminarContactoSeleccionado();
                break;
            case "buscar":
                mostrarDialogoBuscar();
                break;
            case "limpiar":
                limpiarAgenda();
                break;
            case "salir":
                salir();
                break;
        }
    }

    private void mostrarDialogoAgregar() {
        // Crear campos de texto simples
        JTextField campoNombre = new JTextField(20);
        JTextField campoApellido = new JTextField(20);
        JTextField campoTelefono = new JTextField(20);

        // Configurar los campos
        campoNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        campoApellido.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        campoTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        // Crear el panel con los campos
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Nombre:"));
        panel.add(campoNombre);
        panel.add(new JLabel("Apellido:"));
        panel.add(campoApellido);
        panel.add(new JLabel("Telefono (10 numeros):"));
        panel.add(campoTelefono);

        // Mostrar el diálogo
        int resultado = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Agregar Nuevo Contacto",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        // Procesar el resultado
        if (resultado == JOptionPane.OK_OPTION) {
            try {
                String nombre = campoNombre.getText().trim();
                String apellido = campoApellido.getText().trim();
                String telefono = campoTelefono.getText().trim();

                if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty()) {
                    mostrarMensaje("Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Contacto nuevoContacto = new Contacto(nombre, apellido, telefono);
                if (agenda.agregarContacto(nuevoContacto)) {
                    mostrarMensaje("Contacto agregado exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    actualizarTabla();
                    actualizarInfo();
                }
            } catch (IllegalArgumentException ex) {
                mostrarMensaje(ex.getMessage(), "Error de Validacion", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void mostrarDialogoModificar() {
        int filaSeleccionada = tablaContactos.getSelectedRow();
        if (filaSeleccionada == -1) {
            mostrarMensaje("Seleccione un contacto de la tabla para modificar", "Sin Seleccion", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombreActual = (String) modelo.getValueAt(filaSeleccionada, 0);
        String apellidoActual = (String) modelo.getValueAt(filaSeleccionada, 1);
        String telefonoActual = (String) modelo.getValueAt(filaSeleccionada, 2);

        // Crear campos de texto
        JTextField campoNombre = new JTextField(nombreActual, 20);
        JTextField campoApellido = new JTextField(apellidoActual, 20);
        JTextField campoTelefono = new JTextField(telefonoActual, 20);

        // Configurar los campos
        campoNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        campoApellido.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        campoTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        // Crear el panel
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Nombre:"));
        panel.add(campoNombre);
        panel.add(new JLabel("Apellido:"));
        panel.add(campoApellido);
        panel.add(new JLabel("Telefono (10 numeros):"));
        panel.add(campoTelefono);

        // Mostrar el diálogo
        int resultado = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Modificar Contacto",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        // Procesar el resultado
        if (resultado == JOptionPane.OK_OPTION) {
            try {
                String nuevoNombre = campoNombre.getText().trim();
                String nuevoApellido = campoApellido.getText().trim();
                String nuevoTelefono = campoTelefono.getText().trim();

                if (nuevoNombre.isEmpty() || nuevoApellido.isEmpty() || nuevoTelefono.isEmpty()) {
                    mostrarMensaje("Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verificar si el nombre o apellido cambiaron
                boolean nombreCambio = !nuevoNombre.equals(nombreActual);
                boolean apellidoCambio = !nuevoApellido.equals(apellidoActual);
                boolean telefonoCambio = !nuevoTelefono.equals(telefonoActual);

                if (nombreCambio || apellidoCambio) {
                    // Si cambió el nombre o apellido, verificar que no exista otro contacto con esos datos
                    Contacto contactoExistente = agenda.buscarContacto(nuevoNombre, nuevoApellido);
                    if (contactoExistente != null &&
                            (!nuevoNombre.equals(nombreActual) || !nuevoApellido.equals(apellidoActual))) {
                        mostrarMensaje("Ya existe un contacto con ese nombre y apellido", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Modificar el teléfono si cambió
                if (telefonoCambio) {
                    if (agenda.modificarTelefono(nombreActual, apellidoActual, nuevoTelefono)) {
                        mostrarMensaje("Telefono modificado exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

                // Si cambió el nombre o apellido, eliminar el contacto anterior y agregar el nuevo
                if (nombreCambio || apellidoCambio) {
                    Contacto contactoAnterior = agenda.buscarContacto(nombreActual, apellidoActual);
                    if (contactoAnterior != null) {
                        agenda.eliminarContacto(contactoAnterior);
                        Contacto nuevoContacto = new Contacto(nuevoNombre, nuevoApellido, nuevoTelefono);
                        if (agenda.agregarContacto(nuevoContacto)) {
                            mostrarMensaje("Contacto modificado exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }

                actualizarTabla();

            } catch (IllegalArgumentException ex) {
                mostrarMensaje(ex.getMessage(), "Error de Validacion", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarContactoSeleccionado() {
        int filaSeleccionada = tablaContactos.getSelectedRow();
        if (filaSeleccionada == -1) {
            mostrarMensaje("Seleccione un contacto de la tabla para eliminar", "Sin Seleccion", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombre = (String) modelo.getValueAt(filaSeleccionada, 0);
        String apellido = (String) modelo.getValueAt(filaSeleccionada, 1);

        int confirmacion = JOptionPane.showConfirmDialog(this,
                "Esta seguro de que desea eliminar el contacto " + nombre + " " + apellido + "?",
                "Confirmar Eliminacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            Contacto contacto = agenda.buscarContacto(nombre, apellido);
            if (contacto != null && agenda.eliminarContacto(contacto)) {
                mostrarMensaje("Contacto eliminado exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                actualizarTabla();
                actualizarInfo();
            } else {
                mostrarMensaje("No se pudo eliminar el contacto", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void mostrarDialogoBuscar() {
        // Crear campos de texto simples
        JTextField campoNombre = new JTextField(15);
        JTextField campoApellido = new JTextField(15);

        // Configurar los campos
        campoNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        campoApellido.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        // Crear el panel con los campos
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Nombre:"));
        panel.add(campoNombre);
        panel.add(new JLabel("Apellido:"));
        panel.add(campoApellido);

        // Mostrar el diálogo
        int resultado = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Buscar Contacto",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        // Procesar el resultado
        if (resultado == JOptionPane.OK_OPTION) {
            String nombre = campoNombre.getText().trim();
            String apellido = campoApellido.getText().trim();

            if (nombre.isEmpty() || apellido.isEmpty()) {
                mostrarMensaje("Ingrese nombre y apellido para buscar", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Contacto encontrado = agenda.buscarContacto(nombre, apellido);
            if (encontrado != null) {
                mostrarMensaje("Contacto encontrado:\n" +
                                "Nombre: " + encontrado.getNombre() + "\n" +
                                "Apellido: " + encontrado.getApellido() + "\n" +
                                "Telefono: " + encontrado.getTelefono(),
                        "Resultado de Busqueda", JOptionPane.INFORMATION_MESSAGE);
            } else {
                mostrarMensaje("Contacto no encontrado", "Resultado de Busqueda", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void limpiarAgenda() {
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "Esta seguro de que desea eliminar TODOS los contactos de la agenda?",
                "Confirmar Limpieza", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            agenda = new Agenda();
            actualizarTabla();
            actualizarInfo();
            mostrarMensaje("Agenda limpiada exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void salir() {
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "Esta seguro de que desea salir de la aplicacion?",
                "Confirmar Salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void actualizarTabla() {
        modelo.setRowCount(0); // Limpiar tabla

        List<Contacto> contactos = agenda.getContactos();
        for (Contacto contacto : contactos) {
            Object[] fila = {
                    contacto.getNombre(),
                    contacto.getApellido(),
                    contacto.getTelefono()
            };
            modelo.addRow(fila);
        }
    }

    private void actualizarInfo() {
        String info = String.format("Tamano maximo: %d | Contactos: %d | Espacios libres: %d | %s",
                agenda.getTamanoMaximo(),
                agenda.getNumeroContactos(),
                agenda.espacioLibres(),
                agenda.agendaLlena() ? "Llena" : "Disponible"
        );
        labelInfo.setText(info);
    }

    private void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }

    private void configurarVentana() {
        setTitle("Sistema de Agenda Telefonica - Java Swing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 750);
        setLocationRelativeTo(null);
        setResizable(true);
        setMinimumSize(new Dimension(750, 700));

        // Configurar icono de la ventana
        try {
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));
        } catch (Exception e) {
            // Si no hay icono, continuar sin él
        }
    }

    public static void main(String[] args) {
        // Ejecutar en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            new VentanaAgenda().setVisible(true);
        });
    }
}