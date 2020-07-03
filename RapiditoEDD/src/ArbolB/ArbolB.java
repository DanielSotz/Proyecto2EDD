/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbolB;

import Arreglo.Arreglo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis Rivera
 */

public class ArbolB {
    
    NodoArbolB Raiz;
    NodoArbolB auxDer = new NodoArbolB();
    NodoArbolB auxIzq = new NodoArbolB();
    vehiculo p;
    vehiculo Aux;
    NodoArbolB AuxR;
    String salida, imps, vehiculo;
    boolean bandera = false;
    int posicion;
    boolean flagEmpujarAdelante = false, flagExiste = false;
    
    Arreglo datos = new Arreglo();
    
    StringBuffer buffer;
    int val = 0;
    
   
    public ArbolB() {
        Raiz = new NodoArbolB();
    }
    
    public void Iniciar(){
        Raiz = new NodoArbolB();
    }
    
    public NodoArbolB obtenerRaiz(){
        return this.Raiz;
    } 
   
    //Inserta un nodo en un arbol b
    public void Insertar(vehiculo pLlave) {
        
        insertarRecursiva(pLlave, Raiz);
    }

    public void insertarRecursiva(vehiculo pLlave, NodoArbolB raiz) {
        Ordenar(pLlave, raiz);
        if (flagEmpujarAdelante) {
            Raiz = new NodoArbolB();
            Raiz.numKeys = 1;
            Raiz.llaves[0] = Aux;
            Raiz.ramas[0] = raiz;
            Raiz.ramas[1] = AuxR;
        }
        
        InsertarDato(pLlave);
        
    }
    
    //Ordena los elementos del arbol
    public void Ordenar(vehiculo pLlave, NodoArbolB raiz) {
        int k;
        flagExiste = false;
        if (Vacio(raiz)) {
            flagEmpujarAdelante = true;
            Aux = pLlave;
            AuxR = null;
        } else {
            k = BuscarNodo(pLlave, raiz);
            if (flagExiste) {
                flagEmpujarAdelante = false;
            } else {
                Ordenar(pLlave, raiz.ramas[k]);
                if (flagEmpujarAdelante) {
                    if (raiz.numKeys < 4) {
                        flagEmpujarAdelante = false;
                        MeterHoja(Aux, raiz, k);
                    } else {
                        flagEmpujarAdelante = true;
                        DividirN(Aux, raiz, k);
                    }
                }
            }
        }
    }
    
    //Método para meter hoja
    public void MeterHoja(vehiculo pLlave, NodoArbolB raiz, int k) {
        int i = raiz.numKeys;
        while (i != k) {
            raiz.llaves[i] = raiz.llaves[i - 1];
            raiz.ramas[i + 1] = raiz.ramas[i];
            --i;
        }
        raiz.llaves[k] = pLlave;
        raiz.ramas[k + 1] = AuxR;
        raiz.numKeys = ++raiz.numKeys;
    }
    
    //Método para dividir nodo
    public void DividirN(vehiculo pLlave, NodoArbolB Raiz, int k) {
        int pos;
        int Posmda;
        if (k <= 2) {
            Posmda = 2;
        } else {
            Posmda = 3;
        }
        NodoArbolB Mder = new NodoArbolB();
        pos = Posmda + 1;
        while (pos != 5) {
            Mder.llaves[(pos - Posmda) - 1] = Raiz.llaves[pos - 1];
            Mder.ramas[pos - Posmda] = Raiz.ramas[pos];
            ++pos;
        }
        Mder.numKeys = 4 - Posmda;
        Raiz.numKeys = Posmda;
        if (k <= 2) {
            MeterHoja(pLlave, Raiz, k);
        } else {
            MeterHoja(pLlave, Mder, (k - Posmda));
        }
        Aux = Raiz.llaves[Raiz.numKeys - 1];
        Mder.ramas[0] = Raiz.ramas[Raiz.numKeys];
        Raiz.numKeys = --Raiz.numKeys;
        AuxR = Mder;
    }
    
    //Retorna true si el arbol esta vacio
    public boolean Vacio(NodoArbolB raiz) {
        return (raiz == null || raiz.numKeys == 0);
    }
    
