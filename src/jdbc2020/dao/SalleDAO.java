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
public class SalleDAO extends DAO<Salle> {

    public SalleDAO(Connexion conn) {
        super(conn);
    }

    public boolean create(Salle salle) {
        return false;
    }

    public boolean delete(Salle salle) {
        return false;
    }

    public boolean update(Salle salle) {
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
}
