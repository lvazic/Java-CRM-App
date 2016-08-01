/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DatabaseBroker;
import domen.KontaktOsoba;

/**
 *
 * @author lvazi_000
 */
public class SOUcitajOsobu extends OpstaSistemskaOperacija {

    
    KontaktOsoba ko;

    public KontaktOsoba getKo() {
        return ko;
    }
    
    
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {

    }

    @Override
    protected void proveriPostuslov(Object obj) throws Exception {

    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {

        KontaktOsoba k = (KontaktOsoba) obj;
        ko = (KontaktOsoba) DatabaseBroker.getInstance().vratiJoinObjekat(k);
    }
    
}
