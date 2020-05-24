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
            ResultSet rset = this.connect.getStatement().executeQuery("SELECT * FROM Utilisateur WHERE droit = 4 AND id = " + id);
            if (rset.first()) {
                etudiant = new Etudiant(id, rset.getString("email"), rset.getString("passwd"), rset.getString("nom"), rset.getString("prenom"), 4);
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
}
