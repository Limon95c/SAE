/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Jorge
 */
public class Boton {
    private Rectangle areaBtn;
    private Color colorBtn;
    private String textoBtn;
    private Image iconoBtn;
    private boolean activeBtn;
    
    public Boton() {
        areaBtn = new Rectangle(0, 0, 10, 10);
        textoBtn = "";
        colorBtn = Color.DARK_GRAY;
        activeBtn = false;
    }
    
    public Boton(int x, int y, int w, Color color) {
        areaBtn = new Rectangle(x, y, w, 100);
        colorBtn = color;
    }
    
}