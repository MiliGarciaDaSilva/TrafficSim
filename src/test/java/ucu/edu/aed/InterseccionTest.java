package ucu.edu.aed;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ucu.edu.aed.modelo.Calle;
import ucu.edu.aed.modelo.Interseccion;
import ucu.edu.aed.sistema.SistemaTrafico;

public class InterseccionTest {

    @Test
    public void incorporarCalleDebePermitirObtenerla() {
        Interseccion interseccion = new Interseccion("Cruce 1");

        interseccion.incorporarCalle("18 de Julio",10);

        Calle calle = interseccion.obtenerCalle("18 de Julio");

        assertNotNull(calle);
        assertEquals("18 de Julio", calle.getNombre());
    }

    @Test
    public void obtenerCalleInexistenteDebeDevolverNull() {
        Interseccion interseccion = new Interseccion("Cruce 1");

        Calle calle = interseccion.obtenerCalle("Rivera");

        assertNull(calle);
    }

    @Test
    public void incorporarVehiculoACalleDebeAgregarALaCola() {
        SistemaTrafico sistema = SistemaTrafico.getInstancia();
        sistema.registrarVehiculo("auto", 10, "Toyota");

        Interseccion interseccion = new Interseccion("Cruce 1");
        interseccion.incorporarCalle("18 de Julio", 10);

        boolean resultado = interseccion.incorporarVehiculo(1, "18 de Julio");

        assertTrue(resultado);
        assertEquals(1, interseccion.obtenerCalle("18 de Julio").tamaño());
    }
}
