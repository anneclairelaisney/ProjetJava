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
public class SeanceSalles extends Seance {
    
    // Attributs
    private ArrayList<Salle> salles;
    
    // Constructeurs
    public SeanceSalles() {}
    public SeanceSalles(int id, int semaine, int date, int heureDebut, int heureFin, int etat, ArrayList<Salle> salles) {
        super(id, semaine, date, heureDebut, heureFin, etat);
        this.salles = salles;
    }
    
    // Accesseurs
    public ArrayList<Salle> getSalles() {
        return this.salles;
    }
    
    // Methodes
    public void ajoutSalle(Salle salle) {
        this.salles.add(salle);
        JOptionPane.showMessageDialog(null, "La salle " + salle.getNom() + " a été ajoutée."); 
        System.out.println("Numéro salle : " + salle.getNom() + " - Nombre places : " + salle.getCapacite() + " - Site : " + salle.getIdSite());
    }
}