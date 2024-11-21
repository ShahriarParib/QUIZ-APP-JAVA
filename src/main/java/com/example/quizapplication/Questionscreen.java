package com.example.quizapplication;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import model.Quiz;
import java.net.URL;
import java.util.ResourceBundle;
public class Questionscreen implements Initializable {
    public Label title;
    public Label time;
    public Label question;
    public RadioButton option1;
    public RadioButton option2;
    public RadioButton option3;
    public RadioButton option4;
    public Button next;
    public Button submit;
    public ToggleGroup Options;
    private Quiz quiz;

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        this.title.setText(this.quiz.getTitle());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void nextquestion(ActionEvent actionEvent) {
    }

    public void submit(ActionEvent actionEvent) {
    }
}
