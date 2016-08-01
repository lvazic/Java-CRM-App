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
public class Komercijalista implements OpstiDomenskiObjekat, Serializable {
    
    
    private int kid;
    private String ime;
    private String prezime;
    private String username;
    private String password;

    public Komercijalista() {
    }

    public Komercijalista(int kid, String ime, String prezime, String username, String password) {
        this.kid = kid;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
    }

    public int getKid() {
        return kid;
    }

    public void setKid(int kid) {
        this.kid = kid;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String vratiNazivTabele() {

        return " komercijalista ";
    
    }

    @Override
    public String vratiNaziveAtributa() {

        return " kid, ime, prezime, username, pass ";
    
    }

    @Override
    public String vratiVrednostiAtributa() {

        return " '" + kid + "' ,'" + ime + "' ,'" + prezime + "' ,'" + username + "', '" + password + "' ";
    
    }

    @Override
    public List<OpstiDomenskiObjekat> napuni(ResultSet rs) throws Exception {

        List<OpstiDomenskiObjekat> lista=new ArrayList<>();
        while(rs.next()){
            int kid = rs.getInt("kid");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String username = rs.getString("username");
            String password = rs.getString("pass");
            
            Komercijalista k = new Komercijalista(kid, ime, prezime, username, password);
            
            lista.add(k);
        }
        
        return lista;
    
    }

    @Override
    public String vratiJoinUpit() {
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

    @Override
    public String toString() {

        return ime + " " + prezime;
    
    }
    
    
    
}
