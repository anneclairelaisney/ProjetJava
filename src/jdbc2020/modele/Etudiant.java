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

public class Etudiant extends Utilisateur {
    
    // Attributs
    private int numero;
    private int idGroupe;
    
    // Constructeurs

    /**
     *Constructeur Etudiant sans paramètres
     */
    public Etudiant() {}

    /**
     *
     * @param id
     * @param numero
     * @param id_groupe
     */
    public Etudiant (int id, int numero, int id_groupe) {
        this.id = id;
        this.numero = numero;
        this.idGroupe = id_groupe;
        this.droit = 4;
    }

    /**
     *
     * @param id
     * @param email
     * @param passwd
     * @param nom
     * @param prenom
     */
    public Etudiant (int id, String email, String passwd, String nom, String prenom) {
        this.id = id;
        this.email = email;
        this.passwd = passwd;
        this.nom = nom;
        this.prenom = prenom;
        this.droit = 4;
    }

    /**
     *
     * @param id
     * @param email
     * @param passwd
     * @param nom
     * @param prenom
     * @param numero
     * @param id_groupe
     */
    public Etudiant (int id, String email, String passwd, String nom, String prenom, int numero, int id_groupe) {
        this.id = id;
        this.email = email;
        this.passwd = passwd;
        this.nom = nom;
        this.prenom = prenom;
        this.droit = 4;
        this.numero = numero;
        this.idGroupe = id_groupe;
    }
    
    // Accesseurs

    /**
     *
     * @return
     */
    public int getNumero() {
        return this.numero;
    }
    
    /**
     *
     * @return
     */
    public int getIdGroupe() {
        return this.idGroupe;
    }
    
    // Manipulateurs

    /**
     *
     * @param num
     */
    public void setNumero(int num) {
        this.numero = num;
    }

}
