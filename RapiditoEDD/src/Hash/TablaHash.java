/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hash;

import java.io.File;
import java.io.FileWriter;
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
                    this.insertar(aux.dpi.toString(), aux.nombre, aux.apellido,aux.genero,aux.fecha,aux.telefono,aux.direccion);
                }
                
                this.insertar(temp_hash_ante[i].dpi.toString(), temp_hash_ante[i].nombre,temp_hash_ante[i].apellido,temp_hash_ante[i].genero,temp_hash_ante[i].fecha,temp_hash_ante[i].telefono,temp_hash_ante[i].direccion);               
            }  
        }        
    }
    
     public void insertar(String dpi, String nombre, String apellido, String genero,String fecha, String telefono, String direccion)
    {
        BigInteger bdpi = new BigInteger(dpi);
        int posicion = funcionHash(dpi);
        NodoHash nuevo;
        
        nuevo = new NodoHash(bdpi,nombre,apellido,genero,fecha,telefono,direccion);
        nuevo.siguiente = this.tabla[posicion]; //creando la lista enlazada         
        this.tabla[posicion]= nuevo; //insertando al principio d    11 1e la lita 
        
        System.out.println("insertado en : " + posicion);
        this.ocupado++;
        
        this.Limite_llenado();  //verificando porcentaje llenado
        
    }
     
     public NodoHash buscar(String clave)
    {        
        int posicion = funcionHash(clave);
        
       NodoHash aux = null;
       
       
       if(this.tabla[posicion]!= null)
       {
            aux = this.tabla[posicion];
            while( (aux.siguiente != null) && (!aux.dpi.toString().equals(clave)) )
                aux = aux.siguiente;
            
            if( !aux.dpi.toString().equals(clave))
                aux = null;
       }       
       return aux;
    }
     
     
     public void modificar(String clave,String nclave, String nombre, String apellido, String genero,String fecha, String telefono, String direccion)
     {
         
         int posicion = funcionHash(clave);
         NodoHash aux = null;
       
       
       if(this.tabla[posicion]!= null)
       {
            aux = this.tabla[posicion];
            while( (aux.siguiente != null) && (!aux.dpi.toString().equals(clave)) )
                aux = aux.siguiente;
            
            if( !aux.dpi.toString().equals(clave))
                aux = null;
       }
       
       if(aux != null)
       {
           BigInteger bdpi = new BigInteger(nclave);
           aux.dpi = bdpi;
           aux.nombre = nombre;
           aux.apellido = apellido;
           aux.fecha = fecha;
           aux.genero = genero;
           aux.telefono = telefono;
           aux.direccion = direccion;
           JOptionPane.showMessageDialog(null, "Datos cliente actualizado");
           
       }
       else
           JOptionPane.showMessageDialog(null, "No se encontro el cliente");

       
       
         
     }
     
     public void eliminar(String clave)
    {
        int posicion = funcionHash(clave);
        if(this.tabla[posicion] != null)
        {
            NodoHash anterior= null;
            NodoHash actual= tabla[posicion];
            
            while( (actual.siguiente != null) && (!actual.dpi.toString().equals(clave)) )
            {
                anterior= actual;
                actual = actual.siguiente;
            }
            
            if( !actual.dpi.toString().equals(clave) )
            {
                JOptionPane.showMessageDialog(null, "No se encuentra elemento en la tabla");
                return;
            }
            else if(anterior == null)
                this.tabla[posicion] = actual.siguiente;
            else 
                anterior.siguiente = actual.siguiente;
            
            JOptionPane.showMessageDialog(null, "Elemento eliminado");
            actual=null;
        }
        else
            JOptionPane.showMessageDialog(null, "No se encuentra elemento en la tabla");            
        
    }
     
     
     public boolean graficando_users(){
        StringBuilder graf  = new StringBuilder(); //grafica total
        StringBuilder index  = new StringBuilder(); // enlaces del vector hash principal
        StringBuilder rank  = new StringBuilder(); // para alinear el vector hash
        StringBuilder datos  = new StringBuilder(); // para los datos de la tabla
        StringBuilder enlace  = new StringBuilder(); // para enlaces de los datos
                       
        graf.append("digraph G { rankdir=LR\n");
        graf.append("node [shape=record];\n");
                
        
        rank.append("{rank=same "); 
         
        for (int i = 0; i < tabla.length ;i++) {
            graf.append("nd_"+i+" [label = \" \" ];\n"); //dibujando el vector hash
            if(i < (tabla.length -1) )
            {
                index.append("nd_"+i+" ->");
            }
            else
            {
                index.append("nd_"+i+" [color=grey arrowhead=none];\n");
            }
            
            rank.append("nd_"+i +" ");
        }
        
        
        for (int i = 0; i < tabla.length ;i++) {
            
            NodoHash aux = null;
            
            if(this.tabla[i]!= null)
            {
                aux = this.tabla[i];
                datos.append("n"+i+" [label = \" "+ aux.dpi.toString() +"-"+aux.nombre+ "\" ];\n");
                
                enlace.append("nd_"+i+" -> " + "n"+i+";\n");
                int contador=0;
                while( (aux.siguiente != null)  )
                {                    
                    datos.append("ns"+i+contador+" [label = \" "+ aux.siguiente.dpi.toString() +"-"+aux.nombre+ "\"];\n");
                    if(contador ==0)
                    {
                        enlace.append("n"+i+" -> " + "ns"+i+contador+";\n");
                    }
                    else
                    {
                        enlace.append("ns"+i+(contador-1)+" -> " + "ns"+i+contador+";\n");
                    }
                    contador++;
                    
                    aux = aux.siguiente;
                }             
            }   
       
        }
        
        rank.append("}\n"); 
        
        graf.append(index);
        graf.append(datos);
        graf.append(enlace);
        graf.append(rank);
        
        
        graf.append("}");

        //JOptionPane.showMessageDialog(null,graf);
        return this.graf_users(graf.toString());
    }
    
    public boolean graf_users(String grafica){
        //File archivo =new File("hash_user.txt");
        try
            {
            File archivo =new File("hash_user.txt");
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
            Process p = rt.exec("dot -Tpng hash_user.txt -o hash_user.png");
            p.waitFor();
            
            //definiendo la ruta en la propiedad file
            Runtime.getRuntime().exec("cmd /c start hash_user.png");
            //rt.exec("hash_user.jpg");
            //Desktop.getDesktop().open(new File("hash_user.jpg"));

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex,"Erro",JOptionPane.ERROR_MESSAGE);
                return false;
            } finally {}
        
        return true;
        
    }
   
    
}