    //Buscar, retorna true si lo encuentra y la posicion
    public int BuscarNodo(vehiculo pLlave, NodoArbolB raiz) {
        int j;
        if (pLlave.getPlaca().compareTo(raiz.llaves[0].getPlaca()) < 0) {
          flagExiste = false;
          j = 0;
        } else {
            j = raiz.numKeys;
            while (pLlave.getPlaca().compareTo(raiz.llaves[j - 1].getPlaca()) > 0  && j > 1) {       
                --j;
            }
            flagExiste = (pLlave.getPlaca() == raiz.llaves[j - 1].getPlaca());
        }
        return j;
    }
    
    //BUSCA EL NUMERO Y NOMBRE DEL NODO SEGUN PLACA
    public void partida(String pPlaca){
        buscarPlaca(pPlaca, Raiz, true);
    }
    
    public void destino(String pPlaca){
        buscarPlaca(pPlaca, Raiz, false);
    }
    

    //Método para visualizar(Ordenar)  -- BUSCAR un nodo por codigo
    public void buscarPlaca(String pPlaca, NodoArbolB raiz, boolean bandera){
        int k;
        flagExiste = false;
        if (!Vacio(raiz)) {
            k = visualizarRecNodo(pPlaca, raiz);
            if (flagExiste) {
                if(!bandera){
                    vehiculo = raiz.llaves[k-1].getPlaca();
                }else{
                    vehiculo = raiz.llaves[k-1].getPlaca();
                }
            } else {
                
                if(!bandera){
                    buscarPlaca(pPlaca, raiz.ramas[k], true);
                }else{
                    buscarPlaca(pPlaca, raiz.ramas[k], false);
                }
                
            }
        }            
    }
    
    public String devolverVehiculo(){
       return vehiculo;
    }
    
    public void visualizar(String pPlaca){
        visualizarNodo(pPlaca,Raiz);
    }
    
  
    public void InsertarDato(vehiculo p){
        //datos.add(p);
        datos.Agregar(p);
    }
    
    public String BuscarMarca(String placa){
        
        String nombre = "";
        for(int i=0; i<datos.size();i++){
            /*if(datos.get(i).getCodigo() == codigo){
                nombre = datos.get(i).getNombre();
            }*/
            vehiculo buscar = (vehiculo)datos.ObtenerArreglo()[i];
            if(buscar.getPlaca()==placa){
                nombre = buscar.getMarca();
            }
            
        }
        
        return nombre;
    }
    
    //public String bscPos(int pos){
      public vehiculo bscPos(String pos){  
        vehiculo buscado = null;
        //String nombre = "";
        for(int i=0; i<datos.size();i++){
            /*if(datos.get(i).getCodigo() == codigo){
                nombre = datos.get(i).getNombre();
            }*/
            vehiculo buscar = (vehiculo)datos.ObtenerArreglo()[i];
            if(buscar.getPlaca()==pos){
                buscado = buscar;
                //nombre = buscar.getNombre();
            }
            
        }
        
        return buscado;
        //return nombre;
    }
    
    
    //Método para visualizar(Ordenar)  -- BUSCAR un nodo por carnet
    public void visualizarNodo(String pPlacas, NodoArbolB raiz){
        int k;
        flagExiste = false;
        if (!Vacio(raiz)) {
            k = visualizarRecNodo(pPlacas, raiz);
            if (flagExiste) {
                System.out.println("Placa: "+raiz.llaves[k-1].getPlaca());
                System.out.println("Marca: "+raiz.llaves[k-1].getMarca());
                System.out.println("Modelo: "+raiz.llaves[k-1].getModelo());
                System.out.println("Año: "+raiz.llaves[k-1].getAnio());
                System.out.println("Color: "+raiz.llaves[k-1].getColor());
                System.out.println("Precio: "+raiz.llaves[k-1].getPrecio());                
                System.out.println("Tipo de Transmisión: "+raiz.llaves[k-1].getTransmision());                
            } else {
                visualizarNodo(pPlacas, raiz.ramas[k]);
            }
        }            
    }
    //Buscar retorna true si lo encuentra y la posicion(Buscar)
    public int visualizarRecNodo(String pPlacas, NodoArbolB raiz) {                            
        int j;
        if (pPlacas.compareTo(raiz.llaves[0].getPlaca()) < 0) {
          flagExiste = false;
          j = 0;
        } else {
            j = raiz.numKeys;
            while (pPlacas.compareTo(raiz.llaves[j - 1].getPlaca()) < 0 && j > 1) {       
                --j;
            }
            flagExiste = (pPlacas == raiz.llaves[j - 1].getPlaca());
        }
        return j;
    }
    
