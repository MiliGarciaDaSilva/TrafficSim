package ucu.edu.aed.modelo;

import ucu.edu.aed.tda.Implementaciones.Cola;
import ucu.edu.aed.tda.Interfaces.TDACola;

public class Calle {
    private String nombre;
    private TDACola<Vehiculo> colaVehiculos;
    private String semaforo;

    public Calle (String unNombre){
        this.nombre = unNombre;
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
        if(semaforo.equals("verde"))
            colaVehiculos.quitaDeCola();
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getSemaforo(){
        return this.semaforo;
    }

    public void setSemaforo(String color){
        this.semaforo = color;
    }

    public int tamaño(){
        return colaVehiculos.tamaño();
    }

    public String toString(){
        return "Nombre calle :" + nombre + " - " + "cantidad vehiculos: " + colaVehiculos.tamaño() + " - " + "Semaforo: " + semaforo;
    }
}
