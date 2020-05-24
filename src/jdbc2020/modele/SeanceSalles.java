/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.modele;

import java.sql.Date;
import java.sql.Time;
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
    public SeanceSalles(int id, int semaine, Date date, int heureDebut, int heureFin, int etat, int id_cours, int id_type, ArrayList<Salle> salles) {
        super(id, semaine, date, heureDebut, heureFin, etat, id_cours, id_type);
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