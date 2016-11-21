import java.util.ArrayList;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.File;
public class Controlador{
    private ArrayList<Alumno> Alumnos;
    private ArrayList<Profesor> Profesores;
    private ArrayList<Falta> Faltas;
    private ArrayList<Periodo> Periodos;
    private ArrayList<Curso> Cursos;
    private RandomAccessFile rafAlumnos;
    private RandomAccessFile rafProfesores;
    private RandomAccessFile rafFaltas;
    private RandomAccessFile rafPeriodos;
    private RandomAccessFile rafCursos;


    public Controlador(){
        // Inicializacion de atributos;
        this.Alumnos = new ArrayList<Alumno>();
        this.Profesores = new ArrayList<Profesor>();
        this.Faltas = new ArrayList<Falta>();
        this.Periodos = new ArrayList<Periodo>();
        this.Cursos = new ArrayList<Curso>();
        try{
            this.rafAlumnos = new RandomAccessFile("alumnos.dat", "rw");
            this.rafProfesores = new RandomAccessFile("profesores.dat", "rw");
            this.rafFaltas = new RandomAccessFile("faltas.dat", "rw");
            this.rafPeriodos = new RandomAccessFile("periodos.dat", "rw");
            this.rafCursos = new RandomAccessFile("cursos.dat", "rw");
        }catch(IOException e){
            System.out.println("Exception line 25 ");
            System.out.println(e);
        }
        this.cargarDatos();
    }
    /*
     * Area de Getters
     */
    public ArrayList<Alumno> getAlumnos(){
        return this.Alumnos;
    }

    public ArrayList<Profesor> getProfesores(){
        return this.Profesores;
    }

    public ArrayList<Falta> getFaltas(){
        return this.Faltas;
    }

    public ArrayList<Periodo> getPeriodos(){
        return this.Periodos;
    }

    public ArrayList<Curso> getCursos(){
        return this.Cursos;
    }

    // Funciones para interactuar con el controlador.

    // Crea un Alumno, lo inserta en el array list y regresa la referencia a 
    // este objeto.
    public Alumno createAlumno(String Nombre, String Correo){
        // Primero generar el ID del nuevo alumno, el cual debe
        // ser unico.
        int ID, contador;
        do{
            ID = generateID();
            contador = 0;
            for(Alumno a : Alumnos){
                if (a.getID() == ID)
                    contador++;
            }
        }while(contador > 0);

        Alumno nuevo = new Alumno(ID, Nombre, Correo, 
                new ArrayList<Falta>(), this);
        this.Alumnos.add(nuevo);
        return nuevo;
    }

    // Crea un objeto profesor y lo inserta al array list, regresa 
    // la referencia al objeto creado.
    public Profesor createProfesor(String Nombre, String puesto, 
            String Correo){
        // Primero generar el ID del nuevo alumno, el cual debe
        // ser unico.
        int ID, contador;
        do{
            ID = generateID();
            contador = 0;
            for(Profesor p : this.Profesores){
                if (p.getID() == ID)
                    contador++;
            }
        }while(contador > 0);

        Profesor nuevo = new Profesor(ID, Nombre, puesto, Correo, this);
        this.Profesores.add(nuevo);
        return nuevo;

    }

    public Periodo createPeriodo(int mesInicio, int mesFin, int year){

        int ID, contador;
        do{
            ID = generateID();
            contador = 0;
            for(Periodo p : this.Periodos){
                if (p.getID() == ID)
                    contador++;
            }
        }while(contador > 0);

        Periodo nuevo = new Periodo(ID, mesInicio, mesFin, year, this, 
                new ArrayList<Curso>());
        this.Periodos.add(nuevo);
        return nuevo;
    }

    public Curso createCurso(String Nombre, int faltasAlerta, 
            int IDProfesor, int limiteFaltas) throws Exception{
        Profesor p = encontrarProfesor(IDProfesor);
        if(p == null){
            throw new Exception("No se encontro el profesor");
        }    
        int ID, contador;
        do{
            ID = generateID();
            contador = 0;
            for(Curso c : this.Cursos){
                if (c.getID() == ID)
                    contador++;
            }
        }while(contador > 0);

        Curso nuevo = new Curso(ID, Nombre, faltasAlerta, limiteFaltas, p, 
                this, new ArrayList<Alumno>() );
        this.Cursos.add(nuevo);
        return nuevo;
    }

