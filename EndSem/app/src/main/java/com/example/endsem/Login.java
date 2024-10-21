package com.example.endsem;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView tvWelcome = findViewById(R.id.tvWelcome);
        String userEmail = getIntent().getStringExtra("USER_EMAIL");

        tvWelcome.setText("Welcome back, " + userEmail + "!");
    }
}
