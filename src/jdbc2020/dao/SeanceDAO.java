/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import jdbc2020.controleur.Connexion;
import jdbc2020.modele.Enseignant;
import jdbc2020.modele.Groupe;
import jdbc2020.modele.Salle;
import jdbc2020.modele.Seance;
import jdbc2020.modele.SeanceGroupes;

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
            this.connect.getStatement().executeUpdate("INSERT INTO `SEANCE` (`ID`, `SEMAINE`, `DATE`, `HEURE_DEBUT`, `HEURE_FIN`, `ETAT`, `ID_COURS`, `ID_TYPE`) VALUES ('" + seance.getId() + "','" + seance.getSemaine()
                    + "','" + seance.getDate() + "','" + seance.getHeureDebut() + "','" + seance.getHeureFin() + "','" + seance.getEtat() + "',(SELECT id FROM Cours WHERE id='" + seance.getIdCours() + "'),(SELECT id FROM Type_Cours WHERE id='" + seance.getIdType() + "'));");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createEnseignants(Seance seance, Enseignant enseignant) {
        try {
            this.connect.getStatement().executeUpdate("INSERT INTO `SEANCE_ENSEIGNANTS` (`ID_SEANCE`, `ID_ENSEIGNANT`) VALUES ('" + seance.getId() + "',(SELECT id_utilisateur FROM Enseignant WHERE id_utilisateur='" + enseignant.getId() + "'));");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createGroupes(Seance seance, Groupe groupe) {
        try {
            this.connect.getStatement().executeUpdate("INSERT INTO `SEANCE_GROUPES` (`ID_SEANCE`, `ID_GROUPE`) VALUES ('" + seance.getId() + "',(SELECT id FROM Groupe WHERE id='" + groupe.getId() + "'));");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createSalles(Seance seance, Salle salle) {
        try {
            this.connect.getStatement().executeUpdate("INSERT INTO `SEANCE_SALLES` (`ID_SEANCE`, `ID_SALLE`) VALUES ('" + seance.getId() + "',(SELECT id FROM Salle WHERE id='" + salle.getId() + "'));");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Seance seance) {
        return true;
    }

    public boolean update(Seance seance, int cours, int type_cours) {
        try {
            this.connect.getStatement().executeUpdate("UPDATE Seance SET id_cours ='" + cours + "', id_type ='" + type_cours + "' WHERE id =" + seance.getId() + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean validate(Seance seance) {
        try {
            this.connect.getStatement().executeUpdate("UPDATE Seance SET etat ='2' WHERE id =" + seance.getId() + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean cancel(Seance seance) {
        try {
            this.connect.getStatement().executeUpdate("UPDATE Seance SET etat ='3' WHERE id =" + seance.getId() + ";");
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
            while (rset.next()) {
                seance = new Seance(id, rset.getInt("semaine"), rset.getDate("date"), rset.getInt("heure_debut"), rset.getInt("heure_fin"), rset.getInt("etat"), rset.getInt("id_cours"), rset.getInt("id_type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seance;
    }

    public ArrayList<Seance> find(String login) throws SQLException {
        ArrayList<SeanceGroupes> listTemp = new ArrayList<>();
        ArrayList<Seance> seance = new ArrayList<Seance>();
        Statement myStatement = null;
        ResultSet rset = null;
        try {
            rset = this.connect.getStatement().executeQuery("SELECT * FROM SEANCE_GROUPES WHERE ID_GROUPE=(SELECT ID_GROUPE FROM ETUDIANT WHERE ID_UTILISATEUR =(SELECT ID FROM UTILISATEUR WHERE EMAIL ='" + login + "'))");
            while (rset.next()) {
                int id_seance = rset.getInt("ID_SEANCE");
                SeanceGroupes temp = new SeanceGroupes(id_seance, rset.getInt("ID_GROUPE"));
                listTemp.add(temp);
                for (SeanceGroupes e : listTemp) {
                ResultSet rset1 = null;
                rset1 = this.connect.getStatement().executeQuery("SELECT * FROM SEANCE where id=" + e.getSeance());
                if (rset1.first()) {
                    int semaine = rset1.getInt("Semaine");
                    Date date = rset1.getDate("Date");
                    int heureDebut = rset1.getInt("Heure_Debut");
                    int heureFin = rset1.getInt("Heure_Fin");
                    int etat = rset1.getInt("Etat");
                    int id_cours = rset1.getInt("ID_COURS");
                    int id_type = rset1.getInt("ID_TYPE");
                    Seance tempSeance = new Seance(id_seance, semaine, date, heureDebut, heureFin, etat, id_cours, id_type);
                    seance.add(tempSeance);
                }
            }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(myStatement, rset);
            return seance;
        }
    }

    public ArrayList<Seance> getAllSeances() throws Exception {
        ArrayList<Seance> list = new ArrayList<Seance>();
        Statement myStatement = null;
        ResultSet rset = null;
        try {
            rset = this.connect.getStatement().executeQuery("SELECT * FROM Seance");
            while (rset.next()) {
                Seance tempSeance = convertRowToSeance(rset);
                list.add(tempSeance);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            return list;
        }
    }

    private Seance convertRowToSeance(ResultSet myResult) throws SQLException {
        int id = myResult.getInt("ID");
        int semaine = myResult.getInt("Semaine");
        Date date = myResult.getDate("Date");
        int heure_debut = myResult.getInt("Heure_debut");
        int heure_fin = myResult.getInt("Heure_fin");
        int etat = myResult.getInt("Etat");
        int id_cours = myResult.getInt("Id_cours");
        int id_type = myResult.getInt("Id_type");
        Seance tempSeance = new Seance(id, semaine, date, heure_debut, heure_fin, etat, id_cours, id_type);
        return tempSeance;
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

    @Override
    public boolean update(Seance obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
}
