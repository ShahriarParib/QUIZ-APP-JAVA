package com.example.quizapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Quiz;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Quizlayout implements Initializable {
    public Label quizcount;
    public Label quiztitle;
    public Button start;
    private Quiz quiz;

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        this.quiztitle.setText(this.quiz.getTitle());
    }

    private newscreenlistener screenlistener;

    public void setScreenlistener(newscreenlistener screenlistener) {
        this.screenlistener = screenlistener;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setQuizcount(String value) {

        this.quizcount.setText(value);
    }




        public void startquiz(ActionEvent actionEvent) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("questionscreen.fxml"));
            try {
                Node node = fxmlLoader.load();
               Questionscreen questionscreen= fxmlLoader.getController();
               questionscreen.setQuiz(this.quiz);
               this. screenlistener.changescreen(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

