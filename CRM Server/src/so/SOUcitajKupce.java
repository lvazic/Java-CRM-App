/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DatabaseBroker;
import domen.Kupac;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lvazi_000
 */
public class SOUcitajKupce extends OpstaSistemskaOperacija {
    
    private List<Kupac> kupci;
    
    

    @Override
    protected void proveriPreduslov(Object obj) throws Exception {

    }

    @Override
    protected void proveriPostuslov(Object obj) throws Exception {

    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {

      
        List<OpstiDomenskiObjekat> lista = DatabaseBroker.getInstance().vratiJoinListu(new Kupac());
        kupci = new ArrayList<>();
        
        for (OpstiDomenskiObjekat odo : lista) {
            
            kupci.add((Kupac)odo);
            
        }
        
    }

    public List<Kupac> getKupci() {
        return kupci;
    }

    
    
}
