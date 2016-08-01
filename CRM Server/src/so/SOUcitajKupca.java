/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DatabaseBroker;
import domen.Kupac;

/**
 *
 * @author lvazi_000
 */
public class SOUcitajKupca extends OpstaSistemskaOperacija {

    
    Kupac k;

    public Kupac getK() {
        return k;
    }
    
    
    
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {

    }

    @Override
    protected void proveriPostuslov(Object obj) throws Exception {

    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {

        Kupac kupac = (Kupac) obj;
        k = (Kupac) DatabaseBroker.getInstance().vratiJoinObjekat(kupac);
    }
    
}
