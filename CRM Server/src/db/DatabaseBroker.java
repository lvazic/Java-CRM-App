/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.OpstiDomenskiObjekat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author student1
 */
public class DatabaseBroker {

    private Connection connection;
    private static DatabaseBroker instance;

    private DatabaseBroker() {

    }

    public static DatabaseBroker getInstance() {
        if (instance == null) {
            instance = new DatabaseBroker();
        }
        return instance;
    }

    public void ucitajDriver() throws Exception {
        try {
            Class.forName(Util.getInstance().get("driver"));
        } catch (ClassNotFoundException ex) {
            throw new Exception("Neuspesno ucitavanje drivera!", ex);
        }
    }

    public void otvoriKonekciju() throws Exception {
        try {
            connection = DriverManager.getConnection(Util.getInstance().get("url"), Util.getInstance().get("user"), Util.getInstance().get("password"));
            connection.setAutoCommit(false);
            // Zahteva se eksplicitna potvrda transakcije; podrazumevana vrednost je true
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Neuspesno uspostavanje konekcije!", ex);
        }

    }

    public void commitTransakcije() throws Exception {
        try {
            connection.commit();
        } catch (SQLException ex) {
            throw new Exception("Neuspesan commit transakcije!", ex);
        }
    }

    public void rollbackTransakcije() throws Exception {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            throw new Exception("Neuspesan rollback transakcije!", ex);
        }
    }

    public void zatvoriKonekciju() throws Exception {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new Exception("Neuspesno zatvaranje konekcije!", ex);
        }
    }

    public List<OpstiDomenskiObjekat> vratiListu(OpstiDomenskiObjekat odo) throws Exception {
        try {
            List<OpstiDomenskiObjekat> list;
            String sql = "SELECT * FROM " + odo.vratiNazivTabele();
            System.out.println(sql);
            Statement sqlStatement = connection.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            list = odo.napuni(rs);
            rs.close();
            sqlStatement.close();
            return list;
        } catch (SQLException ex) {
            throw new Exception("Neuspesno ucitavanje objekata!", ex);
        }
    }

    public List<OpstiDomenskiObjekat> vratiFiltriranuJoinListu(OpstiDomenskiObjekat odo, String kriterijum) throws Exception {
        try {
            List<OpstiDomenskiObjekat> list;
            String sql = odo.vratiJoinUpit() + " WHERE " + kriterijum;
            System.out.println(sql);
            Statement sqlStatement = connection.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            list = odo.napuni(rs);
            rs.close();
            sqlStatement.close();
            return list;
        } catch (SQLException ex) {
            throw new Exception("Neuspesno ucitavanje objekata!", ex);
        }
    }

    public List<OpstiDomenskiObjekat> vratiJoinListu(OpstiDomenskiObjekat odo) throws Exception {
        try {
            List<OpstiDomenskiObjekat> list;
            String sql = odo.vratiJoinUpit();
            System.out.println(sql);
            Statement sqlStatement = connection.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            list = odo.napuni(rs);
            rs.close();
            sqlStatement.close();
            return list;
        } catch (SQLException ex) {
            throw new Exception("Neuspesno ucitavanje objekata!", ex);
        }
    }

    public List<OpstiDomenskiObjekat> vratiFiltriranuListu(OpstiDomenskiObjekat odo, String kriterijum) throws Exception {
        try {
            List<OpstiDomenskiObjekat> list;
            String sql = "SELECT * FROM " + odo.vratiNazivTabele() + " WHERE " + kriterijum;
            System.out.println(sql);
            Statement sqlStatement = connection.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            list = odo.napuni(rs);
            rs.close();
            sqlStatement.close();
            return list;
        } catch (SQLException ex) {
            throw new Exception("Neuspesno ucitavanje objekata!", ex);
        }
    }

    public int sacuvaj(OpstiDomenskiObjekat odo) throws Exception {
        try {
            String sql = "INSERT INTO " + odo.vratiNazivTabele() + " (" + odo.vratiNaziveAtributa() + ") VALUES (" + odo.vratiVrednostiAtributa() + ")";
            System.out.println(sql);
            Statement sqlStatement = connection.createStatement();
            sqlStatement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = sqlStatement.getGeneratedKeys();
            int kljuc = -1;
            while (rs.next()) {
                kljuc = rs.getInt(1);
            }

            sqlStatement.close();
            return kljuc;
        } catch (SQLException ex) {
            throw new Exception("Neuspesno cuvanje zapisa!", ex);
        }
    }

    public void azuriraj(OpstiDomenskiObjekat odo) throws Exception {
        try {
            String sql = " UPDATE " + odo.vratiNazivTabele() + " SET " + odo.vratiUpdateAtributa() + " WHERE " + odo.vratiKljuc() + " = " + odo.vratiVrednostKljuca() + " ";
            System.out.println(sql);
            Statement sqlStatement = connection.createStatement();
            sqlStatement.executeUpdate(sql);
            sqlStatement.close();
        } catch (SQLException ex) {
            throw new Exception("Neuspesno cuvanje objekata!", ex);
        }
    }

    public void obrisi(OpstiDomenskiObjekat odo) throws Exception {
        try {
            String sql = " DELETE FROM " + odo.vratiNazivTabele() + " WHERE " + odo.vratiKljuc() + " = " + odo.vratiVrednostKljuca() + " ";
            System.out.println(sql);
            Statement sqlStatement = connection.createStatement();
            sqlStatement.executeUpdate(sql);
            sqlStatement.close();
        } catch (SQLException ex) {
            throw new Exception("Neuspesno brisanje objekta!", ex);
        }
    }

    public OpstiDomenskiObjekat vratiObjekat(OpstiDomenskiObjekat odo) throws Exception {
        try {
            List<OpstiDomenskiObjekat> lista;
            String sql = "SELECT * FROM " + odo.vratiNazivTabele() + " WHERE " + odo.vratiKljuc() + " = " + odo.vratiVrednostKljuca() + " ";
            System.out.println(sql);
            Statement sqlStatement = connection.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            lista = odo.napuni(rs);
            rs.close();
            sqlStatement.close();
            return lista.get(0);
        } catch (SQLException ex) {
            throw new Exception("Neuspesno ucitavanje objekta!", ex);
        }
    }

    public OpstiDomenskiObjekat vratiJoinObjekat(OpstiDomenskiObjekat odo) throws Exception {
        try {
            List<OpstiDomenskiObjekat> list;
            String sql = odo.vratiJoinUpit() + " WHERE " + odo.vratiKljuc() + " = " + odo.vratiVrednostKljuca() + " ";
            System.out.println(sql);
            Statement sqlStatement = connection.createStatement();
            ResultSet rs = sqlStatement.executeQuery(sql);
            list = odo.napuni(rs);
            rs.close();
            sqlStatement.close();
            return list.get(0);
        } catch (SQLException ex) {
            throw new Exception("Neuspesno ucitavanje objekata!", ex);
        }
    }

}
