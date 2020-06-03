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
    public Etudiant() {}
    public Etudiant (int id, String email, String passwd, String nom, String prenom) {
        this.id = id;
        this.email = email;
        this.passwd = passwd;
        this.nom = nom;
        this.prenom = prenom;
        this.droit = 4;
    }
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
    public int getId() {
        return this.id;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getPasswd() {
        return this.passwd;
    }
    
    public String getNom() {
        return this.nom;
    }
    
    public String getPrenom() {
        return this.prenom;
    }
    
    public int getDroit() {
        return this.droit;
    }
    public int getNumero() {
        return this.numero;
    }
    
    public int getIdGroupe() {
        return this.idGroupe;
    }
    
    // Manipulateurs
    public void setNumero(int num) {
        this.numero = num;
    }

}
