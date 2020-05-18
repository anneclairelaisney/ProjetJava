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
public class Groupe {
    
    // Attributs
    private int id;
    private String nom;
    private int idPromotion;
    
    // Constructeurs
    public Groupe(int id, String nom, int idPromotion) {
        this.id = id;
        this.nom = nom;
        this.idPromotion = idPromotion;
    }
    
    // Accesseurs
    public int getId() {
        return this.id;
    }
    
    public String getNom() {
        return this.nom;
    }
    
    public int getIdPromotion() {
        return this.idPromotion;
    }
}
