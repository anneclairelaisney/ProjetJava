/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.controleur;

import jdbc2020.modele.*;
import jdbc2020.vue.*;

import java.io.*;
import java.util.ArrayList;
/**
 *
 * @author apple
 */
public class Generateur {
    
    // Attributs
    private Fenetre fenetre;
    private SeanceEnseignants lesEnseignants;
    private SeanceGroupes lesGroupes;
    private SeanceSalles lesSalles;
    
    // Constructeurs
    public Generateur() {
        //this.fenetre = new Fenetre();
        this.lesEnseignants = new SeanceEnseignants();
        this.lesGroupes = new SeanceGroupes();
        this.lesSalles = new SeanceSalles();
    }
}
