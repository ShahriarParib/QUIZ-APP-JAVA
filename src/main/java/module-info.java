module com.example.quizapplication {
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires java.desktop;
    requires java.sql;
    requires com.jfoenix;


    opens com.example.quizapplication to javafx.fxml;
    opens model to javafx.base;
    exports com.example.quizapplication;
}