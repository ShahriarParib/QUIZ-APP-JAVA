package model;

import java.sql.*;
import java.util.ArrayList;

public class Student
{


    private Integer id;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private String password;
    private Character gender;
    public Student( String firstName, String lastName, String mobile,Character gender, String email, String password) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.gender = gender;
        this.email = email;
        this.password = password;

    }

    public Student() {

    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                '}';
    }

    public Student(Integer id, String firstName, String lastName, String mobile,Character gender,String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public  Character getGender() {
        return gender;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }
    private static class mydata
    {
        public static final String TABLE_NAME="students";
        public static final String ID="id";
        public static final String FIRST_NAME ="firstName";
        public static final String LAST_NAME="lastName";
        public static final String MOBILE="mobile";
        public static final String EMAIL="email";
        public static final String PASSWORD="password";
        public static final String GENDER="gender";

    }

    public static void createTable()
    {
        String raw= "CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s VARCHAR(20), %s VARCHAR(20), %s VARCHAR(20), %s VARCHAR(30), %s VARCHAR(30), %s CHAR)";
        String query =String.format(raw,
                mydata.TABLE_NAME,
                mydata.ID,
                mydata.FIRST_NAME,
                mydata.LAST_NAME,
                mydata.MOBILE,
                mydata.EMAIL,
                mydata.PASSWORD,
                mydata.GENDER);
        System.out.println(query);

       try {
            System.out.println(query);
            String connectionurl = "jdbc:sqlite:quiz.db";
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(connectionurl);
            PreparedStatement ps= connection.prepareStatement(query);
            boolean b=ps.execute();
            System.out.println("model.Student.createTable()");
            System.out.println(b);
            connection.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public Student save()
    {

        try{
           String raw = "INSERT INTO students(%s,%s,%s,%s,%s,%s) VALUES(?,?,?,?,?,?)";
           String query =String.format(raw,
                   mydata.FIRST_NAME,
                   mydata.LAST_NAME,
                   mydata.MOBILE,
                   mydata.EMAIL,
                   mydata.PASSWORD,
                   mydata.GENDER);
           System.err.println("Generated Query: " + query);
            System.err.println("Generated Query: " + query);
            String connectionurl = "jdbc:sqlite:quiz.db";
            System.out.println("Connecting to database...");
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(connectionurl);
            System.out.println("Database connected successfully.");
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.firstName);
            ps.setString(2, this.lastName);
            ps.setString(3, this.mobile);
            ps.setString(4, this.email);
            ps.setString(5, this.password);
            ps.setString(6, String.valueOf(this.gender));


            int i = ps.executeUpdate();
            ResultSet keys=ps.getGeneratedKeys();
            if(keys.next())
            {
               this.id= keys.getInt(1);
            }
            System.out.println("Executing query..."+i);

            connection.close();
            return this;
        }

        catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;

    }
    public static ArrayList<Student>getinfo()
    {
        ArrayList<Student>students=new ArrayList<>();
        try {
            String query = String.format("Select %s,%s,%s,%s,%s,%s,%s from %s;", mydata.ID, mydata.FIRST_NAME,mydata.LAST_NAME,mydata.MOBILE,mydata.EMAIL,mydata.PASSWORD,mydata.GENDER,mydata.TABLE_NAME);
            String connectionurl = "jdbc:sqlite:quiz.db";
            System.out.println("Connecting to database...");
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(connectionurl);
            System.out.println("Database connected successfully.");
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ResultSet result = ps.executeQuery();
            while (result.next()) {
               Student s=new Student();
               s.setId(result.getInt(1));
                s.setFirstName(result.getString(2));
                s.setLastName(result.getString(3));
                s.setMobile(result.getString(4));
                s.setEmail(result.getString(5));
                s.setPassword(result.getString(6));
                s.setGender(result.getString(7).charAt(0));
                students.add(s);
            }
           connection.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();

        }
        return students;

    }



    public boolean isexist()
    {
        try {
            String query = String.format("Select * from %s where %s = ?;", mydata.TABLE_NAME, mydata.EMAIL);
            String connectionurl = "jdbc:sqlite:quiz.db";
            System.out.println("Connecting to database...");
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(connectionurl);
            System.out.println("Database connected successfully.");
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.email);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                return true;
            }
            connection.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();

        }
        return false;

    }

}
