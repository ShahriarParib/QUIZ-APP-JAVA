package com.example.quizapplication;
import java.sql.Connection;
import java.util.ArrayList;
import model.Questions;
import model.Quiz;
import model.Student;

public class test {
    public static void main(String[] args) {


        Quiz quiz =new Quiz();
        quiz.setQuizId(3);
        System.out.println(quiz.getQuestions());
    }
    }
