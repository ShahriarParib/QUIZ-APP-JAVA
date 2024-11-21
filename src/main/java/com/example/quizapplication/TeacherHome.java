package com.example.quizapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TeacherHome {
    public void handleAddStudent(ActionEvent actionEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/quizapplication/add_student.fxml"));
            Parent teacherLoginPage = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(teacherLoginPage));
            stage.setTitle("Add Student");


            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleAddQuiz(ActionEvent actionEvent) {
        try {
            // Load the Teacher Login page (Teacher login FXML)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/quizapplication/add_quiz.fxml"));
            Parent teacherLoginPage = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(teacherLoginPage));
            stage.setTitle("Teacher Login");

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logoutteacher(ActionEvent actionEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/quizapplication/Home_page.fxml"));
            Parent teacherLoginPage = loader.load();


            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(teacherLoginPage));
            stage.setTitle("Teacher Login");


            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

