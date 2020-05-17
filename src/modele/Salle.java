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
public class Salle {
    
    // Attributs
    int id;
    String nom;
    int capacite;
    int idSite;
    
    // Constructeurs
    public Salle(int id, String nom, int capacite, int idSite) {
        this.id = id;
        this.nom = nom;
        this.capacite = capacite;
        this.idSite = idSite;
    }
    
    // Accesseurs
    public int getId() {
        return id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public int getCapacite() {
        return capacite;
    }
    
    public int getIdSite() {
        return idSite;
    }
}
