package ucu.edu.aed.sistema;

import ucu.edu.aed.modelo.Calle;
import ucu.edu.aed.modelo.Interseccion;
import ucu.edu.aed.modelo.Vehiculo;
import ucu.edu.aed.tda.Implementaciones.ListaEnlazada;
import ucu.edu.aed.tda.Interfaces.TDALista;

public class SistemaTrafico {
    GestionVehiculos gVehiculos;
    TDALista<Interseccion> lstIntersecciones;

    //  SINGLETON DE GESTION VEHICULOS
    private static SistemaTrafico instancia;

    private SistemaTrafico() {
        gVehiculos = GestionVehiculos.getInstancia();
        lstIntersecciones = new ListaEnlazada<>();
    }
    
    public static SistemaTrafico getInstancia() {
        if (instancia == null) {
            instancia = new SistemaTrafico();
        }
        return instancia;
    }

    public void registrarVehiculo(String unTipo,int unTiempo, String unaMarca){
        gVehiculos.registrarVehiculo(unTipo, unTiempo, unaMarca);
    }

    public void incorporarVehiculo(int idVehiculo,String interseccion ,String nombCalle){
        Interseccion unaInterseccion = lstIntersecciones.buscar
        (interseccion1 -> interseccion1.getNombre().equals(interseccion));
        if(unaInterseccion != null){
            unaInterseccion.incorporarVehiculo(idVehiculo, nombCalle);
        }
    }

    public Vehiculo obtenerVehiculo(int unId){
        return gVehiculos.obtenerVehiculo(unId);
    }

    public boolean retirarVehiculo (int unId){
        return gVehiculos.retirarVehiculo(unId);
    }

    public String listarVehiculos(){
        return gVehiculos.listarVehiculos();
    }

    public void incorporarInterseccion(String unNombre){
        if(!unNombre.isEmpty()){
        Interseccion unaInterseccion = lstIntersecciones.buscar
        (interseccion -> interseccion.getNombre().equals(unNombre));
        if(unaInterseccion == null){
            lstIntersecciones.agregar(new Interseccion(unNombre));
        }
        else{
            System.out.println(unNombre + " ya existe");
        }
    }
}
    public void incorporarCalle(String nombCalle, String nombInterseccion, int tiempoVerde){
        if(!lstIntersecciones.esVacio()){
            Interseccion unaInterseccion = lstIntersecciones.buscar
            (interseccion -> interseccion.getNombre().equals(nombInterseccion));
            if(unaInterseccion == null){
                System.out.println(nombInterseccion + " no existe");
                return;
            }else{
                Calle unaCalle = unaInterseccion.obtenerCalle(nombCalle);
                if(unaCalle == null){
                    unaInterseccion.incorporarCalle(nombCalle, tiempoVerde);
                }
                else{
                    System.out.println(nombCalle + " ya existe");
                }
            }
        }

    }

    public void simularTrafico(String unNombre,int ciclos){
        Interseccion unaInterseccion = lstIntersecciones.buscar
        (interseccion -> interseccion.getNombre().equals(unNombre));
        if(unaInterseccion != null){
            unaInterseccion.circularSemaforo(ciclos);
        }
        else{
            System.out.println(unNombre + " no existe");
        }
    }

    

    


}
