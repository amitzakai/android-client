package com.example.osapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import android.view.View;

import com.example.osapp.DB.ContactDB;
import com.example.osapp.Dao.ContactDao;
import com.example.osapp.adapters.ContactsListAdapter;
import com.example.osapp.models.Contact;
import com.example.osapp.models.ContactRemote;
import com.example.osapp.services.MessageService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import API.ApiContact;

public class Chats extends AppCompatActivity {

    private ContactDao dao;
    private ContactDB db;
    private ContactsListAdapter adapter;
    private ContactsListAdapter.RecycleViewClickListener listener;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();

        setContentView(R.layout.activity_chats);

        setOnClickListener();

        RecyclerView lstContacts = findViewById(R.id.lscontact);
        adapter = new ContactsListAdapter(this, this.listener);
        lstContacts.setAdapter(adapter);
        lstContacts.setLayoutManager(new LinearLayoutManager(this));

        db = Room.databaseBuilder(getApplicationContext(), ContactDB.class, "contactDb")
                .allowMainThreadQueries().build();
        dao = db.contactDao();
        List<ContactRemote> contactsRlist= new ArrayList<>(dao.get(i.getStringExtra("userName")));
        List<Contact> contactsList= new ArrayList<>();
        for(ContactRemote r: contactsRlist) {
            Contact c = new Contact(r.getId(), r.getName(), new MessageService()
                    , r.getServer(), r.getLast(), r.getLastdate());
            contactsList.add(c);
        }
        adapter.setContacts(contactsList);
        ApiContact api = new ApiContact();
        api.getAll(i.getStringExtra("userName"), adapter, dao);
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

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();
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
        db = Room.databaseBuilder(getApplicationContext(), ContactDB.class, "contactDb")
                .allowMainThreadQueries().build();
        dao = db.contactDao();
        List<ContactRemote> contactsRlist= new ArrayList<>(dao.get(getIntent().getStringExtra("userName")));
        List<Contact> contactsList= new ArrayList<>();
        for(ContactRemote r: contactsRlist) {
            Contact c = new Contact(r.getId(), r.getName(), new MessageService()
                    , r.getServer(), r.getLast(), r.getLastdate());
            contactsList.add(c);
        }
        adapter.setContacts(contactsList);
        ApiContact api = new ApiContact();
        api.getAll(getIntent().getStringExtra("userName"), adapter, dao);
    }
}