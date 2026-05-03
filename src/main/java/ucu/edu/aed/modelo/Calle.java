package ucu.edu.aed.modelo;

import ucu.edu.aed.tda.Implementaciones.Cola;
import ucu.edu.aed.tda.Interfaces.TDACola;

public class Calle {
    private String nombre;
    private TDACola<Vehiculo> colaVehiculos;
    private String semaforo;
    private int tiempoVerde;

    public Calle (String unNombre,int unTiempo){
        this.nombre = unNombre;
        this.tiempoVerde = unTiempo;
        colaVehiculos = new Cola<>();
        semaforo = "rojo";

    }

    public TDACola<Vehiculo> getCola(){
        return this.colaVehiculos;
    }

    public void incorporarVehiculo(Vehiculo unVehiculo){
        colaVehiculos.poneEnCola(unVehiculo);        
    }

    public void circular(){ // Posiblemente en Interseccion cambio los estados del semaforo
        if(semaforo.equals("verde")){
            if(!colaVehiculos.esVacio()){
                int tiempo = this.tiempoVerde;
                while (!colaVehiculos.esVacio() && tiempo >= colaVehiculos.frente().getTiempoViaje()){
                        Vehiculo vehiculoFrente = colaVehiculos.quitaDeCola();
                        tiempo -= vehiculoFrente.getTiempoViaje();                    
                }
            }
            semaforo = "amarillo"; 
        } else if(semaforo.equals("amarillo")){
            semaforo = "rojo";
        } else {
            semaforo = "verde";
        }
    
        
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getSemaforo(){
        return this.semaforo;
    }

    public void setSemaforo(String unColor){
        this.semaforo = unColor;
    }

    public void setTiempoVerde(int tiempo){
        this.tiempoVerde = tiempo;
    }
    
    public int tamaño(){
        return colaVehiculos.tamaño();
    }

    public String toString(){
        return "Nombre calle :" + nombre + " - " + "cantidad vehiculos: " + colaVehiculos.tamaño() + " - " + "Semaforo: " + semaforo;
    }
}
