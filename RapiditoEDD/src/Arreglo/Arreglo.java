/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arreglo;

/**
 *
 * @author Luis Rivera
 */
public class Arreglo {
    
    int tam = 1;
    Object [] arreglo = new Object [1];
    
    public void Agregar(Object o){
        if(arreglo[0]==null){
            arreglo[0] = o;
        }else{
            Object [] tmp = new Object [tam];
            System.arraycopy(arreglo, 0, tmp, 0, arreglo.length);
            
            tam++;
            
            arreglo = new Object [tam];
            System.arraycopy(tmp, 0, arreglo, 0, arreglo.length-1);
            arreglo[tam-1] = o;
            
        }
    }
    
    
    public int size(){
        return tam;
    }
    
    public Object[] ObtenerArreglo(){
        return arreglo;
    }
    
}
