/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import jdbc2020.dao.*;
import jdbc2020.modele.*;
import jdbc2020.vue.*;

/**
 *
 * @author apple
 */
public class Generateur {

    private Connexion maConnexion;
    private String login;
    private String mdp;
    private Fenetre fenetre;

    public Generateur(String login, String mdp, String database) throws SQLException, ClassNotFoundException, Exception {
        this.maConnexion = new Connexion("jdbc2020", "root", "root");
        this.fenetre = new Fenetre(login, mdp, "jdbc2020");
        this.login = login;
        this.mdp = mdp;

        generer(this.maConnexion);
    }

    public void generer(Connexion conn) throws Exception {}
}
