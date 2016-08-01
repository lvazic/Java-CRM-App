/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.model;

import domen.Kupac;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lvazi_000
 */
public class TableModelKupci extends AbstractTableModel {

    private List<Kupac> kupci;

    
    String[] kolone = new String[]{"Naziv", "PIB", "MIB", "Ulica i broj", "Komercijalista", "Mesto"};
    
    
    public TableModelKupci(List<Kupac> kupci) {
        this.kupci = kupci;
    }

    public List<Kupac> getKupci() {
        return kupci;
    }

    public void setKupci(List<Kupac> kupci) {
        this.kupci = kupci;
    }
    
    

    @Override
    public String getColumnName(int column) {

        if (column >= kolone.length) return "N/A";
        return kolone[column];

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return false;

    }

    @Override
    public int getRowCount() {

        return kupci.size();

    }

    @Override
    public int getColumnCount() {

        return 7;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Kupac k = kupci.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return k.getKid();
            case 1:
                return k.getNaziv();
            case 2:
                return k.getPib();
            case 3:
                return k.getMib();
            case 4:
                return k.getUlicaIBroj();
            case 5:
                return k.getKomercijalista();
            case 6:
                return k.getMesto();
            default:
                return "N/A";

        }

    }

}
