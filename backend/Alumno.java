import java.util.ArrayList;
/*
 * Clase que modela el funcionamiento del alumno.
 *
 * Esta clase no debe ser instanciada fuera de controlador.
 */ 
public class Alumno{
    private int ID;
    private String Nombre;
    private String Correo;
    private ArrayList<Falta> Faltas;
    private Controlador controlador;

    /* Constructor default */
    public Alumno(int ID, String Nombre, String Correo, 
                  ArrayList<Falta> Faltas, Controlador controlador){
        this.ID = ID;
        this.Nombre = Nombre;
        this.Correo = Correo;
        this.Faltas = Faltas;
        this.controlador = controlador;

    }
    /*
     * Area de Getters
     */ 

    public int getID(){
        return this.ID;
    }
        
    public String getNombre(){
        return this.Nombre;
    }

    public String getCorreo(){
        return this.Correo;
    }

    public ArrayList<Falta> getFaltas(){
        return this.Faltas;
    }

    /*
     * Area de Setters
     */

   public void setNombre(String Nombre){
        this.Nombre = Nombre;
   } 

   public void setCorreo(String Correo){
        this.Correo = Correo;
   }

   /*
    * Area de funciones de la clase
    */

  // Funcion que agrega una falta al alumno, el manejo de la actualizacion
  // de la informacion en disco es automatica.
  //
  // Regresa la instancia de la falta agregada al alumno.
  public Falta agregaFalta(Date fecha, boolean justificada){
      Falta falta = this.controlador.createFalta(fecha, justificada);
      this.Faltas.add(falta);
      return falta;
  }

  // Funcion que, dado el ID de una falta, la elimina de la lista de 
  // referencias del alumno. La actualizacion de datos en disco es manejada
  // de manera automatica.
  public void eliminaFalta(int ID){
    for(int i=0; i<this.Faltas.size(); i++){
        Falta falta = this.Faltas.get(i);
        if (falta.getID() == ID)
            this.Faltas.remove(i);
    }
  }

  // Funcion que dado el ID de una falta, la elimina de la lista de referencias
  // del alumno Y de la base de datos.
  public void eliminaFaltaTotal(int ID){
      try{
      this.controlador.deleteFalta(ID);
      this.eliminaFalta(ID);
      }catch (Exception e){
          System.out.println("Elimina Falta Total");
          System.out.println(e);
      }
  }

}
