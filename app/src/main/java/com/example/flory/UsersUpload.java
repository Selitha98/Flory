package com.example.flory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UsersUpload extends AppCompatActivity {

    Button saveButton;

    EditText update_username,update_phone,update_email;

    String key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_upload);

        saveButton =findViewById(R.id.saveButton);
        update_username =findViewById(R.id.update_username);
        update_phone =findViewById(R.id.update_phone);
        update_email =findViewById(R.id.update_email);

        key = getIntent().getStringExtra("Key");
        update_username.setText(getIntent().getStringExtra("Username"));
        update_email.setText(getIntent().getStringExtra("Email"));
        update_phone.setText(getIntent().getStringExtra("Phone"));

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UsersUpload.this);
                builder.setCancelable(false);
                builder.setView(R.layout.progress_layout);
                AlertDialog dialog = builder.create();
                dialog.show();
                updateUserData();
            }
        });

    }

    public void updateUserData() {
        String updatedUsername = update_username.getText().toString();
        String updatedEmail = update_email.getText().toString();
        String updatedPhone = update_phone.getText().toString();

        DatabaseReference userReference = FirebaseDatabase.getInstance().getReference("users").child(key);

        userReference.child("username").setValue(updatedUsername);
        userReference.child("useremail").setValue(updatedEmail);
        userReference.child("userphone").setValue(updatedPhone).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(UsersUpload.this, "User data updated successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UsersUpload.this, Users.class);
                    startActivity(intent);
                    finish();  // Close the current activity
                } else {
                    Toast.makeText(UsersUpload.this, "Failed to update user data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}