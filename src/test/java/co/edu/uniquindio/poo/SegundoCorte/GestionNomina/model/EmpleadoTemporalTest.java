package co.edu.uniquindio.poo.SegundoCorte.GestionNomina.model;
/*
 * Clase para probar métodos en EmpleadoVentas
 * @author Juan Carlos Polania
 * @since 2026 - 04
 *
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE)
 */

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import  org.junit.jupiter.api.BeforeEach;

class EmpleadoTemporalTest {
    private Logger LOG = Logger.getLogger(EmpleadoTemporalTest.class.getName());
    private Empleado empleado;
    @BeforeEach
    void setUp() {
        empleado = new EmpleadoTemporal("Juan", "12345", 25, 2000f, CategoriaEmpleado.SEMISENIOR, 5, 100f, 4f, 4f);
    }
    @Test

    void testSalarioNetoNoEsCero() {
        LOG.info("Inicio test testSalarioNetoNoEsCero");
        float esperado = 0f; // El salario neto no debería ser cero
        assertNotEquals(esperado, empleado.calcularSalarioNeto(), 0.01f);
        LOG.info("Fin test testSalarioNetoNoEsCero");
    }

}
