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

public class ElegirProfesor {
    
    public static Profesor Referencia;
        
    public static Profesor MODALGetRefProfesor(ControladorBackend c1) {

        // Crear ventana
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Datos del profesor");
        window.setMinWidth(250);
        
        window.setOnCloseRequest(e -> {
            Referencia = c1.getProfesores().get(0);
        });
        
        // Crear Mensaje
        Label label = new Label();
        label.setText("Editar datos del profesor (Nombre completo - Puesto - Correo)");
        
        // Crear campo para escribir su nombre
        TextField Nom = new TextField();
        Nom.setPromptText("Nombre");
        
        // Crear campo para elegir el año
        TextField Puest = new TextField();
        Puest.setPromptText("Puesto");
        
        // Crear campo para elegir el año
        TextField corr = new TextField();
        corr.setPromptText("Correo");
        
        // Crear espacio para poner nuevo periodo
        HBox HBoxPeriodo = new HBox(10);
        HBoxPeriodo.setAlignment(Pos.CENTER);
        HBoxPeriodo.setSpacing(5);
        HBoxPeriodo.getChildren().addAll(Nom, Puest, corr);
        
        // Crear boton de submit
        Button button = new Button("Terminar edición");
        button.setOnAction(e -> {
            Referencia = c1.createProfesor(Nom.getText(),Puest.getText(), corr.getText());
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