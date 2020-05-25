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
    private int idCours;
    
    // Constructeurs
    public Enseignant() {}
    public Enseignant (int id, String email, String passwd, String nom, String prenom, int droit) {
        super(id,email,passwd,nom,prenom,droit);
    }
    public Enseignant (int id, ArrayList<Cours> cours, int id_cours) {
        this.id = id;
        this.droit = 3;
        this.idCours = id_cours;
    }
    
    // Accesseurs
    public int getIdCours() {
        return this.idCours;
    }
}
