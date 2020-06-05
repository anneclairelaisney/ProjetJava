/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.vue;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import jdbc2020.controleur.Connexion;
import jdbc2020.dao.GroupeDAO;
import jdbc2020.dao.SeanceGroupesDAO;
import jdbc2020.modele.Groupe;
import jdbc2020.modele.Seance;
import jdbc2020.modele.SeanceGroupes;
import jdbc2020.modele.SeanceSalles;
import jdbc2020.modele.Site;

/**
 *
 * @author apple
 */
public class Panneau extends JPanel {

    private Connexion maconnexion;
    private String login;
    private int semaine;

    public Panneau() {
        this.setLayout(null);
        this.setSize(1200, 750);
        this.setBackground(new Color(4, 116, 124));
    }

    public void remplirEDT(String login, int semaine) throws SQLException, ClassNotFoundException, Exception {
        
            System.out.println("Id login " + login);
        this.login = login;
        this.semaine = semaine;
        this.maconnexion = new Connexion("jdbc2020", "root", "root");
        this.setVisible(true);
        this.setBackground(new Color(4, 116, 124));
        Insets insets = this.getInsets();
        javax.swing.border.Border blackline = BorderFactory.createLineBorder(Color.white, 1);

        JLabel day = new JLabel();
        day.setVerticalAlignment(SwingConstants.CENTER);
        day.setBorder(blackline);
        day.setBackground(new Color(4, 116, 124));
        day.setPreferredSize(new Dimension(200, 50));
        Dimension s = day.getPreferredSize();
        day.setForeground(Color.white);
        day.setBounds(insets.left, insets.top, s.width, s.height);
        this.add(day);

        int debut = 7;
        int fin = 8;
        for (int i = 1; i < 14; i++) {
            debut += 1;
            fin += 1;
            String h = debut + ":00 - " + fin + ":00";
            JLabel heure = new JLabel(h, JLabel.CENTER);
            heure.setHorizontalAlignment(SwingConstants.CENTER);
            Font font = new Font("Arial", Font.BOLD, 14);
            heure.setFont(font);
            heure.setForeground(Color.WHITE);
            heure.setBackground(new Color(4, 116, 124));
            Dimension size = heure.getPreferredSize();
            heure.setPreferredSize(new Dimension(200, 50));
            heure.setBounds(insets.left + 35, insets.top + i * 50 + 17, size.width, size.height);
            this.add(heure);
        }

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2020);
        cal.set(Calendar.WEEK_OF_YEAR, semaine);

        for (int i = 2; i < 7; i++) {
            int j = 1;
            cal.set(Calendar.DAY_OF_WEEK, i);
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
            weekDay = weekDay + " " + cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1);
            JLabel weekDayPanel = new JLabel(weekDay);
            weekDayPanel.setHorizontalAlignment(SwingConstants.CENTER);
            weekDayPanel.setPreferredSize(new Dimension(200, 50));
            Dimension size = weekDayPanel.getPreferredSize();
            weekDayPanel.setBorder(blackline);
            weekDayPanel.setForeground(Color.white);
            weekDayPanel.setBounds(insets.left + j * 200, insets.top, size.width, size.height);
            this.add(weekDayPanel);
        }
        SeanceLabel tempSeancelabel = new SeanceLabel();
        ArrayList<SeanceGroupes> tempSgs = tempSeancelabel.sgLogin(login);
        for (int j = 0; j < tempSgs.size(); j++) {

            SeanceGroupesDAO seancegrpesdao = new SeanceGroupesDAO(maconnexion);
            SeanceLabel seancelabel = new SeanceLabel();
            ArrayList<SeanceGroupes> sgs = seancelabel.sgLogin(login);

            seancelabel.remplirSeance(sgs.get(j).getSeance());
            ArrayList<Site> ssites = seancelabel.site(seancelabel.getSeance());
            ArrayList<SeanceSalles> sss = seancelabel.ss(sgs.get(j).getSeance());
            ArrayList<Seance> nouvelle = seancegrpesdao.findSeance(sgs.get(j).getGroupe());

            GroupeDAO grpesdao = new GroupeDAO(maconnexion);
            ArrayList<Groupe> groupes = grpesdao.getAllGroupes();

            String strg = " ";
            for (Seance seance : nouvelle) {
                for (Groupe groupe : groupes) {
                    ResultSet rset1 = this.maconnexion.getStatement().executeQuery("SELECT id_groupe FROM Seance_Groupes WHERE id_seance=" + seance.getId());
                    while (rset1.next()) {
                        if(rset1.getInt("id_groupe") == groupe.getId()) {
                            strg += groupe.getNom() + " ";
                        }
                    }
                }
            }
            seancelabel.remplir(strg);

            String strs = " ";
            for (SeanceSalles ss : sss) {
                ResultSet rset2 = this.maconnexion.getStatement().executeQuery("SELECT nom FROM Salle WHERE id = " + ss.getSalle());
                while (rset2.next()) {
                    String name = rset2.getString("nom");
                    strs += name + " ";
                }
            }
            seancelabel.remplir(strs);

            String site = " ";
            for (Site ssite : ssites) {
                site = site + ssite.getNom() + " ";
            }
            seancelabel.remplir(site);

            seancelabel.setPreferredSize(new Dimension(200, 50));
            Dimension size = seancelabel.getPreferredSize();

            int n = 1;
            int m = 1;
            System.out.println("Id seance " + nouvelle.get(j).getId());
            switch (nouvelle.get(j).getHeureDebut()) {
                case 8:
                    n = 1;
                    break;
                case 9:
                    n = 2;
                    break;
                case 10:
                    n = 3;
                    break;
                case 11:
                    n = 4;
                    break;
                case 12:
                    n = 5;
                    break;
                case 13:
                    n = 6;
                    break;
                case 14:
                    n = 7;
                    break;
                case 15:
                    n = 8;
                    break;
                case 16:
                    n = 9;
                    break;
                case 17:
                    n = 10;
                    break;
                case 18:
                    n = 11;
                    break;
                case 19:
                    n = 12;
                    break;
                case 20:
                    n = 13;
                    break;
            }

            switch (nouvelle.get(j).dateToInt()) {
                case 2:
                    m = 2;
                    break;
                case 3:
                    m = 3;
                    break;
                case 4:
                    m = 4;
                    break;
                case 5:
                    m = 5;
                    break;
                case 6:
                    m = 6;
                    break;
            }

            seancelabel.setBounds(insets.left + m * 200, insets.top + n * 50, size.width, size.height);
            this.add(seancelabel);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j <= 15; j++) {
                g.setColor(Color.WHITE);
                g.drawRect(200 * i, 50 * j, 200, 50);
            }
        }
    }
}
