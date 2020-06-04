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
import javax.swing.*;
import java.sql.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc2020.dao.CoursDAO;
import jdbc2020.dao.EnseignantDAO;
import jdbc2020.dao.EtudiantDAO;
import jdbc2020.dao.GroupeDAO;
import jdbc2020.dao.PromotionDAO;
import jdbc2020.dao.SalleDAO;
import jdbc2020.dao.SeanceDAO;
import jdbc2020.dao.TypeCoursDAO;
import jdbc2020.dao.UtilisateurDAO;
import jdbc2020.modele.Cours;
import jdbc2020.modele.Enseignant;
import jdbc2020.modele.Etudiant;
import jdbc2020.modele.Groupe;
import jdbc2020.modele.Promotion;
import jdbc2020.modele.Salle;
import jdbc2020.modele.Seance;
import jdbc2020.modele.TypeCours;
import jdbc2020.modele.Utilisateur;

/**
 *
 * Affiche dans la fenetre graphique les champs de tables et les requetes de la
 * BDD
 *
 * @author apple
 */
public class Fenetre extends JFrame implements ActionListener, ItemListener {

    //Attributs
    /* CONNEXION */
    private Connexion maconnexion;
    private Utilisateur utilisateur;
    private Utilisateur user;
    private int droit_acces;
    private int id_salle = 1;
    private int id_semaine;

    private CardLayout cardLayout;
    private JPanel panelTout, panelEmploiduTemps, panelMiseAJour, panelMiseAJourBoutons;
    private JPanel choix, panelGeneral;
    private JButton edt, maj;
    private String[] listContent = {"EDT", "MAJ"};
    ;

    /* MENU */
    private JLabel logo;
    private JMenuBar menuBar;
    private JMenu cours = new JMenu("Cours"),
            etudiants = new JMenu("Etudiants"),
            promotions = new JMenu("Promotions"),
            enseignants = new JMenu("Enseignants"),
            salles = new JMenu("Salles");

    private JMenuItem accueil = new JMenuItem("Page d'accueil"),
            listeCours = new JMenuItem("Emploi du temps"),
            listeSeances = new JMenuItem("Liste des séances"),
            recapCours = new JMenuItem("Récapitulatif des cours");

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

    private JMenuItem edtSalle = new JMenuItem("Emploi du temps"),
            listeSalles = new JMenuItem("Listes des salles");

    /* RECHERCHE */
    private JScrollPane jsp;
    private JViewport jvp;
    private JPanel p1;
    private JComboBox<String> cmbListeGrille, cmbChoixType, cmbElement;
    private JPanel barreDeRecherche;
    private JButton recherche;

    /* EDT */
    private JPanel north;
    private Panneau pan;
    private PanneauEDTEnseignant panEnseignant;
    private PanneauEDTSalle panSalle;
    private PanneauListePromotion panelPromotion;
    private PanneauListeGroupe panelGroupe;
    private PanneauListeEtudiant panelEtudiant;
    private PanneauListeIntervenants panelIntervenants;
    private PanneauListeSalle panelSalle;
    private PanneauListeEnseignant panelEnseignant;
    private PanneauListeSeance panelSeance;
    private PanneauRecapCoursEnseignant panelRecap;

    /* Ajout */
    private JPanel panelGlobal, panelEtat, panelMajSeance, panelMajSeance2;
    private JButton ajouter, modifier, changer, semaine;
    private JFrame ajoutSeance, modifSeance, changeSeance;
    private JLabel seanceEtat, majSeance, dateSeance, heureDebutSeance, heureFinSeance, etatSeance, groupesSeance, enseignantsSeance, sallesSeance, coursSeance, typeCoursSeance;
    private JTextField etatTexte;
    private JFormattedTextField heureDebutTexte, heureFinTexte;
    private JComboBox<Integer> cmbSeance;
    private JComboBox<String> cmbCoursSeance, cmbTypeCoursSeance, cmbEtat, cmbSeanceEtat;
    private ArrayList<JCheckBox> groupeE, groupeG, groupeS;
    private JButton validerSeance, modifierSeance, changerSeance;
    private JDateTextField dateChoix;

