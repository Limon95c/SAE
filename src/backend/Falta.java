package backend;

public class Falta{
    private Fecha fecha;
    private int ID;
    private int Tipo; // 1 = falta normal, 2 = Falta Justificada, 3 = Retardo
    private ControladorBackend controlador;
    public Falta(int ID, Fecha fecha, int tipo, 
                 ControladorBackend controlador){
        this.ID = ID;
        this.fecha = fecha;
        this.Tipo = tipo;
        this.controlador = controlador;
    }
    /*
     * Getters
     */
   public int getID(){
        return this.ID;
   } 

   public Fecha getFecha(){
        return this.fecha;
   }

   public int getTipo(){
        return this.Tipo;
   }

    public ControladorBackend getControlador() {
        return controlador;
    }

   /*
    * Setters
    */

   public void setFecha(Fecha fecha){
        this.fecha = fecha;
   }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTipo(int Tipo) {
        this.Tipo = Tipo;
    }

    public void setControlador(ControladorBackend controlador) {
        this.controlador = controlador;
    }
}
