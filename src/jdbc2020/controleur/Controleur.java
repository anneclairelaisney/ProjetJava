/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jdbc2020.controleur;

import java.sql.SQLException;
import jdbc2020.dao.*;
import jdbc2020.modele.*;
import jdbc2020.vue.*;

/**
 *
 * Contrôle l'interrogation de la BDD dans la Fenetre
 *
 * @author segado
 */
public class Controleur {

    /**
     *
     * une methode principal (main) pour lancer l'application
     *
     * @param
     */
    public static void main(String[] s) throws SQLException, ClassNotFoundException {
        new Generateur();
    }
}
