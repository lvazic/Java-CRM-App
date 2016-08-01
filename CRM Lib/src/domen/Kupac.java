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
public class Kupac implements OpstiDomenskiObjekat, Serializable, Filter {

    private int kupid;
    private String naziv;
    private String pib;
    private String mib;
    private String ulicaIBroj;
    private int kid;
    private int mid;

    private Mesto mesto;
    private Komercijalista komercijalista;

    public Kupac() {
    }

    public Kupac(int kupid, String naziv, String pib, String mib, String ulicaIBroj, int kid, int mid) {
        this.kupid = kupid;
        this.naziv = naziv;
        this.pib = pib;
        this.mib = mib;
        this.ulicaIBroj = ulicaIBroj;
        this.kid = kid;
        this.mid = mid;
    }

    public Kupac(int kupid, String naziv, String pib, String mib, String ulicaIBroj, int kid, int mid, Mesto mesto, Komercijalista komercijalista) {
        this.kupid = kupid;
        this.naziv = naziv;
        this.pib = pib;
        this.mib = mib;
        this.ulicaIBroj = ulicaIBroj;
        this.kid = kid;
        this.mid = mid;
        this.mesto = mesto;
        this.komercijalista = komercijalista;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    public int getKupid() {
        return kupid;
    }

    public void setKupid(int kupid) {
        this.kupid = kupid;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getMib() {
        return mib;
    }

    public void setMib(String mib) {
        this.mib = mib;
    }

    public String getUlicaIBroj() {
        return ulicaIBroj;
    }

    public void setUlicaIBroj(String ulicaIBroj) {
        this.ulicaIBroj = ulicaIBroj;
    }

    public int getKid() {
        return kid;
    }

    public void setKid(int kid) {
        this.kid = kid;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    @Override
    public String vratiNazivTabele() {

        return " kupac ";
    }

    @Override
    public String vratiNaziveAtributa() {

        return " kupid, naziv, pib, mib, ulicaibroj, kid, mid ";

    }

    @Override
    public String vratiVrednostiAtributa() {

        return " '" + kupid + "', '" + naziv + "', '" + pib + "', '" + mib + "', '" + ulicaIBroj + "', '" + kid + "', '" + mid + "' ";

    }

    @Override
    public String vratiJoinUpit() {

        return " SELECT k.kupid, k.naziv, k.pib, k.mib, k.ulicaibroj, k.kid, k.mid, kom.ime AS komime, kom.prezime AS komprezime, m.naziv AS mnaziv, m.ptt FROM kupac k JOIN komercijalista kom ON (k.kid = kom.kid) JOIN mesto m ON (k.mid = m.mid) ";

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

            lista.add(kupac);

        }

        return lista;

    }

    public Komercijalista getKomercijalista() {
        return komercijalista;
    }

    public void setKomercijalista(Komercijalista komercijalista) {
        this.komercijalista = komercijalista;
    }

    @Override
    public String vratiKljuc() {

        return " kupid ";

    }

    @Override
    public String vratiVrednostKljuca() {

        return " '" + kupid + "' ";

    }

    @Override
    public String vratiUpdateAtributa() {

        return " naziv = '" + naziv + "', pib = '" + pib + "', mib = '" + mib + "', ulicaibroj = '" + ulicaIBroj + "', kid = '" + kid + "', mid = '" + mid + "' ";

    }

    @Override
    public String toString() {

        return naziv;

    }

    @Override
    public boolean equals(Object obj) {

        Kupac k = (Kupac) obj;

        if (this.getKupid() == k.getKupid()) {
            return true;
        }
        return false;
    }

    @Override
    public String napraviFilter(String kriterijum) {

        return " k.naziv LIKE '%" + kriterijum + "%' OR k.mib LIKE '%" + kriterijum + "%' OR k.pib LIKE '%" + kriterijum + "%' ";
    
    }

}
