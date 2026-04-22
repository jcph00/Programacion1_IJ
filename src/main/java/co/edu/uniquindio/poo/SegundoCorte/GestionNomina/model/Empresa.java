package co.edu.uniquindio.poo.SegundoCorte.GestionNomina.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Empresa {
    private List<Empleado> empleados;
    private String nombre;
    public Empresa(String nombre) {
        this.empleados = new ArrayList<>();
        this.nombre = nombre;
    }



    public void mostrarEmpleados() {
        for (Empleado empleado : empleados) {
            System.out.println(empleado);
        }
    }

    public String registrarEmpleado(Empleado empleado) {
        if (empleados.stream().anyMatch(e -> e.getDocumento().equals(empleado.getDocumento()))) {
            return "Error: Ya existe un empleado con el mismo documento.";
        }
        empleados.add(empleado);
        return "Empleado registrado exitosamente.";
    }

    public Optional<Empleado> buscarEmpleadoPorDocumento(String documento) {
        return empleados.stream()
                .filter(e -> e.getDocumento().equals(documento))
                .findAny();
    }

    public Optional<Empleado> buscarEmpleadoMayorSalario() {
        return empleados.stream()
                .max((e1, e2) -> Float.compare(e1.calcularSalarioNeto(), e2.calcularSalarioNeto()));
    }

    public List<Empleado> buscarEmpleadoSalarioMayorA(float umbral){
        return empleados.stream()
                .filter(e -> e.calcularSalarioNeto() > umbral)
                .toList();
    }
    public float calcularNominaEmpresa() {
        return (float) empleados.stream()
                .mapToDouble(Empleado::calcularSalarioNeto)
                .sum();
    }

    public void mostrarResumenesDePago() {
        System.out.println("--- RESÚMENES DE PAGO GENERADOS ---");
        for (Empleado emp : empleados) {
            ResumenPago resumen = emp.generarResumenPago();
            System.out.println(resumen);
        }
    }

    public String obtenerListaEmpleadosTexto() {
        if (empleados.isEmpty()) return "No hay empleados registrados.";

        StringBuilder sb = new StringBuilder();
        sb.append("LISTADO GENERAL DE EMPLEADOS\n");
        sb.append("========================================\n");
        for (Empleado e : empleados) {
            sb.append(e.toString()).append("\n");
            sb.append("----------------------------------------\n");
        }
        return sb.toString();
    }

    public String obtenerResumenesTexto() {
        if (empleados.isEmpty()) return "No hay datos para generar resúmenes.";

        StringBuilder sb = new StringBuilder();
        sb.append("REPORTES DE PAGO (RECORDS)\n");
        sb.append("========================================\n");
        for (Empleado e : empleados) {
            // Aquí usamos el Record y su toString automático
            sb.append(e.generarResumenPago().toString()).append("\n");
            sb.append("----------------------------------------\n");
        }
        return sb.toString();
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }


}
