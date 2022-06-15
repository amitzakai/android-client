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

        Button btn_reg = findViewById(R.id.add_button);
        btn_reg.setOnClickListener(v -> {
            EditText userName = (EditText)findViewById(R.id.user_name_to_add);
            String user = userName.getText().toString();
            EditText nickName = (EditText)findViewById(R.id.nick_name_to_add);
            String nick = nickName.getText().toString();
            EditText serverName = (EditText)findViewById(R.id.server_name_to_add);
            String server = serverName.getText().toString();
            Contact c = new Contact(user, nick, new MessageService(), server, null, null);
            api.addContact(i.getStringExtra("userName"), c, adapter, this);
        });

    }

//    @SuppressLint("SetTextI18n")
//    @Override
//    protected void onResume() {
//        super.onResume();
////        Intent i = getIntent();
////        setContentView(R.layout.activity_chats);
////
////        RecyclerView lstContacts = findViewById(R.id.lscontact);
////        final ContactsListAdapter adapter = new ContactsListAdapter(this);
////        lstContacts.setAdapter(adapter);
////        lstContacts.setLayoutManager(new LinearLayoutManager(this));
////        ApiContact api = new ApiContact();
////        api.getAll(i.getStringExtra("userName"), adapter);

//    }
}