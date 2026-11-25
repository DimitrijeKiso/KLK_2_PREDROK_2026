/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import model.Korisnik;

/**
 *
 * @author Dimitrije
 */
public class ModelTabele extends AbstractTableModel {
    
    private final String[] kolone ={"username","status"};

    List<Korisnik> listaKorisnika = new ArrayList();
    
    public ModelTabele(List<Korisnik> lista){
    listaKorisnika = lista;
    }
    
    
    
    @Override
    public int getRowCount() {
       return listaKorisnika.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Korisnik k = listaKorisnika.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return k.getKorisnik();
            case 1:
                return k.isPrijavljen();
            default: 
                return "N'A";
        }
        
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    
    
    
    
}
