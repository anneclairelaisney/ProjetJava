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
        this.p0.setLayout(new GridLayout(2, 2));
        this.p0.setPreferredSize(new Dimension(100, 50));
        this.add(p0);
        this.seance = new Seance();
    }

    /* EDT ETUDIANT */
    public ArrayList<SeanceGroupes> sg(Seance s) throws SQLException {
        ArrayList<SeanceGroupes> sg = new ArrayList();
        ResultSet rset1 = this.maconnexion.getStatement().executeQuery("SELECT * FROM Seance_Groupes WHERE id_seance = " + s.getId());
        while (rset1.next()) {
            sg.add(new SeanceGroupes(rset1.getInt("id_seance"), rset1.getInt("id_groupe")));
        }
        return sg;
    }

    public ArrayList<SeanceGroupes> sg(String login) throws SQLException {
        ArrayList<SeanceGroupes> sg = new ArrayList();
        ResultSet rset1 = this.maconnexion.getStatement().executeQuery("SELECT * FROM Seance_Groupes WHERE id_groupe=(SELECT id_groupe FROM Etudiant WHERE id_utilisateur =(SELECT id FROM Utilisateur WHERE email ='" + login + "'))");
        while (rset1.next()) {
            sg.add(new SeanceGroupes(rset1.getInt("id_seance"), rset1.getInt("id_groupe")));
        }
        return sg;
    }

    public ArrayList<SeanceSalles> ss(Seance s) throws SQLException {
        ArrayList<SeanceSalles> ss = new ArrayList();
        ResultSet rset1 = this.maconnexion.getStatement().executeQuery("SELECT * FROM Seance_Salles WHERE id_seance = " + s.getId());
        while (rset1.next()) {
            ss.add(new SeanceSalles(rset1.getInt("id_seance"), rset1.getInt("id_salle")));
        }
        return ss;
    }

    public ArrayList<SeanceSalles> ss(int id_seance) throws SQLException {
        ArrayList<SeanceSalles> ss = new ArrayList();
        ResultSet rset1 = this.maconnexion.getStatement().executeQuery("SELECT id_salle FROM Seance_Salles WHERE id_seance = " + id_seance);
        while (rset1.next()) {
            ss.add(new SeanceSalles(id_seance, rset1.getInt("id_salle")));
        }
        return ss;
    }

    public ArrayList<Site> site(Seance s) throws SQLException {
        ArrayList<Site> site = new ArrayList();
        ResultSet rset1 = this.maconnexion.getStatement().executeQuery("SELECT * FROM Site WHERE id = " + s.getId());
        while (rset1.next()) {
            site.add(new Site(rset1.getInt("id"), rset1.getString("nom")));
        }
        return site;
    }

    public ArrayList<Cours> cours(int i) throws SQLException {
        ArrayList<Cours> cours = new ArrayList();
        ResultSet rset1 = this.maconnexion.getStatement().executeQuery("SELECT * FROM Cours WHERE id=(SELECT id_cours FROM Enseignant WHERE id_utilisateur = " + i +")");
        while (rset1.next()) {
            cours.add(new Cours(rset1.getInt("id"), rset1.getString("nom")));
        }
        return cours;
    }
    
    public ArrayList<Site> site(int id_salle) throws SQLException {
        ArrayList<Site> site = new ArrayList();
        ResultSet rset1 = this.maconnexion.getStatement().executeQuery("SELECT * FROM Site WHERE id = " + id_salle);
        while (rset1.next()) {
            site.add(new Site(rset1.getInt("id"), rset1.getString("nom")));
        }
        return site;
    }

    public void remplirGroupe(String s) throws SQLException {
        this.groupe = new JLabel(s);
        groupe.setHorizontalAlignment(SwingConstants.CENTER);
        this.p0.add(groupe);
    }

    public void remplirSalle(String s) throws SQLException {
        this.salle = new JLabel(s);
        salle.setHorizontalAlignment(SwingConstants.CENTER);
        this.p0.add(salle);
    }

    public void remplirSite(String s) throws SQLException {
        this.site = new JLabel(s);
        site.setHorizontalAlignment(SwingConstants.CENTER);
        this.p0.add(site);
    }

    public void remplirSeance(int i) throws SQLException {
        this.cours = new JLabel();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.p0.add(cours);
    }

    /* EDT ENSEIGNANT */
    
    public ArrayList<SeanceGroupes> sgE(int id) throws SQLException {
        ArrayList<SeanceGroupes> ss = new ArrayList();
        ResultSet rset1 = this.maconnexion.getStatement().executeQuery("SELECT id_groupe FROM Seance_Groupes WHERE id_seance = " + id);
        while (rset1.next()) {
            ss.add(new SeanceGroupes(id, rset1.getInt("id_groupe")));
        }
        return ss;
    }
    
    public void remplirCours(String s) throws SQLException {
        this.cours = new JLabel(s);
        cours.setHorizontalAlignment(SwingConstants.CENTER);
        this.p0.add(cours);
    }
    
    public ArrayList<SeanceEnseignants> se(String login) throws SQLException {
        ArrayList<SeanceEnseignants> se = new ArrayList();
        ResultSet rset1 = this.maconnexion.getStatement().executeQuery("SELECT * FROM Seance_Enseignants WHERE id_enseignant=(SELECT id FROM Utilisateur WHERE email ='" + login + "')");
        while (rset1.next()) {
            se.add(new SeanceEnseignants(rset1.getInt("id_seance"), rset1.getInt("id_enseignant")));
        }
        return se;
    }

    public ArrayList<SeanceGroupes> seG(Seance s) throws SQLException {
        ArrayList<SeanceGroupes> se = new ArrayList();
        ResultSet rset1 = this.maconnexion.getStatement().executeQuery("SELECT * FROM Seance_Groupes WHERE id_seance=" + s.getId());
        while (rset1.next()) {
            se.add(new SeanceGroupes(rset1.getInt("id_seance"), rset1.getInt("id_groupe")));
        }
        return se;
    }

    public void recupInfos(int i) throws SQLException {
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

    public ArrayList<Cours> sgEnseignant(String login) throws SQLException {
        ArrayList<Cours> sg = new ArrayList();
        ResultSet rset1 = this.maconnexion.getStatement().executeQuery("SELECT * FROM Cours WHERE id=(SELECT id_cours FROM Enseignant WHERE id_utilisateur =(SELECT id FROM Utilisateur WHERE email ='" + login + "'))");
        while (rset1.next()) {
            sg.add(new Cours(rset1.getInt("id"), rset1.getString("nom")));
        }
        return sg;
    }

    // ACCESSEURS 
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
