package co.edu.uniquindio.poo.SegundoCorte.GestionNomina.app;

import java.util.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;
import co.edu.uniquindio.poo.SegundoCorte.GestionNomina.model.*;

public class Main {
    private static Empresa empresa = new Empresa();
    private static Map<Integer, MenuAccion> opciones = new HashMap<>();

    public static void main(String[] args) {
        configurarMenu();
        String[] opcionesMenu = {
                "1. Registrar Planta", "2. Registrar Ventas", "3. Registrar Temporal",
                "4. Mostrar Empleados", "5. Buscar por Documento", "6. Empleado Mayor Salario",
                "7. Calcular Nómina Total", "8. Mostrar Resúmenes (Records)", "9. Salir"
        };

        while (true) {
            try {
                String seleccion = (String) JOptionPane.showInputDialog(
                        null, "Seleccione una acción:", "Sistema de Gestión de Nómina",
                        JOptionPane.PLAIN_MESSAGE, null, opcionesMenu, opcionesMenu[0]
                );

                if (seleccion == null || seleccion.contains("9. Salir")) break;

                int op = Character.getNumericValue(seleccion.charAt(0));

                if (opciones.containsKey(op)) {
                    opciones.get(op).ejecutar();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error de entrada", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void configurarMenu() {
        opciones.put(1, () -> registrarPlanta());
        opciones.put(2, () -> registrarVentas());
        opciones.put(3, () -> registrarTemporal());
        opciones.put(4, () -> mostrarMensajeLargo("Listado de Empleados", empresa.obtenerListaEmpleadosTexto()));
        opciones.put(5, () -> buscarPorDoc());
        opciones.put(6, () -> mostrarMayor());
        opciones.put(7, () -> JOptionPane.showMessageDialog(null, "La Nómina Total es: $" + empresa.calcularNominaEmpresa()));
        opciones.put(8, () -> mostrarMensajeLargo("Resúmenes de Pago (Records)", empresa.obtenerResumenesTexto()));
    }

    // --- MÉTODOS DE REGISTRO VISUAL ---

    private static void registrarPlanta() {
        String nom = JOptionPane.showInputDialog("Nombre del empleado:");
        String doc = JOptionPane.showInputDialog("Documento:");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));
        float base = Float.parseFloat(JOptionPane.showInputDialog("Salario Base:"));

        CategoriaEmpleado cat = (CategoriaEmpleado) JOptionPane.showInputDialog(null, "Categoría:", "Selección",
                JOptionPane.QUESTION_MESSAGE, null, CategoriaEmpleado.values(), CategoriaEmpleado.values()[0]);

        String cargo = JOptionPane.showInputDialog("Cargo:");
        int horas = Integer.parseInt(JOptionPane.showInputDialog("Horas Extra:"));
        float valorH = Float.parseFloat(JOptionPane.showInputDialog("Valor Hora Extra:"));
        float aux = Float.parseFloat(JOptionPane.showInputDialog("Auxilio Transporte:"));
        float desSalud = Float.parseFloat(JOptionPane.showInputDialog("Descuento Salud (%):"));
        float desPension = Float.parseFloat(JOptionPane.showInputDialog("Descuento Pensión (%):"));

        empresa.registrarEmpleado(new EmpleadoPlanta(nom, doc, edad, base, cargo, cat, horas, valorH, aux, desSalud, desPension));
        JOptionPane.showMessageDialog(null, "Empleado de Planta registrado exitosamente.");
    }

    private static void registrarVentas() {
        String nom = JOptionPane.showInputDialog("Nombre:");
        String doc = JOptionPane.showInputDialog("Documento:");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));
        float base = Float.parseFloat(JOptionPane.showInputDialog("Salario Base:"));

        CategoriaEmpleado cat = (CategoriaEmpleado) JOptionPane.showInputDialog(null, "Categoría:", "Selección",
                JOptionPane.QUESTION_MESSAGE, null, CategoriaEmpleado.values(), CategoriaEmpleado.values()[0]);

        float ventas = Float.parseFloat(JOptionPane.showInputDialog("Total Ventas del Mes:"));
        float comision = Float.parseFloat(JOptionPane.showInputDialog("Porcentaje Comisión (0-100):"));
        float desSalud = Float.parseFloat(JOptionPane.showInputDialog("Descuento Salud (%):"));
        float desPension = Float.parseFloat(JOptionPane.showInputDialog("Descuento Pensión (%):"));

        empresa.registrarEmpleado(new EmpleadoVentas(nom, doc, edad, base, cat, ventas, comision, desSalud, desPension));
        JOptionPane.showMessageDialog(null, "Empleado de Ventas registrado exitosamente.");
    }

    private static void registrarTemporal() {
        String nom = JOptionPane.showInputDialog("Nombre:");
        String doc = JOptionPane.showInputDialog("Documento:");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));
        float base = Float.parseFloat(JOptionPane.showInputDialog("Salario Base (para bonificación):"));

        CategoriaEmpleado cat = (CategoriaEmpleado) JOptionPane.showInputDialog(null, "Categoría:", "Selección",
                JOptionPane.QUESTION_MESSAGE, null, CategoriaEmpleado.values(), CategoriaEmpleado.values()[0]);

        int dias = Integer.parseInt(JOptionPane.showInputDialog("Días Trabajados:"));
        float valorD = Float.parseFloat(JOptionPane.showInputDialog("Valor del Día:"));
        float desSalud = Float.parseFloat(JOptionPane.showInputDialog("Descuento Salud (%):"));
        float desPension = Float.parseFloat(JOptionPane.showInputDialog("Descuento Pensión (%):"));

        empresa.registrarEmpleado(new EmpleadoTemporal(nom, doc, edad, base, cat, dias, valorD, desSalud, desPension));
        JOptionPane.showMessageDialog(null, "Empleado Temporal registrado exitosamente.");
    }

    // --- MÉTODOS DE CONSULTA ---

    private static void buscarPorDoc() {
        String id = JOptionPane.showInputDialog("Ingrese el documento a buscar:");
        empresa.buscarEmpleadoPorDocumento(id).ifPresentOrElse(
                emp -> mostrarMensajeLargo("Información del Empleado", emp.toString()), // O usar un método que formatee el texto
                () -> JOptionPane.showMessageDialog(null, "No se encontró el empleado.")
        );
    }

    private static void mostrarMayor() {
        empresa.buscarEmpleadoMayorSalario().ifPresentOrElse(
                emp -> mostrarMensajeLargo("Mayor Salario Neto", emp.toString()),
                () -> JOptionPane.showMessageDialog(null, "No hay empleados registrados.")
        );
    }

    // Método de apoyo para mostrar textos largos con scroll (como los reportes)
    private static void mostrarMensajeLargo(String titulo, String contenido) {
        JTextArea textArea = new JTextArea(15, 50);
        textArea.setText(contenido);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(null, scroll, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}
