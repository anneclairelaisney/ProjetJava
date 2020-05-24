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
import javax.swing.JComboBox;

/**
 *
 * @author apple
 */
public class GroupeDAO extends DAO<Groupe> {

    public GroupeDAO(Connexion conn) {
        super(conn);
    }

    public boolean create(Groupe groupe) {
        try {
            this.connect.getStatement().executeUpdate("INSERT INTO Groupe(ID,NOM,ID_PROMOTION) VALUES (" + groupe.getId() + ",'" + groupe.getNom() + "',(SELECT id FROM Promotion WHERE id =" + groupe.getIdPromotion() + "));");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Groupe groupe) {
        try {
            this.connect.getStatement().executeUpdate("DELETE FROM Groupe WHERE id =" + groupe.getId() + ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Groupe groupe) {
        try {
            this.connect.getStatement().executeUpdate("UPDATE Groupe SET nom ='" + groupe.getNom() + "' WHERE id =" + groupe.getId() + ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Groupe find(int id) {
        Groupe groupe = new Groupe();

        try {
            ResultSet rset = this.connect.getStatement().executeQuery("SELECT * FROM Groupe WHERE id = " + id);
            if (rset.first()) {
                groupe = new Groupe(id, rset.getString("nom"), rset.getInt("id_promotion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupe;
    }

    public void display(Groupe groupe) {
        try {
            if (groupe.getId() != 0) {
                ResultSet rset = this.connect.getStatement().executeQuery("SELECT nom FROM Promotion WHERE id = " + groupe.getIdPromotion());
                if (rset.first()) {
                    System.out.println("Nom : " + groupe.getNom() + " - Promotion : " + rset.getString("nom"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void chargerComboBoxGroupe(ArrayList<Groupe> lesClasses, JComboBox<String> cmbClasseGroupe) {
        for (Groupe c : lesClasses) {
            cmbClasseGroupe.addItem(c.getNom());
        }
    }
}
