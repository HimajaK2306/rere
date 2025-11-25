package com.example.rere;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText etFirstName, etUsername, etPassword;
    private Button btnSignUp;
    private TextView tvSignInLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        etFirstName = findViewById(R.id.etFirstName);
        etUsername  = findViewById(R.id.etUsername);
        etPassword  = findViewById(R.id.etPassword);
        btnSignUp   = findViewById(R.id.btnSignUp);
        tvSignInLink = findViewById(R.id.tvSignInLink);

        btnSignUp.setOnClickListener(v -> {
            String first = etFirstName.getText().toString().trim();
            String user  = etUsername.getText().toString().trim();
            String pass  = etPassword.getText().toString().trim();

            if (first.isEmpty() || user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // save this user in SharedPreferences
            SharedPreferences users = getSharedPreferences("users", MODE_PRIVATE);

            // optional: avoid overwriting an existing user
            if (users.contains(user + "_user")) {
                Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
                return;
            }

            users.edit()
                    .putString(user + "_user", user)
                    .putString(user + "_pass", pass)
                    .apply();

            // set this user as the current session
            getSharedPreferences("session", MODE_PRIVATE)
                    .edit()
                    .putString("active_user", user)
                    .apply();

            Toast.makeText(this, "Account created for " + first, Toast.LENGTH_SHORT).show();

            // go to login screen
            startActivity(new Intent(this, SignInActivity.class));
            finish();
        });

        tvSignInLink.setOnClickListener(v -> {
            startActivity(new Intent(this, SignInActivity.class));
            finish();
        });
    }
}
