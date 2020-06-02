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
public class SeanceEnseignantsDAO extends DAO<SeanceEnseignants> {

    public SeanceEnseignantsDAO(Connexion conn) {
        super(conn);
    }

    public boolean create(SeanceEnseignants seance) {
        try {
            this.connect.getStatement().executeUpdate("WHERE [NOT] EXISTS (INSERT INTO SEANCE_ENSEIGNANTS(ID_SEANCE,ID_ENSEIGNANT) VALUES ((SELECT id FROM Seance WHERE id =" + seance.getSeance() + "),(SELECT id_utilisateur FROM Enseignant WHERE id_utilisateur =" + seance.getEnseignant() + "));");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(SeanceEnseignants seance) {
        return true;
    }

    public boolean update(SeanceEnseignants seance) {
        return false;
    }

    public SeanceEnseignants find(int id) {
        SeanceEnseignants x = null;

        try {
            ResultSet rset = this.connect.getStatement().executeQuery("SELECT * FROM Seance_Enseignants WHERE id_enseignant = " + id);
            if (rset.first()) {
                int seance = rset.getInt("Id_Seance");
                int enseignant = rset.getInt("Id_enseignant");
                x = new SeanceEnseignants(seance, enseignant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }

    public ArrayList<SeanceEnseignants> getAllSeanceTeachers() throws Exception {
        ArrayList<SeanceEnseignants> list = new ArrayList<>();
        Statement myStatement = null;
        ResultSet rset = null;
        try {
            rset = this.connect.getStatement().executeQuery("SELECT * FROM Seance_Enseignants");
            while (rset.next()) {
                int seance = rset.getInt("id_seance");
                int enseignant = rset.getInt("id_enseignant");
                SeanceEnseignants tempTeacher = new SeanceEnseignants(seance, enseignant);
                list.add(tempTeacher);
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
    public void display(SeanceEnseignants obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
