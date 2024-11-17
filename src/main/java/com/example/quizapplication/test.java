package com.example.quizapplication;
import java.sql.Connection;
import java.util.ArrayList;
import model.Questions;
import model.Quiz;

public class test {
    public static void main(String[] args) {
        Quiz quiz = new Quiz("MySql Quiz");
        Questions question = new Questions(quiz, "1 + 3 = ? ", "4", "5", "10", "45", "4");

        ArrayList<Questions> arr = new ArrayList<>();
        arr.add(question);
        arr.add(question);
        arr.add(question);
        arr.add(question);
        arr.add(question);
        arr.add(question);
        arr.add(question);
        arr.add(question);
        arr.add(question);
        question.setQuiz(quiz);
        quiz.save(arr);
    }
}
