
public class Falta{
    private Date fecha;
    private int ID;
    private boolean Justificada;
    private Controlador controlador;
    public Falta(int ID, Date fecha, boolean Justificada, 
                 Controlador controlador){
        this.ID = ID;
        this.fecha = fecha;
        this.Justificada = Justificada;
        this.controlador = controlador;
    }
    /*
     * Getters
     */
   public int getID(){
        return this.ID;
   } 

   public Date getFecha(){
        return this.fecha;
   }

   public boolean getJustificada(){
        return this.Justificada;
   }

   /*
    * Setters
    */

   public void setFecha(Date fecha){
        this.fecha = fecha;
   }
}
