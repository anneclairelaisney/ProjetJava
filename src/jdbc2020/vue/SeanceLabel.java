/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.vue;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import jdbc2020.controleur.Connexion;
import jdbc2020.dao.DAO;
import jdbc2020.dao.SeanceDAO;
import jdbc2020.modele.*;

/**
 *
 * @author apple
 */
public class SeanceLabel extends JButton {

    //Attributs
    private final Connexion maconnexion;
    private JPanel p0;
    private JLabel cours, groupe, salle, site;
    private Seance seance;
    private ArrayList<Enseignant> enseignants;
    private ArrayList<Salle> salles;
    private ArrayList<Groupe> groupes;
    

    public SeanceLabel() throws SQLException, ClassNotFoundException {
        this.maconnexion = new Connexion("jdbc2020", "root", "root");
        this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        this.setBorderPainted(true);
        this.setContentAreaFilled(true);
        this.setFocusPainted(true);
        this.setHorizontalAlignment(SwingConstants.CENTER);

        // creation des textes
        this.p0 = new JPanel();
        this.p0.setLayout(new GridLayout(2,2));
        this.p0.setPreferredSize(new Dimension(100,50));
        this.add(p0);
        this.seance = new Seance();
    }
    
    public void remplirSeance(int i) {
        this.cours = new JLabel();
        this.groupe = new JLabel();
        this.salle = new JLabel();
        this.site = new JLabel();
        DAO<Seance> seanceDAO = new SeanceDAO(this.maconnexion);
        Seance a = seanceDAO.find(i);
        this.seance = a;
        try {
            ResultSet rset = this.maconnexion.getStatement().executeQuery("SELECT nom FROM Cours WHERE id = " + a.getIdCours());
            if (rset.first()) {
                cours = new JLabel(rset.getString("nom"));
                cours.setHorizontalAlignment(SwingConstants.CENTER);
            }
            this.p0.add(cours);

            rset = this.maconnexion.getStatement().executeQuery("SELECT nom FROM Groupe WHERE id = (SELECT id_groupe FROM Seance_Groupes WHERE id_seance=" + a.getId() + ")");
            if (rset.first()) {
                groupe = new JLabel(rset.getString("nom"));
                groupe.setHorizontalAlignment(SwingConstants.CENTER);
            }
            this.p0.add(groupe);

            rset = this.maconnexion.getStatement().executeQuery("SELECT nom FROM Salle WHERE id = (SELECT id_salle FROM Seance_Salles WHERE id_seance=" + a.getId() + ")");
            if (rset.first()) {
                salle = new JLabel(rset.getString("nom"));
                salle.setHorizontalAlignment(SwingConstants.CENTER);
            }
            this.p0.add(salle);

            rset = this.maconnexion.getStatement().executeQuery("SELECT nom FROM Site WHERE id = (SELECT id_site FROM Salle WHERE id= (SELECT id_salle FROM Seance_salles WHERE id_seance=" + a.getId() + "))");
            if (rset.first()) {
                site = new JLabel(rset.getString("nom"));
                site.setHorizontalAlignment(SwingConstants.CENTER);
            }
            this.p0.add(site);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void recupInfos(int i) {
        DAO<Seance> seanceDAO = new SeanceDAO(this.maconnexion);
        Seance a = seanceDAO.find(i);
        this.seance = a;
        try {
            ResultSet rset = this.maconnexion.getStatement().executeQuery("SELECT * FROM Enseignant WHERE id_utilisateur = " + a.getIdCours());
            if (rset.first()) {
                cours = new JLabel(rset.getString("nom"));
                cours.setHorizontalAlignment(SwingConstants.CENTER);
            }
            this.p0.add(cours);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Seance getSeance() {
        return this.seance;
    }
    private ArrayList<Enseignant> getEnseignants() {
        return this.enseignants;
    }
    private ArrayList<Salle> getSalles() {
        return this.salles;
    }
    private ArrayList<Groupe> getGroupes() {
        return this.groupes;
    }
}
