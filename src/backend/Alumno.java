package backend;

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
    private int NumFaltas;
    private int NumFaltasJustificadas;
    private int NumRetardos;
    private ArrayList<Falta> Faltas;
    private ControladorBackend controlador;

    /* Constructor default */
    public Alumno(int ID, String Nombre, String Correo, int NumFaltas, int NumFaltasJustificadas,
                  int NumRetardos, ArrayList<Falta> Faltas, ControladorBackend controlador){
        this.ID = ID;
        this.Nombre = Nombre;
        this.Correo = Correo;
        this.NumFaltas = NumFaltas;
        this.NumFaltasJustificadas = NumFaltasJustificadas;
        this.NumRetardos = NumRetardos;
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

    public int getNumFaltas() {
        calcularTipoFaltas();
        return NumFaltas;
    }

    public int getNumFaltasJustificadas() {
        calcularTipoFaltas();
        return NumFaltasJustificadas;
    }

    public int getNumRetardos() {
        calcularTipoFaltas();
        return NumRetardos;
    }

    public ArrayList<Falta> getFaltas(){
        return this.Faltas;
    }

    public ControladorBackend getControlador() {
        return controlador;
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

    public void setNumFaltas(int NumFaltas) {
        this.NumFaltas = NumFaltas;
    }

    public void setNumFaltasJustificadas(int NumFaltasJustificadas) {
        this.NumFaltasJustificadas = NumFaltasJustificadas;
    }

    public void setNumRetardos(int NumRetardos) {
        this.NumRetardos = NumRetardos;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setFaltas(ArrayList<Falta> Faltas) {
        this.Faltas = Faltas;
    }

    public void setControlador(ControladorBackend controlador) {
        this.controlador = controlador;
    }

   /*
    * Area de funciones de la clase
    */

  // Funcion que agrega una falta al alumno, el manejo de la actualizacion
  // de la informacion en disco es automatica.
  //
  // Regresa la instancia de la falta agregada al alumno.
  public Falta agregaFalta(Fecha fecha, int tipo){
      Falta falta = this.controlador.createFalta(fecha, tipo);
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
  
  public void calcularTipoFaltas() {
      NumFaltas = 0;
      NumFaltasJustificadas = 0;
      NumRetardos = 0;
      for(Falta act : Faltas) {
          switch(act.getTipo()) {
              case 1:
                  NumFaltas++;
                  break;
              case 2:
                  NumFaltasJustificadas++;
                  break;
              case 3:
                  NumRetardos++;
                  break;
          }
      }
  }
    
}
