/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.model;

import domen.Proizvod;
import domen.StavkaProdaje;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lvazi_000
 */
public class TableModelStavkeProdaje extends AbstractTableModel {
    
    private List<StavkaProdaje> stavke;
    
    String[] kolone = new String[]{"Proizvod", "Cena", "Količina", "Ukupno"};
    
    public TableModelStavkeProdaje() {
        
        stavke = new ArrayList<>();
    }
    
    @Override
    public String getColumnName(int column) {
        
        if (column >= kolone.length) {
            return "N/A";
        }
        return kolone[column];
        
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        
        switch (columnIndex) {
            case 0:
                return true;
            case 2:
                return true;
            default:
                return false;
        }
        
    }
    
    @Override
    public int getRowCount() {
        
        return stavke.size();
        
    }
    
    @Override
    public int getColumnCount() {
        
        return kolone.length;
        
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        StavkaProdaje sp = stavke.get(rowIndex);
        
        switch (columnIndex) {
            
            case 0:
                return sp.getProizvod();
            case 1:
                return sp.getProizvod().getCena();
            case 2:
                return sp.getKolicina();
            case 3:
                return sp.getVrednost();
            default:
                return "N/A";
            
        }
        
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        
        StavkaProdaje sp = stavke.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                sp.setProizvod((Proizvod) aValue);
                sp.setPrid(sp.getProizvod().getPrid());
                System.out.println("PROIZVOD ID " + sp.getPrid());
                break;
            case 2:
                sp.setKolicina(Integer.parseInt((String) aValue));
                sp.setVrednost(sp.getKolicina() * sp.getProizvod().getCena());
                fireTableDataChanged();
                break;
            
        }
        
    }
    
    public List<StavkaProdaje> getStavke() {
        return stavke;
    }
    
}
