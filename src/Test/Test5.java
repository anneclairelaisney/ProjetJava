/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.awt.Point;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import jdbc2020.vue.PanneauRecapCoursEnseignant;

/**
 *
 * @author anne-clairelaisney
 */
public class Test5 {

    public Test5() {

        JFrame f = new JFrame("JScrollPaneExemple");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setSize(300, 300);

        ImageIcon image = new ImageIcon("logo.png");
        JLabel pimage = new JLabel();
        pimage.setIcon(image);

        JScrollPane jsp = new JScrollPane(pimage);
        JViewport jvp = new JViewport();
        JScrollBar vjsp = jsp.getVerticalScrollBar();
        JScrollBar hjsp = jsp.getHorizontalScrollBar();
        jvp = jsp.getViewport();
        jvp.setViewPosition(new Point(30, 200));
        jsp.setVerticalScrollBarPolicy(jsp.VERTICAL_SCROLLBAR_ALWAYS);
        jvp.add(vjsp);
        f.add(jsp);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //new PanneauRecapCoursEnseignant("q").setVisible(true);
    }
}
