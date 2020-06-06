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
public class GroupeDAO extends DAO<Groupe> {

    /**
     *
     * @param conn
     */
    public GroupeDAO(Connexion conn) {
        super(conn);
    }

    public boolean create(Groupe groupe) {
        try {
            this.connect.getStatement().executeUpdate("WHERE [NOT] EXISTS (INSERT INTO Groupe(ID,NOM,ID_PROMOTION) VALUES (" + groupe.getId() + ",'" + groupe.getNom() + "',(SELECT id FROM Promotion WHERE id =" + groupe.getIdPromotion() + ")));");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Groupe groupe) {
        try {
            this.connect.getStatement().executeUpdate("DELETE FROM Groupe WHERE id =" + groupe.getId() + ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Groupe groupe) {
        try {
            this.connect.getStatement().executeUpdate("UPDATE Groupe SET nom ='" + groupe.getNom() + "' WHERE id =" + groupe.getId() + ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Groupe find(int id) {
        Groupe groupe = new Groupe();

        try {
            ResultSet rset = this.connect.getStatement().executeQuery("SELECT * FROM Groupe WHERE id = " + id);
            if (rset.first()) {
                groupe = new Groupe(id, rset.getString("nom"), rset.getInt("id_promotion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupe;
    }
    
    /**
     *
     * @return ArrayList
     * @throws Exception
     */
    public ArrayList<Groupe> getAllGroupes() throws Exception {
        ArrayList<Groupe> list = new ArrayList<Groupe>();
        Statement myStatement = null;
        ResultSet rset = null;
        try {
            rset = this.connect.getStatement().executeQuery("SELECT * FROM Groupe");
            while (rset.next()) {
                Groupe tempGroupe = convertRowToGroupe(rset);
                list.add(tempGroupe);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(myStatement, rset);
            return list;
        }
    }

    private Groupe convertRowToGroupe(ResultSet myResult) throws SQLException {
        int id = myResult.getInt("ID");
        String nom = myResult.getString("Nom");
        int id_promotion = myResult.getInt("ID_Promotion");
        Groupe tempGroupe = new Groupe(id,nom,id_promotion);
        return tempGroupe;
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

    public void display(Groupe groupe) {
        try {
            if (groupe.getId() != 0) {
                ResultSet rset = this.connect.getStatement().executeQuery("SELECT nom FROM Groupe WHERE id = " + groupe.getIdPromotion());
                if (rset.first()) {
                    System.out.println("Nom : " + groupe.getNom() + " - Promotion : " + rset.getString("nom"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
