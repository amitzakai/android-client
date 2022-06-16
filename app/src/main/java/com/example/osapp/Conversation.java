package com.example.osapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.osapp.adapters.ContactsListAdapter;
import com.example.osapp.adapters.MessageAdapter;
import com.example.osapp.models.Message;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import API.ApiContact;
import API.ApiMessage;

public class Conversation extends AppCompatActivity {



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        RecyclerView lstMessages = findViewById(R.id.lsmessage);

        final MessageAdapter adapter = new MessageAdapter(this);
        lstMessages.setAdapter(adapter);
        lstMessages.setLayoutManager(new LinearLayoutManager(this));
        ApiMessage api = new ApiMessage();
        String user = getIntent().getStringExtra("User");
        String contact = getIntent().getStringExtra("Contact");

        api.getMessagesList(getIntent().getStringExtra("User")
                , getIntent().getStringExtra("Contact"), adapter);


        ImageView imgFavorite = (ImageView) findViewById(R.id.imageView);
        imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.send);
                String content = editText.getText().toString();
                Calendar now = Calendar.getInstance();
                String time = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE);
                editText.setText("");
                Message m = new Message(0, content, time, true);
                api.sendMessage(user, contact, "7249"
                        ,adapter, m);
            }
        });
    }


}