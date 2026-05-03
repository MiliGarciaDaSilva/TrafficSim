package ucu.edu.aed.modelo;

import ucu.edu.aed.tda.Implementaciones.Cola;

public class Calle {
  public Semaforo semaforo;
  public Cola<Vehiculo> espera = new Cola<>();
  public String nombre;
  
  public Calle(Semaforo semaforo, String nombre){
    this.semaforo = semaforo;
    this.nombre = nombre;
  }

  public String getNombre(){
    return nombre;
  }

  public void setEstadoSemaforo(EstadoSemaforo estado){
    semaforo.setEstado(estado);
  }

  public void ingresarVehiculo(Vehiculo v){
    espera.agregar(v);
  }

  public void transitar(){
    if (semaforo.getEstado() == EstadoSemaforo.VERDE) {
      if (!espera.esVacio()) {
        int i = semaforo.getTiempoParaTransitar();
        while (!espera.esVacio() && i >= espera.frente().getTiempoLlegada()) {
          Vehiculo vehiculoPasado = espera.quitaDeCola();
          i -= vehiculoPasado.getTiempoLlegada();
        }
      }
      semaforo.setEstado(EstadoSemaforo.ROJO);
    }
  }

  public Cola<Vehiculo> getEspera() {
    return espera;
  }

}
