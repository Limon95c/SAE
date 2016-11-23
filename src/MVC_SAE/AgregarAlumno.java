/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC_SAE;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import backend.*;
import java.util.ArrayList;

public class AgregarAlumno {
    
    public static Curso Referencia;
        
    public static Curso MODALGetRefCurso(Curso actual, ControladorBackend c1) {
        
        // Crear ventana
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Agregar alumno...");
        window.setMinWidth(250);
        
        window.setOnCloseRequest(e -> {
            Referencia = actual;
        });
        
        // Crear Mensaje
        Label label = new Label();
        label.setText("Llenar datos del alumno (Nombre - Correo)");
        
        // Crear campo para elegir el nombre
        TextField Nom = new TextField();
        Nom.setPromptText("Nombre");
        
        // Crear campo para elegir el correo
        TextField Corr = new TextField();
        Corr.setPromptText("Correo");
        
        // Crear espacio para poner nuevo periodo
        HBox HBoxPeriodo = new HBox(10);
        HBoxPeriodo.setAlignment(Pos.CENTER);
        HBoxPeriodo.setSpacing(5);
        HBoxPeriodo.getChildren().addAll(Nom, Corr);
        
        // Crear boton de submit
        ArrayList<Periodo> temp = c1.getPeriodos();
        Button button = new Button("Agregar alumno");
        button.setOnAction(e -> {
            actual.agregaAlumno(Nom.getText(), Corr.getText());
            Referencia = actual;
            c1.guardarDatos();
            window.close();
        });
        
        // Agregar a la escena
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, HBoxPeriodo, button);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        //Make sure to return answer
        return Referencia;
    }
}