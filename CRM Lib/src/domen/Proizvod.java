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
public class Proizvod implements OpstiDomenskiObjekat, Serializable, Filter {

    private int prid;
    private String naziv;
    private double cena;

    public Proizvod() {
    }

    public Proizvod(int prid, String naziv, double cena) {
        this.prid = prid;
        this.naziv = naziv;
        this.cena = cena;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getPrid() {
        return prid;
    }

    public void setPrid(int prid) {
        this.prid = prid;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String vratiNazivTabele() {

        return " proizvod ";

    }

    @Override
    public String vratiNaziveAtributa() {

        return " prid, naziv, cena ";
    }

    @Override
    public String vratiVrednostiAtributa() {

        return " '" + prid + "', '" + naziv + "' ,'" + cena + "' ";

    }

    @Override
    public String vratiJoinUpit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OpstiDomenskiObjekat> napuni(ResultSet rs) throws Exception {

        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int pr = rs.getInt("prid");
            String naziv = rs.getString("naziv");
            double c = rs.getDouble("cena");

            Proizvod p = new Proizvod(pr, naziv, c);

            lista.add(p);
        }

        return lista;

    }

    @Override
    public String vratiKljuc() {

        return " prid ";

    }

    @Override
    public String vratiVrednostKljuca() {

        return " '" + prid + "' ";

    }

    @Override
    public String vratiUpdateAtributa() {

        return " naziv = '" + naziv + "', cena = '" + cena + "' ";

    }

    @Override
    public String toString() {

        return naziv;

    }

    @Override
    public String napraviFilter(String kriterijum) {

        return " naziv LIKE '%" + kriterijum + "%' OR cena <= '" + kriterijum +"' ";

    }

}
