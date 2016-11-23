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

public class ElegirPeriodo {
    
    public static Periodo Referencia;
        
    public static Periodo MODALGetRefPeriodo(Periodo actual, ControladorBackend c1) {
        
        // Crear ventana
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Elegir periodo académico");
        window.setMinWidth(250);
        
        window.setOnCloseRequest(e -> {
            Referencia = actual;
        });
        
        // Crear Mensaje
        Label label = new Label();
        label.setText("Elegir periodo académico (Mes inicial - Mes final - Año)");
        
        // Crear campo para elegir mes inicial
        ComboBox Inicial = new ComboBox<>();
        Inicial.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        Inicial.setPromptText("Mes incial");
        
        // Crear campo para elegir mes final
        ComboBox Final = new ComboBox<>();
        Final.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        Final.setPromptText("Mes final");
        
        // Crear campo para elegir el año
        TextField Anio = new TextField();
        Anio.setPromptText("Año");
        
        // Crear espacio para poner nuevo periodo
        HBox HBoxPeriodo = new HBox(10);
        HBoxPeriodo.setAlignment(Pos.CENTER);
        HBoxPeriodo.setSpacing(5);
        HBoxPeriodo.getChildren().addAll(Inicial, Final, Anio);
        
        // Crear boton de submit
        Button button = new Button("Definir periodo academico");
        button.setOnAction(e -> {
            Referencia = c1.encontrarPeriodo(
                        Integer.parseInt(Inicial.getSelectionModel().getSelectedItem().toString()),
                        Integer.parseInt(Final.getSelectionModel().getSelectedItem().toString()),
                        Integer.parseInt(Anio.getText()));
            if(Referencia == null) {
                Referencia = c1.createPeriodo(
                        Integer.parseInt(Inicial.getSelectionModel().getSelectedItem().toString()),
                        Integer.parseInt(Final.getSelectionModel().getSelectedItem().toString()),
                        Integer.parseInt(Anio.getText()));
                c1.guardarDatos();
            }
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