/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;

/**
 *
 * @author apple
 */
public class SeanceSalles {
    
    // Attributs
    Seance seance;
    ArrayList<Salle> salles;
    
    // Constructeurs

    public SeanceSalles(Seance seance, ArrayList<Salle> salles) {
        this.seance = seance;
        this.salles = salles;
    }
}