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


public class Enseignant extends Utilisateur {
    
    // Attributs
    private int idCours;
    
    // Constructeurs

    /**
     *Constructeur Enseignant sans paramètres
     */
    public Enseignant() {}

    /**
     *
     * @param id
     * @param id_cours
     */
    public Enseignant (int id, int id_cours) {
        this.id = id;
        this.idCours = id_cours;
        this.droit = 3;
    }
    
    /**
     *
     * @param id
     * @param email
     * @param passwd
     * @param nom
     * @param prenom
     */
    public Enseignant (int id, String email, String passwd, String nom, String prenom) {
        this.id = id;
        this.email = email;
        this.passwd = passwd;
        this.nom = nom;
        this.prenom = prenom;
        this.droit = 3;
    }

    /**
     *
     * @param id
     * @param email
     * @param passwd
     * @param nom
     * @param prenom
     * @param idCours
     */
    public Enseignant (int id, String email, String passwd, String nom, String prenom, int idCours) {
        this.id = id;
        this.email = email;
        this.passwd = passwd;
        this.nom = nom;
        this.prenom = prenom;
        this.droit = 3;
        this.idCours = idCours;
    }
    
    // Accesseurs

    /**
     *
     * @return int
     */
    public int getIdCours() {
        return this.idCours;
    }
}
