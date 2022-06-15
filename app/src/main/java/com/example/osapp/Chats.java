package com.example.osapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.osapp.adapters.ContactsListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import API.ApiContact;

public class Chats extends AppCompatActivity {
//amit
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        setContentView(R.layout.activity_chats);

        RecyclerView lstContacts = findViewById(R.id.lscontact);
        final ContactsListAdapter adapter = new ContactsListAdapter(this);
        lstContacts.setAdapter(adapter);
        lstContacts.setLayoutManager(new LinearLayoutManager(this));
        ApiContact api = new ApiContact();
        api.getAll(i.getStringExtra("userName"), adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent j = new Intent(this, AddContact.class);
            startActivity(j);
        });

    }

//    @SuppressLint("SetTextI18n")
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        Intent i = getIntent();
//        String nickName = i.getStringExtra("nickName");
//        TextView tv = (TextView)findViewById(R.id.nick_name);
//        tv.setText("hello " + nickName);
//
//
//    }
}