package com.example.flory;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    EditText editTextEmail, editTextPassword;

    ImageButton login_btn;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        login_btn = findViewById(R.id.login_btn);
        firebaseAuth = FirebaseAuth.getInstance();


        TextView regiterlink = findViewById(R.id.regiterlink);
        regiterlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        });



        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(MainActivity.this, "Enter Your Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this, "Enter Your Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                                    checkUserRole(currentUser.getUid());
                                } else {
                                    Toast.makeText(MainActivity.this, "Authentication Failed !", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private void checkUserRole(String userId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(userId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    String role = documentSnapshot.getString("role");

                    if ("admin".equals(role)) {
                        startActivity(new Intent(MainActivity.this, AdminHome.class));
                    } else if ("florist".equals(role) || "student".equals(role)) {
                        Intent homeIntent = new Intent(MainActivity.this, Home.class);
                        homeIntent.putExtra("userRole", role); // Passing the role to the next activity
                        startActivity(homeIntent);
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid role!", Toast.LENGTH_SHORT).show();
                    }
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(MainActivity.this, "Error fetching user role.", Toast.LENGTH_SHORT).show();
                });
    }
}