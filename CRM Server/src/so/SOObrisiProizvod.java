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
public class SOObrisiProizvod extends OpstaSistemskaOperacija {

    @Override
    protected void proveriPreduslov(Object obj) throws Exception {

        
    }

    @Override
    protected void proveriPostuslov(Object obj) throws Exception {

    
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {

        Proizvod p = (Proizvod) obj;
        DatabaseBroker.getInstance().obrisi(p);
    
    }
    
    
    
}
