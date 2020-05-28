/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.controleur;

import java.util.ArrayList;
import jdbc2020.modele.*;
import jdbc2020.vue.*;

/**
 *
 * @author apple
 */
public class Ajouter {
	private MaFenetre maFenetre;
	private ArrayList<Salle> salles;
	private ArrayList<Enseignant> enseignants;
	private ArrayList<Etudiant> etudiants;
	private ArrayList<Promotion> promotions;
	private ArrayList<Groupe> groupes;
	private ArrayList<Seance> seances;

    public Ajouter() {
        this.salles = new ArrayList<>();
        this.enseignants = new ArrayList<>();
        this.etudiants = new ArrayList<>();
        this.promotions = new ArrayList<>();
        this.groupes = new ArrayList<>();
        this.seances = new ArrayList<>();
    }
    
    public void add() {
        
    }
}
