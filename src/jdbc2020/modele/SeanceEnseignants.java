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
public class SeanceEnseignants {
    //Attributs
    private int seance;
    private int enseignant;
    
    // Constructeurs

    /**
     *Constructeur SeanceEnseignants sans paramètres
     */
    public SeanceEnseignants() {}

    /**
     *
     * @param id_seance
     * @param id_enseignant
     */
    public SeanceEnseignants(int id_seance, int id_enseignant) {
        this.seance = id_seance;
        this.enseignant = id_enseignant;
    }
    
    // Methodes

    /**
     *
     * @return
     */
    public int getSeance() {
        return this.seance;
    }

    /**
     *
     * @return
     */
    public int getEnseignant() {
        return this.enseignant;
    }   
}
