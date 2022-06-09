package com.example.osapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_login = findViewById(R.id.login_btn);
        btn_login.setOnClickListener(v -> {
            Intent i = new Intent(this, Chats.class);
            startActivity(i);
        });

        Button btn_reg = findViewById(R.id.register_log_btn);
        btn_reg.setOnClickListener(v -> {
            Intent i = new Intent(this, Register.class);
            startActivity(i);
        });
    }


}