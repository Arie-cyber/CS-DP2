package com.example.cs_ia_0512;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.AndroidRuntimeException;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.ByteArrayOutputStream;

import static com.example.cs_ia_0512.Booklist.ChosenBook;
import static com.example.cs_ia_0512.MainActivity.conn;

public class UploadAnswers extends AppCompatActivity {

    private static final int OPEN_DOCUMENT_CODE = 2;
    private ImageView imageView111;
    private Button btnUpload, btnChoose;
    private Uri mImageUri;
    private EditText pageNum;
    private EditText questNum;
    private TextView text_view_show_uploads;
    private ProgressBar mProgressBar;
    private final int RESULT_LOAD_IMAGE= 1;
    String un,ip,password,db;
    String encodedImage;
    byte[] byteArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_answers);
        imageView111 = (ImageView) findViewById(R.id.imageView111);
        btnChoose = (Button) findViewById(R.id.btnChoose);
        btnUpload = (Button) findViewById(R.id.btnUpload);
        pageNum = (EditText) findViewById(R.id.pageNum);
        questNum = (EditText) findViewById(R.id.questNum);
        mProgressBar = findViewById(R.id.progress_bar);
        text_view_show_uploads = findViewById(R.id.text_view_show_uploads);
        Connection con;
        String ip, port,db, un, password;

        btnChoose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)&& !Environment.getExternalStorageState().equals(Environment.MEDIA_CHECKING)){
                    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("image/*");
                    startActivityForResult(intent, OPEN_DOCUMENT_CODE);
                }
                else{
                    Toast.makeText(UploadAnswers.this, "A picture was not chosen", Toast.LENGTH_SHORT).show();
                }


            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connection conn = SQLConnection.connect();
                Statement stmt = null;
                int b_id = 0;
                b_id = gettingid();
                String page_number;
                String question_number;
                page_number = PN_VALUE();
                question_number= QN_VALUE();
                int a_id = 0;
                try {
                    a_id = AnswerID();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                mProgressBar.setVisibility(View.GONE);


                try {
                    stmt = conn.createStatement();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
//                    String query = "INSERT INTO ANSWERS" + "VALUES ('";
//                    query = query + a_id;
//                    query = query + "' , ";  // = "INSERT INTO ANSWERS VALUES('3',
//                    query
                    stmt.executeUpdate("INSERT INTO ANSWERS" + "VALUES ('" + a_id + "' , ' " + b_id +"', 'Mr.', '" +page_number+ "', '" +question_number+ "')");
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                Intent intent = new Intent(UploadAnswers.this, UploadAnswers.class);
                startActivity(intent);
            }
        });
        ip= "arielcs.database.windows.net";
        db = "CSIA";
        un ="ariel";
        port="1433";
        password = "Zigler1805";

//        int ANSWER_ID =

//        " BOOK_ID INTEGER not NULL, " +
//                " IMAGE IMAGE, " +
//                " PAGE_NUM INTEGER not NULL, " +
//                " QUESTION_NUM INTEGER not NULL, " +
//                " FOREIGN KEY(BOOK_ID) references BOOKS


      // stmt = "IF NOT EXISTS (SELECT * FROM dbo.ANSWERS WHERE (ANSWER_ID LIKE "+ getCorrectString(ANSWER_ID)

//        try {
//            stmt = conn.createStatement();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            stmt.executeUpdate("INSERT INTO dbo.ANSWERS " +
//                    "VALUES ('1351235', '3255', '36', '5', 2001)");
////            stmt.executeUpdate("INSERT INTO ANSWERS " +
////                    "VALUES (1002, 'McBeal', 'Ms.', 'Boston', 2004)");
////            stmt.executeUpdate("INSERT INTO ANSWERS " +
////                    "VALUES (1003, 'Flinstone', 'Mr.', 'Bedrock', 2003)");
////            stmt.executeUpdate("INSERT INTO ANSWERS " +
////                    "VALUES (1004, 'Cramden', 'Mr.', 'New York', 2001)");
//            conn.close();
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
    private void InsertAnswers(){

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
    private int AnswerID() throws SQLException {
        Statement stmt = null;
        PreparedStatement get;
        ResultSet rs;
        int counter;
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT COUNT (*) FROM ANSWER");
        int AnswerId = rs.getInt("COUNT");
        return AnswerId;

    }
    private String PN_VALUE(){
        String Pagenumber =  pageNum.getText().toString();
        return Pagenumber;

    }
    private String QN_VALUE(){
        String Questionnumber = questNum.getText().toString();
        return Questionnumber;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == OPEN_DOCUMENT_CODE && resultCode == RESULT_OK) {
            //OPEN_IMAGE- what is the difference between those
            if (resultData != null) {
                // this is the image selected by the user
                Uri imageUri = resultData.getData();
//              Bitmap mBitmap  = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                imageView111.setImageURI(imageUri);
            }
        }

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK  && null != resultData){
            mProgressBar.setVisibility(View.VISIBLE);
            Bitmap originBitmap = null;
            Uri selectedImage = resultData.getData();
            Toast.makeText(UploadAnswers.this, selectedImage.toString(), Toast.LENGTH_LONG).show();
            InputStream imageStream;
            try
            {
                imageStream = getContentResolver().openInputStream(selectedImage);
                originBitmap = BitmapFactory.decodeStream(imageStream);
            }
            catch (FileNotFoundException e)
            {
                System.out.println(e.getMessage().toString());
            }
            if (originBitmap != null)
            {
                this.imageView111.setImageBitmap(originBitmap);
                Log.w("Image Setted in", "Done Loading Image");
                try
                {
                    Bitmap image = ((BitmapDrawable) imageView111.getDrawable()).getBitmap();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    image.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
                    byteArray = byteArrayOutputStream.toByteArray();
                    encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
                    // Calling the background process so that application wont slow down
                    UploadImage uploadImage = new UploadImage();
                    uploadImage.execute("");
                    //End Calling the background process so that application wont slow down
                }
                catch (Exception e)
                {
                    Log.w("OOooooooooo","exception");
                }
                Toast.makeText(UploadAnswers.this, "Conversion Done",Toast.LENGTH_SHORT).show();
            }
            // End getting the selected image, setting in imageview and converting it to byte and base 64
        }
        else
        {
            System.out.println("Error Occured");
        }

    }



