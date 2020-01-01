package com.example.cs_ia_0512;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.AndroidRuntimeException;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOError;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Answers_3 extends AppCompatActivity {
    final String TAG = Answers_3.class.getSimpleName();
    TextView errorMsg;
    private Button btnGoBack;
    private TextView ChosenAnswer;
    ProgressBar progressBar;
    private ImageView imageView;
    String Chosen_Answer;
    Connection conn;
    byte[] byteArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers_3);
        imageView = (ImageView) findViewById(R.id.imageView111);
        Chosen_Answer = getIntent().getStringExtra("Chosen_Answer");
        errorMsg = (TextView) findViewById(R.id.errorMsg);
        btnGoBack = (Button) findViewById(R.id.btnGoBack);
        ChosenAnswer = (TextView) findViewById(R.id.txtChosenAnswer);
        progressBar = findViewById(R.id.progress_bar);
        getString();
        bitmap();

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    public String getString() {
        String z = " ";
        String msg = "";
        Statement stmt = null;
        ResultSet rs;
        Connection conn = SQLConnection.connect();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM ANSWERS WHERE ANSWER_NAME = '" + Chosen_Answer + "'");
            if (rs != null && rs.next()) {
                z = rs.getString("IMAGE");
                System.out.println("Retrieved Successfully" + "_______________" + z);
            } else {
                msg = "Image not Found in the Database";
            }

        } catch (SQLException ex) {
            msg = ex.getMessage().toString();
            Log.d("seotoolzz", msg);
        } catch (IOError ex) {
            // TODO: handle exception
            msg = ex.getMessage().toString();
            Log.d("seotoolzz", msg);
        } catch (AndroidRuntimeException ex) {
            msg = ex.getMessage().toString();
            Log.d("seotoolzz", msg);
        } catch (NullPointerException ex) {
            msg = ex.getMessage().toString();
            Log.d("seotoolzz", msg);
        } catch (Exception ex) {
            msg = ex.getMessage().toString();
            Log.d("seotoolzz", msg);
        }

        System.out.println("_____________________"+z+"_____________");
        return z;
    }

    public ImageView bitmap() {
        String z = getString();
        System.out.println("_____________________"+z+"_____________");
        byte[] decodedString = Base64.decode(z, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageView.setImageBitmap(decodedByte);
        return imageView;
    }
}



//    private class Fetch extends AsyncTask<String, String, String> {
//        String z = " ";
//        String msg =  "";
//
//        @Override
//        protected void onPreExecute() {
//            progressBar.setVisibility(View.VISIBLE);
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            Statement stmt = null;
//            ResultSet rs;
//            conn = SQLConnection.connect();
//            try {
//                stmt = conn.createStatement();
//                rs = stmt.executeQuery("SELECT * FROM ANSWERS WHERE ANSWER_NAME = '"+ Chosen_Answer +"'");
//                if (rs != null && rs.next()) {
//                    z = rs.getString("IMAGE");
//                    System.out.println("Retrieved Successfully"+"_______________"+z);
//                }
//                else {
//                    msg = "Image not Found in the Database";
//                }
//
//            }
//            catch (SQLException ex)
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
//
//            Log.d(TAG + "First",z);
//            return z;
//        }
//
//        @Override
//        protected void onPostExecute(String z) {
//            Log.d(TAG + "second",z);
//            progressBar.setVisibility(View.GONE);
//            errorMsg.setText(msg);
//            String base64Image = z.split(",")[1];
//            byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
//            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//            imageView.setImageBitmap(decodedByte);
////            byteArray = Base64.decode(z, Base64.DEFAULT);
////            Bitmap decodedImage = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
////            imageView.setImageBitmap(decodedImage);
//            System.out.println("______image________");
////                 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
////                 Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.imageView111);
////                 bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
////                 byte[] imageBytes = byteArrayOutputStream.toByteArray();
////                 String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
//                 imageBytes = Base64.decode(imageString, Base64.DEFAULT);
//
//
//                 Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
//                 imageView.setImageBitmap(decodedImage);
//                 byte[] decodeString = Base64.decode(z, Base64.DEFAULT);
//                 Bitmap decodebitmap = BitmapFactory.decodeByteArray(decodeString,
//                         0, decodeString.length);
//                 imageView.setImageBitmap(decodebitmap);
//                 progressBar.setVisibility(View.GONE);


