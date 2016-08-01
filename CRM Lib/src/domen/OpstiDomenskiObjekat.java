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
 * @author Luka Vazic
 */
public interface OpstiDomenskiObjekat {
    
    
    public String vratiNazivTabele();
    
    public String vratiNaziveAtributa();
    
    public String vratiVrednostiAtributa();
    
    public String vratiJoinUpit();
    
    public String vratiKljuc();
    
    public String vratiVrednostKljuca();
    
    public String vratiUpdateAtributa();
    
    public List<OpstiDomenskiObjekat> napuni(ResultSet rs) throws Exception;
    
    
     
}
