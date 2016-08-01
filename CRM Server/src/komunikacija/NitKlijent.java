/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import domen.Komercijalista;
import domen.KontaktOsoba;
import domen.Kupac;
import domen.Mesto;
import domen.Prodaja;
import domen.Proizvod;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import logic.Kontroler;
import so.OpstaSistemskaOperacija;
import so.SOAzurirajKontaktOsobu;
import so.SOAzurirajKupca;
import so.SOAzurirajProizvod;
import so.SOObrisiKupca;
import so.SOObrisiOsobu;
import so.SOObrisiProizvod;
import so.SOUcitajKontaktOsobe;
import so.SOUcitajKupce;
import so.SOUcitajMesta;
import so.SOUcitajProizvode;
import so.SOZapamtiKupca;
import so.SOZapamtiOsobu;
import so.SOZapamtiProdaju;
import so.SOZapamtiProizvode;
import transfer.KlijentTransferObjekat;
import transfer.ServerTransferObjekat;

/**
 *
 * @author student1
 */
public class NitKlijent extends Thread {

    private Socket soket;
    private boolean status;
    private Komercijalista komercijalista;

    public NitKlijent(Socket socket) {
        this.soket = socket;
        status = true;
        start();
    }

    @Override
    public void run() {

        while (status) {

            try {

                izvrsenjeOperacije();

            } catch (Exception ex) {

                Kontroler.getInstance().izbaciKorisnika(komercijalista);
                status = false;
                System.out.println("Klijent je prekinuo rad!");

            }
        }
    }

