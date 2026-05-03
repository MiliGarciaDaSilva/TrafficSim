package ucu.edu.aed;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ucu.edu.aed.modelo.Vehiculo;
import ucu.edu.aed.sistema.GestionVehiculos;

public class GestionVehiculosTest {

    @Test
    public void registrarVehiculoConTiempoValidoDebeCrearlo() {
        GestionVehiculos gestion = GestionVehiculos.getInstancia();

        gestion.registrarVehiculo("auto", 15, "Toyota");

        Vehiculo v = gestion.obtenerVehiculo(1);

        assertNotNull(v);
        assertEquals(1, v.getIdentificador());
    }

    @Test
    public void obtenerVehiculoInexistenteDebeDevolverNull() {
        GestionVehiculos gestion = GestionVehiculos.getInstancia();

        Vehiculo v = gestion.obtenerVehiculo(999);

        assertNull(v);
    }

    @Test
    public void retirarVehiculoExistenteDebeDevolverTrue() {
        GestionVehiculos gestion = GestionVehiculos.getInstancia();

        gestion.registrarVehiculo("moto", 10, "Yamaha");

        boolean resultado = gestion.retirarVehiculo(1);

        assertTrue(resultado);
    }

    @Test
    public void retirarVehiculoInexistenteDebeDevolverFalse() {
        GestionVehiculos gestion = GestionVehiculos.getInstancia();

        boolean resultado = gestion.retirarVehiculo(999);

        assertFalse(resultado);
    }

    @Test
    public void listarVehiculosConVehiculosDebeDevolverTexto() {
        GestionVehiculos gestion = GestionVehiculos.getInstancia();

        gestion.registrarVehiculo("auto", 10, "Fiat");

        String listado = gestion.listarVehiculos();

        assertNotNull(listado);
        assertTrue(listado.contains("Fiat"));
    }
}