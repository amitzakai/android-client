package com.example.osapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.osapp.DB.ContactDB;
import com.example.osapp.DB.MessageDB;
import com.example.osapp.Dao.ContactDao;
import com.example.osapp.Dao.MessageDao;
import com.example.osapp.adapters.ContactsListAdapter;
import com.example.osapp.adapters.MessageAdapter;
import com.example.osapp.models.Contact;
import com.example.osapp.models.ContactRemote;
import com.example.osapp.models.Message;
import com.example.osapp.models.MessageRemote;
import com.example.osapp.services.MessageService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import API.ApiContact;
import API.ApiMessage;

public class Conversation extends AppCompatActivity {

    private MessageDao dao;
    private MessageDB db;

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
        String server = getIntent().getStringExtra("Server");


        db = Room.databaseBuilder(getApplicationContext(), MessageDB.class, "messageDb")
                .allowMainThreadQueries().build();
        dao = db.messageDao();
        List<MessageRemote> messageRlist= new ArrayList<>(dao.get(getIntent().getStringExtra("User")
                , getIntent().getStringExtra("Contact")));
        List<Message> messageList= new ArrayList<>();
        int i=0;
        for(MessageRemote mr: messageRlist) {
            Message m = new Message(i++, mr.getContent(), mr.getTime(), mr.getSent());
            messageList.add(m);
        }
        adapter.setMessages(messageList);

        api.getMessagesList(getIntent().getStringExtra("User")
                , getIntent().getStringExtra("Contact"), adapter, dao);


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
                api.sendMessage(user, contact, server
                        ,adapter, m, dao);
            }
        });
    }


}