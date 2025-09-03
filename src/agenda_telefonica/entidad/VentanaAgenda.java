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
        agenda = new Agenda();
        inicializarComponentes();
        configurarVentana();
        actualizarTabla();
        actualizarInfo();
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

        // Título principal
        labelTitulo = new JLabel("Sistema de Agenda Telefonica", JLabel.CENTER);
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        labelTitulo.setForeground(COLOR_PRINCIPAL);
        panel.add(labelTitulo, BorderLayout.NORTH);

        // Panel de información
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

        // Título de la tabla
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
        JDialog dialogo = new JDialog(this, "Agregar Nuevo Contacto", true);
        dialogo.setSize(450, 300);
        dialogo.setLocationRelativeTo(this);
        dialogo.setLayout(new BorderLayout());
        dialogo.getContentPane().setBackground(COLOR_FONDO);

        // Panel del formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(COLOR_FONDO);
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        // Campos del formulario
        JLabel labelNombre = new JLabel("Nombre:");
        labelNombre.setFont(new Font("Segoe UI", Font.BOLD, 12));
        JTextField campoNombre = new JTextField(20);
        campoNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        campoNombre.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_SECUNDARIO, 1),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));

        JLabel labelApellido = new JLabel("Apellido:");
        labelApellido.setFont(new Font("Segoe UI", Font.BOLD, 12));
        JTextField campoApellido = new JTextField(20);
        campoApellido.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        campoApellido.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_SECUNDARIO, 1),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));

        JLabel labelTelefono = new JLabel("Telefono:");
        labelTelefono.setFont(new Font("Segoe UI", Font.BOLD, 12));
        JTextField campoTelefono = new JTextField(20);
        campoTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        campoTelefono.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_SECUNDARIO, 1),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));

        // Agregar componentes al panel
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        panelFormulario.add(labelNombre, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panelFormulario.add(campoNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        panelFormulario.add(labelApellido, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panelFormulario.add(campoApellido, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        panelFormulario.add(labelTelefono, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panelFormulario.add(campoTelefono, gbc);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setBackground(COLOR_FONDO);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.setBackground(COLOR_EXITO);
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnGuardar.setPreferredSize(new Dimension(100, 35));
        btnGuardar.setFocusPainted(false);

        btnCancelar.setBackground(COLOR_PELIGRO);
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnCancelar.setPreferredSize(new Dimension(100, 35));
        btnCancelar.setFocusPainted(false);

        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        dialogo.add(panelFormulario, BorderLayout.CENTER);
        dialogo.add(panelBotones, BorderLayout.SOUTH);

        // Acciones de los botones
        btnGuardar.addActionListener(e -> {
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
                    dialogo.dispose();
                } else {
                    mostrarMensaje("No se pudo agregar el contacto. Verifique que no exista ya.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IllegalArgumentException ex) {
                mostrarMensaje(ex.getMessage(), "Error de Validacion", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> dialogo.dispose());

        dialogo.setVisible(true);
    }

    private void mostrarDialogoModificar() {
        int filaSeleccionada = tablaContactos.getSelectedRow();
        if (filaSeleccionada == -1) {
            mostrarMensaje("Seleccione un contacto de la tabla para modificar", "Sin Seleccion", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombre = (String) modelo.getValueAt(filaSeleccionada, 0);
        String apellido = (String) modelo.getValueAt(filaSeleccionada, 1);
        String telefonoActual = (String) modelo.getValueAt(filaSeleccionada, 2);

        JDialog dialogo = new JDialog(this, "Modificar Telefono", true);
        dialogo.setSize(400, 200);
        dialogo.setLocationRelativeTo(this);
        dialogo.setLayout(new BorderLayout());
        dialogo.getContentPane().setBackground(COLOR_FONDO);

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(COLOR_FONDO);
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        JLabel labelContacto = new JLabel("Contacto: " + nombre + " " + apellido);
        labelContacto.setFont(new Font("Segoe UI", Font.BOLD, 12));
        labelContacto.setForeground(COLOR_PRINCIPAL);

        JLabel labelTelefono = new JLabel("Nuevo Telefono:");
        labelTelefono.setFont(new Font("Segoe UI", Font.BOLD, 12));
        JTextField campoTelefono = new JTextField(telefonoActual, 20);
        campoTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        campoTelefono.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_SECUNDARIO, 1),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panelFormulario.add(labelContacto, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.EAST;
        panelFormulario.add(labelTelefono, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panelFormulario.add(campoTelefono, gbc);

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setBackground(COLOR_FONDO);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.setBackground(COLOR_INFO);
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnGuardar.setPreferredSize(new Dimension(100, 35));
        btnGuardar.setFocusPainted(false);

        btnCancelar.setBackground(COLOR_PELIGRO);
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnCancelar.setPreferredSize(new Dimension(100, 35));
        btnCancelar.setFocusPainted(false);

        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        dialogo.add(panelFormulario, BorderLayout.CENTER);
        dialogo.add(panelBotones, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> {
            try {
                String nuevoTelefono = campoTelefono.getText().trim();
                if (nuevoTelefono.isEmpty()) {
                    mostrarMensaje("El telefono no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (agenda.modificarTelefono(nombre, apellido, nuevoTelefono)) {
                    mostrarMensaje("Telefono modificado exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    actualizarTabla();
                    dialogo.dispose();
                } else {
                    mostrarMensaje("No se pudo modificar el telefono", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IllegalArgumentException ex) {
                mostrarMensaje(ex.getMessage(), "Error de Validacion", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> dialogo.dispose());

        dialogo.setVisible(true);
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
        JDialog dialogo = new JDialog(this, "Buscar Contacto", true);
        dialogo.setSize(350, 180);
        dialogo.setLocationRelativeTo(this);
        dialogo.setLayout(new BorderLayout());
        dialogo.getContentPane().setBackground(COLOR_FONDO);

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(COLOR_FONDO);
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        JLabel labelNombre = new JLabel("Nombre:");
        labelNombre.setFont(new Font("Segoe UI", Font.BOLD, 12));
        JTextField campoNombre = new JTextField(15);
        campoNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        campoNombre.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_SECUNDARIO, 1),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));

        JLabel labelApellido = new JLabel("Apellido:");
        labelApellido.setFont(new Font("Segoe UI", Font.BOLD, 12));
        JTextField campoApellido = new JTextField(15);
        campoApellido.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        campoApellido.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_SECUNDARIO, 1),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        panelFormulario.add(labelNombre, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panelFormulario.add(campoNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        panelFormulario.add(labelApellido, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panelFormulario.add(campoApellido, gbc);

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setBackground(COLOR_FONDO);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JButton btnBuscar = new JButton("Buscar");
        JButton btnCancelar = new JButton("Cancelar");

        btnBuscar.setBackground(COLOR_ADVERTENCIA);
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnBuscar.setPreferredSize(new Dimension(100, 35));
        btnBuscar.setFocusPainted(false);

        btnCancelar.setBackground(COLOR_PELIGRO);
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnCancelar.setPreferredSize(new Dimension(100, 35));
        btnCancelar.setFocusPainted(false);

        panelBotones.add(btnBuscar);
        panelBotones.add(btnCancelar);

        dialogo.add(panelFormulario, BorderLayout.CENTER);
        dialogo.add(panelBotones, BorderLayout.SOUTH);

        btnBuscar.addActionListener(e -> {
            String nombre = campoNombre.getText().trim();
            String apellido = campoApellido.getText().trim();

            if (nombre.isEmpty() || apellido.isEmpty()) {
                mostrarMensaje("Ingrese nombre y apellido para buscar", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Contacto encontrado = agenda.buscarContacto(nombre, apellido);
            if (encontrado != null) {
                mostrarMensaje("Contacto encontrado:\n" + encontrado.toString(), "Resultado de Busqueda", JOptionPane.INFORMATION_MESSAGE);
            } else {
                mostrarMensaje("Contacto no encontrado", "Resultado de Busqueda", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> dialogo.dispose());

        dialogo.setVisible(true);
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
                agenda.getContactos().size(),
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