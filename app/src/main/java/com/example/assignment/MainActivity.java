package com.example.assignment;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText emailField, phoneField, passwordField;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailField = findViewById(R.id.emailField);
        phoneField = findViewById(R.id.phoneField);
        passwordField = findViewById(R.id.passwordField);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    Toast.makeText(MainActivity.this, "Form Submitted Successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateInput() {
        String email = emailField.getText().toString();
        String phone = phoneField.getText().toString();
        String password = passwordField.getText().toString();

        if (!isValidEmail(email)) {
            emailField.setError("Invalid Email");
            return false;
        }

        if (!isValidPhone(phone)) {
            phoneField.setError("Invalid Phone Number");
            return false;
        }

        if (!isValidPassword(password)) {
            passwordField.setError("Password must be at least 6 characters, include 1 number, and 1 special character");
            return false;
        }

        return true;
    }

    // Email validation using Android's built-in Patterns class
    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // Phone number validation (10 digits only)
    private boolean isValidPhone(String phone) {
        return !TextUtils.isEmpty(phone) && Pattern.compile("^[0-9]{10}$").matcher(phone).matches();
    }

    // Password validation (at least 6 characters, 1 number, 1 special character)
    private boolean isValidPassword(String password) {
        return password.length() >= 6 && Pattern.compile("^(?=.*[0-9])(?=.*[!@#$%^&*]).{6,}$").matcher(password).matches();
    }
}


