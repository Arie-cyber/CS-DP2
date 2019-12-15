package com.example.cs_ia_0512;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SignUp extends AppCompatActivity {
    private Button btnSignup1;
    private EditText txtName,txtPassword1, txtEmail,txtCountry;
    private TextView txtSignup1;
    Connection conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnSignup1 = (Button) findViewById(R.id.btnSignup1);
        txtName = (EditText) findViewById(R.id.txtName);
        txtPassword1 = (EditText) findViewById(R.id.txtPassword1);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtCountry = (EditText) findViewById(R.id.txtCountry);

        btnSignup1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Connection conn = SQLConnection.connect();
                Statement stmt = null;
                int u_id = 0;
                try {
                    u_id = USER_ID();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    stmt = conn.createStatement();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {

//
                    stmt.execute("INSERT INTO USERS VALUES ('" + u_id + "' , ' " + Name_Value() +"', ' " + Password_Value() +"', '" +Email_Value()+ "', '" +Country_Value()+ "')");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(SignUp.this, Subjectslist.class);
                startActivity(intent);


            }
        });

    }

    private String Name_Value(){
        String Name = txtName.getText().toString();
        return Name;

    }
    private int Password_Value(){
        String Password1 = txtPassword1.getText().toString();
        return Integer.parseInt(Password1);

    }
    private String Email_Value(){
        String Email =  txtEmail.getText().toString();
        return Email;

    }
    private String Country_Value(){
        String Country =  txtCountry.getText().toString();
        return Country;

    }
    private int USER_ID() throws SQLException {
        Statement stmt = null;
        PreparedStatement get;
        int counter;
        stmt = conn.createStatement();
        int user_Id = 0;
        try (ResultSet rs = stmt.executeQuery("SELECT COUNT (*) AS TOTAL FROM USERS")) {
            user_Id = rs.getInt("TOTAL");
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        int User_ID= user_Id+1;
        return User_ID;

    }
}
