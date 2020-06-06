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
public class CoursDAO extends DAO<Cours> {

    /**
     *
     * @param conn
     */
    public CoursDAO(Connexion conn) {
        super(conn);
    }

    public boolean create(Cours cours) {
        try {
            this.connect.getStatement().executeUpdate("INSERT INTO Cours(ID,NOM) VALUES (" + cours.getId() + ",'" + cours.getNom() + ")");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }  
        return false;
    }

    public boolean delete(Cours cours) {
        try {
            this.connect.getStatement().executeUpdate("DELETE FROM Cours WHERE id =" + cours.getId() + ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }  
        return false;
    }

    public boolean update(Cours cours) {
        try {
            this.connect.getStatement().executeUpdate("UPDATE Cours SET nom ='" + cours.getNom() + ")");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }  
        return false;
    }

    public Cours find(int id) {
        Cours cours = new Cours();

        try {
            ResultSet rset = this.connect.getStatement().executeQuery("SELECT * FROM Cours WHERE id = " + id);
            if (rset.first()) {
                cours = new Cours(id, rset.getString("nom"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cours;
    }
    
    /**
     * Récupérer l'intégralité des cours de la BDD
     * @return ArrayList
     * @throws Exception
     */
    public ArrayList<Cours> getAllCours() throws Exception {
        ArrayList<Cours> list = new ArrayList<>();
        Statement myStatement = null;
        ResultSet rset = null;
        try {
            rset = this.connect.getStatement().executeQuery("SELECT * FROM Cours");
            while (rset.next()) {
                Cours tempCours = convertRowToCours(rset);
                list.add(tempCours);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(myStatement, rset);
            return list;
        }
    }

    private Cours convertRowToCours(ResultSet myResult) throws SQLException {
        int id = myResult.getInt("ID");
        String nom = myResult.getString("Nom");
        Cours tempCours = new Cours(id,nom);
        return tempCours;
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
    
    @Override
    public void display(Cours cours) {
        if (cours.getId() != 0) {
            System.out.println("Nom : " + cours.getNom());
        }
    }
}
