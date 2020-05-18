/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.modele;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author apple
 */
public class SeanceGroupes extends Seance {
    
    // Attributs
    private ArrayList<Groupe> groupes;
    
    // Constructeurs
    public SeanceGroupes() {}
    public SeanceGroupes(int id, int semaine, int date, int heureDebut, int heureFin, int etat, ArrayList<Groupe> groupes) {
        super(id, semaine, date, heureDebut, heureFin, etat);
        this.groupes = groupes;
    }
    
    // Accesseurs
    public ArrayList<Groupe> getGroupes() {
        return this.groupes;
    }
    
    // Methodes
    public void ajoutGroupe(Groupe groupe) {
        this.groupes.add(groupe);
        JOptionPane.showMessageDialog(null, "Le groupe " + groupe.getNom() + " a été ajouté."); 
        System.out.println("Nom du groupe : " + groupe.getNom() + " - Promotion : " + groupe.getIdPromotion());
    }
}