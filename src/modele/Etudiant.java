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
public class Etudiant extends Utilisateur {
    
    // Attributs
    int numero;
    
    // Constructeurs
    public Etudiant (int id, String email, String passwd, String nom, String prenom, int numero) {
        this.id = id;
        this.email = email;
        this.passwd = passwd;
        this.nom = nom;
        this.prenom = prenom;
        droit = 4;
        this.numero = numero;
    }
}
