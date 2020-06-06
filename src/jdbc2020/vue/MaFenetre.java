/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import javax.swing.*;

/**
 *
 * @author apple
 */
public class MaFenetre extends JFrame {

    private Panneau pan;

    /**
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public MaFenetre() throws SQLException, ClassNotFoundException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(960, 700);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        //pan = new Panneau();
        this.add(pan);
    }

    /**
     *
     * @param x
     * @param y
     * @param titre
     */
    public MaFenetre(int x, int y, String titre) {
        setSize(x, y);
        setTitle(titre);
        setVisible(true);
    }

}
