package co.edu.uniquindio.poo.SegundoCorte.GestionNomina.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmpleadoVentasTest {
    private EmpleadoVentas empleado;

    @BeforeEach
    void setUp() {
        empleado = new EmpleadoVentas("Carlos", "123", 25, 1000.0f,
                CategoriaEmpleado.JUNIOR, 0, 0,
                2000.0f, 10.0f);
    }

    @Test
    void calcularSalarioBruto() {
        float esperado = 1150.0f; // 1000 base + 50 bono + 100 comision
        assertEquals(esperado, empleado.calcularSalarioBruto(), 0.01f);
    }
}