package ucu.edu.aed;

import ucu.edu.aed.modelo.Calle;
import ucu.edu.aed.modelo.EstadoSemaforo;
import ucu.edu.aed.modelo.Interseccion;
import ucu.edu.aed.modelo.Semaforo;
import ucu.edu.aed.modelo.Vehiculo;
import ucu.edu.aed.sistema.GestionTransito;
import ucu.edu.aed.sistema.GestionVehiculos;
import ucu.edu.aed.tda.Implementaciones.ColaArregloCircular;

public class Main {
  public static void main(String[] args) {
    GestionTransito gestionTransito = new GestionTransito();
    GestionVehiculos gestionVehiculos = GestionVehiculos.getInstancia();

    // Crear 8 vehículos de ejemplo
    Vehiculo vehiculo1 = gestionVehiculos.crearVehiculo("Auto", 5);
    Vehiculo vehiculo2 = gestionVehiculos.crearVehiculo("Moto", 3);
    Vehiculo vehiculo3 = gestionVehiculos.crearVehiculo("Camión", 8);
    Vehiculo vehiculo4 = gestionVehiculos.crearVehiculo("Bicicleta", 2);
    Vehiculo vehiculo5 = gestionVehiculos.crearVehiculo("Auto", 4);
    Vehiculo vehiculo6 = gestionVehiculos.crearVehiculo("Moto", 3);
    Vehiculo vehiculo7 = gestionVehiculos.crearVehiculo("Autobús", 6);
    Vehiculo vehiculo8 = gestionVehiculos.crearVehiculo("Auto", 5);

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
    Interseccion interseccion = gestionTransito.crearInterseccion(calles);

    // Agregar vehículos a las calles
    calle1.ingresarVehiculo(vehiculo1);
    calle1.ingresarVehiculo(vehiculo2);
    calle1.ingresarVehiculo(vehiculo3);
    calle1.ingresarVehiculo(vehiculo4);
    calle2.ingresarVehiculo(vehiculo5);
    calle2.ingresarVehiculo(vehiculo6);
    calle2.ingresarVehiculo(vehiculo7);
    calle2.ingresarVehiculo(vehiculo8);

    System.out.println("\n============== Prueba con 2 intersecciones ============== \n");

    //simular ciclos
    gestionTransito.simularCiclos(5);

    
    Semaforo semaforo3 = new Semaforo(EstadoSemaforo.ROJO, 10);
    Calle calle3 = new Calle(semaforo3, "Urquiza");
    interseccion.agregarCalle(calle3);
    calle3.ingresarVehiculo(vehiculo5);
    calle3.ingresarVehiculo(vehiculo6);
    calle3.ingresarVehiculo(vehiculo7);
    calle3.ingresarVehiculo(vehiculo8);

    System.out.println("\n============== Prueba con 3 intersecciones ============== \n");

    gestionTransito.simularCiclos(7);
  }
}
