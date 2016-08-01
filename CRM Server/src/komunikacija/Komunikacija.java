/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import domen.OpstiDomenskiObjekat;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import konstante.Konstante;
import logic.Kontroler;
import transfer.KlijentTransferObjekat;
import transfer.ServerTransferObjekat;

/**
 *
 * @author student1
 */
public class Komunikacija extends Thread {

    ServerSocket serverSoket;
    boolean status;

    public Komunikacija() throws IOException {
        serverSoket = new ServerSocket(9000);
        System.out.println("Soket je otvoren");
        status = true;
        start();

    }

    @Override
    public void run() {

        while (status) {
            try {
                Socket soket = serverSoket.accept();
                NitKlijent nitKlijent = new NitKlijent(soket);
                System.out.println("Klijent se povezao.");
            } catch (IOException ex) {
                Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
    }

    

    public void zaustaviServer() {
        status = false;
        System.out.println("Zaustavljam server.");
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
    
}
