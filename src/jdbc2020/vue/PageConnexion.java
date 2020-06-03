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

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc2020.controleur.Generateur;

/* Interface graphique */
public class PageConnexion extends JFrame implements ActionListener, ItemListener {

    // Attributs
    private Connexion maconnexion;
    public ModeleSouris modeleSouris;
    private final JButton local;
    private final JPanel p0, p1, p2, p3, center;
    private final JTextField nomUtilisateurTexte;
    private final JLabel titreConnexion, logo;
    private final JLabel nomUtilisateur, motDePasse;
    private final JPasswordField motDePasseTexte;

    // Constructeur
    public PageConnexion() {

        // mise en page (layout) de la fenetre visible
        this.setSize(750, 500); // Taille en hauteur et largeur de la fenêtre
        this.setTitle("BIENVENUE SUR L'INTRANET DE L'ECE PARIS-LYON");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // creation des boutons
        //connect = new JButton("Connexion ECE");
        local = new JButton("Connexion");
        local.setHorizontalAlignment(JTextField.CENTER);

        // creation des panneaux
        p0 = new JPanel();
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        logo = new JLabel(new ImageIcon(PageConnexion.class.getResource("ece.png")));
        center = new JPanel();

        // creation des textes
        nomUtilisateurTexte = new JTextField();
        nomUtilisateurTexte.setHorizontalAlignment(JTextField.CENTER);
        motDePasseTexte = new JPasswordField(JLabel.CENTER);
        motDePasseTexte.setHorizontalAlignment(JPasswordField.CENTER);

        // creation des labels
        titreConnexion = new JLabel("Connexion", JLabel.CENTER);
        nomUtilisateur = new JLabel("Nom d'Utilisateur :", JLabel.CENTER);
        motDePasse = new JLabel("Mot de Passe :", JLabel.CENTER);

        // mise en page des panneaux
        Font font = new Font("Helvetica", Font.BOLD, 36);
        Font font1 = new Font("Helvetica", Font.BOLD, 16);
        titreConnexion.setFont(font);
        titreConnexion.setForeground(new Color(4, 116, 124));
        nomUtilisateur.setFont(font1);
        motDePasse.setFont(font1);
        nomUtilisateur.setForeground(Color.white);
        motDePasse.setForeground(Color.white);
        p0.setLayout(new GridLayout(2, 1));
        p1.setLayout(new GridLayout(1, 2));
        p2.setLayout(new GridLayout(1, 2));
        center.setLayout(new GridLayout(3, 3));

        // ajout des objets graphiques dans les panneaux
        p0.add(logo);
        p0.add(titreConnexion);

        p1.add(nomUtilisateur);
        p1.add(nomUtilisateurTexte);
        p1.setBackground(new Color(4, 116, 124));
        p1.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));

        p2.add(motDePasse);
        p2.add(motDePasseTexte);
        p2.setBackground(new Color(4, 116, 124));
        p2.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));

        local.setPreferredSize(new Dimension(100, 33));
        local.setLayout(new BoxLayout(local, BoxLayout.PAGE_AXIS));
        p3.add(local, BorderLayout.SOUTH);

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
            try {
                // tentative de connexion si les 4 attributs sont remplis
                this.maconnexion = new Connexion("jdbc2020", "root", "");
                System.out.println("Connexion dans BDD reussie");

                UtilisateurDAO utilisateurdao = new UtilisateurDAO(this.maconnexion);
                ArrayList<Utilisateur> listeUtilisateurs = new ArrayList<>();
                listeUtilisateurs = utilisateurdao.getAllUsers();

                for (int i = 0; i < listeUtilisateurs.size(); i++) {
                    Utilisateur utilisateur = listeUtilisateurs.get(i);
                    if (utilisateur.getId() != 0) {
                        if (utilisateur.getEmail().equals(nomUtilisateurTexte.getText())) {
                            if (utilisateur.getPasswd().equals(motDePasseTexte.getText())) {
                                System.out.println("Bienvenue dans l'intranet ECE Paris-Lyon " + utilisateur.getPrenom() + " " + utilisateur.getNom() + " !");
                                this.dispose();
                                new Fenetre(utilisateur.getEmail(), utilisateur.getPasswd(), "jdbc2020");
                            }
                        }
                    }
                }
            } catch (ClassNotFoundException cnfe) {
                System.out.println("Connexion echouee : probleme de classe");
            } catch (Exception ex) {
                Logger.getLogger(PageConnexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
