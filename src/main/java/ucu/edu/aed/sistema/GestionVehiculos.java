package ucu.edu.aed.sistema;

import java.io.EOFException;
import java.util.function.Predicate;

import ucu.edu.aed.modelo.Vehiculo;
import ucu.edu.aed.tda.Implementaciones.ListaEnlazada;
import ucu.edu.aed.tda.Interfaces.TDALista;


public class GestionVehiculos {

    TDALista<Vehiculo> ListaVehiculos;
    
//  SINGLETON DE GESTION VEHICULOS
    private static GestionVehiculos instancia;

    private GestionVehiculos() {
        ListaVehiculos = new ListaEnlazada<>();
    }
    
    public static GestionVehiculos getInstancia() {
        if (instancia == null) {
            instancia = new GestionVehiculos();
        }
        return instancia;
    }

    public void registrarVehiculo(String unTipo,int unTiempo, String unaMarca){
        if(unTiempo > 0){
            if(ListaVehiculos.tamaño() > 0 ){
            Vehiculo ultimo = ListaVehiculos.obtener(ListaVehiculos.tamaño() - 1); 
            Vehiculo unVehiculo = new Vehiculo(ultimo.getIdentificador() + 1, unTipo, unTiempo, unaMarca);
            ListaVehiculos.agregar(unVehiculo);
            }
            else{
                Vehiculo unVehiculo = new Vehiculo( 1, unTipo, unTiempo, unaMarca);
                ListaVehiculos.agregar(unVehiculo);
            }
        }
    }

    public Vehiculo obtenerVehiculo(int id){
        if(id > 0)
            return ListaVehiculos.buscar(Vehiculo -> Vehiculo.getIdentificador() == id);
        throw new IndexOutOfBoundsException();
    }

    public boolean retirarVehiculo(int unId){
        Vehiculo unVehiculo = ListaVehiculos.buscar(Vehiculo -> Vehiculo.getIdentificador() == unId);
        if(unVehiculo != null)
            return ListaVehiculos.eliminar(unVehiculo);
        return false;
    }

    public String listarVehiculos(){
       // String listado = ((ListaEnlazada<Vehiculo>) ListaVehiculos).listarElementos(); 
       // The method listarElementos() is undefined for the type TDALista<Vehiculo>
       if(!ListaVehiculos.esVacio()){
        String listado = "";
        for(int i = 0 ; i < ListaVehiculos.tamaño(); i++) {
            listado += ListaVehiculos.obtener(i);
        }
        return listado;
       }
       return null;
    }

}
