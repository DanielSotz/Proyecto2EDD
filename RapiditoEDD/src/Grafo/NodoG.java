/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

/**
 *
 * @author danis
 */
public class NodoG {
    public String origen;
    public String destino;
    public int tiempo;
    NodoG siguiente;
    

    public NodoG(String origen, String destino, int tiempo) {
        this.origen = origen;
        this.destino = destino;
        this.tiempo = tiempo;        
    }
    
  
    
    
    
}
