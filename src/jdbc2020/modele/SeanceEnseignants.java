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
public class SeanceEnseignants extends Seance {
    
    // Attributs
    private ArrayList<Enseignant> enseignants;
    
    // Constructeurs
    public SeanceEnseignants() {}
    public SeanceEnseignants(int id, int semaine, int date, int heureDebut, int heureFin, int etat, ArrayList<Enseignant> enseignants) {
        super(id, semaine, date, heureDebut, heureFin, etat);
        this.enseignants = enseignants;
    }
    
    // Accesseurs 
    public ArrayList<Enseignant> getEnseignants() {
        return this.enseignants;
    }
    
    // Methodes
    public void ajoutEnseignant(Enseignant enseignant) {
        this.enseignants.add(enseignant);
        JOptionPane.showMessageDialog(null, "L'enseignant " + enseignant.getNom() + " a été ajouté."); 
        System.out.println("Nom du groupe : " + enseignant.getNom());
    }
}
