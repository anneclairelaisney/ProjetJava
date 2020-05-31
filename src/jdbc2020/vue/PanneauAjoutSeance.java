/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import jdbc2020.controleur.Connexion;
import jdbc2020.dao.SeanceDAO;
import jdbc2020.modele.Etudiant;
import jdbc2020.modele.Seance;

/**
 *
 * @author apple
 */
public class PanneauAjoutSeance extends JPanel {

    private Connexion maconnexion;
    public PanneauAjoutSeance() throws SQLException, ClassNotFoundException {
        this.setLayout(null);
        this.setSize(800, 750);
        this.setBackground(new Color(4, 116, 124));
        this.maconnexion = new Connexion("jdbc2020", "root", "root");

        JLabel lblMessage = new JLabel("Ajouter une séance de cours :");

    }

    public void ajoutSeance(Seance seance) throws SQLException, ClassNotFoundException, Exception {
        this.setVisible(true);
        this.setBackground(new Color(4, 116, 124));
        Insets insets = this.getInsets();
        javax.swing.border.Border blackline = BorderFactory.createLineBorder(Color.white, 1);

        SeanceDAO seancedao = new SeanceDAO(maconnexion);
        ArrayList<Seance> listeSeances = new ArrayList<>();
        listeSeances = seancedao.getAllSeances();
        for (Seance s : listeSeances) {
            if (s.getId() != seance.getId()) {
                seancedao.create(seance);
                listeSeances.add(seance);
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
