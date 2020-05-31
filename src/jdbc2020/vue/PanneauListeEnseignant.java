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
import java.util.ArrayList;
import jdbc2020.dao.DAO;
import jdbc2020.dao.EnseignantDAO;

/**
 *
 * Affiche dans la fenetre graphique les champs de tables et les requetes de la
 * BDD
 *
 * @author segado
 */
public class PanneauListeEnseignant extends JPanel {

    //Attributs
    /* CONNEXION */
    private Connexion maconnexion;

    // Constructeur
    public PanneauListeEnseignant() throws SQLException, ClassNotFoundException {
        this.setLayout(null);
        this.setSize(800, 750);
        this.setBackground(new Color(4, 116, 124));
        this.maconnexion = new Connexion("jdbc2020", "root", "root");
    }

    public void remplirListe() throws Exception {
        this.setVisible(true);
        this.setBackground(new Color(4, 116, 124));
        Insets insets = this.getInsets();
        javax.swing.border.Border blackline = BorderFactory.createLineBorder(Color.white, 1);
        
        EnseignantDAO enseignantdao = new EnseignantDAO(this.maconnexion);
        ArrayList<Enseignant> listeEnseignants = new ArrayList<>();
        listeEnseignants = enseignantdao.getAllTeachers();

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
                    titre = "Cours";
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
        for (int k = 0; k < listeEnseignants.size(); k++) {
            for (int i = 1; i < 7; i++) {
                String titre = "";
                Enseignant enseignant = listeEnseignants.get(k);
                if (enseignant.getId() != 0) {
                    switch (i) {
                        case 1:
                            titre = " " + enseignant.getId();
                            j = 0;
                            break;
                        case 2:
                            titre = " " + enseignant.getEmail();
                            j = 1;
                            break;
                        case 3:
                            titre = " " + enseignant.getPasswd();
                            j = 2;
                            break;
                        case 4:
                            titre = " " + enseignant.getNom();
                            j = 3;
                            break;
                        case 5:
                            titre = " " + enseignant.getPrenom();
                            j = 4;
                            break;
                        case 6:
                            titre = " " + enseignant.getIdCours();
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
    }
}
