/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.modele;

import java.sql.Date;

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

    /**
     *Constructeur Seance sans paramètres
     */
    public Seance() {
    }

    /**
     *
     * @param id
     * @param semaine
     * @param date
     * @param heureDebut
     * @param heureFin
     * @param etat
     * @param id_cours
     * @param id_type
     */
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

    /**
     *
     * @return
     */
    public int getId() {
        return this.id;
    }

    /**
     *
     * @return
     */
    public int getSemaine() {
        return this.semaine;
    }

    /**
     *
     * @return
     */
    public Date getDate() {
        return this.date;
    }

    /**
     *
     * @return
     */
    public int getHeureDebut() {
        return this.heureDebut;
    }

    /**
     *
     * @return
     */
    public int getHeureFin() {
        return this.heureFin;
    }

    /**
     *
     * @return
     */
    public int getEtat() {
        return this.etat;
    }

    /**
     *
     * @return
     */
    public int getIdCours() {
        return this.idCours;
    }

    /**
     *
     * @return
     */
    public int getIdType() {
        return this.idType;
    }

    // Methodes

    /**
     *
     * @return
     */
    public int dateToInt() {
        return this.date.getDay();
    }
}
