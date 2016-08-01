/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import db.DatabaseBroker;
import domen.Filter;
import domen.Komercijalista;
import domen.KontaktOsoba;
import domen.Kupac;
import domen.Mesto;
import domen.Prodaja;
import domen.Proizvod;
import java.util.ArrayList;
import java.util.List;
import so.OpstaSistemskaOperacija;
import so.SOAzurirajKontaktOsobu;
import so.SOAzurirajKupca;
import so.SOAzurirajProizvod;
import so.SOKreirajOsobu;
import so.SOKreirajKupca;
import so.SOKreirajProdaju;
import so.SOKreirajProizvod;
import so.SOObrisiKupca;
import so.SOObrisiOsobu;
import so.SOObrisiProizvod;
import so.SOPretraziKupce;
import so.SOPretraziOsobe;
import so.SOPretraziProizvode;
import so.SOPronadjiKomercijalistu;
import so.SOUcitajKontaktOsobe;
import so.SOUcitajKupca;
import so.SOUcitajKupce;
import so.SOUcitajMesta;
import so.SOUcitajOsobu;
import so.SOUcitajProizvod;
import so.SOUcitajProizvode;
import so.SOZapamtiKupca;
import so.SOZapamtiOsobu;
import so.SOZapamtiProdaju;
import so.SOZapamtiProizvode;

/**
 *
 * @author lvazi_000
 */
public class Kontroler {

    private DatabaseBroker broker;
    private static Kontroler instance;

    private List<Komercijalista> komercijalisti;

    private Kontroler() {

        broker = DatabaseBroker.getInstance();
        komercijalisti = new ArrayList<>();
    }

