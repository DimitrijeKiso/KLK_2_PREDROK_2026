/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Dimitrije
 */
public class Korisnik implements Serializable {
    
    private String korisnik;
    private boolean prijavljen;

    public Korisnik() {
    }

    public Korisnik(String korisnik, boolean prijavljen) {
        this.korisnik = korisnik;
        this.prijavljen = prijavljen;
    }

    public String getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(String korisnik) {
        this.korisnik = korisnik;
    }

    public boolean isPrijavljen() {
        return prijavljen;
    }

    public void setPrijavljen(boolean prijavljen) {
        this.prijavljen = prijavljen;
    }

    @Override
    public String toString() {
        return "Korisnik{" + "korisnik=" + korisnik + ", prijavljen=" + prijavljen + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Korisnik other = (Korisnik) obj;
        return Objects.equals(this.korisnik, other.korisnik);
    }
    
    
    
    
    
}
