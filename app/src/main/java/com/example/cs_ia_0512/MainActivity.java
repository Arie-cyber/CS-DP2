package com.example.cs_ia_0512;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private Button button1234;
    public static Connection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conn = SQLConnection.connect();
        Statement stmt = null;
        button1234 = (Button) findViewById(R.id.button1234);

        button1234.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(MainActivity.this, Login.class);
                 startActivity(intent);
             }
         });

        try {
            stmt = conn.createStatement();

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
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void onClick(View view) {
        if (view == button1234) {
//            finish();
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);

        }
    }
}

