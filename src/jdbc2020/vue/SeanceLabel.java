/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.vue;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JLabel;
import jdbc2020.controleur.Connexion;
import jdbc2020.dao.DAO;
import jdbc2020.dao.SeanceDAO;
import jdbc2020.modele.Seance;

/**
 *
 * @author apple
 */
public class SeanceLabel extends JPanel {

    //Attributs
    private Connexion maconnexion;
    private JLabel cours, groupe, salle, site;
    private Seance seance;

    public SeanceLabel() throws SQLException, ClassNotFoundException {
        this.maconnexion = new Connexion("jdbc2020", "root", "root");

        // creation des textes
        this.cours = new JLabel();
        this.groupe = new JLabel();
        this.salle = new JLabel();
        this.site = new JLabel();
        this.seance = new Seance();
    }

    public void remplirSeance(int i) {
        DAO<Seance> seanceDAO = new SeanceDAO(this.maconnexion);
        Seance a = seanceDAO.find(i);
        this.seance = a;
        try {
            ResultSet rset = this.maconnexion.getStatement().executeQuery("SELECT nom FROM Cours WHERE id = " + a.getIdCours());
            if (rset.first()) {
                cours = new JLabel(rset.getString("nom"));
            }
            this.add(cours);
            
            rset = this.maconnexion.getStatement().executeQuery("SELECT nom FROM Groupe WHERE id = (SELECT id_groupe FROM Seance_Groupes WHERE id_seance=" + a.getId() + ")");
            if (rset.first()) {
                groupe = new JLabel(rset.getString("nom"));
            }
            this.add(groupe);
            
            rset = this.maconnexion.getStatement().executeQuery("SELECT nom FROM Salle WHERE id = (SELECT id_salle FROM Seance_Salles WHERE id_seance=" + a.getId() + ")");
            if (rset.first()) {
                salle = new JLabel(rset.getString("nom"));
            }
            this.add(salle);
            
            rset = this.maconnexion.getStatement().executeQuery("SELECT nom FROM Site WHERE id = (SELECT id_site FROM Salle WHERE id= (SELECT id_salle FROM Seance_salles WHERE id_seance=" + a.getId() + "))");
            if (rset.first()) {
                site = new JLabel(rset.getString("nom"));
            }
            this.add(site);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Seance getSeance() {
        return this.seance;
    }
}
