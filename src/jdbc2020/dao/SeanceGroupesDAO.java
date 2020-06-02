/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.dao;

import java.sql.Date;
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
public class SeanceGroupesDAO extends DAO<SeanceGroupes> {

    public SeanceGroupesDAO(Connexion conn) {
        super(conn);
    }

    public boolean create(SeanceGroupes seance) {
        try {
            this.connect.getStatement().executeUpdate("WHERE [NOT] EXISTS (INSERT INTO SEANCE_GROUPES(ID_SEANCE,ID_GROUPE) VALUES ((SELECT id FROM Seance WHERE id =" + seance.getSeance() + "),(SELECT id FROM Groupe WHERE id =" + seance.getGroupe() + "));");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(SeanceGroupes seance) {
        return true;
    }

    public boolean update(SeanceGroupes seance) {
        return false;
    }

    public SeanceGroupes find(int id) {
        SeanceGroupes x = null;

        try {
            ResultSet rset = this.connect.getStatement().executeQuery("SELECT * FROM Seance_Groupes WHERE id_groupe = " + id);
            if (rset.first()) {
                int seance = rset.getInt("Id_Seance");
                int groupe = rset.getInt("Id_groupe");
                x = new SeanceGroupes(seance, groupe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }

    public Seance findSeance(int id) {
        Seance x = null;
        try {
            ResultSet rset = this.connect.getStatement().executeQuery("SELECT * FROM Seance where ID = (SELECT ID_SEANCE FROM Seance_Groupes WHERE id_groupe = " + id + ")");
            if (rset.first()) {
                x = new Seance(rset.getInt("ID"), rset.getInt("Semaine"), rset.getDate("Date"),rset.getInt("Heure_Debut"),rset.getInt("Heure_Fin"), rset.getInt("Etat"), rset.getInt("ID_COURS"),rset.getInt("ID_TYPE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }
    
    public ArrayList<SeanceGroupes> getAllSeanceGroupes() throws Exception {
        ArrayList<SeanceGroupes> list = new ArrayList<>();
        Statement myStatement = null;
        ResultSet rset = null;
        try {
            rset = this.connect.getStatement().executeQuery("SELECT * FROM Seance_Groupes");
            while (rset.next()) {
                int seance = rset.getInt("id_seance");
                int groupe = rset.getInt("id_groupe");
                SeanceGroupes tempGroup = new SeanceGroupes(seance, groupe);
                list.add(tempGroup);
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
    public void display(SeanceGroupes obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
