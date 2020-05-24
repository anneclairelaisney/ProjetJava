/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.vue;

import java.util.Vector;
import javax.swing.*;
import jdbc2020.modele.Seance;

/**
 *
 * @author apple
 */
public class EDT extends JPanel {

    private JScrollPane tableScroll = new JScrollPane();
    private JTable tableP;
    private JTable tableV;

    public EDT(Vector<String> aHorizontalHeader,Vector<String> aVerticalHeader,Vector<Vector<Seance>> aDataVector) {
        this.tableP = new JTable(aDataVector, aHorizontalHeader);

        this.tableScroll.setViewportView(tableP);

        // creation de la table d'entete verticale
        Vector<Vector<String>> vectTbV = new Vector<Vector<String>>();

        for (int i = 0; i < aVerticalHeader.size(); i++) {
            Vector<String> tempVect = new Vector<String>();
            tempVect.add(aVerticalHeader.get(i));
            vectTbV.add(tempVect);
        }
        Vector<String> vectTbVHead = new Vector<String>();
        vectTbVHead.add("");
        this.tableV = new JTable(vectTbV, vectTbVHead);
        this.tableV.setDefaultRenderer(Seance.class, this.tableP.getTableHeader().getDefaultRenderer());
        this.tableV.setEnabled(false);

        //ajout de la tableV au header du scroll
        this.tableScroll.setRowHeaderView(tableV);

        // ajout au panel maitre
        this.add(tableScroll);
    }

    public JScrollPane getTableScroll() {
        return tableScroll;
    }

    public JTable getTableP() {
        return tableP;
    }

    public JTable getTableV() {
        return tableV;
    }
}
