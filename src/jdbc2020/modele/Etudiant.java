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
    public Etudiant (int id, String email, String passwd, String nom, String prenom, int droit) {
        super(id,email,passwd,nom,prenom,droit);
    }
    public Etudiant (int id, int numero, int id_groupe) {
        this.id = id;
        this.droit = 4;
        this.numero = numero;
        this.idGroupe = id_groupe;
    }
    
    // Accesseurs
    public int getNumero() {
        return this.numero;
    }
    
    public int getGroupe() {
        return this.numero;
    }
    
    // Manipulateurs
    public void setNumero(int num) {
        this.numero = num;
    }
}
