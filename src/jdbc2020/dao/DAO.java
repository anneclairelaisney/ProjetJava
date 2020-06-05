/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import jdbc2020.controleur.*;
/**
 *
 * @author apple
 */

public abstract class DAO<T> {
    
  protected Connexion connect;
   
  public DAO(Connexion conn){
    this.connect = conn;
  }
   
  /**
  * Méthode de création
  * @param obj
  * @return boolean 
  */
  public abstract boolean create(T obj);

  /**
  * Méthode pour effacer
  * @param obj
  * @return boolean 
  */
  public abstract boolean delete(T obj);

  /**
  * Méthode de mise à jour
  * @param obj
  * @return boolean
  */
  public abstract boolean update(T obj);

  /**
  * Méthode de recherche des informations
  * @param id
  * @return T
     * @throws java.sql.SQLException
  */
  public abstract T find(int id) throws SQLException ;
  
  /**
  * Méthode d'affichage
  * @param obj
  * @return boolean
  */
  public abstract void display(T obj);
}