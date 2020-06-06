/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.vue;

import java.sql.SQLException;
import java.util.ArrayList;
import jdbc2020.controleur.*;
import jdbc2020.dao.*;
import jdbc2020.modele.*;

/**
 *
 * @author apple
 */
public class Liste {

    // Attributs
    private Connexion conn;

    /**
     *
     */
    public ArrayList<Utilisateur> utilisateurs;

    /**
     *
     */
    public ArrayList<Promotion> promotions;

    /**
     *
     */
    public ArrayList<Groupe> groupes;

    /**
     *
     */
    public ArrayList<Etudiant> etudiants;

    /**
     *
     */
    public ArrayList<Enseignant> enseignants;

    /**
     *
     */
    public ArrayList<Salle> salles;

    // Constructeurs

    /**
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public Liste() throws SQLException, ClassNotFoundException, Exception  {
        this.conn = new Connexion("jdbc2020", "root", "root");
        this.listePromotions();
        this.listeGroupes();
        this.listeEtudiants();
        this.listeEnseignants();
        this.listeSalles();
    }
    
    /**
     *
     * @throws Exception
     */
    public void listePromotions () throws Exception {
        this.promotions = new ArrayList<Promotion>();
        DAO<Promotion> promotionDao = new PromotionDAO(conn);
        for (int i = 0; i < 5000; i++) {
            Promotion promotion = promotionDao.find(i);
            if (promotion.getId() != 0) {
                promotions.add(promotion);
            }
        }
    }
    
    /**
     *
     * @throws Exception
     */
    public void listeGroupes ()  throws Exception {
        this.groupes = new ArrayList<Groupe>();
        DAO<Groupe> groupeDAO = new GroupeDAO(conn);
        for (int i = 0; i < 5000; i++) {
            Groupe groupe = groupeDAO.find(i);
            if (groupe.getId() != 0) {
                groupes.add(groupe);
            }
        }
    }
    
    /**
     *
     * @throws Exception
     */
    public void listeEtudiants ()  throws Exception {
        this.etudiants = new ArrayList<Etudiant>();
        DAO<Etudiant> etudiantDAO = new EtudiantDAO(conn);
        for (int i = 0; i < 5000; i++) {
            Etudiant etudiant = etudiantDAO.find(i);
            if (etudiant.getId() != 0) {
                etudiants.add(etudiant);
            }
        }
    }
    
    /**
     *
     * @throws Exception
     */
    public void listeEnseignants ()  throws Exception {
        this.enseignants = new ArrayList<Enseignant>();
        DAO<Enseignant> enseignantDAO = new EnseignantDAO(conn);
        for (int i = 0; i < 5000; i++) {
            Enseignant enseignant = enseignantDAO.find(i);
            if (enseignant.getId() != 0) {
                enseignants.add(enseignant);
            }
        }
    }
    
    /**
     *
     * @throws Exception
     */
    public void listeSalles ()  throws Exception {
        this.salles = new ArrayList<Salle>();
        DAO<Salle> salleDAO = new SalleDAO(conn);
        for (int i = 0; i < 5000; i++) {
            Salle salle = salleDAO.find(i);
            if (salle.getId() != 0) {
                salles.add(salle);
            }
        }
    }
}
