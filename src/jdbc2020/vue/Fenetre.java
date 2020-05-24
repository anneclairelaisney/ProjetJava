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
    private CardLayout cardLayout;
    private JButton boutonPrecedent, boutonSuivant;
    private JPanel panelGlobal, panelBoutonPrecedent, panelBoutonSuivant;

    /* LISTES */
    private Liste listes;
    private JComboBox<String> cmbChoixList, cmbTypeList;

    // Constructeur
    public Fenetre(String login, String mdp, String database) throws SQLException, ClassNotFoundException {

        // creation par heritage de la fenetre
        super("Projet d'utilisation de JDBC dans MySQL");
        this.maconnexion = new Connexion(database, "root", "root");

        // mise en page (layout) de la fenetre visible
        this.setSize(765, 920);
        this.setTitle("INTRANET ECE PARIS-LYON");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        this.getContentPane().add(menuBar(), BorderLayout.NORTH);
        this.getContentPane().add(p1(), BorderLayout.SOUTH);
        //this.getContentPane().add(new JScrollPane(edt()), BorderLayout.CENTER);
        this.getContentPane().add(panelGlobal(), BorderLayout.CENTER);

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

    /* JPanel contenant le bouton précedent */
    private JPanel panelBoutonPrecedent() {
        panelBoutonPrecedent = new JPanel();
        boutonPrecedent = new JButton("◄");
        panelBoutonPrecedent.add(boutonPrecedent, BorderLayout.CENTER);
        return panelBoutonPrecedent;
    }

    /* JPanel contenant le bouton suivant */
    private JPanel panelBoutonSuivant() {
        panelBoutonSuivant = new JPanel();
        boutonSuivant = new JButton("►");
        panelBoutonSuivant.add(boutonSuivant, BorderLayout.CENTER);
        return panelBoutonSuivant;
    }

    /* JPanel global qui contient cardCreation & cardEDT */
    private JPanel panelGlobal() throws SQLException, ClassNotFoundException {
        cardLayout = new CardLayout();
        panelGlobal = new JPanel();
        panelGlobal.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
        panelGlobal.setBackground(new Color(4, 116, 124));

        javax.swing.border.Border blackline = BorderFactory.createLineBorder(Color.black, 1);

        JPanel jour = new JPanel();
        jour.setPreferredSize(new Dimension(120, 30));
        jour.setBorder(blackline);
        jour.add(new JLabel());
        jour.add(new JLabel(""));
        panelGlobal.add(jour);

        Calendar cal = Calendar.getInstance();

        System.out.println("Jour de la semaine: " + 5);
        for (int i = 1; i <= 7; i++) {
            Calendar calendarNomJourSemaine = Calendar.getInstance();
            calendarNomJourSemaine.set(Calendar.YEAR, 2020);
            calendarNomJourSemaine.set(Calendar.MONTH, 11);
            calendarNomJourSemaine.set(Calendar.DAY_OF_MONTH, 23);
            int semaine = calendarNomJourSemaine.get(Calendar.DAY_OF_WEEK);
            String nomjourSemaine = "";
            switch (semaine) {
                case 1:
                    nomjourSemaine = "Dimanche";
                    break;
                case 2:
                    nomjourSemaine = "Lundi";
                    break;
                case 3:
                    nomjourSemaine = "Mardi";
                    break;
                case 4:
                    nomjourSemaine = "Mercredi";
                    break;
                case 5:
                    nomjourSemaine = "Jeudi";
                    break;
                case 6:
                    nomjourSemaine = "Vendredi";
                    break;
                case 7:
                    nomjourSemaine = "Samedi";
                    break;
            }
            System.out.println(nomjourSemaine);
        }

        JPanel lundi = new JPanel();
        lundi.setPreferredSize(new Dimension(120, 30));
        lundi.setBorder(blackline);
        lundi.add(new JLabel());
        lundi.add(new JLabel("Lundi", JLabel.CENTER));
        panelGlobal.add(lundi);

        JPanel mardi = new JPanel();
        mardi.setPreferredSize(new Dimension(120, 30));
        mardi.setBorder(blackline);
        mardi.add(new JLabel());
        mardi.add(new JLabel("Mardi", JLabel.CENTER));
        panelGlobal.add(mardi);

        JPanel mercredi = new JPanel();
        mercredi.setPreferredSize(new Dimension(120, 30));
        mercredi.setBorder(blackline);
        mercredi.add(new JLabel());
        mercredi.add(new JLabel("Mercredi", JLabel.CENTER));
        panelGlobal.add(mercredi);

        JPanel jeudi = new JPanel();
        jeudi.setPreferredSize(new Dimension(120, 30));
        jeudi.setBorder(blackline);
        jeudi.add(new JLabel());
        jeudi.add(new JLabel("Jeudi", JLabel.CENTER));
        panelGlobal.add(jeudi);

        JPanel vendredi = new JPanel();
        vendredi.setPreferredSize(new Dimension(120, 30));
        vendredi.setBorder(blackline);
        vendredi.add(new JLabel());
        vendredi.add(new JLabel("Vendredi", JLabel.CENTER));
        panelGlobal.add(vendredi);
        int debut = 7;
        int fin = 8;
        for (int i = 0; i < 84; i++) {
            SeanceLabel seance = new SeanceLabel();
            seance.remplirSeance(2);
            if (i % 6 == 0) {
                debut += 1;
                fin += 1;
                JPanel heure = new JPanel();
                heure.setPreferredSize(new Dimension(120, 50));
                heure.setBorder(blackline);
                heure.add(new JLabel());
                heure.add(new JLabel(debut + ":00 - " + fin + ":00", JLabel.CENTER));
                panelGlobal.add(heure);

            } else {
                if (seance.getSeance().getHeureDebut() == debut) {
                    seance.setBorder(blackline);
                    seance.setPreferredSize(new Dimension(120, 50));
                    panelGlobal.add(seance);
                } else {
                    JPanel vide = new JPanel();
                    vide.setPreferredSize(new Dimension(120, 50));
                    vide.setBorder(blackline);
                    panelGlobal.add(vide);
                }
            }
        }
        return panelGlobal;
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
        if (source == listeEtudiants){
            System.out.println("non");
        }
        // tester cas de la commande evenementielle
        if (source == recherche) {
            ArrayList<String> liste;
            try {
                try {
                    // tentative de connexion si les 4 attributs sont remplis
<<<<<<< HEAD
                    //maconnexion = new Connexion("jps", "root", "");
                    maconnexion = new Connexion(nameBDDTexte.getText(), "root", "");
=======
                    this.maconnexion = new Connexion("jdbc2020", "root", "root");
<<<<<<< HEAD
                    System.out.println("Connexion dans BDD reussie");
>>>>>>> master

=======
                    this.listes = new Liste(this.maconnexion);
                    for (Etudiant u : listes.etudiants) {
                        if (u.getPrenom().equals(barreDeRechercheTexte.getText())) {
                            System.out.println("OUI");
                        }
                        if (u.getNom().equals(barreDeRechercheTexte.getText())) {
                            System.out.println("OUI");
                        }
                    }
>>>>>>> master
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
