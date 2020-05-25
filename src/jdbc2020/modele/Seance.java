/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.modele;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import javax.swing.JPanel;

/**
 *
 * @author apple
 */
public class Seance {

    //Attributs
    private int id;
    private int semaine;
    private Date date;
    private int heureDebut;
    private int heureFin;
    private int etat;
    private int idCours;
    private int idType;

    // Constructeurs
    public Seance() {
    }

    public Seance(int id, int semaine, Date date, int heureDebut, int heureFin, int etat, int id_cours, int id_type) {
        this.id = id;
        this.semaine = semaine;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.etat = etat;
        this.idCours = id_cours;
        this.idType = id_type;
    }

    // Accesseurs
    public int getId() {
        return this.id;
    }

    public int getSemaine() {
        return this.semaine;
    }

    public Date getDate() {
        return this.date;
    }

    public int getHeureDebut() {
        return this.heureDebut;
    }

    public int getHeureFin() {
        return this.heureFin;
    }

    public int getEtat() {
        return this.etat;
    }

    public int getIdCours() {
        return this.idCours;
    }

    public int getIdType() {
        return this.idType;
    }

    // Methodes
    public int dateToInt() {
        return this.date.getDay();
    }
}
