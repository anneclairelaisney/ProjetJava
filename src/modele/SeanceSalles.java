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
public class SeanceSalles extends Seance {
    
    // Attributs
    ArrayList<Salle> salles;
    
    // Constructeurs
    public SeanceSalles(int id, int semaine, int date, int heureDebut, int heureFin, int etat, ArrayList<Salle> salles) {
        super(id, semaine, date, heureDebut, heureFin, etat);
        this.salles = salles;
    }
    
    public ArrayList<Salle> getSalles() {
        return salles;
    }
}