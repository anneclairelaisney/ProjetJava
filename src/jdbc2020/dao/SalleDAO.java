/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.dao;

import jdbc2020.controleur.*;
import jdbc2020.modele.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author apple
 */
public class SalleDAO extends DAO<Salle> {

    public SalleDAO(Connexion conn) {
        super(conn);
    }

    public boolean create(Salle salle) {
        try {
            this.connect.getStatement().executeUpdate("INSERT INTO Salle(ID,NOM,CAPACITE,ID_SITE) VALUES (" + salle.getId() + ",'" + salle.getNom() + "," + salle.getCapacite() + ",(SELECT id FROM Site WHERE id =" + salle.getIdSite() + "));");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }  
        return false;
    }

    public boolean delete(Salle salle) {
        try {
            this.connect.getStatement().executeUpdate("DELETE FROM Salle WHERE id =" + salle.getId() + ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }  
        return false;
    }

    public boolean update(Salle salle) {
        try {
            this.connect.getStatement().executeUpdate("UPDATE Salle SET nom ='" + salle.getNom() +"', capacite = '" + salle.getCapacite() + "' WHERE id =" + salle.getId() + ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }  
        return false;
    }

    public Salle find(int id) {
        Salle salle = new Salle();

        try {
            ResultSet rset = this.connect.getStatement().executeQuery("SELECT * FROM Salle WHERE id = " + id);
            if (rset.first()) {
                salle = new Salle(id, rset.getString("nom"), rset.getInt("capacite"), rset.getInt("id_site"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salle;
    }
    
    public ArrayList<Salle> getAllSalles() throws Exception {
        ArrayList<Salle> list = new ArrayList<Salle>();
        Statement myStatement = null;
        ResultSet rset = null;
        try {
            rset = this.connect.getStatement().executeQuery("SELECT * FROM Salle");
            while (rset.next()) {
                Salle tempSalle = convertRowToSalle(rset);
                list.add(tempSalle);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(myStatement, rset);
            return list;
        }
    }

    private Salle convertRowToSalle(ResultSet myResult) throws SQLException {
        int id = myResult.getInt("ID");
        String nom = myResult.getString("Nom");
        int capacite = myResult.getInt("Capacite");
        int id_site = myResult.getInt("ID_Site");
        Salle tempSalle = new Salle(id,nom,capacite,id_site);
        return tempSalle;
    }

    private static void close(Connexion myConnection, Statement myStatement, ResultSet myResult) throws SQLException {
        if (myResult != null) {
            myResult.close();
        }
        if (myStatement != null) {
        }
        if (myConnection != null) {
        }
    }

    private void close(Statement myStmt, ResultSet myRs) throws SQLException {
        close(null, myStmt, myRs);
    }
    
    public void display(Salle salle) {
        if (salle.getId() != 0) {
            System.out.println("Nom : " + salle.getNom());
        }
    }
}
