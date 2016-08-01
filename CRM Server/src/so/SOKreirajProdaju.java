/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.Prodaja;

/**
 *
 * @author lvazi_000
 */
public class SOKreirajProdaju extends OpstaSistemskaOperacija {

    Prodaja p;

    public Prodaja getP() {
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

    p = new Prodaja();
    
    }
    
}
