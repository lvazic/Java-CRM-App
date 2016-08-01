/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;

/**
 *
 * @author lvazi_000
 */
public class UptimeNit extends Thread {

    private JLabel vreme;
    private boolean status;
    Date pocetnoVreme;

    public UptimeNit(JLabel vreme) {
        this.vreme = vreme;
        pocetnoVreme = new Date();
        status = true;
    }

    public void postaviVreme() {
        pocetnoVreme = new Date();

    }

    @Override
    public void run() {

        while (status) {
            Date trenutnoVreme = new Date();
            long razlika = trenutnoVreme.getTime() - pocetnoVreme.getTime();

            String razl = String.format("%d:%d:%d", TimeUnit.MILLISECONDS.toHours(razlika), TimeUnit.MILLISECONDS.toMinutes(razlika) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(razlika)), razlika / 1000 % 60);
            vreme.setText(razl);
        }

    }

    public void zaustaviVreme() {
        status = false;

    }

}
