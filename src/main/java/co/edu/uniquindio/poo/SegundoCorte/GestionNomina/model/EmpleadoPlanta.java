package co.edu.uniquindio.poo.SegundoCorte.GestionNomina.model;

public class EmpleadoPlanta extends Empleado {
    private String cargo;
    private int horasExtras;
    private float valorHoraExtra;
    private float auxilioTransporte;

    public EmpleadoPlanta(String nombre, String documento, int edad, float salarioBase, String cargo, CategoriaEmpleado categoria, int horasExtras, float valorHoraExtra, float auxilioTransporte, float descuentoSalud, float descuentoPension) {
        super(nombre, documento, edad, salarioBase, categoria, descuentoSalud, descuentoPension);
        validarDatosPlanta(horasExtras, valorHoraExtra, auxilioTransporte);
        this.cargo = cargo;
        this.horasExtras = horasExtras;
        this.valorHoraExtra = valorHoraExtra;
        this.auxilioTransporte = auxilioTransporte;
    }

    public String getCargo() {
        return cargo;
    }

    public int getHorasExtras() {
        return horasExtras;
    }

    public float getValorHoraExtra() {
        return valorHoraExtra;
    }

    public float getAuxilioTransporte() {
        return auxilioTransporte;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setHorasExtras(int horasExtras) {
        this.horasExtras = horasExtras;
    }

    public void setValorHoraExtra(float valorHoraExtra) {
        this.valorHoraExtra = valorHoraExtra;
    }

    public void setAuxilioTransporte(float auxilioTransporte) {
        this.auxilioTransporte = auxilioTransporte;
    }

    private void validarDatosPlanta(int horasExtras, float valorHoraExtra, float auxilioTransporte) {
        if (horasExtras < 0) throw new IllegalArgumentException("Las horas extras no pueden ser negativas.");
        if (valorHoraExtra < 0) throw new IllegalArgumentException("El valor de la hora extra no puede ser negativo.");
        if (auxilioTransporte < 0) throw new IllegalArgumentException("El auxilio de transporte no puede ser negativo.");
    }

    @Override
    public float calcularSalarioBruto() {
        return salarioBase + (horasExtras * valorHoraExtra) + auxilioTransporte;
    }

    public String toString() {
        return super.toString() + String.format(
                "\nCargo:       %s\n" +
                        "Horas Extra: %d\n" +
                        "Auxilio Trp: $%,.2f",
                cargo, horasExtras, auxilioTransporte
        );
    }

}
