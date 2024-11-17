package com.example.quizapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HomePageController {

    @FXML
    public void teacherstart(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/quizapplication/teacher_login.fxml"));
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
