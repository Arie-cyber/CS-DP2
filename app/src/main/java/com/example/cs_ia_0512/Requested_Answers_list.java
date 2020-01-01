 package com.example.cs_ia_0512;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.AndroidRuntimeException;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOError;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

 public class Requested_Answers_list extends AppCompatActivity {
     int book_id = Answers.getbookid();
     String page_number = Answers.PN_VALUE();
     String question_number = Answers.QN_VALUE();
     ArrayList<String> Answer_l = new ArrayList<String>();
     ArrayAdapter<String> adapter;
     public ListView list;
     Connection conn;
     public static String Chosen_Answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_pic_);
        conn = SQLConnection.connect();
        final ListView Answers_list = findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(this, R.layout.list_item3, Answer_l);
        Answers_list.setAdapter(adapter);
        Answers_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Chosen_Answer = (String) Answers_list.getItemAtPosition(i).toString();
                Intent intent = new Intent(Requested_Answers_list.this, ChoosingAction.class);
                startActivity(intent);

            }
        });
    }
     public void onResume() {
         super.onResume();
         Statement stmt = null;
         PreparedStatement get;
         ResultSet rs;
         System.out.println("**********************"+book_id+"**********************");
         System.out.println("**********************"+page_number+"**********************");
         System.out.println("**********************%%%%%%%%%%%%%%%%%%%5"+question_number+"%%%%%%%%%%%%**********************");

         try {
             stmt = conn.createStatement();
             System.out.println("created statement");
             rs = stmt.executeQuery("SELECT * FROM ANSWERS WHERE BOOK_ID LIKE '"+ book_id +"' AND PAGE_NUM LIKE'"+ page_number +"' AND QUESTION_NUM LIKE'"+ question_number +"'");
             while ( rs.next() ) {
                 adapter.add(rs.getString("ANSWER_NAME"));
                 adapter.notifyDataSetChanged();
             }
             if (conn != null)
                 conn.close();
         } catch (Exception e) {
             System.err.println("Got an exception! ");
             System.err.println(e.getMessage());
         }
     }

     public static String getChosen_Answer(){
         return Chosen_Answer;
     }



//     public void downloadImage(View view)
//     {
//         // Setting an Async Task so that main thread does not through exception
//         DownloadImage doin = new DownloadImage();
//         doin.execute();
//     }
//
//     // Async task ; a background method
//     private class DownloadImage extends AsyncTask<String, Void, String>
//     {
//         String image="";
//         String msg =  "";
//         ResultSet rs;
//
//         @Override
//         protected void onPreExecute()
//         {
//             errorMsg.setText("Downloading Please Wait...");
//             progressBar.setVisibility(View.VISIBLE);
//         }
//
//         @Override
//         protected String doInBackground(String... params)
//         {
//             String msg =  "";
//             try
//             {
//                 con = ConnectionHelper(un, password, db, ip);
//                 String commands = "SELECT columnPicture From tableImage WHERE imageId='1' ";
//                 Statement stmt = con.createStatement();
//                 rs = stmt.executeQuery(commands);
//                 if(rs.next())
//                 {
//                     image = rs.getString("columnPicture");
//                     msg = "Retrieved Successfully";
//                 }
//                 else
//                 {
//                     msg = "Image not Found in the Database";
//                 }
//             }
//             // Catching all the exceptions
//             catch (SQLException ex)
//             {
//                 msg = ex.getMessage().toString();
//                 Log.d("seotoolzz", msg);
//             }
//             catch (IOError ex)
//             {
//                 // TODO: handle exception
//                 msg = ex.getMessage().toString();
//                 Log.d("seotoolzz", msg);
//             }
//             catch (AndroidRuntimeException ex)
//             {
//                 msg = ex.getMessage().toString();
//                 Log.d("seotoolzz", msg);
//             }
//             catch (NullPointerException ex)
//             {
//                 msg = ex.getMessage().toString();
//                 Log.d("seotoolzz", msg);
//             }
//             catch (Exception ex)
//             {
//                 msg = ex.getMessage().toString();
//                 Log.d("seotoolzz", msg);
//             }
//             return image;
//         }
//
//         @Override
//         protected void onPostExecute(String resultSet)
//         {
//             //Stoping the progress bar and showing the message
//             progressBar.setVisibility(View.GONE);
//             errorMsg.setText(msg);
//             //Checking if image we got is empty or we have success
//             if(resultSet.matches(""))
//             {
//
//             }
//             else
//             {
//                 //Decoding and Setting Image in the imageview
//                 byte[] decodeString = Base64.decode(resultSet, Base64.DEFAULT);
//                 Bitmap decodebitmap = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
//                 imageView.setImageBitmap(decodebitmap);
//             }
//         }
//     }



 }

