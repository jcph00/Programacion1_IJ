package co.edu.uniquindio.poo.SegundoCorte.GestionNomina.model;

public enum CategoriaEmpleado {
    JUNIOR(.05f), SEMISENIOR(.1f), SENIOR(.15f);

    private float valorAdicional;

    CategoriaEmpleado(float valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    public float getValorAdicional() {
        return valorAdicional;
    }
}