    public static Kontroler getInstance() {

        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public void dodajKorisnika(Komercijalista k) {
        komercijalisti.add(k);
    }

    public void izbaciKorisnika(Komercijalista k) {
        komercijalisti.remove(k);
    }

    public boolean daLiKomercijalistaPostoji(Komercijalista k) throws Exception {

        OpstaSistemskaOperacija oso = new SOPronadjiKomercijalistu();
        oso.izvrsenjeSO(k);
        List<Komercijalista> rez = ((SOPronadjiKomercijalistu) oso).getRezultat();

        for (Komercijalista kr : rez) {
            if (kr.getUsername().equals(k.getUsername())) {
                return true;
            }

        }
        return false;
    }

    public boolean daLiJePasswordTacan(Komercijalista k) throws Exception {

        OpstaSistemskaOperacija oso = new SOPronadjiKomercijalistu();
        oso.izvrsenjeSO(k);
        List<Komercijalista> rez = ((SOPronadjiKomercijalistu) oso).getRezultat();

        for (Komercijalista kr : rez) {
            if (kr.getUsername().equals(k.getUsername()) && kr.getPassword().equals(k.getPassword())) {
                return true;
            }

        }
        return false;
    }

    public Komercijalista vratiKomercijalistu(Komercijalista k) throws Exception {

        OpstaSistemskaOperacija oso = new SOPronadjiKomercijalistu();
        oso.izvrsenjeSO(k);
        List<Komercijalista> rez = ((SOPronadjiKomercijalistu) oso).getRezultat();
        for (Komercijalista kr : rez) {
            if (kr.getUsername().equals(k.getUsername()) && kr.getPassword().equals(k.getPassword())) {
                return kr;
            }

        }
        return null;
    }

    public List<Komercijalista> getKomercijalisti() {
        return komercijalisti;
    }

    public List<Mesto> vratiMesta() throws Exception {
        OpstaSistemskaOperacija oso = new SOUcitajMesta();
        oso.izvrsenjeSO(new Mesto());
        return ((SOUcitajMesta) oso).getMesta();
    }

    public void sacuvajKupca(Kupac k) throws Exception {
        OpstaSistemskaOperacija oso = new SOZapamtiKupca();
        oso.izvrsenjeSO(k);
    }

    public List<Kupac> vratiKupce() throws Exception {
        OpstaSistemskaOperacija oso = new SOUcitajKupce();
        oso.izvrsenjeSO(new Kupac());
        return ((SOUcitajKupce) oso).getKupci();
    }
    
    public List<Kupac> vratiFiltriraneKupce(String kriterijum) throws Exception {
        OpstaSistemskaOperacija oso = new SOPretraziKupce();
        Filter f = new Kupac();
        
        oso.izvrsenjeSO(f.napraviFilter(kriterijum));
        return ((SOPretraziKupce) oso).getKupci();
    }
    
    public List<KontaktOsoba> vratiFiltriraneOsobe(String kriterijum) throws Exception {
        OpstaSistemskaOperacija oso = new SOPretraziOsobe();
        Filter f = new KontaktOsoba();
        
        oso.izvrsenjeSO(f.napraviFilter(kriterijum));
        return ((SOPretraziOsobe) oso).getOsobe();
    }
    
    public List<Proizvod> vratiFiltriraneProizvode(String kriterijum) throws Exception {
        OpstaSistemskaOperacija oso = new SOPretraziProizvode();
        Filter f = new Proizvod();
        
        oso.izvrsenjeSO(f.napraviFilter(kriterijum));
        return ((SOPretraziProizvode) oso).getProizvodi();
    }

    public void sacuvajKontaktOsobu(KontaktOsoba k) throws Exception {
        OpstaSistemskaOperacija oso = new SOZapamtiOsobu();
        oso.izvrsenjeSO(k);
    }

    public void sacuvajProizvode(List<Proizvod> lista) throws Exception {
        OpstaSistemskaOperacija oso = new SOZapamtiProizvode();
        oso.izvrsenjeSO(lista);
    }

    public List<KontaktOsoba> vratiKontaktOsobe() throws Exception {
        OpstaSistemskaOperacija oso = new SOUcitajKontaktOsobe();
        oso.izvrsenjeSO(new KontaktOsoba());
        return ((SOUcitajKontaktOsobe) oso).getOsobe();
    }

    public List<Proizvod> vratiProizvode() throws Exception {
        OpstaSistemskaOperacija oso = new SOUcitajProizvode();
        oso.izvrsenjeSO(new Proizvod());
        return ((SOUcitajProizvode) oso).getProizvodi();
    }

    public void obrisiKupca(Kupac k) throws Exception {
        OpstaSistemskaOperacija oso = new SOObrisiKupca();
        oso.izvrsenjeSO(k);
    }

    public void obrisiKontaktOsobu(KontaktOsoba ko) throws Exception {
        OpstaSistemskaOperacija oso = new SOObrisiOsobu();
        oso.izvrsenjeSO(ko);
    }

    public void obrisiProizvod(Proizvod p) throws Exception {
        OpstaSistemskaOperacija oso = new SOObrisiProizvod();
        oso.izvrsenjeSO(p);
    }

    public void sacuvajProdaju(Prodaja p) throws Exception {

        OpstaSistemskaOperacija oso = new SOZapamtiProdaju();
        oso.izvrsenjeSO(p);
    }

    public void azurirajKupca(Kupac k) throws Exception {
        OpstaSistemskaOperacija oso = new SOAzurirajKupca();
        oso.izvrsenjeSO(k);
    }

    public void azurirajKontaktOsobu(KontaktOsoba ko) throws Exception {

        OpstaSistemskaOperacija oso = new SOAzurirajKontaktOsobu();
        oso.izvrsenjeSO(ko);
    }

    public void azurirajProizvod(Proizvod p) throws Exception {

        OpstaSistemskaOperacija oso = new SOAzurirajProizvod();
        oso.izvrsenjeSO(p);
    }

    public Kupac napraviKupca() throws Exception {
        OpstaSistemskaOperacija oso = new SOKreirajKupca();
        oso.izvrsenjeSO(null);
        return ((SOKreirajKupca) oso).getK();
    }

    public KontaktOsoba napraviOsobu() throws Exception {
        OpstaSistemskaOperacija oso = new SOKreirajOsobu();
        oso.izvrsenjeSO(null);
        return ((SOKreirajOsobu) oso).getKo();
    }

    public Proizvod napraviProizvod() throws Exception {
        OpstaSistemskaOperacija oso = new SOKreirajProizvod();
        oso.izvrsenjeSO(null);
        return ((SOKreirajProizvod) oso).getP();
    }

    public Prodaja napraviProdaju() throws Exception {
        OpstaSistemskaOperacija oso = new SOKreirajProdaju();
        oso.izvrsenjeSO(null);
        return ((SOKreirajProdaju) oso).getP();
    }

    public Kupac ucitajKupca(Kupac k) throws Exception {
        OpstaSistemskaOperacija oso = new SOUcitajKupca();
        oso.izvrsenjeSO(k);
        return ((SOUcitajKupca) oso).getK();

    }
    
    public Proizvod ucitajProizvod(Proizvod p) throws Exception {
        OpstaSistemskaOperacija oso = new SOUcitajProizvod();
        oso.izvrsenjeSO(p);
        return ((SOUcitajProizvod) oso).getP();

    }
    
    public KontaktOsoba ucitajOsobu(KontaktOsoba ko) throws Exception {
        OpstaSistemskaOperacija oso = new SOUcitajOsobu();
        oso.izvrsenjeSO(ko);
        return ((SOUcitajOsobu) oso).getKo();

    }

}
