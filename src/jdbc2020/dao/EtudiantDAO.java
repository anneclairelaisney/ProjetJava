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
public class EtudiantDAO extends DAO<Etudiant> {

    // Constructeur
    public EtudiantDAO(Connexion conn) {
        super(conn);
    }

    // Methodes
    public boolean create(Etudiant etudiant) {
        try {
            this.connect.getStatement().executeUpdate("WHERE [NOT] EXISTS (INSERT INTO Etudiant(ID_UTILISATEUR,NUMERO,ID_GROUPE) VALUES ((SELECT id FROM Utilisateur WHERE id =" + etudiant.getId() + "),'" + etudiant.getNumero() + "'," + etudiant.getIdGroupe() + "));");
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
        Etudiant etudiant = null;

        try {
            ResultSet rset = this.connect.getStatement().executeQuery("SELECT * FROM Etudiant WHERE id_utilisateur = " + id);
            if (rset.first()) {
                etudiant = new Etudiant(id, rset.getInt("numero"), rset.getInt("id_groupe"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiant;
    }

    public void display(Etudiant etudiant) {
        if (etudiant.getId() != 0) {
            System.out.println("Nom : " + etudiant.getNom() + " - Prenom : " + etudiant.getPrenom() + " - E-mail : " + etudiant.getEmail() + " - Mot de passe : " + etudiant.getPasswd() + " - Numero : " + etudiant.getNumero() + " - Id Groupe : " + etudiant.getIdGroupe());
        }
    }

    public ArrayList<Etudiant> getAllStudents() throws Exception {
        ArrayList<Etudiant> listTemp = new ArrayList<>();
        ArrayList<Etudiant> list = new ArrayList<>();
        Statement myStatement = null;
        ResultSet rset = null;
        try {
            rset = this.connect.getStatement().executeQuery("SELECT * FROM Utilisateur where droit=4");
            while (rset.next()) {
                int id = rset.getInt("id");
                String nom = rset.getString("Nom");
                String prenom = rset.getString("Prenom");
                String email = rset.getString("Email");
                String passwd = rset.getString("Passwd");
                Etudiant tempStudent = new Etudiant(id, email, passwd, nom, prenom);
                listTemp.add(tempStudent);
            }
            close(myStatement, rset);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        ResultSet rset2 = null;
        try {
            for (Etudiant e : listTemp) {
                rset2 = this.connect.getStatement().executeQuery("SELECT * FROM Etudiant where id_utilisateur=" + e.getId());
                if (rset2.first()) {
                    Etudiant student = new Etudiant(e.getId(), e.getEmail(), e.getPasswd(), e.getNom(), e.getPrenom(), rset2.getInt("numero"), rset2.getInt("id_groupe"));
                    rset2.beforeFirst();
                    while (rset2.next()) {
                        list.add(student);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(myStatement, rset2);
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
}