    //////////////
    void Eliminar(vehiculo pLlave) {
        if (Vacio(Raiz)) {
           //No hay elementos
        } else {
            eliminarRecursiva(Raiz, pLlave);
        }
    }

    public void eliminarRecursiva(NodoArbolB raiz, vehiculo pLlave) {
        try {
            EliminarRegistro(raiz, pLlave);
        } catch (Exception e) {
            flagExiste = false;
        }
        if (!flagExiste) {
            //No se encontro el elemento
        } else {
            if (raiz.numKeys == 0) {
                raiz = raiz.ramas[0];
            }
            Raiz = raiz;
        }
    }
    
    //Elimina el registro
    public void EliminarRegistro(NodoArbolB raiz, vehiculo pLlave) {
        int pos;
        if (Vacio(raiz)) {
            flagExiste = false;
        } else {
            pos = BuscarNodo(pLlave, raiz);
            if (flagExiste) {
                if (Vacio(raiz.ramas[pos - 1])) {
                    Quitar(raiz, pos);
                } else {
                    Sucesor(raiz, pos);
                    EliminarRegistro(raiz.ramas[pos], raiz.llaves[pos - 1]);
                }
            } else {
                EliminarRegistro(raiz.ramas[pos], pLlave);
                if ((raiz.ramas[pos] != null) && (raiz.ramas[pos].numKeys < 2)) {
                    Restablecer(raiz, pos);
                }
            }
        }
    }

    //Busca el sucesor
    public void Sucesor(NodoArbolB raiz, int k) {
        NodoArbolB q = raiz.ramas[k];
        while (!Vacio(q.ramas[0])) {
            q = q.ramas[0];
        }
        raiz.llaves[k - 1] = q.llaves[0];
    }

    //Combina para formar un nodo
    public void Combina(NodoArbolB raiz, int pos) {
        int j;
        auxDer = raiz.ramas[pos];
        auxIzq = raiz.ramas[pos - 1];
        auxIzq.numKeys++;
        auxIzq.llaves[auxIzq.numKeys - 1] = raiz.llaves[pos - 1];
        auxIzq.ramas[auxIzq.numKeys] = auxDer.ramas[0];
        j = 1;
        while (j != auxDer.numKeys + 1) {
            auxIzq.numKeys++;
            auxIzq.llaves[auxIzq.numKeys - 1] = auxDer.llaves[j - 1];
            auxIzq.ramas[auxIzq.numKeys] = auxDer.ramas[j];
            j++;
        }
        Quitar(raiz, pos);
    }

    //Mueve a la derecha
    public void MoverDer(NodoArbolB raiz, int pos) {
        int i = raiz.ramas[pos].numKeys;
        while (i != 0) {
            raiz.ramas[pos].llaves[i] = raiz.ramas[pos].llaves[i - 1];
            raiz.ramas[pos].ramas[i + 1] = raiz.ramas[pos].ramas[i];
            --i;
        }
        raiz.ramas[pos].numKeys++;
        raiz.ramas[pos].ramas[1] = raiz.ramas[pos].ramas[0];
        raiz.ramas[pos].llaves[0] = raiz.llaves[pos - 1];
        raiz.llaves[pos - 1] = raiz.ramas[pos - 1].llaves[raiz.ramas[pos - 1].numKeys - 1];
        raiz.ramas[pos].ramas[0] = raiz.ramas[pos - 1].ramas[raiz.ramas[pos - 1].numKeys];
        raiz.ramas[pos - 1].numKeys--;
    }

    //Mover a la izquierda
    public void MoverIzq(NodoArbolB raiz, int pos) {
        int i;
        raiz.ramas[pos - 1].numKeys++;
        raiz.ramas[pos - 1].llaves[raiz.ramas[pos - 1].numKeys - 1] = raiz.llaves[pos - 1];
        raiz.ramas[pos - 1].ramas[raiz.ramas[pos - 1].numKeys] = raiz.ramas[pos].ramas[0];
        raiz.llaves[pos - 1] = raiz.ramas[pos].llaves[0];
        raiz.ramas[pos].ramas[0] = raiz.ramas[pos].ramas[1];
        raiz.ramas[pos].numKeys--;
        i = 1;
        while (i != raiz.ramas[pos].numKeys + 1) {
            raiz.ramas[pos].llaves[i - 1] = raiz.ramas[pos].llaves[i];
            raiz.ramas[pos].ramas[i] = raiz.ramas[pos].ramas[i + 1];
            i++;
        }
    }

