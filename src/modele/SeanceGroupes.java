/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;

/**
 *
 * @author apple
 */
public class SeanceGroupes {
    
    // Attributs
    Seance seance;
    ArrayList<Groupe> groupes;
    
    // Constructeurs

    public SeanceGroupes(Seance seance, ArrayList<Groupe> groupes) {
        this.seance = seance;
        this.groupes = groupes;
    }
}