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
public class GroupeDAO extends DAO<Groupe> {

    public GroupeDAO(Connexion conn) {
        super(conn);
    }

    public boolean create(Groupe groupe) {
        return false;
    }

    public boolean delete(Groupe groupe) {
        return false;
    }

    public boolean update(Groupe groupe) {
        return false;
    }

    public Groupe find(int id) {
        Groupe groupe = new Groupe();

        try {
            ResultSet rset = this.connect.getStatement().executeQuery("SELECT * FROM Groupe WHERE id = " + id);
            if (rset.first()) {
                groupe = new Groupe(id, rset.getString("nom"),rset.getInt("id_promotion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupe;
    }
}
