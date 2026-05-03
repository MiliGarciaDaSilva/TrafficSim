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

    public void incorporarCalle(String unNombre, int tiempoVerde){
        if(!unNombre.equals(null) && !unNombre.equals("")){
            Calle unCalle = new Calle(unNombre, tiempoVerde);
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
            if(unaCalle == null){
                System.out.println(unaCalle + " no existe");
                return false;
            }else{
                TDACola<Vehiculo> unaCola = calle.getCola();
                if(unaCola.contiene(unVehiculo) == false){
                    if(calle != null){
                        calle.incorporarVehiculo(unVehiculo);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void circularSemaforo(int ciclos){
        for (int i = 0; i < ciclos; i++) {
            cruce.realizarAccion(calle ->{ 
                if(calle == cruce.obtener(0))
                    calle.setSemaforo("verde");
                calle.circular();
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
