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

    /**
     *
     * @param str
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public static void main(String[] str) throws SQLException, ClassNotFoundException, Exception {
        new GenerateurConnexion();
    }
}
