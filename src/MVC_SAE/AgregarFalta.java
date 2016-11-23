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
import java.util.Date;
import backend.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AgregarFalta {
    
    public static Curso Referencia;
        
    public static Curso MODALGetRefCurso(Curso curso, Alumno actual, ControladorBackend c1) {
        
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
        label.setText("Ingrese los datos de la falta\n(Fecha dd/mm/aaaa - Tipo de falta)");
        
        // Crear Mensaje
        Label D = new Label();
        D.setText("Dia:");
        // Crear Mensaje
        Label M = new Label();
        M.setText("Mes:");
        // Crear Mensaje
        Label A = new Label();
        A.setText("Año:");
        // Crear Mensaje
        Label T = new Label();
        T.setText("Tipo de falta:");
        
        // Obtener fecha de hoy
        Date d = new Date();
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        
        // Crear campo para escribir el dia
        TextField Dia = new TextField();
        Dia.setText(Integer.toString(c.get(Calendar.DAY_OF_MONTH)));
        
        // Crear campo para escribir el mes
        TextField Mes = new TextField();
        Mes.setText(Integer.toString(c.get(Calendar.MONTH)));
        
        // Crear campo para escribir el año
        TextField Anio = new TextField();
        Anio.setText(Integer.toString(c.get(Calendar.YEAR)));
        
        // Crear campo para escribir el tipo
        ComboBox tipo = new ComboBox<>();
        tipo.getItems().addAll("Falta", "Falta justificada", "Retardo");
        tipo.getSelectionModel().select(0);
        
        // Crear espacio para poner nuevo periodo
        HBox HBoxFecha = new HBox(10);
        HBoxFecha.setAlignment(Pos.CENTER);
        HBoxFecha.setSpacing(2);
        HBoxFecha.getChildren().addAll(D, Dia, M, Mes, A, Anio);
        
        HBox HBoxTipo = new HBox(10);
        HBoxTipo.setAlignment(Pos.CENTER);
        HBoxTipo.setSpacing(2);
        HBoxTipo.getChildren().addAll(T, tipo);
        
        // Crear boton de submit
        Button button = new Button("Agregar falta");
        button.setOnAction(e -> {
            Fecha f = new Fecha(Integer.parseInt(Dia.getText()),
                    Integer.parseInt(Mes.getText()),
                    Integer.parseInt(Anio.getText()));
            actual.agregaFalta(f, tipo.getSelectionModel().getSelectedIndex() + 1);
            int Falt = actual.getNumFaltas();
            actual.getNumFaltasJustificadas();
            actual.getNumRetardos();
            c1.guardarDatos();
            if(Falt == curso.getFaltasAlerta())
                EmailSender.SendEmail(actual.getCorreo(), "Advertencia de faltas", "Mensaje");
            Referencia = c1.encontrarCurso(curso.getID());
            window.close();
        });
        
        // Agregar a la escena
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, HBoxFecha, HBoxTipo, button);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        //Make sure to return answer
        return Referencia;
    }
}