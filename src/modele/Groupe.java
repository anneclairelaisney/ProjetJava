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
public class Groupe {
    
    // Attributs
    int id;
    String nom;
    int idPromotion;
    
    // Constructeurs
    public Groupe(int id, String nom, int idPromotion) {
        this.id = id;
        this.nom = nom;
        this.idPromotion = idPromotion;
    }
}
