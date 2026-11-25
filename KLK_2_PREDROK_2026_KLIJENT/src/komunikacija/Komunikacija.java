/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.KorisnickiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Dimitrije
 */
public class Komunikacija {
    private static Komunikacija instance;
    private Socket s;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    
    
    
    
    private Komunikacija() {
        try {
            s = new Socket("localhost", 9000);
            
            out = new ObjectOutputStream(s.getOutputStream());
            in = new ObjectInputStream(s.getInputStream());
        } catch (IOException ex) {
            System.getLogger(Komunikacija.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
       
    }
    
    public static Komunikacija getInstance(){
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }
    
    
    public void posaljiZahtev(KorisnickiZahtev kz){
        try {
            out.writeObject(kz);
            out.flush();
            
        } catch (IOException ex) {
            System.getLogger(Komunikacija.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public ServerskiOdgovor primiOdgovor(){
        try {
            return (ServerskiOdgovor) in.readObject();
        } catch (IOException ex) {
            System.getLogger(Komunikacija.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (ClassNotFoundException ex) {
            System.getLogger(Komunikacija.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }
    
    
    public void zatvoriKomunikaciju(){
        try {
            in.close();
            out.close();
            s.close();
    
        } catch (IOException ex) {
            System.getLogger(Komunikacija.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
   
    }
    
    
}
