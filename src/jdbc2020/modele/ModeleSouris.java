/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc2020.modele;

import java.awt.MouseInfo;

/**
 *
 * @author apple
 */
public class ModeleSouris {
    private double xpos;
    private double ypos;
    
    /**
     *
     * @return
     */
    public double getXpos()
    {
        return xpos;
    }
    
    /**
     *
     * @return
     */
    public double getYpos()
    {
        return ypos;
    }
    
    /**
     *
     * @param x
     * @param y
     */
    public ModeleSouris(double x, double y)
    {
        xpos = x;
        ypos = y;
    }
}