    private void izvrsenjeOperacije() throws IOException, ClassNotFoundException {

        ObjectInputStream inSoket = new ObjectInputStream(soket.getInputStream());

        KlijentTransferObjekat toKlijent = (KlijentTransferObjekat) inSoket.readObject();

        ServerTransferObjekat toServer = new ServerTransferObjekat();

        try {
            switch (toKlijent.getOperacija()) {

                case konstante.Konstante.OPERACIJA_LOGIN:
                    Komercijalista k = (Komercijalista) toKlijent.getParametar();
                    if (Kontroler.getInstance().daLiKomercijalistaPostoji(k)) {
                        if (Kontroler.getInstance().daLiJePasswordTacan(k)) {
                            komercijalista = Kontroler.getInstance().vratiKomercijalistu(k);
                            toServer.setRezultat(konstante.Konstante.REZULTAT_OK);
                            toServer.setOdgovor(komercijalista);
                            Kontroler.getInstance().dodajKorisnika(komercijalista);
                            break;
                        } else {
                            toServer.setRezultat(konstante.Konstante.REZULTAT_FAIL);
                            toServer.setOdgovor("Netacan password!");
                            break;
                        }
                    } else {

                        toServer.setRezultat(konstante.Konstante.REZULTAT_FAIL);
                        toServer.setOdgovor("Korisnik ne postoji!");
                        break;

                    }

                case konstante.Konstante.OPERACIJA_VRATI_MESTA:
                    toServer.setRezultat(konstante.Konstante.REZULTAT_OK);
                    toServer.setOdgovor(Kontroler.getInstance().vratiMesta());
                    break;

                case konstante.Konstante.OPERACIJA_SACUVAJ_KUPCA:
                    Kontroler.getInstance().sacuvajKupca((Kupac) toKlijent.getParametar());
                    toServer.setRezultat(konstante.Konstante.REZULTAT_OK);
                    break;

                case konstante.Konstante.OPERACIJA_VRATI_KUPCE:
                    toServer.setRezultat(konstante.Konstante.REZULTAT_OK);
                    toServer.setOdgovor(Kontroler.getInstance().vratiKupce());
                    break;

                case konstante.Konstante.OPERACIJA_SACUVAJ_KONTAKT_OSOBU:
                    Kontroler.getInstance().sacuvajKontaktOsobu((KontaktOsoba) toKlijent.getParametar());
                    toServer.setRezultat(konstante.Konstante.REZULTAT_OK);
                    break;

                case konstante.Konstante.OPERACIJA_SACUVAJ_PROIZVODE:
                    Kontroler.getInstance().sacuvajProizvode((List<Proizvod>) toKlijent.getParametar());
                    toServer.setRezultat(konstante.Konstante.REZULTAT_OK);
                    break;

                case konstante.Konstante.OPERACIJA_VRATI_KONTAKT_OSOBE:
                    toServer.setRezultat(konstante.Konstante.REZULTAT_OK);
                    toServer.setOdgovor(Kontroler.getInstance().vratiKontaktOsobe());
                    break;

                case konstante.Konstante.OPERACIJA_VRATI_PROIZVODE:
                    toServer.setRezultat(konstante.Konstante.REZULTAT_OK);
                    toServer.setOdgovor(Kontroler.getInstance().vratiProizvode());
                    break;

                case konstante.Konstante.OPERACIJA_OBRISI_KUPCA:
                    Kontroler.getInstance().obrisiKupca((Kupac) toKlijent.getParametar());
                    toServer.setRezultat(konstante.Konstante.REZULTAT_OK);
                    break;

                case konstante.Konstante.OPERACIJA_OBRISI_KONTAKT_OSOBU:
                    Kontroler.getInstance().obrisiKontaktOsobu((KontaktOsoba) toKlijent.getParametar());
                    toServer.setRezultat(konstante.Konstante.REZULTAT_OK);
                    break;

                case konstante.Konstante.OPERACIJA_OBRISI_PROIZVOD:
                    Kontroler.getInstance().obrisiProizvod((Proizvod) toKlijent.getParametar());
                    toServer.setRezultat(konstante.Konstante.REZULTAT_OK);
                    break;

                case konstante.Konstante.OPERACIJA_SACUVAJ_PRODAJU:
                    Kontroler.getInstance().sacuvajProdaju((Prodaja) toKlijent.getParametar());
                    toServer.setRezultat(konstante.Konstante.REZULTAT_OK);
                    break;

                case konstante.Konstante.OPERACIJA_AZURIRAJ_KUPCA:
                    Kontroler.getInstance().azurirajKupca((Kupac) toKlijent.getParametar());
                    toServer.setRezultat(konstante.Konstante.REZULTAT_OK);
                    break;

                case konstante.Konstante.OPERACIJA_AZURIRAJ_KONTAKT_OSOBU:
                    Kontroler.getInstance().azurirajKontaktOsobu((KontaktOsoba) toKlijent.getParametar());
                    toServer.setRezultat(konstante.Konstante.REZULTAT_OK);
                    break;

                case konstante.Konstante.OPERACIJA_AZURIRAJ_PROIZVOD:
                    Kontroler.getInstance().azurirajProizvod((Proizvod) toKlijent.getParametar());
                    toServer.setRezultat(konstante.Konstante.REZULTAT_OK);
                    break;

                case konstante.Konstante.OPERACIJA_NAPRAVI_KUPCA:
                    toServer.setOdgovor(Kontroler.getInstance().napraviKupca());
                    toServer.setRezultat(konstante.Konstante.REZULTAT_OK);
                    break;

                case konstante.Konstante.OPERACIJA_NAPRAVI_OSOBU:
                    toServer.setOdgovor(Kontroler.getInstance().napraviOsobu());
                    toServer.setRezultat(konstante.Konstante.REZULTAT_OK);
                    break;

                case konstante.Konstante.OPERACIJA_NAPRAVI_PROIZVOD:
                    toServer.setOdgovor(Kontroler.getInstance().napraviProizvod());
                    toServer.setRezultat(konstante.Konstante.REZULTAT_OK);
                    break;

                case konstante.Konstante.OPERACIJA_NAPRAVI_PRODAJU:
                    toServer.setOdgovor(Kontroler.getInstance().napraviProdaju());
                    toServer.setRezultat(konstante.Konstante.REZULTAT_OK);
                    break;

                case konstante.Konstante.OPERACIJA_UCITAJ_KUPCA:
                    toServer.setOdgovor(Kontroler.getInstance().ucitajKupca((Kupac) toKlijent.getParametar()));
                    toServer.setRezultat(konstante.Konstante.REZULTAT_OK);
                    break;

                case konstante.Konstante.OPERACIJA_UCITAJ_PROIZVOD:
                    toServer.setOdgovor(Kontroler.getInstance().ucitajProizvod((Proizvod) toKlijent.getParametar()));
                    toServer.setRezultat(konstante.Konstante.REZULTAT_OK);
                    break;

                case konstante.Konstante.OPERACIJA_UCITAJ_OSOBU:
                    toServer.setOdgovor(Kontroler.getInstance().ucitajOsobu((KontaktOsoba) toKlijent.getParametar()));
                    toServer.setRezultat(konstante.Konstante.REZULTAT_OK);
                    break;

                case konstante.Konstante.OPERACIJA_PRETRAZI_KUPCE:
                    toServer.setOdgovor(Kontroler.getInstance().vratiFiltriraneKupce((String) toKlijent.getParametar()));
                    toServer.setRezultat(konstante.Konstante.REZULTAT_OK);
                    break;

                case konstante.Konstante.OPERACIJA_PRETRAZI_OSOBE:
                    toServer.setOdgovor(Kontroler.getInstance().vratiFiltriraneOsobe((String) toKlijent.getParametar()));
                    toServer.setRezultat(konstante.Konstante.REZULTAT_OK);
                    break;

                case konstante.Konstante.OPERACIJA_PRETRAZI_PROIZVODE:
                    toServer.setOdgovor(Kontroler.getInstance().vratiFiltriraneProizvode((String) toKlijent.getParametar()));
                    toServer.setRezultat(konstante.Konstante.REZULTAT_OK);
                    break;
            }
        } catch (Exception exception) {

            toServer.setRezultat(konstante.Konstante.REZULTAT_GRESKA);
            toServer.setIzuzetak(exception);
        }

        posaljiOdgovor(toServer);

    }

    private void posaljiOdgovor(ServerTransferObjekat toOdgovor) throws IOException {
        ObjectOutputStream outSocket = new ObjectOutputStream(soket.getOutputStream());
        outSocket.writeObject(toOdgovor);
    }

}
