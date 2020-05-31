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
import jdbc2020.dao.EnseignantDAO;
import jdbc2020.dao.EtudiantDAO;
import jdbc2020.dao.GroupeDAO;
import jdbc2020.dao.PromotionDAO;
import jdbc2020.dao.SalleDAO;
import jdbc2020.modele.Enseignant;
import jdbc2020.modele.Etudiant;
import jdbc2020.modele.Groupe;
import jdbc2020.modele.Salle;

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
    private PanneauListeGroupe panelGroupe;
    private PanneauListeEtudiant panelEtudiant;
    private JScrollPane scrollPane = new JScrollPane(panelEtudiant);
    private PanneauListeSalle panelSalle;
    private PanneauListeEnseignant panelEnseignant;

    /* Ajout */
    private JFrame ajoutSeance;
    private JPanel panelMajSeance, panelMajSeance2;
    private JLabel dateSeance, heureDebutSeance, heureFinSeance, etatSeance, groupesSeance, enseignantsSeance, sallesSeance, coursSeance, typeCoursSeance;
    private JTextField etatTexte;
    private JFormattedTextField heureDebutTexte, heureFinTexte;
    private JComboBox<String> cmbCoursSeance, cmbTypeCoursSeance;
    private ArrayList<JCheckBox> groupeE, groupeG, groupeS;
    private JButton validerSeance;
    private JDateTextField dateChoix;

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

        this.panelGroupe = new PanneauListeGroupe();
        this.panelGroupe.setVisible(false);
        this.add(this.panelGroupe, BorderLayout.CENTER);

        this.panelEtudiant = new PanneauListeEtudiant();
        this.panelEtudiant.setVisible(false);
        this.add(this.panelEtudiant, BorderLayout.CENTER);

        this.panelSalle = new PanneauListeSalle();
        this.panelSalle.setVisible(false);
        this.add(this.panelSalle, BorderLayout.CENTER);

        this.panelEnseignant = new PanneauListeEnseignant();
        this.panelEnseignant.setVisible(false);
        this.add(this.panelEnseignant, BorderLayout.CENTER);

        this.getContentPane().add(p1(), BorderLayout.SOUTH);

        // ajout des listeners
        edtEtudiant.addActionListener(this);
        listePromotions.addActionListener(this);
        listeGroupes.addActionListener(this);
        listeEtudiants.addActionListener(this);
        listeSalles.addActionListener(this);
        listeEnseignants.addActionListener(this);

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

    private Panneau pan() throws SQLException, ClassNotFoundException {
        this.pan.remplirEDT();
        return this.pan;
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
        scrollPane.setPreferredSize(new Dimension(300, 400));
        scrollPane.setAutoscrolls(true);
        scrollPane.setVisible(true);
        this.panelEtudiant.remplirListe();
        this.panelEtudiant.add(scrollPane);
        return this.panelEtudiant;
    }

    private PanneauListeSalle panelSalle() throws SQLException, ClassNotFoundException, Exception {
        this.panelSalle.remplirListe();
        return this.panelSalle;
    }

    private PanneauListeEnseignant panelEnseignant() throws SQLException, ClassNotFoundException, Exception {
        this.panelEnseignant.remplirListe();
        return this.panelEnseignant;
    }

    public void setInvisible() {
        this.pan.setVisible(false);
        this.panelPromotion.setVisible(false);
        this.panelGroupe.setVisible(false);
        this.panelEtudiant.setVisible(false);
        this.panelSalle.setVisible(false);
        this.panelEnseignant.setVisible(false);
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

        panelMajSeance2.setLayout(
                new FlowLayout());
        validerSeance = new JButton("✓");

        validerSeance.setPreferredSize(
                new Dimension(50, 50));

        panelMajSeance2.add(validerSeance);

        ajoutSeance.add(panelMajSeance, BorderLayout.CENTER);

        ajoutSeance.add(panelMajSeance2, BorderLayout.SOUTH);

        return ajoutSeance;
    }

    public JButton getValiderSeance() {
        return this.validerSeance;
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
        if (source == edtEtudiant) {
            try {
                setInvisible();
                this.pan = this.pan();
                this.add(this.pan);
                System.out.println("Panneau EDT Etudiant");

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == listePromotions) {
            try {
                setInvisible();

                this.panelPromotion = this.panelPromotion();
                this.add(this.panelPromotion);
                System.out.println("Panneau Liste Promotions");

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (Exception ex) {
                Logger.getLogger(Fenetre.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == listeGroupes) {
            try {
                setInvisible();

                this.panelGroupe = this.panelGroupe();
                this.add(this.panelGroupe);
                System.out.println("Panneau Liste Groupes");

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (Exception ex) {
                Logger.getLogger(Fenetre.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == listeEtudiants) {
            try {
                setInvisible();

                this.panelEtudiant = this.panelEtudiant();
                this.add(this.panelEtudiant);
                System.out.println("Panneau Liste Etudiants");

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (Exception ex) {
                Logger.getLogger(Fenetre.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == listeSalles) {
            try {
                setInvisible();

                this.panelSalle = this.panelSalle();
                this.add(this.panelSalle);
                System.out.println("Panneau Liste Salles");

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (Exception ex) {
                Logger.getLogger(Fenetre.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == listeEnseignants) {
            try {
                setInvisible();

                this.panelEnseignant = this.panelEnseignant();
                this.add(this.panelEnseignant);
                System.out.println("Panneau Liste Enseignants");

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (Exception ex) {
                Logger.getLogger(Fenetre.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
