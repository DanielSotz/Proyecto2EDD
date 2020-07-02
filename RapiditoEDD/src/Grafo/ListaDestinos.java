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
public class ListaDestinos {
    
    public NodoG inicio;
    public int tamanio;
    
    public ListaDestinos(){
        
        this.inicio = null;        
        this.tamanio = 0;
    }
    
    public boolean estaVacia(){
        if(this.inicio == null) return true;
        else return false;
    }
    
    public void insertar(String origen, String destino, int tiempo)
    {
        NodoG nuevo = new NodoG(origen,destino,tiempo);
        
        if(estaVacia())
        {
            this.inicio = nuevo;
        }
        else
        {
            NodoG temp = this.inicio;
            
            while(temp.siguiente !=null)
            {
                temp = temp.siguiente;
            }
            
            temp.siguiente = nuevo;         
            
        }
        tamanio++;
        
    }
    
    
    public void imprimir()
    {
        if(estaVacia())
        {
            System.out.println("lista destino vacia");
        
        }
        else
        {
            NodoG temp = this.inicio;
            while( temp!=null)
            {
                System.out.println(temp.destino+ " en "+ temp.tiempo);
                temp = temp.siguiente;
            }            
        }
        
        
    }
}
