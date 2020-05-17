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
public class SeanceGroupes extends Seance {
    
    // Attributs
    ArrayList<Groupe> groupes;
    
    // Constructeurs
    public SeanceGroupes(int id, int semaine, int date, int heureDebut, int heureFin, int etat, ArrayList<Groupe> groupes) {
        super(id, semaine, date, heureDebut, heureFin, etat);
        this.groupes = groupes;
    }
    
    public ArrayList<Groupe> getGroupes() {
        return groupes;
    }
}