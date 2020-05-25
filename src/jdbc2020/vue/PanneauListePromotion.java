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
import jdbc2020.dao.PromotionDAO;

/**
 *
 * Affiche dans la fenetre graphique les champs de tables et les requetes de la
 * BDD
 *
 * @author segado
 */
public class PanneauListePromotion extends JPanel {
    private Connexion maconnexion; 

    // Constructeur
    public PanneauListePromotion() throws SQLException, ClassNotFoundException {
        this.setLayout(null);
        this.setSize(800, 750);
        this.setBackground(new Color(4, 116, 124));
        this.maconnexion = new Connexion("jdbc2020", "root", "root");
    }

    public void remplirListe() throws SQLException, ClassNotFoundException {
        this.setVisible(true);
        this.setBackground(new Color(4, 116, 124));
        Insets insets = this.getInsets();
        javax.swing.border.Border blackline = BorderFactory.createLineBorder(Color.white, 1);

        for (int i = 1; i <= 2; i++) {
            int j = 0;
            String titre = "";
            switch (i) {
                case 1:
                    titre = "ID";
                    j = 0;
                    break;
                case 2:
                    titre = "Nom";
                    j = 1;
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
        for (int k = 1; k < 6; k++) {
            for (int i = 1; i <= 2; i++) {
                String titre = "";
                DAO<Promotion> promotionDAO = new PromotionDAO(this.maconnexion);
                Promotion promotion = promotionDAO.find(k);
                if (promotion.getId() != 0) {
                    switch (i) {
                        case 1:
                            titre = " " + promotion.getId();
                            j = 0;
                            break;
                        case 2:
                            titre = " " + promotion.getNom();
                            j = 1;
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
