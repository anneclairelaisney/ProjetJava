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
public class EnseignantDAO extends DAO<Enseignant> {

    public EnseignantDAO(Connexion conn) {
        super(conn);
    }

    public boolean create(Enseignant enseignant) {
        try {
            this.connect.getStatement().executeUpdate("INSERT INTO Enseignant(ID_UTILISATEUR,ID_COURS) VALUES ((SELECT id FROM Utilisateur WHERE id =" + enseignant.getId() + ")," + enseignant.getIdCours() + ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Enseignant enseignant) {
        try {
            this.connect.getStatement().executeUpdate("DELETE FROM Enseignant WHERE id =" + enseignant.getId() + ");");
            this.connect.getStatement().executeUpdate("DELETE FROM Utilisateur WHERE id =" + enseignant.getId() + ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Enseignant enseignant) {
        return false;
    }

    public Enseignant find(int id) {
        Enseignant enseignant = new Enseignant();

        try {
            ResultSet rset = this.connect.getStatement().executeQuery("SELECT * FROM Utilisateur WHERE droit = 3 AND id = " + id);
            if (rset.first()) {
                enseignant = new Enseignant(id, rset.getString("email"), rset.getString("passwd"), rset.getString("nom"), rset.getString("prenom"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enseignant;
    }

    public ArrayList<Enseignant> getAllTeachers() throws Exception {
        ArrayList<Enseignant> list = new ArrayList<Enseignant>();
        Statement myStatement = null;
        ResultSet rset = null;
        try {
            rset = this.connect.getStatement().executeQuery("SELECT * FROM Utilisateur where droit=3");
            while (rset.next()) {
                Enseignant tempTeacher = convertRowToTeacher(rset);
                list.add(tempTeacher);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(myStatement, rset);
            return list;
        }
    }

    private Enseignant convertRowToTeacher(ResultSet myResult) throws SQLException {
        int id = myResult.getInt("ID");
        String nom = myResult.getString("Nom");
        String prenom = myResult.getString("Prenom");
        String email = myResult.getString("Email");
        String passwd = myResult.getString("Passwd");
        Enseignant tempTeacher = new Enseignant(id, email, passwd, nom, prenom);
        return tempTeacher;
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

    public void display(Enseignant enseignant) {
        if (enseignant.getId() != 0) {
            System.out.println("Nom : " + enseignant.getNom() + " - Prenom : " + enseignant.getPrenom() + " - E-mail : " + enseignant.getEmail() + " - Mot de passe : " + enseignant.getPasswd());
        }
    }
}
