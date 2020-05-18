/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.modele;

import java.util.ArrayList;
/**
 *
 * @author apple
 */
public class Enseignant extends Utilisateur {
    
    // Attributs
    private ArrayList<Cours> cours;
    
    // Constructeurs
    public Enseignant (int id, String email, String passwd, String nom, String prenom, ArrayList<Cours> cours) {
        this.id = id;
        this.email = email;
        this.passwd = passwd;
        this.nom = nom;
        this.prenom = prenom;
        this.droit = 3;
        this.cours = cours;
    }
    
    // Accesseurs
    public ArrayList<Cours> getCours() {
        return this.cours;
    }
}
