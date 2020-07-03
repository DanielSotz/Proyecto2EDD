/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbolB;

/**
 *
 * @author Luis Rivera
 */

public class NodoArbolB {
 
    public vehiculo []llaves;
    public NodoArbolB []ramas;
    public int numKeys;
    
    public NodoArbolB(){
       llaves = new vehiculo[4];
       ramas = new NodoArbolB[5];
       numKeys = 0;
    }
    
    
}
    