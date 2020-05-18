/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.modele;

/**
 *
 * @author apple
 */
public class Seance {
    
    //Attributs
    private int id;
    private int semaine;
    private int date;
    private int heureDebut;
    private int heureFin;
    private int etat;
    
    // Constructeurs
    public Seance() {}
    public Seance (int id, int semaine, int date, int heureDebut, int heureFin, int etat) {
        this.id = id;
        this.semaine = semaine;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.etat = etat;
    }
    
    // Accesseurs
    public int getId() {
        return this.id;
    }
    
    public int getSemaine() {
        return this.semaine;
    }
    
    public int getDate() {
        return this.date;
    }
    
    public int getHeureDebut() {
        return this.heureDebut;
    }
    
    public int getHeureFin() {
        return this.heureFin;
    }
    
    public int getEtat() {
        return this.etat;
    }
}
