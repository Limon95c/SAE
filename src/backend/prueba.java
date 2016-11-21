
import java.util.ArrayList;
public class prueba{
    public static void printAll(Controlador c1)
    {
        ArrayList<Alumno> alumni = c1.getAlumnos();
        ArrayList<Profesor> profes = c1.getProfesores();
        ArrayList<Falta> faltas = c1.getFaltas();
        ArrayList<Periodo> periodos = c1.getPeriodos();
        ArrayList<Curso> cursos = c1.getCursos();
        
        System.out.println("Alumnos:");
        for(int i=0; i<alumni.size();i++)
        {
            System.out.println(alumni.get(i).getID());
            System.out.println(alumni.get(i).getNombre());
            System.out.println(alumni.get(i).getFaltas());
            System.out.println(alumni.get(i).getCorreo());
        }
        System.out.println("Profesores");
        for(int i=0; i<profes.size();i++)
        {
            System.out.println(profes.get(i).getID());
            System.out.println(profes.get(i).getNombre());
            System.out.println(profes.get(i).getPuesto());
            System.out.println(profes.get(i).getCorreo());
        }
        System.out.println("faltas:");
        for(int i=0; i<faltas.size();i++)
        {
            System.out.println(faltas.get(i).getID());
            System.out.println(faltas.get(i).getFecha());
            System.out.println(faltas.get(i).getJustificada());
        }
        System.out.println("periodos:");
        for(int i=0; i<periodos.size();i++)
        {
            System.out.println(periodos.get(i).getID());
            System.out.println(periodos.get(i).getCursos());
            System.out.println(periodos.get(i).getMesInicio());
            System.out.println(periodos.get(i).getMesFin());
            System.out.println(periodos.get(i).getYear());
            
        }
        System.out.println("Cursos");
        for(int i=0; i<cursos.size();i++)
        {
            System.out.println(cursos.get(i).getID());
            System.out.println(cursos.get(i).getNombre());
            System.out.println(cursos.get(i).getProfesor());
            System.out.println(cursos.get(i).getFaltasAlerta());
            System.out.println(cursos.get(i).getLimiteFaltas());
            System.out.println(cursos.get(i).getAlumnos());
        }
    }
    
    public static void guardarTodo(Controlador c1) throws Exception{
         Alumno a1 = c1.createAlumno("Elias Mera","eliasmera@gmail.com");
        Alumno a2 = c1.createAlumno("Jorge Limón","limonc95@gmail.com");
        Alumno a3 = c1.createAlumno("Pablo Brubeck","pbrubeck@gmail.com");
        Alumno a4 = c1.createAlumno("Juan José López","jj.lopezjaimez@gmail.com");
        
        a1.agregaFalta(new Date(12,8,2014), true);
        a3.agregaFalta(new Date(12,8,2014), false);
        a2.agregaFalta(new Date(9,12,2016), false);
        a3.agregaFalta(new Date(2,2,2015), true);
        
        Profesor prof1 = c1.createProfesor("José Guevara", "Profesor", "jhgm_1995@hotmail.com");
        Profesor prof2 = c1.createProfesor("Miguel Cuellas", "ProfesorAsistente", "migcuellar@hotmail.com");
        Profesor prof3 = c1.createProfesor("Oscar Gonzalez", "Profesor", "oscarg@hotmail.com");
        Profesor prof4 = c1.createProfesor("Melissa Treviño", "Directora", "melyTreviño@hotmail.com");
        
        Curso cur1 = c1.createCurso("Matematicas", 3, prof1.getID(), 5);
        Curso cur2 = c1.createCurso("Historia", 2, prof2.getID(), 3);
        Curso cur3 = c1.createCurso("Español", 4, prof3.getID(), 8);
        Curso cur4 = c1.createCurso("Ingles", 5, prof4.getID(), 6);
        
        cur1.agregaAlumno(a1.getID());
        cur1.agregaAlumno(a2.getID());
        cur1.agregaAlumno(a3.getID());
        cur1.agregaAlumno(a4.getID());
        cur1.agregaAlumno("Juan Pablo", "jp@hotmail.com");
        cur2.agregaAlumno(a2.getID());
        cur3.agregaAlumno(a3.getID());
        cur4.agregaAlumno(a1.getID());
        cur4.agregaAlumno(a2.getID());
        
        Periodo per1 = c1.createPeriodo(8, 12, 2015);
        Periodo per2 = c1.createPeriodo(1, 4, 2014);
        Periodo per3 = c1.createPeriodo(5, 8, 2013);
        Periodo per4 = c1.createPeriodo(3, 6, 2012);
        
        per1.agregaCurso(cur1.getID());
        per1.agregaCurso("Japones", 3, 8, prof2.getID());
        per2.agregaCurso(cur2.getID());
        per3.agregaCurso(cur3.getID());
        per3.agregaCurso(cur4.getID());
        per4.agregaCurso(cur4.getID());
        
        Falta f1 = c1.createFalta(new Date(12,8,2014), true);
        Falta f2 = c1.createFalta(new Date(9,12,2016), false);
        Falta f3 = c1.createFalta(new Date(1,1,2015), true);
        Falta f4 = c1.createFalta(new Date(6,6,2006), false);
        
        printAll(c1);
        
        c1.guardarDatos();
    }
    
    public static void borrarDatos(Controlador c1) throws Exception
    {
        ArrayList<Alumno> alumni = c1.getAlumnos();
        ArrayList<Profesor> profes = c1.getProfesores();
        ArrayList<Falta> faltas = c1.getFaltas();
        ArrayList<Periodo> periodos = c1.getPeriodos();
        ArrayList<Curso> cursos = c1.getCursos();
        
        c1.deleteAlumno(alumni.get(0).getID());
        c1.deleteCurso(cursos.get(0).getID());
        c1.deleteFalta(faltas.get(0).getID());
        c1.deletePeriodo(periodos.get(0).getID());
        c1.deleteProfesor(profes.get(0).getID());
        
        c1.guardarDatos();
    }
    
    public static void main(String[] args) throws Exception{
        Controlador c1 = new Controlador();
        
        printAll(c1);
        //guardarTodo(c1);
        //borrarDatos(c1);
    }

}
