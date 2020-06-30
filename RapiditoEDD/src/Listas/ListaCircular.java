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
public class ListaCircular {
    
    private Nodo cabeza;
    private Nodo ultimo;
    private int tamanio;
    
    public ListaCircular(){
        this.cabeza = null;
        this.ultimo = null;
    }
        
    public boolean estaVacia(){
        if(cabeza == null) return true;
        else return false;
    }
    
    public int getTamanio(){
        return tamanio;
    }
    
    public ListaCircular InsertarNodo(int ID, String Nombre, String Apellido, String Tipo, String Genero, String Telefono, String Direccion){
        
        Nodo actual;
        
        actual = cabeza;
        Nodo nuevo = new Nodo(ID, Nombre, Apellido, Tipo, Genero, Telefono, Direccion);
        
        if(estaVacia()){ 
            cabeza = nuevo;
            ultimo = nuevo;
            nuevo.setAnterior(nuevo);
            nuevo.setSiguiente(nuevo);
            return this;
        }else if(!estaVacia()){
            while(actual.getSiguiente() != cabeza){
                actual = actual.getSiguiente();
            }
            if(actual.getSiguiente() == cabeza){
                actual.setSiguiente(nuevo);
                cabeza.setAnterior(nuevo);
                nuevo.setSiguiente(cabeza);
                nuevo.setAnterior(actual);
                ultimo = nuevo;
                return this;
            }
        }        
        tamanio++;
        return this;
    }   

    public ListaCircular BuscarNodo(int ID){
        Nodo actual;
        
        actual = cabeza;
        if(!estaVacia()){
            if(actual.getID() == ID){
                System.out.println("Datos del DPI: "+ ID + " encontrados con éxito.");
                System.out.println("DPI: "+ID);
                System.out.println("Nombre: "+ actual.getNombre());
                System.out.println("Apellido: "+ actual.getApellido());
                return this;
            }else{
                while(actual.getID() != ID){
                    actual = actual.getSiguiente();
                    if(actual.getSiguiente() == cabeza && actual.getID()!= ID){
                        System.out.println("Datos no encontrados DPI: "+ ID + " inexistente.");
                        return this;
                    }
                }
                if(actual.getID() == ID){
                    System.out.println("Datos del DPI: "+ ID + " encontrados con éxito.");
                    System.out.println("DPI: "+ID);
                    System.out.println("Nombre: "+ actual.getNombre());
                    System.out.println("Apellido: "+ actual.getApellido());
                    return this;    
                }
            }
        }else{
            System.out.println("Lista vacía, datos no encontrados.");
        }        
        return this;
    }
    
    public ListaCircular EliminarNodo(int ID){
        Nodo actual, aux;
        
        aux = null;
        actual = cabeza;
        
        if(!estaVacia()){
            if(actual.getID() == ID){
                System.out.println("Datos del DPI: "+ ID + " eliminados con éxito.");
                System.out.println("DPI: "+ID);
                System.out.println("Nombre: "+ actual.getNombre());
                System.out.println("Apellido: "+ actual.getApellido());
                if(tamanio == 1){
                    cabeza = null;
                    ultimo = null;
                    return this;
                }else{
                    cabeza = cabeza.getSiguiente();
                    cabeza.setAnterior(ultimo);
                    ultimo.setSiguiente(cabeza);
                    return this;
                }
            }else{
                while(actual.getID() != ID){
                    actual = actual.getSiguiente();
                    if(actual.getSiguiente() == cabeza && actual.getID()!= ID){
                        System.out.println("Datos no encontrados DPI: "+ ID + " inexistente.");
                        return this;
                    }
                }
                if(actual.getID() == ID){
                    System.out.println("Datos del DPI: "+ ID + " eliminados con éxito.");
                    System.out.println("DPI: "+ID);
                    System.out.println("Nombre: "+ actual.getNombre());
                    System.out.println("Apellido: "+ actual.getApellido());
                    if(actual.getSiguiente() == cabeza){ 
                        actual = actual.getAnterior();
                        actual.setSiguiente(cabeza);
                        cabeza.setAnterior(actual);
                        ultimo = actual;
                    }else{
                        aux = actual.getAnterior();
                        actual = actual.getSiguiente();
                        aux.setSiguiente(actual);
                        actual.setAnterior(aux);
                    }
                }
            }
        }else{
            System.out.println("Lista vacía, datos no encontrados.");
        }        
        return this;
    }
    
    public void GraficarLista(){
        Nodo auxiliar;
        auxiliar = cabeza;
        String contenido = "digraph ListaCircular {\n";
        if(!estaVacia()){
            while(auxiliar.getSiguiente() != cabeza){
                contenido += auxiliar.getID() + "[shape = box, label = \" DPI:"+ auxiliar.getID() +"\"];\n";            
                contenido += auxiliar.getID() +"->"+ auxiliar.getAnterior().getID()+";\n";
                contenido += auxiliar.getID() + "->" + auxiliar.getSiguiente().getID() + ";\n";
                auxiliar = auxiliar.getSiguiente();
            }
            contenido += auxiliar.getID() + "[shape = box, label = \" DPI:"+ auxiliar.getID() +"\"];\n";            
            contenido += auxiliar.getID() +"->"+ auxiliar.getAnterior().getID()+";\n";
            contenido += auxiliar.getID() + "->" + cabeza.getID() + ";\n";
            contenido += "}";
            try {
                String ruta = "C:\\Users\\Luis Rivera\\Desktop\\ListaCircular.dot";
                String ruta2 = "C:\\Users\\Luis Rivera\\Desktop\\ListaCircular.jpg\"";
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
