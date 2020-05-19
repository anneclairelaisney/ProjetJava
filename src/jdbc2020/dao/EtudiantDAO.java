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

/**
 *
 * @author apple
 */
public class EtudiantDAO extends DAO<Etudiant> {

    public EtudiantDAO(Connexion conn) {
        super(conn);
    }

    public boolean create(Etudiant etudiant) {
        return false;
    }

    public boolean delete(Etudiant etudiant) {
        return false;
    }

    public boolean update(Etudiant etudiant) {
        return false;
    }

    public Etudiant find(int id) {
        Etudiant etudiant = new Etudiant();

        try {
            ResultSet rset = this.connect.getStatement().executeQuery("SELECT * FROM Utilisateur WHERE droit = 4 AND id = " + id);
            if (rset.first()) {
                etudiant = new Etudiant(id, rset.getString("email"),rset.getString("passwd"),rset.getString("nom"), rset.getString("prenom"),4);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiant;
    }
}
