/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.controleur;

import jdbc2020.modele.*;
import jdbc2020.vue.*;

/**
 *
 * @author apple
 */
public class GenerateurConnexion {

    // Attributs
    private PageConnexion pageConnexion;
    private SeanceEnseignants lesEnseignants;
    private SeanceGroupes lesGroupes;
    private SeanceSalles lesSalles;

    // Constructeurs
    public GenerateurConnexion() {
        this.pageConnexion = new PageConnexion();
        this.pageConnexion.setVisible(true);
        this.lesEnseignants = new SeanceEnseignants();
        this.lesGroupes = new SeanceGroupes();
        this.lesSalles = new SeanceSalles();
        
        setupActionButton();
    }
    
    // Methodes
    public void setupActionButton() {
        
    }
}
