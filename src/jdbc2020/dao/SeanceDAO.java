/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import jdbc2020.controleur.Connexion;
import jdbc2020.modele.Seance;

/**
 *
 * @author apple
 */
public class SeanceDAO extends DAO<Seance> {

    public SeanceDAO(Connexion conn) {
        super(conn);
    }

    public boolean create(Seance seance) {
        try {
            this.connect.getStatement().executeUpdate("INSERT INTO `SEANCE` (`ID`, `SEMAINE`, `DATE`, `HEURE_DEBUT`, `HEURE_FIN`, `ETAT`, `ID_COURS`, `ID_TYPE`) VALUES ('" + seance.getId() + "','" + seance.getSemaine() + "','" + seance.getDate() + "','" + seance.getHeureDebut() + "','" + seance.getHeureFin() + "','" + seance.getEtat() + "','" + seance.getIdCours() + "','" + seance.getIdType() + "');");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Seance seance) {
        return true;
    }

    public boolean update(Seance seance) {
        try {
            this.connect.getStatement().executeUpdate("UPDATE Seance SET semaine ='" + seance.getSemaine() + "', date ='" + seance.getDate() + "', heure_debut ='" + seance.getHeureDebut() + "', heure_fin ='" + seance.getHeureFin() + "', etat ='" + seance.getEtat() + "', id_cours ='" + seance.getIdCours() + "', id_type ='" + seance.getIdType() + "' WHERE id =" + seance.getId() + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Seance find(int id) {
        Seance seance = new Seance();
        try {
            ResultSet rset = this.connect.getStatement().executeQuery("SELECT * FROM Seance WHERE id = " + id);
            if (rset.first()) {
                seance = new Seance(id, rset.getInt("semaine"), rset.getDate("date"), rset.getInt("heure_debut"), rset.getInt("heure_fin"), rset.getInt("etat"), rset.getInt("id_cours"), rset.getInt("id_type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seance;
    }

    public void display(Seance seance) {
        if (seance.getId() != 0) {
            System.out.println("Semaine - " + seance.getSemaine());
            System.out.println("Date - " + seance.getDate());
            System.out.println("Heure de Debut - " + seance.getHeureDebut());
            System.out.println("Heure de Fin - " + seance.getHeureFin());
            System.out.println("Etat - " + seance.getEtat());
        }
    }
}