    // Constructeur
    public Fenetre(String login, String mdp, String database) throws SQLException, ClassNotFoundException {
        super("Projet d'utilisation de JDBC dans MySQL");
        this.maconnexion = new Connexion(database, "root", "root");
        this.utilisateur = this.init(login, mdp);

        this.setSize(1200, 750);
        this.setTitle("INTRANET ECE PARIS-LYON");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setVisible(true);
        this.getContentPane().add(choix(), BorderLayout.NORTH);
        this.getContentPane().add(panelTout(), BorderLayout.CENTER);

        this.ajoutListeners();
        this.pan = new Panneau();
        this.panEnseignant = new PanneauEDTEnseignant();
        this.panSalle = new PanneauEDTSalle();
        this.panelPromotion = new PanneauListePromotion();
        this.panelGroupe = new PanneauListeGroupe();
        this.panelEtudiant = new PanneauListeEtudiant();
        this.panelIntervenants = new PanneauListeIntervenants();
        this.panelSalle = new PanneauListeSalle();
        this.panelEnseignant = new PanneauListeEnseignant();
        this.panelSeance = new PanneauListeSeance();
        this.panelRecap = new PanneauRecapCoursEnseignant();

        // pour fermer la fenetre
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });
    }

    public Utilisateur init(String login, String mdp) throws SQLException {
        System.out.println(login);
        utilisateur = getUtilisateur(login, mdp);
        switch (utilisateur.getDroit()) {
            case 1:
                System.out.println("Vous etes administrateur");
                droit_acces = 1;
                break;
            case 2:
                System.out.println("Vous etes referent");
                droit_acces = 2;
                break;
            case 3:
                System.out.println("Vous etes enseignant");
                droit_acces = 3;
                break;
            case 4:
                System.out.println("Vous etes etudiant");
                droit_acces = 4;
                break;
        }
        return utilisateur;
    }

    // ajout des listeners
    public void ajoutListeners() {
        accueil.addActionListener(this);
        ajouter.addActionListener(this);
        modifier.addActionListener(this);
        changer.addActionListener(this);
        edtEtudiant.addActionListener(this);
        edtEnseignant.addActionListener(this);
        edtSalle.addActionListener(this);
        listePromotions.addActionListener(this);
        listeGroupes.addActionListener(this);
        listeEtudiants.addActionListener(this);
        listeIntervenants.addActionListener(this);
        listeSalles.addActionListener(this);
        listeEnseignants.addActionListener(this);
        listeSeances.addActionListener(this);
        //recherche.addActionListener(this);
        recapEnseignants.addActionListener(this);
    }

    private Utilisateur getUtilisateur(String login, String mdp) throws SQLException {
        UtilisateurDAO utilisateurdao = new UtilisateurDAO(this.maconnexion);
        Utilisateur utilisateur = utilisateurdao.find(login);
        return utilisateur;
    }

    private JPanel choix() {
        choix = new JPanel();
        choix.setBackground(new Color(4, 116, 124));
        edt = new JButton("Emploi du Temps");
        edt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(panelTout, listContent[0]);
            }
        });
        maj = new JButton("Mise à jour");
        maj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cardLayout.show(panelTout, listContent[1]);
            }
        });
        choix.add(edt);
        choix.add(maj);
        return choix;
    }

    private JPanel panelTout() throws SQLException, ClassNotFoundException {
        cardLayout = new CardLayout();
        panelTout = new JPanel();
        panelTout.setLayout(cardLayout);
        panelTout.setBackground(new Color(4, 116, 124));
        panelTout.add(panelEmploiduTemps(), listContent[0]);
        panelTout.add(panelMiseAJour(), listContent[1]);

        panelTout.setVisible(true);
        return panelTout;
    }

    // EMPLOI DU TEMPS
    private JPanel panelEmploiduTemps() throws SQLException, ClassNotFoundException {
        panelEmploiduTemps = new JPanel();
        panelEmploiduTemps.setLayout(new BorderLayout());
        panelEmploiduTemps.add(north(), BorderLayout.NORTH);
        panelEmploiduTemps.add(panelGlobal(), BorderLayout.CENTER);
        return panelEmploiduTemps;
    }

    private JPanel north() {
        north = new JPanel();
        north.add(this.menuBar());
        menuBar.setLayout(new FlowLayout());
        north.add(this.p1());
        p1.setLayout(new FlowLayout());
        p1.setBackground(new Color(4, 116, 124));
        JPanel semaines = new JPanel();

        setSize(930, 610);

        for (int i = 1; i <= 52; i++) {
            int a = i;
            semaine = new JButton(a + "");
            semaine.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    try {
                        pan.removeAll();
                        id_semaine = a;
                        pan = pan(user.getEmail(), id_semaine);
                        panelGlobal.removeAll();
                        panelGlobal.add(pan);
                        System.out.println("Panneau EDT Semaine : " + a);

                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            semaines.add(semaine);
        }

        semaines.setLayout(new GridLayout(2, 26));
        semaines.setBackground(new Color(4, 116, 124));
        north.add(semaines);

        north.setPreferredSize(new Dimension(300, 130));
        north.setBackground(new Color(4, 116, 124));
        north.setLayout(new GridLayout(3, 1));
        return north;
    }

    private JMenuBar menuBar() {
        this.menuBar = new JMenuBar();
        this.menuBar.setBackground(new Color(4, 116, 124));
        if (droit_acces == 1 || droit_acces == 2) {
            this.logo = new JLabel(new ImageIcon(Fenetre.class.getResource("logo.png")));
            this.logo.setPreferredSize(new Dimension(50, 100));

            //Menu Cours
            cours.add(accueil);
            cours.add(listeCours);
            cours.add(listeSeances);
            cours.add(recapCours);
            cours.setBackground(new Color(4, 116, 124));

            //Menu Etudiants
            etudiants.add(edtEtudiant);
            etudiants.add(recapEtudiant);
            etudiants.add(coursAnnules);
            etudiants.add(listeEtudiants);
            etudiants.setBackground(new Color(4, 116, 124));

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
            salles.add(edtSalle);
            salles.add(listeSalles);
            salles.setBackground(new Color(4, 116, 124));

            //Ajout des menus dans la barre de menus
            //menuBar.add(logo);
            menuBar.add(cours);
            menuBar.add(etudiants);
            menuBar.add(promotions);
            menuBar.add(enseignants);
            menuBar.add(salles);

            return menuBar;
        } else {
            return menuBar;
        }
    }

    public void scroll(JPanel panneau) {
        this.jsp = new JScrollPane(panneau);
        this.jvp = new JViewport();
        this.jvp = this.jsp.getViewport();
        JScrollBar vjsp = jsp.getVerticalScrollBar();
        //vjsp.setBounds(1200, 750, 300, 100);
        this.add(vjsp);
        vjsp.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
            }
        });
        this.jvp.setViewPosition(new Point(30, 200));
        this.add(this.jsp);
        this.jsp.setVerticalScrollBarPolicy(jsp.VERTICAL_SCROLLBAR_ALWAYS);
        //panneau.setVisible(false);
    }

    private JPanel p1() {
        if (droit_acces == 1 || droit_acces == 2) {
            // creation des boutons
            recherche = new JButton("Search");
            recherche.setHorizontalAlignment(JTextField.CENTER);
            recherche.setBounds(10, 10, 10, 10);

            p1 = new JPanel();

            String[] listegrille = {"En liste ", "En grille"};
            cmbListeGrille = new JComboBox<String>(listegrille);

            String[] choixType = {"Etudiant", "Enseignant", "Salle"};
            cmbChoixType = new JComboBox<String>(choixType);
            cmbElement = new JComboBox<String>();
            cmbChoixType.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    try {
                        cmbElement.removeAllItems();
                        switch (cmbChoixType.getSelectedIndex()) {
                            case 0:
                                for (Etudiant e : etudiantsDao()) {
                                    getBarreRecherche().addItem(e.getNom() + " " + e.getPrenom());
                                }
                                break;
                            case 1:
                                for (Enseignant e : enseignantsDao()) {
                                    getBarreRecherche().addItem(e.getNom() + " " + e.getPrenom());
                                }
                                break;
                            default:
                                for (Salle s : sallesDao()) {
                                    getBarreRecherche().addItem(s.getNom());
                                }
                                break;
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            // ajout des objets graphqiues dans les panneaux 
            p1.add(cmbListeGrille);
            p1.add(cmbChoixType);
            p1.add(cmbElement);

            // ajout des listeners
            recherche.addActionListener(this);
            p1.add(recherche);

            p1.setLayout(new GridLayout(1, 3));

            return p1;
        } else {
            return null;
        }
    }

    private JPanel panelGlobal() throws SQLException, ClassNotFoundException {
        panelGlobal = new JPanel();
        panelGlobal.setVisible(true);
        panelGlobal.setBackground(new Color(4, 116, 124));
        panelGlobal.setLayout(null);
        return panelGlobal;
    }

    private Panneau pan(String login, int semaine) throws SQLException, ClassNotFoundException, Exception {
        pan.removeAll();
        this.pan.remplirEDT(login, semaine);
        return this.pan;
    }

    private PanneauEDTEnseignant panEnseignant(String login, int semaine) throws SQLException, ClassNotFoundException, Exception {
        panEnseignant.removeAll();
        this.panEnseignant.remplirEDT(login, semaine);
        return this.panEnseignant;
    }

    private PanneauEDTSalle panSalle(int id_salle, int id_semaine) throws SQLException, ClassNotFoundException, Exception {
        panEnseignant.removeAll();
        this.panSalle.remplirEDT(id_salle, id_semaine);
        return this.panSalle;
    }

    private PanneauListePromotion panelPromotion() throws SQLException, ClassNotFoundException, Exception {
        this.panelPromotion.remplirListe();
        return this.panelPromotion;
    }

    private PanneauListeGroupe panelGroupe() throws SQLException, ClassNotFoundException, Exception {
        this.panelGroupe.remplirListe();
        return this.panelGroupe;
    }

    private PanneauListeEtudiant panelEtudiant() throws SQLException, ClassNotFoundException, Exception {
        /*scrollPane.setPreferredSize(new Dimension(300, 400));
        scrollPane.setAutoscrolls(true);
        scrollPane.setVisible(true);*/
        this.panelEtudiant.remplirListe();
        //this.panelEtudiant.add(scrollPane);
        return this.panelEtudiant;
    }

    private PanneauListeIntervenants panelIntervenants() throws SQLException, ClassNotFoundException, Exception {
        this.panelIntervenants.remplirListe();
        return this.panelIntervenants;
    }

    private PanneauListeSalle panelSalle() throws SQLException, ClassNotFoundException, Exception {
        this.panelSalle.remplirListe();
        return this.panelSalle;
    }

    private PanneauListeEnseignant panelEnseignant() throws SQLException, ClassNotFoundException, Exception {
        this.panelEnseignant.remplirListe();
        return this.panelEnseignant;
    }

    private PanneauListeSeance panelSeance() throws SQLException, ClassNotFoundException, Exception {
        this.panelSeance.remplirListe();
        return this.panelSeance;
    }

    private PanneauRecapCoursEnseignant panelRecap() throws SQLException, ClassNotFoundException, Exception {
        this.panelRecap.remplirListe();
        return this.panelRecap;
    }

    public void setInvisible() {
        this.pan.setVisible(false);
        this.panelEnseignant.setVisible(false);
        this.panelPromotion.setVisible(false);
        this.panelGroupe.setVisible(false);
        this.panelEtudiant.setVisible(false);
        this.panelIntervenants.setVisible(false);
        this.panelSalle.setVisible(false);
        this.panelSeance.setVisible(false);
        this.panelRecap.setVisible(false);
        this.panelGlobal.setVisible(false);
    }

    // MISE A JOUR
    private JPanel panelMiseAJour() {
        panelMiseAJour = new JPanel();
        panelMiseAJour.setLayout(new BorderLayout());
        panelMiseAJour.setBackground(new Color(4, 116, 124));
        panelMiseAJour.add(panelMiseAJourBoutons(), BorderLayout.NORTH);
        panelMiseAJour.add(new JLabel(new ImageIcon(Fenetre.class.getResource("accueil.png"))), BorderLayout.CENTER);
        return panelMiseAJour;
    }

    private JPanel panelMiseAJourBoutons() {
        panelMiseAJourBoutons = new JPanel();
        panelMiseAJourBoutons.setBackground(new Color(4, 116, 124));

        ajouter = new JButton("Ajouter");
        modifier = new JButton("Modifier");
        changer = new JButton("Changer Etat");

        panelMiseAJourBoutons.add(ajouter);
        panelMiseAJourBoutons.add(modifier);
        panelMiseAJourBoutons.add(changer);

        return panelMiseAJourBoutons;
    }

    /* Ajouter une séance de cours en lui affectant, si possible, toutes les informations nécessaires : la date (sauf samedi et
dimanche), l’heure de début et de fin (en respect des créneaux horaires d’ouverture de l’école), le(s) groupe(s) et le(s) enseignant(s) 
    disponible(s) à ce créneau horaire, la (les) salle(s) disponible(s) dont la capacité est suffisante, l’état (« en cours de validation » ou 
    « validé »), le cours et le type de cours. 
    Remarque : pour la même séance, il ne peut pas y avoir de doublon de groupe, d’enseignant et de salle.*/
    public JFrame ajoutSeance() throws Exception {
        ajoutSeance = new JFrame();
        ajoutSeance.setSize(400, 300);
        ajoutSeance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ajoutSeance.setTitle("Ajout d'une séance");
        ajoutSeance.setLayout(new BorderLayout());
        ajoutSeance.setLocationRelativeTo(null);
        ajoutSeance.setResizable(true);
        ajoutSeance.setVisible(true);

        panelMajSeance = new JPanel();
        panelMajSeance.setLayout(new GridLayout(9, 2));

        ArrayList<Salle> salles = new ArrayList<>();
        ArrayList<Enseignant> enseignants = new ArrayList<>();
        ArrayList<Groupe> groupes = new ArrayList<>();
        SalleDAO salledao = new SalleDAO(this.maconnexion);
        salles = salledao.getAllSalles();
        EnseignantDAO enseignantdao = new EnseignantDAO(this.maconnexion);
        enseignants = enseignantdao.getAllTeachers();
        GroupeDAO groupeDao = new GroupeDAO(this.maconnexion);
        groupes = new ArrayList<>();
        groupes = groupeDao.getAllGroupes();

        groupeG = new ArrayList<JCheckBox>();
        JPanel panelG = new JPanel();
        groupeE = new ArrayList<JCheckBox>();
        JPanel panelE = new JPanel();
        groupeS = new ArrayList<JCheckBox>();
        JPanel panelS = new JPanel();

        for (Groupe g : groupes) {
            JCheckBox a = new JCheckBox(g.getNom());
            this.groupeG.add(a);
            panelG.add(a);
        }
        panelG.setLayout(new GridLayout(groupes.size() / 4, 4));

        for (Enseignant e : enseignants) {
            JCheckBox a = new JCheckBox(e.getNom());
            this.groupeE.add(a);
            panelE.add(a);
        }
        panelE.setLayout(new GridBagLayout());

        for (Salle s : salles) {
            JCheckBox a = new JCheckBox(s.getNom());
            this.groupeS.add(a);
            panelS.add(a);
        }
        panelS.setLayout(new GridBagLayout());

        dateSeance = new JLabel("Date : ", JLabel.CENTER);
        dateChoix = new JDateTextField();
        heureDebutSeance = new JLabel("Heure de début (int): ", JLabel.CENTER);
        heureDebutTexte = new JFormattedTextField(NumberFormat.getIntegerInstance());
        heureFinSeance = new JLabel("Heure de fin (int): ", JLabel.CENTER);
        heureFinTexte = new JFormattedTextField(NumberFormat.getIntegerInstance());
        groupesSeance = new JLabel("Groupes : ", JLabel.CENTER);
        enseignantsSeance = new JLabel("Enseignants : ", JLabel.CENTER);
        sallesSeance = new JLabel("Salles :", JLabel.CENTER);
        etatSeance = new JLabel("Etat : ", JLabel.CENTER);
        etatTexte = new JTextField(2);
        coursSeance = new JLabel("Cours : ", JLabel.CENTER);
        cmbCoursSeance = new JComboBox<String>();
        typeCoursSeance = new JLabel("Type de cours : ", JLabel.CENTER);
        cmbTypeCoursSeance = new JComboBox<String>();

        panelMajSeance.add(dateSeance);
        panelMajSeance.add(dateChoix);
        panelMajSeance.add(heureDebutSeance);
        panelMajSeance.add(heureDebutTexte);
        panelMajSeance.add(heureFinSeance);
        panelMajSeance.add(heureFinTexte);
        panelMajSeance.add(groupesSeance);
        panelMajSeance.add(panelG);
        panelMajSeance.add(enseignantsSeance);
        panelMajSeance.add(panelE);
        panelMajSeance.add(sallesSeance);
        panelMajSeance.add(panelS);
        panelMajSeance.add(etatSeance);
        panelMajSeance.add(etatTexte);
        panelMajSeance.add(coursSeance);
        panelMajSeance.add(cmbCoursSeance);
        panelMajSeance.add(typeCoursSeance);
        panelMajSeance.add(cmbTypeCoursSeance);

        panelMajSeance2 = new JPanel();
        panelMajSeance2.setLayout(new FlowLayout());
        validerSeance = new JButton("✓");
        validerSeance.setPreferredSize(new Dimension(50, 50));

        panelMajSeance2.add(validerSeance);
        ajoutSeance.add(panelMajSeance, BorderLayout.CENTER);
        ajoutSeance.add(panelMajSeance2, BorderLayout.SOUTH);

        return ajoutSeance;
    }

    /* Modifier le cours (son nom ou son type) dans une séance de cours */
    public JFrame modifSeance() throws Exception {
        modifSeance = new JFrame();
        modifSeance.setSize(400, 300);
        modifSeance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        modifSeance.setTitle("Modifier une séance");
        modifSeance.setLayout(new BorderLayout());
        modifSeance.setLocationRelativeTo(null);
        modifSeance.setResizable(true);
        modifSeance.setVisible(true);

        panelMajSeance = new JPanel();
        panelMajSeance.setLayout(new GridLayout(3, 2));

        majSeance = new JLabel("Seance : ", JLabel.CENTER);
        cmbSeance = new JComboBox<Integer>();
        coursSeance = new JLabel("Cours : ", JLabel.CENTER);
        cmbCoursSeance = new JComboBox<String>();
        typeCoursSeance = new JLabel("Type de cours : ", JLabel.CENTER);
        cmbTypeCoursSeance = new JComboBox<String>();

        panelMajSeance.add(majSeance);
        panelMajSeance.add(cmbSeance);
        panelMajSeance.add(coursSeance);
        panelMajSeance.add(cmbCoursSeance);
        panelMajSeance.add(typeCoursSeance);
        panelMajSeance.add(cmbTypeCoursSeance);

        panelMajSeance2 = new JPanel();

        panelMajSeance2.setLayout(
                new FlowLayout());
        modifierSeance = new JButton("✓");

        modifierSeance.setPreferredSize(
                new Dimension(50, 50));

        panelMajSeance2.add(modifierSeance);

        modifSeance.add(panelMajSeance, BorderLayout.CENTER);
        modifSeance.add(panelMajSeance2, BorderLayout.SOUTH);

        return modifSeance;
    }

    public JFrame changeSeance() throws Exception {
        changeSeance = new JFrame();
        changeSeance.setSize(250, 120);
        changeSeance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        changeSeance.setTitle("Changer l'Etat d'une séance");
        changeSeance.setLayout(new BorderLayout());
        changeSeance.setLocationRelativeTo(null);
        changeSeance.setResizable(true);
        changeSeance.setVisible(true);
        changeSeance.setBackground(Color.YELLOW);

        panelEtat = new JPanel();
        cmbSeanceEtat = new JComboBox<String>();
        seanceEtat = new JLabel("Seance : ", JLabel.CENTER);

        String[] messageStrings = {"Valider", "Annuler"};
        cmbEtat = new JComboBox<String>(messageStrings);
        changerSeance = new JButton("✓");

        JPanel panelEtat2 = new JPanel();
        changerSeance.setPreferredSize(new Dimension(20, 20));

        panelEtat.add(seanceEtat);
        panelEtat.add(cmbSeanceEtat);
        panelEtat.add(cmbEtat);
        panelEtat2.add(changerSeance);
        panelEtat.setLayout(new FlowLayout());

        changeSeance.add(panelEtat, BorderLayout.CENTER);
        changeSeance.add(panelEtat2, BorderLayout.SOUTH);

        return changeSeance;
    }

    public ArrayList<Utilisateur> utilisateurDao() throws Exception {
        UtilisateurDAO utilisateurdao = new UtilisateurDAO(this.maconnexion);
        ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
        utilisateurs = utilisateurdao.getAllUsers();
        return utilisateurs;
    }

    public ArrayList<Salle> sallesDao() throws Exception {
        SalleDAO salledao = new SalleDAO(this.maconnexion);
        ArrayList<Salle> salles = new ArrayList<Salle>();
        salles = salledao.getAllSalles();
        return salles;
    }

    public ArrayList<Enseignant> enseignantsDao() throws Exception {
        EnseignantDAO enseignantdao = new EnseignantDAO(this.maconnexion);
        ArrayList<Enseignant> enseignants = new ArrayList<Enseignant>();
        enseignants = enseignantdao.getAllTeachers();
        return enseignants;
    }

    public ArrayList<Etudiant> etudiantsDao() throws Exception {
        EtudiantDAO etudiantdao = new EtudiantDAO(this.maconnexion);
        ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
        etudiants = etudiantdao.getAllStudents();
        return etudiants;
    }

    public ArrayList<Promotion> promotionsDao() throws Exception {
        PromotionDAO promotionDao = new PromotionDAO(this.maconnexion);
        ArrayList<Promotion> promotions = new ArrayList<>();
        promotions = promotionDao.getAllPromotions();
        return promotions;
    }

    public ArrayList<Groupe> groupesDao() throws Exception {
        GroupeDAO groupeDao = new GroupeDAO(this.maconnexion);
        ArrayList<Groupe> groupes = new ArrayList<>();
        groupes = groupeDao.getAllGroupes();
        return groupes;
    }

    public ArrayList<Seance> seancesDao() throws Exception {
        SeanceDAO seancedao = new SeanceDAO(this.maconnexion);
        ArrayList<Seance> seances = new ArrayList<>();
        seances = seancedao.getAllSeances();
        return seances;
    }

    public ArrayList<Cours> coursDao() throws Exception {
        CoursDAO coursdao = new CoursDAO(this.maconnexion);
        ArrayList<Cours> cours = new ArrayList<>();
        cours = coursdao.getAllCours();
        return cours;
    }

    public ArrayList<TypeCours> typeCoursDao() throws Exception {
        TypeCoursDAO typeCoursdao = new TypeCoursDAO(this.maconnexion);
        ArrayList<TypeCours> typeCours = new ArrayList<>();
        typeCours = typeCoursdao.getAllTypeCours();
        return typeCours;
    }

    public JButton getBoutonAjouter() {
        return this.ajouter;
    }

    public JButton getBoutonModifier() {
        return this.modifier;
    }

    public JButton getBoutonChanger() {
        return this.changer;
    }

    public JButton getValiderSeance() {
        return this.validerSeance;
    }

    public JButton getModifierSeance() {
        return this.modifierSeance;
    }

    public JButton getChangerSeance() {
        return this.changerSeance;
    }

    public ArrayList<JCheckBox> getEnseignantsSeance() {
        return this.groupeE;
    }

    public ArrayList<JCheckBox> getGroupesSeance() {
        return this.groupeG;
    }

    public ArrayList<JCheckBox> getSallesSeance() {
        return this.groupeS;
    }

    public JComboBox<String> getBarreRecherche() {
        return this.cmbElement;
    }

    public JComboBox<String> getChangerEtat() {
        return this.cmbEtat;
    }

    public JComboBox<Integer> getSeance() {
        return this.cmbSeance;
    }

    public JComboBox<String> getSeanceEtat() {
        return this.cmbSeanceEtat;
    }

    public JComboBox<String> getCoursSeance() {
        return this.cmbCoursSeance;
    }

    public JComboBox<String> getTypeCoursSeance() {
        return this.cmbTypeCoursSeance;
    }

    public JDateTextField getDateChoix() {
        return this.dateChoix;
    }

    public JTextField getHeureDebutTexte() {
        return this.heureDebutTexte;
    }

    public JTextField getHeureFinTexte() {
        return this.heureFinTexte;
    }

    public JTextField getEtatTexte() {
        return this.etatTexte;
    }

    @Override
    @SuppressWarnings("CallToThreadDumpStack")
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        if (source == recherche) {
            try {
                switch (cmbChoixType.getSelectedIndex()) {
                    case 0:
                        UtilisateurDAO etudiantdao = new UtilisateurDAO(maconnexion);
                        Utilisateur tempStudent = utilisateurDao().get(cmbElement.getSelectedIndex());
                        Utilisateur student = etudiantdao.find(tempStudent.getId());
                        this.user = student;
                        System.out.println("EDT de " + student.getPrenom() + " " + student.getNom());
                        this.pan = this.pan(user.getEmail(), id_semaine);
                        this.pan.setVisible(true);
                        this.panelGlobal.removeAll();
                        this.panelGlobal.add(this.pan, BorderLayout.CENTER);
                        panelGlobal.setVisible(true);
                        System.out.println("Panneau EDT Etudiant");
                        break;
                    case 1:
                        UtilisateurDAO enseignantdao = new UtilisateurDAO(maconnexion);
                        Utilisateur tempTeacher = utilisateurDao().get(cmbElement.getSelectedIndex());
                        Utilisateur teacher = enseignantdao.find(tempTeacher.getId());
                        this.user = teacher;
                        System.out.println("EDT de " + teacher.getPrenom() + " " + teacher.getNom());
                        this.panEnseignant = this.panEnseignant(user.getEmail(), id_semaine);
                        this.panEnseignant.setVisible(true);
                        this.panelGlobal.removeAll();
                        this.panelGlobal.add(this.panEnseignant, BorderLayout.CENTER);
                        panelGlobal.setVisible(true);
                        System.out.println("Panneau EDT Etudiant");
                        break;
                    default:
                        SalleDAO salledao = new SalleDAO(maconnexion);
                        Salle tempSalle = sallesDao().get(cmbElement.getSelectedIndex());
                        Salle salle = salledao.find(tempSalle.getId());
                        System.out.println("EDT de " + salle.getNom());
                        this.panSalle = this.panSalle(salle.getId(), id_semaine);
                        this.panSalle.setVisible(true);
                        this.panelGlobal.removeAll();
                        this.panelGlobal.add(this.panSalle, BorderLayout.CENTER);
                        panelGlobal.setVisible(true);
                        System.out.println("Panneau EDT Salle");
                        break;
                }

            } catch (Exception ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == edtEtudiant) {
            try {
                this.pan = this.pan(user.getEmail(), id_semaine);
                this.pan.setVisible(true);
                this.panelGlobal.removeAll();
                this.panelGlobal.add(this.pan, BorderLayout.CENTER);
                panelGlobal.setVisible(true);
                System.out.println("Panneau EDT Etudiant");
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == edtEnseignant) {
            try {
                this.panelEnseignant = this.panelEnseignant();
                this.panelEnseignant.setVisible(true);
                this.panelGlobal.removeAll();
                this.panelGlobal.add(this.panelEnseignant, BorderLayout.CENTER);
                panelGlobal.setVisible(true);
                System.out.println("Panneau EDT Enseignant");

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == edtSalle) {
            try {
                this.panSalle = this.panSalle(id_salle, id_semaine);
                this.panSalle.setVisible(true);
                this.panelGlobal.removeAll();
                this.panelGlobal.add(this.panSalle, BorderLayout.CENTER);
                panelGlobal.setVisible(true);
                System.out.println("Panneau EDT Salle");

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == listePromotions) {
            try {
                this.panelPromotion = this.panelPromotion();
                this.panelPromotion.setVisible(true);
                this.panelGlobal.removeAll();
                this.panelGlobal.add(this.panelPromotion, BorderLayout.CENTER);
                panelGlobal.setVisible(true);
                System.out.println("Panneau Liste Promotions");

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);

            } catch (Exception ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == listeGroupes) {
            try {
                this.panelGroupe = this.panelGroupe();
                this.panelGroupe.setVisible(true);
                this.panelGlobal.removeAll();
                this.panelGlobal.add(this.panelGroupe, BorderLayout.CENTER);
                panelGlobal.setVisible(true);
                System.out.println("Panneau Liste Groupes");

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);

            } catch (Exception ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == listeEtudiants) {
            try {
                this.panelEtudiant = this.panelEtudiant();
                this.panelEtudiant.setVisible(true);
                this.panelGlobal.removeAll();
                this.panelGlobal.add(this.panelEtudiant, BorderLayout.CENTER);
                panelGlobal.setVisible(true);
                System.out.println("Panneau Liste Etudiants");

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);

            } catch (Exception ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == listeSalles) {
            try {
                this.panelSalle = this.panelSalle();
                this.panelSalle.setVisible(true);
                this.panelGlobal.removeAll();
                this.panelGlobal.add(this.panelSalle, BorderLayout.CENTER);
                panelGlobal.setVisible(true);
                System.out.println("Panneau Liste Salles");

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);

            } catch (Exception ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == listeEnseignants) {
            try {
                this.panelEnseignant = this.panelEnseignant();
                this.panelEnseignant.setVisible(true);
                this.panelGlobal.removeAll();
                this.panelGlobal.add(this.panelEnseignant, BorderLayout.CENTER);
                panelGlobal.setVisible(true);
                System.out.println("Panneau Liste Enseignants");

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);

            } catch (Exception ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == listeIntervenants) {
            try {
                this.panelIntervenants = this.panelIntervenants();
                this.panelIntervenants.setVisible(true);
                this.panelGlobal.removeAll();
                this.panelGlobal.add(this.panelIntervenants, BorderLayout.CENTER);
                panelGlobal.setVisible(true);
                System.out.println("Panneau Liste Intervenants");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == listeSeances) {
            try {
                this.panelSeance = this.panelSeance();
                this.panelSeance.setVisible(true);
                this.panelGlobal.removeAll();
                this.panelGlobal.add(this.panelSeance, BorderLayout.CENTER);
                panelGlobal.setVisible(true);
                System.out.println("Panneau Liste Seances");

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);

            } catch (Exception ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == recapEnseignants) {
            try {
                this.panelRecap = this.panelRecap();
                this.panelRecap.setVisible(true);
                this.panelGlobal.removeAll();
                this.panelGlobal.add(this.panelRecap, BorderLayout.CENTER);
                panelGlobal.setVisible(true);
                System.out.println("Recap des cours de l'enseignant");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == ajouter) {
            try {
                this.ajoutSeance();
                for (Cours c : this.coursDao()) {
                    this.getCoursSeance().addItem(c.getNom());
                }
                for (TypeCours t : this.typeCoursDao()) {
                    this.getTypeCoursSeance().addItem(t.getNom());
                }
                this.getValiderSeance().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Object source = e.getSource();
                        if (source == validerSeance) {
                            try {
                                CoursDAO coursdao = new CoursDAO(maconnexion);
                                Cours tempCours = coursDao().get(cmbCoursSeance.getSelectedIndex());
                                Cours cours = coursdao.find(tempCours.getId());

                                TypeCoursDAO type_coursdao = new TypeCoursDAO(maconnexion);
                                TypeCours tempTC = typeCoursDao().get(cmbTypeCoursSeance.getSelectedIndex());
                                TypeCours type_cours = type_coursdao.find(tempTC.getId());

                                int a = Integer.parseInt(getHeureDebutTexte().getText().trim());
                                int b = Integer.parseInt(getHeureFinTexte().getText().trim());
                                int etat = Integer.parseInt(getEtatTexte().getText().trim());

                                SeanceDAO seancedao = new SeanceDAO(maconnexion);
                                java.sql.Date sqlDate = new java.sql.Date(getDateChoix().getDate().getTime());
                                seancesDao().add(new Seance(seancesDao().size(), 23, sqlDate, a, b, etat, cours.getId(), type_cours.getId()));
                                Seance seance = new Seance(seancesDao().size() + 1, 23, sqlDate, a, b, etat, cours.getId(), type_cours.getId());
                                seancedao.display(seance);
                                seancedao.create(seance);

                                for (int i = 0; i < getEnseignantsSeance().size(); i++) {
                                    JCheckBox jcb = getEnseignantsSeance().get(i);
                                    boolean state = jcb.isSelected();
                                    if (state) {
                                        System.out.println(jcb.getText() + " est coché");
                                        EnseignantDAO enseignantdao = new EnseignantDAO(maconnexion);
                                        Enseignant tempE = enseignantsDao().get(i);
                                        Enseignant enseignant = enseignantdao.find(tempE.getId());
                                        seancedao.createEnseignants(seance, enseignant);
                                    } else {
                                        System.out.println(jcb.getText() + " n'est pas coché");
                                    }

                                }

                                for (int i = 0; i < getGroupesSeance().size(); i++) {
                                    JCheckBox jcb = getGroupesSeance().get(i);
                                    boolean state = jcb.isSelected();
                                    if (state) {
                                        System.out.println(jcb.getText() + " est coché");
                                        GroupeDAO groupedao = new GroupeDAO(maconnexion);
                                        Groupe tempG = groupesDao().get(i);
                                        Groupe groupe = groupedao.find(tempG.getId());
                                        seancedao.createGroupes(seance, groupe);
                                    } else {
                                        System.out.println(jcb.getText() + " n'est pas coché");
                                    }

                                }

                                for (int i = 0; i < getSallesSeance().size(); i++) {
                                    JCheckBox jcb = getSallesSeance().get(i);
                                    boolean state = jcb.isSelected();
                                    if (state) {
                                        System.out.println(jcb.getText() + " est coché");
                                        SalleDAO salledao = new SalleDAO(maconnexion);
                                        Salle tempS = sallesDao().get(i);
                                        Salle salle = salledao.find(tempS.getId());
                                        seancedao.createSalles(seance, salle);
                                    } else {
                                        System.out.println(jcb.getText() + " n'est pas coché");
                                    }
                                }
                            } catch (Exception ex) {
                                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        // pour fermer la fenetre
                        addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent evt) {
                                try {
                                    ajoutSeance().dispose();
                                    System.exit(0); // tout fermer
                                } catch (Exception ex) {
                                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                    }

                });
            } catch (Exception ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == modifier) {
            try {
                this.modifSeance();
                for (Seance s : seancesDao()) {
                    getSeance().addItem(s.getId());
                }
                for (Cours c : coursDao()) {
                    getCoursSeance().addItem(c.getNom());
                }
                for (TypeCours t : typeCoursDao()) {
                    getTypeCoursSeance().addItem(t.getNom());
                }

                getModifierSeance().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Object source = e.getSource();
                        if (source == getModifierSeance()) {
                            try {
                                SeanceDAO seancedao = new SeanceDAO(maconnexion);
                                Seance tempSeance = seancesDao().get(getSeance().getSelectedIndex());
                                Seance seance = seancedao.find(tempSeance.getId());

                                CoursDAO coursdao = new CoursDAO(maconnexion);
                                Cours tempCours = coursDao().get(getCoursSeance().getSelectedIndex());
                                Cours cours = coursdao.find(tempCours.getId());

                                TypeCoursDAO type_coursdao = new TypeCoursDAO(maconnexion);
                                TypeCours tempTC = typeCoursDao().get(getTypeCoursSeance().getSelectedIndex());
                                TypeCours type_cours = type_coursdao.find(tempTC.getId());

                                seancedao.update(seance, cours.getId(), type_cours.getId());
                            } catch (Exception ex) {
                                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        // pour fermer la fenetre
                        addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent evt) {
                                try {
                                    modifSeance().dispose();
                                    System.exit(0); // tout fermer
                                } catch (Exception ex) {
                                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                    }

                });
            } catch (Exception ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == changer) {
            try {
                this.changeSeance();
                for (Seance s : this.seancesDao()) {
                    this.getSeanceEtat().addItem("S" + s.getId() + " /Cours" + s.getIdCours());
                }
                getChangerSeance().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Object source = e.getSource();
                        if (source == getModifierSeance()) {
                            try {
                                SeanceDAO seancedao = new SeanceDAO(maconnexion);
                                Seance tempSeance = seancesDao().get(getSeance().getSelectedIndex());
                                Seance seance = seancedao.find(tempSeance.getId());
                                switch (getChangerEtat().getSelectedItem().toString()) {
                                    case "Valider":
                                        seancedao.validate(seance);
                                        break;
                                    case "Annuler":
                                        seancedao.cancel(seance);
                                        break;
                                }
                            } catch (Exception ex) {
                                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        // pour fermer la fenetre
                        addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent evt) {
                                try {
                                    changeSeance().dispose();
                                    System.exit(0); // tout fermer
                                } catch (Exception ex) {
                                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                    }
                });
            } catch (Exception ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e
    ) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
