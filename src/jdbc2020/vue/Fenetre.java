/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.vue;

import jdbc2020.controleur.*;
/*
 * 
 * Librairies importées
 */
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Border;
import jdbc2020.dao.DAO;
import jdbc2020.dao.UtilisateurDAO;
import jdbc2020.modele.Utilisateur;

/**
 *
 * Affiche dans la fenetre graphique les champs de tables et les requetes de la
 * BDD
 *
 * @author segado
 */
public class Fenetre extends JFrame implements ActionListener, ItemListener {

    //Attributs
    private Connexion maconnexion;
    private final JPanel p0, p1, edt;
    private JLabel logo;
    private JMenuBar menuBar;
    private JMenu cours = new JMenu("Cours"),
            etudiants = new JMenu("Etudiants"),
            promotions = new JMenu("Promotions"),
            enseignants = new JMenu("Enseignants"),
            salles = new JMenu("Salles");

    private JMenuItem listeCours = new JMenuItem("Emploi du temps"),
            recapCours = new JMenuItem("Récapitulatif des cours");

    private JMenuItem edtEtudiant = new JMenuItem("Emploi du temps"),
            recapEtudiant = new JMenuItem("Récapitulatif des cours"),
            coursAnnules = new JMenuItem("Cours annulés"),
            listeEtudiants = new JMenuItem("Liste des étudiants");

    private JMenuItem listePromotions = new JMenuItem("Liste des Promotions"),
            listeGroupes = new JMenuItem("Liste des groupes"),
            listeIntervenants = new JMenuItem("Liste des intervenants");

    private JMenuItem edtEnseignant = new JMenuItem("Emploi du temps"),
            recapEnseignants = new JMenuItem("Récapitulatif des cours"),
            listeEnseignants = new JMenuItem("Liste des Enseignants");

    private JMenuItem listeSalles = new JMenuItem("Listes des salles");
    private final JTextField barreDeRechercheTexte;
    private final JLabel barreDeRecherche;
    private final JButton recherche;

    // Constructeur
    public Fenetre(String login, String mdp, String database) throws SQLException, ClassNotFoundException {

        // creation par heritage de la fenetre
        super("Projet d'utilisation de JDBC dans MySQL");
        this.maconnexion = new Connexion(database, "root", "root");

        // mise en page (layout) de la fenetre visible
        this.setSize(800, 600);
        this.setTitle("INTRANET ECE PARIS-LYON");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setVisible(true);

        this.menuBar = new JMenuBar();
        this.logo = new JLabel(new ImageIcon(Fenetre.class.getResource("logo.png")));
        this.logo.setPreferredSize(new Dimension(50, 100));
        
        // creation des boutons
        recherche = new JButton("Search");
        recherche.setHorizontalAlignment(JTextField.CENTER);
        
        // creation des Panneaux
        p0 = new JPanel();
        p1 = new JPanel();
        edt = new JPanel();
        
        // creation des textes
        barreDeRechercheTexte = new JTextField();
        
        // creation des labels
        barreDeRecherche = new JLabel("Saisie du nom", JLabel.CENTER);
        
        // ajout des objets graphqiues dans les panneaux 
        p1.add(barreDeRecherche);
        p1.add(barreDeRechercheTexte);
        p1.add(recherche);
        
        javax.swing.border.Border blackline = BorderFactory.createLineBorder(Color.black, 1);
        for (int i = 0; i < 65; i++) {
            JPanel ptest = new JPanel();
            ptest.setBorder(blackline);
            edt.add(ptest);
        }

        // mise en page des panneaux
        p0.setLayout(new GridLayout(1, 3));
        p1.setLayout(new GridLayout(1, 1));
        
        // ajout des listeners
        recherche.addActionListener(this);

        this.add(p0);
        this.add(p1, BorderLayout.NORTH);
        edt.setLayout(new GridLayout(13, 5));
        this.add(edt, BorderLayout.CENTER);
        this.initMenu();
        
        // pour fermer la fenetre
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0); // tout fermer												System.exit(0); // tout fermer
            }
        });
    }

    private void initMenu() {
        //Menu Cours
        cours.add(listeCours);
        cours.add(recapCours);
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

        //Ajout de la barre de menus sur la fenêtre
        this.setJMenuBar(menuBar);
    }

    // Initialisation des tables
    private void remplirTables() {
        maconnexion.ajouterTable("COURS");
        maconnexion.ajouterTable("ENSEIGNANT");
        maconnexion.ajouterTable("ETUDIANT");
        maconnexion.ajouterTable("GROUPE");
        maconnexion.ajouterTable("PROMOTION");
        maconnexion.ajouterTable("SALLE");
        maconnexion.ajouterTable("SEANCE");
        maconnexion.ajouterTable("SEANCE_ENSEIGNANTS");
        maconnexion.ajouterTable("SEANCE_GROUPES");
        maconnexion.ajouterTable("SEANCE_SALLES");
        maconnexion.ajouterTable("SITE");
        maconnexion.ajouterTable("TYPE_COURS");
        maconnexion.ajouterTable("UTILISATEUR");

    }

    public ArrayList<String> afficherRes(String requeteSelectionnee) throws SQLException {
        ArrayList<String> liste = null;
        return liste;
    }

    @Override
    @SuppressWarnings("CallToThreadDumpStack")
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();

        // tester cas de la commande evenementielle
        if (source == recherche) {
            ArrayList<String> liste;
            try {
                try {
                    // tentative de connexion si les 4 attributs sont remplis
                    this.maconnexion = new Connexion("jdbc2020", "root", "root");
                    System.out.println("Connexion dans BDD reussie");

                } catch (ClassNotFoundException cnfe) {
                    System.out.println("Connexion echouee : probleme de classe");
                    cnfe.printStackTrace();
                }
            } catch (SQLException exc) {
                System.out.println("Connexion echouee : probleme SQL");
                exc.printStackTrace();
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
