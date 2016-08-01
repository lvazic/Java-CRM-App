/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DatabaseBroker;
import domen.Proizvod;

/**
 *
 * @author lvazi_000
 */
public class SOUcitajProizvod extends OpstaSistemskaOperacija {

    
    Proizvod p;

    public Proizvod getP() {
        return p;
    }
    
    
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {

    }

    @Override
    protected void proveriPostuslov(Object obj) throws Exception {

    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {

        Proizvod pr = (Proizvod) obj;
        
        p = (Proizvod) DatabaseBroker.getInstance().vratiObjekat(pr);
    
    }
    
}
