/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.model;

import domen.Proizvod;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lvazi_000
 */
public class TableModelProizvodi extends AbstractTableModel {

    public List<Proizvod> lista;
    
    private boolean edit;

    public TableModelProizvodi() {
        lista = new ArrayList<>();
        edit = true;
    }

    public TableModelProizvodi(List<Proizvod> lista) {
        this.lista = lista;
        edit = false;
    }
    
    

    @Override
    public String getColumnName(int column) {

        switch (column) {
            case 0:
                return "Naziv";

            case 1:
                return "Cena";

            default:
                return "N/A";

        }
    }

    @Override
    public int getRowCount() {

        return lista.size();

    }

    @Override
    public int getColumnCount() {

        return 2;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Proizvod p = lista.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return p.getNaziv();
            case 1:
                return p.getCena();
            default:
                return "N/A";

        }

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return edit;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        Proizvod p = lista.get(rowIndex);

        switch (columnIndex) {

            case 0:
                p.setNaziv((String) aValue);
                break;
            case 1:
                p.setCena(Double.parseDouble((String)aValue));
                break;

        }

    }

    public List<Proizvod> getLista() {
        return lista;
    }
    
    

}
