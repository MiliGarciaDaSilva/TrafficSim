package ucu.edu.aed;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ucu.edu.aed.modelo.Vehiculo;
import ucu.edu.aed.sistema.SistemaTrafico;

public class VehiculoTest {

    @Test
    public void registrarVehiculoDebeAgregarVehiculo() {
        SistemaTrafico sistema = SistemaTrafico.getInstancia();

        sistema.registrarVehiculo("auto", 10, "Toyota");

        Vehiculo v = sistema.obtenerVehiculo(1);

        assertNotNull(v);
        assertEquals(1, v.getIdentificador());
    }

    @Test
    public void retirarVehiculoExistenteDebeDevolverTrue() {
        SistemaTrafico sistema = SistemaTrafico.getInstancia();

        sistema.registrarVehiculo("moto", 5, "Yamaha");

        boolean resultado = sistema.retirarVehiculo(1);

        assertTrue(resultado);
    }

    @Test
    public void retirarVehiculoInexistenteDebeDevolverFalse() {
        SistemaTrafico sistema = SistemaTrafico.getInstancia();

        boolean resultado = sistema.retirarVehiculo(999);

        assertFalse(resultado);
    }
}