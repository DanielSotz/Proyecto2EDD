/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hash;

import java.math.BigInteger;

/**
 *
 * @author danis
 */
public class NodoHash {
    public BigInteger dpi;
    String nombre;
    String apellido;
    String genero;
    String fecha;
    String telefono;
    String direccion;
    NodoHash siguiente;
    
    
     public NodoHash(BigInteger dpi, String nombre, String apellido, String genero,String fecha, String telefono, String direccion) {
        this.dpi = dpi;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.fecha = fecha;
        this.telefono = telefono;
        this.direccion = direccion;
        this.siguiente = null;
    }

    public NodoHash(BigInteger dpi, String nombre) {
        this.dpi = dpi;
        this.nombre = nombre;
        this.siguiente = null;
    }
    
    
}
