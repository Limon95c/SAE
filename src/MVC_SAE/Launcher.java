/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC_SAE;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ControladorSAE.fxml"));
        primaryStage.setWidth(1000);
        primaryStage.setHeight(565);
        primaryStage.setTitle("Sistema de Asistencia Estudiantil");
        primaryStage.setScene(new Scene(root, 1000, 565));
        primaryStage.show();
    }
}
