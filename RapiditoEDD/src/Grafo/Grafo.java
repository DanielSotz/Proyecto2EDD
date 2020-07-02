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
public class Grafo {
    
    public NodoGra cabeza;       
    private int tamanio;
    
    public Grafo(){
        this.cabeza = null;        
        this.tamanio = 0;     
    }
    
    public boolean estaVacia(){
        if(cabeza == null) return true;
        else return false;
    }
    
    public void insertar(String origen,String destino,int tiempo)
    {
        NodoGra buscar = BuscarNodo(origen);
        
        if(buscar != null)
        {
            buscar.destinos.insertar(origen, destino, tiempo);            
        }
        else
        {
            NodoGra temp = cabeza;
            
            NodoGra nuevo = new NodoGra(origen);
            nuevo.destinos.insertar(origen, destino, tiempo);
            
            
            if(estaVacia())
            {
                cabeza= nuevo;
                tamanio++;
            }else
            {
                NodoGra te = cabeza;
                
                while(te.siguiente !=null)
                {
                    te = te.siguiente;   
                }
                
                te.siguiente = nuevo;              
                tamanio++;                
            }
            
            
        }        
    }
    
    public void imprimirTODO()
    {
        NodoGra temp=cabeza;
	while(temp!= null)
	{
		System.out.println("\nOrigen "+temp.origen+" a:");
                temp.destinos.imprimir();
                temp = temp.siguiente;		
	}
    }
    
    public NodoGra imprimirOrigen(String origen)
    {
        NodoGra temp=cabeza;
	while(temp!= null)
	{
            if(temp.origen ==origen )
            {
               System.out.println("\nOrigen "+temp.origen+" a:");
               temp.destinos.imprimir();
               break;
            }
            temp = temp.siguiente;			
	}
        return temp;
        
    }
    
    public NodoGra BuscarNodo(String origen){
        NodoGra temp = cabeza;
        
        NodoGra actual = null;
        
        while(temp != null)
        {
            if(temp.origen == origen)
            {
                actual=temp;
                break;                
            }
            temp = temp.siguiente;
        }
           
        return actual;
    }
    
    public int getTamanio(){
        return tamanio;
    }
    
    
    
    
}
