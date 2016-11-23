/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC_SAE;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import backend.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;


/**
 * FXML Controller class
 *
 * @author Jorge
 */
public class ControladorSAE implements Initializable {
    
    // Backend
    private ControladorBackend c1;
    
    // Referencias a objetos seleccionados
    public Profesor RefProfe;
    public Periodo RefPeriodo;
    public ArrayList<Curso> RefCursos;
    public Curso RefCursoSelect;
    public ArrayList<Alumno> RefAlumnos;
    public Alumno RefAlumnoSelect;
    public ArrayList<Falta> RefFaltas;
    
    // SidePanel elements
    public TextField ProfeTF;
    public ImageView ConfigProfe;
    public TextField PeriodoTF;
    public ImageView ConfigPeriodo;
    public TableView<Curso> CursoTV;
    public TableColumn<Curso, String> nameColumn;
    public ObservableList<Curso> CursoTableList;
    public Button AgregaCurso;
    public Button EliminaCurso;
    
    // Elementos Principal
    public TableView<Alumno> AlumnosTV;
    public TableColumn<Alumno, String> nomAlumno, Fcol, Rcol, FJcol, FRcol;
    public ObservableList<Alumno> AlumnoTableList;
    public Button AgregaAlumnoBtn;
    public Button AgregaFaltaORetardoBtn;
    public Label TituloCurso;
    public Label TituloPeriodo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Backend
        c1 = new ControladorBackend();
        c1.cargarDatos();
        
        // ______________________CARGAR PROFESOR_____________________________
        // Si existe un profesor desplegarlo
        if(c1.getProfesores().size() > 0) {
            RefProfe = c1.getProfesores().get(0);
            // Desplegar profe
            ProfeTF.setText(RefProfe.getNombre());
        }
        else { // Si no crear uno
            RefProfe = ElegirProfesor.MODALGetRefProfesor(c1);
            ProfeTF.setText(RefProfe.getNombre());
        }
        
        // ___________________CARGAR PERIODO Y CURSOS_____________________
        // Si no existen periodos...
        if(c1.getPeriodos().size() < 1) { 
            // Elegir un periodo nuevo
            RefPeriodo = ElegirPeriodo.MODALGetRefPeriodo(RefPeriodo, c1);
        }
        else { // Si si existen periodos...
            // Seleccionar el periodo más reciente
            RefPeriodo = c1.getPeriodos().get(c1.getPeriodos().size() - 1);
        }
        // Cargar periodo y cursos
        cargarPeriodoYCursos();
        
        // _______________________Botones de SideBar_______________________
        // Si eligen cambiar de periodo...
        ConfigPeriodo.setOnMouseClicked(e -> {
            // Elegir nuevo periodo
            RefPeriodo = ElegirPeriodo.MODALGetRefPeriodo(RefPeriodo, c1);
            // Cargar periodo y cursos
            cargarPeriodoYCursos();
        });
        
        // Si eligen cambiar de profe...
        ConfigProfe.setOnMouseClicked(e -> {
            RefProfe = ElegirProfesor.MODALGetRefProfesor(c1);
            ProfeTF.setText(RefProfe.getNombre());
        });
        
        // Si eligen añadir un curso...
        AgregaCurso.setOnAction(e -> {
            Curso temp = CrearCurso.MODALGetRefCurso(RefPeriodo, c1);
            if(temp != null)
                cargarPeriodoYCursos();
        });
        
        EliminaCurso.setOnAction(e -> {
            Curso temp = CursoTV.getSelectionModel().getSelectedItem();
            RefPeriodo.eliminaCurso(temp.getID());
            c1.guardarDatos();
            cargarPeriodoYCursos();
        });
        
        CursoTV.setOnMouseClicked(e -> {
            if(CursoTV.getSelectionModel().getSelectedItem() != null) {
                RefCursoSelect = c1.encontrarCurso(CursoTV.getSelectionModel().getSelectedItem().getID());
                TituloCurso.setText(RefCursoSelect.getNombre() + " - ");
                TituloPeriodo.setText(RefPeriodo.toString());
                cargaAlumnos();
            }
        });
        
        // _______________________Elementos Principal_______________________
        AgregaAlumnoBtn.setOnAction(e -> {
            RefCursoSelect = AgregarAlumno.MODALGetRefCurso(RefCursoSelect, c1);
            cargaAlumnos();
        });
        
        AgregaFaltaORetardoBtn.setOnAction(e -> {
            if(AlumnosTV.getSelectionModel().getSelectedItem() != null) {
                RefAlumnoSelect = AlumnosTV.getSelectionModel().getSelectedItem();
                RefCursoSelect = AgregarFalta.MODALGetRefCurso(RefCursoSelect, RefAlumnoSelect, c1);
                cargaAlumnos();
            }
        });
    }
    
    public void cargarPeriodoYCursos() {
        
        // Desplegar periodo
        PeriodoTF.setText(RefPeriodo.toString());

        // Referencia a lista de cursos
        RefCursos = RefPeriodo.getCursos();

        // Columna de nombre de cursos
        CursoTableList = FXCollections.observableArrayList();
        for(Curso cur:RefCursos) {
            CursoTableList.add(cur);
        }

        nameColumn.setText("Cursos");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Nombre"));

        CursoTV.setItems(CursoTableList);
    }
    
    public void cargaAlumnos() {
        
        // Obtengo la referencia a alumnos
        RefAlumnos = RefCursoSelect.getAlumnos();
        
        // Columna de nombre de cursos
        AlumnoTableList = FXCollections.observableArrayList();
        for(Alumno alum:RefAlumnos) {
            alum.getNumFaltas();
            alum.getNumFaltasJustificadas();
            alum.getNumRetardos();
            AlumnoTableList.add(alum);
        }
        
        nomAlumno.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        
        Fcol.setCellValueFactory(new PropertyValueFactory<>("NumFaltas"));

        FJcol.setCellValueFactory(new PropertyValueFactory<>("NumFaltasJustificadas"));
        
        Rcol.setCellValueFactory(new PropertyValueFactory<>("NumRetardos"));
        
        AlumnosTV.setItems(AlumnoTableList);
    }
}


