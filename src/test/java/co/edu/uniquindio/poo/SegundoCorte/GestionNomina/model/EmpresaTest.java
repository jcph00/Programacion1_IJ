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
import java.util.logging.Logger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class EmpresaTest {
    private Empresa empresa;
    private Empleado planta;
    private Empleado ventas;
    private Empleado temporal;
    private Logger LOG = Logger.getLogger(EmpresaTest.class.getName());


    @BeforeEach
    void setUp() {
        empresa = new Empresa("Test Empresa");

        planta = new EmpleadoPlanta("Ana", "111", 30, 1500f, "Gerente", CategoriaEmpleado.SEMISENIOR, 12, 200f, 200f, 4f, 4f);
        ventas = new EmpleadoVentas("Luis", "222", 25, 1200f, CategoriaEmpleado.JUNIOR, 5000, 0.1f, 4f, 4f);;
        temporal = new EmpleadoTemporal("Maria", "333", 28, 1000f, CategoriaEmpleado.SENIOR, 15, 150f, 4f, 4f);;;
    }

    @Test
    void testRegistrarEmpleado() {
        LOG.info("Inicio test testRegistrarEmpleado");
        empresa.registrarEmpleado(planta);
        empresa.registrarEmpleado(ventas);
        empresa.registrarEmpleado(temporal);
        empresa.registrarEmpleado(planta); // Intento registrar el mismo empleado
        assertEquals(3, empresa.getEmpleados().size(), "La cantidad de empleados no coincide");
        assertTrue(empresa.getEmpleados().contains(planta), "El empleado de planta debería estar en la lista");
        assertTrue(empresa.getEmpleados().contains(ventas), "El empleado de ventas debería estar en la lista");
        assertTrue(empresa.getEmpleados().contains(temporal), "El empleado temporal debería estar en la lista");
        LOG.info("Fin test testRegistrarEmpleado");
    }
    @Test
    void testSalarioNetoNuncaEsNegativo() {
        LOG.info("Inicio test testSalarioNetoNuncaEsNegativo");
        assertThrows(IllegalArgumentException.class, () -> {EmpleadoVentas ev = new EmpleadoVentas("Juan", "444", 30, 1000f, CategoriaEmpleado.JUNIOR, 30f, 0.1f, 60f, 60f);ev.calcularSalarioNeto();}, "El salario base no puede ser negativo");
        LOG.info("Fin test testSalarioNetoNuncaEsNegativo");
    }
    @Test
    void testBusquedaEmpleadoInexistente() {
        LOG.info("Inicio test testBusquedaEmpleadoInexistente");

        // Act: Buscamos un documento que sabemos que NO hemos registrado
        Optional<Empleado> resultado = empresa.buscarEmpleadoPorDocumento("999999");

        // Assert: Verificamos que el Optional esté vacío
        // assertFalse(resultado.isPresent()) es lo mismo que decirle "no lo encuentres"
        assertFalse(resultado.isPresent(), "El resultado debería estar vacío para un documento inexistente");

        LOG.info("Fin test testBusquedaEmpleadoInexistente");
    }

    @Test
    void testSalarioBaseNegativoLanzaExcepcion() {
        LOG.info("Inicio test testSalarioBaseNegativoLanzaExcepcion");

        // Assert & Act: Intentamos crear el objeto con salario -1000
        assertThrows(IllegalArgumentException.class, () -> {
            new EmpleadoVentas("Carlos", "123", 25, -1000.0f,
                    CategoriaEmpleado.JUNIOR, 2000.0f, 10.0f, 4.0f, 4.0f);
        }, "Debería lanzar IllegalArgumentException cuando el salario base es negativo");

        LOG.info("Fin test testSalarioBaseNegativoLanzaExcepcion");
    }

}
