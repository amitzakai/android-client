package com.example.osapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.osapp.services.MessageService;

import java.util.List;

@Entity
public class Contact {
    @PrimaryKey(autoGenerate = true)
    private String id;
    private String name;
    private MessageService messages;
    private String server;
    private String last;
    private String lastdate;

    public Contact(String id, String name, MessageService messages, String server, String last, String lastdate) {
        this.id = id;
        this.name = name;
        this.messages = messages;
        this.server = server;
        this.last = last;
        this.lastdate = lastdate;
    }

    //id:
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    //name:
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //messages:
    public MessageService getMessages() {
        return messages;
    }
    public void setMessages(MessageService messages) {
        this.messages = messages;
    }

    //server:
    public String getServer() {
        return server;
    }
    public void setServer(String server) {
        this.server = server;
    }

    //last:
    public String getLast() {
        return last;
    }
    public void setLast(String last) {
        this.last = last;
    }

    //lastDate:
    public String getLastdate() {
        return lastdate;
    }
    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }


    public boolean findContact(List<Contact> list) {
        int size = list.size(), i;
        for (i=0; i<size; i++) {
            if ((this.getId().equals(list.get(i).getId()))
                    && (this.getServer().equals(list.get(i).getServer())))
                return true;
        }
        return false;
    }
}
