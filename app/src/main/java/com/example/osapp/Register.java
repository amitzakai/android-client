package com.example.osapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btn_reg = findViewById(R.id.register_btn);
        btn_reg.setOnClickListener(v -> {
            EditText conPass = (EditText)findViewById(R.id.con_pass);
            EditText password = (EditText)findViewById(R.id.password);
            if(password.getText().toString().equals(conPass.getText().toString())) {
                Intent i = new Intent(this, Chats.class);
                startActivity(i);
            } else {
                TextView tv1 = (TextView)findViewById(R.id.textView);
                tv1.setText("Password don't match");
            }
        });
    }


}