    public Falta createFalta(Date fecha, boolean justificada){
        /*
         * Codigo para crear Alumno
         */ 
        int ID, contador;
        do{
            ID = generateID();
            contador = 0;
            for(Falta p : this.Faltas){
                if (p.getID() == ID)
                    contador++;
            }
        }while(contador > 0);

        Falta nuevo = new Falta(ID, fecha, justificada, this);
        this.Faltas.add(nuevo);

        return nuevo;
    }

    // Funciones para eliminar objetos de disco.

    public void deleteAlumno(int id) throws Exception{
        Alumno a = encontrarAlumno(id);
        if(a == null)
            throw new Exception("no se encontro el alumno especificado");
        else{
            for(Curso curso : cursos){
                curso.eliminaAlumno(id);   
            }
            ArrayList<Falta> faltas = a.getFaltas();
            for(Falta f : faltas){
             this.Faltas.remove(f);   
            }
            this.Alumnos.remove(a);
            
        }
    }

    public void deleteProfesor(int id) throws Exception {
        Profesor a = encontrarProfesor(id);
        if(a == null)
            throw new Exception("no se encontro el profesor especificado");
        else{
            for(Curso curso: this.Cursos){
              if(curso.getProfesor().getID() == id)
                  this.deleteCurso(curso.getID());
            }
            this.Profesores.remove(a);
        }
    }

    public void deletePeriodo(int id) throws Exception{
        Periodo a = encontrarPeriodo(id);
        if(a == null)
            throw new Exception("no se encontro el periodo especificado");
        else{
            
            this.Periodos.remove(a);
        }
    }

    public void deleteCurso(int id) throws Exception{
        Curso a = encontrarCurso(id);
        if(a == null)
            throw new Exception("no se encontro el curso especificado");
        else{
            for(Periodo periodo : this.Periodos){
                periodo.eliminaCurso(id);
            }
            this.Cursos.remove(a);
        }
    }

