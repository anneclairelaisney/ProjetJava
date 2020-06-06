/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.dao;

import jdbc2020.controleur.*;
import jdbc2020.modele.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author apple
 */
public class SeanceSallesDAO extends DAO<SeanceSalles> {

    /**
     *
     * @param conn
     */
    public SeanceSallesDAO(Connexion conn) {
        super(conn);
    }

    public boolean create(SeanceSalles seance) {
        try {
            this.connect.getStatement().executeUpdate("WHERE [NOT] EXISTS (INSERT INTO Seance_Salles(ID_SEANCE,ID_SALLE) VALUES ((SELECT id FROM Seance WHERE id =" + seance.getSeance() + "),(SELECT id FROM Salle WHERE id =" + seance.getSalle() + "));");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(SeanceSalles seance) {
        return true;
    }

    public boolean update(SeanceSalles seance) {
        return false;
    }

    public SeanceSalles find(int id) {
        SeanceSalles x = null;
        try {
            ResultSet rset = this.connect.getStatement().executeQuery("SELECT * FROM Seance_Salles WHERE id_salle = " + id);
            if (rset.first()) {
                int seance = rset.getInt("Id_Seance");
                int salle = rset.getInt("Id_groupe");
                x = new SeanceSalles(seance, salle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }
    
    /**
     *
     * @param id
     * @return ArrayList
     */
    public ArrayList<Seance> findSeance(int id) {
        Seance x = null;
        ArrayList<Seance> seancestemp = new ArrayList<>();
        ArrayList<Seance> seances = new ArrayList<>();

        try {
            ResultSet rset = this.connect.getStatement().executeQuery("SELECT * FROM Seance");
            while (rset.next()) {
                x = new Seance(rset.getInt("ID"), rset.getInt("Semaine"), rset.getDate("Date"), rset.getInt("Heure_Debut"), rset.getInt("Heure_Fin"), rset.getInt("Etat"), rset.getInt("ID_COURS"), rset.getInt("ID_TYPE"));
                seancestemp.add(x);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Seance s : seancestemp) {
            try {
                ResultSet rset1 = this.connect.getStatement().executeQuery("SELECT ID_SEANCE FROM Seance_Salles WHERE id_salle = " + id);
                while (rset1.next()) {
                    if(s.getId() == rset1.getInt("id_seance")) {
                        seances.add(s);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return seances;
    }
    
    /**
     *
     * @return ArrayList
     * @throws Exception
     */
    public ArrayList<SeanceSalles> getAllSeanceSalles() throws Exception {
        ArrayList<SeanceSalles> list = new ArrayList<>();
        Statement myStatement = null;
        ResultSet rset = null;
        try {
            rset = this.connect.getStatement().executeQuery("SELECT * FROM Seance_Salles");
            while (rset.next()) {
                int seance = rset.getInt("id_seance");
                int salle = rset.getInt("id_salle");
                SeanceSalles tempSalle = new SeanceSalles(seance, salle);
                list.add(tempSalle);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(myStatement, rset);
            return list;
        }
    }

    private static void close(Connexion myConnection, Statement myStatement, ResultSet myResult) throws SQLException {
        if (myResult != null) {
            myResult.close();
        }
        if (myStatement != null) {
        }
        if (myConnection != null) {
        }
    }

    private void close(Statement myStmt, ResultSet myRs) throws SQLException {
        close(null, myStmt, myRs);
    }

    @Override
    public void display(SeanceSalles obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
