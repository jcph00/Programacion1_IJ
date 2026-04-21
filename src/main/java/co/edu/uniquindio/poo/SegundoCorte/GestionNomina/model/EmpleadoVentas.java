package co.edu.uniquindio.poo.SegundoCorte.GestionNomina.model;

public class EmpleadoVentas extends Empleado {
    private float totalVentas;
    private float porcentajeComision;

    public EmpleadoVentas(String nombre, String documento, int edad, float salarioBase, CategoriaEmpleado categoria, float totalVentas, float porcentajeComision, float descuentoSalud, float descuentoPension) {
        super(nombre, documento, edad, salarioBase, categoria, descuentoSalud, descuentoPension);
        validarVentas(totalVentas, porcentajeComision);
        this.totalVentas = totalVentas;
        this.porcentajeComision = porcentajeComision;
    }

    public float getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(float totalVentas) {
        this.totalVentas = totalVentas;
    }

    public float getPorcentajeComision() {
        return porcentajeComision;
    }

    public void setPorcentajeComision(float porcentajeComision) {
        this.porcentajeComision = porcentajeComision;
    }

    private void validarVentas(float ventas, float comision) {
        if (ventas < 0) throw new IllegalArgumentException("Las ventas no pueden ser negativas.");
        if (comision < 0 || comision > 100) throw new IllegalArgumentException("Comisión inválida.");
    }

    @Override
    public float calcularSalarioBruto() {
        return salarioBase + (totalVentas * (porcentajeComision / 100)); // Salario base más comisión por ventas
    }
    public String toString() {
        return super.toString() + String.format(
                "\nVentas Mes:  $%,.2f\n" +
                        "Comisión:    %.1f%%",
                totalVentas, porcentajeComision
        );
    }

}
