module com.example.quizapplication {
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires java.desktop;
    requires java.sql;



    opens com.example.quizapplication to javafx.fxml;
    exports com.example.quizapplication;
}