package co.edu.uniquindio.poo.SegundoCorte.GestionNomina.model;
/*
 * Clase para probar métodos en EmpleadoVentas
 * @author Juan Carlos Polania
 * @since 2026 - 04
 *
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE)
 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.logging.Logger;

class EmpleadoPlantaTest {
    private EmpleadoPlanta empleado;
    private static final Logger LOG = Logger.getLogger(EmpleadoPlantaTest.class.getName());

    @BeforeEach
    void setUp() {
        empleado = new EmpleadoPlanta("Ana", "111", 30, 1500f, "Gerente", CategoriaEmpleado.SEMISENIOR, 12, 200f, 200f, 4f, 4f);
    }

    @Test
    void testBonificacionJuniorMayorCero() {
        LOG.info("Inicio test BonificacionJuniorMayorCero");
        LocalDate fecha = LocalDate.now();
        float bonificacion = empleado.calcularBonificacionCategoria();
        assertTrue(bonificacion > 0 , "El empleado recibe la bonificación");
        LOG.info("Fin test testBonificacionJuniorMayorCero");
    }
}