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
public class Salle {
    
    // Attributs
    private int id;
    private String nom;
    private int capacite;
    private int idSite;
    
    // Constructeurs

    /**
     *Constructeur Salle sans paramètres
     */
    public Salle () {}

    /**
     *
     * @param id
     * @param nom
     * @param capacite
     * @param idSite
     */
    public Salle(int id, String nom, int capacite, int idSite) {
        this.id = id;
        this.nom = nom;
        this.capacite = capacite;
        this.idSite = idSite;
    }
    
    // Accesseurs

    /**
     *
     * @return
     */
    public int getId() {
        return this.id;
    }
    
    /**
     *
     * @return
     */
    public String getNom() {
        return this.nom;
    }
    
    /**
     *
     * @return
     */
    public int getCapacite() {
        return this.capacite;
    }
    
    /**
     *
     * @return
     */
    public int getIdSite() {
        return this.idSite;
    }
}
