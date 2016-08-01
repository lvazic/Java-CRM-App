/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lvazi_000
 */
public class Prodaja implements OpstiDomenskiObjekat, Serializable {
    
    private int pid;
    private Date datum;
    private double vrednost;
    private int kupid;
    
    private Kupac kupac;
    
    private List<StavkaProdaje> stavke;

    public Prodaja() {
        
        stavke = new ArrayList<>();
        
    }

    public Prodaja(int pid, Date datum, double vrednost, int kupid) {
        this.pid = pid;
        this.datum = datum;
        this.vrednost = vrednost;
        this.kupid = kupid;
    }
    
    
    
    

    public Prodaja(int pid, Date datum, double vrednost, int kupid, Kupac kupac) {
        this.pid = pid;
        this.datum = datum;
        this.vrednost = vrednost;
        this.kupid = kupid;
        this.kupac = kupac;
        stavke = new ArrayList<>();
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public double getVrednost() {
        return vrednost;
    }

    public void setVrednost(double vrednost) {
        this.vrednost = vrednost;
    }

    public int getKupid() {
        return kupid;
    }

    public void setKupid(int kupid) {
        this.kupid = kupid;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public List<StavkaProdaje> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaProdaje> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String vratiNazivTabele() {

    
        return " prodaja ";
    
    }

    @Override
    public String vratiNaziveAtributa() {

        return " datum, vrednost, kupid ";
    
    }

    @Override
    public String vratiVrednostiAtributa() {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dat = df.format(datum);
        
        return " '" + dat + "', '" + vrednost + "', '" + kupid + "' ";
        
    
    }

    @Override
    public String vratiJoinUpit() {

       return " SELECT p.pid, p.datum, p.vrednost, p.kupid, k.naziv, k.pib, k.mib, k.ulicaibroj, k.kid, k.mid, kom.ime, kom.prezime, m.mid, m.naziv AS mnaziv, m.ptt FROM prodaja p JOIN kupac k ON (p.kupid = k.kupid) JOIN komercijalista kom ON (k.kid = kom.kid) JOIN mesto m ON (k.mid = m.mid) "; 
    
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
            
            int pid = rs.getInt("pid");
            Date datum = rs.getDate("datum");
            double vrednost = rs.getDouble("vrednost");
            
            Prodaja p = new Prodaja(pid, datum, vrednost, kupid, kupac);
            lista.add(p);
            
        }

        return lista;
    
    
    
    
    }

    @Override
    public String vratiKljuc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiVrednostKljuca() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiUpdateAtributa() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
