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
public class SOZapamtiOsobu extends OpstaSistemskaOperacija {

    @Override
    protected void proveriPreduslov(Object obj) throws Exception {

        //validacija
    }

    @Override
    protected void proveriPostuslov(Object obj) throws Exception {

    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {

        KontaktOsoba ko = (KontaktOsoba) obj;
        DatabaseBroker.getInstance().sacuvaj(ko);
    }
    
}
