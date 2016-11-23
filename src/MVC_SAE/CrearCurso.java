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

public class CrearCurso {
    
    public static Curso Referencia;
        
    public static Curso MODALGetRefCurso(Periodo Actual, ControladorBackend c1) {
        
        // Crear ventana
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Crear curso nuevo...");
        window.setMinWidth(250);
        
        window.setOnCloseRequest(e -> {
            Referencia = null;
        });
        
        // Crear Mensaje
        Label label = new Label();
        label.setText("Ingrese los datos del curso\n(Nombre - Numero de faltas para alerta por correo - Límite de faltas)");
        
        // Crear campo para escribir su nombre
        TextField Nom = new TextField();
        Nom.setPromptText("Nombre");
        
        // Crear campo para elegir las faltas para envío de correo
        TextField Alerta = new TextField();
        Alerta.setPromptText("Faltas para correo electrónico");
        
        // Crear campo para elegir el limite de faltas
        TextField Limite = new TextField();
        Limite.setPromptText("Límite de faltas");
        
        // Crear espacio para poner nuevo periodo
        HBox HBoxPeriodo = new HBox(10);
        HBoxPeriodo.setAlignment(Pos.CENTER);
        HBoxPeriodo.setSpacing(5);
        HBoxPeriodo.getChildren().addAll(Nom, Alerta, Limite);
        
        // Crear boton de submit
        Button button = new Button("Crear curso");
        button.setOnAction(e -> {
            Profesor temp = c1.getProfesores().get(0);
            Referencia = Actual.agregaCurso(Nom.getText(),
                    Integer.parseInt(Alerta.getText()),
                    Integer.parseInt(Limite.getText()),
                    temp.getID());
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