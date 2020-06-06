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

    /**
     *Constructeur SeanceSalles sans paramètres
     */
    public SeanceSalles() {}

    /**
     *
     * @param id_seance
     * @param id_salle
     */
    public SeanceSalles(int id_seance, int id_salle) {
        this.seance = id_seance;
        this.salle = id_salle;
    }
    
    // Methodes

    /**
     *
     * @return
     */
    public int getSeance() {
        return this.seance;
    }

    /**
     *
     * @return
     */
    public int getSalle() {
        return this.salle;
    }   
}
