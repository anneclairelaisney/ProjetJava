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
public class Site {
    
    // Attributs
    private int id;
    private String nom;
    
    // Constructeurs
    public Site(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    
    // Accesseurs
    public int getId() {
        return this.id;
    }
    
    public String getNom() {
        return this.nom;
    }
}
