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
import static java.awt.BorderLayout.CENTER;
import java.util.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.Border;

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

    /* RECHERCHE */
    private JPanel p1;
    private JTextField barreDeRechercheTexte;
    private JLabel barreDeRecherche;
    private JButton recherche;

    /* EDT */
    ;
    private JPanel heures;
    private Panneau pan;

    /* LISTES */
    private Liste listes;

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
        this.setResizable(false);
        this.setVisible(true);

        pan = new Panneau();

        this.getContentPane().add(menuBar(), BorderLayout.NORTH);
        this.getContentPane().add(heures(), BorderLayout.WEST);
        this.add(pan);
        this.getContentPane().add(p1(), BorderLayout.SOUTH);

        // ajout des listeners
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

    private JPanel heures() {
        this.heures = new JPanel();
        javax.swing.border.Border blackline = BorderFactory.createLineBorder(Color.white, 1);
        JLabel day = new JLabel();
        day.setBorder(blackline);
        day.setBackground(new Color(4, 116, 124));
        day.setPreferredSize(new Dimension(160, 50));
        day.add(new JLabel());
        this.heures.add(day);

        int debut = 7;
        int fin = 8;
        for (int i = 0; i < 13; i++) {
            debut += 1;
            fin += 1;
            String h = debut + ":00 - " + fin + ":00";
            JLabel heure = new JLabel(h, JLabel.CENTER);
            heure.setBorder(blackline);
            Font font = new Font("Arial", Font.BOLD, 14);
            heure.setFont(font);
            heure.setForeground(Color.WHITE);
            heure.setBackground(new Color(4, 116, 124));
            heure.setPreferredSize(new Dimension(160, 50));
            this.heures.add(heure);
        }
        this.heures.setLayout(new GridLayout(14, 0, 0, 2));
        this.heures.setBackground(new Color(4, 116, 124));
        return this.heures;
    }

// Initialisation des tables
    private void remplirTables() {
        this.listes.listePromotions();
        this.listes.listeGroupes();
        this.listes.listeEtudiants();
        this.listes.listeEnseignants();
        this.listes.listeSalles();
    }

    @Override
    @SuppressWarnings("CallToThreadDumpStack")
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        if (source == listeEtudiants) {
            System.out.println("non");
        }
        // tester cas de la commande evenementielle
        if (source == recherche) {
            ArrayList<String> liste;
            try {
                try {
                    // tentative de connexion si les 4 attributs sont remplis
                    this.maconnexion = new Connexion("jdbc2020", "root", "root");
                    this.listes = new Liste(this.maconnexion);
                    for (Etudiant u : listes.etudiants) {
                        if (u.getPrenom().equals(barreDeRechercheTexte.getText())) {
                            System.out.println("OUI");
                        }
                        if (u.getNom().equals(barreDeRechercheTexte.getText())) {
                            System.out.println("OUI");
                        }
                    }
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
