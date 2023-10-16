package com.example.flory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

public class AdminHome extends AppCompatActivity {

    ImageButton menu_, admin_h_agro_icon, admin_h_flower_icon,admin_h_user_icon,admin_h_diseases_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        menu_ = findViewById(R.id.menu_);
        admin_h_agro_icon = findViewById(R.id.admin_h_agro_icon);
        admin_h_flower_icon = findViewById(R.id.admin_h_flower_icon);
        admin_h_user_icon = findViewById(R.id.admin_h_user_icon);
        admin_h_diseases_icon = findViewById(R.id.admin_h_diseases_icon);

        menu_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this, AdminNav.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
                finish();
            }
        });

        admin_h_agro_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this, AgroPathologist.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
                finish();
            }
        });


        admin_h_flower_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this, Flowers.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
                finish();
            }
        });


//        admin_h_user_icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AdminHome.this, UsersView.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
//                finish();
//            }
//        });


        admin_h_diseases_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this, Diseases.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
                finish();
            }
        });

    }
}