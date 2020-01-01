package com.example.cs_ia_0512;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Login extends AppCompatActivity {
    private Button btnLogin, btnSignup;
    private EditText txtUsername;
    private EditText txtPassword;
    private TextView textView;
    private ProgressBar progressBar12345;
    Connection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SQLConnection.connect();

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        progressBar12345 = (ProgressBar) findViewById(R.id.progressBar12345);
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                CheckLogin checkLogin = new CheckLogin();// this is the Asynctask, which is used to process in background to reduce load on app process
                checkLogin.execute("");
                Intent intent = new Intent(Login.this, Subjectslist.class);
                startActivity(intent);

            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);

            }
        });
    }
    public class CheckLogin extends AsyncTask<String,String,String>
    {
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        String MS = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute()
        {

            progressBar12345.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String r)
        {
            progressBar12345.setVisibility(View.GONE);
            Toast.makeText(Login.this, r, Toast.LENGTH_SHORT).show();
            if(isSuccess)
            {
                Toast.makeText(Login.this , "Login Successfull" , Toast.LENGTH_LONG).show();
                //finish();
            }
        }
        @Override
        protected String doInBackground(String... args)
        {
            if (args.length < 2)
                return "";
            if (args[0] == null || args[1] == null)
                return "";

            username = args[0];
            password = args[1];
            if(username.trim().equals("")|| password.trim().equals(""))
                MS = "Please enter Username and Password";
            else
            {
                try
                {
                    conn = SQLConnection.connect();
                    Statement stmt = null; // Connect to database
                    if (conn == null)
                    {
                        MS = "Check Your Internet Access!";
                    }
                    else
                    {
                        // Change below query according to your own database.
                        String query = "select * from USERS where USERNAME= '" + username + "' and PASSWORD = '"+ password +"'  ";
                        stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        if(rs.next())
                        {
                            MS = "Login successful";
                            isSuccess=true;
                            conn.close();
                        }
                        else
                        {
                            MS = "Invalid Credentials!";
                            isSuccess = false;
                        }
                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    MS = ex.getMessage();
                }
            }
            return MS;
        }
    }


//    @SuppressLint("NewApi")
//    public Connection connectionclass(String user, String password, String database, String server)
//    {
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//        Connection connection = null;
//        String ConnectionURL = null;
//        try
//        {
//            Class.forName("net.sourceforge.jtds.jdbc.Driver");
//            ConnectionURL = "jdbc:jtds:sqlserver://" + server + database + ";user=" + user+ ";password=" + password + ";";
//            connection = DriverManager.getConnection(ConnectionURL);
//        }
//        catch (SQLException se)
//        {
//            Log.e("error here 1 : ", se.getMessage());
//        }
//        catch (ClassNotFoundException e)
//        {
//            Log.e("error here 2 : ", e.getMessage());
//        }
//        catch (Exception e)
//        {
//            Log.e("error here 3 : ", e.getMessage());
//        }
//        return connection;
//    }
}


