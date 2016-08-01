/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DatabaseBroker;
import domen.Prodaja;
import domen.StavkaProdaje;
import java.util.List;

/**
 *
 * @author lvazi_000
 */
public class SOZapamtiProdaju extends OpstaSistemskaOperacija {

    @Override
    protected void proveriPreduslov(Object obj) throws Exception {

    }

    @Override
    protected void proveriPostuslov(Object obj) throws Exception {

    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {

        Prodaja p = (Prodaja) obj;

        List<StavkaProdaje> stavke = p.getStavke();

        for (StavkaProdaje sp : p.getStavke()) {

            p.setVrednost(p.getVrednost() + sp.getVrednost());
        }
        int kljuc = DatabaseBroker.getInstance().sacuvaj(p);
        System.out.println("KLJUC KOJI JE DB VRATIO: " + kljuc);
        System.out.println("Prosla prodaja, prelazak na stavke");

        for (StavkaProdaje sp : stavke) {

            sp.setPid(kljuc);
            DatabaseBroker.getInstance().sacuvaj(sp);

        }
    }

}