    public void deleteFalta(int id) throws Exception{
        Falta a = encontrarFalta(id);
        if(a == null)
            throw new Exception("no se encontro la falta especificada");
        else{
            for(Alumno alumno : this.Alumnos){
                alumno.eliminaFalta(id);
                }
            this.Faltas.remove(a);
    }

    public boolean guardarDatos(){
        /*
         * Guarda los datos en disco
         */
        if( this.guardarFaltas() &&
                this.guardarProfesores() &&
                this.guardarAlumnos() &&
                this.guardarCursos() &&
                this.guardarPeriodos())
            return true;
            
        return false;
    }

    /*
     * Seccion de metodos privados que sirven para guardar la informacion
     * de los objetos en el disco.
     */

    // Funcion auxiliar que serializa una string para escribirla
    // en un archivo
    private String convertString(String entrada, int size){
        String salida = entrada+"$";
        while(salida.length() < size){
            salida += " ";
        }
        return salida;
    }

    // Funcion auxiliar que recupera una string a partir de una string serial.
    private String recoverString(RandomAccessFile archivo, int length)
        throws IOException{
        String temp = "";
        String salida = "";
        for(int i=0; i<length; i++)
            temp += archivo.readChar();
        int index = 0;
        while(index < temp.length() &&temp.charAt(index) != '$'){
            salida += temp.charAt(index);
            index++;
        }
        return salida;
    }

    private boolean guardarAlumnos(){
        // Registro de alumnos:
        // ID: 4 bytes
        // Nombre: 20 chars, 40 bytes
        // Correo: 30 chars, 60 bytes
        // CantidadFaltas: Int, 4 bytes
        // ListaDe faltas: CantidadFaltas*4 bytes
        try{
            this.rafAlumnos.close();
            File al = new File("alumnos.dat");
            al.delete();
            al = null;
            this.rafAlumnos = new RandomAccessFile("alumnos.dat", "rw");
            for(Alumno a : this.Alumnos){
                rafAlumnos.writeInt(a.getID());
                rafAlumnos.writeChars(convertString(a.getNombre(), 20));
                rafAlumnos.writeChars(convertString(a.getCorreo(), 30));
                ArrayList<Falta> faltas = a.getFaltas();
                rafAlumnos.writeInt(faltas.size());

                // Solo se almacena la referencia a la falta.
                for(Falta f : faltas){
                    rafAlumnos.writeInt(f.getID());
                }
            }}catch(IOException e){
                System.out.println("Line 266 ");
                System.out.println(e);
            }

        return true;
    }

    private boolean guardarProfesores(){
        // Registro de Profesor:
        // ID: int, 4 bytes
        // Nombre: String, 20 chars, 40 bytes
        // Puesto: String, 20 chars, 40 bytes
        // Correo: String, 30 chars, 60 bytes
        try{
            this.rafProfesores.close();
            File pr = new File("profesores.dat");
            pr.delete();
            pr = null;
            this.rafProfesores = new RandomAccessFile("profesores.dat", "rw");
            for(Profesor p : this.Profesores){
                rafProfesores.writeInt(p.getID());
                rafProfesores.writeChars(convertString(p.getNombre(), 20));
                rafProfesores.writeChars(convertString(p.getPuesto(), 20));
                rafProfesores.writeChars(convertString(p.getCorreo(), 30));
            }
        }catch(IOException e){
            System.out.println("line 284");
            System.out.println(e);
            return false;
        }
        return true;

    }

    // Guarda la informacion de periodos en el disco.
    private boolean guardarPeriodos(){
        // Registro de periodos
        // ID: int, 4 bytes
        // MesInicio: int, 4 bytes
        // MesFin: int, 4 bytes
        // Year: int, 4 bytes
        // CantidadCursos: int, 4 bytes
        // ListaCursos: CantidadCursos*4 bytes
        try{
            this.rafPeriodos.close();
            File per = new File("periodos.dat");
            per.delete();
            this.rafPeriodos = new RandomAccessFile("periodos.dat", "rw");
            for(Periodo p : this.Periodos){
                this.rafPeriodos.writeInt(p.getID());
                this.rafPeriodos.writeInt(p.getMesInicio());
                this.rafPeriodos.writeInt(p.getMesFin());
                this.rafPeriodos.writeInt(p.getYear());
                ArrayList<Curso> c = p.getCursos();
                this.rafPeriodos.writeInt(c.size());
                for(Curso cu : c){
                    this.rafPeriodos.writeInt(cu.getID());
                } 
            }
            return true;
        }catch(Exception e){
            System.out.println("guardar Periodos");
            System.out.println(e);
            return false;
        }
    }

    private boolean guardarCursos(){
        // Registro curso:
        // ID: int, 4 bytes
        // Nombre: 20 chars, 40 bytes
        // FaltasAlerta: 4 bytes
        // LimiteFaltas: 4 bytes
        // Profesor: Int, 4 bytes
        // CantidadAlumnos: Int 4 bytes
        // Alumnos: CantidadAlumnos * 4 bytes
        try{
            this.rafCursos.close();
            File cur = new File("cursos.dat");
            cur.delete();
            this.rafCursos = new RandomAccessFile("cursos.dat", "rw");
            for(Curso curso : this.Cursos){
                this.rafCursos.writeInt(curso.getID());
                this.rafCursos.writeChars(convertString(curso.getNombre(), 20));
                this.rafCursos.writeInt(curso.getFaltasAlerta());
                this.rafCursos.writeInt(curso.getLimiteFaltas());
                this.rafCursos.writeInt(curso.getProfesor().getID());
                ArrayList<Alumno> alumnos = curso.getAlumnos();
                this.rafCursos.writeInt(alumnos.size());
                for(Alumno alumno : alumnos){
                    this.rafCursos.writeInt(alumno.getID());
                }

            }
            return true;
        }catch(Exception e){
            System.out.println("Guardar cursos");
            System.out.println(e);
            return false;
        }
    }

    private boolean guardarFaltas(){
        // Registro de Falta
        // ID: Int, 4 bytes
        // Dia: Int, 4 bytes
        // Mes: Int, 4 bytes
        // año: int, 4 bytes
        // Justificada: boolean 1 byte
        try{
            this.rafFaltas.close();
            File arcFalta = new File("faltas.dat");
            arcFalta.delete();
            arcFalta = null;
            this.rafFaltas = new RandomAccessFile("faltas.dat", "rw");
            for(Falta falta : this.Faltas){
                this.rafFaltas.writeInt(falta.getID());
                Date fecha = falta.getFecha();
                this.rafFaltas.writeInt(fecha.getDay());
                this.rafFaltas.writeInt(fecha.getMonth());
                this.rafFaltas.writeInt(fecha.getYear());
                this.rafFaltas.writeBoolean(falta.getJustificada());

            }
            return true;
        }catch(Exception e){
            System.out.println("Guardar faltas");
            System.out.println(e);
            return false;
        }
    }

    /* 
     * Funciones dedicadas a cargar los datos de los archivos a los ArrayList.
     */
    public boolean cargarDatos(){
        if(this.cargarFaltas() &&
                this.cargarProfesores() &&
                this.cargarAlumnos() &&
                this.cargarCursos() &&
                this.cargarPeriodos())
            return true;
        else
            return false;
    }
    public boolean cargarAlumnos(){
        // Registro de alumnos:
        // ID: 4 bytes
        // Nombre: 20 chars, 40 bytes
        // Correo: 30 chars, 60 bytes
        // CantidadFaltas: Int, 4 bytes
        // ListaDeFaltas: CantidadFaltas*4 bytes
        try{
            while(rafAlumnos.getFilePointer() < rafAlumnos.length()){
                int ID = rafAlumnos.readInt();
                String Nombre = "";
                Nombre = recoverString(rafAlumnos, 20);
                String Correo = "";
                Correo = recoverString(rafAlumnos, 30);

                int CantidadFaltas = rafAlumnos.readInt();

                ArrayList<Falta> faltas = new ArrayList<Falta>();
                for(int i=0; i<CantidadFaltas; i++){
                    int IDFalta = rafAlumnos.readInt();
                    Falta f = encontrarFalta(IDFalta);
                    faltas.add(f);
                }
                Alumno nuevo = new Alumno(ID, Nombre, Correo, faltas, this);
                this.Alumnos.add(nuevo);
            }
        }catch(IOException e){
            System.out.println("line 332");
            System.out.println(e);
        }
        return true;
    }

    // Lee los datos del profesor del disco y los carga en objetos.
    private boolean cargarProfesores(){
        // Registro de Profesor:
        // ID: int, 4 bytes
        // Nombre: String, 20 chars, 40 bytes
        // Puesto: String, 20 chars, 40 bytes
        // Correo: String, 30 chars, 60 bytes
        try{
            while(rafProfesores.getFilePointer() < rafProfesores.length()){
                int ID = rafProfesores.readInt();
                String Nombre = recoverString(rafProfesores, 20);
                String Puesto = recoverString(rafProfesores, 20);
                String Correo = recoverString(rafProfesores, 30);
                Profesor p = new Profesor(ID, Nombre, Puesto, Correo, this);
                this.Profesores.add(p);
            }
        }catch(Exception e){
            System.out.println("cargar Profesores");
            System.out.println(e);
            return false;
        }
        return true;
    }

    // Se cargan los datos de los periodos del disco a los objetos
    // Para la lista de cursos solo se guardan las referencias de los cursos.
    private boolean cargarPeriodos(){
        // Registro de periodos
        // ID: int, 4 bytes
        // MesInicio: int, 4 bytes
        // MesFin: int, 4 bytes
        // Year: int, 4 bytes
        // CantidadCursos: int, 4 bytes
        // ListaCursos: CantidadCursos*4 bytes
        try{
            while(this.rafPeriodos.getFilePointer() < this.rafPeriodos
                    .length()){
                int ID = this.rafPeriodos.readInt();
                int mesInicio = this.rafPeriodos.readInt();
                int mesFin = this.rafPeriodos.readInt();
                int year = this.rafPeriodos.readInt();
                int cantidad = this.rafPeriodos.readInt();
                ArrayList<Curso> cursos = new ArrayList<Curso>();
                for(int i=0; i< cantidad; i++){
                    int cId = rafPeriodos.readInt();
                    Curso c = encontrarCurso(cId);
                    cursos.add(c);
                }
                Periodo per = new Periodo(ID, mesInicio, mesFin,
                        year, this, cursos);
                this.Periodos.add(per);
                    }
            return true;
        }catch(Exception e){
            System.out.println("cargarPeriodos");
            System.out.println(e);
            return false;
        }

    }

    private boolean cargarCursos(){
        // Registro curso:
        // ID: int, 4 bytes
        // Nombre: 20 chars, 40 bytes
        // FaltasAlerta: 4 bytes
        // LimiteFaltas: 4 bytes
        // Profesor: Int, 4 bytes
        // CantidadAlumnos: Int 4 bytes
        // Alumnos: CantidadAlumnos * 4 bytes
        try{    
            while(this.rafCursos.getFilePointer() <
                    this.rafCursos.length()){
                int ID = this.rafCursos.readInt();
                String nombre = recoverString(this.rafCursos, 20);
                int FaltasAlerta = this.rafCursos.readInt();
                int LimiteFaltas = this.rafCursos.readInt();
                int IDProfesor = this.rafCursos.readInt();
                Profesor profesor = encontrarProfesor(IDProfesor);
                int CantidadAlumnos = this.rafCursos.readInt();
                ArrayList<Alumno> alumnos = new ArrayList<Alumno>();

                for(int i = 0; i<CantidadAlumnos; i++){
                    int AlumnoID = this.rafCursos.readInt();
                    Alumno alumno = encontrarAlumno(AlumnoID);
                    alumnos.add(alumno);
                }
                Curso curso = new Curso(ID, nombre, FaltasAlerta,
                        LimiteFaltas, profesor, this, alumnos);
                this.Cursos.add(curso);
                    }
            return true;
        }catch(Exception e){
            System.out.println("Cargar Cursos");
            System.out.println(e);
            return false;
        }
    }

    private boolean cargarFaltas(){
        // Registro de Falta
        // ID: Int, 4 bytes
        // Dia: Int, 4 bytes
        // Mes: Int, 4 bytes
        // año: int, 4 bytes
        // Justificada: boolean 1 byte
        try{    
            while(this.rafFaltas.getFilePointer() < this.rafFaltas.length()){
                int ID = this.rafFaltas.readInt();
                int dia = this.rafFaltas.readInt();
                int month = this.rafFaltas.readInt();
                int year = this.rafFaltas.readInt();
                Date fecha = new Date(dia, month, year);
                boolean justificada = this.rafFaltas.readBoolean();
                Falta falta = new Falta(ID, fecha, justificada, this);
                this.Faltas.add(falta);
            }
            return true;

        }catch(Exception e){
            System.out.println("Cargar Faltas");
            System.out.println(e);
            return false;
        }
    }

    // Metodo utilizado para generar un ID a un objeto nuevo
    private int generateID(){
        int ID = (int) (Math.random()*(1000000000 - 1))+1;
        return ID;
    }

    // Funciones utiles para encontrar un objeto dado su ID.

    public Alumno encontrarAlumno(int ID){
        for(Alumno A : Alumnos){
            if(A.getID() == ID)
                return A;
        }
        return null;
    }
    public Profesor encontrarProfesor(int ID){
        for(Profesor A : Profesores){
            if(A.getID() == ID)
                return A;
        }
        return null;
    }
    public Periodo encontrarPeriodo(int ID){
        for(Periodo A : Periodos){
            if(A.getID() == ID)
                return A;
        }
        return null;
    }
    public Curso encontrarCurso(int ID){
        for(Curso A : Cursos){
            if(A.getID() == ID)
                return A;
        }
        return null;
    }
    public Falta encontrarFalta(int ID){
        for(Falta A : Faltas){
            if(A.getID() == ID)
                return A;
        }
        return null;
    }
}
