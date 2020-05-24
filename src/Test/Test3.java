/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import jdbc2020.controleur.Connexion;
import jdbc2020.dao.DAO;
import jdbc2020.dao.SeanceDAO;
import jdbc2020.modele.Seance;

/**
 *
 * @author apple
 */
public class Test3 {
     public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connexion conn = new Connexion("jdbc2020", "root", "root");
        DAO<Seance> seanceDAO = new SeanceDAO(conn);
        //seanceDAO.update(new Seance(2,22,new java.sql.Date(120,04,26),new java.sql.Time(13,45,00), new java.sql.Time(15,15,00), 1, 2, 2));
        Seance s = seanceDAO.find(1);
        seanceDAO.display(s);
        s = seanceDAO.find(2);
        seanceDAO.display(s);
     }
}
