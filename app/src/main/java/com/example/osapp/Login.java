package com.example.osapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import API.ApiUser;
public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_login = findViewById(R.id.login_btn);
        btn_login.setOnClickListener(v -> {
            EditText username = (EditText)findViewById(R.id.editTextTextPersonName);
            EditText password = (EditText)findViewById(R.id.editTextTextPassword);
            ApiUser api = new ApiUser();
            api.sign_in(username.getText().toString(), password.getText().toString()
                    , (TextView)findViewById(R.id.textView3), this);
        });

        Button btn_reg = findViewById(R.id.register_log_btn);
        btn_reg.setOnClickListener(v -> {

            Intent i = new Intent(this, Register.class);
            startActivity(i);
        });

        FloatingActionButton fab = findViewById(R.id.settings_btn);
        fab.setOnClickListener(v -> {
            Intent j = new Intent(this, Settings.class);
            startActivity(j);
        });

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(Login.this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
            }
        });
    }


}