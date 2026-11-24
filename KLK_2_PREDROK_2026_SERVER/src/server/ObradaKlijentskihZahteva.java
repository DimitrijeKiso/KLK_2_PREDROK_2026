/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import transfer.KorisnickiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Dimitrije
 */
public class ObradaKlijentskihZahteva extends Thread {

    private Socket s;
    private String ulogovaniUser = null;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    
    public ObradaKlijentskihZahteva() {
    }

    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        
        try {
            in = new ObjectInputStream(s.getInputStream());
              out = new ObjectOutputStream(s.getOutputStream());
              
           s.setSoTimeout(10000);
            while (true) {
                KorisnickiZahtev kz = new KorisnickiZahtev();
                try {
                    
                    try {
                         kz = (KorisnickiZahtev) in.readObject();
                    } catch (ClassNotFoundException ex) {
                        System.getLogger(ObradaKlijentskihZahteva.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    }
                    
                } catch (SocketTimeoutException ex) {
                    System.out.println("Proslo 10 sekundi");
                    ugasiKlijenta();
                    return;
                }
                
                ServerskiOdgovor so = new ServerskiOdgovor();
                so.setOperacija(kz.getOperacija()); 
                
                switch (kz.getOperacija()) {
                    case operacije.Operacije.LOGIN:
                        String user = (String) kz.getParametar();
                        s.setSoTimeout(0);
                        
                    default:
                        System.out.println("Greska...");
                }
                
                
                
                
            }
            
            
            
            
              
          
        } catch (IOException ex) {
            System.getLogger(ObradaKlijentskihZahteva.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
      
        
        
        
    }

    private void ugasiKlijenta() {
        try {
            in.close();
            out.close();
        s.close();
       
        } catch (IOException ex) {
            System.getLogger(ObradaKlijentskihZahteva.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
       
    }
    
    




    
}




