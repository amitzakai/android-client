package com.example.osapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import android.view.View;

import com.example.osapp.adapters.ContactsListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import API.ApiContact;

public class Chats extends AppCompatActivity {

    private ContactsListAdapter.RecycleViewClickListener listener;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();

        setContentView(R.layout.activity_chats);

        setOnClickListener();

        RecyclerView lstContacts = findViewById(R.id.lscontact);
        final ContactsListAdapter adapter = new ContactsListAdapter(this, this.listener);
        lstContacts.setAdapter(adapter);
        lstContacts.setLayoutManager(new LinearLayoutManager(this));
        ApiContact api = new ApiContact();
        api.getAll(i.getStringExtra("userName"), adapter);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent j = new Intent(this, AddContact.class);
            j.putExtra("userName", i.getStringExtra("userName"));
            startActivity(j);
        });
    }

    private void setOnClickListener() {
        this.listener = new ContactsListAdapter.RecycleViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                RecyclerView lstContacts = findViewById(R.id.lscontact);
                ContactsListAdapter a = (ContactsListAdapter) lstContacts.getAdapter();
                Intent intent = new Intent(getApplicationContext(), Conversation.class);
                intent.putExtra("name", a.getContacts().get(position).getName());
                intent.putExtra("Contact", a.getContacts().get(position).getId());
                intent.putExtra("User", getIntent().getStringExtra("userName"));
                intent.putExtra("Server", a.getContacts().get(position).getServer());

                startActivity(intent);
            }
        };
    }

//    @SuppressLint("SetTextI18n")
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Intent i = getIntent();
//
//        setContentView(R.layout.activity_chats);
//
//        RecyclerView lstContacts = findViewById(R.id.lscontact);
//        final ContactsListAdapter adapter = new ContactsListAdapter(this);
//        lstContacts.setAdapter(adapter);
//        lstContacts.setLayoutManager(new LinearLayoutManager(this));
//        ApiContact api = new ApiContact();
//        api.getAll(i.getStringExtra("userName"), adapter);
//
//    }
}