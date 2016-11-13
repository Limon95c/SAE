/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import javax.swing.JFrame;

/**
 * SAE - Sistema de Asistencia Escolar
 * @author Jorge Limón, José María Flores, Juan José López, José Humberto Guevara
 */
public class SAE extends JFrame implements Runnable {
    
    PrintWriter fileOut; // Archivo de escritura
    BufferedReader fileIn; // Archivo de lectura
    
    /* objetos para manejar el buffer del JFrame y 
       que la imagen no parpadee */
    private Image    imaImagenJFrame;   // Imagen a proyectar en JFrame
    private Graphics graGraficaJFrame;  // Objeto grafico de la Imagen
    
    private Image imaImagenFondo; // Imagen del fondo
    
    private int iEscena; // Pantalla actual
    
    private final int iGLXToolbar = 720; //Coordenada X de guía para botones en toolbar
    private final int iGLYToolbar = 85; //Coordenada Y de guía para botones en toolbar
    private final int iGLWToolbar = 225; //Coordenada Y de guía para botones en toolbar
    
    private static int iWidth; //Ancho
    private static int iHeight; //Alto
    
    public SAE() {
        // Definir el ancho
        iWidth = 1000;
        // Definir el alto
        iHeight = 565;
        // Pantalla actual
        iEscena = 1;
        // Correr el init
        init();
        // Correr el start
        start();
    }
    
    public void init() {
        // Creo la imagen del fondo
        URL urlImagenFondo = this.getClass().getResource("Fondo.jpg");
        imaImagenFondo = Toolkit.getDefaultToolkit().getImage(urlImagenFondo);
    }
    
    public void start () {
        // Declaras un hilo
        Thread th = new Thread (this);
        // Empieza el hilo
        th.start();
    }
    
    public void run() {
        // Mientras se tengan vidas, actualizar posicion, checar colisiones,
        // volver a pintar todo y mandar los hilos a dormir 20 milisegundos.
        while (true) {
            actualiza();
            checaColision();
            repaint();
            try	{
                // El hilo del juego se duerme.
                Thread.sleep (20);
            }
            catch (InterruptedException iexError) {
                System.out.println("Hubo un error en el programa " + 
                        iexError.toString());
            }
        }
    }
    
    /** 
     * actualiza
     * 
     * Metodo que actualiza la posicion de los objetos 
     * 
     */
    public void actualiza(){
        switch(iEscena) {
            case 1:
                break;
            default:
                break;
        };
    }
    
    /**
     * checaColision
     * 
     * Metodo usado para checar la colision entre objetos
     * 
     */
    public void checaColision(){
        switch(iEscena) {
            case 1:
                break;
            default:
                break;
        };
    }
    
    public void paint (Graphics graGrafico){
         
        // Inicializan el DoubleBuffer
        if (imaImagenJFrame == null){
                imaImagenJFrame = createImage (this.getSize().width, 
                        this.getSize().height);
                graGraficaJFrame = imaImagenJFrame.getGraphics ();
        }

        // Actualiza la imagen de fondo.
        URL urlImagenFondo = this.getClass().getResource("Fondo.jpg");
        Image imaImagenFondo =
                Toolkit.getDefaultToolkit().getImage(urlImagenFondo);
         graGraficaJFrame.drawImage(imaImagenFondo, 0, 0, getWidth(),
                 getHeight(), this);

        // Actualiza el Foreground.
        graGraficaJFrame.setColor (getForeground());
        paint1(graGraficaJFrame);

        // Dibuja la imagen actualizada
        graGrafico.drawImage (imaImagenJFrame, 0, 0, this);
    }
    
    /**
     * paint1
     * 
     * Metodo sobrescrito de la clase <code>JFrame</code>,
     * heredado de la clase Container.<P>
     * En este metodo se dibuja la imagen con la posicion actualizada,
     * ademas que cuando la imagen es cargada te despliega una advertencia.
     * 
     * @param graDibujo es el objeto de <code>Graphics</code> usado para
     * dibujar.
     * 
     */
    public void paint1(Graphics graDibujo) {            
        graDibujo.drawImage(imaImagenFondo, 0, 0, getWidth(), getHeight(), this);
        graDibujo.setColor(Color.LIGHT_GRAY);
        graDibujo.fillRect(700, 65, 265, 470); //Toolbar
        graDibujo.fillRect(25, 65, 665, 470); //Principal
        switch(iEscena) {
            case 1:
                break;
            default:
                break;
        };
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        SAE SAE = new SAE();
        // Define el tamaño del JFrame
        SAE.setSize(iWidth, iHeight);
        // Not resizable
        SAE.setResizable(false);
        // Define el boton de cerrar
        SAE.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Hace visible el JFrame
        SAE.setVisible(true);
    }
}
