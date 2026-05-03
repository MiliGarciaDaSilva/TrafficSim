package ucu.edu.aed.modelo;

public class Semaforo {
  public EstadoSemaforo estado;
  public int tiempoEnVerde;
  public int tiempoEnAmarillo = 5;

  public Semaforo(EstadoSemaforo estado, int tiempoEnVerde){
    this.estado = estado;
    this.tiempoEnVerde = tiempoEnVerde;
  }

  public EstadoSemaforo getEstado() {
    return estado;
  }

  public int getTiempoParaTransitar() {
    return tiempoEnVerde + tiempoEnAmarillo;
  }

  public void setEstado(EstadoSemaforo estado) {
    this.estado = estado;
  }

}
