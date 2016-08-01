/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import domen.Komercijalista;
import domen.KontaktOsoba;
import domen.Kupac;
import domen.Mesto;
import domen.OpstiDomenskiObjekat;
import domen.Prodaja;
import domen.Proizvod;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import transfer.KlijentTransferObjekat;
import transfer.ServerTransferObjekat;
import view.JDialogLogin;
import view.JPanelAzuriranje;
import view.model.TableModelKupci;

/**
 *
 * @author lvazi_000
 */
public class Kontroler {

    private Komercijalista komercijalista;

    private List<Kupac> kupci;
    private List<Proizvod> proizvodi;
    private List<KontaktOsoba> osobe;

    private List<Kupac> filtriraniKupci;
    private List<Proizvod> filtriraniProizvodi;
    private List<KontaktOsoba> filtriraneOsobe;

    private static Kontroler instance;

    private Kontroler() {

    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public Komercijalista getKomercijalista() {
        return komercijalista;
    }

    public void setKomercijalista(Komercijalista komercijalista) {
        this.komercijalista = komercijalista;
    }

    public List<Kupac> getKupci() {
        return kupci;
    }

    public void setKupci(List<Kupac> kupci) {
        this.kupci = kupci;
    }

    public List<Proizvod> getProizvodi() {
        return proizvodi;
    }

    public void setProizvodi(List<Proizvod> proizvodi) {
        this.proizvodi = proizvodi;
    }

    public List<KontaktOsoba> getOsobe() {
        return osobe;
    }

    public void setOsobe(List<KontaktOsoba> osobe) {
        this.osobe = osobe;
    }

    public List<Kupac> getFiltriraniKupci() {
        return filtriraniKupci;
    }

    public void setFiltriraniKupci(List<Kupac> filtriraniKupci) {
        this.filtriraniKupci = filtriraniKupci;
    }

    public List<Proizvod> getFiltriraniProizvodi() {
        return filtriraniProizvodi;
    }

    public void setFiltriraniProizvodi(List<Proizvod> filtriraniProizvodi) {
        this.filtriraniProizvodi = filtriraniProizvodi;
    }

    public List<KontaktOsoba> getFiltriraneOsobe() {
        return filtriraneOsobe;
    }

    public void setFiltriraneOsobe(List<KontaktOsoba> filtriraneOsobe) {
        this.filtriraneOsobe = filtriraneOsobe;
    }

    public void pretraziKupce(String kriterijum) {

        filtriraniKupci = new ArrayList<>();

        for (Kupac k : kupci) {

            if (k.getNaziv().toUpperCase().contains(kriterijum.toUpperCase()) || k.getPib().contains(kriterijum) || k.getMib().contains(kriterijum)) {
                filtriraniKupci.add(k);
            }

        }
    }

    public void pretraziProizvode(String kriterijum) {

        filtriraniProizvodi = new ArrayList<>();

        for (Proizvod p : proizvodi) {

            double d;

            try {
                d = Double.parseDouble(kriterijum);

                if (p.getCena() < d) {
                    filtriraniProizvodi.add(p);
                }

            } catch (NumberFormatException numberFormatException) {
                if (p.getNaziv().toUpperCase().contains(kriterijum.toUpperCase())) {
                    filtriraniProizvodi.add(p);
                }
            }

        }
    }

    public void pretraziKontaktOsobe(String kriterijum) {

        filtriraneOsobe = new ArrayList<>();

        for (KontaktOsoba ko : osobe) {

            if (ko.getIme().toUpperCase().contains(kriterijum.toUpperCase()) || ko.getPrezime().toUpperCase().contains(kriterijum.toUpperCase()) || ko.getEmail().toUpperCase().contains(kriterijum.toUpperCase()) || ko.getKupac().toString().toUpperCase().contains(kriterijum.toUpperCase())) {
                filtriraneOsobe.add(ko);
            }

        }
    }

    public boolean loginKomercijaliste(String username, String password) throws Exception {

        Komercijalista k = new Komercijalista(0, null, null, username, password);

        KlijentTransferObjekat toKlijent = new KlijentTransferObjekat();
        toKlijent.setOperacija(konstante.Konstante.OPERACIJA_LOGIN);
        toKlijent.setParametar(k);
        komunikacija.Komunikacija.getInstance().posaljiZahtev(toKlijent);

        ServerTransferObjekat toServer = komunikacija.Komunikacija.getInstance().procitajOdgovor();

        switch (toServer.getRezultat()) {

            case (konstante.Konstante.REZULTAT_OK):
                setKomercijalista((Komercijalista) toServer.getOdgovor());
                return true;
            case (konstante.Konstante.REZULTAT_FAIL):
                return false;

            case (konstante.Konstante.REZULTAT_GRESKA):
                throw toServer.getIzuzetak();

        }
        return false;

    }

    public List<Kupac> vratiKupce() throws Exception {

        KlijentTransferObjekat toKlijent = new KlijentTransferObjekat();
        toKlijent.setOperacija(konstante.Konstante.OPERACIJA_VRATI_KUPCE);

        komunikacija.Komunikacija.getInstance().posaljiZahtev(toKlijent);

        ServerTransferObjekat toServer = komunikacija.Komunikacija.getInstance().procitajOdgovor();

        switch (toServer.getRezultat()) {

            case konstante.Konstante.REZULTAT_OK:
                setKupci((List<Kupac>) toServer.getOdgovor());
                return getKupci();

            case konstante.Konstante.REZULTAT_GRESKA:
                throw toServer.getIzuzetak();
        }

        return null;

    }

    public List<KontaktOsoba> vratiKontaktOsobe() throws Exception {
        KlijentTransferObjekat toKlijent = new KlijentTransferObjekat();
        toKlijent.setOperacija(konstante.Konstante.OPERACIJA_VRATI_KONTAKT_OSOBE);

        komunikacija.Komunikacija.getInstance().posaljiZahtev(toKlijent);

        ServerTransferObjekat toServer = komunikacija.Komunikacija.getInstance().procitajOdgovor();

        switch (toServer.getRezultat()) {

            case konstante.Konstante.REZULTAT_OK:
                setOsobe((List<KontaktOsoba>) toServer.getOdgovor());
                return getOsobe();

            case konstante.Konstante.REZULTAT_GRESKA:
                throw toServer.getIzuzetak();
        }

        return null;

    }

    public List<Proizvod> vratiProizvode() throws Exception {

        KlijentTransferObjekat toKlijent = new KlijentTransferObjekat();
        toKlijent.setOperacija(konstante.Konstante.OPERACIJA_VRATI_PROIZVODE);

        komunikacija.Komunikacija.getInstance().posaljiZahtev(toKlijent);

        ServerTransferObjekat toServer = komunikacija.Komunikacija.getInstance().procitajOdgovor();

        switch (toServer.getRezultat()) {

            case konstante.Konstante.REZULTAT_OK:
                setProizvodi((List<Proizvod>) toServer.getOdgovor());
                return getProizvodi();

            case konstante.Konstante.REZULTAT_GRESKA:
                throw toServer.getIzuzetak();
        }

        return null;
    }

    public void azurirajProizvod(Proizvod p) throws Exception {
        KlijentTransferObjekat toKlijent = new KlijentTransferObjekat();
        toKlijent.setOperacija(konstante.Konstante.OPERACIJA_AZURIRAJ_PROIZVOD);
        toKlijent.setParametar(p);

        komunikacija.Komunikacija.getInstance().posaljiZahtev(toKlijent);

        ServerTransferObjekat toServer = komunikacija.Komunikacija.getInstance().procitajOdgovor();
        
        switch (toServer.getRezultat()) {
                case konstante.Konstante.REZULTAT_OK:
                    break;

                case konstante.Konstante.REZULTAT_GRESKA:
                    throw toServer.getIzuzetak();

            }
        
    }
    
    
    public void azurirajKontaktOsobu(KontaktOsoba ko) throws Exception {
        KlijentTransferObjekat toKlijent = new KlijentTransferObjekat();
        toKlijent.setOperacija(konstante.Konstante.OPERACIJA_AZURIRAJ_KONTAKT_OSOBU);
        toKlijent.setParametar(ko);

        komunikacija.Komunikacija.getInstance().posaljiZahtev(toKlijent);

        ServerTransferObjekat toServer = komunikacija.Komunikacija.getInstance().procitajOdgovor();
        
        switch (toServer.getRezultat()) {
                case konstante.Konstante.REZULTAT_OK:
                    break;

                case konstante.Konstante.REZULTAT_GRESKA:
                    throw toServer.getIzuzetak();

            }
        
    }
    
    
    public void sacuvajKontaktOsobu(KontaktOsoba ko) throws Exception {
        KlijentTransferObjekat toKlijent = new KlijentTransferObjekat();
        toKlijent.setOperacija(konstante.Konstante.OPERACIJA_SACUVAJ_KONTAKT_OSOBU);
        toKlijent.setParametar(ko);

        komunikacija.Komunikacija.getInstance().posaljiZahtev(toKlijent);

        ServerTransferObjekat toServer = komunikacija.Komunikacija.getInstance().procitajOdgovor();
        
        switch (toServer.getRezultat()) {
                case konstante.Konstante.REZULTAT_OK:
                    break;

                case konstante.Konstante.REZULTAT_GRESKA:
                    throw toServer.getIzuzetak();

            }
        
    }
    
    public void azurirajKupca(Kupac k) throws Exception {
        KlijentTransferObjekat toKlijent = new KlijentTransferObjekat();
        toKlijent.setOperacija(konstante.Konstante.OPERACIJA_AZURIRAJ_KUPCA);
        toKlijent.setParametar(k);

        komunikacija.Komunikacija.getInstance().posaljiZahtev(toKlijent);

        ServerTransferObjekat toServer = komunikacija.Komunikacija.getInstance().procitajOdgovor();
        
        switch (toServer.getRezultat()) {
                case konstante.Konstante.REZULTAT_OK:
                    break;

                case konstante.Konstante.REZULTAT_GRESKA:
                    throw toServer.getIzuzetak();

            }
        
    }
    
    
    public void sacuvajKupca(Kupac k) throws Exception {
        KlijentTransferObjekat toKlijent = new KlijentTransferObjekat();
        toKlijent.setOperacija(konstante.Konstante.OPERACIJA_SACUVAJ_KUPCA);
        toKlijent.setParametar(k);

        komunikacija.Komunikacija.getInstance().posaljiZahtev(toKlijent);

        ServerTransferObjekat toServer = komunikacija.Komunikacija.getInstance().procitajOdgovor();
        
        switch (toServer.getRezultat()) {
                case konstante.Konstante.REZULTAT_OK:
                    break;

                case konstante.Konstante.REZULTAT_GRESKA:
                    throw toServer.getIzuzetak();

            }
        
    }
    
    public List<Mesto> vratiMesta() throws Exception {

        KlijentTransferObjekat toKlijent = new KlijentTransferObjekat();
        toKlijent.setOperacija(konstante.Konstante.OPERACIJA_VRATI_MESTA);

        komunikacija.Komunikacija.getInstance().posaljiZahtev(toKlijent);

        ServerTransferObjekat toServer = komunikacija.Komunikacija.getInstance().procitajOdgovor();

        switch (toServer.getRezultat()) {

            case konstante.Konstante.REZULTAT_OK:
                return ((List<Mesto>) toServer.getOdgovor());
                

            case konstante.Konstante.REZULTAT_GRESKA:
                throw toServer.getIzuzetak();
        }

        return null;

    }
    
    
    public void sacuvajProdaju(Prodaja p) throws Exception {
        KlijentTransferObjekat toKlijent = new KlijentTransferObjekat();
        toKlijent.setOperacija(konstante.Konstante.OPERACIJA_SACUVAJ_PRODAJU);
        toKlijent.setParametar(p);

        komunikacija.Komunikacija.getInstance().posaljiZahtev(toKlijent);

        ServerTransferObjekat toServer = komunikacija.Komunikacija.getInstance().procitajOdgovor();
        
        switch (toServer.getRezultat()) {
                case konstante.Konstante.REZULTAT_OK:
                    break;

                case konstante.Konstante.REZULTAT_GRESKA:
                    throw toServer.getIzuzetak();

            }
        
    }
    
    public void sacuvajProizvode(List<Proizvod> lista) throws Exception {
        KlijentTransferObjekat toKlijent = new KlijentTransferObjekat();
        toKlijent.setOperacija(konstante.Konstante.OPERACIJA_SACUVAJ_PROIZVODE);
        toKlijent.setParametar(lista);

        komunikacija.Komunikacija.getInstance().posaljiZahtev(toKlijent);

        ServerTransferObjekat toServer = komunikacija.Komunikacija.getInstance().procitajOdgovor();
        
        switch (toServer.getRezultat()) {
                case konstante.Konstante.REZULTAT_OK:
                    break;

                case konstante.Konstante.REZULTAT_GRESKA:
                    throw toServer.getIzuzetak();

            }
        
    }
    
    public Kupac napraviKupca() throws Exception {

        KlijentTransferObjekat toKlijent = new KlijentTransferObjekat();
        toKlijent.setOperacija(konstante.Konstante.OPERACIJA_NAPRAVI_KUPCA);

        komunikacija.Komunikacija.getInstance().posaljiZahtev(toKlijent);

        ServerTransferObjekat toServer = komunikacija.Komunikacija.getInstance().procitajOdgovor();

        switch (toServer.getRezultat()) {

            case konstante.Konstante.REZULTAT_OK:
                return  (Kupac) toServer.getOdgovor();
                

            case konstante.Konstante.REZULTAT_GRESKA:
                throw toServer.getIzuzetak();
        }

        return null;

    }
    
    public KontaktOsoba napraviOsobu() throws Exception {

        KlijentTransferObjekat toKlijent = new KlijentTransferObjekat();
        toKlijent.setOperacija(konstante.Konstante.OPERACIJA_NAPRAVI_OSOBU);

        komunikacija.Komunikacija.getInstance().posaljiZahtev(toKlijent);

        ServerTransferObjekat toServer = komunikacija.Komunikacija.getInstance().procitajOdgovor();

        switch (toServer.getRezultat()) {

            case konstante.Konstante.REZULTAT_OK:
                return  (KontaktOsoba) toServer.getOdgovor();
                

            case konstante.Konstante.REZULTAT_GRESKA:
                throw toServer.getIzuzetak();
        }

        return null;

    }
    
    
    public Proizvod napraviProizvod() throws Exception {

        KlijentTransferObjekat toKlijent = new KlijentTransferObjekat();
        toKlijent.setOperacija(konstante.Konstante.OPERACIJA_NAPRAVI_PROIZVOD);

        komunikacija.Komunikacija.getInstance().posaljiZahtev(toKlijent);

        ServerTransferObjekat toServer = komunikacija.Komunikacija.getInstance().procitajOdgovor();

        switch (toServer.getRezultat()) {

            case konstante.Konstante.REZULTAT_OK:
                return  (Proizvod) toServer.getOdgovor();
                

            case konstante.Konstante.REZULTAT_GRESKA:
                throw toServer.getIzuzetak();
        }

        return null;

    }
    
    
    public Prodaja napraviProdaju() throws Exception {

        KlijentTransferObjekat toKlijent = new KlijentTransferObjekat();
        toKlijent.setOperacija(konstante.Konstante.OPERACIJA_NAPRAVI_PRODAJU);

        komunikacija.Komunikacija.getInstance().posaljiZahtev(toKlijent);

        ServerTransferObjekat toServer = komunikacija.Komunikacija.getInstance().procitajOdgovor();

        switch (toServer.getRezultat()) {

            case konstante.Konstante.REZULTAT_OK:
                return  (Prodaja) toServer.getOdgovor();
                

            case konstante.Konstante.REZULTAT_GRESKA:
                throw toServer.getIzuzetak();
        }

        return null;

    }
    
    public Kupac ucitajKupca(Kupac k) throws Exception {

        KlijentTransferObjekat toKlijent = new KlijentTransferObjekat();
        toKlijent.setOperacija(konstante.Konstante.OPERACIJA_UCITAJ_KUPCA);
        toKlijent.setParametar(k);
        komunikacija.Komunikacija.getInstance().posaljiZahtev(toKlijent);

        ServerTransferObjekat toServer = komunikacija.Komunikacija.getInstance().procitajOdgovor();

        switch (toServer.getRezultat()) {

            case konstante.Konstante.REZULTAT_OK:
                return  (Kupac) toServer.getOdgovor();
                

            case konstante.Konstante.REZULTAT_GRESKA:
                throw toServer.getIzuzetak();
        }

        return null;

    }
    
    public Proizvod ucitajProizvod(Proizvod p) throws Exception {

        KlijentTransferObjekat toKlijent = new KlijentTransferObjekat();
        toKlijent.setOperacija(konstante.Konstante.OPERACIJA_UCITAJ_PROIZVOD);
        toKlijent.setParametar(p);
        komunikacija.Komunikacija.getInstance().posaljiZahtev(toKlijent);

        ServerTransferObjekat toServer = komunikacija.Komunikacija.getInstance().procitajOdgovor();

        switch (toServer.getRezultat()) {

            case konstante.Konstante.REZULTAT_OK:
                return  (Proizvod) toServer.getOdgovor();
                

            case konstante.Konstante.REZULTAT_GRESKA:
                throw toServer.getIzuzetak();
        }

        return null;

    }
    
    public KontaktOsoba ucitajOsobu(KontaktOsoba ko) throws Exception {

        KlijentTransferObjekat toKlijent = new KlijentTransferObjekat();
        toKlijent.setOperacija(konstante.Konstante.OPERACIJA_UCITAJ_OSOBU);
        toKlijent.setParametar(ko);
        komunikacija.Komunikacija.getInstance().posaljiZahtev(toKlijent);

        ServerTransferObjekat toServer = komunikacija.Komunikacija.getInstance().procitajOdgovor();

        switch (toServer.getRezultat()) {

            case konstante.Konstante.REZULTAT_OK:
                return  (KontaktOsoba) toServer.getOdgovor();
                

            case konstante.Konstante.REZULTAT_GRESKA:
                throw toServer.getIzuzetak();
        }

        return null;

    }
    
    public List<Kupac> vratiFiltriraneKupce(String kriterijum) throws Exception {

        KlijentTransferObjekat toKlijent = new KlijentTransferObjekat();
        toKlijent.setOperacija(konstante.Konstante.OPERACIJA_PRETRAZI_KUPCE);
        toKlijent.setParametar(kriterijum);
        
        komunikacija.Komunikacija.getInstance().posaljiZahtev(toKlijent);

        ServerTransferObjekat toServer = komunikacija.Komunikacija.getInstance().procitajOdgovor();

        switch (toServer.getRezultat()) {

            case konstante.Konstante.REZULTAT_OK:
                setFiltriraniKupci((List<Kupac>) toServer.getOdgovor());
                return getFiltriraniKupci();

            case konstante.Konstante.REZULTAT_GRESKA:
                throw toServer.getIzuzetak();
        }

        return null;

    }
    
    
    public List<KontaktOsoba> vratiFiltriraneOsobe(String kriterijum) throws Exception {

        KlijentTransferObjekat toKlijent = new KlijentTransferObjekat();
        toKlijent.setOperacija(konstante.Konstante.OPERACIJA_PRETRAZI_OSOBE);
        toKlijent.setParametar(kriterijum);
        
        komunikacija.Komunikacija.getInstance().posaljiZahtev(toKlijent);

        ServerTransferObjekat toServer = komunikacija.Komunikacija.getInstance().procitajOdgovor();

        switch (toServer.getRezultat()) {

            case konstante.Konstante.REZULTAT_OK:
                setFiltriraneOsobe((List<KontaktOsoba>) toServer.getOdgovor());
                return getFiltriraneOsobe();

            case konstante.Konstante.REZULTAT_GRESKA:
                throw toServer.getIzuzetak();
        }

        return null;

    }
    
    public List<Proizvod> vratiFiltriraneProizvode(String kriterijum) throws Exception {

        KlijentTransferObjekat toKlijent = new KlijentTransferObjekat();
        toKlijent.setOperacija(konstante.Konstante.OPERACIJA_PRETRAZI_PROIZVODE);
        toKlijent.setParametar(kriterijum);
        
        komunikacija.Komunikacija.getInstance().posaljiZahtev(toKlijent);

        ServerTransferObjekat toServer = komunikacija.Komunikacija.getInstance().procitajOdgovor();

        switch (toServer.getRezultat()) {

            case konstante.Konstante.REZULTAT_OK:
                setFiltriraniProizvodi((List<Proizvod>) toServer.getOdgovor());
                return getFiltriraniProizvodi();

            case konstante.Konstante.REZULTAT_GRESKA:
                throw toServer.getIzuzetak();
        }

        return null;

    }
    
    public void obrisiKupca(Kupac k) throws Exception {
        KlijentTransferObjekat toKlijent = new KlijentTransferObjekat();
        toKlijent.setOperacija(konstante.Konstante.OPERACIJA_OBRISI_KUPCA);
        toKlijent.setParametar(k);

        komunikacija.Komunikacija.getInstance().posaljiZahtev(toKlijent);

        ServerTransferObjekat toServer = komunikacija.Komunikacija.getInstance().procitajOdgovor();
        
        switch (toServer.getRezultat()) {
                case konstante.Konstante.REZULTAT_OK:
                    break;

                case konstante.Konstante.REZULTAT_GRESKA:
                    throw toServer.getIzuzetak();

            }
    }
    
    
    public void obrisiOsobu(KontaktOsoba k) throws Exception {
        KlijentTransferObjekat toKlijent = new KlijentTransferObjekat();
        toKlijent.setOperacija(konstante.Konstante.OPERACIJA_OBRISI_KONTAKT_OSOBU);
        toKlijent.setParametar(k);

        komunikacija.Komunikacija.getInstance().posaljiZahtev(toKlijent);

        ServerTransferObjekat toServer = komunikacija.Komunikacija.getInstance().procitajOdgovor();
        
        switch (toServer.getRezultat()) {
                case konstante.Konstante.REZULTAT_OK:
                    break;

                case konstante.Konstante.REZULTAT_GRESKA:
                    throw toServer.getIzuzetak();

            }
    }
    
    public void obrisiProizvod(Proizvod p) throws Exception {
        KlijentTransferObjekat toKlijent = new KlijentTransferObjekat();
        toKlijent.setOperacija(konstante.Konstante.OPERACIJA_OBRISI_PROIZVOD);
        toKlijent.setParametar(p);

        komunikacija.Komunikacija.getInstance().posaljiZahtev(toKlijent);

        ServerTransferObjekat toServer = komunikacija.Komunikacija.getInstance().procitajOdgovor();
        
        switch (toServer.getRezultat()) {
                case konstante.Konstante.REZULTAT_OK:
                    break;

                case konstante.Konstante.REZULTAT_GRESKA:
                    throw toServer.getIzuzetak();

            }
    }
    
    

}
