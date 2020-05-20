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
    private final JPanel p0, edt;
    private CardLayout cardLayout;

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

        // creation des boutons
        // creation des listes pour les tables et les requetes
        // creation des textes
        // creation des labels
        // creation des panneaux
        p0 = new JPanel();
        edt = new JPanel();
        javax.swing.border.Border blackline = BorderFactory.createLineBorder(Color.black, 1);
        for (int i = 0; i < 65; i++) {
            JPanel ptest = new JPanel();
            ptest.setBorder(blackline);
            edt.add(ptest);
        }

        // mise en page des panneaux
        p0.setLayout(new GridLayout(1, 3));
        edt.setLayout(new GridLayout(13, 5));

        this.add(p0);
        this.add(edt);
        // ajout des objets graphiques dans les panneaux
        // ajout des listeners
        // couleurs des objets graphiques
        // disposition geographique des panneaux

        // pour fermer la fenetre
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0); // tout fermer												System.exit(0); // tout fermer
            }
        });
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
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
