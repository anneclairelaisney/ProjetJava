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
public class PanneauRecapCoursEnseignant extends JPanel {

    //Attributs
    /* CONNEXION */
    private Connexion maconnexion;
    private JTable monRecap;
    private String login;

    // Constructeur
    public PanneauRecapCoursEnseignant(String login) throws SQLException, ClassNotFoundException {
        super();
        this.maconnexion = new Connexion("jdbc2020", "root", "root");
        this.login = "jeanpierre.segado@ece.fr";
        this.setSize(1000, 750);
        //this.setBackground(new Color(4, 116, 124));
    }

    public PanneauRecapCoursEnseignant() {}

    public void remplirListe() throws Exception {
        this.setVisible(true);
        this.setBackground(new Color(4, 116, 124));
        Border blackline, raisedetched, loweredetched,
                raisedbevel, loweredbevel, empty;

        blackline = BorderFactory.createLineBorder(Color.white);
        raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        raisedbevel = BorderFactory.createRaisedBevelBorder();
        loweredbevel = BorderFactory.createLoweredBevelBorder();

        SeanceLabel seancelabel = new SeanceLabel();
        SeanceEnseignantsDAO seancesesdao = new SeanceEnseignantsDAO(maconnexion);
        GroupeDAO groupedao = new GroupeDAO(maconnexion);
        EnseignantDAO enseignantdao = new EnseignantDAO(maconnexion);
        Enseignant e = enseignantdao.find(this.login);
        System.out.println(e.getEmail());

        ArrayList<Cours> scs = seancelabel.cours(e.getId());
        ArrayList<Seance> nouvelle = seancesesdao.findSeance(this.login);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Matière - Public");
        model.addColumn("Première Séance");
        model.addColumn("Dernière Séance");
        model.addColumn("Durée");
        model.addColumn("Nb");
        JTable table = new JTable(model);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        TableColumn col1 = table.getColumnModel().getColumn(0);
        col1.setPreferredWidth(150);
        TableColumn col2 = table.getColumnModel().getColumn(1);
        col2.setPreferredWidth(150);
        TableColumn col3 = table.getColumnModel().getColumn(2);
        col3.setPreferredWidth(150);
        TableColumn col4 = table.getColumnModel().getColumn(3);
        col4.setPreferredWidth(50);
        TableColumn col5 = table.getColumnModel().getColumn(4);
        col5.setPreferredWidth(50);

        JPanel a = new JPanel();
        a.setLayout(new BorderLayout());
        for (Cours cours : scs) {
            int compteur = 0;
            Seance tempSeance = new Seance(nouvelle.get(0).getId(), nouvelle.get(0).getSemaine(), nouvelle.get(0).getDate(), nouvelle.get(0).getHeureDebut(), nouvelle.get(0).getHeureFin(), nouvelle.get(0).getEtat(), nouvelle.get(0).getIdCours(), nouvelle.get(0).getIdType());
            for (Seance seance : nouvelle) {
                if (seance.getIdCours() == tempSeance.getIdCours()) {
                    if (seance.getDate() != tempSeance.getDate()) {
                        compteur += 1;
                    } else if (seance.getHeureDebut() != tempSeance.getHeureDebut()) {
                        compteur += 1;
                    }
                }
            }
            for (Seance seance : nouvelle) {
                ArrayList<SeanceGroupes> sgs = seancelabel.seG(seance);
                for (SeanceGroupes sg : sgs) {
                    Groupe groupe = groupedao.find(sg.getGroupe());
                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.YEAR, 2020);
                    cal.set(Calendar.WEEK_OF_YEAR, seance.getSemaine());
                    int j = 1;
                    cal.set(Calendar.DAY_OF_WEEK, seance.getDate().getDay() + 1);
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

                    model.addRow(new Object[]{cours.getNom() + " " + groupe.getNom(), weekDay + " " + seance.getHeureDebut() + "h à " + seance.getHeureFin() + "h", weekDay + " " + seance.getHeureDebut() + "h à " + seance.getHeureFin() + "h", compteur + "h", compteur});
                }
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
