package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Quiz {
    // Properties

    private Integer quizId;
    private String title;

    // Constructers
    public Quiz(String title) {
        this.title = title;
    }

    public static class MetaData {

        public static final String TABLE_NAME = "quizs";
        public static final String QUIZ_ID = "quiz_id";
        static final String TITLe = "title";

    }

    public Quiz() {
    }

    // Getter Setters
    public Integer getQuizId() {
        return quizId;
    }

    public String getTitle() {
        return title;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Quiz{" + "quizId=" + quizId + ", title=" + title + '}';
    }

    // Other Methods
    public static void createTable() {
        try {
            String raw = "CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s VARCHAR(50) );";
            String query = String.format(raw, MetaData.TABLE_NAME, MetaData.QUIZ_ID, MetaData.TITLe);
            System.err.println(query);
            String connectionUrl = "jdbc:sqlite:quiz.db";
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(connectionUrl);
            PreparedStatement ps = connection.prepareStatement(query);
            boolean b = ps.execute();
            System.out.println("models.Quiz.createTable()");
            System.out.println(b);
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int save() {
        String raw = "Insert into %s (%s) values (?)";
        String query = String.format(raw, MetaData.TABLE_NAME, MetaData.TITLe);
        String connectionUrl = "jdbc:sqlite:quiz.db";

        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DriverManager.getConnection(connectionUrl)) {

                PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, this.title);
                int i = ps.executeUpdate();
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }

        return -1;
    }

    public boolean save(ArrayList<Questions> questions){
        boolean flag = true;
        this.quizId = this.save();

        for(Questions q : questions){
            flag = flag && q.save();
            System.out.println(flag);
        }
        return flag;
    }
    public static Map<Quiz, Integer> getAllWithQuestionCount() {
        Map<Quiz, Integer> quizes = new HashMap<>();
        Quiz key = null;

        String query = String.
                format("SELECT %s.%s , %s  ," +
                                " COUNT(*) as question_count  " +

                                "FROM %s join %s on %s.%s = %s.%s GROUP BY %s.%s",
                        MetaData.TABLE_NAME,
                        MetaData.QUIZ_ID,
                        MetaData.TITLe,
                        MetaData.TABLE_NAME,
                        Questions.myData.Table_name,
                        Questions.myData.Table_name,
                        Questions.myData.QUIZID,
                        MetaData.TABLE_NAME,
                        MetaData.QUIZ_ID,
                        MetaData.TABLE_NAME,
                        MetaData.QUIZ_ID
                );
        String connectionUrl ="jdbc:sqlite:quiz.db";
        System.out.println(query);
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DriverManager.getConnection(connectionUrl)) {

                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet result = ps.executeQuery();

                while (result.next()) {
                    Quiz temp = new Quiz();
                    temp.setQuizId(result.getInt(1));
                    temp.setTitle(result.getString(2));
                    int count = result.getInt(3);
                    quizes.put(temp, count);

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return quizes;
    }
}




