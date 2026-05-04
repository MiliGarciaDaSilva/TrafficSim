package ucu.edu.aed.tda.Implementaciones;


import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.function.Consumer;
import java.util.function.Predicate;

import ucu.edu.aed.tda.Interfaces.TDALista;
import ucu.edu.aed.tda.Interfaces.TDANodo;

public class ListaEnlazada<T> implements TDALista<T> {

    TDANodo<T> primero;
    
    int cantidad;

    

    public void agregar(T elem){

        if(esVacio()){
            primero = new TDANodo<T>(elem);
            cantidad++;
        }
        else{
            TDANodo<T> nodoActual = primero;
            while (nodoActual.getSiguiente() != null) {
                nodoActual = nodoActual.getSiguiente();
            }
            TDANodo<T> nuevoNodo = new TDANodo<T>(elem);
            nodoActual.setSiguiente(nuevoNodo);
            cantidad++;
            

        }
   
    }

    
    public void agregar(int index, T elem){
        if(index >= 0){
            if (esVacio()){
                if(index == 0 ){
                    primero = new TDANodo<T>(elem);
                    cantidad++;
                    }
                else
                throw new IndexOutOfBoundsException();
            }
            else{
                TDANodo<T> nodoActual = primero;
                int posicion = 0;
                while (nodoActual != null && posicion != index - 1) { // me detengo en el nodo anterior al nuevo
                    nodoActual = nodoActual.getSiguiente();
                    posicion ++;
                }
                if(posicion == index - 1){
                    TDANodo<T> nodoDesplazado = nodoActual.getSiguiente();
                    TDANodo<T> nuevoNodo = new TDANodo<T>(elem);
                    nuevoNodo.setSiguiente(nodoDesplazado);
                    nodoActual.setSiguiente(nuevoNodo);
                    cantidad++;
                }
                else{
                    throw new IndexOutOfBoundsException();
                }

            }
        }

    }

    public T obtener(int index){
        
        TDANodo<T> nodoActual = primero;
        int contador = 0;

        
        
        while (nodoActual != null && contador != index) {
           
            nodoActual = nodoActual.getSiguiente();   
            contador++;
        }    

        if(index == contador)
           return nodoActual.getDato();
        return null;
        
        
    }
    
    public T remover(int index){
        TDANodo<T> nodoActual = primero;
        TDANodo<T> nodoBorrar;
        T elementoBorrado;
        
        if(index > 0){ 
            if(!esVacio()){ 
                int contador = 0;
                while (nodoActual != null && contador != index -1) {
                    nodoActual = nodoActual.getSiguiente();
                    contador++;
                }

                if(contador == index - 1) {
                        nodoBorrar = nodoActual.getSiguiente();
                        nodoActual.setSiguiente(nodoBorrar.getSiguiente());
                        elementoBorrado = nodoBorrar.getDato();
                        nodoBorrar.setSiguiente(null);
                        cantidad--;
                        return elementoBorrado;
                        
                }
            }
        }
        else 
        if (index == 0 ){
            if (tamaño() == 1){
            nodoBorrar = primero;
            elementoBorrado = nodoBorrar.getDato();
            vaciar();
            return elementoBorrado;
            }
            else{
                nodoBorrar = primero;
                elementoBorrado = nodoBorrar.getDato();
                primero.setSiguiente(nodoActual.getSiguiente()); 
                return elementoBorrado;

            }
        }
        throw new IndexOutOfBoundsException();

        
    }

    public boolean contiene(T elem){
        TDANodo<T> nodoActual = primero;
        while (nodoActual != null) {
            if(nodoActual.getDato() == elem){
                return true;
            }
            nodoActual = nodoActual.getSiguiente();
        }
        return false;
    }

    public boolean eliminar(T elem){
        TDANodo<T> nodoActual = primero;
        TDANodo<T> nodoSiguiente = nodoActual.getSiguiente();
        if(esVacio())
            return false;
        if(nodoActual.getSiguiente() == null && nodoActual.getDato() == elem){
            vaciar();
            return true;    
        }
        while (nodoActual != null) {
            if(primero.getDato() == elem){
                primero = nodoActual.getSiguiente();
                cantidad --;
                return true;    
            }
            
            if(nodoSiguiente.getDato() == elem){
                nodoActual.setSiguiente(nodoSiguiente.getSiguiente());
                nodoSiguiente.setSiguiente(null);
                cantidad--;
                return true;    
            }
            nodoActual = nodoActual.getSiguiente();
            nodoSiguiente = nodoActual.getSiguiente();
                
            }
            
        return false;
        
    }

    public int indiceDe (T elem){

        TDANodo<T> nodoActual = primero;
        
        int indice = 0;
        while (nodoActual != null) {
            if(nodoActual.getDato() == elem){
                return indice;
            }
            nodoActual = nodoActual.getSiguiente();
            indice++;
        }
        return 0;
    }


    public T buscar(Predicate<T> criterio){
         
        TDANodo<T> nodoActual = primero; 

        while (nodoActual != null) {
            
            if (criterio.test(nodoActual.getDato())) {
                return nodoActual.getDato(); 
        }
        nodoActual = nodoActual.getSiguiente();
    }

        return null; 

    }
    
    public String listarElementos(){
        
        String listado = "";
        if(esVacio()){
            return null;
            
        }
        else{
            TDANodo<T> nodoActual = primero;
            while (nodoActual.getSiguiente() != null) {
                nodoActual = nodoActual.getSiguiente();
                listado += nodoActual.getDato().toString() + "," ;
            }
            
        }


        return listado;
    }

    public TDALista<T> ordenar(Comparator<T> comparador){
        
        ListaEnlazada<T> nuevaLista = new ListaEnlazada<>();
        
        
        TDANodo<T> nodoActual = primero;
        while (nodoActual != null) {
            nuevaLista.agregar(nodoActual.getDato()); 
            nodoActual = nodoActual.getSiguiente();
        }

        
        if (nuevaLista.primero == null || nuevaLista.primero.getSiguiente() == null) {
            return nuevaLista; 
        }

        boolean huboIntercambio;
        do {
            huboIntercambio = false;
            TDANodo<T> temp = nuevaLista.primero;
            while (temp.getSiguiente() != null) {
                
                if (comparador.compare(temp.getDato(), temp.getSiguiente().getDato()) > 0) {
                    T datoTemp = temp.getDato();
                    temp.setDato(temp.getSiguiente().getDato());
                    temp.getSiguiente().setDato(datoTemp);
                    huboIntercambio = true;
                }
                temp = temp.getSiguiente();
            }
        } while (huboIntercambio);

        return nuevaLista; 
    }

    

    public int tamaño(){
        return cantidad;
    }

    public boolean esVacio(){
        
        return (primero == null);

    }

    public void vaciar(){
        primero = null;
        cantidad = 0;
    }
}
