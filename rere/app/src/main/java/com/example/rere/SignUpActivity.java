package com.example.rere;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText etFirstName, etUsername, etPassword;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etFirstName = findViewById(R.id.etFirstName);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = etFirstName.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                // Simple validation to ensure fields are not empty
                if (firstName.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Navigate to the Home Page
                    Intent intent = new Intent(SignUpActivity.this, HomePageActivity.class);
                    startActivity(intent);
                    finish(); // Prevents user from going back to the sign-up page
                }
            }
        });
    }
}