/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.controleur;

import java.sql.SQLException;

/**
 *
 * @author apple
 */

public class Controleur {
    public static void main(String[] s) throws SQLException, ClassNotFoundException, Exception {
        new GenerateurConnexion();
    }
}
