package org.myself;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        //un throws IOException est ajouté car l'opération de chargement du FXML peut échouer:
        // 1. On crée le Parent qui contient le FXMLLoader "chargeur" de fxml avec l'adresse du fichier:
        Parent root = FXMLLoader.load(getClass().getResource("/org/myself/gui/menu.fxml"));
        // 2. On l'injecte dans la scene:
        Scene scene = new Scene(root);
        // 3. On assigne la scene au stage:
        primaryStage.setScene(scene);
        primaryStage.setTitle("Application desktop BenGreen");
        // 4. On affiche le stage:
        primaryStage.show();
    }
}
