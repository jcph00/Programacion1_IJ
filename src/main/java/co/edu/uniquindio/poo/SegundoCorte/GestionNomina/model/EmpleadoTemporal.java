package co.edu.uniquindio.poo.SegundoCorte.GestionNomina.model;

public class EmpleadoTemporal extends Empleado {
    private int diasTrabajados;
    private float valorDia;

    public EmpleadoTemporal(String nombre, String documento, int edad, float salarioBase, CategoriaEmpleado categoria, int diasTrabajados, float valorDia, float descuentoSalud, float descuentoPension) {
        super(nombre, documento, edad, salarioBase, categoria, descuentoSalud, descuentoPension);
        validarDatosTemporales(diasTrabajados, valorDia);
        this.diasTrabajados = diasTrabajados;
        this.valorDia = valorDia;
    }

    public int getDiasTrabajados() {
        return diasTrabajados;
    }

    public void setDiasTrabajados(int diasTrabajados) {
        this.diasTrabajados = diasTrabajados;
    }

    public float getValorDia() {
        return valorDia;
    }

    public void setValorDia(float valorDia) {
        this.valorDia = valorDia;
    }

    private void validarDatosTemporales(int diasTrabajados, float valorDia) {
        if (diasTrabajados < 0) throw new IllegalArgumentException("Los días trabajados no pueden ser negativos.");
        if (valorDia < 0) throw new IllegalArgumentException("El valor del día no puede ser negativo.");
    }

    @Override

    public float calcularSalarioBruto() {
        return salarioBase + diasTrabajados * valorDia;
    }
    public String toString() {
        return super.toString() + String.format(
                "\nDías Lab:    %d\n" +
                        "Valor Día:   $%,.2f",
                diasTrabajados, valorDia
        );
    }

}
