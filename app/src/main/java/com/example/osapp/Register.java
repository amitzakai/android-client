package com.example.osapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import API.ApiUser;

public class Register extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btn_reg = findViewById(R.id.register_btn);
        btn_reg.setOnClickListener(v -> {
            EditText userName = (EditText)findViewById(R.id.user_name);
            EditText nickName = (EditText)findViewById(R.id.nick_name);
            EditText conPass = (EditText)findViewById(R.id.con_pass);
            EditText password = (EditText)findViewById(R.id.password);

            ApiUser api = new ApiUser();
            if(password.getText().toString().equals(conPass.getText().toString())) {
                api.register(userName.getText().toString(), nickName.getText().toString()
                        , password.getText().toString(), (TextView)findViewById(R.id.textView2)
                        , this);
            } else {
                TextView tv1 = (TextView)findViewById(R.id.textView);
                tv1.setText("Password don't match");
            }
        });
    }


}