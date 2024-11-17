package com.example.quizapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Questions;
import model.Quiz;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.util.ArrayList;

public class AddQuiz {

    @FXML
    private TextField questionNameField;
    @FXML
    private TextField quizTitleField;

    @FXML
    private TextField option1Field;

    @FXML
    private TextField option2Field;

    @FXML
    private TextField option3Field;

    @FXML
    private TextField option4Field;

    @FXML
    private RadioButton option1RadioButton;

    @FXML
    private RadioButton option2RadioButton;

    @FXML
    private RadioButton option3RadioButton;

    @FXML
    private RadioButton option4RadioButton;

    @FXML
    private ToggleGroup correctAnswerToggleGroup;
    private Quiz quiz = null;
    private ArrayList<Questions> questions = new ArrayList<>();


    @FXML
    public void initialize() {
        // Assign the same ToggleGroup to all RadioButtons
        correctAnswerToggleGroup = new ToggleGroup();

        option1RadioButton.setToggleGroup(correctAnswerToggleGroup);
        option2RadioButton.setToggleGroup(correctAnswerToggleGroup);
        option3RadioButton.setToggleGroup(correctAnswerToggleGroup);
        option4RadioButton.setToggleGroup(correctAnswerToggleGroup);
    }

    public void backnewquiz(ActionEvent actionEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/quizapplication/teacher_home.fxml"));
            Parent teacherLoginPage = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(teacherLoginPage));
            stage.setTitle("Teacher Login");

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void handleOkButton(ActionEvent actionEvent) {
        String title = quizTitleField.getText().trim();
        if (title.isEmpty()) {
            Notifications.create().title("Input Required").text("Please Enter The Quiz Title.").position(Pos.CENTER).hideAfter(Duration.seconds(1)).showWarning();
            return;
        }
            else {
            quizTitleField.setEditable(false);
            System.err.println("Save Title.....");
            this.quiz = new Quiz(title);
        }

        }




    private boolean validatefield() {

        if (quiz == null) {
            Notifications.create().title("Input Required").text("Please Enter the Quiz Title").position(Pos.CENTER).hideAfter(Duration.seconds(1)).showWarning();
            return false;
        }
        String que = this.questionNameField.getText();
        String op1 = this.option1Field.getText();
        String op2 = this.option2Field.getText();
        String op3 = this.option3Field.getText();
        String op4 = this.option4Field.getText();

        Toggle selectedradio = correctAnswerToggleGroup.getSelectedToggle();
        if (que.trim().isEmpty() || op1.trim().isEmpty() || op2.trim().isEmpty() || op3.trim().isEmpty() || op4.trim().isEmpty()) {
            Notifications.create().title("Input Required").text("Please fill out all fields.").position(Pos.CENTER).hideAfter(Duration.seconds(1)).showWarning();
            return false;

        } else {
            if (selectedradio == null) {
                Notifications.create().title("Input Required").text("Please Select An Answer").position(Pos.CENTER).hideAfter(Duration.seconds(1)).showWarning();
                return false;


            } else {
                return true;
            }
        }
    }
    public void nextquestion(ActionEvent actionEvent) {
        addquestion();

    }

    public boolean addquestion() {
        boolean valid = validatefield();
        Questions question = new Questions();
        if (valid) {
            question.setOption1(option1Field.getText().trim());
            question.setOption2(option2Field.getText().trim());
            question.setOption3(option3Field.getText().trim());
            question.setOption4(option4Field.getText().trim());
            Toggle selected = correctAnswerToggleGroup.getSelectedToggle();
            String ans = null;
            if (selected == option1RadioButton) {
                ans = option1Field.getText().trim();
            } else if (selected == option2RadioButton) {
                ans = option2Field.getText().trim();
            } else if (selected == option3RadioButton) {
                ans = option3Field.getText().trim();
            } else if (selected == option4RadioButton) {
                ans = option4Field.getText().trim();
            }
            question.setAnswer(ans);
            question.setQuestion(this.questionNameField.getText().trim());
            this.questionNameField.clear();
            option1Field.clear();
            option2Field.clear();
            option3Field.clear();
            option4Field.clear();
            questions.add(question);
            question.setQuiz(quiz);
            System.out.println("save ques");
            System.out.println(questions);
            System.out.println(quiz);

        }
        return valid;

    }



    public void submitquiz(ActionEvent actionEvent) {

        boolean flag=addquestion();
        if(flag)
        {

        boolean success = quiz.save(questions);
        if (success) {
            Notifications.create().title("Quiz Submitted").text("Quiz \"" + quiz.getTitle() + "\" saved successfully!").position(Pos.CENTER).hideAfter(Duration.seconds(2)).showInformation();
            // Reset the state for new quiz
            quiz = null;
            questions.clear();
            quizTitleField.clear();
            questionNameField.clear();
            option1Field.clear();
            option2Field.clear();
            option3Field.clear();
            option4Field.clear();

            quizTitleField.setEditable(true);
        } else {
            Notifications.create().title("Error").text("Failed to save quiz.").position(Pos.CENTER).hideAfter(Duration.seconds(2)).showError();
        }
    }}

}


