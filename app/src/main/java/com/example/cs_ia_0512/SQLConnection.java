package com.example.cs_ia_0512;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection {
    private static String ip = "arielcs.database.windows.net";
    private static String port = "1433";
    private static String classs = "net.sourceforge.jtds.jdbc.Driver";
    private static String db = "CSIA";
    private static String un = "ariel";
    private static String password = "Zigler1805";


    public static Connection connect() {
        Connection conn = null;
        String ConnURL = null;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(classs);
            ConnURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";"
                    + "databaseName=" + db + ";user=" + un + ";password="
                    + password + ";";
            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.err.println("*****************************************************\n creating tables\n*****************************");
        Createtables(conn);
        System.err.println("*****************************************************\n populating tables\n*****************************");
        insertValue(conn);
        System.err.println("*****************************************************\n insert subkects\n*****************************");
        insertBooks(conn);
        System.err.println("*****************************************************\n booksss\n*****************************");
        return conn;
    }
    private static void Createtables(Connection conn){
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            String create_subjects = "CREATE TABLE SUBJECTS " +
                    "(SUBJECT_ID INTEGER not NULL, " +
                    " SUBJECT_NAME VARCHAR(255), " +
                    " PRIMARY KEY ( SUBJECT_ID ))";


            String create_books = "CREATE TABLE BOOKS " +
                    "(BOOK_ID INTEGER not NULL, " +
                    " BOOK_NAME VARCHAR(255), " +
                    " SUBJECT_ID INTEGER not NULL , " +
                    " FOREIGN KEY(SUBJECT_ID) references SUBJECTS, " +
                    " PRIMARY KEY ( BOOK_ID ))";

            String create_answers = "CREATE TABLE ANSWERS " +
                    "(ANSWER_ID INTEGER not NULL, " +
                    " BOOK_ID INTEGER not NULL, " +
                    " ANSWER_NAME VARCHAR(255), " +
                    " IMAGE IMAGE, " +
                    " PAGE_NUM INTEGER not NULL, " +
                    " QUESTION_NUM INTEGER not NULL, " +
                    " FOREIGN KEY(BOOK_ID) references BOOKS, " +
                    " PRIMARY KEY ( ANSWER_ID ))";

            String create_users = "CREATE TABLE USERS " +
                    "(USER_ID INTEGER not NULL, " +
                    " USERNAME VARCHAR(255), " +
                    " PASSWORD VARCHAR(255), " +
                    " EMAIL VARCHAR(255), " +
                    " COUNTRY VARCHAR(255), " +
                    " PRIMARY KEY ( USER_ID ))";

            try {
                stmt.executeUpdate(create_subjects);
            } catch (SQLException e) {
            }
            try {
                stmt.executeUpdate(create_books);
            } catch (SQLException e) {
            }
            try {
                stmt.executeUpdate(create_answers);
            } catch (SQLException e) {
            }
            try {
                stmt.executeUpdate(create_users);
            } catch (SQLException e) {
            }



            System.out.println("Finished Creating The DB");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static int insertBooks(Connection conn) {
        ResultSet rs;
        Statement stmt = null;
        int count = 0;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            rs = stmt.executeQuery("SELECT * FROM BOOKS");
            if (rs.next()== true) {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try { //TODO: query does not match the table definition. book id,book_name,subject id
            stmt.executeUpdate("INSERT INTO BOOKS " + "VALUES ('11', 'English A: literature second edition (Oxford)', '1')");
            stmt.executeUpdate("INSERT INTO BOOKS " + "VALUES ('12', 'English A:literature-course companion', '1')");
            stmt.executeUpdate("INSERT INTO BOOKS " + "VALUES ('13', 'English A:language and literature', '1')");
            stmt.executeUpdate("INSERT INTO BOOKS " + "VALUES ('21', 'NULL', '2')");
            stmt.executeUpdate("INSERT INTO BOOKS " + "VALUES ('31', 'NULL', '3')");
            stmt.executeUpdate("INSERT INTO BOOKS " + "VALUES ('41', 'English B - PEARSON BACCALAURREATE third edition)', '4')");
            stmt.executeUpdate("INSERT INTO BOOKS " + "VALUES ('42', 'English B-course companion (OXFORD))', '4')");
            stmt.executeUpdate("INSERT INTO BOOKS " + "VALUES ('51', 'NULL', '5')");
            stmt.executeUpdate("INSERT INTO BOOKS " + "VALUES ('61', 'Spanish ab initio - J. Rafael Angel', '6')");
            stmt.executeUpdate("INSERT INTO BOOKS " + "VALUES ('71', 'NULL', '7')");
            stmt.executeUpdate("INSERT INTO BOOKS " + "VALUES ('81', 'GLOBAL POLITICS OXFORD (Max Kirsch)', '8')");
            stmt.executeUpdate("INSERT INTO BOOKS " + "VALUES ('91', 'Psychology For the IB Diploma, Pearson', '9')");
            stmt.executeUpdate("INSERT INTO BOOKS " + "VALUES ('101', 'PHILOSOPHY- BEING HUMAN (OXFORD)', '10')");
            stmt.executeUpdate("INSERT INTO BOOKS " + "VALUES ('111', 'COMPUTER SCIENCE 2018 EDITION ', '11')");
            stmt.executeUpdate("INSERT INTO BOOKS " + "VALUES ('121', 'NULL', '12')");
            stmt.executeUpdate("INSERT INTO BOOKS " + "VALUES ('131', 'NULL', '13')");
            stmt.executeUpdate("INSERT INTO BOOKS " + "VALUES ('141', 'Physics- 2014 Edition OXFORD IB STUDY GUIDE', '14')");
            stmt.executeUpdate("INSERT INTO BOOKS " + "VALUES ('151', 'Mathematics for the international student, Mathematics SL(third edition)', '15')");
            stmt.executeUpdate("INSERT INTO BOOKS " + "VALUES ('161', 'NULL')', '16')");
            stmt.executeUpdate("INSERT INTO BOOKS " + "VALUES ('171', 'VISUAL_ARTS (OXFORD) JAYSON PATERSON', '17')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    public static int insertValue(Connection conn) {
        Statement stmt = null;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            rs = stmt.executeQuery("SELECT * FROM SUBJECTS");
            if (rs.next() == true) {
                return 0;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        try { //TODO: query matches the table definition. create subjects list
            stmt.executeUpdate("INSERT INTO SUBJECTS " + " VALUES ('1', 'ENGLISH A')");
            stmt.executeUpdate("INSERT INTO SUBJECTS " + " VALUES ('2', 'HEBREW')");
            stmt.executeUpdate("INSERT INTO SUBJECTS " + " VALUES ('3', 'Arabic')");
            stmt.executeUpdate("INSERT INTO SUBJECTS " + " VALUES ('4', 'English B')");
            stmt.executeUpdate("INSERT INTO SUBJECTS " + " VALUES ('5', 'French AB')");
            stmt.executeUpdate("INSERT INTO SUBJECTS " + " VALUES ('6', 'Spanish AB')");
            stmt.executeUpdate("INSERT INTO SUBJECTS " + " VALUES ('7', 'Economics')");
            stmt.executeUpdate("INSERT INTO SUBJECTS " + " VALUES ('8', 'Global politics')");
            stmt.executeUpdate("INSERT INTO SUBJECTS " + " VALUES ('9', 'Psychology')");
            stmt.executeUpdate("INSERT INTO SUBJECTS " + " VALUES ('10', 'Philosophy')");
            stmt.executeUpdate("INSERT INTO SUBJECTS " + " VALUES ('11', 'Computer Science')");
            stmt.executeUpdate("INSERT INTO SUBJECTS " + " VALUES ('12', 'Chemistry')");
            stmt.executeUpdate("INSERT INTO SUBJECTS " + " VALUES ('13', 'Biology')");
            stmt.executeUpdate("INSERT INTO SUBJECTS " + " VALUES ('14', 'Physics')");
            stmt.executeUpdate("INSERT INTO SUBJECTS " + " VALUES ('15', 'Math')");
            stmt.executeUpdate("INSERT INTO SUBJECTS " + " VALUES ('16', 'Chemistry')");
            stmt.executeUpdate("INSERT INTO SUBJECTS " + " VALUES ('17', 'Art')");

            // create a list of all the subjects

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    }
