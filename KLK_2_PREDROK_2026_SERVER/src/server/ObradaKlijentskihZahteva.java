/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.Controller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import javax.swing.JButton;
import javax.swing.text.DateFormatter;
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
          
              out = new ObjectOutputStream(s.getOutputStream());
                in = new ObjectInputStream(s.getInputStream());
              
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
                //so.setOperacija(kz.getOperacija()); 
                
                String komanda = kz.getParametar().toString();  // ADD 5 2 ili || DATE || MINMAX
                String podaci[] = komanda.split(" ");  // ["ADD","5","2"]
                
                
                
                switch (podaci[0]) {
                    
                    case "MINMAX":
                      int min = Integer.MAX_VALUE;
                      int max = Integer.MIN_VALUE;
                        
                        for (int i = 1; i < podaci.length; i++) {
                            int broj = Integer.parseInt(podaci[i]);
                            if (broj > max) {
                                max = broj;
                            }
                            if(broj < min){
                            min = broj;
                            }
                        }
                        String minS = String.valueOf(min);
                        String maxS = String.valueOf(max);
                        
                        String odg2 = "MIN: "+minS+" MAX: "+maxS;
                        so.setOdgovor(odg2); 
                        
                        break;
                    case "DATE":
                        LocalDate datum = LocalDate.now();
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.YYY");
                        String odg1 = "CURRENT DATE: " + datum.format(dtf);
                        so.setOdgovor(odg1);
                        
                        break;
                    case "ADD":
                        
                        try{
                         int odg = Integer.parseInt(podaci[1]) + Integer.parseInt(podaci[2]);
                          String odgovor2 = "RESULT: " + String.valueOf(odg);
                            so.setOdgovor(odgovor2); 
                        }
                        catch(NumberFormatException ex){
                        so.setOdgovor("ERROR: Pogresan format");
                        }
                        break;
                    
                    case "LOGIN":
                        String user = podaci[1];
                        String odgovor = Controller.getInstance().Login(user);
                        if (odgovor.startsWith("SUCC")) {
                            ulogovaniUser = user;
                             s.setSoTimeout(0);
                        }
                        so.setOdgovor(odgovor); 
                        break;
                    
//                    case "LOGOUT":
//                       Controller.getInstance().Logout(ulogovaniUser);
//                       so.setOdgovor("LOGOUT: Korisnik se izlogova");
//                        
//                        ugasiKlijenta();
//                        return;   
                        
                    default:
                        System.out.println("Greska...");
                }
                
                    out.writeObject(so);
                
                
                
                
              
                
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
            ex.printStackTrace();
        }
       
    }
    
    




    
}




