/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import domen.Kupac;
import domen.Mesto;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import logic.Kontroler;
import transfer.KlijentTransferObjekat;
import transfer.ServerTransferObjekat;

/**
 *
 * @author lvazi_000
 */
public class JPanelUnosKupca extends javax.swing.JPanel {

    private Kupac k;

    private boolean izmena = false;

    /**
     * Creates new form JPanelUnosKupca
     */
    public JPanelUnosKupca() {
        initComponents();
        pripremiFormu();
        napraviKupca();
    }

    public JPanelUnosKupca(Kupac k) {
        try {
            initComponents();

            this.k = Kontroler.getInstance().ucitajKupca(k);
            pripremiFormu();
            jTextFieldNaziv.setText(k.getNaziv());
            jTextFieldPIB.setText(k.getPib());
            jTextFieldMIB.setText(k.getMib());
            jTextFieldUlicaIBroj.setText(k.getUlicaIBroj());

            jComboBoxMesto.setSelectedItem(k.getMesto());
            izmena = true;
            jButtonDodajKupca.setText("Izmeni kupca");
            JOptionPane.showMessageDialog(this, "Sistem je pronašao podatke o izabranom kupcu", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da nađe izabranog kupca", "Greška", JOptionPane.ERROR_MESSAGE);

            Logger.getLogger(JPanelUnosKupca.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel2 = new javax.swing.JPanel();
        jLabelNaziv = new javax.swing.JLabel();
        jTextFieldNaziv = new javax.swing.JTextField();
        jLabelPIB = new javax.swing.JLabel();
        jTextFieldPIB = new javax.swing.JTextField();
        jTextFieldMIB = new javax.swing.JTextField();
        jLabelMIB = new javax.swing.JLabel();
        jTextFieldUlicaIBroj = new javax.swing.JTextField();
        jLabelUlicaIBroj = new javax.swing.JLabel();
        jComboBoxMesto = new javax.swing.JComboBox();
        jLabelMesto = new javax.swing.JLabel();
        jButtonDodajKupca = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelNaziv.setText("Naziv:");

        jLabelPIB.setText("PIB:");

        jLabelMIB.setText("MIB:");

        jLabelUlicaIBroj.setText("Ulica i broj:");

        jComboBoxMesto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabelMesto.setText("Mesto:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelPIB)
                    .addComponent(jLabelNaziv)
                    .addComponent(jLabelMIB)
                    .addComponent(jLabelUlicaIBroj)
                    .addComponent(jLabelMesto))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxMesto, 0, 252, Short.MAX_VALUE)
                    .addComponent(jTextFieldUlicaIBroj)
                    .addComponent(jTextFieldMIB)
                    .addComponent(jTextFieldNaziv)
                    .addComponent(jTextFieldPIB))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNaziv)
                    .addComponent(jTextFieldNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPIB)
                    .addComponent(jTextFieldPIB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldMIB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMIB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldUlicaIBroj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUlicaIBroj))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxMesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMesto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabelNaziv, jLabelPIB});

        jButtonDodajKupca.setText("Dodaj kupca");
        jButtonDodajKupca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDodajKupcaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonDodajKupca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonDodajKupca)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDodajKupcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDodajKupcaActionPerformed

        try {

            k.setNaziv(jTextFieldNaziv.getText());
            k.setPib(jTextFieldPIB.getText());
            k.setMib(jTextFieldMIB.getText());
            k.setUlicaIBroj(jTextFieldUlicaIBroj.getText());
            k.setKomercijalista(Kontroler.getInstance().getKomercijalista());
            k.setKid(k.getKomercijalista().getKid());
            k.setMesto((Mesto) jComboBoxMesto.getSelectedItem());
            k.setMid(k.getMesto().getMid());

            validacija.KupacValidator.validiraj(k);

            try {

                if (izmena) {
                    Kontroler.getInstance().azurirajKupca(k);
                } else {
                    Kontroler.getInstance().sacuvajKupca(k);
                }

                JOptionPane.showMessageDialog(this, "Sistem je zapamtio kupca.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {

                String poruka;
                if (izmena) {
                    poruka = "Sistem ne može da zapamti kupca.";
                } else {
                    poruka = "Sistem ne može da zapamti novog kupca.";
                }

                JOptionPane.showMessageDialog(this, poruka, "Greška", JOptionPane.ERROR_MESSAGE);

            }

        } catch (Exception ex) {

            Logger.getLogger(JPanelUnosKupca.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);

        }


    }//GEN-LAST:event_jButtonDodajKupcaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDodajKupca;
    private javax.swing.JComboBox jComboBoxMesto;
    private javax.swing.JLabel jLabelMIB;
    private javax.swing.JLabel jLabelMesto;
    private javax.swing.JLabel jLabelNaziv;
    private javax.swing.JLabel jLabelPIB;
    private javax.swing.JLabel jLabelUlicaIBroj;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextFieldMIB;
    private javax.swing.JTextField jTextFieldNaziv;
    private javax.swing.JTextField jTextFieldPIB;
    private javax.swing.JTextField jTextFieldUlicaIBroj;
    // End of variables declaration//GEN-END:variables

    private void pripremiFormu() {

        try {
            jComboBoxMesto.setModel(new DefaultComboBoxModel(Kontroler.getInstance().vratiMesta().toArray()));
        } catch (Exception ex) {
            Logger.getLogger(JPanelUnosKupca.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Nije moguće učitati listu mesta!", "Greška", JOptionPane.ERROR_MESSAGE);

        }

    }

    private void napraviKupca() {

        try {
            k = Kontroler.getInstance().napraviKupca();
            JOptionPane.showMessageDialog(this, "Sistem je napravio novog kupca.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Sistem ne može da kreira novog kupca.", "Greška", JOptionPane.ERROR_MESSAGE);

        }

    }
}
