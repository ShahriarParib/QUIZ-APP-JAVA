package com.example.quizapplication;

import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Student;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class Addstudent implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private TextField firstName;

    @FXML
    private TextField secondName;

    @FXML
    private TextField mobileNumber;

    @FXML
    private RadioButton male;

    @FXML
    private RadioButton female;

    @FXML
    private Button saveButton;

    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TableColumn<Student, String> studentIdColumn;

    @FXML
    private TableColumn<Student, String> firstNameColumn;

    @FXML
    private TableColumn<Student, String> lastNameColumn;

    @FXML
    private TableColumn<Student, String> mobileNumberColumn;

    @FXML
    private TableColumn<Student, Character> genderColumn;
    @FXML
    private TableColumn<Student, String> emailColumn;
    @FXML
    private TableColumn<Student, String> passwordcolumn;

    private ToggleGroup toggleGroup;

    private void radioButtonsetup() {
        System.out.println("Setting up radio buttons...");
        this.male.setSelected(true);
        this.male.setToggleGroup(toggleGroup);
        this.female.setToggleGroup(toggleGroup);
    }

    private void initial() {
        System.out.println("Initializing toggle group...");
        toggleGroup = new ToggleGroup();
    }
    private void showstudents() {
        List<Student> students = Student.getinfo();
        System.out.println("Fetched students: " + students); // Debugging line
        if (students == null || students.isEmpty()) {
            System.out.println("No students found or list is null.");
        }
        studentTable.getItems().clear();
        this.studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        this.lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        this.mobileNumberColumn.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        this.emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.passwordcolumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        this.genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        studentTable.getItems().addAll(students); // Debugging focus here
        System.out.println("Table updated with students: " + studentTable.getItems());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initializing Addstudent controller...");
        initial();
        radioButtonsetup();
        showstudents();
    }


    private void resetform() {
        System.out.println("Resetting form fields...");
        this.firstName.clear();
        this.secondName.clear();
        this.email.clear();
        this.password.clear();
        this.mobileNumber.clear();
    }

    private String vadilate(Student student) {
        System.out.println("Validating student: " + student);
        String message = null;
        Pattern p = Pattern.compile("^[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+(?:[a-zA-Z]{2}|aero|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel)$");
        if (student.getFirstName().length() < 4) {
            message = "First name must be more than 4 characters long";
        } else if (student.getLastName().length() < 2) {
            message = "Last name must be more than 4 characters long";
        } else if (!p.matcher(student.getEmail()).matches()) {
            message = "Please enter a valid email.";
        } else if (student.getPassword().length() < 4) {
            message = "Password must be more than 6 characters";
        } else if (student.getMobile().length() < 11) {
            message = "Enter a valid mobile number";
        }
        System.out.println("Validation message: " + message);
        return message;
    }

    @FXML
    public void saveStudent(ActionEvent event) {
        System.out.println("Saving new student...");
        String firstName = this.firstName.getText().trim();
        String lastName = this.secondName.getText().trim();
        String mobile = this.mobileNumber.getText().trim();
        String email = this.email.getText().trim();
        String password = this.password.getText().trim();
        Character gen = 'M';
        RadioButton gender = (RadioButton) toggleGroup.getSelectedToggle();
        if (gender != null) {
            if (gender == female) {
                gen = 'F';
            }
        }
        Student s = new Student(firstName, lastName, mobile, gen, email, password);
        String message = vadilate(s);
        if (message != null) {
            Notifications.create()
                    .title("Fill the Form Correctly")
                    .text(message)
                    .position(Pos.CENTER)
                    .hideAfter(Duration.seconds(2))
                    .showWarning();
            return;
        }
        if (s.isexist()) {
            Notifications.create()
                    .title("Failed")
                    .text("Already registered")
                    .position(Pos.CENTER)
                    .hideAfter(Duration.seconds(2))
                    .showError();
            return;
        }
        System.out.println("Attempting to save student...");
        s = s.save();
        if (s != null) {
            System.out.println("Student saved successfully: " + s);
            Notifications.create()
                    .title("Success")
                    .text("Student has been registered")
                    .position(Pos.CENTER)
                    .hideAfter(Duration.seconds(2))
                    .showInformation();
            resetform();
            showstudents(); // Update the table with new student
        } else {
            System.out.println("Student save failed.");
            Notifications.create()
                    .title("Failed")
                    .text("Student registration failed.")
                    .position(Pos.CENTER)
                    .hideAfter(Duration.seconds(2))
                    .showError();
        }
    }

    public void backfromaddstudent(ActionEvent actionEvent) {
        System.out.println("Navigating back to Teacher Home...");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/quizapplication/teacher_home.fxml"));
            Parent teacherLoginPage = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(teacherLoginPage));
            stage.setTitle("Teacher Login");
            stage.show();
        } catch (IOException e) {
            System.out.println("Error navigating back to Teacher Home.");
            e.printStackTrace();
        }
    }
}
