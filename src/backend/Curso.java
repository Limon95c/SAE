
/*
 * Clase encargada de modelar el funcionamiento de un curso.
 * Esta clase no se debe de instanciar fuera de controlador
 */ 
import java.util.ArrayList;

public class Curso{
    private int ID;
    private String Nombre;
    private int FaltasAlerta;
    private int LimiteFaltas;
    private Profesor profesor;
    private Controlador controlador;
    private ArrayList<Alumno> Alumnos;
    public Curso(int ID, String Nombre, int FaltasAlerta, int LimiteFaltas,
                 Profesor profesor, Controlador controlador, 
                 ArrayList<Alumno> alumnos){
        this.ID = ID;
        this.Nombre = Nombre;
        this.FaltasAlerta = FaltasAlerta;
        this.LimiteFaltas = LimiteFaltas;
        this.profesor = profesor;
        this.controlador = controlador;
        this.Alumnos = alumnos;
    }
    
    /* 
     * Area de Getters
     */ 
    public int getID(){
        return this.ID;
    }

    public Profesor getProfesor(){
        return this.profesor;
    }

    public String getNombre(){
        return this.Nombre;
    }

    public int getFaltasAlerta(){
        return this.FaltasAlerta;
    }  

    public int getLimiteFaltas(){
        return this.LimiteFaltas;
    }

    public ArrayList<Alumno> getAlumnos(){
        return this.Alumnos;
    }

    /*
     * Area de setters
     */
    
    public void setProfesor(Profesor p){
        this.profesor = p;
    }

    public void setNombre(String n){
        this.Nombre = n;
    }

    public void setFaltasAlerta(int FaltasAlerta){
        this.FaltasAlerta = FaltasAlerta;
    }      

    public void setLimiteFaltas(int LimiteFaltas){
        this.LimiteFaltas = LimiteFaltas;
    }   

    /*
     * Area de funciones de la clase
     */

    // CREA y agrega un alumno al curso.
   public Alumno agregaAlumno(String Nombre, String Correo){
        Alumno alumno = this.controlador.createAlumno(Nombre, Correo);
        this.Alumnos.add(alumno);
        return alumno;
   }

  // AGREGA un alumno que ya esta creado al curso 
    public Alumno agregaAlumno(int ID){
        try{
            Alumno alumno = controlador.encontrarAlumno(ID);
            this.Alumnos.add(alumno);
            return alumno;
        }catch(Exception e){
            System.out.println("Agrega alumno");
            System.out.println(e);
        return null;
        }
    }

    // Elimina un alumno de la lista de referencia de este objeto.
    public void eliminaAlumno(int ID){
        for(int i=0; i<this.Alumnos.size(); i++){
            Alumno alumno = this.Alumnos.get(i);
            if (alumno.getID() == ID)
                this.Alumnos.remove(i);
        }
    }

    // Elimina un alumno de la lista de referencia de este objeto y de la base
    // de datos.
    public void eliminaAlumnoTotal(int ID){
        try{
            this.controlador.deleteAlumno(ID);
            this.eliminaAlumno(ID);
        }catch(Exception e){
            System.out.println("Elimina Alumno Total");
            System.out.println(e);
        }

    }

}
