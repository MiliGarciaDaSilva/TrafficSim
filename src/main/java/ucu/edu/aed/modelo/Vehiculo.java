package ucu.edu.aed.modelo;

public class Vehiculo {

    private int Identificador;
    private String tipoVehiculo;
    private String marca;
    private int tiempoLlegada;

    public Vehiculo (int unId, String unTipo, int unTiempo , String unaMarca){
        this.Identificador = unId;
        this.tipoVehiculo = unTipo;
        this.tiempoLlegada = unTiempo;
        this.marca = unaMarca;
    }

    public int getIdentificador(){
        return Identificador;
    }
    
    public String toString(){
        return "id vehiculo : "+ Identificador + " - " + "tipo vehiculo : " + tipoVehiculo + " - " + "marca : " + marca +" - " + "tiempo llegada :" + tiempoLlegada + "\n";
    }
    


}
