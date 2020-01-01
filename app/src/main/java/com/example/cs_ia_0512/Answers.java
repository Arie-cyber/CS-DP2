package com.example.cs_ia_0512;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Answers extends AppCompatActivity {
    private Button btnwatchanswer;
    private static EditText txtPageNum;
    private static EditText txtQuestionNum;
    ProgressBar progressBar;
    Button btnDownload;
    TextView errorMsg;
    private static int Answer_id;
    Connection conn; // Declaring connection variables
    static String Book_name = Booklist.getData();
    static String Pagenum;
    static String Questionnum;
    static int book_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);
        btnwatchanswer = (Button) findViewById(R.id.btnwatchanswer);
        txtPageNum = (EditText) findViewById(R.id.txtPageNum);
        txtQuestionNum = (EditText) findViewById(R.id.txtQuestionNum);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        errorMsg = (TextView) findViewById(R.id.errorMsg);
        progressBar.setVisibility(View.GONE);
        PN_VALUE();
        QN_VALUE();
        getbookid();
        btnwatchanswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Answers.this, Requested_Answers_list.class);
                startActivity(intent);

            }
        });


    }

    public static String PN_VALUE() {
        Pagenum = txtPageNum.getText().toString();
        System.out.println(Pagenum);
        return Pagenum;
    }

    public static String QN_VALUE() {
       Questionnum = txtQuestionNum.getText().toString();
        System.out.println(Questionnum);
       return Questionnum;
    }

    public static int getbookid() {
        Connection conn = SQLConnection.connect();
        Statement stmt = null;
        ResultSet rs = null;
        book_id = 0;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            rs = stmt.executeQuery("SELECT BOOK_ID FROM BOOKS WHERE BOOK_NAME LIKE '" + Book_name + "'");
            System.out.println("selected book_id\n******************************");
            while (rs.next()){
                book_id = rs.getInt("BOOK_ID");
            }
            System.out.println("######################################################################################"+book_id+"**********************************");

            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return book_id;
//        while (true) {
//            try {
//                if (!rs.next()) break;
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                book_id = rs.getInt("BOOK_ID");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        }


    }

    public static int getAnswer() {
        return Answer_id;
    }
}


//    Statement stmt = null;
//    PreparedStatement get;
//    ResultSet rs;
//    String page_number;
//    String question_number;
//    page_number = PN_VALUE();
//    question_number = QN_VALUE();
//    int b_id = 0;
//    b_id = getbookid();
//                    System.out.println("****************************");
//
//    // String url = "jdbc:msql://200.210.220.1:1114/Demo";
////            Connection conn = DriverManager.getConnection(url,"","");
//    stmt = conn.createStatement();
//    rs = stmt.executeQuery("SELECT * FROM ANSWERS WHERE BOOK_ID = '" + b_id + "'AND PAGE_NUM='" + page_number + "' AND PAGE_NUM='" + question_number + "' ");
//                    while (rs.next()) {
//        Answer_id = rs.getInt("ANSWER_ID");
//    }
//                    if (conn != null)
//            conn.close();
//} catch (Exception e)
// {
//        System.err.println("Got an exception! ");
//        System.err.println(e.getMessage());
//        }



