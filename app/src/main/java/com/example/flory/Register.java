package com.example.flory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText reg_email, reg_password;

    ImageButton register_btn;

    FirebaseAuth firebaseAuth =FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reg_email = findViewById(R.id.reg_email);
        reg_password = findViewById(R.id.reg_password);
        register_btn = findViewById(R.id.register_btn);

        TextView loginlink = findViewById(R.id.loginlink);
        loginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email , password;
                email = String.valueOf(reg_email.getText());
                password = String.valueOf(reg_password.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this, "Enter Your Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this, "Enter Your Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Register.this, "Registration Successful!", Toast.LENGTH_SHORT).show();

                                    // Add user to Firestore with 'student' role
                                    FirebaseUser registeredUser = firebaseAuth.getCurrentUser();
                                    if (registeredUser != null) {
                                        String userId = registeredUser.getUid();
                                        FirebaseFirestore db = FirebaseFirestore.getInstance();

                                        // Create user document with role 'student'
                                        Map<String, Object> users = new HashMap<>();
                                        users.put("role", "student");

                                        db.collection("users").document(userId)
                                                .set(users)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Intent intent = new Intent(Register.this, MainActivity.class);
                                                        startActivity(intent);
                                                        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
                                                    }
                                                })
                                                .addOnFailureListener(e -> {
                                                    Toast.makeText(Register.this, "Error adding user to Firestore: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                                    Log.e("FirestoreError", "Error adding user to Firestore: " + e.getMessage(), e);

                                                });
                                    }

                                } else {
                                    Toast.makeText(Register.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }

//    private void saveUserAsStudent(String userId) {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//        Map<String, Object> userData = new HashMap<>();
//        userData.put("role", "student");
//
//        db.collection("users").document(userId)
//                .set(userData)
//                .addOnSuccessListener(aVoid -> {
//                    Log.e("SaveStudentSuccessfully", "Successfully saving user as student");
//                })
//                .addOnFailureListener(e -> {
//                    Log.e("SaveStudentError", "Error saving user as student", e);
//                });
//    }

}