package com.example.osapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button btn_set = findViewById(R.id.change_btn);
        btn_set.setOnClickListener(v -> {
            Log.d("put here", "something");
        });
    }
}