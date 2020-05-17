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
public class Enseignant extends Utilisateur {
    
    // Constructeurs
    public Enseignant (int id, String email, String passwd, String nom, String prenom) {
        this.id = id;
        this.email = email;
        this.passwd = passwd;
        this.nom = nom;
        this.prenom = prenom;
        droit = 3;
    }
}
