package com.example.quizapplication;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Student_home implements Initializable {

    @FXML
    private Button backbutton;
    @FXML
    private StackPane stackpane;


    private void addscreentostackplane(Node node)
    {
        this.stackpane.getChildren().add(node);


    }
    private void addquizlist()  {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("quiz_list.fxml")); // Correct file
        try {
            Node node = fxmlLoader.load();
            QuizList quizList = fxmlLoader.getController();
            quizList.setScreenlistener(new newscreenlistener() {
                @Override
                public void changescreen(Node node) {
                    addscreentostackplane(node);
                }
                @Override
                public void handle(Event event) {
                    // Optional
                }
            });
            stackpane.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addquizlist();
    }

    public void handleback(ActionEvent actionEvent) {
        ObservableList<Node>nodes=this.stackpane.getChildren();
        if(nodes.size()==1)
        {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/quizapplication/studentdashboard.fxml"));
                Parent teacherLoginPage = loader.load();
                Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(new Scene(teacherLoginPage));
                stage.setTitle("Teacher Login");
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.stackpane.getChildren().remove (nodes.size()-1);
    }
}
