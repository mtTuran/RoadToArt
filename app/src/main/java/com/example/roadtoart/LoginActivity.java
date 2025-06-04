package com.example.roadtoart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText usernameEntry;
    EditText passwordEntry;
    Button registerButton;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEntry = findViewById(R.id.editTextTextEmailAddress);
        passwordEntry = findViewById(R.id.editTextTextPassword);
        registerButton = findViewById(R.id.register_button);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String username = usernameEntry.getText().toString();
        String password = passwordEntry.getText().toString();
        if (username.length() < 3) {
            usernameEntry.setError("Username length must be at least 3 characters!");
        }
        else if (password.length() < 8) {
            passwordEntry.setError("Password must contain at least 8 characters!");
        }

        else {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("username", username);
            startActivity(intent);
        }
    }
}