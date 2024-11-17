package model;

import java.sql.*;

public class Questions {
    private Quiz quiz;
    private Integer qeustionId;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;



    public static class myData
{
    public static final String Table_name="questions";
    public static final String QUESTION="question";
    public static final String OPTION1="option1";
    public static final String OPTION2="option2";
    public static final String OPTION3 ="option3";
    public static final String OPTION4="option4";
    public static final String ANSWER="answer";
    public static final String QUIZID ="quizId";

}

    public Questions() {
    }

    public Questions(Quiz quiz, String question, String option1, String option2, String option3, String option4,String answer) {
        this.quiz = quiz;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer=answer;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer= answer;
    }

public static void createTable()
{

    String raw="CREATE TABLE %s (questionId INTEGER PRIMARY KEY AUTOINCREMENT, question VARCHAR(1000), %s INTEGER, %s VARCHAR(500), %s VARCHAR(500), %s VARCHAR(500), %s VARCHAR(500), %s VARCHAR(500),FOREIGN KEY (%s) REFERENCES %s(%s))";
    String query=String.format(raw,myData.Table_name,myData.OPTION1,myData.OPTION2,myData.OPTION3
    ,myData.OPTION4,myData.ANSWER,myData.QUIZID,myData.QUIZID,myData.Table_name,myData.QUIZID);
    try {
        System.out.println(query);
        String connectionurl = "jdbc:sqlite:quiz.db";
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection(connectionurl);
        PreparedStatement ps= connection.prepareStatement(query);
        boolean b=ps.execute();
        System.out.println("model.Questions.createTable()");
        System.out.println(b);
        connection.close();
    }
    catch (Exception ex)
    {
        ex.printStackTrace();
    }
}
public boolean save()
{
    boolean flag=false;
    try {
        String raw = "INSERT INTO %s(%s,%s,%s,%s,%s,%s,%s) VALUES(?,?,?,?,?,?,?)";
        String query = String.format(raw, myData.Table_name, myData.QUESTION,myData.OPTION1,myData.OPTION2, myData.OPTION3,myData.OPTION4,myData.ANSWER,myData.QUIZID);
        System.err.println("Generated Query: " + query);
        String connectionurl = "jdbc:sqlite:quiz.db";
        System.out.println("Connecting to database...");
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection(connectionurl);
        System.out.println("Database connected successfully.");
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, this.question);
        ps.setString(2, this.option1);
        ps.setString(3, this.option2);
        ps.setString(4, this.option3);
        ps.setString(5, this.option4);
        ps.setString(6, this.answer);
        ps.setInt(7, this.quiz.getQuizId());

        int i = ps.executeUpdate();
        System.out.println("Executing query..."+i);

        connection.close();
        flag=true;
    }

    catch (Exception ex) {
        ex.printStackTrace();
        return false;

    }
    return flag;

}



}
