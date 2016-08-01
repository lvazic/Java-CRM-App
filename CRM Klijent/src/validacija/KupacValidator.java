/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validacija;

import domen.Kupac;

/**
 *
 * @author lvazi_000
 */
public class KupacValidator {
    
    public static void validiraj(Kupac k) throws Exception {
        
        String pib = k.getPib();
        if (pib.length() != 9) throw new Exception("PIB nije dobre dužine!");
        for (char c : pib.toCharArray()) {
            
            if (!Character.isDigit(c)) throw new Exception("PIB sadrži slova!");
            
        }
        
        String mib = k.getMib();
        
        if (mib.length() != 8) throw new Exception("MIB nije dobre dužine!");
        
        for (char c : mib.toCharArray()) {
            
            if (!Character.isDigit(c)) throw new Exception("MIB sadrži slova!");
            
        }
    }
    
}
