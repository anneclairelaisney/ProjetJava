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
import java.util.Calendar;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import jdbc2020.dao.*;

/**
 *
 * Affiche dans la fenetre graphique les champs de tables et les requetes de la
 * BDD
 *
 * @author segado
 */
public class PanneauRecapCoursEtudiant extends JPanel {

    //Attributs
    /* CONNEXION */
    private Connexion maconnexion;
    private JTable monRecap;
    private String login;

    // Constructeur

    /**
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public PanneauRecapCoursEtudiant() throws SQLException, ClassNotFoundException {
        this.setSize(800, 750);
        this.setBackground(new Color(4, 116, 124));
        this.maconnexion = new Connexion("jdbc2020", "root", "root");
    }

    /**
     *
     * @param login
     * @throws Exception
     */
    public void remplirListe(String login) throws Exception {
        this.setVisible(true);
        this.setBackground(new Color(4, 116, 124));
        Border blackline, raisedetched, loweredetched,
                raisedbevel, loweredbevel, empty;

        blackline = BorderFactory.createLineBorder(Color.white);
        raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        raisedbevel = BorderFactory.createRaisedBevelBorder();
        loweredbevel = BorderFactory.createLoweredBevelBorder();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Matière - Public");
        model.addColumn("Date");
        model.addColumn("Enseignants");
        model.addColumn("Groupes");
        model.addColumn("Type de Cours");
        JTable table = new JTable(model);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        TableColumn col1 = table.getColumnModel().getColumn(0);
        col1.setPreferredWidth(200);
        TableColumn col2 = table.getColumnModel().getColumn(1);
        col2.setPreferredWidth(200);
        TableColumn col3 = table.getColumnModel().getColumn(2);
        col3.setPreferredWidth(200);
        TableColumn col4 = table.getColumnModel().getColumn(3);
        col4.setPreferredWidth(200);
        TableColumn col5 = table.getColumnModel().getColumn(4);
        col5.setPreferredWidth(100);

        JPanel a = new JPanel();
        a.setLayout(new BorderLayout());

        UtilisateurDAO userdao = new UtilisateurDAO(this.maconnexion);
        Utilisateur u = userdao.find(login);
        EtudiantDAO etudiantdao = new EtudiantDAO(this.maconnexion);
        Etudiant e = etudiantdao.find(u.getId());
        System.out.println(u.getEmail());
        GroupeDAO groupedao = new GroupeDAO(this.maconnexion);
        TypeCoursDAO typedao = new TypeCoursDAO(this.maconnexion);
        SeanceGroupesDAO seancegrpesdao = new SeanceGroupesDAO(maconnexion);
        SeanceEnseignantsDAO seanceensdao = new SeanceEnseignantsDAO(maconnexion);
        SeanceLabel seancelabel = new SeanceLabel();
        SeanceDAO seancedao = new SeanceDAO(this.maconnexion);
        ArrayList<SeanceGroupes> sgs = seancelabel.sgLogin(login);

        ArrayList<Cours> cours = new ArrayList<>();
        ArrayList<Seance> nouvelle = seancegrpesdao.findSeance(e.getIdGroupe());

        for (SeanceGroupes sg : sgs) {
            Seance seance = seancedao.find(sg.getSeance());
            try {
                ResultSet rset = this.maconnexion.getStatement().executeQuery("SELECT * FROM Cours WHERE id = " + seance.getIdCours());
                while (rset.next()) {
                    cours.add(new Cours(rset.getInt("id"), rset.getString("nom")));
                }
            } catch (SQLException err) {
                err.printStackTrace();
            }
        }

        for (Cours c : cours) {
            for (Seance s : nouvelle) {
                ArrayList<SeanceEnseignants> teachers = seancelabel.seSalle(s.getId());
                ArrayList<SeanceGroupes> groups = seancelabel.sgE(s.getId());
                TypeCours type = typedao.find(s.getIdType());
                String teacherstr = "";
                String groupstr = "";
                for (SeanceGroupes sg : groups) {
                    Groupe groupe = groupedao.find(sg.getGroupe());
                    groupstr += " " + groupe.getNom();
                }
                for (SeanceEnseignants se : teachers) {
                    Utilisateur teacher = userdao.find(se.getEnseignant());
                    teacherstr += " " + teacher.getPrenom() + " " + teacher.getNom();
                }
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, 2020);
                cal.set(Calendar.WEEK_OF_YEAR, s.getSemaine());
                int j = 1;
                cal.set(Calendar.DAY_OF_WEEK, s.getDate().getDay() + 1);
                String weekDay = "";

                switch (cal.get(Calendar.DAY_OF_WEEK)) {
                    case 1:
                        weekDay = "Dimanche";
                        break;
                    case 2:
                        weekDay = "Lundi";
                        break;
                    case 3:
                        weekDay = "Mardi";
                        j = 2;
                        break;
                    case 4:
                        weekDay = "Mercredi";
                        j = 3;
                        break;
                    case 5:
                        weekDay = "Jeudi";
                        j = 4;
                        break;
                    case 6:
                        weekDay = "Vendredi";
                        j = 5;
                        break;
                }
                weekDay += " " + cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1);
                model.addRow(new Object[]{c.getNom(), weekDay + " " + s.getHeureDebut() + "h à " + s.getHeureFin() + "h", teacherstr, groupstr, type.getNom()});
            }
        }

        TableCellRenderer rendererFromHeader = table.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        table.getTableHeader().setPreferredSize(new Dimension(150, 30));
        table.getTableHeader().setForeground(Color.white);
        table.getTableHeader().setBackground(new Color(4, 116, 124));
        table.getTableHeader().setBorder(blackline);
        table.setBorder(blackline);
        table.getTableHeader().setSize(20, 20);
        UIManager.put("Table.alternateRowColor", new Color(255, 255, 204));
        table.setRowHeight(30);
        table.setRowMargin(13);

        DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
        custom.setHorizontalAlignment(JLabel.CENTER); // centre les données de ton tableau
        for (int i = 0; i < table.getColumnCount(); i++) // centre chaque cellule de ton tableau
        {
            table.getColumnModel().getColumn(i).setCellRenderer(custom);
        }

        a.add(table.getTableHeader(), BorderLayout.NORTH);
        a.add(table, BorderLayout.CENTER);
        a.setVisible(true);
        table.getTableHeader().setVisible(true);
        table.setVisible(true);
        this.add(a);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
