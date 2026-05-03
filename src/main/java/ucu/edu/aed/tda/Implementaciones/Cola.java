package ucu.edu.aed.tda.Implementaciones;


import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.function.Consumer;
import java.util.function.Predicate;

import ucu.edu.aed.tda.Interfaces.TDACola;
import ucu.edu.aed.tda.Interfaces.TDALista;
import ucu.edu.aed.tda.Interfaces.TDANodo;

public class Cola<T> implements TDACola<T> {

    TDANodo<T> primero;
    TDANodo<T> ultimo;
    
    int cantidad;

    public void agregar(T elem){
        throw new IndexOutOfBoundsException("Funcion no valida para Cola"); 
    }

    public void agregar(int index, T elem){
        throw new IndexOutOfBoundsException("Funcion no valida para Cola"); 
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
        throw new IndexOutOfBoundsException("Funcion no valida para Cola"); 
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
        throw new IndexOutOfBoundsException("Funcion no valida para Cola"); 
        
    }

    public int indiceDe (T elem){
        throw new IndexOutOfBoundsException("Funcion no valida para Cola"); 
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
        throw new IndexOutOfBoundsException("Funcion no valida para Cola"); 
    }

    

    public int tamaño(){
        return cantidad;
    }

    public boolean esVacio(){
        return (primero == null);
    }

    public void vaciar(){
        primero = null;
        ultimo = null;
        cantidad = 0;
    }


    
    public T frente(){

        return primero.getDato();
    }

    public boolean poneEnCola(T dato){
        if(esVacio()){
            primero = new TDANodo<T>(dato);
            ultimo = primero;
            cantidad++;
            return true;
        }
        
        TDANodo<T> nuevoNodo = new TDANodo<T>(dato);
        ultimo.setSiguiente(nuevoNodo);
        ultimo = nuevoNodo;
        cantidad++;
        return true;
    }

    public T quitaDeCola(){
        if(esVacio())
            return null;
        TDANodo<T> nodoAEliminar = primero;
        primero = primero.getSiguiente();
        nodoAEliminar.setSiguiente(null);
        cantidad--;
        return nodoAEliminar.getDato();

        
    }

    @Override
    public void realizarAccion(Consumer<T> accion) {
        throw new IndexOutOfBoundsException("Funcion no valida para Cola"); 
    }

}
