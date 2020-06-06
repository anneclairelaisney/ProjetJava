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
public class Cours {
    
    // Attributs

    /**
     *
     */
    protected int id;
    private String nom;
    
    // Constructeurs

    /**
     * Constructeur Cours sans paramètres
     */
    public Cours () {}

    /**
     *
     * @param id
     * @param nom
     */
    public Cours (int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    
    // Accesseurs

    /**
     *
     * @return int
     */
    public int getId() {
        return this.id;
    }
    
    /**
     *
     * @return String
     */
    public String getNom() {
        return this.nom;
    }
}
