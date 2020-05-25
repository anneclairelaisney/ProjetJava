/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.vue;

import jdbc2020.controleur.*;
import jdbc2020.modele.*;
/*
 * 
 * Librairies importées
 */
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Affiche dans la fenetre graphique les champs de tables et les requetes de la
 * BDD
 *
 * @author segado
 */
public class Fenetre extends JFrame implements ActionListener, ItemListener {

    //Attributs
    /* CONNEXION */
    private Connexion maconnexion;

    /* MENU */
    private JLabel logo;
    private JMenuBar menuBar;
    private JMenu cours = new JMenu("Cours"),
            etudiants = new JMenu("Etudiants"),
            promotions = new JMenu("Promotions"),
            enseignants = new JMenu("Enseignants"),
            salles = new JMenu("Salles");

    private JMenuItem listeCours = new JMenuItem("Emploi du temps"),
            recapCours = new JMenuItem("Récapitulatif des cours"),
            listeUtilisateurs = new JMenuItem("Liste des utilisateurs");

    private JMenuItem edtEtudiant = new JMenuItem("Emploi du temps"),
            recapEtudiant = new JMenuItem("Récapitulatif des cours"),
            coursAnnules = new JMenuItem("Cours annulés"),
            listeEtudiants = new JMenuItem("Liste des étudiants");

    private JMenuItem listePromotions = new JMenuItem("Liste des promotions"),
            listeGroupes = new JMenuItem("Liste des groupes"),
            listeIntervenants = new JMenuItem("Liste des intervenants");

    private JMenuItem edtEnseignant = new JMenuItem("Emploi du temps"),
            recapEnseignants = new JMenuItem("Récapitulatif des cours"),
            listeEnseignants = new JMenuItem("Liste des enseignants");

    private JMenuItem listeSalles = new JMenuItem("Listes des salles");

    /* RECHERCHE */
    private JPanel p1;
    private JTextField barreDeRechercheTexte;
    private JLabel barreDeRecherche;
    private JButton recherche;

    /* EDT */
    private Panneau pan;
    private PanneauListePromotion panelPromotion;
    private PanneauListeUtilisateur panelUtilisateur;
    private PanneauListeGroupe panelGroupe;
    private PanneauListeEtudiant panelEtudiant;

