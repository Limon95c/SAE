/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import javax.swing.JFrame;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 * SAE - Sistema de Asistencia Escolar
 * @author Jorge Limón, José María Flores, Juan José López, José Humberto Guevara
 */
public class SAE extends JFrame implements Runnable, MouseListener {
    
    // Manejo de archivos
    PrintWriter fileOut; // Archivo de escritura
    BufferedReader fileIn; // Archivo de lectura
    
    /* objetos para manejar el buffer del JFrame y 
       que la imagen no parpadee */
    private Image    imaImagenJFrame;   // Imagen a proyectar en JFrame
    private Graphics graGraficaJFrame;  // Objeto grafico de la Imagen
    
    private Profesor Profe; // Objeto profesor
    
    private Icono btnEditProfe; // Boton de editar profesor
    
    private Vector<Vector<String>> sTrim; // Lista de trimestres
    
    private int iTrimActual; // Trimestre Actual
    private int iCursoActual; // Curso Actual
    
    // Fonts y colores
    private Font fontTituloToolbar; // Font de los titulos en la barra
    private Color colorTitulo; // Color de los titulos
    
    private Image imaImagenFondo; // Imagen del fondo
    
    private int iEscena; // Pantalla actual
    
    private final int iGLXToolbar = 720; //Coordenada X de guía para botones en toolbar
    private final int iGLYFinProfe = 137; //Coordenada X de guía para botones en toolbar
    private final int iGLYToolbar = 95; //Coordenada Y de guía para botones en toolbar
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
        // Mouse
        addMouseListener(this);
        // Correr el init
        init();
        // Correr el start
        start();
    }
    
    public void init() {
        // Creo la imagen del fondo
        URL urlImagenFondo = this.getClass().getResource("Fondo.jpg");
        imaImagenFondo = Toolkit.getDefaultToolkit().getImage(urlImagenFondo);
        
        // Creo el boton para editar profesor
        URL urlIconoEditar = this.getClass().getResource("configicon.png");
	btnEditProfe = new Icono(0, 0, Toolkit.getDefaultToolkit().getImage(urlIconoEditar));
        btnEditProfe.setX(iGLXToolbar + 188);
        btnEditProfe.setY(iGLYToolbar + 10);
        
        sTrim = new Vector<Vector<String>>();
        iTrimActual = -1;
        iCursoActual = -1;
        Profe = new Profesor();
        cargarDatosIniciales();
        
        // Titulos y fonts
        fontTituloToolbar = Font.decode("Arial Unicode MS-BOLD-16");
        colorTitulo = Color.getColor("#212121", 0x212121);
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
     * @param g es el objeto de <code>Graphics</code> usado para
     * dibujar.
     * 
     */
    public void paint1(Graphics g) {
        // Dibuja la imagen de fondo y el fondo de la Toolbar
        g.drawImage(imaImagenFondo, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(700, 65, 265, 470); //Toolbar
        
        // Texto y Fondo de Profesor en Toolbar
        g.setColor(colorTitulo);
        g.setFont(fontTituloToolbar);
        g.drawString("Profesor", iGLXToolbar, iGLYToolbar); // Titulo profesor
        g.setColor(Color.DARK_GRAY);
        g.fillRect(iGLXToolbar, iGLYToolbar + 10, iGLWToolbar - 40, 32); // Fondo de Profesor
        
        // Nombre de profesor y boton
        g.setColor(Color.WHITE);
        if(Profe.getNombre().length() > 17) {
            g.drawString(Profe.getNombre().substring(0, 14) + "...", iGLXToolbar + 3, iGLYToolbar + 32);
        }
        else {
            g.drawString(Profe.getNombre(), iGLXToolbar + 3, iGLYToolbar + 32);
        }
        btnEditProfe.paint(g, this);
        
        // Texto y Fondo de Trimestre
        g.setColor(Color.DARK_GRAY);
        g.setFont(fontTituloToolbar);
        g.drawString("Trimestre", iGLXToolbar, iGLYFinProfe + 20); // Titulo profesor
        g.fillRect(iGLXToolbar, iGLYFinProfe + 30, iGLWToolbar - 40, 32); // Fondo de Profesor
        
        switch(iEscena) {
            case 1:
                break;
            case 2:
                // Fondo principal
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(25, 65, 665, 470);
            default:
                break;
        };
    }
    
    public void cargarDatosIniciales() {
        StringTokenizer tok; // Divisor de strings
        String linea; // Lector de lineas
        // Abrimos el archivo en caso de que hubiera uno
        try {
            // Abrir el archivo
            fileIn = new BufferedReader(new FileReader("DPTM.txt"));
            // Obtenemos la primera linea
            linea = fileIn.readLine();
            // Dividimos la linea por tokens
            tok = new StringTokenizer(linea, "/");
            
            //----------------------CARGAR PROFESOR--------------------------
            // Obtener el nombre
            Profe.setNombre(tok.nextToken());
            // Obtener el puesto
            Profe.setPuesto(tok.nextToken());
            // Obtener el correo
            Profe.setCorreo(tok.nextToken());
            
            //-----------------CARGAR TRIMESTRES Y CURSOS--------------------
            // Obtenemos la siguiente linea
            linea = fileIn.readLine();
            while(linea != null) { // Mientras haya trimestres por leer
                // Dividir linea y agregar un nuevo trimestre
                tok = new StringTokenizer(linea, "/");
                sTrim.add(new Vector<String>());
                iTrimActual++;
                // Obtener nombre del trimestre
                sTrim.elementAt(iTrimActual).add(tok.nextToken());
                // Mientras haya tokens
                while(tok.countTokens() > 0) {
                    // Obtener el nombre del curso
                    sTrim.elementAt(iTrimActual).add(tok.nextToken());
                }
            }
            
            // Cerrar el archivo
            fileIn.close();
        }
        catch (Exception e){
            // Si no se encuentra archivo guardado crear uno y pedir datos
            obtenerProfe();
        }
    }
    
    public void obtenerProfe() {
        try
        {
            // Pedir datos
            String nom = JOptionPane.showInputDialog("Nombre del profesor:");
            String pue = JOptionPane.showInputDialog("Puesto del profesor:");
            String corr = JOptionPane.showInputDialog("Correo del profesor:");
            
            if(nom != null && pue != null && corr != null)
            {
                // Asignar en objeto Profe
                Profe.setNombre(nom);
                Profe.setPuesto(pue);
                Profe.setCorreo(corr);

                // Guardar datos en archivo
                fileOut = new PrintWriter(new FileWriter("DPTM.txt"));
                fileOut.println(nom + "/" + pue + "/" + corr);
                fileOut.close();
            }
        }
        catch (Exception e) {
            System.out.println("Error de guardado");
        }
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
        // Titulo
        SAE.setTitle("SAE - Sistema de Asistencia Estudiantil");
        // Hace visible el JFrame
        SAE.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if(btnEditProfe.contiene(me.getX(), me.getY()))
            obtenerProfe();
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }    
    
}
