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
public class TypeCours {
    
    // Attributs
    private int id;
    private String nom;
    
    // Constructeurs

    /**
     *Constructeur TypeCours sans paramètres
     */
    public TypeCours() {}

    /**
     *
     * @param id
     * @param nom
     */
    public TypeCours(int id, String nom) {
        this.id = id;
        this.nom = nom;
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
}
