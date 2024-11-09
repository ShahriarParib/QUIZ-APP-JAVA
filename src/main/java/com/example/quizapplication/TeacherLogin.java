package com.example.quizapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TeacherLogin {
    public void handleLogin(ActionEvent actionEvent) {
        try {
            // Load the Teacher Login page (Teacher login FXML)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/quizapplication/teacher_home.fxml"));
            Parent teacherLoginPage = loader.load();

            // Get the current stage (window) and set the new scene (Teacher login page)
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(teacherLoginPage));
            stage.setTitle("Teacher Login");

            // Show the new scene (Teacher login page)
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();  // Handle the exception if the FXML file is not found
        }
    }
}

