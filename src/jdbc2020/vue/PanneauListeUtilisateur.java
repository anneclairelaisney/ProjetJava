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
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import jdbc2020.dao.DAO;
import jdbc2020.dao.UtilisateurDAO;

/**
 *
 * Affiche dans la fenetre graphique les champs de tables et les requetes de la
 * BDD
 *
 * @author segado
 */
public class PanneauListeUtilisateur extends JPanel {

    //Attributs
    /* CONNEXION */
    private Connexion maconnexion;

    // Constructeur
    public PanneauListeUtilisateur() throws SQLException, ClassNotFoundException {
        this.setLayout(null);
        this.setSize(800, 750);
        this.setBackground(new Color(4, 116, 124));
        this.maconnexion = new Connexion("jdbc2020", "root", "root");
    }

    public void remplirListe() {
        this.setVisible(true);
        this.setBackground(new Color(4, 116, 124));
        Insets insets = this.getInsets();
        javax.swing.border.Border blackline = BorderFactory.createLineBorder(Color.white, 1);

        for (int i = 1; i < 7; i++) {
            int j = 0;
            String titre = "";
            switch (i) {
                case 1:
                    titre = "ID";
                    break;
                case 2:
                    titre = "E-Mail";
                    j = 1;
                    break;
                case 3:
                    titre = "Mot de passe";
                    j = 2;
                    break;
                case 4:
                    titre = "Nom";
                    j = 3;
                    break;
                case 5:
                    titre = "Prenom";
                    j = 4;
                    break;
                case 6:
                    titre = "Type Utilisateur";
                    j = 5;
                    break;
            }
            JLabel weekDayPanel = new JLabel(titre);
            weekDayPanel.setHorizontalAlignment(SwingConstants.CENTER);
            weekDayPanel.setPreferredSize(new Dimension(160, 50));
            Dimension size = weekDayPanel.getPreferredSize();
            weekDayPanel.setBorder(blackline);
            weekDayPanel.setForeground(Color.white);
            weekDayPanel.setBounds(insets.left + j * 160, insets.top, size.width, size.height);
            this.add(weekDayPanel);
        }

        int j = 0;
        int n = 1;
        for (int k = 1; k < 15; k++) {
            for (int i = 1; i < 7; i++) {
                String titre = "";
                DAO<Utilisateur> utilisateurDAO = new UtilisateurDAO(this.maconnexion);
                Utilisateur utilisateur = utilisateurDAO.find(k);
                if (utilisateur.getId() != 0) {
                    switch (i) {
                        case 1:
                            titre = " " + utilisateur.getId();
                            j = 0;
                            break;
                        case 2:
                            titre = " " + utilisateur.getEmail();
                            j = 1;
                            break;
                        case 3:
                            titre = " " + utilisateur.getPasswd();
                            j = 2;
                            break;
                        case 4:
                            titre = " " + utilisateur.getNom();
                            j = 3;
                            break;
                        case 5:
                            titre = " " + utilisateur.getPrenom();
                            j = 4;
                            break;
                        case 6:
                            switch (utilisateur.getDroit()) {
                                case 1:
                                    titre = "Administrateur";
                                    break;
                                case 2:
                                    titre = "Référent";
                                    break;
                                case 3:
                                    titre = "Enseignant";
                                    break;
                                case 4:
                                    titre = "Etudiant";
                                    break;
                            }
                            j = 5;
                            break;
                    }
                    JLabel weekDayPanel = new JLabel(titre);
                    weekDayPanel.setHorizontalAlignment(SwingConstants.CENTER);
                    weekDayPanel.setPreferredSize(new Dimension(160, 50));
                    Dimension size = weekDayPanel.getPreferredSize();
                    weekDayPanel.setBorder(blackline);
                    weekDayPanel.setForeground(Color.white);
                    weekDayPanel.setBounds(insets.left + j * 160, insets.top + n * 50, size.width, size.height);
                    this.add(weekDayPanel);
                }
            }
            n += 1;
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j <= 14; j++) {
                g.setColor(Color.WHITE);
                g.drawRect(160 * i, 50 * j, 160, 50);
            }
        }
    }
}
