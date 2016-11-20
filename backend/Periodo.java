import java.util.ArrayList;
public class Periodo{
    private int ID;
    private int MesInicio;
    private int MesFin;
    private int Year;
    private Controlador controlador;
    private ArrayList<Curso> Cursos;
    public Periodo(int ID, int MesInicio, int MesFin, int Year,
            Controlador controlador, ArrayList<Curso> Cursos){
        this.ID = ID;
        this.MesInicio = MesInicio;
        this.MesFin = MesFin;
        this.Year = Year;
        this.controlador = controlador;
        this.Cursos = Cursos;
    }
    /*
     * Area de getters
     */
    public int getID(){
        return this.ID;
    } 

    public int getMesInicio(){
        return this.MesInicio;
    }

    public int getMesFin(){
        return this.MesFin;
    }

    public int getYear(){
        return this.Year;
    }    

    public ArrayList<Curso> getCursos(){
        return this.Cursos;
    }
    /*
     * Area de setters
     */
    public void setMesInicio(int mesInicio){
        this.MesInicio = mesInicio;
    } 

    public void setMesfin(int MesFin){
        this.MesFin = MesFin;
    }

    public void setYear(int year){
        this.Year = year;
    }

    /*
     * Area de metodos de la clase
     */
    // CREA y AGREGA un curso a la clase, regresa la instancia creada.
    public Curso  agregaCurso(String Nombre, int FaltasAlerta, int LimiteFaltas,
            int IDProfesor){
        try{
            Curso curso = this.controlador.createCurso(Nombre, FaltasAlerta, 
                    IDProfesor, LimiteFaltas);
            this.Cursos.add(curso);
            return curso;
        }catch(Exception e){
            System.out.println("Agrega Curso");
            System.out.println(e);
            return null;
        }

    }

    // AGREGA un curso que ya esta creado, regresa la instancia de este curso.
    public Curso agregaCurso(int ID){
        Curso curso = controlador.encontrarCurso(ID);
        this.Cursos.add(curso);
        return curso;
    }

    // ELIMINA un curso de la referencia de este periodo. El curso sigue existiendo
    // en la lista de cursos creados, para eliminarlo definitivamente, se debe
    // de usar la funcion eliminarCurso de controlador.
    public void eliminaCurso(int ID){
        for(int i=0; i< this.Cursos.size(); i++){
            Curso curso = this.Cursos.get(i);
            if (curso.getID() == ID)
                this.Cursos.remove(i);

        }
    }

    // Funcion que elimina el curso de la lista de cursos del periodo y de la lista
    // de cursos creados.
    public void eliminaCursoTotal(int ID){
        try{        
            this.controlador.deleteCurso(ID);
            this.eliminaCurso(ID);
        }catch(Exception e){
            System.out.println("Elimina Curso Total");
            System.out.println(e);
        }
    }

}

