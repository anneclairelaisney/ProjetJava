/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jdbc2020.controleur;

import java.awt.Color;
import java.awt.GridLayout;
import jdbc2020.modele.*;
import jdbc2020.vue.*;

import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author apple
 */

public class Controleur {
    public static void main(String[] s) throws SQLException, ClassNotFoundException {
        new Generateur();
    }
}
