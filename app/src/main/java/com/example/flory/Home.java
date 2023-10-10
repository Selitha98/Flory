package com.example.flory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String userRole = getIntent().getStringExtra("userRole");
        if ("student".equals(userRole)) {
            ImageButton pathologistButton = findViewById(R.id.patholist_btn);
            pathologistButton.setVisibility(View.GONE);
        }
    }
}