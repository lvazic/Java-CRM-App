/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DatabaseBroker;

/**
 *
 * @author student1
 */
public abstract class OpstaSistemskaOperacija {
    
    public final void izvrsenjeSO(Object obj) throws Exception {
        try {
            ucitajDriver();
            otvoriKonekciju();
            proveriPreduslov(obj);
            izvrsiKonkretnuOperaciju(obj);
            proveriPostuslov(obj);
            commitTransakcije();
        } catch (Exception ex) {
            rollbackTransakcije();
            throw ex;
        } finally {
            zatvoriKonekciju();
        }
        
    }

    private void ucitajDriver() throws Exception {
        DatabaseBroker.getInstance().ucitajDriver();
    }

    private void otvoriKonekciju() throws Exception {
        DatabaseBroker.getInstance().otvoriKonekciju();
    }

    // Specificno za svaku SO
    protected abstract void proveriPreduslov(Object obj) throws Exception;
    
    protected abstract void proveriPostuslov(Object obj) throws Exception;

    // Specificno za svaku SO
    protected abstract void izvrsiKonkretnuOperaciju(Object obj) throws Exception;

    private void commitTransakcije() throws Exception {
        DatabaseBroker.getInstance().commitTransakcije();
    }

    private void rollbackTransakcije() throws Exception {
        DatabaseBroker.getInstance().rollbackTransakcije();
    }

    private void zatvoriKonekciju() throws Exception {
        DatabaseBroker.getInstance().zatvoriKonekciju();
    }
    
}
