package ucu.edu.aed.modelo;

import ucu.edu.aed.tda.Implementaciones.ColaArregloCircular;

public class Interseccion {
  public ColaArregloCircular<Calle> calles;
  
  public Interseccion(ColaArregloCircular<Calle> calles){
    if (calles.tamaño() >= 2) {
      ColaArregloCircular<Calle> callesSeteadas = calles;
      int i = 0;
      while (i < calles.tamaño()) { //inicializamos los semaforos de la interseccion en rojo
        callesSeteadas.obtener(i).setEstadoSemaforo(EstadoSemaforo.ROJO);
        ++i;
      }
      this.calles = callesSeteadas;
    }
  }

  public void agregarCalle(Calle calle){
    calles.agregar(calle);
  }

  public ColaArregloCircular<Calle> getCalles(){
    return calles;
  }
}
