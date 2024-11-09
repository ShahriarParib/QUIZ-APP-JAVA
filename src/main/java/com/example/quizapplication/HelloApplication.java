package com.example.quizapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Home_page.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root, 1024, 768);

        // Add the CSS file to the scene
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        // Set the scene and show the stage
//        stage.setTitle("Hello!");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
