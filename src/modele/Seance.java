/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author apple
 */
public class Seance {
    
    //Attributs
    int id;
    int semaine;
    int date;
    int heureDebut;
    int heureFin;
    int etat;
    
    // Constructeurs
    public Seance (int id, int semaine, int date, int heureDebut, int heureFin, int etat) {
        this.id = id;
        this.semaine = semaine;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.etat = etat;
    }
}
