/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.vue;

import jdbc2020.controleur.*;
import jdbc2020.dao.*;
import jdbc2020.modele.*;

/**
 *
 * @author apple
 */
public class Liste {
    public Liste(Connexion conn) {
        DAO<Utilisateur> utilisateurDao = new UtilisateurDAO(conn);
        for (int i = 1; i < 5000; i++) {
            Utilisateur utilisateur = utilisateurDao.find(i);
            if (utilisateur.getId() != 0) {
                System.out.println("Utilisateur N°" + utilisateur.getId() + "  - " + utilisateur.getNom() + " " + utilisateur.getPrenom() + " " + utilisateur.getEmail() + " " + utilisateur.getPasswd() + " " + utilisateur.getDroit());
            }
        }
        DAO<Etudiant> etudiantDao = new EtudiantDAO(conn);
        for (int i = 2; i < 5000; i++) {
            Etudiant etudiant = etudiantDao.find(i);
            if (etudiant.getId() != 0) {
                System.out.println("Etudiant N°" + etudiant.getId() + "  - " + etudiant.getNom() + " " + etudiant.getPrenom() + " " + etudiant.getEmail() + " " + etudiant.getPasswd());
            }
        }

        DAO<Enseignant> enseignantDao = new EnseignantDAO(conn);
        for (int i = 1; i < 5000; i++) {
            Enseignant enseignant = enseignantDao.find(i);
            if (enseignant.getId() != 0) {
                System.out.println("Enseignant N°" + enseignant.getId() + "  - " + enseignant.getNom() + " " + enseignant.getPrenom() + " " + enseignant.getEmail() + " " + enseignant.getPasswd());
            }
        }

        DAO<Groupe> groupeDao = new GroupeDAO(conn);
        for (int i = 1; i < 5000; i++) {
            Groupe groupe = groupeDao.find(i);
            if (groupe.getId() != 0) {
                System.out.println("Groupe N°" + groupe.getId() + "  - " + groupe.getNom() + " " + groupe.getIdPromotion());
            }
        }

        DAO<Promotion> promotionDao = new PromotionDAO(conn);
        for (int i = 1; i < 5; i++) {
            Promotion promotion = promotionDao.find(i);
            if (promotion.getId() != 0) {
                System.out.println("Promotion N°" + promotion.getId() + "  - " + promotion.getNom());
            }
        }

        DAO<Salle> salleDao = new SalleDAO(conn);
        for (int i = 1; i < 5000; i++) {
            Salle salle = salleDao.find(i);
            if (salle.getId() != 0) {
                System.out.println("Salle N°" + salle.getId() + "  - " + salle.getNom() + " " + salle.getCapacite() + " " + salle.getIdSite());
            }
        }
    }
}
