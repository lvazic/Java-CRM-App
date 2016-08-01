/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.KontaktOsoba;

/**
 *
 * @author lvazi_000
 */
public class SOKreirajOsobu extends OpstaSistemskaOperacija {

    private KontaktOsoba ko;
    
    
    
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {

    }

    @Override
    protected void proveriPostuslov(Object obj) throws Exception {

    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {

        ko = new KontaktOsoba();
    }

    public KontaktOsoba getKo() {
        return ko;
    }
    
}
