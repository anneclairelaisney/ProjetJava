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
public class SeanceSalles {
    //Attributs
    private int seance;
    private int salle;
    
    // Constructeurs
    public SeanceSalles() {}
    public SeanceSalles(int id_seance, int id_salle) {
        this.seance = id_seance;
        this.salle = id_salle;
    }
    
    // Methodes
    public int getSeance() {
        return this.seance;
    }
    public int getSalle() {
        return this.salle;
    }   
}
