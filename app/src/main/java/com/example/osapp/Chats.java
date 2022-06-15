package com.example.osapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import android.view.View;
import android.widget.TextView;

import com.example.osapp.adapters.ContactsListAdapter;
import com.example.osapp.models.Contact;
import com.example.osapp.services.MessageService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

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
            j.putExtra("userName", i.getStringExtra("userName"));
            startActivity(j);
        });
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