package com.example.osapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.osapp.adapters.ContactsListAdapter;
import com.example.osapp.adapters.MessageAdapter;
import com.example.osapp.models.Message;

import java.util.ArrayList;
import java.util.List;

import API.ApiContact;

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
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(1, "hello hello hello hello hello hello hello hello hello hello hello hello hello hello","",true));
        messages.add(new Message(2, "hello2lko;nwaefpawepghpawihepwaiehg hello hello lnsafalhello hello","",false));
        messages.add(new Message(3, "hello3","",true));
        messages.add(new Message(4, "hello4","",false));
        adapter.setMessages(messages);

    }

    public void SendMessage(View view){
        EditText message = (EditText) findViewById(R.id.send);
        Log.d("message", message.getText().toString());
    }


}