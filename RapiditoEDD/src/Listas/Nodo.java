/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listas;

import java.math.BigInteger;

/**
 *
 * @author Luis Rivera
 */
public class Nodo {
    
    public BigInteger DPI;
    public String Nombre, Apellido, tipoLicencia, Genero,Fecha, Telefono, Direccion;      
    private Nodo Siguiente, Anterior;
    
    public Nodo(BigInteger ID, String Nombre, String Apellido, String tipo, String genero,String fecha, String phone, String direction){
    
        this.DPI = ID;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.tipoLicencia = tipo;
        this.Genero = genero;
        this.Fecha = fecha;
        this.Telefono = phone;
        this.Direccion = direction;
        this.Siguiente = null;
        this.Anterior = null;
    }

    /**
     * @return the ID
     */
    public BigInteger getID() {
        return DPI;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(BigInteger ID) {
        this.DPI = ID;
    }

    /**
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the Apellido
     */
    public String getApellido() {
        return Apellido;
    }

    /**
     * @param Apellido the Apellido to set
     */
    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    /**
     * @return the Siguiente
     */
    public Nodo getSiguiente() {
        return Siguiente;
    }

    /**
     * @param Siguiente the Siguiente to set
     */
    public void setSiguiente(Nodo Siguiente) {
        this.Siguiente = Siguiente;
    }

    /**
     * @return the Anterior
     */
    public Nodo getAnterior() {
        return Anterior;
    }

    /**
     * @param Anterior the Anterior to set
     */
    public void setAnterior(Nodo Anterior) {
        this.Anterior = Anterior;
    }

    /**
     * @return the tipoLicencia
     */
    public String getTipoLicencia() {
        return tipoLicencia;
    }

    /**
     * @param tipoLicencia the tipoLicencia to set
     */
    public void setTipoLicencia(String tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    /**
     * @return the Genero
     */
    public String getGenero() {
        return Genero;
    }

    /**
     * @param Genero the Genero to set
     */
    public void setGenero(String Genero) {
        this.Genero = Genero;
    }
    
     /**
     * @return the Fecha
     */
    public String getFecha() {
        return Fecha;
    }

    /**
     * @param Fecha the Fecha to set
     */
    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }
    

    /**
     * @return the Telefono
     */
    public String getTelefono() {
        return Telefono;
    }

    /**
     * @param Telefono the Telefono to set
     */
    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    /**
     * @return the Direccion
     */
    public String getDireccion() {
        return Direccion;
    }

    /**
     * @param Direccion the Direccion to set
     */
    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    
}
