/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import model.Korisnik;

/**
 *
 * @author Dimitrije
 */
public class Controller {
    
    private static Controller instance;
    private final int limit = 5;
    private List<Korisnik> korisnici = new ArrayList<>();
    
    private Controller() {
        Korisnik k1 = new Korisnik("korisnik1", false);
        Korisnik k2 = new Korisnik("korisnik2", false);
        Korisnik k3 = new Korisnik("korisnik3", false);
        Korisnik k4 = new Korisnik("korisnik4", false);
        Korisnik k5 = new Korisnik("korisnik5", false);
        Korisnik k6 = new Korisnik("korisnik6", false);
        
        korisnici.add(k1);
        korisnici.add(k2);
        korisnici.add(k3);
        korisnici.add(k4);
        korisnici.add(k5);
        korisnici.add(k6);
        
        
    }
    
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public List<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(List<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    public int getLimit() {
        return limit;
    }
    
    
    
    
    
}
