package ucu.edu.aed.sistema;

import ucu.edu.aed.modelo.Calle;
import ucu.edu.aed.modelo.EstadoSemaforo;
import ucu.edu.aed.modelo.Interseccion;
import ucu.edu.aed.tda.Implementaciones.ColaArregloCircular;

public class GestionTransito {
  public Interseccion interseccion;

  public Interseccion crearInterseccion(ColaArregloCircular<Calle> calles){
    Interseccion i = new Interseccion(calles);
    registrarInterseccion(i);
    return i;
  }

  private void registrarInterseccion(Interseccion i){
    this.interseccion = i;
  }

  public void simularCiclos(int ciclos){ /*ciclos = cantidad de ciclos que se quieren simular
    1 ciclo = cada vez que 1 semaforo de la interseccion se pone en verde*/
    int i = 0;
    int tamaño = interseccion.getCalles().tamaño();
    while (i < ciclos) {
      Calle calleActual = interseccion.getCalles().obtener(i % tamaño);
      System.out.println("Calle " + calleActual.getNombre() +" en rojo con "+ calleActual.getEspera().tamaño()+ " vehiculos en espera");
      calleActual.setEstadoSemaforo(EstadoSemaforo.VERDE);
      System.out.println("Calle " + calleActual.getNombre() +" en verde");
      calleActual.transitar();
      System.out.println("Calle " + calleActual.getNombre() +" vuelve a rojo con "+ calleActual.getEspera().tamaño()+ " vehiculos en espera");
      System.out.println("================ Termina ciclo "+ (i+1) + " ================");
      i++;
    }
    
  }

}
