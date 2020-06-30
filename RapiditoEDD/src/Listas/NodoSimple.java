/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listas;

/**
 *
 * @author Luis Rivera
 */
public class NodoSimple {
    
    private String Lugar, Tiempo;      
    private NodoSimple Siguiente;
    
    public NodoSimple(String Place, String Time){
    
        this.Lugar = Place;
        this.Tiempo = Time;
        this.Siguiente = null;
    }
    
    /**
     * @return the Siguiente
     */
    public NodoSimple getSiguiente() {
        return Siguiente;
    }

    /**
     * @param Siguiente the Siguiente to set
     */
    public void setSiguiente(NodoSimple Siguiente) {
        this.Siguiente = Siguiente;
    }
    
    /**
     * @return the Lugar
     */
    public String getLugar() {
        return Lugar;
    }

    /**
     * @param Lugar the Lugar to set
     */
    public void setLugar(String Lugar) {
        this.Lugar = Lugar;
    }
    
    /**
     * @return the Tiempo
     */
    public String getTiempo() {
        return Tiempo;
    }

    /**
     * @param Tiempo the Tiempo to set
     */
    public void setTiempo(String Tiempo) {
        this.Tiempo = Tiempo;
    }
    
}
