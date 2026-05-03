package ucu.edu.aed.modelo;

public class Vehiculo implements Comparable<Vehiculo>{
  private int id;
  public String tipo;
  public int tiempoLlegada;
  public static int contadorId = 0;
  
  public Vehiculo(String tipo, int tiempoLlegada){
    this.id = ++contadorId;
    this.tipo = tipo;
    this.tiempoLlegada = tiempoLlegada;
  }

  public int getId(){
    return id;
  }
  @Override
  public int compareTo(Vehiculo otroVehiculo) {
    return Integer.compare(id, otroVehiculo.getId());
  }

  public int getTiempoLlegada() {
    return tiempoLlegada;
  }
}
