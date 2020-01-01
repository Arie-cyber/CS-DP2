package com.example.cs_ia_0512;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    int User_ID, User_ID_g;
    String Country, Email,UserName, Password1;
    String Country_g, Email_g,UserName_g, Password1_g;


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
                conn = SQLConnection.connect();
                Statement stmt = null;
                try {
                    stmt = conn.createStatement();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ResultSet rs;
                int pass;
                try {
                    User_ID_g= USER_ID();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                UserName_g= Name_Value();
                Password1_g= Password_Value();
                Email_g= Email_Value();
                Country_g= Country_Value();
                System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&_____________"+User_ID+"_______&&&&&&&&&&&&&&&&&"+UserName+"___________&&&&&&&&&&&&&&&&&"+Password1+"&&&&&&&&&&&&&&&&&"+Email+"&&&&&&&&&&&&&&&&&"+Country+"&&&&&&&&&&&&&&&&&");
                try {
                    stmt.executeUpdate("INSERT INTO USERS" + " VALUES ('" +User_ID_g+ "' , '" +UserName_g+ "' , '"+Password1_g+ "' , '" +Email_g+ "' , '" +Country_g+ "')");
                    System.out.println("information was added___________________________________________________");
                    Toast.makeText(SignUp.this, "SIGNING UP COMPLETED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent(SignUp.this, Subjectslist.class);
                startActivity(intent);
            }
        });
    }

    private String Name_Value(){
        UserName = txtName.getText().toString();
        return UserName;
    }
    private String Password_Value(){
        Password1 = txtPassword1.getText().toString();
        return Password1;

    }
    private String Email_Value(){
        Email =  txtEmail.getText().toString();
        return Email;

    }
    private String Country_Value(){
        Country =  txtCountry.getText().toString();
        return Country;

    }
    private int USER_ID() throws SQLException {
        conn = SQLConnection.connect();
        int counter;
        Statement stmt = null;
        ResultSet rs = null;
        int user_Id = -1;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            rs = stmt.executeQuery("SELECT COUNT (*) AS TOTAL FROM USERS");
            while (rs.next())
                user_Id = rs.getInt("TOTAL");

            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        User_ID= user_Id+1;
        System.out.println("*****************************"+User_ID+"*****************************");
        return User_ID;


    }
}
