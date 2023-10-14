package com.example.flory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.firestore.auth.User;

public class AdminNav extends AppCompatActivity {

    ImageButton close_icon_black,admin_nav_users_btn,admin_nav_agro_btn, admin_nav_flower_btn, admin_nav_diseases_btn,admin_nav_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_nav);

        close_icon_black = findViewById(R.id.close_icon_black);
        admin_nav_agro_btn = findViewById(R.id.admin_nav_agro_btn);
        admin_nav_flower_btn = findViewById(R.id.admin_nav_flower_btn);
        admin_nav_users_btn = findViewById(R.id.admin_nav_users_btn);
        admin_nav_diseases_btn = findViewById(R.id.admin_nav_diseases_btn);
        admin_nav_logout = findViewById(R.id.admin_nav_logout);

        close_icon_black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminNav.this, AdminHome.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
                finish();
            }
        });

        admin_nav_agro_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminNav.this, AgroPathologist.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
                finish();
            }
        });

        admin_nav_flower_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminNav.this, Flowers.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
                finish();
            }
        });

        admin_nav_users_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminNav.this, User.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
                finish();
            }
        });

        admin_nav_diseases_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminNav.this, Diseases.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
                finish();
            }
        });

        admin_nav_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminNav.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
                finish();
            }
        });
    }
}