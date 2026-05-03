package ucu.edu.aed.modelo;

public class Vehiculo implements Comparable<Vehiculo>{
  public int id;
  public String tipo;
  public int tiempoLlegada;
  
  public Vehiculo(int id, String tipo, int tiempoLlegada){
    this.id = id;
    this.tipo = tipo;
    this.tiempoLlegada = tiempoLlegada;
  }

  @Override
  public int compareTo(Vehiculo otroVehiculo) {
    return Integer.compare(id, otroVehiculo.id);
  }

  public int getTiempoLlegada() {
    return tiempoLlegada;
  }
}
