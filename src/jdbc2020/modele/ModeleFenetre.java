/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.modele;

import java.util.Scanner;

/**
 *
 * @author apple
 */
public class ModeleFenetre {

    private int dimX;
    private int dimY;
    private String winTitle = "Fenetre";

    public ModeleFenetre() {
        dimX = 300;
        dimY = 150; // donne une taille en hauteur et largeur à la fenêtre
        winTitle = "Fenetre";
    }

    public int getDimX() {
        return dimX;
    }

    public int getDimY() {
        return dimY;
    }

    public String getWinTitle() {
        return winTitle;
    }

    public void SaisirFen() {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Veuillez saisir une dimension positive en X");
            dimX = sc.nextInt();
        } while (dimX <= 0);

        do {
            System.out.println("Veuillez saisir une dimension positive en Y");
            dimY = sc.nextInt();
        } while (dimY <= 0);

        System.out.println("Veuillez saisir un titre pour la fenetre");
        winTitle = sc.next();
        System.out.println(winTitle);
    }
}