    // Constructeur
    public Fenetre(String login, String mdp, String database) throws SQLException, ClassNotFoundException {

        // creation par heritage de la fenetre
        super("Projet d'utilisation de JDBC dans MySQL");
        this.maconnexion = new Connexion(database, "root", "root");

        // mise en page (layout) de la fenetre visible
        this.setSize(950, 850);
        this.setTitle("INTRANET ECE PARIS-LYON");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setVisible(true);

        this.getContentPane().add(menuBar(), BorderLayout.NORTH);

        this.pan = new Panneau();
        this.pan.setVisible(false);
        this.add(this.pan, BorderLayout.CENTER);

        this.panelPromotion = new PanneauListePromotion();
        this.panelPromotion.setVisible(false);
        this.add(this.panelPromotion, BorderLayout.CENTER);

        this.panelUtilisateur = new PanneauListeUtilisateur();
        this.panelUtilisateur.setVisible(false);
        this.add(this.panelUtilisateur, BorderLayout.CENTER);

        this.panelGroupe = new PanneauListeGroupe();
        this.panelGroupe.setVisible(false);
        this.add(this.panelGroupe, BorderLayout.CENTER);

        this.panelEtudiant = new PanneauListeEtudiant();
        this.panelEtudiant.setVisible(false);
        this.add(this.panelEtudiant, BorderLayout.CENTER);

        this.getContentPane().add(p1(), BorderLayout.SOUTH);

        // ajout des listeners
        edtEtudiant.addActionListener(this);
        listePromotions.addActionListener(this);
        listeUtilisateurs.addActionListener(this);
        listeGroupes.addActionListener(this);
        listeEtudiants.addActionListener(this);

        recherche.addActionListener(this);

        // pour fermer la fenetre
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0); // tout fermer												System.exit(0); // tout fermer
            }
        });
    }

    private JMenuBar menuBar() {
        this.menuBar = new JMenuBar();
        this.logo = new JLabel(new ImageIcon(Fenetre.class.getResource("logo.png")));
        this.logo.setPreferredSize(new Dimension(50, 100));

        //Menu Cours
        cours.add(listeCours);
        cours.add(recapCours);
        cours.add(listeUtilisateurs);
        cours.setBackground(new Color(4, 116, 124));

        //Menu Etudiants
        etudiants.add(edtEtudiant);
        etudiants.add(recapEtudiant);
        etudiants.add(coursAnnules);
        etudiants.add(listeEtudiants);
        //etudiants.setBackground(new Color(4, 116, 124));

        //Menu Promotions
        promotions.add(listePromotions);
        promotions.add(listeGroupes);
        promotions.add(listeIntervenants);
        promotions.setBackground(new Color(4, 116, 124));

        //Menu Enseignants
        enseignants.add(edtEnseignant);
        enseignants.add(recapEnseignants);
        enseignants.add(listeEnseignants);
        enseignants.setBackground(new Color(4, 116, 124));

        //Menu Salles
        salles.add(listeSalles);
        salles.setBackground(new Color(4, 116, 124));

        //Ajout des menus dans la barre de menus
        menuBar.add(logo);
        menuBar.add(cours);
        menuBar.add(etudiants);
        menuBar.add(promotions);
        menuBar.add(enseignants);
        menuBar.add(salles);

        return menuBar;
    }

    private JPanel p1() {
        // creation des boutons
        recherche = new JButton("Search");
        recherche.setHorizontalAlignment(JTextField.CENTER);

        // creation des Panneaux
        p1 = new JPanel();

        // creation des textes
        barreDeRechercheTexte = new JTextField();

        // creation des labels
        barreDeRecherche = new JLabel("Saisie du nom", JLabel.CENTER);

        // ajout des objets graphqiues dans les panneaux 
        p1.add(barreDeRecherche);
        p1.add(barreDeRechercheTexte);
        p1.add(recherche);

        // ajout des listeners
        recherche.addActionListener(this);

        p1.setLayout(new GridLayout(1, 1));

        return p1;
    }

    private Panneau pan() throws SQLException, ClassNotFoundException {
        this.pan.remplirEDT();
        return this.pan;
    }

    private PanneauListePromotion panelPromotion() throws SQLException, ClassNotFoundException {
        this.panelPromotion.remplirListe();
        return this.panelPromotion;
    }

    private PanneauListeUtilisateur panelUtilisateur() throws SQLException, ClassNotFoundException {
        this.panelUtilisateur.remplirListe();
        return this.panelUtilisateur;
    }

    private PanneauListeGroupe panelGroupe() throws SQLException, ClassNotFoundException {
        this.panelGroupe.remplirListe();
        return this.panelGroupe;
    }

    private PanneauListeEtudiant panelEtudiant() throws SQLException, ClassNotFoundException {
        this.panelEtudiant.remplirListe();
        return this.panelEtudiant;
    }

    public void setInvisible() {
        this.pan.setVisible(false);
        this.panelPromotion.setVisible(false);
        this.panelUtilisateur.setVisible(false);
        this.panelGroupe.setVisible(false);
        this.panelEtudiant.setVisible(false);
    }

    @Override
    @SuppressWarnings("CallToThreadDumpStack")
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        if (source == edtEtudiant) {
            try {
                setInvisible();
                this.pan = this.pan();
                this.add(this.pan);
                System.out.println("Panneau EDT");
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == listePromotions) {
            try {
                setInvisible();

                this.panelPromotion = this.panelPromotion();
                this.add(this.panelPromotion);
                System.out.println("Panneau Liste Promotions");
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == listeUtilisateurs) {
            try {
                setInvisible();

                this.panelUtilisateur = this.panelUtilisateur();
                this.add(this.panelUtilisateur);
                System.out.println("Panneau Liste Utilisateurs");
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == listeGroupes) {
            try {
                setInvisible();

                this.panelGroupe = this.panelGroupe();
                this.add(this.panelGroupe);
                System.out.println("Panneau Liste Groupes");
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == listeEtudiants) {
            try {
                setInvisible();

                this.panelEtudiant = this.panelEtudiant();
                this.add(this.panelEtudiant);
                System.out.println("Panneau Liste Etudiants");
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
