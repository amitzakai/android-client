package com.example.osapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.osapp.models.Contact;
import com.example.osapp.services.MessageService;

import API.ApiContact;

public class AddContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        Button btn_reg = findViewById(R.id.add_c_btn);
        btn_reg.setOnClickListener(v -> {
            Intent i = getIntent();
            ApiContact api = new ApiContact();
            EditText userName = (EditText)findViewById(R.id.add_contact_user);
            String user = userName.getText().toString();
            EditText nickName = (EditText)findViewById(R.id.add_contact_nick);
            String nick = nickName.getText().toString();
            EditText serverName = (EditText)findViewById(R.id.add_contact_server);
            String server = serverName.getText().toString();
            Contact c = new Contact(user, nick, new MessageService(), server, null, null);
            TextView txt = findViewById(R.id.has_this_contact);
            api.addContact(i.getStringExtra("userName"), c, this, txt);
        });
    }
}