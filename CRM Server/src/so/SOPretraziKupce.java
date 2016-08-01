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
public class SOPretraziKupce extends OpstaSistemskaOperacija {

    List<Kupac> kupci;

    public List<Kupac> getKupci() {
        return kupci;
    }
    
    
    
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {

    }

    @Override
    protected void proveriPostuslov(Object obj) throws Exception {

    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {

        String kriterijum = (String) obj;
        List<OpstiDomenskiObjekat> lista = DatabaseBroker.getInstance().vratiFiltriranuJoinListu(new Kupac(), kriterijum);
    
        kupci = new ArrayList<>();
        for (OpstiDomenskiObjekat odo : lista) {
            
            kupci.add((Kupac) odo);
        }
    
    }
    
    
}
