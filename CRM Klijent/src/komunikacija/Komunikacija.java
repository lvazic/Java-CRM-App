/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.KlijentTransferObjekat;
import transfer.ServerTransferObjekat;

/**
 *
 * @author student1
 */
public class Komunikacija {
    private Socket soket;
    private static Komunikacija instance;

    public Komunikacija() throws IOException {
        soket = new Socket("127.0.0.1", 9000);
    }
    
    public static Komunikacija getInstance() throws IOException {
       if (instance == null) {
           instance = new Komunikacija();
       } 
       return instance;
    }
    
    public void posaljiZahtev(KlijentTransferObjekat kto) throws IOException {
        ObjectOutputStream outSoket = new ObjectOutputStream(soket.getOutputStream());
        outSoket.writeObject(kto);
    }
    
    public ServerTransferObjekat procitajOdgovor() throws IOException, ClassNotFoundException {
        ObjectInputStream inSoket = new ObjectInputStream(soket.getInputStream());
        return (ServerTransferObjekat) inSoket.readObject();
    }
    
    
    
    
}
