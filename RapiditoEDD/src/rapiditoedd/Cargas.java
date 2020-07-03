/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rapiditoedd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Grafo.Grafo;
import Hash.TablaHash;

/**
 *
 * @author danis
 */
public class Cargas {
    public JFileChooser fileChooser;
    JFileChooser dialog = new JFileChooser();
    Grafo graf;
    TablaHash thash;
    
    public Cargas(){
   
    }
    
    public Grafo cargaGrafo() {
        int contador = 1;
        
        graf = new Grafo();
        
        dialog.setDialogTitle("Cargar archivo de clientes");
        if(dialog.showOpenDialog(null)== JFileChooser.APPROVE_OPTION)
        {
            File archivo = dialog.getSelectedFile();
            try
            {
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                String linea;                
                
                try
                {                   
                    
                    
                    while ((linea = br.readLine()) != null) {
                        String[] parametros = linea.split("/");
                        
                        String numeros=parametros[2];
                        String nnumero= numeros.replaceAll("%", "");
                        
                        int n= Integer.parseInt(nnumero);
                        
                        
                        
                        
                        graf.insertar(parametros[0], parametros[1],n);
                        contador++;
                        
                    }
                    
                    
                    
                    
                    
                    
                    
                }
                catch (IOException ioe) {
                    // xdxdxd
                }
                 br.close();
            }catch (FileNotFoundException ex) {
                //Logger.getLogger(Cargas.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                //Logger.getLogger(Cargas.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception e){
                //System.out.println("Puede que el archivo de entrada contenga lineas en blanco");
                JOptionPane.showMessageDialog(null, "A ocurrido un error", "Alerta", JOptionPane.WARNING_MESSAGE);
            }            
            
        }
        return graf;
    }
    
    
    public TablaHash cargaHash() {
        int contador = 1;
        
        thash = new TablaHash(37);
        
        dialog.setDialogTitle("Cargar archivo de clientes");
        if(dialog.showOpenDialog(null)== JFileChooser.APPROVE_OPTION)
        {
            File archivo = dialog.getSelectedFile();
            try
            {
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                String linea;                
                
                try
                {                   
                    
                    
                    while ((linea = br.readLine()) != null) {
                        String[] parametros = linea.split(",");
                        
                        String numeros=parametros[6];
                        String nnumero= numeros.replaceAll(";", "");
                        
                      
                        thash.insertar(parametros[0],parametros[1],parametros[2],parametros[3],parametros[4],parametros[5],nnumero);
                        
                        
                        contador++;
                        
                    }
                }
                catch (IOException ioe) {
                    // xdxdxd
                }
                 br.close();
            }catch (FileNotFoundException ex) {
                //Logger.getLogger(Cargas.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                //Logger.getLogger(Cargas.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception e){
                //System.out.println("Puede que el archivo de entrada contenga lineas en blanco");
                JOptionPane.showMessageDialog(null, "A ocurrido un error", "Alerta", JOptionPane.WARNING_MESSAGE);
            }            
            
        }
        return thash;
    }
}
