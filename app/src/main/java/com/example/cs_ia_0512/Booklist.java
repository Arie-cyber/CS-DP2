package com.example.cs_ia_0512;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class Booklist extends AppCompatActivity {
    public static String ChosenBook;

    int subject_id = Subjectslist.getData();
    ArrayList<String> bl = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    //android:divider="@null"
    public ListView list;
    Connection conn;
    private static String Book_name;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklist);
        conn = SQLConnection.connect();
        final ListView Books_list = findViewById(R.id.list);

        adapter = new ArrayAdapter<String>(this, R.layout.listitem2, bl);
        Books_list.setAdapter(adapter);

        Books_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Book_name = (String) Books_list.getItemAtPosition(i);
//
//                Connection conn = SQLConnection.connect();
//                Statement stmt = null;
//                PreparedStatement get;
//                ResultSet rs;
//
//                int counter;
//                try {
//            rs = stmt.executeQuery("SELECT COUNT (*) FROM BOOKS WHERE SUBJECT_ID LIKE '" + subject_id + "')");
//            int BOOK_NUM = rs.getInt("COUNT");
//                    rs = stmt.executeQuery("SELECT * FROM BOOKS WHERE SUBJECT_ID LIKE '" + subject_id + "')");
//                    while ( rs.next()) {
//                        Book_name = rs.getString("BOOK_NAME");
//                        bl.add(Book_name);
//                    }
//                    if (conn != null)
//                        conn.close();
//                }
//                catch (Exception e) {
//                    e.printStackTrace();
//                }
//                Book_name=(Books_list.getItemAtPosition(i)).toString();
//                Intent intent = new Intent(Booklist.this, ChoosingAction.class);
//                startActivity(intent);
            }
        });
//        // Create an ArrayAdapter from List
//        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
//                (this, android.R.layout.simple_list_item_1, Bookslist);
//
//        // DataBind ListView with items from ArrayAdapter
//        Books_list.setAdapter(arrayAdapter);
//        // Create a List from String Array elements
//
//                List Books_list = Arrays.asList(Bookslist);
//                List<HashMap<String, String>> Bookslist = new ArrayList<HashMap<String, String>>();
//                ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.listitem2, Books_list);
//
//                for (int i = 0; i < Books_list.size(); i++) {
//                    HashMap<String, String> hm = new HashMap<String, String>();
//                    hm.put("listview_title", Books_list[i]);
//                    Bookslist.add(hm);
//                }
//                adapter = new ArrayAdapter<String>(this, R.layout.listitem2, Bookslist);
//                    throw new IllegalStateException("Unexpected value: " + subjectname);

//
                //show the book list that are connected to the chosen book
                //String book_name = rs.getString("BOOK_NAME");


//                ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.listitem2, (List<String>) Book_nameslist);
//                Books_list.setAdapter(adapter);
//                Books_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        ChosenBook=(Books_list.getItemAtPosition(i)).toString();
//                        Intent intent = new Intent(Booklist.this, Booklist.class);
//                        startActivity(intent);
//                    }
//                });


    }
    @Override
    public void onResume() {
        super.onResume();
        Statement stmt = null;
        PreparedStatement get;
        ResultSet rs;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM BOOKS WHERE SUBJECT_ID = '"+subject_id+"'");
            while ( rs.next() ) {
                adapter.add(rs.getString("BOOK_NAME"));
                adapter.notifyDataSetChanged();
            }
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    public static String getData(){

        return Book_name;
    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
//    public void addItems(View v) {
//        adapter.add();
////        bl.add(clickCounter);
////        adapter.notifyDataSetChanged();
//    }


}
