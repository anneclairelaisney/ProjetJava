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
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc2020.dao.DAO;
import jdbc2020.dao.EtudiantDAO;

/**
 *
 * Affiche dans la fenetre graphique les champs de tables et les requetes de la
 * BDD
 *
 * @author segado
 */
public class PanneauListeEtudiant extends JPanel {

    //Attributs
    /* CONNEXION */
    private Connexion maconnexion;

    // Constructeur
    public PanneauListeEtudiant() throws SQLException, ClassNotFoundException {
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

        EtudiantDAO etudiantdao = new EtudiantDAO(this.maconnexion);
        ArrayList<Etudiant> listeEtudiants = new ArrayList<>();
        listeEtudiants = etudiantdao.getAllStudents();

        for (int i = 1; i < 8; i++) {
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
                    titre = "Numero";
                    j = 5;
                    break;
                case 7:
                    titre = "Groupe";
                    j = 6;
                    break;
            }
            JLabel weekDayPanel = new JLabel(titre);
            weekDayPanel.setHorizontalAlignment(SwingConstants.CENTER);
            weekDayPanel.setPreferredSize(new Dimension(134, 50));
            Dimension size = weekDayPanel.getPreferredSize();
            weekDayPanel.setBorder(blackline);
            weekDayPanel.setForeground(Color.white);
            weekDayPanel.setBounds(insets.left + j * 134, insets.top, size.width, size.height);
            this.add(weekDayPanel);
        }

        int j = 0;
        int n = 1;
        for (int k = 0; k < listeEtudiants.size(); k++) {
            for (int i = 1; i < 8; i++) {
                String titre = "";
                Etudiant etudiant = listeEtudiants.get(k);
                if (etudiant.getId() != 0) {
                    switch (i) {
                        case 1:
                            titre = " " + etudiant.getId();
                            j = 0;
                            break;
                        case 2:
                            titre = " " + etudiant.getEmail();
                            j = 1;
                            break;
                        case 3:
                            titre = " " + etudiant.getPasswd();
                            j = 2;
                            break;
                        case 4:
                            titre = " " + etudiant.getNom();
                            j = 3;
                            break;
                        case 5:
                            titre = " " + etudiant.getPrenom();
                            j = 4;
                            break;
                        case 6:
                            titre = " " + etudiant.getNumero();
                            j = 5;
                            break;
                        case 7:
                            titre = " " + etudiant.getIdGroupe();
                            j = 6;
                            break;
                    }
                    JLabel weekDayPanel = new JLabel(titre);
                    weekDayPanel.setHorizontalAlignment(SwingConstants.CENTER);
                    weekDayPanel.setPreferredSize(new Dimension(134, 50));
                    Dimension size = weekDayPanel.getPreferredSize();
                    weekDayPanel.setBorder(blackline);
                    weekDayPanel.setForeground(Color.white);
                    weekDayPanel.setBounds(insets.left + j * 134, insets.top + n * 50, size.width, size.height);
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
