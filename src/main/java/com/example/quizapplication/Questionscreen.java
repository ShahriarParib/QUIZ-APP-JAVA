package com.example.quizapplication;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import model.Questions;
import model.Quiz;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
public class Questionscreen implements Initializable {
    public FlowPane progressplane;

    private class Questionchecker
    {
        Property<String> question = new SimpleStringProperty();
        Property<String> option1 = new SimpleStringProperty();
        Property<String> option2 = new SimpleStringProperty();
        Property<String> option3 = new SimpleStringProperty();
        Property<String> option4 = new SimpleStringProperty();
        Property<String> answer = new SimpleStringProperty();

        public void setQuestion(Questions question) {
            this.question.setValue(question.getQuestion());
            this.option1.setValue(question.getOption1());
            this.option2.setValue(question.getOption2());
            this.option3.setValue(question.getOption3());
            this.option4.setValue(question.getOption4());
            this.answer.setValue(question.getAnswer());
        }
    }

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
    private Questions currentquestion;

    private List<Questions> questionsList;
    int currentindex =0;
     private  Questionchecker questionchecker;
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        this.title.setText(this.quiz.getTitle());
        this.getdata();
    }
    private void getdata()
    {
        if(quiz!=null)
        {
            this.questionsList=quiz.getQuestions();
            Collections.shuffle(this.questionsList);
            setnextquestion();
            checkprogress();
        }
    }

private void checkprogress()
{
    for(int i=0;i<this.questionsList.size();i++)
    {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("progresslayout.fxml"));
        try
        {
            Node node =fxmlLoader.load();
            Progresslayout progresslayout=fxmlLoader.getController();
            progresslayout.setNumber(i+1);
            progressplane.getChildren().add(node);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.shownextquestion();
        this.hidesubmitquiz();
        this.questionchecker=new Questionchecker();
        bindfield();

    }
    private void bindfield()
    {
        this.question.textProperty().bind(this.questionchecker.question);
        this.option4.textProperty().bind(this.questionchecker.option4);
        this.option3.textProperty().bind(this.questionchecker.option3);
        this.option2.textProperty().bind(this.questionchecker.option2);
        this.option1.textProperty().bind(this.questionchecker.option1);
    }

    private void setnextquestion() {
        if (!(currentindex >= questionsList.size())) {
            this.currentquestion = this.questionsList.get(currentindex);
            List<String>options=new ArrayList<>();

            options.add(this.currentquestion.getOption1());
            options.add(this.currentquestion.getOption2());
            options.add(this.currentquestion.getOption3());
            options.add(this.currentquestion.getOption4());
           Collections.shuffle(options);

            this.currentquestion.setOption1(options.get(0));
            this.currentquestion.setOption2(options.get(1));
            this.currentquestion.setOption3(options.get(2));
            this.currentquestion.setOption4(options.get(3));
            this.questionchecker.setQuestion(this.currentquestion);
            currentindex++;
        } else {
            hidenextquestion();
            showsubmitquiz();
        }
    }


    public void nextquestion(ActionEvent actionEvent) {
        this.setnextquestion();
    }

    private void hidenextquestion()
    {
        this.next.setVisible(false);

    }
    private void shownextquestion()
    {
        this.next.setVisible(true);

    }
    private void hidesubmitquiz()
    {
        this.submit.setVisible(false);

    }
    private void showsubmitquiz()
    {
        this.submit.setVisible(true);

    }


    public void submit(ActionEvent actionEvent) {
    }
}
