package com.example.flory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Home extends AppCompatActivity {

    ImageButton diseases_db_btn, prediction_camera_btn,flower_db_btn,patholist_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String userRole = getIntent().getStringExtra("userRole");
        if ("student".equals(userRole)) {
            ImageButton pathologistButton = findViewById(R.id.patholist_btn);
            pathologistButton.setVisibility(View.GONE);
        }

        diseases_db_btn = findViewById(R.id.diseases_db_btn);
        prediction_camera_btn = findViewById(R.id.prediction_camera_btn);
        flower_db_btn = findViewById(R.id.flower_db_btn);
        patholist_btn = findViewById(R.id.patholist_btn);

        diseases_db_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Diseases.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
                finish();
            }
        });

        prediction_camera_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Prediction.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
                finish();
            }
        });

        flower_db_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Flowers.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
                finish();
            }
        });

        patholist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, AgroPathologist.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
                finish();
            }
        });


    }
}