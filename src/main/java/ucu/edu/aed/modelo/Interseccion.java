package ucu.edu.aed.modelo;


import ucu.edu.aed.sistema.GestionVehiculos;
import ucu.edu.aed.tda.Implementaciones.ListaEnlazada;
import ucu.edu.aed.tda.Interfaces.TDACola;
import ucu.edu.aed.tda.Interfaces.TDALista;


public class Interseccion {
    private String nombre;
    private TDALista<Calle> cruce;

    public Interseccion(String unNombre){
        this.nombre = unNombre;
        cruce = new ListaEnlazada<>();
    }

    public void incorporarCalle(String unNombre){
        if(!unNombre.equals(null) && !unNombre.equals("")){
            Calle unCalle = new Calle(unNombre);
            cruce.agregar(unCalle);
        }
    }

    public Calle obtenerCalle(String unNombre){
        if(!unNombre.equals(null) && !unNombre.equals("")){
            return cruce.buscar(calle -> calle.getNombre().equals(unNombre));
        }
        return null;
    }

    public boolean incorporarVehiculo (int unId, String unaCalle){
        GestionVehiculos gestVehiculos = GestionVehiculos.getInstancia();
        Vehiculo unVehiculo = gestVehiculos.obtenerVehiculo(unId);
        if(unVehiculo != null){
            Calle calle = obtenerCalle(unaCalle);
            TDACola<Vehiculo> unaCola = calle.getCola();
            if(unaCola.contiene(unVehiculo) == false){
                if(calle != null){
                    calle.incorporarVehiculo(unVehiculo);
                    return true;
                }
            }
        }
        return false;
    }

    public void circularSemaforo(){
        for (int i = 0; i == 20; i ++){
            cruce.realizarAccion(calle ->{

                for(int k = 0; k == 5; k++)
                    calle.circular();
                if(calle.getSemaforo().equals("verde"))
                    calle.setSemaforo("amarillo");
                else if (calle.getSemaforo().equals("amarillo"))
                    calle.setSemaforo("rojo");
                else
                    calle.setSemaforo("verde");

            });

        }
    }

    public String getNombre(){
        return this.nombre;
    }

    public String toString(){
        return "Nombre: " + nombre + " - " + "Cantidad calles: " + cruce.tamaño();
    }


}
