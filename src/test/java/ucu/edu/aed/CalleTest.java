package ucu.edu.aed;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ucu.edu.aed.modelo.Calle;
import ucu.edu.aed.modelo.Vehiculo;

public class CalleTest {

    @Test
    public void calleNuevaDebeTenerSemaforoRojo() {
        Calle calle = new Calle("18 de Julio", 10);

        assertEquals("rojo", calle.getSemaforo());
    }

    @Test
    public void incorporarVehiculoDebeAumentarCola() {
        Calle calle = new Calle("Rivera", 10);
        Vehiculo v = new Vehiculo(1, "auto", 10, "Fiat");

        calle.incorporarVehiculo(v);

        assertEquals(1, calle.tamaño());
    }

    @Test
    public void siSemaforoEstaVerdeVehiculoDebeCircular() {
        Calle calle = new Calle("Bvar Artigas",10);
        Vehiculo v = new Vehiculo(1, "auto", 10, "Fiat");

        calle.incorporarVehiculo(v);
        calle.setSemaforo("verde");

        calle.circular();

        assertEquals(0, calle.tamaño());
    }

    @Test
    public void siSemaforoEstaRojoVehiculoNoDebeCircular() {
        Calle calle = new Calle("Bvar Artigas", 10);
        Vehiculo v = new Vehiculo(1, "auto", 10, "Fiat");

        calle.incorporarVehiculo(v);
        calle.setSemaforo("rojo");

        calle.circular();

        assertEquals(1, calle.tamaño());
    }
}
