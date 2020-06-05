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

    public EnseignantDAO(Connexion conn) throws SQLException, ClassNotFoundException {
        super(conn);
    }

    public boolean create(Enseignant enseignant) {
        try {
            this.connect.getStatement().executeUpdate("WHERE [NOT] EXISTS (INSERT INTO Enseignant(ID_UTILISATEUR,ID_COURS) VALUES ((SELECT id FROM Utilisateur WHERE id =" + enseignant.getId() + ")," + enseignant.getIdCours() + "));");
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
        Enseignant enseignant = null;

        try {
            ResultSet rset = this.connect.getStatement().executeQuery("SELECT * FROM Utilisateur WHERE id = " + id);
            if (rset.first()) {
                String nom = rset.getString("Nom");
                String prenom = rset.getString("Prenom");
                String email = rset.getString("Email");
                String passwd = rset.getString("Passwd");

                ResultSet rset2 = null;
                rset2 = this.connect.getStatement().executeQuery("SELECT * FROM Enseignant where id_utilisateur=" + id);
                if (rset2.first()) {
                    enseignant = new Enseignant(id, nom, prenom, email, passwd, rset2.getInt("id_cours"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enseignant;
    }

    public ArrayList<Enseignant> findEnseignant(String login) throws SQLException {
        Enseignant x = null;
        System.out.println("login : " + login);
        ArrayList<Enseignant> enstemp = new ArrayList<>();
        ArrayList<Enseignant> ens = new ArrayList<>();
        ResultSet rset = null;

        try {
            rset = this.connect.getStatement().executeQuery("SELECT * FROM Enseignant");
            while (rset.next()) {
                x = new Enseignant(rset.getInt("id_utilisateur"), rset.getInt("id_cours"));
                enstemp.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Enseignant e : enstemp) {
            try {
                ResultSet rset1 = this.connect.getStatement().executeQuery("SELECT * FROM Utilisateur WHERE droit = 3");
                while (rset1.next()) {
                    if (e.getId() == rset1.getInt("id")) {
                        ens.add(e);
                    }
                }
            } catch (SQLException err) {
                err.printStackTrace();
            }
        }
        return ens;
    }

    public Enseignant find(String login) throws SQLException {
        Enseignant enseignant = null;
        ArrayList<Enseignant> ens = findEnseignant(login);

        for (Enseignant e : ens) {
            if (e.getEmail() == login) {
                return e;
            }
        }
        return enseignant;
    }

    public ArrayList<Enseignant> getAllTeachers() throws Exception {
        ArrayList<Enseignant> listTemp = new ArrayList<>();
        ArrayList<Enseignant> list = new ArrayList<>();
        Statement myStatement = null;
        ResultSet rset = null;
        try {
            rset = this.connect.getStatement().executeQuery("SELECT * FROM Utilisateur where droit=3");
            while (rset.next()) {
                int id = rset.getInt("id");
                String nom = rset.getString("Nom");
                String prenom = rset.getString("Prenom");
                String email = rset.getString("Email");
                String passwd = rset.getString("Passwd");
                Enseignant tempTeacher = new Enseignant(id, email, passwd, nom, prenom);
                listTemp.add(tempTeacher);
            }

            for (Enseignant e : listTemp) {
                ResultSet rset2 = null;
                rset2 = this.connect.getStatement().executeQuery("SELECT id_cours FROM Enseignant where id_utilisateur=" + e.getId());
                if (rset2.first()) {
                    Enseignant tempTeacher = new Enseignant(e.getId(), e.getEmail(), e.getPasswd(), e.getNom(), e.getPrenom(), rset2.getInt("ID_COURS"));
                    list.add(tempTeacher);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(myStatement, rset);
            return list;
        }
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
