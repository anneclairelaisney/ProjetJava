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
public class SeanceEnseignants {
    
    // Attributs
    Seance seance;
    ArrayList<Enseignant> enseignants;
    
    // Constructeurs

    public SeanceEnseignants(Seance seance, ArrayList<Enseignant> enseignants) {
        this.seance = seance;
        this.enseignants = enseignants;
    }
}
