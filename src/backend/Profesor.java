
/**
 * Clase que contiene la informacion relevante a los profesores.
 * Favor de notar que esta clase nunca debe ser instanciada fuera de 
 * la clase controlador, para crear un nuevo objeto Profesor, usar el metodo
 * crearProfesor de la clase controlador
 */
public class Profesor{
    private int ID;
    private String Nombre;
    private String Puesto;
    private String Correo;
    private Controlador controlador;
    public Profesor(int id, String Nombre, String Puesto, String Correo, 
                    Controlador controlador){
        this.ID = id;
        this.Nombre = Nombre;
        this.Puesto = Puesto;
        this.Correo = Correo;
        this.controlador = controlador;
        
    }
    /**
     * Area de Getters
     */

    public int getID(){
        return this.ID;
    }  

    public String getNombre(){
        return this.Nombre;
    }

    public String getPuesto(){
        return this.Puesto;
    }

    public String getCorreo(){
        return this.Correo;
    }

    /* 
     * Area de Setters
     */

    public void setNombre(String nombre){
        this.Nombre = nombre;
    }

    public void setPuesto(String puesto){
        this.Puesto = puesto;
    }

    public void setCorreo(String correo){
        this.Correo = correo;
    }
}
