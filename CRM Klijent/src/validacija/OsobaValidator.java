/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validacija;

import domen.KontaktOsoba;

/**
 *
 * @author lvazi_000
 */
public class OsobaValidator {
    
    
    public static void validiraj(KontaktOsoba k) throws Exception {
        
        if (k.getIme().length() == 0) throw new Exception("Niste uneli ime!");
        if (k.getPrezime().length() == 0) throw new Exception("Niste uneli prezime!");
        if (k.getTelefon().length() == 0) throw new Exception("Niste uneli telefon!");
        
        String email = k.getEmail();
        
        int br = 0;
        for (char c : email.toCharArray()) {
            
            if (c == '@') br++;
        }
        
        if (br != 1 && email.length() != 0) throw new Exception("Email nije ispravan!");
    }
    
    
}
