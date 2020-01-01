package com.example.cs_ia_0512;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.AndroidRuntimeException;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOError;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Answers_3 extends AppCompatActivity {
    TextView errorMsg;
    private Button btnGoBack;
    private TextView ChosenAnswer;
    ProgressBar progressBar;
    private ImageView imageView;
    String Chosen_Answer= Requested_Answers_list.getChosen_Answer();
    Connection conn;
    byte[] byteArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers_3);
        errorMsg = (TextView) findViewById(R.id.errorMsg);
        imageView = (ImageView) findViewById(R.id.imageView111);
        btnGoBack = (Button) findViewById(R.id.btnGoBack);
        ChosenAnswer = (TextView) findViewById(R.id.txtChosenAnswer);
        progressBar = findViewById(R.id.progress_bar);

        Fetch f = new Fetch();
        f.execute("");

        btnGoBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
    private class Fetch extends AsyncTask<String, String, String> {
        String z = " ";
        String msg =  "";

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            Statement stmt = null;
            ResultSet rs;
            try {
                stmt = conn.createStatement();
                System.out.println("created statement");
                rs = stmt.executeQuery("SELECT * FROM ANSWERS WHERE ANSWER_ID = '"+ Chosen_Answer +"'");
                if (rs != null && rs.next()) {
                    z = rs.getString("IMAGE");
                    msg = "Retrieved Successfully";
                }
                else {
                    msg = "Image not Found in the Database";
                }

            }
//            catch (Exception e) {
//                System.out.print("err");
//                e.printStackTrace();
//                z = "No such ID";
//            }
            catch (SQLException ex)
             {
                 msg = ex.getMessage().toString();
                 Log.d("seotoolzz", msg);
             }
             catch (IOError ex)
             {
                 // TODO: handle exception
                 msg = ex.getMessage().toString();
                 Log.d("seotoolzz", msg);
             }
             catch (AndroidRuntimeException ex)
             {
                 msg = ex.getMessage().toString();
                 Log.d("seotoolzz", msg);
             }
             catch (NullPointerException ex)
             {
                 msg = ex.getMessage().toString();
                 Log.d("seotoolzz", msg);
             }
             catch (Exception ex)
             {
                 msg = ex.getMessage().toString();
                 Log.d("seotoolzz", msg);
             }

            return z;
        }

        @Override
        protected void onPostExecute(String rs) {
            progressBar.setVisibility(View.GONE);
            errorMsg.setText(msg);
            //Checking if image we got is empty or we have success
             if(rs.matches(""))
             {
             }
             else {
                 byte[] decodeString = Base64.decode(rs, Base64.DEFAULT);
                 Bitmap decodebitmap = BitmapFactory.decodeByteArray(decodeString,
                         0, decodeString.length);
                 imageView.setImageBitmap(decodebitmap);
                 progressBar.setVisibility(View.GONE);
             }
        }

    }

}
