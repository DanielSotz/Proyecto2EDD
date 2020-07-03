/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigInteger;
import javax.swing.JOptionPane;

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
    
    public void InsertarNodo(String ID, String Nombre, String Apellido, String Tipo, String Genero,String fecha, String Telefono, String Direccion){
        
        Nodo actual;
        BigInteger bdpi = new BigInteger(ID);
        
        actual = cabeza;
        Nodo nuevo = new Nodo(bdpi, Nombre, Apellido, Tipo, Genero,fecha, Telefono, Direccion);
        
        if(estaVacia()){ 
            cabeza = nuevo;
            ultimo = nuevo;
            nuevo.setAnterior(nuevo);
            nuevo.setSiguiente(nuevo);
            return;
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
                return;
            }
        }        
        tamanio++;
        return;
    }   

    public Nodo BuscarNodo(String ID){
        Nodo actual;
        
        actual = cabeza;
        if(!estaVacia()){
            
            while(actual.getSiguiente()!= cabeza)
            {
                if(actual.getID().toString().equals(ID))
                {
                    System.out.println("1111111111111");
                    return actual;
                    
                }
                else
                {
                    System.out.println("2222222222");
                    System.out.println(actual.getID().toString());
                    actual = actual.getSiguiente();
                }
            }
            System.out.println("----");
            System.out.println(ID);
            
            System.out.println(actual.getID().toString());
            System.out.println("-------");
            
             if(actual.getID().toString().equals(ID))
             {
                 System.out.println("333333333333");
                 return actual;
             }
             else
             {
                 System.out.println("4444444444");
                 actual=null;
             }
            
        }else{
            System.out.println("Lista vacía, datos no encontrados.");
            actual=null;
        }
        System.out.println("555555555555555555");
        
        return actual;
    }
    
    public ListaCircular EliminarNodo(String ID){
        Nodo actual, aux;
        
        aux = null;
        actual = cabeza;
        BigInteger bdpi = new BigInteger(ID);
        
        if(!estaVacia()){
            if(actual.getID() == bdpi){
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
                while(actual.getID() != bdpi){
                    actual = actual.getSiguiente();
                    if(actual.getSiguiente() == cabeza && actual.getID()!= bdpi){
                        System.out.println("Datos no encontrados DPI: "+ ID + " inexistente.");
                        return this;
                    }
                }
                if(actual.getID() == bdpi){
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
    
    
    
    public void Modificar(String clave,String nclave, String nombre, String apellido,String tipo, String genero,String fecha, String telefono, String direccion)
    {
        Nodo aux = BuscarNodo(clave);
        
        if(aux != null)
        {
           BigInteger bdpi = new BigInteger(nclave);
           aux.DPI = bdpi;
           aux.Nombre = nombre;
           aux.Apellido = apellido;
           aux.tipoLicencia= tipo;
           aux.Fecha = fecha;
           aux.Genero = genero;
           aux.Telefono = telefono;
           aux.Direccion = direccion;
           JOptionPane.showMessageDialog(null, "Datos conductor actualizado");
        }
        else
            JOptionPane.showMessageDialog(null, "Conductor no encontrado");
        
        
    }
    
    public void GraficarLista(){
        Nodo auxiliar;
        auxiliar = cabeza;
        String contenido = "digraph ListaCircular {\n";
        if(!estaVacia()){
            while(auxiliar.getSiguiente() != cabeza){
                contenido += auxiliar.getID().toString() + "[shape = box, label = \" DPI:"+ auxiliar.getID().toString() +"\"];\n";            
                contenido += auxiliar.getID().toString() +"->"+ auxiliar.getAnterior().getID().toString()+";\n";
                contenido += auxiliar.getID().toString() + "->" + auxiliar.getSiguiente().getID().toString() + ";\n";
                auxiliar = auxiliar.getSiguiente();
            }
            contenido += auxiliar.getID().toString() + "[shape = box, label = \" DPI:"+ auxiliar.getID().toString() +"\"];\n";            
            contenido += auxiliar.getID().toString() +"->"+ auxiliar.getAnterior().getID().toString()+";\n";
            contenido += auxiliar.getID().toString() + "->" + cabeza.getID().toString() + ";\n";
            contenido += "}";
            try {
                String ruta = "ListaCircular.txt";                
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
            
            try {

            Runtime rt = Runtime.getRuntime();
            //rt.exec( cmd );
            Process p = rt.exec("dot -Tpng ListaCircular.txt -o ListaCircular.png");
            p.waitFor();
            
            //definiendo la ruta en la propiedad file
            Runtime.getRuntime().exec("cmd /c start ListaCircular.png");
            //rt.exec("hash_user.jpg");
            //Desktop.getDesktop().open(new File("hash_user.jpg"));

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex,"Error",JOptionPane.ERROR_MESSAGE);                
            } finally {}
            
            
            
        }
    }
}
