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
    return false;
  }

  public boolean delete(Utilisateur utilisateur) {
    return false;
  }
   
  public boolean update(Utilisateur utilisateur) {
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