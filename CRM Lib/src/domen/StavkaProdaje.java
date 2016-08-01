/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author lvazi_000
 */
public class StavkaProdaje implements OpstiDomenskiObjekat, Serializable {

    private int pid;
    private int spid;
    private int kolicina;
    private double vrednost;
    private int prid;

    private Prodaja prodaja;
    private Proizvod proizvod;

    public StavkaProdaje() {
    }

    public StavkaProdaje(int pid, int spid, int kolicina, double vrednost, int prid) {
        this.pid = pid;
        this.spid = spid;
        this.kolicina = kolicina;
        this.vrednost = vrednost;
        this.prid = prid;
    }

    public StavkaProdaje(int pid, int spid, int kolicina, double vrednost, int prid, Prodaja prodaja, Proizvod proizvod) {
        this.pid = pid;
        this.spid = spid;
        this.kolicina = kolicina;
        this.vrednost = vrednost;
        this.prid = prid;
        this.prodaja = prodaja;
        this.proizvod = proizvod;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getSpid() {
        return spid;
    }

    public void setSpid(int spid) {
        this.spid = spid;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getVrednost() {
        return vrednost;
    }

    public void setVrednost(double vrednost) {
        this.vrednost = vrednost;
    }

    public int getPrid() {
        return prid;
    }

    public void setPrid(int prid) {
        this.prid = prid;
    }

    public Prodaja getProdaja() {
        return prodaja;
    }

    public void setProdaja(Prodaja prodaja) {
        this.prodaja = prodaja;
    }

    @Override
    public String vratiNazivTabele() {

        return " stavkaprodaje ";

    }

    @Override
    public String vratiNaziveAtributa() {

        return " pid, spid, kolicina, vrednost, prid ";

    }

    @Override
    public String vratiVrednostiAtributa() {

        return " '" + pid + "', '" + spid + "', '" + kolicina + "', '" + vrednost + "', '" + proizvod.getPrid() + "' ";

    }

    @Override
    public String vratiJoinUpit() {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public List<OpstiDomenskiObjekat> napuni(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
