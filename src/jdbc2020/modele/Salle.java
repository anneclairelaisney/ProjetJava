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
    public Salle () {}
    public Salle(int id, String nom, int capacite, int idSite) {
        this.id = id;
        this.nom = nom;
        this.capacite = capacite;
        this.idSite = idSite;
    }
    
    // Accesseurs
    public int getId() {
        return this.id;
    }
    
    public String getNom() {
        return this.nom;
    }
    
    public int getCapacite() {
        return this.capacite;
    }
    
    public int getIdSite() {
        return this.idSite;
    }
}
