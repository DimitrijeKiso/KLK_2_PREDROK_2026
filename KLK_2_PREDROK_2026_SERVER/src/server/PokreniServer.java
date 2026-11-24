/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Dimitrije
 */
public class PokreniServer extends Thread {

    private ServerSocket ss;
    
    @Override
    public void run() {
        
        
             
            try {
                ss = new ServerSocket(9000);
                System.out.println("Pokrenut server");
                while (true) {                    
                    Socket s = ss.accept();
                    System.out.println("Povezan Klijent");
                }
                
            } catch (IOException ex) {
                System.getLogger(PokreniServer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        
        
       
        
        
    }
    
    
    
    
    
}
