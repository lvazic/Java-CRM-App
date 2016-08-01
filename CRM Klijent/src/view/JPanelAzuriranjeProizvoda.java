/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import domen.Proizvod;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import logic.Kontroler;
import transfer.KlijentTransferObjekat;
import transfer.ServerTransferObjekat;

/**
 *
 * @author lvazi_000
 */
public class JPanelAzuriranjeProizvoda extends javax.swing.JPanel {

    Proizvod p;

    /**
     * Creates new form JPanelAzuriranjeProizvoda
     */
    public JPanelAzuriranjeProizvoda(Proizvod p) {
        initComponents();
        try {
            this.p = Kontroler.getInstance().ucitajProizvod(p);

            jTextFieldNaziv.setText(p.getNaziv());
            jTextFieldCena.setText(p.getCena() + "");
            JOptionPane.showMessageDialog(this, "Sistem je prikazao proizvod", "Greška", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da nađe izabrani proizvod!", "Greška", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            this.getTopLevelAncestor().setVisible(false);

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldNaziv = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldCena = new javax.swing.JTextField();
        jButtonIzmeniProizvod = new javax.swing.JButton();

        jLabel1.setText("Naziv:");

        jLabel2.setText("Cena:");

        jButtonIzmeniProizvod.setText("Izmeni proizvod");
        jButtonIzmeniProizvod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIzmeniProizvodActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCena))
                    .addComponent(jButtonIzmeniProizvod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldCena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonIzmeniProizvod)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonIzmeniProizvodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIzmeniProizvodActionPerformed

        try {
            double cena = Double.parseDouble(jTextFieldCena.getText());

            p.setCena(cena);
            p.setNaziv(jTextFieldNaziv.getText());

            Kontroler.getInstance().azurirajProizvod(p);
            JOptionPane.showMessageDialog(this, "Sistem je zapamtio proizvod", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(this, "Neispravna cena!", "Greška", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da zapamti proizvod", "Greška", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_jButtonIzmeniProizvodActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonIzmeniProizvod;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextFieldCena;
    private javax.swing.JTextField jTextFieldNaziv;
    // End of variables declaration//GEN-END:variables
}
