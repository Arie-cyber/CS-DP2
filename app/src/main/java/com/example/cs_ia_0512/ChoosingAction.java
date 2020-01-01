package com.example.cs_ia_0512;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class ChoosingAction extends AppCompatActivity {
    String ChosenBook = Booklist.getData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_action);
        TextView Title = findViewById(R.id.txtviewtitle);
        Title.setText(ChosenBook);
        System.out.println(ChosenBook);


        Button uploadingfiles = findViewById(R.id.btnuploading);
        Button watchinganwers=findViewById(R.id.btnbookanswers);

        uploadingfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoosingAction.this, UploadAnswers.class);
                startActivity(intent);
            }
        });
        watchinganwers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoosingAction.this, Answers.class);
                startActivity(intent);
            }
        });
    }
}
