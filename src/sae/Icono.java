/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

/**
 *
 * @author Jorge
 */
public class Icono {
    private int iX;     //posicion en x.       
    private int iY;     //posicion en y.
    private int iAncho; //ancho del objeto
    private int iAlto; //largo del objeto
    private Image imaImagen;	//imagen.
    private ImageIcon imiImagen;  // imagen con medidas
    
    public Icono(int iX, int iY ,  Image imaImagen) {
        this.iX = iX;
        this.iY = iY;
        this.imaImagen = imaImagen;
        this.imiImagen = new ImageIcon(imaImagen);
        this.iAncho = this.imiImagen.getIconWidth();
        this.iAlto = this.imiImagen.getIconHeight();
    }
    
    public void setX(int iX) {
        this.iX = iX;
    }
    
    public int getX() {
            return iX;
    }
    
    public void setY(int iY) {
            this.iY = iY;
    }
    
    public int getY() {
        return iY;
    }
    
    public void setImagen(Image imaImagen) {
        this.imaImagen = imaImagen;
        this.imiImagen = new ImageIcon(imaImagen);
        this.iAncho = this.imiImagen.getIconWidth();
        this.iAlto = this.imiImagen.getIconHeight();
    }
    
    public Image getImagen() {
        return imaImagen;
    }
    
    public int getAncho() {
        return iAncho;
    }
    
    public int getAlto() {
        return iAlto;
    }
    
    public void paint(Graphics graGrafico, ImageObserver imoObserver) {
        graGrafico.drawImage(getImagen(), getX(), getY(), getAncho(), getAlto(), imoObserver);
    }
    
    public boolean equals(Object objObjeto) {
        // si el objeto parametro es una instancia de la clase Base
        if (objObjeto instanceof Icono) {
            // se regresa la comparación entre este objeto que invoca y el
            // objeto recibido como parametro
            Icono basParam = (Icono) objObjeto;
            return this.getX() ==  basParam.getX() && 
                    this.getY() == basParam.getY() &&
                    this.getAncho() == basParam.getAncho() &&
                    this.getAlto() == basParam.getAlto() &&
                    this.getImagen() == basParam.getImagen();
        }
        else {
            // se regresa un falso porque el objeto recibido no es tipo Base
            return false;
        }
    }
    
    public String toString() {
        return " x: " + this.getX() + " y: "+ this.getY() +
                " ancho: " + this.getAncho() + " alto: " + this.getAlto();
    }
    
    public boolean contiene(int x, int y){
        
        Rectangle recEste = new Rectangle(getX(), getY(), getAncho(),
                getAlto());
        return recEste.contains(x, y);
    }
}
