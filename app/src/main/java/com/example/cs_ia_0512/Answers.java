package com.example.cs_ia_0512;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import static com.example.cs_ia_0512.Booklist.ChosenBook;

public class Answers extends AppCompatActivity {
    private Button btnwatchanswer;
    private EditText txtPageNum;
    private EditText txtQuestionNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);
        btnwatchanswer = (Button) findViewById(R.id.btnwatchanswer);
        txtPageNum = (EditText) findViewById(R.id.txtPageNum);
        txtQuestionNum = (EditText) findViewById(R.id.txtQuestionNum);
        Connection conn = MainActivity.conn;

        String page_number;
        String question_number;
        page_number = PN_VALUE();
        question_number= QN_VALUE();
        int b_id = 0;
        b_id = gettingid();
//        try {
//            conn = MainActivity.SQLConnection.connect();
//        }catch (Exception e) {
//            System.err.println("Got an exception! ");
//            System.err.println(e.getMessage() + e.getStackTrace());
//        }
        Statement stmt = null;
        PreparedStatement get;
        ResultSet rs;

        btnwatchanswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Answers.this, UploadAnswers.class);
                startActivity(intent);
            }
        });


        try {
           // String url = "jdbc:msql://200.210.220.1:1114/Demo";
//            Connection conn = DriverManager.getConnection(url,"","");
            stmt = conn.createStatement();


            rs = stmt.executeQuery("SELECT * FROM ANSWERS WHERE BOOK_ID = '"+b_id+"',PAGE_NUM='"+page_number+"',PAGE_NUM='"+question_number+"' ");
            while ( rs.next() ) {
                //i should get the picture
//                strin IMAGE = rs.getString("IMAIGE");
//                System.out.println(questionNum);
            }
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

    }
    private String PN_VALUE(){
        String Pagenum =  txtPageNum.getText().toString();
        return Pagenum;

    }
    private String QN_VALUE(){
        String Questionnum = txtQuestionNum.getText().toString();
        return Questionnum;
    }
    private int gettingid(){
        Connection conn = SQLConnection.connect();
        Statement stmt = null;
        ResultSet rs= null;
        int book_id=0;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            rs = stmt.executeQuery("SELECT * FROM BOOKS WHERE BOOK_NAME = '"+ChosenBook+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                book_id = rs.getInt("BOOK_ID");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return book_id;


    }
}
