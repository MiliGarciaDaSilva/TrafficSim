package ucu.edu.aed;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ucu.edu.aed.modelo.Calle;
import ucu.edu.aed.modelo.EstadoSemaforo;
import ucu.edu.aed.modelo.Interseccion;
import ucu.edu.aed.modelo.Semaforo;
import ucu.edu.aed.modelo.Vehiculo;
import ucu.edu.aed.sistema.GestionTransito;
import ucu.edu.aed.sistema.GestionVehiculos;
import ucu.edu.aed.tda.Implementaciones.ColaArregloCircular;

public class TraficoSimulationTest {

  private GestionTransito gestionTransito;
  private GestionVehiculos gestionVehiculos;
  private Interseccion interseccion;

  @Before
  public void setUp() {
    // Inicializar gestiones
    gestionTransito = new GestionTransito();
    gestionVehiculos = GestionVehiculos.getInstancia();

    // Crear semáforos
    Semaforo semaforo1 = new Semaforo(EstadoSemaforo.ROJO, 10);
    Semaforo semaforo2 = new Semaforo(EstadoSemaforo.ROJO, 10);

    // Crear calles
    Calle calle1 = new Calle(semaforo1, "8 de octubre");
    Calle calle2 = new Calle(semaforo2, "Garibaldi");

    // Crear intersección
    ColaArregloCircular<Calle> calles = new ColaArregloCircular<>();
    calles.agregar(calle1);
    calles.agregar(calle2);
    interseccion = gestionTransito.crearInterseccion(calles);
  }

  @Test
  public void testCrearVehiculos() {
    Vehiculo vehiculo1 = gestionVehiculos.crearVehiculo(1, "Auto", 5);
    Vehiculo vehiculo2 = gestionVehiculos.crearVehiculo(2, "Moto", 3);

    assertNotNull("Vehículo 1 no debería ser null", vehiculo1);
    assertNotNull("Vehículo 2 no debería ser null", vehiculo2);
    assertEquals("ID del vehículo 1 debería ser 1", 1, vehiculo1.id);
    assertEquals("Tipo del vehículo 1 debería ser Auto", "Auto", vehiculo1.tipo);
    assertEquals("Tiempo de llegada debería ser 5", 5, vehiculo1.tiempoLlegada);
  }

  @Test
  public void testRegistrarVehiculos() {
    Vehiculo vehiculo1 = gestionVehiculos.crearVehiculo(1, "Auto", 5);
    Vehiculo vehiculo2 = gestionVehiculos.crearVehiculo(2, "Moto", 3);

    boolean resultado1 = gestionVehiculos.registrarVehiculo(vehiculo1);
    boolean resultado2 = gestionVehiculos.registrarVehiculo(vehiculo2);

    assertTrue("Debería registrar el primer vehículo", resultado1);
    assertTrue("Debería registrar el segundo vehículo", resultado2);
  }

  @Test
  public void testIngresarVehiculosEnCalle() {
    Calle calle1 = interseccion.getCalles().obtener(0);
    Vehiculo vehiculo1 = gestionVehiculos.crearVehiculo(1, "Auto", 5);
    Vehiculo vehiculo2 = gestionVehiculos.crearVehiculo(2, "Moto", 3);

    calle1.ingresarVehiculo(vehiculo1);
    calle1.ingresarVehiculo(vehiculo2);

    assertEquals("Debería haber 2 vehículos en espera", 2, calle1.getEspera().tamaño());
  }

  @Test
  public void testTransitarEnSemaforoVerde() {
    Calle calle1 = interseccion.getCalles().obtener(0);
    Vehiculo vehiculo1 = gestionVehiculos.crearVehiculo(1, "Auto", 5);

    calle1.ingresarVehiculo(vehiculo1);
    assertEquals("Inicialmente debe haber 1 vehículo", 1, calle1.getEspera().tamaño());

    // Poner semáforo en verde y transitar
    calle1.setEstadoSemaforo(EstadoSemaforo.VERDE);
    calle1.transitar();

    // El vehículo debería haber pasado
    assertEquals("El vehículo debería haber pasado", 0, calle1.getEspera().tamaño());
    assertEquals("El semáforo debería estar en rojo", EstadoSemaforo.ROJO, calle1.semaforo.getEstado());
  }

  @Test
  public void testEstadoSemaforoInicial() {
    Calle calle1 = interseccion.getCalles().obtener(0);
    Calle calle2 = interseccion.getCalles().obtener(1);

    // Los semáforos deben estar inicialmente en rojo
    assertEquals("Semáforo de calle 1 debe estar en rojo", EstadoSemaforo.ROJO, calle1.semaforo.getEstado());
    assertEquals("Semáforo de calle 2 debe estar en rojo", EstadoSemaforo.ROJO, calle2.semaforo.getEstado());
  }

  @Test
  public void testSimularMultiplosCiclos() {
    Calle calle1 = interseccion.getCalles().obtener(0);
    Calle calle2 = interseccion.getCalles().obtener(1);

    // Agregar vehículos a ambas calles
    for (int i = 1; i <= 4; i++) {
      Vehiculo v = gestionVehiculos.crearVehiculo(i, "Auto", 2);
      gestionVehiculos.registrarVehiculo(v);
      calle1.ingresarVehiculo(v);
    }

    for (int i = 5; i <= 8; i++) {
      Vehiculo v = gestionVehiculos.crearVehiculo(i, "Auto", 2);
      gestionVehiculos.registrarVehiculo(v);
      calle2.ingresarVehiculo(v);
    }

    assertEquals("Calle 1 debe tener 4 vehículos", 4, calle1.getEspera().tamaño());
    assertEquals("Calle 2 debe tener 4 vehículos", 4, calle2.getEspera().tamaño());

    // Simular 2 ciclos
    gestionTransito.simularCiclos(2);

    // Después de 2 ciclos, los vehículos deberían haber avanzado
    assertTrue("Debe haber pasado algún vehículo", 
        calle1.getEspera().tamaño() < 4 || calle2.getEspera().tamaño() < 4);
  }

  @Test
  public void testEliminarVehiculo() {
    Vehiculo vehiculo1 = gestionVehiculos.crearVehiculo(1, "Auto", 5);
    Vehiculo vehiculo2 = gestionVehiculos.crearVehiculo(2, "Moto", 3);

    gestionVehiculos.registrarVehiculo(vehiculo1);
    gestionVehiculos.registrarVehiculo(vehiculo2);

    boolean resultado = gestionVehiculos.eliminarVehiculo(1);
    assertTrue("Debería eliminar el vehículo con ID 1", resultado);

    // Intenta eliminar un vehículo que no existe
    boolean resultadoNoExiste = gestionVehiculos.eliminarVehiculo(999);
    assertFalse("No debería encontrar vehículo con ID 999", resultadoNoExiste);
  }

}
