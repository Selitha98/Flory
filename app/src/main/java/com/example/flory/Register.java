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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText reg_email, reg_password,reg_username, reg_phone;

    ImageButton register_btn;

    FirebaseAuth firebaseAuth =FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reg_email = findViewById(R.id.reg_email);
        reg_password = findViewById(R.id.reg_password);
        register_btn = findViewById(R.id.register_btn);
        reg_username = findViewById(R.id.reg_username);
        reg_phone = findViewById(R.id.reg_phone);

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
                String email, password,username,userphone;
                email = String.valueOf(reg_email.getText());
                password = String.valueOf(reg_password.getText());
                username = String.valueOf(reg_username.getText());
                userphone = String.valueOf(reg_phone.getText());

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(Register.this, "Enter Your Username", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(userphone)) {
                    Toast.makeText(Register.this, "Enter Your Phone Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidSriLankanPhone(userphone)) {
                    Toast.makeText(Register.this, "Enter a valid Phone Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email) || !isValidEmail(email)) {
                    Toast.makeText(Register.this, "Enter a valid Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Register.this, "Enter Your Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 8) {
                    Toast.makeText(Register.this, "Password should be at least 8 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Register.this, "Registration Successful!", Toast.LENGTH_SHORT).show();

                                    FirebaseUser registeredUser = firebaseAuth.getCurrentUser();
                                    if (registeredUser != null) {
                                        String userId = registeredUser.getUid();
                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId);

                                        // Instead of just setting the role, now we're creating a userMap to set multiple values at once.
                                        Map<String, String> userMap = new HashMap<>();
                                        userMap.put("role", "student");
                                        userMap.put("useremail", email);
                                        userMap.put("username", username);
                                        userMap.put("userphone", userphone);

                                        databaseReference.setValue(userMap)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Intent intent = new Intent(Register.this, MainActivity.class);
                                                        startActivity(intent);
                                                        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
                                                    }
                                                })
                                                .addOnFailureListener(e -> {
                                                    Toast.makeText(Register.this, "Error adding user details to Database: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                                    Log.e("DatabaseError", "Error adding user details to Database: " + e.getMessage(), e);
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

    public boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }

    public boolean isValidSriLankanPhone(String phone) {
        String regex = "^07[0-9]{8}$";
        return phone.matches(regex);
    }



}