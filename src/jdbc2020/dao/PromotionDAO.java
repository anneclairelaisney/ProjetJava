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
public class PromotionDAO extends DAO<Promotion> {

    public PromotionDAO(Connexion conn) {
        super(conn);
    }

    public boolean create(Promotion promotion) {
        try {
            this.connect.executeUpdate("INSERT INTO Promotion(id,nom) VALUES(" + promotion.getId() + "," + promotion.getNom() + ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }  
        return false;
    }

    public boolean delete(Promotion promotion) {
        try {
            this.connect.getStatement().executeUpdate("DELETE FROM Promotion WHERE id =" + promotion.getId() + ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }  
        return false;
    }

    public boolean update(Promotion promotion) {
        try {
            this.connect.getStatement().executeUpdate("UPDATE Promotion SET nom ='" + promotion.getNom() +"' WHERE id =" + promotion.getId() + ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }  
        return false;
    }

    public Promotion find(int id) {
        Promotion promotion = new Promotion();

        try {
            ResultSet rset = this.connect.getStatement().executeQuery("SELECT * FROM Promotion WHERE id = " + id);
            if (rset.first()) {
                promotion = new Promotion(id, rset.getString("nom"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return promotion;
    }
}