//        switch (requestCode) {
//            case 1:
//                if (requestCode == OPEN_DOCUMENT_CODE && resultCode == RESULT_OK) {
//                    if (resultData != null) {
//                        // this is the image selected by the user
//                        Uri imageUri = resultData.getData();
////              Bitmap mBitmap  = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
//                        imageView111.setImageURI(imageUri);
//                    }
//                }
//                break;
//            case 2:
//                if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK  && null != resultData){
//                    mProgressBar.setVisibility(View.VISIBLE);
//                    Bitmap originBitmap = null;
//                    Uri selectedImage = resultData.getData();
//                    Toast.makeText(UploadAnswers.this, selectedImage.toString(), Toast.LENGTH_LONG).show();
//                    InputStream imageStream;
//                    try
//                    {
//                        imageStream = getContentResolver().openInputStream(selectedImage);
//                        originBitmap = BitmapFactory.decodeStream(imageStream);
//                    }
//                    catch (FileNotFoundException e)
//                    {
//                        System.out.println(e.getMessage().toString());
//                    }
//                    if (originBitmap != null)
//                    {
//                        this.imageView111.setImageBitmap(originBitmap);
//                        Log.w("Image Setted in", "Done Loading Image");
//                        try
//                        {
//                            Bitmap image = ((BitmapDrawable) imageView111.getDrawable()).getBitmap();
//                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                            image.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
//                            byteArray = byteArrayOutputStream.toByteArray();
//                            encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
//                            // Calling the background process so that application wont slow down
//                            UploadImage uploadImage = new UploadImage();
//                            uploadImage.execute("");
//                            //End Calling the background process so that application wont slow down
//                        }
//                        catch (Exception e)
//                        {
//                            Log.w("OOooooooooo","exception");
//                        }
//                        Toast.makeText(UploadAnswers.this, "Conversion Done",Toast.LENGTH_SHORT).show();
//                    }
//                    // End getting the selected image, setting in imageview and converting it to byte and base 64
//                }
//                else
//                {
//                    System.out.println("Error Occured");
//                }
//                break;
//        }


    public class UploadImage extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPostExecute(String r)
        {
            // After successful insertion of image
            mProgressBar.setVisibility(View.GONE);
            Toast.makeText(UploadAnswers.this , "Image Inserted Succesfully" , Toast.LENGTH_LONG).show();
            // End After successful insertion of image
        }
        @Override
        protected String doInBackground(String... params)
        {
            // Inserting in the database
            String msg = "unknown";
            Connection conn = null;
            Statement stmt = null;
            try
            {
                conn = SQLConnection.connect();
                String commands = "Insert into MYIMAGE (picture) values ('" + encodedImage + "')";
                PreparedStatement preStmt = conn.prepareStatement(commands);
                preStmt.executeUpdate();
                msg = "Inserted Successfully";
            }
            catch (SQLException ex)
            {
                msg = ex.getMessage().toString();
                Log.d("Error no 1:", msg);
            }
            catch (IOError ex)
            {
                msg = ex.getMessage().toString();
                Log.d("Error no 2:", msg);
            }
            catch (AndroidRuntimeException ex)
            {
                msg = ex.getMessage().toString();
                Log.d("Error no 3:", msg);
            }
            catch (NullPointerException ex)
            {
                msg = ex.getMessage().toString();
                Log.d("Error no 4:", msg);
            }
            catch (Exception ex)
            {
                msg = ex.getMessage().toString();
                Log.d("Error no 5:", msg);
            }
            System.out.println(msg);
            return "";
            //End Inserting in the database
        }
    }







}

