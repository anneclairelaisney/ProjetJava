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
public class SeanceGroupes {
    //Attributs
    private int seance;
    private int groupe;
    
    // Constructeurs

    /**
     *Constructeur SeanceGroupes sans paramètres
     */
    public SeanceGroupes() {}

    /**
     *
     * @param id_seance
     * @param id_groupe
     */
    public SeanceGroupes(int id_seance, int id_groupe) {
        this.seance = id_seance;
        this.groupe = id_groupe;
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
    public int getGroupe() {
        return this.groupe;
    }   
}
