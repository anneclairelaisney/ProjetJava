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
public class Utilisateur {
    
    //Attributs

    /**
     *
     */
    protected int id;

    /**
     *
     */
    protected String email;

    /**
     *
     */
    protected String passwd;

    /**
     *
     */
    protected String nom;

    /**
     *
     */
    protected String prenom;

    /**
     *
     */
    protected int droit;
    
    /**
     *Constructeur Utilisateur sans paramètres
     */
    public Utilisateur() {}

    /**
     *
     * @param id
     * @param email
     * @param passwd
     * @param nom
     * @param prenom
     * @param droit
     */
    public Utilisateur (int id, String email, String passwd, String nom, String prenom, int droit) {
        this.id = id;
        this.email = email;
        this.passwd = passwd;
        this.nom = nom;
        this.prenom = prenom;
        this.droit = droit;
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
    public String getEmail() {
        return this.email;
    }
    
    /**
     *
     * @return String
     */
    public String getPasswd() {
        return this.passwd;
    }
    
    /**
     *
     * @return String
     */
    public String getNom() {
        return this.nom;
    }
    
    /**
     *
     * @return String
     */
    public String getPrenom() {
        return this.prenom;
    }
    
    /**
     *
     * @return int
     */
    public int getDroit() {
        return this.droit;
    }
}