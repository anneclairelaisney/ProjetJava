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
    public ArrayList<Utilisateur> utilisateurs;
    public ArrayList<Promotion> promotions;
    public ArrayList<Groupe> groupes;
    public ArrayList<Etudiant> etudiants;
    public ArrayList<Enseignant> enseignants;
    public ArrayList<Salle> salles;

    // Constructeurs
    public Liste() throws SQLException, ClassNotFoundException {
        this.conn = new Connexion("jdbc2020", "root", "root");
        this.listePromotions();
        this.listeGroupes();
        this.listeEtudiants();
        this.listeEnseignants();
        this.listeSalles();
    }
    
    public void listePromotions () {
        this.promotions = new ArrayList<Promotion>();
        DAO<Promotion> promotionDao = new PromotionDAO(conn);
        for (int i = 0; i < 5000; i++) {
            Promotion promotion = promotionDao.find(i);
            if (promotion.getId() != 0) {
                promotions.add(promotion);
            }
        }
    }
    
    public void listeGroupes () {
        this.groupes = new ArrayList<Groupe>();
        DAO<Groupe> groupeDAO = new GroupeDAO(conn);
        for (int i = 0; i < 5000; i++) {
            Groupe groupe = groupeDAO.find(i);
            if (groupe.getId() != 0) {
                groupes.add(groupe);
            }
        }
    }
    
    public void listeEtudiants () {
        this.etudiants = new ArrayList<Etudiant>();
        DAO<Etudiant> etudiantDAO = new EtudiantDAO(conn);
        for (int i = 0; i < 5000; i++) {
            Etudiant etudiant = etudiantDAO.find(i);
            if (etudiant.getId() != 0) {
                etudiants.add(etudiant);
            }
        }
    }
    
    public void listeEnseignants () {
        this.enseignants = new ArrayList<Enseignant>();
        DAO<Enseignant> enseignantDAO = new EnseignantDAO(conn);
        for (int i = 0; i < 5000; i++) {
            Enseignant enseignant = enseignantDAO.find(i);
            if (enseignant.getId() != 0) {
                enseignants.add(enseignant);
            }
        }
    }
    
    public void listeSalles () {
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