    //Quita el elemento
    public void Quitar(NodoArbolB raiz, int pos) {
        int j = pos + 1;
        while (j != raiz.numKeys + 1) {
            raiz.llaves[j - 2] = raiz.llaves[j - 1];
            raiz.ramas[j - 1] = raiz.ramas[j];
            j++;
        }
        raiz.numKeys--;
    }
    
    //Restablece el nodo
    public void Restablecer(NodoArbolB raiz, int pos) {
        if (pos > 0) {
            if (raiz.ramas[pos - 1].numKeys > 2) {
                MoverDer(raiz, pos);
            } else {
                Combina(raiz, pos);
            }
        } else if (raiz.ramas[1].numKeys > 2) {
            MoverIzq(raiz, 1);
        } else {
            Combina(raiz, 1);
        }
    }
    
    /////////////////////
    public void GeneradorDot() {
        String ruta_dot = "arbolB.dot";        
        //String ruta_dot =  "arbolB.dot";
        buffer = new StringBuffer();
        buffer.append("\ndigraph G {\r\n node [shape=record];\n");

        graficar();

        buffer.append("}");
        this.creararchivo(ruta_dot, buffer.toString());
        
        try {
            
            
            Runtime rt = Runtime.getRuntime();
           
            Process p = rt.exec("dot -Tpng arbolB.dot -o arbolB.png");
            p.waitFor();
            
            //definiendo la ruta en la propiedad file
            Runtime.getRuntime().exec("cmd /c start arbolB.png");
            
            

        } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex,"Error",JOptionPane.ERROR_MESSAGE);                
            } finally {}
    }
    
    public void graficar() {
        NodoArbolB nodo = Raiz;
        int k = 0;
        int c = 0;

        buffer.append("Nodo" + val + "[label=\"<P0>");
        while (nodo.llaves[c] != null) {
            buffer.append("|" + "Placas: "+(nodo.llaves[c].getPlaca()));
            buffer.append("|<P" + (c + 1) + ">");
            c++;
            if (c >= 4) {
                break;
            }
        }
        buffer.append("\"];\n");
        String pasa = "Nodo" + val;
        while (nodo.ramas[k] != null && nodo.numKeys >= k) {
            if (nodo.ramas[k].numKeys != 0) {
                val++;
                buffer.append(pasa + ":P" + k + " -> " + "Nodo" + val + ";\n");
                recursivo(nodo.ramas[k]);
                k++;
                if (k >= 5) {
                    break;
                }
            } else {
                break;
            }
        }
    }
    
    void recursivo(NodoArbolB nodo) {
        int k = 0;
        int c = 0;
        buffer.append("Nodo" + val + "[label=\"<P0>");
        while (nodo.llaves[c] != null) {
            if (c != nodo.numKeys && nodo.numKeys != 0) {
                buffer.append("|" + "Codigo: "+(nodo.llaves[c].getPlaca()));
                buffer.append("|<P" + (c + 1) + ">");
                c++;
                if (c >= 4) {
                    break;
                }
            } else {
                break;
            }
        }
        buffer.append("\"];\n");

        String pasa = "Nodo" + val;
        while (nodo.ramas[k] != null && nodo.numKeys >= k) {
            if (nodo.ramas[k].numKeys != 0) {
                val++;
                buffer.append(pasa + ":P" + k + " -> " + "Nodo" + val + ";\n");
                recursivo(nodo.ramas[k]);
                k++;
                if (k >= 5) {
                    break;
                }
            } else {
                break;
            }
        }
    }
    
    public synchronized void creararchivo(String pfichero, String pcontenido) {
        FileWriter archivo = null;

        try {
            archivo = new FileWriter(pfichero);
        } catch (IOException ex) {
            Logger.getLogger(ArbolB.class.getName()).log(Level.SEVERE, null, ex);
        }
        File a = new File(pfichero);
        if (!a.exists()) {
            return;
        }
        try (PrintWriter printwriter = new PrintWriter(archivo)) {
            printwriter.print(pcontenido);
            printwriter.close();
        }
    }
    
    
}