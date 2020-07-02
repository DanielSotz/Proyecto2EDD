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
public class NodoGra {
    
    public String origen;
    public NodoGra siguiente =null;
    public ListaDestinos destinos;

    public NodoGra(String origen) {
        destinos = new ListaDestinos();
        this.origen = origen;
    }
    
    
    
}
