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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apple
 */
public class EtudiantDAO extends DAO<Etudiant> {

    // Constructeur
    public EtudiantDAO(Connexion conn) {
        super(conn);
    }

    // Methodes
    public boolean create(Etudiant etudiant) {
        try {
            this.connect.getStatement().executeUpdate("INSERT INTO Etudiant(ID_UTILISATEUR,NUMERO,ID_GROUPE) VALUES ((SELECT id FROM Utilisateur WHERE id =" + etudiant.getId() + "),'" + etudiant.getNumero() + "'," + etudiant.getGroupe() + ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Etudiant etudiant) {
        try {
            this.connect.getStatement().executeUpdate("DELETE FROM Etudiant WHERE id =" + etudiant.getId() + ");");
            this.connect.getStatement().executeUpdate("DELETE FROM Utilisateur WHERE id =" + etudiant.getId() + ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Etudiant etudiant) {
        try {
            this.connect.getStatement().executeUpdate("UPDATE Etudiant SET numero ='" + etudiant.getNumero() + "' WHERE id =" + etudiant.getId() + ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Etudiant find(int id) {
        Etudiant etudiant = new Etudiant();

        try {
            ResultSet rset = this.connect.getStatement().executeQuery("SELECT * FROM Etudiant WHERE id = " + id);
            if (rset.first()) {
                etudiant = convertRowToStudent(rset);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiant;
    }

    public void display(Etudiant etudiant) {
        if (etudiant.getId() != 0) {
            System.out.println("Nom : " + etudiant.getNom() + " - Prenom : " + etudiant.getPrenom() + " - E-mail : " + etudiant.getEmail() + " - Mot de passe : " + etudiant.getPasswd());
        }
    }

    public ArrayList<Etudiant> getAllStudents() throws Exception {
        ArrayList<Etudiant> list = new ArrayList<Etudiant>();
        Statement myStatement = null;
        ResultSet rset = null;
        try {
            rset = this.connect.getStatement().executeQuery("SELECT * FROM Utilisateur where droit=4");
            while (rset.next()) {
                Etudiant tempStudent = convertRowToStudent(rset);
                list.add(tempStudent);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        finally {
            close(myStatement, rset);
            return list;
        }
    }

    private Etudiant convertRowToStudent(ResultSet myResult) throws SQLException {
        int id = myResult.getInt("ID");
        String nom = myResult.getString("Nom");
        String prenom = myResult.getString("Prenom");
        String email = myResult.getString("Email");
        String passwd = myResult.getString("Passwd");
        Etudiant tempStudent = new Etudiant(id, email, passwd, nom, prenom);
        return tempStudent;
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
}
