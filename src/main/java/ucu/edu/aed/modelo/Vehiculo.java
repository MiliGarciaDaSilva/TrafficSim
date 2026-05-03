package ucu.edu.aed.modelo;

public class Vehiculo {

    private int Identificador;
    private String tipoVehiculo;
    private String marca;
    private int tiempoLlegada;
    private int tiempoViaje;

    public Vehiculo (int unId, String unTipo, int unTiempo , String unaMarca){
        this.Identificador = unId;
        this.tipoVehiculo = unTipo;
        this.tiempoLlegada = unTiempo;
        this.tiempoViaje = this.tiempoLlegada;
        this.marca = unaMarca;
    }

    public int getTiempoLlegada(){
        return tiempoLlegada;
    }

    public int getTiempoViaje(){
        return tiempoViaje;
    }
    
    public void disminuirTiempoViaje(int unTiempo){
        this.tiempoViaje -= unTiempo;
    }

    public void finalizarViaje(){
        this.tiempoViaje = tiempoLlegada;
    }

    public int getIdentificador(){
        return Identificador;
    }
    
    public String toString(){
        return "id vehiculo : "+ Identificador + " - " + "tipo vehiculo : " + tipoVehiculo + " - " + "marca : " + marca +" - " + "tiempo llegada :" + tiempoLlegada + "\n";
    }
    


}
