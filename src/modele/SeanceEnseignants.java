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
public class SeanceEnseignants extends Seance {
    
    // Attributs
    ArrayList<Enseignant> enseignants;
    
    // Constructeurs
    public SeanceEnseignants(int id, int semaine, int date, int heureDebut, int heureFin, int etat, ArrayList<Enseignant> enseignants) {
        super(id, semaine, date, heureDebut, heureFin, etat);
        this.enseignants = enseignants;
    }
    
    public ArrayList<Enseignant> getEnseignants() {
        return enseignants;
    }
}
