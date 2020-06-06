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
import jdbc2020.dao.SeanceDAO;

/**
 *
 * Affiche dans la fenetre graphique les champs de tables et les requetes de la
 * BDD
 *
 * @author segado
 */
public class PanneauListeSeance extends JPanel {

    private Connexion maconnexion;

    // Constructeur

    /**
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public PanneauListeSeance() throws SQLException, ClassNotFoundException {
        this.setLayout(null);
        this.setSize(800, 750);
        this.setBackground(new Color(4, 116, 124));
        this.maconnexion = new Connexion("jdbc2020", "root", "");
    }

    /**
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public void remplirListe() throws SQLException, ClassNotFoundException, Exception {
        this.setVisible(true);
        this.setBackground(new Color(4, 116, 124));
        Insets insets = this.getInsets();
        javax.swing.border.Border blackline = BorderFactory.createLineBorder(Color.white, 1);

        SeanceDAO seancedao = new SeanceDAO(this.maconnexion);
        ArrayList<Seance> listeSeances = new ArrayList<>();
        listeSeances = seancedao.getAllSeances();

        for (int i = 1; i < 9; i++) {
            int j = 0;
            String titre = "";
            switch (i) {
                case 1:
                    titre = "Id";
                    break;
                case 2:
                    titre = "Semaine";
                    j = 1;
                    break;
                case 3:
                    titre = "Date";
                    j = 2;
                    break;
                case 4:
                    titre = "Heure Debut";
                    j = 3;
                    break;
                case 5:
                    titre = "Heure Fin";
                    j = 4;
                    break;
                case 6:
                    titre = "Etat";
                    j = 5;
                    break;
                case 7:
                    titre = "Cours";
                    j = 6;
                    break;
                case 8:
                    titre = "Type de cours";
                    j = 7;
                    break;
            }
            JLabel weekDayPanel = new JLabel(titre);
            weekDayPanel.setHorizontalAlignment(SwingConstants.CENTER);
            weekDayPanel.setPreferredSize(new Dimension(114, 50));
            Dimension size = weekDayPanel.getPreferredSize();
            weekDayPanel.setBorder(blackline);
            weekDayPanel.setForeground(Color.white);
            weekDayPanel.setBounds(insets.left + j * 114, insets.top, size.width, size.height);
            this.add(weekDayPanel);
        }

        int j = 0;
        int n = 1;
        for (int k = 0; k < listeSeances.size(); k++) {
            for (int i = 1; i < 9; i++) {
                String titre = "";
                Seance seance = listeSeances.get(k);
                if (seance.getId() != 0) {
                    switch (i) {
                        case 1:
                            titre = " " + seance.getId();
                            j = 0;
                            break;
                        case 2:
                            titre = " " + seance.getSemaine();
                            j = 1;
                            break;
                        case 3:
                            titre = " " + seance.getDate();
                            j = 2;
                            break;
                        case 4:
                            titre = " " + seance.getHeureDebut();
                            j = 3;
                            break;
                        case 5:
                            titre = " " + seance.getHeureFin();
                            j = 4;
                            break;
                        case 6:
                            titre = " " + seance.getEtat();
                            j = 5;
                            break;
                        case 7:
                            titre = " " + seance.getIdCours();
                            j = 6;
                            break;
                        case 8:
                            titre = " " + seance.getIdType();
                            j = 7;
                            break;
                    }
                    JLabel weekDayPanel = new JLabel(titre);
                    weekDayPanel.setHorizontalAlignment(SwingConstants.CENTER);
                    weekDayPanel.setPreferredSize(new Dimension(114, 50));
                    Dimension size = weekDayPanel.getPreferredSize();
                    weekDayPanel.setBorder(blackline);
                    weekDayPanel.setForeground(Color.white);
                    weekDayPanel.setBounds(insets.left + j * 114, insets.top + n * 50, size.width, size.height);
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
