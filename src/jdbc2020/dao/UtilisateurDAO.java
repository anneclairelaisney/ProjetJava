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
//CTRL + SHIFT + O pour générer les imports

public class UtilisateurDAO extends DAO<Utilisateur> {
    
  public UtilisateurDAO(Connexion conn) {
    super(conn);
  }

  public boolean create(Utilisateur utilisateur) {
    return false;
  }

  public boolean delete(Utilisateur utilisateur) {
    return false;
  }
   
  public boolean update(Utilisateur utilisateur) {
    return false;
  }
   
  public Utilisateur find(String login) {
    Utilisateur utilisateur = null;    
      
    try {
      ResultSet rset = this.connect.getStatement().executeQuery("SELECT * FROM Utilisateur WHERE email ='" + login + "'");
      if(rset.first())
        utilisateur = new Utilisateur(rset.getInt("id"),login,rset.getString("passwd"),rset.getString("nom"), rset.getString("prenom"), rset.getInt("droit"));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return utilisateur;
  }

    @Override
    public void display(Utilisateur obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Utilisateur find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList<Utilisateur> getAllUsers() throws Exception {
        ArrayList<Utilisateur> list = new ArrayList<>();
        Statement myStatement = null;

        ResultSet rset = null;
        try {
            rset = this.connect.getStatement().executeQuery("SELECT * FROM Utilisateur");
            while (rset.next()) {
                int id = rset.getInt("id");
                String nom = rset.getString("Nom");
                String prenom = rset.getString("Prenom");
                String email = rset.getString("Email");
                String passwd = rset.getString("Passwd");
                int droit = rset.getInt("droit");
                Utilisateur tempUser = new Utilisateur(id, email, passwd, nom, prenom, droit);
                list.add(tempUser);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            close(myStatement, rset);
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