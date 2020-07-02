/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rapiditoedd;

/**
 *
 * @author danis
 */
 
import Grafo.*;
import Listas.*;

public class RapiditoEDD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Grafo g = new Grafo();
        g.insertar("guate", "xela", 15);
        g.insertar("guate", "toto", 20);
        g.insertar("guate", "huehue", 25);
        g.insertar("xela", "toto", 5);
        g.insertar("toto", "huehue", 10);
        
       
        
        g.imprimirTODO();
        
        //System.out.println("----");
        
        
        
        
        
    }
    
}
