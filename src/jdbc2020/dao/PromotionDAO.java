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
public class PromotionDAO extends DAO<Promotion> {

    public PromotionDAO(Connexion conn) {
        super(conn);
    }

    public boolean create(Promotion promotion) {
        try {
            this.connect.executeUpdate("WHERE [NOT] EXISTS (INSERT INTO Promotion(id,nom) VALUES(" + promotion.getId() + "," + promotion.getNom() + "));");
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
            this.connect.getStatement().executeUpdate("UPDATE Promotion SET nom ='" + promotion.getNom() + "' WHERE id =" + promotion.getId() + ");");
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

    public ArrayList<Promotion> getAllPromotions() throws Exception {
        ArrayList<Promotion> list = new ArrayList<Promotion>();
        Statement myStatement = null;
        ResultSet rset = null;
        try {
            rset = this.connect.getStatement().executeQuery("SELECT * FROM Promotion");
            while (rset.next()) {
                Promotion tempPromotion = convertRowToPromotion(rset);
                list.add(tempPromotion);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(myStatement, rset);
            return list;
        }
    }

    private Promotion convertRowToPromotion(ResultSet myResult) throws SQLException {
        int id = myResult.getInt("ID");
        String nom = myResult.getString("Nom");
        Promotion tempPromotion = new Promotion(id,nom);
        return tempPromotion;
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

    public void display(Promotion promotion) {
        if (promotion.getId() != 0) {
            System.out.println("Nom : " + promotion.getNom());
        }
    }
}
