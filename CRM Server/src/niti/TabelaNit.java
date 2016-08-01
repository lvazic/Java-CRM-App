/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import view.model.TableModelKorisnici;

/**
 *
 * @author lvazi_000
 */
public class TabelaNit extends Thread {
    
    private JTable tabela;
    private TableModelKorisnici model;

    public TabelaNit(JTable tabela) {
        this.tabela = tabela;
    }

    public TabelaNit(TableModelKorisnici model) {
        this.model = model;
    }
    
    

    @Override
    
    public void run() {

        while(true) {
            
            try {
                
                model.fireTableDataChanged();
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TabelaNit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
    
    
    
}
