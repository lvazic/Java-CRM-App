/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lvazi_000
 */
public class KontaktOsoba implements OpstiDomenskiObjekat, Serializable, Filter {

    private int kupid;
    private int kontid;
    private String ime;
    private String prezime;
    private String telefon;
    private String email;
    private String napomene;

    private Kupac kupac;

    public KontaktOsoba() {
    }

    public KontaktOsoba(int kupid, int kontid, String ime, String prezime, String telefon, String email, String napomene) {
        this.kupid = kupid;
        this.kontid = kontid;
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.email = email;
        this.napomene = napomene;
    }

    public KontaktOsoba(int kupid, int kontid, String ime, String prezime, String telefon, String email, String napomene, Kupac kupac) {
        this.kupid = kupid;
        this.kontid = kontid;
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.email = email;
        this.napomene = napomene;
        this.kupac = kupac;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public int getKupid() {
        return kupid;
    }

    public void setKupid(int kupid) {
        this.kupid = kupid;
    }

    public int getKontid() {
        return kontid;
    }

    public void setKontid(int kontid) {
        this.kontid = kontid;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNapomene() {
        return napomene;
    }

    public void setNapomene(String napomene) {
        this.napomene = napomene;
    }

    @Override
    public String vratiNazivTabele() {

        return " kontaktosoba ";

    }

    @Override
    public String vratiNaziveAtributa() {

        return " kupid, kontid, ime, prezime, telefon, email, napomene ";

    }

    @Override
    public String vratiVrednostiAtributa() {

        return " '" + kupid + "', '" + kontid + "', '" + ime + "', '" + prezime + "', '" + telefon + "', '" + email + "', '" + napomene + "' ";

    }

    @Override
    public String vratiJoinUpit() {

        return " SELECT ko.kupid, ko.kontid, ko.ime, ko.prezime, ko.telefon, ko.email, ko.napomene, k.naziv, k.pib, k.mib, k.ulicaibroj, k.kid, k.mid, kom.ime AS komime, kom.prezime AS komprezime, m.naziv AS mnaziv, m.ptt FROM kontaktosoba ko JOIN kupac k ON (ko.kupid = k.kupid) JOIN komercijalista kom ON (k.kid = kom.kid) JOIN mesto m ON (k.mid = m.mid) ";
    }

    @Override
    public List<OpstiDomenskiObjekat> napuni(ResultSet rs) throws Exception {

        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int mid = rs.getInt("mid");
            String mnaziv = rs.getString("mnaziv");
            int ptt = rs.getInt("ptt");

            Mesto m = new Mesto(mid, mnaziv, ptt);

            int kid = rs.getInt("kid");
            String komime = rs.getString("komime");
            String komprezime = rs.getString("komprezime");

            Komercijalista k = new Komercijalista(kid, komime, komprezime, "", "");

            int kupid = rs.getInt("kupid");
            String naziv = rs.getString("naziv");
            String pib = rs.getString("pib");
            String mib = rs.getString("mib");
            String ulicaIBroj = rs.getString("ulicaibroj");

            Kupac kupac = new Kupac(kupid, naziv, pib, mib, ulicaIBroj, kid, mid, m, k);

            int kontid = rs.getInt("kontid");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String telefon = rs.getString("telefon");
            String email = rs.getString("email");
            String napomene = rs.getString("napomene");

            KontaktOsoba ko = new KontaktOsoba(kupid, kontid, ime, prezime, telefon, email, napomene, kupac);
            lista.add(ko);

        }

        return lista;

    }

    @Override
    public String vratiKljuc() {

        return " kontid ";

    }

    @Override
    public String vratiVrednostKljuca() {

        return " '" + kontid + "' ";

    }

    @Override
    public String vratiUpdateAtributa() {

        return " ime = '" + ime + "', prezime = '" + prezime + "', telefon = '" + telefon + "', email = '" + email + "', napomene = '" + napomene + "' ";

    }

    @Override
    public String napraviFilter(String kriterijum) {

        return " ko.ime LIKE '%" + kriterijum + "%' OR ko.prezime LIKE '%" + kriterijum + "%' OR ko.email LIKE '%" + kriterijum + "%' OR k.naziv LIKE '%" + kriterijum + "%' ";

    }

}
