package com.example.osapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.osapp.adapters.ContactsListAdapter;
import com.example.osapp.models.Contact;
import com.example.osapp.services.MessageService;

import java.util.ArrayList;
import java.util.List;

import API.ApiContact;

public class Chats extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        String nickName = i.getStringExtra("nickName");
        TextView tv = (TextView)findViewById(R.id.nick_name);
        tv.setText("hello " + nickName);


    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();
        Intent i = getIntent();
        setContentView(R.layout.activity_chats);

        RecyclerView lstContacts = findViewById(R.id.lscontact);
        final ContactsListAdapter adapter = new ContactsListAdapter(this);
        lstContacts.setAdapter(adapter);
        lstContacts.setLayoutManager(new LinearLayoutManager(this));
        ApiContact api = new ApiContact();
        api.getAll(i.getStringExtra("userName"), adapter);
    }
}