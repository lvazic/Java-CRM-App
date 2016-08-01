/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.model;

import domen.KontaktOsoba;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lvazi_000
 */
public class TableModelKontaktOsobe extends AbstractTableModel {
    
    private List<KontaktOsoba> osobe;
    
    private String[] kolone = new String[]{"Šifra", "Ime", "Prezime","Kompanija", "Telefon", "Email", "Napomene"};

    public TableModelKontaktOsobe(List<KontaktOsoba> osobe) {
        this.osobe = osobe;
    }

    @Override
    public String getColumnName(int column) {

        if (column >= kolone.length) return "N/A";
        return kolone[column];
    
    }
    
    

    @Override
    public int getRowCount() {

        return osobe.size();
    
    }

    @Override
    public int getColumnCount() {

        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        KontaktOsoba ko = osobe.get(rowIndex);
        
        switch(columnIndex) {
            
            case 0: return ko.getKontid();
            case 1: return ko.getIme();
            case 2: return ko.getPrezime();
            case 3: return ko.getKupac();
            case 4: return ko.getTelefon();
            case 5: return ko.getEmail();
            case 6: return ko.getNapomene();
            default: return "N/A";
                
            
        }
        
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return false;
    
    
    }

    public List<KontaktOsoba> getOsobe() {
        return osobe;
    }
    
    
    
}
