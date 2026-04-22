package co.edu.uniquindio.poo.SegundoCorte.GestionNomina.model;
/*
 * Clase para probar métodos en EmpleadoVentas
 * @author Juan Carlos Polania
 * @since 2026 - 04
 *
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE)
 */

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;


class EmpleadoVentasTest {
    private EmpleadoVentas empleado;
    private static final Logger LOG = Logger.getLogger(EmpleadoVentasTest.class.getName());

    @BeforeEach
    void setUp() {
        empleado = new EmpleadoVentas("Carlos", "123", 25, 1000.0f,
                CategoriaEmpleado.JUNIOR, 3000, 1f,
                4f, 4f);
    }

    @Test
    void calcularSalarioBruto() {
        LOG.info("Inicio test calcularSalarioBruto");
        float esperado = 1030.0f; // 1000 base + 50 bono + 30 comision
        assertEquals(esperado, empleado.calcularSalarioBruto(), 0.01f);
        LOG.info("Fin test calcularSalarioBruto");
    }
}