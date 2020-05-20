/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.vue;

import java.awt.BorderLayout;
import jdbc2020.modele.*;
import jdbc2020.controleur.Connexion;
import jdbc2020.dao.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

/* Interface graphique */
public class PageConnexion extends JFrame implements ActionListener, ItemListener {

    // Attributs
    private Connexion maconnexion;
    public ModeleSouris modeleSouris;
    private final JButton local;
    private final JPanel p0, p1, p2, p3, center;
    private final JTextField nomUtilisateurTexte, motDePasseTexte;
    private final JLabel titreConnexion;
    private final JLabel nomUtilisateur, motDePasse;

    // Constructeur
    public PageConnexion() {

        // mise en page (layout) de la fenetre visible
        this.setSize(1000, 800); // Taille en hauteur et largeur de la fenêtre
        this.setTitle("BIENVENUE SUR L'INTRANET DE L'ECE PARIS-LYON");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(true);

        // creation des boutons
        //connect = new JButton("Connexion ECE");
        local = new JButton("Connexion");

        // creation des panneaux
        p0 = new JPanel();
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        center = new JPanel();

        // creation des textes
        nomUtilisateurTexte = new JTextField(JTextField.CENTER);
        motDePasseTexte = new JTextField(JTextField.CENTER);

        // creation des labels
        titreConnexion = new JLabel("CONNECTION", JLabel.CENTER);
        nomUtilisateur = new JLabel("Nom Utilisateur :", JLabel.CENTER);
        motDePasse = new JLabel("Mot de Passe :", JLabel.CENTER);

        // mise en page des panneaux
        p0.setLayout(new GridLayout(0, 1));
        p1.setLayout(new GridLayout(1, 4));
        p2.setLayout(new GridLayout(1, 4));
        p3.setLayout(new GridLayout(1, 4));
        center.setLayout(new GridLayout(3, 1));

        // ajout des objets graphiques dans les panneaux
        p0.add(titreConnexion);

        p1.add(nomUtilisateur);
        p1.add(nomUtilisateurTexte);
        p1.setBackground(Color.LIGHT_GRAY);

        p2.add(motDePasse);
        p2.add(motDePasseTexte);
        p2.setBackground(Color.LIGHT_GRAY);

        p3.add(local);

        this.add(p0);
        this.add(p1);
        this.add(p2);
        this.add(p3);
        center.add("Center", p1);
        center.add("Center", p2);
        center.add("Center", p3);

        // ajout des listeners
        local.addActionListener(this);

        // couleurs des objets graphiques
        titreConnexion.setBackground(Color.MAGENTA);

        // disposition geographique des panneaux
        add("North", p0);
        add("Center", center);

        // pour fermer la fenetre
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0); // tout fermer												System.exit(0); // tout fermer
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // tester cas de la commande evenementielle
        if (source == local) {
            ArrayList<String> liste;
            try {
                try {
                    // tentative de connexion si les 4 attributs sont remplis
                    this.maconnexion = new Connexion("jdbc2020", "root", "root");
                    System.out.println("Connexion dans BDD reussie");

                    DAO<Utilisateur> utilisateurDao = new UtilisateurDAO(this.maconnexion);
                    for (int i = 1; i < 7; i++) {
                        Utilisateur utilisateur = utilisateurDao.find(i);
                        if (utilisateur.getEmail().equals(nomUtilisateurTexte.getText())) {
                            if (utilisateur.getPasswd().equals(motDePasseTexte.getText())) {
                                System.out.println("Bienvenue dans l'intranet ECE Paris-Lyon " + utilisateur.getPrenom() + " " + utilisateur.getNom() + " !");
                                break;
                            }
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public class Panneau extends JPanel {

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
        }
    }
}
