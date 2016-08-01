/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.model;

import domen.Komercijalista;
import domen.Kupac;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lvazi_000
 */
public class TableModelKorisnici extends AbstractTableModel {

    private List<Komercijalista> lista;
    
    String[] kolone = new String[] {"Korisnik"};

    public TableModelKorisnici(List<Komercijalista> lista) {
        this.lista = lista;
    }
    
    

    @Override
    public String getColumnName(int column) {

        if (column >= kolone.length) return "N/A";
        return kolone[column];
    
    }
    
    
    
    
    @Override
    public int getRowCount() {

        return lista.size();
    
    }

    @Override
    public int getColumnCount() {

        return kolone.length;
    
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Komercijalista k = lista.get(rowIndex);
        
        if (k != null) return k.toString();
        
        return "";
    
    }
    
}
