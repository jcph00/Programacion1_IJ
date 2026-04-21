package co.edu.uniquindio.poo.SegundoCorte.GestionNomina.model;

public record ResumenPago(
        String documento, String nombre, String tipoEmpleado, float salarioBruto, float descuentos, float salarioNeto
) {
    @Override
    public String toString() {
        return String.format(
                "==========================================\n" +
                        "         RESUMEN DE PAGO (RECORD)         \n" +
                        "==========================================\n" +
                        "Documento:      %s\n" +
                        "Nombre:         %s\n" +
                        "Tipo Empleado:  %s\n" +
                        "------------------------------------------\n" +
                        "Salario Bruto:  $%12.2f\n" +
                        "Descuentos:     $%12.2f\n" +
                        "------------------------------------------\n" +
                        "SALARIO NETO:   $%12.2f\n" +
                        "==========================================\n",
                documento, nombre, tipoEmpleado, salarioBruto, descuentos, salarioNeto
        );
    }
}
