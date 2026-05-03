package ucu.edu.aed.sistema;

import ucu.edu.aed.modelo.Vehiculo;
import ucu.edu.aed.tda.Implementaciones.AVLArbol;
import ucu.edu.aed.tda.Implementaciones.ListaEnlazada;

public class GestionVehiculos {
  //hago un arbol de registroPrincipal en el que se guarden todos los vehiculos registrados en todos las intersecciones, para poder eliminar un arbol por id de forma mas eficiente
  public AVLArbol<Vehiculo> registroPrincipal;

  //singleton
  private static GestionVehiculos instancia = null;

  private GestionVehiculos(){
    this.registroPrincipal = new AVLArbol<>();
  }

  public static GestionVehiculos getInstancia(){
    if (instancia == null) {
      instancia = new GestionVehiculos();
    }
    return instancia;
  }

  public Vehiculo crearVehiculo(int id, String tipo, int tiempoLlegada){
    Vehiculo v = new Vehiculo(id, tipo, tiempoLlegada);
    registrarVehiculo(v);
    return v;
  }

  private boolean registrarVehiculo(Vehiculo vehiculo){
    return registroPrincipal.insertar(vehiculo);
  }

  public ListaEnlazada<Vehiculo> listarVehiculos(){
    ListaEnlazada<Vehiculo> resultado = new ListaEnlazada<>();
    registroPrincipal.inOrder(vehiculo -> resultado.agregar(vehiculo));
    return resultado;
  }

  public boolean eliminarVehiculo(int id){
    return registroPrincipal.eliminar(new Comparable<Vehiculo>() {
      //creamos un vehiculo con el mismo id que estamos buscando, cuando el id sea igual, lo eliminamos
      //hacemos esto porque el metodo eliminar espera un Comparable<T> y nosotros queremos buscar por un int
      @Override
      public int compareTo(Vehiculo otro) {
        return Integer.compare(id, otro.id);
      }
    });
  }
}
