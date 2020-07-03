/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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
    
    public String imprimirTODO()
    {
        NodoGra temp=cabeza;
        String res="";
	while(temp!= null)
	{		
                res +=temp.destinos.imprimir();                
                temp = temp.siguiente;		
	}
        return res;
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
    
    
    public boolean graficar(){
        StringBuilder graf  = new StringBuilder(); //grafica total
        
        
                       
        graf.append("digraph grafo { rankdir=LR\n");
        graf.append("node [shape=circle];\n");
        graf.append(imprimirTODO());    
        
        graf.append("}");

        //JOptionPane.showMessageDialog(null,graf);
        return this.graf_users(graf.toString());
    }
    
    public boolean graf_users(String grafica){
        //File archivo =new File("hash_user.txt");
        try
            {
            File archivo =new File("grafo.txt");
            FileWriter escribir= new FileWriter(archivo);
            escribir.write(grafica);
            escribir.close();
            }

            catch(Exception e)
            {
            JOptionPane.showMessageDialog(null, "Error al escribir","Erro",JOptionPane.ERROR_MESSAGE);
            return false;
            }
        
        try {

            Runtime rt = Runtime.getRuntime();
            //rt.exec( cmd );
            Process p = rt.exec("dot -Tpng grafo.txt -o grafo.png");
            p.waitFor();
            
            //definiendo la ruta en la propiedad file
            Runtime.getRuntime().exec("cmd /c start grafo.png");
            //rt.exec("hash_user.jpg");
            //Desktop.getDesktop().open(new File("hash_user.jpg"));

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex,"Error",JOptionPane.ERROR_MESSAGE);
                return false;
            } finally {}
        
        return true;
        
    }
    
    
    
    
    
    
}
