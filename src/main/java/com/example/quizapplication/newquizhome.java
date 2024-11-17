package com.example.quizapplication;

import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class newquizhome {

    // Define FXML elements as specified in your FXML file
    @FXML
    private Label addNewQuizLabel;

    @FXML
    private TextField quizTitleField;

    @FXML
    private TextField remarksField;

    @FXML
    private Button submitButton;

    // This method will be invoked when the button is pressed
    @FXML
    public void addingquizhome() {
        // Retrieve data from the fields
        String quizTitle = quizTitleField.getText();
        String remarks = remarksField.getText();




        if (quizTitle.trim().isEmpty() || remarks.trim().isEmpty()) {
            Notifications.create()
                    .title("Input Required")
                    .text("Please fill out all fields.")
                    .position(Pos.CENTER)
                    .hideAfter(Duration.seconds(1))
                    .showWarning();
        } else {
            System.out.println("Quiz successfully added.");

        }
    }
}
