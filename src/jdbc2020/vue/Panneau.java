/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.vue;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apple
 */
public class Panneau extends JPanel {

    public Panneau() throws SQLException, ClassNotFoundException {
        this.setLayout(null);
        this.setSize(800, 750);
        this.setBackground(new Color(4,116,124));
        Insets insets = this.getInsets();

        javax.swing.border.Border blackline = BorderFactory.createLineBorder(Color.white, 1);
        int numSemaine = 22;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2020);
        cal.set(Calendar.WEEK_OF_YEAR, numSemaine);
       
        for (int i = 2; i < 7; i++) { 
            int j = 0;
            cal.set(Calendar.DAY_OF_WEEK, i);
            String weekDay = "";
            switch (cal.get(Calendar.DAY_OF_WEEK)) {
                case 1:
                    weekDay = "Dimanche";
                    break;
                case 2:
                    weekDay = "Lundi";
                    j=0;
                    break;
                case 3:
                    weekDay = "Mardi";
                    j=1;
                    break;
                case 4:
                    weekDay = "Mercredi";
                    j=2;
                    break;
                case 5:
                    weekDay = "Jeudi";
                    j=3;
                    break;
                case 6:
                    weekDay = "Vendredi";
                    j=4;
                    break;
            }
            weekDay = weekDay + " " + cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1);
            JLabel weekDayPanel = new JLabel(weekDay);
            weekDayPanel.setHorizontalAlignment(SwingConstants.CENTER);
            weekDayPanel.setPreferredSize(new Dimension(160, 50));
            Dimension size = weekDayPanel.getPreferredSize();
            weekDayPanel.setBorder(blackline);
            weekDayPanel.setForeground(Color.white);
            weekDayPanel.setBounds(insets.left + j*160, insets.top, size.width, size.height);
            this.add(weekDayPanel);
        }
        for (int i = 1; i < 3; i++) {
            int n = 0;
            int m = 0;
            SeanceLabel seance = new SeanceLabel();
            seance.remplirSeance(i);
            seance.setPreferredSize(new Dimension(160, 50));
            Dimension size = seance.getPreferredSize();

            switch (seance.getSeance().getHeureDebut()) {
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

            switch (seance.getSeance().dateToInt()) {
                case 2:
                    m = 1;
                    break;
                case 3:
                    m = 2;
                    break;
                case 4:
                    m = 3;
                    break;
                case 5:
                    m = 4;
                    break;
                case 6:
                    m = 5;
                    break;
            }

            seance.setBounds(insets.left + m * 160, insets.top + n * 50, size.width, size.height);
            this.add(seance);

        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j <= 14; j++) {
                g.drawRect(160 * i, 50 * j, 160, 50);
                g.setColor(Color.WHITE);
            }
        }
    }
}
