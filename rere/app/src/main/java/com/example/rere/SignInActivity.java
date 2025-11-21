package com.example.rere;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnSignIn;
    private TextView tvSignUpLink;

    // Hardcoded valid users (for demo purposes)
    private final String[][] validUsers = {
            {"user1", "password1"},
            {"user2", "password2"},
            {"admin", "admin123"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Optional: hide ActionBar for a cleaner look
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize views
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        tvSignUpLink = findViewById(R.id.tvSignUpLink);

        // Sign In button click
        btnSignIn.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(SignInActivity.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean isValid = false;
            for (String[] user : validUsers) {
                if (username.equals(user[0]) && password.equals(user[1])) {
                    isValid = true;
                    break;
                }
            }

            if (isValid) {
                Toast.makeText(SignInActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();

                // 🔔 Notification
                NotificationHelper.showNotification(
                        this,
                        "Welcome Back",
                        "Signed in as " + username
                );

                // Navigate to HomePage
                Intent intent = new Intent(SignInActivity.this, HomePageActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(SignInActivity.this, "Invalid credentials. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });

        // Sign Up link click
        tvSignUpLink.setOnClickListener(v -> {
            Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
