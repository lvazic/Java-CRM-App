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
 * @author Luka Vazic
 */
public class Mesto implements OpstiDomenskiObjekat, Serializable {
    
    private int mid;
    private String naziv;
    private int ptt;

    public Mesto() {
    }
    
    

    public Mesto(int mid, String naziv, int ptt) {
        this.mid = mid;
        this.naziv = naziv;
        this.ptt = ptt;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getPtt() {
        return ptt;
    }

    public void setPtt(int ptt) {
        this.ptt = ptt;
    }

    @Override
    public String vratiNazivTabele() {

        return " mesto ";
    
    }

    @Override
    public String vratiNaziveAtributa() {

        return " mid, naziv, ptt ";
    }

    @Override
    public String vratiVrednostiAtributa() {

        return " '" + mid + "' ,'" + naziv +"' ,'" + ptt + "' ";
        
    }

    @Override
    public List<OpstiDomenskiObjekat> napuni(ResultSet rs) throws Exception {

        
        List<OpstiDomenskiObjekat> lista=new ArrayList<>();
        while(rs.next()){
            int mid = rs.getInt("mid");
            String naziv = rs.getString("naziv");
            int pttBroj = rs.getInt("ptt");
            Mesto m = new Mesto(mid, naziv, pttBroj);

            lista.add(m);
        }
        
        return lista;
        
    
    }

    @Override
    public String vratiJoinUpit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiKljuc() {

        return " mid ";
    }

    @Override
    public String vratiVrednostKljuca() {

        return " '" + mid + "' ";
    
    }

    @Override
    public String vratiUpdateAtributa() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {

        return ptt + " " + naziv;
    
    }

    @Override
    public boolean equals(Object obj) {

        if (this.ptt == ((Mesto)obj).getPtt()) return true;
        return false;
     
    }
    
    
    
}
