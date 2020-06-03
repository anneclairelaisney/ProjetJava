/**
 *
 * @author apple
 */
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
public class TypeCoursDAO extends DAO<TypeCours> {

    public TypeCoursDAO(Connexion conn) {
        super(conn);
    }

    public boolean create(TypeCours typeCours) {
        try {
            this.connect.getStatement().executeUpdate("WHERE [NOT] EXISTS (INSERT INTO TypeCours(ID,NOM) VALUES (" + typeCours.getId() + ",'" + typeCours.getNom() + "))");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }  
        return false;
    }

    public boolean delete(TypeCours typeCours) {
        try {
            this.connect.getStatement().executeUpdate("DELETE FROM TYPE_COURS WHERE id =" + typeCours.getId() + ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }  
        return false;
    }

    public boolean update(TypeCours typeCours) {
        try {
            this.connect.getStatement().executeUpdate("UPDATE TYPE_COURS SET nom ='" + typeCours.getNom() + ")");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }  
        return false;
    }

    public TypeCours find(int id) {
        TypeCours typeCours = new TypeCours();

        try {
            ResultSet rset = this.connect.getStatement().executeQuery("SELECT * FROM TYPE_COURS WHERE id = " + id);
            if (rset.first()) {
                typeCours = new TypeCours(id, rset.getString("nom"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeCours;
    }
    
    public ArrayList<TypeCours> getAllTypeCours() throws Exception {
        ArrayList<TypeCours> list = new ArrayList<TypeCours>();
        Statement myStatement = null;
        ResultSet rset = null;
        try {
            rset = this.connect.getStatement().executeQuery("SELECT * FROM TYPE_COURS");
            while (rset.next()) {
                TypeCours tempTypeCours = convertRowToTypeCours(rset);
                list.add(tempTypeCours);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(myStatement, rset);
            return list;
        }
    }

    private TypeCours convertRowToTypeCours(ResultSet myResult) throws SQLException {
        int id = myResult.getInt("ID");
        String nom = myResult.getString("Nom");
        TypeCours tempTypeCours = new TypeCours(id,nom);
        return tempTypeCours;
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
    
    public void display(TypeCours typeCours) {
        if (typeCours.getId() != 0) {
            System.out.println("Nom : " + typeCours.getNom());
        }
    }
}
