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
//CTRL + SHIFT + O pour générer les imports

public class UtilisateurDAO extends DAO<Utilisateur> {
    
  public UtilisateurDAO(Connexion conn) {
    super(conn);
  }

  public boolean create(Utilisateur utilisateur) {
      try {
            this.connect.executeUpdate("INSERT INTO Utilisateur(id,email,passwd,nom,prenom,droit) VALUES(" + utilisateur.getId() + "," + utilisateur.getEmail() + "," + utilisateur.getPasswd() + "," + utilisateur.getNom() + "," + utilisateur.getPrenom() +  "," + utilisateur.getDroit() + ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }  
        return false;
  }

  public boolean delete(Utilisateur utilisateur) {
      try {
            this.connect.getStatement().executeUpdate("DELETE FROM Utilisateur WHERE id =" + utilisateur.getId() + ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }  
        return false;
  }
   
  public boolean update(Utilisateur utilisateur) {
      try {
            this.connect.getStatement().executeUpdate("UPDATE Promotion SET email ='" + utilisateur.getEmail() +"', passwd = '" + utilisateur.getPasswd() + "', nom ='" + utilisateur.getNom() + "', prenom = '" + utilisateur.getPrenom() + " WHERE id =" + utilisateur.getId() + ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }  
        return false;
  }
   
  public Utilisateur find(int id) {
    Utilisateur etudiant = new Utilisateur();    
      
    try {
      ResultSet rset = this.connect.getStatement().executeQuery("SELECT * FROM Utilisateur WHERE id = " + id);
      if(rset.first())
        etudiant = new Utilisateur(id,rset.getString("email"),rset.getString("passwd"),rset.getString("nom"), rset.getString("prenom"),4);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return etudiant;
  }
}