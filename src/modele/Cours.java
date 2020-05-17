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
public class Cours {
    
    // Attributs
    int id;
    String nom;
    
    // Constructeurs
    public Cours (int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    
    // Accesseurs
    public int getId() {
        return id;
    }
    
    public String getNom() {
        return nom;
    }
}
