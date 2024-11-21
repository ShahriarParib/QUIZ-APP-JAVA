package com.example.quizapplication;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class Progresslayout implements Initializable {
    public Circle circle;
    public Label number;

    public void setNumber(Integer number) {
        this.number.setText(number.toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
