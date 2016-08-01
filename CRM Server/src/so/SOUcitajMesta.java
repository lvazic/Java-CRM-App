/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DatabaseBroker;
import domen.Mesto;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lvazi_000
 */
public class SOUcitajMesta extends OpstaSistemskaOperacija {
    
    private List<Mesto> mesta;

    public List<Mesto> getMesta() {
        return mesta;
    }
    
    

    @Override
    protected void proveriPreduslov(Object obj) throws Exception {

    }

    @Override
    protected void proveriPostuslov(Object obj) throws Exception {

    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {

        
        List<OpstiDomenskiObjekat> lista = DatabaseBroker.getInstance().vratiListu(new Mesto());
        mesta = new ArrayList<>();
        
        for (OpstiDomenskiObjekat odo : lista) {
            
            mesta.add((Mesto) odo);
        }
        
        
        
        
    }
    
}
