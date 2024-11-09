module com.example.quizapplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.quizapplication to javafx.fxml;
    exports com.example.quizapplication;
}