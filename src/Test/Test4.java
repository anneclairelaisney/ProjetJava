/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.sql.Date;
import java.sql.SQLException;
import jdbc2020.controleur.Connexion;
import jdbc2020.dao.SeanceDAO;
import jdbc2020.modele.Seance;
import jdbc2020.vue.MaFenetre;

/**
 *
 * @author apple
 */
public class Test4 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connexion maconnexion = new Connexion("jdbc2020", "root", "root");
        Seance seance = new Seance(4, 23, new Date((2020-1900),4,27), 10, 11, 1,1,1);
        SeanceDAO seancedao = new SeanceDAO(maconnexion);
        seancedao.create(seance);
    }
}
