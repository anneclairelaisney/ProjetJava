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
public abstract class Utilisateur {
    
    //Attributs
    protected int id;
    protected String email;
    protected String passwd;
    protected String nom;
    protected String prenom;
    protected int droit;
    
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
}
