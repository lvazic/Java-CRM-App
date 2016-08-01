/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DatabaseBroker;
import domen.Komercijalista;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lvazi_000
 */
public class SOPronadjiKomercijalistu extends OpstaSistemskaOperacija {
    
    private List<Komercijalista> rezultat;

    public List<Komercijalista> getRezultat() {
        return rezultat;
    }
    
    

    @Override
    protected void proveriPreduslov(Object obj) throws Exception {

    }

    @Override
    protected void proveriPostuslov(Object obj) throws Exception {

    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {

        Komercijalista k = (Komercijalista) obj;
    
        List<OpstiDomenskiObjekat> lista = DatabaseBroker.getInstance().vratiListu(k);
        
        rezultat = new ArrayList<>();
        
        for (OpstiDomenskiObjekat odo : lista) {
            
            rezultat.add((Komercijalista) odo);
            
        }
    }
    
}
