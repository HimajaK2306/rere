package com.example.rere;

import android.content.Intent;
import android.content.SharedPreferences;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        etUsername   = findViewById(R.id.etUsername);
        etPassword   = findViewById(R.id.etPassword);
        btnSignIn    = findViewById(R.id.btnSignIn);
        tvSignUpLink = findViewById(R.id.tvSignUpLink);

        btnSignIn.setOnClickListener(v -> {
            String user = etUsername.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Enter username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences users = getSharedPreferences("users", MODE_PRIVATE);
            String savedUser = users.getString(user + "_user", null);
            String savedPass = users.getString(user + "_pass", null);

            if (savedUser == null || savedPass == null) {
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!pass.equals(savedPass)) {
                Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show();
                return;
            }

            // store active session
            getSharedPreferences("session", MODE_PRIVATE)
                    .edit()
                    .putString("active_user", user)
                    .apply();

            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, HomePageActivity.class));
            finish();
        });

        tvSignUpLink.setOnClickListener(v -> {
            startActivity(new Intent(this, SignUpActivity.class));
        });
    }
}
