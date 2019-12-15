package com.example.cs_ia_0512;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Subjectslist extends AppCompatActivity {
    public static String subjectname;
    private static int subject_id;
    Connection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjectslist);

        Connection conn = SQLConnection.connect();
        final ListView Subjects = findViewById(R.id.Subjects);
        String[] SubjectArry = new String[]{"English A", "Hebrew", "Arabic", "English B", "French AB", "Spanish AB", "Economics", "Global politics",
                "Psychology", "Philosophy", "ESS", "Computer Science", "Chemistry", "Biology","Physics", "Math", "Art"};
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.listitem, SubjectArry);
        Subjects.setAdapter(adapter);
        Subjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                subjectname=(Subjects.getItemAtPosition(i)).toString();
                Intent intent = new Intent(Subjectslist.this, Booklist.class);
                startActivity(intent);
            }
        });



        Statement stmt = null;
        PreparedStatement get;
        ResultSet rs;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT SUBJECT_ID FROM SUBJECTS WHERE SUBJECT_NAME LIKE '" + subjectname + "')");
            while ( rs.next() ) {
                subject_id = rs.getInt("SUBJECT_ID");
            }
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public static int getData(){
        return subject_id;
    }


}
