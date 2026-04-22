package co.edu.uniquindio.poo.SegundoCorte.GestionNomina.model;

public abstract class Empleado {
    protected String nombre;
    protected String documento;
    protected int edad;
    protected float salarioBase;
    protected CategoriaEmpleado categoria;
    protected float descuentoSalud;
    protected float descuentoPension;

    public Empleado(String nombre, String documento, int edad, float salarioBase, CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension) {
        validarDatosBase(salarioBase, descuentoSalud, descuentoPension, edad);
        this.nombre = nombre;
        this.documento = documento;
        this.edad = edad;
        this.salarioBase = salarioBase;
        this.categoria = categoria;
        this.descuentoSalud = descuentoSalud;
        this.descuentoPension = descuentoPension;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getDocumento() {
        return documento;
    }


    public void setDocumento(String documento) {
        this.documento = documento;
    }


    public int getEdad() {
        return edad;
    }


    public void setEdad(int edad) {
        this.edad = edad;
    }


    public float getSalarioBase() {
        return salarioBase;
    }


    public void setSalarioBase(float salarioBase) {
        this.salarioBase = salarioBase;
    }


    public CategoriaEmpleado getCategoria() {
        return categoria;
    }


    public void setCategoria(CategoriaEmpleado categoria) {
        this.categoria = categoria;
    }


    public float getDescuentoSalud() {
        return descuentoSalud;
    }


    public void setDescuentoSalud(float descuentoSalud) {
        this.descuentoSalud = descuentoSalud;
    }


    public float getDescuentoPension() {
        return descuentoPension;
    }


    public void setDescuentoPension(float descuentoPension) {
        this.descuentoPension = descuentoPension;
    }

    // --- MÉTODOS DE CÁLCULO ---

    public abstract float calcularSalarioBruto();

    public float calcularBonificacionCategoria() {
        return salarioBase * categoria.getValorAdicional();
    }

    public float calcularDescuentos() {
        return (calcularSalarioBruto() * (this.descuentoSalud / 100)) + (calcularSalarioBruto() * (this.descuentoPension / 100));

    }

    public float calcularSalarioNeto() {
        float salarioNeto = calcularSalarioBruto() - calcularDescuentos() + calcularBonificacionCategoria();
            if (salarioNeto < 0) {
                throw new IllegalArgumentException("Salario negativo");
            } else{
                return salarioNeto;
            }
    }


    public ResumenPago generarResumenPago() {
        return new ResumenPago(
                this.documento,
                this.nombre,
                this.getClass().getSimpleName(), // Esto obtiene el nombre de la clase (Planta, Ventas, etc.)
                this.calcularSalarioBruto(),
                this.calcularDescuentos(),
                this.calcularSalarioNeto()
        );
    }

    public void mostrarInformacion() {
        System.out.println("\n--- DETALLES DEL EMPLEADO ---");
        System.out.printf("Nombre:      %s%n", nombre);
        System.out.printf("Documento:   %s%n", documento);
        System.out.printf("Categoría:   %s%n", categoria);
        System.out.printf("Salario Base: $%,.2f%n", salarioBase);

        // Aquí mostramos los resultados de los cálculos polimórficos
        System.out.printf("Salario Bruto: $%,.2f%n", calcularSalarioBruto());
        System.out.printf("Bonificación:  $%,.2f%n", calcularBonificacionCategoria());
        System.out.printf("Descuentos:    $%,.2f%n", calcularDescuentos());
        System.out.println("-----------------------------");
        System.out.printf("SALARIO NETO:  $%,.2f%n", calcularSalarioNeto());
        System.out.println("-----------------------------\n");
    }

    private void validarDatosBase(float salario, float salud, float pension, int edad) {
        if (salario < 0) {
            throw new IllegalArgumentException("El salario base no puede ser negativo.");
        }
        if (salud < 0 || salud > 100 || pension < 0 || pension > 100) {
            throw new IllegalArgumentException("Los porcentajes de salud/pensión deben estar entre 0 y 100.");
        }
        if (edad < 18) {
            throw new IllegalArgumentException("El empleado debe ser mayor de edad.");
        }
    }



    @Override
    public String toString() {
        return String.format(
                "DETALLES DEL EMPLEADO\n" +
                        "--------------------------\n" +
                        "Nombre:      %s\n" +
                        "Documento:   %s\n" +
                        "Edad:        %d años\n" +
                        "Categoría:   %s\n" +
                        "Salario Base: $%,.2f\n" +
                        "Salario Neto: $%,.2f",
                nombre, documento, edad, categoria, salarioBase, calcularSalarioNeto()
        );
    }
}
