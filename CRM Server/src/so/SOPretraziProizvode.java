/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DatabaseBroker;
import domen.OpstiDomenskiObjekat;
import domen.Proizvod;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lvazi_000
 */
public class SOPretraziProizvode extends OpstaSistemskaOperacija {

    private List<Proizvod> proizvodi;

    public List<Proizvod> getProizvodi() {
        return proizvodi;
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
        List<OpstiDomenskiObjekat> lista = DatabaseBroker.getInstance().vratiFiltriranuListu(new Proizvod(), kriterijum);

        proizvodi = new ArrayList<>();
        
        for (OpstiDomenskiObjekat odo : lista) {
            
            proizvodi.add((Proizvod) odo);
        }
        
        
    }
    
}
