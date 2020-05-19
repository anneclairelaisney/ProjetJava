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
public class EnseignantDAO extends DAO<Enseignant> {

    public EnseignantDAO(Connexion conn) {
        super(conn);
    }

    public boolean create(Enseignant enseignant) {
        return false;
    }

    public boolean delete(Enseignant enseignant) {
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
                enseignant = new Enseignant(id, rset.getString("email"),rset.getString("passwd"),rset.getString("nom"), rset.getString("prenom"),3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enseignant;
    }
}
