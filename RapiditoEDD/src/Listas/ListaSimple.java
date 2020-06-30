/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
/**
 *
 * @author Luis Rivera
 */
public class ListaSimple {
    
    private NodoSimple cabeza;
    private NodoSimple ultimo;
    private int tamanio;
    
    public ListaSimple(){
        this.cabeza = null;
        this.ultimo = null;
        this.tamanio = 0;
    }
        
    public boolean estaVacia(){
        if(cabeza == null) return true;
        else return false;
    }
    
    public int getTamanio(){
        return tamanio;
    }
    
    public ListaSimple InsertarNodo(String Lugar, String Tiempo){
        
        NodoSimple actual;
        NodoSimple nuevo = new NodoSimple(Lugar, Tiempo);
        
        if(estaVacia()){ 
            cabeza = nuevo;
            ultimo = nuevo;
        }else if(!estaVacia()){
            actual = cabeza;
            while(actual.getSiguiente() != null){
                actual = actual.getSiguiente();
            }
            if(actual.getSiguiente() == null){
                actual.setSiguiente(nuevo);
                nuevo.setSiguiente(null);
                ultimo = nuevo;
            }else{
                while(actual.getSiguiente() != null){
                    actual = actual.getSiguiente();
                }
            }
        }        
        tamanio++;
        return this;
    }   

    public ListaSimple BuscarNodo(String Lugar){
        NodoSimple actual;
        
        actual = cabeza;
        if(!estaVacia()){
            if(actual.getLugar() == Lugar){
                System.out.println("Datos del destino: "+ Lugar + " encontrados con éxito.");
                System.out.println("Destino: "+ actual.getLugar());
                System.out.println("Tiempo: "+ actual.getTiempo());
                return this;
            }else{
                while(actual.getLugar() != Lugar){
                    actual = actual.getSiguiente();
                    if(actual.getSiguiente() == null && actual.getLugar() != Lugar){
                        System.out.println("Datos no encontrados del destino: "+ Lugar + " inexistente.");
                        return this;
                    }
                }
                if(actual.getLugar() == Lugar){
                    System.out.println("Datos del destino: "+ Lugar + " encontrados con éxito.");
                System.out.println("Destino: "+ actual.getLugar());
                System.out.println("Tiempo: "+ actual.getTiempo()); 
                    return this;    
                }
            }
        }else{
            System.out.println("Lista vacía, datos no encontrados.");
        }        
        return this;
    }
    
    public ListaSimple EliminarNodo(String Lugar){
        NodoSimple actual, aux;
        
        aux = null;
        actual = cabeza;
        if(!estaVacia()){
            if(actual.getLugar() == Lugar){
                System.out.println("Datos del destino: "+ Lugar + " encontrados con éxito.");
                System.out.println("Destino: "+ actual.getLugar());
                System.out.println("Tiempo: "+ actual.getTiempo());
                cabeza = cabeza.getSiguiente();   
                actual.setSiguiente(null);
                return this;
            }else{
                while(actual.getLugar() != Lugar){
                    actual = actual.getSiguiente();
                }
                if(actual.getLugar() == Lugar){
                    System.out.println("Datos del destino: "+ Lugar + " encontrados con éxito.");
                System.out.println("Destino: "+ actual.getLugar());
                System.out.println("Tiempo: "+ actual.getTiempo());
                    if(actual.getSiguiente() == null){    
                        actual.setSiguiente(null);
                        ultimo = actual;
                    }else{
                        actual = actual.getSiguiente();
                        aux.setSiguiente(actual);
                    }
                }else{
                    System.out.println("Datos no encontrados del destino: "+ Lugar + " inexistente.");
                }
            }
        }else{
            System.out.println("Lista vacía, datos no encontrados.");
        }        
        return this;
    }
    
    public void GraficarLista(){
        NodoSimple auxiliar;
        auxiliar = cabeza;
        String contenido = "digraph ListaSimple {\n";
        if(!estaVacia()){
            while(auxiliar != null){
                contenido += auxiliar.getLugar() + "[shape = box, label = \" Destino:"+ auxiliar.getLugar() +"\"];\n";            
                if(auxiliar.getSiguiente() == null){            
                }else{
                    contenido += auxiliar.getLugar() +"->"+ auxiliar.getSiguiente().getLugar()+";\n";
                }
                if(auxiliar.getSiguiente() == null){            
                }else{
                    contenido += auxiliar.getSiguiente() + "->" + auxiliar.getSiguiente().getLugar() + ";\n";
                }                
                auxiliar = auxiliar.getSiguiente();
            }
            contenido += "}";
            try {
                String ruta = "C:\\Users\\Luis Rivera\\Desktop\\ListaDoble.dot";
                String ruta2 = "C:\\Users\\Luis Rivera\\Desktop\\ListaDoble.jpg\"";
                File file = new File(ruta);
                // Si el archivo no existe es creado
                if (!file.exists()) {
                   file.createNewFile();
                 }
                
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(contenido);
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
