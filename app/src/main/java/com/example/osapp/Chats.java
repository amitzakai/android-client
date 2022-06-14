package com.example.osapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

public class Chats extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        RecyclerView lstContacts = findViewById(R.id.lstContacts);
        final ContactsListAdapter adapter = new ContactsListAdapter(this);
        lstContacts.setAdapter(adapter);
        lstContacts.setLayoutManager(new LinearLayoutManager(this));

        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("amit", "zakai", new MessageService(), "server", "last", "lastdate"));
        contacts.add(new Contact("amit2", "zakai2", new MessageService(), "server", "last", "lastdate"));
        contacts.add(new Contact("amit3", "zakai3", new MessageService(), "server", "last", "lastdate"));
        contacts.add(new Contact("amit4", "zakai4", new MessageService(), "server", "last", "lastdate"));
        adapter.setContacts(contacts);
    }

//    @SuppressLint("SetTextI18n")
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Intent i = getIntent();
//        String nickName = i.getStringExtra("nickName");
//        TextView tv = (TextView)findViewById(R.id.nick_name);
//        tv.setText("hello " + nickName);
//    }
}