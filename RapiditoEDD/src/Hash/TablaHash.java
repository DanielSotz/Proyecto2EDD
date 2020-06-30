/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hash;

import java.math.BigInteger;
import javax.swing.JOptionPane;

/**
 *
 * @author danis
 */
public class TablaHash {
    
    NodoHash tabla[];
    int tamanio;
    int ocupado;
    public TablaHash(int tamanio)
    {
        this.tamanio = tamanio;           
        this.ocupado = 0;
        
        this.tabla = new NodoHash[tamanio];
        
        //dandole null a cada posicion de la tabla inicialmente
        for(int i =0;i<tamanio; i++)
        {
            tabla[i]=null;
        }
    }
    
    public int funcionHash(String clave)
    {
        BigInteger bdpi = new BigInteger(clave);
        bdpi= bdpi.mod(BigInteger.valueOf(this.tamanio));
        int resultado = bdpi.intValue();
        return resultado;
    }
    public void Limite_llenado(){
        ///verificando si ya se llego al 75% de ocupados
        
        int ocupado_75;
        ocupado_75 =  (int)((this.tamanio*75)/100);
        
        //JOptionPane.showMessageDialog(null, "ocupado: " +  ocupado + ". ocupado_75: " +  ocupado_75);
        if (ocupado_75 == ocupado){
            JOptionPane.showMessageDialog(null, "se llego al 75%");
            this.AumentandoHash(); //Se aumenta el tamaño
        }

    }
    
    public void AumentandoHash(){
        
        int tam_actual = this.tamanio;
        JOptionPane.showMessageDialog(null, "tam_actual: " +  tam_actual);
        tam_actual+=5;
        JOptionPane.showMessageDialog(null, "nuevo tamaño: " +  tam_actual);         
        NodoHash temp_hash_ante[];
        
        temp_hash_ante = this.tabla;
        NodoHash new_hash[] = new NodoHash[tam_actual];
        this.tabla = new_hash;
        this.tamanio = tam_actual;
        //JOptionPane.showMessageDialog(null, "tabla_hash.length: " +  tabla_hash.length);
        this.ocupado = 0; //inicializando valor de ocupados
        ///insertando a nuevo hash
        
        NodoHash aux = null;
        for (int i = 0; i < temp_hash_ante.length ;i++) {
                        
            if (temp_hash_ante[i] != null){
                //JOptionPane.showMessageDialog(null, "temp_hash_ante[i].usuario: " + temp_hash_ante[i].usuario);
                aux = temp_hash_ante[i];
                
                while(aux.siguiente != null )
                {                    
                    aux = aux.siguiente;
                    this.insertar(aux.dpi.toString(), aux.nombre);
                }
                
                this.insertar(temp_hash_ante[i].dpi.toString(), temp_hash_ante[i].nombre);               
            }  
        }        
    }
    
     public void insertar(String dpi, String nombre)
    {
        BigInteger bdpi = new BigInteger(dpi);
        int posicion = funcionHash(dpi);
        NodoHash nuevo;
        
        nuevo = new NodoHash(bdpi,nombre);
        nuevo.siguiente = this.tabla[posicion]; //creando la lista enlazada         
        this.tabla[posicion]= nuevo; //insertando al principio d    11 1e la lita 
        
        System.out.println("insertado en : " + posicion);
        this.ocupado++;
        
        this.Limite_llenado();  //verificando porcentaje llenado
        
    }
    
}
