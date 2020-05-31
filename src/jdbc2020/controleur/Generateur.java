/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import jdbc2020.dao.*;
import jdbc2020.modele.*;
import jdbc2020.vue.*;

/**
 *
 * @author apple
 */
public class Generateur {

    private Connexion maConnexion;
    private String login;
    private String mdp;
    private Fenetre fenetre;
    private ArrayList<Salle> salles;
    private ArrayList<Enseignant> enseignants;
    private ArrayList<Etudiant> etudiants;
    private ArrayList<Promotion> promotions;
    private ArrayList<Groupe> groupes;
    private ArrayList<Seance> seances;
    private ArrayList<Cours> cours;
    private ArrayList<TypeCours> typeCours;

    public Generateur(String login, String mdp, String database) throws SQLException, ClassNotFoundException, Exception {
        this.maConnexion = new Connexion("jdbc2020", "root", "root");
        this.login = login;
        this.mdp = mdp;
        this.fenetre = new Fenetre(login, mdp, "jdbc2020");

        SalleDAO salledao = new SalleDAO(this.maConnexion);
        this.salles = new ArrayList<Salle>();
        this.salles = salledao.getAllSalles();

        EnseignantDAO enseignantdao = new EnseignantDAO(this.maConnexion);
        this.enseignants = new ArrayList<Enseignant>();
        this.enseignants = enseignantdao.getAllTeachers();

        EtudiantDAO etudiantdao = new EtudiantDAO(this.maConnexion);
        this.etudiants = new ArrayList<Etudiant>();
        this.etudiants = etudiantdao.getAllStudents();

        PromotionDAO promotionDao = new PromotionDAO(this.maConnexion);
        this.promotions = new ArrayList<>();
        this.promotions = promotionDao.getAllPromotions();

        GroupeDAO groupeDao = new GroupeDAO(this.maConnexion);
        this.groupes = new ArrayList<>();
        this.groupes = groupeDao.getAllGroupes();

        SeanceDAO seancedao = new SeanceDAO(this.maConnexion);
        this.seances = new ArrayList<>();
        this.seances = seancedao.getAllSeances();

        CoursDAO coursdao = new CoursDAO(this.maConnexion);
        this.cours = new ArrayList<>();
        this.cours = coursdao.getAllCours();

        TypeCoursDAO typeCoursdao = new TypeCoursDAO(this.maConnexion);
        this.typeCours = new ArrayList<>();
        this.typeCours = typeCoursdao.getAllTypeCours();

        generer(this.maConnexion);
    }

    public void generer(Connexion conn) throws Exception {
        fenetre.ajoutSeance();

        for (Cours c : cours) {
            fenetre.getCoursSeance().addItem(c.getNom());
        }
        for (TypeCours t : typeCours) {
            fenetre.getTypeCoursSeance().addItem(t.getNom());
        }
        fenetre.getValiderSeance().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source == fenetre.getValiderSeance()) {

                    CoursDAO coursdao = new CoursDAO(conn);
                    Cours tempCours = cours.get(fenetre.getCoursSeance().getSelectedIndex());
                    Cours cours = coursdao.find(tempCours.getId());

                    TypeCoursDAO type_coursdao = new TypeCoursDAO(conn);
                    TypeCours tempTC = typeCours.get(fenetre.getTypeCoursSeance().getSelectedIndex());
                    TypeCours type_cours = type_coursdao.find(tempTC.getId());

                    int a = Integer.parseInt(fenetre.getHeureDebutTexte().getText().trim());
                    int b = Integer.parseInt(fenetre.getHeureFinTexte().getText().trim());
                    int etat = Integer.parseInt(fenetre.getEtatTexte().getText().trim());

                    SeanceDAO seancedao = new SeanceDAO(conn);
                    java.sql.Date sqlDate = new java.sql.Date(fenetre.getDateChoix().getDate().getTime());
                    seances.add(new Seance(seances.size(), 23, sqlDate, a, b, etat, cours.getId(), type_cours.getId()));
                    Seance seance = new Seance(seances.size() + 1, 23, sqlDate, a, b, etat, cours.getId(), type_cours.getId());
                    seancedao.display(seance);
                    seancedao.create(seance);

                    for (int i = 0; i < fenetre.getEnseignantsSeance().size(); i++) {
                        JCheckBox jcb = fenetre.getEnseignantsSeance().get(i);
                        boolean state = jcb.isSelected();
                        if (state) {
                            System.out.println(jcb.getText() + " est coché");
                            EnseignantDAO enseignantdao = new EnseignantDAO(conn);
                            Enseignant tempE = enseignants.get(i);
                            Enseignant enseignant = enseignantdao.find(tempE.getId());
                            seancedao.createEnseignants(seance, enseignant);
                        } else {
                            System.out.println(jcb.getText() + " n'est pas coché");
                        }

                    }

                    for (int i = 0; i < fenetre.getGroupesSeance().size(); i++) {
                        JCheckBox jcb = fenetre.getGroupesSeance().get(i);
                        boolean state = jcb.isSelected();
                        if (state) {
                            System.out.println(jcb.getText() + " est coché");
                            GroupeDAO groupedao = new GroupeDAO(conn);
                            Groupe tempG = groupes.get(i);
                            Groupe groupe = groupedao.find(tempG.getId());
                            seancedao.createGroupes(seance, groupe);
                        } else {
                            System.out.println(jcb.getText() + " n'est pas coché");
                        }

                    }

                    for (int i = 0; i < fenetre.getSallesSeance().size(); i++) {
                        JCheckBox jcb = fenetre.getSallesSeance().get(i);
                        boolean state = jcb.isSelected();
                        if (state) {
                            System.out.println(jcb.getText() + " est coché");
                            SalleDAO salledao = new SalleDAO(conn);
                            Salle tempS = salles.get(i);
                            Salle salle = salledao.find(tempS.getId());
                            seancedao.createSalles(seance, salle);
                        } else {
                            System.out.println(jcb.getText() + " n'est pas coché");
                        }
                    }

                    try {
                        fenetre.ajoutSeance().dispose();
                    } catch (Exception ex) {
                        Logger.getLogger(Generateur.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        new Fenetre(login, mdp, "jdbc2020");
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(Generateur.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }
        );
    }
}
